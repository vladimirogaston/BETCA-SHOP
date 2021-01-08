package ar.ungs.shop.bussines_controllers;

import ar.ungs.shop.dtos.ArticleDto;
import ar.ungs.shop.entities.ArticleEntity;
import ar.ungs.shop.exceptions.ConflictException;
import ar.ungs.shop.exceptions.ForbiddenException;
import ar.ungs.shop.exceptions.NotFoundException;
import ar.ungs.shop.repositories.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ArticlesControllerImpl implements ArticlesController {
	
	private static final String SAVE_CONFLICT_EXCEPTION = "El código de barra ingresado ya está en uso por otro artículo.";

	private static final String STOCK_FORBIDDEN = "El artículo todavía posee stock. No puede discontinuarse.";

	private static final String CORRUPTION_FORBIDDEN = "No puede alterar el id del artículo.";

	private ArticleDao dao;
	
	public ArticlesControllerImpl() {
	}

	@Override
	public ArticleDto save(ArticleDto article) {
		if(dao.findByBarcode(article.getBarcode()) != null) throw new ConflictException(SAVE_CONFLICT_EXCEPTION);
		ArticleEntity target = buildFromDto(article);
		if(target.getRegistrationDate() == null) target.setRegistrationDate(new Date());
		ArticleEntity saved = dao.save(target);
		article.setId(saved.getId());
		return article;
	}
	
	@Override
	public ArticleDto update(Integer id, ArticleDto article) {
		ArticleEntity target = dao.findById(id).orElseThrow(NotFoundException::new);
		ArticleEntity barcodedTarget = dao.findByBarcode(article.getBarcode());
		if(target.getId() != barcodedTarget.getId()) throw new ConflictException(SAVE_CONFLICT_EXCEPTION);
		if(id != article.getId()) throw new ForbiddenException(CORRUPTION_FORBIDDEN);
		target = buildFromDto(article);
		target.setId(id);
		dao.saveAndFlush(target);
		return article;		
	}

	private ArticleEntity buildFromDto(ArticleDto article) {
		ArticleEntity target = new ArticleEntity();
		target.setBarcode(article.getBarcode());
		target.setDescription(article.getDescription());
		target.setPrice(article.getPrice());
		target.setProvider(article.getProvider());
		target.setRegistrationDate(article.getRegistrationDate());
		return target;
	}
	
	@Override
	public void discontinue(Integer id) {
		ArticleEntity target = dao.findById(id).orElseThrow(NotFoundException::new);
		if(target.getStock() != 0) throw new ForbiddenException(STOCK_FORBIDDEN);
		target.setDiscontinued(true);
		dao.save(target);
	}

	@Override
	public List<ArticleDto> readAll() {
		return dao.findNotDiscontinued().stream().map(ArticleDto::new).collect(Collectors.toList());
	}

	@Autowired
	public void setDao(ArticleDao dao) {
		this.dao = dao;
	}
}