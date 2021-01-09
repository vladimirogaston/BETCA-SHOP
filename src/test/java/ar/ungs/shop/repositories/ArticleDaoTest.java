package ar.ungs.shop.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ar.ungs.shop.entities.ArticleEntity;
import org.springframework.context.annotation.Profile;

@DataJpaTest
class ArticleDaoTest {

	@Autowired
	private ArticleDao dao;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFindByBarcode() {
		ArticleEntity entity = ArticleEntity.makeTestEntity();
		entity = dao.save(entity);
		Assertions.assertNotNull(entity);
		ArticleEntity target = dao.findByBarcode(entity.getBarcode());
		Assertions.assertNotNull(target);
		Assertions.assertTrue(target.equals(entity));
	}
	
	@Test
	void testFindWhereNotDiscontinued() {
		ArticleEntity entity = ArticleEntity.makeTestEntity();
		entity = dao.save(entity);
		Assertions.assertEquals(dao.findNotDiscontinued().size(), 1);
	}
	
	@Test
	void testFindWhereNotDiscontinuedFails() {
		ArticleEntity entity = ArticleEntity.makeTestEntity();
		entity.setDiscontinued(true);
		entity = dao.save(entity);
		Assertions.assertEquals(dao.findNotDiscontinued().size(), 0);
	}
}