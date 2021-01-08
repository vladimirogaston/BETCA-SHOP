package ar.ungs.shop.bussines_controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.ungs.shop.exceptions.BadRequestException;
import ar.ungs.shop.exceptions.NotFoundException;
import ar.ungs.shop.dtos.ArticleItemDto;
import ar.ungs.shop.dtos.ShoppingCartDto;
import ar.ungs.shop.entities.ArticleEntity;
import ar.ungs.shop.entities.ArticleItemEntity;
import ar.ungs.shop.entities.ShoppingCartEntity;
import ar.ungs.shop.repositories.ArticleDao;
import ar.ungs.shop.repositories.ShoppingCartDao;

@Controller
public class ShoppingCartControllerImpl implements ShoppingCartController {
	
	private static final String STOCK_BAD_REQUEST = "No hay suficiente stock del articulo para efectuar la venta.";

	private static final String ADULTERED_BAD_REQUEST = "El articulo está adulterado";

	private ArticleDao articleDao;

	private ShoppingCartDao shoppingCartDao;
	
	public ShoppingCartControllerImpl() {
		articleDao = null;
		shoppingCartDao = null;
	}
	
	@Override
	public ShoppingCartDto save(ShoppingCartDto dto) {
		ShoppingCartEntity target = makeFromDto(dto);
		ShoppingCartEntity saved = shoppingCartDao.save(target);
		updateArticlesStock(saved);
		return new ShoppingCartDto(saved);
	}
	
	private void updateArticlesStock(ShoppingCartEntity shoppingCart) {
			shoppingCart.getArticles().forEach((data)-> {
			ArticleEntity article = data.getArticle();
			Integer newStock = article.getStock() - data.getCount();
			article.setStock(newStock);
			articleDao.saveAndFlush(article);
		});
	}
	
	private ShoppingCartEntity makeFromDto(ShoppingCartDto dto) {
		ShoppingCartEntity ret = new ShoppingCartEntity();
		ret.setAddress(dto.getAddress());
		ret.setUser(dto.getUser());
		ret.setArticles(makeFromArticlesItemDtos(dto.getArticles()));
		return ret;
	}
	
	private List<ArticleItemEntity> makeFromArticlesItemDtos(List<ArticleItemDto> dto) {
		List<ArticleItemEntity> ret = new LinkedList<>();
		dto.forEach(data -> {
			ArticleItemEntity item = makeValidItem(data);
			ret.add(item);
		});
		return ret;
	}

	private ArticleItemEntity makeValidItem(ArticleItemDto data) {
		ArticleEntity article = articleDao.findById(data.getArticle().getId()).orElseThrow(NotFoundException::new);
		
		/**
		 * 
		 *TODO es importante añadir esta validación. La fecha se almacena en un formato diferente al que se crea... revisar eso 
		 * if(!article.equals(data.getArticle().toEntity())) throw new BadRequestException(ADULTERED_BAD_REQUEST);
		 */	

		if(article.getStock() < data.getCount()) throw new BadRequestException(STOCK_BAD_REQUEST);
		ArticleItemEntity item = new ArticleItemEntity();
		item.setCount(data.getCount());
		item.setArticle(article);
		return item;
	}

	@Autowired
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	@Autowired
	public void setShoppingCartDao(ShoppingCartDao shoppingCartDao) {
		this.shoppingCartDao = shoppingCartDao;
	}
}