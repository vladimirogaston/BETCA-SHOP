package ar.ungs.shop.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ar.ungs.shop.entities.ArticleEntity;
import ar.ungs.shop.entities.ArticleItemEntity;

class ArticleItemDtoTest {

	@Test
	void test() {
		ArticleItemEntity entity = ArticleItemEntity.makeTestEntity(ArticleEntity.makeTestEntity());
		ArticleItemDto dto = new ArticleItemDto(entity);
		Assertions.assertEquals(dto.getCount(), entity.getCount());
		Assertions.assertEquals(dto.getArticle().getBarcode(), entity.getArticle().getBarcode());
		Assertions.assertEquals(dto.getArticle().getDescription(), entity.getArticle().getDescription());
	}

}
