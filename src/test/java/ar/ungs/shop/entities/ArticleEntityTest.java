package ar.ungs.shop.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ar.ungs.shop.dtos.ArticleDto;

class ArticleEntityTest {

	@Test
	void test() {
		ArticleEntity entity = ArticleEntity.makeTestEntity();
		ArticleDto dto = new ArticleDto(entity);
		Assertions.assertTrue(entity.equals(dto.toEntity()));
	}
}
