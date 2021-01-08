package ar.ungs.shop.bussines_controllers;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.ungs.shop.dtos.ArticleItemDto;
import ar.ungs.shop.dtos.ShoppingCartDto;
import ar.ungs.shop.entities.ArticleEntity;
import ar.ungs.shop.entities.ArticleItemEntity;
import ar.ungs.shop.repositories.ArticleDao;
import ar.ungs.shop.bussines_controllers.ShoppingCartControllerImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ShoppingCartControllerIT {

	@Autowired
	private ArticleDao articlesDao;
	
	@Autowired
	private ShoppingCartControllerImpl controller;
		
	@Test
	void testSave() {
		ArticleEntity article = articlesDao.save(ArticleEntity.makeTestEntity());
		ArticleItemEntity item = ArticleItemEntity.makeTestEntity(article);
		List<ArticleItemDto> articles = new LinkedList<ArticleItemDto>();
		ArticleItemDto itemDto = new ArticleItemDto(item);
		itemDto.setCount(2);
		articles.add(itemDto);
		
		ShoppingCartDto cart = new ShoppingCartDto();
		cart.setUser("xx");
		cart.setAddress("ddd");
		cart.setArticles(articles);
		
		ShoppingCartDto retrieved = controller.save(cart);
		Assertions.assertNotNull(retrieved);
		Assertions.assertNotNull(retrieved.getId());
		Assertions.assertEquals(retrieved.getArticles().size(), cart.getArticles().size());
		Assertions.assertEquals(retrieved.getUser(), cart.getUser());
		
		Integer oldStock = article.getStock();
		Integer newStock = articlesDao.findById(article.getId()).get().getStock();
		Assertions.assertTrue(oldStock > newStock);
	}
}