package ar.ungs.shop.dtos;

import ar.ungs.shop.entities.ArticleItemEntity;

public class ArticleItemDto {

	private Integer id;
	
	private Integer count;
	
	private ArticleDto article;

	public ArticleItemDto() {}
	
	public ArticleItemDto(ArticleItemEntity item) {
		setId(item.getId());
		setCount(item.getCount());
		setArticle(new ArticleDto(item.getArticle()));
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public ArticleDto getArticle() {
		return article;
	}

	public void setArticle(ArticleDto article) {
		this.article = article;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}