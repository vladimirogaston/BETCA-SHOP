package ar.ungs.shop.bussines_controllers;

import java.util.List;

import ar.ungs.shop.dtos.ArticleDto;

public interface ArticlesController {

	List<ArticleDto> readAll();

	void discontinue(Integer id);

	ArticleDto update(Integer id, ArticleDto article);

	ArticleDto save(ArticleDto article);

}
