package ar.ungs.shop.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.ungs.shop.entities.ArticleEntity;

class ArticleDtoTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testEqualsNotFails() {
		ArticleEntity entity = ArticleEntity.makeTestEntity();
		ArticleDto dto = new ArticleDto(entity);
		Assertions.assertTrue(entity.equals(dto.toEntity()));
	}
	
	@Test
	void testEqualsFails() {
		ArticleEntity entity = ArticleEntity.makeTestEntity();
		ArticleDto dto = new ArticleDto(entity);
		dto.setDescription("ffxxg");
		Assertions.assertFalse(entity.equals(dto.toEntity()));
	}
}
