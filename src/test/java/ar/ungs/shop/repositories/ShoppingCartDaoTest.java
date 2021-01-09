package ar.ungs.shop.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ar.ungs.shop.entities.ArticleEntity;
import ar.ungs.shop.entities.ArticleItemEntity;
import ar.ungs.shop.entities.ShoppingCartEntity;
import org.springframework.context.annotation.Profile;

@Profile("qa")
@DataJpaTest
class ShoppingCartDaoTest {

	@Autowired
	private ShoppingCartDao dao;
	
	@Autowired
	private ArticleDao articleDao;

	@Test
	void testSave() {
		ArticleEntity article = ArticleEntity.makeTestEntity();
		article = articleDao.save(article);
		
		ArticleEntity article2 = new ArticleEntity();
		article2.setBarcode((long)33125531);
		article2.setPrice(2.2);
		article2.setDescription("f");
		article2.setProvider("cc");
		article2.setRegistrationDate(new Date());
		article2.setStock(13);
		article2 = articleDao.save(article2);
		
		ArticleItemEntity item = ArticleItemEntity.makeTestEntity(article);
		ArticleItemEntity item2 = ArticleItemEntity.makeTestEntity(article2);
		List<ArticleItemEntity> items = new LinkedList<>();
		items.add(item);
		items.add(item2);
		
		ShoppingCartEntity cart = new ShoppingCartEntity();
		cart.setUser("johndoe");
		cart.setAddress("vdm");
		cart.setArticles(items);
		cart.setRegistryDate(new Date());
		cart = dao.save(cart);
		
		assertNotNull(article);
		assertNotNull(cart);
		assertTrue(cart.getId() != null);
		assertEquals(articleDao.findAll().size(), 2);
		assertEquals(dao.findAll().size(), 1);
		assertEquals(dao.findAll().get(0).getArticles().get(0).getId(), article.getId());
		assertNotNull(dao.findAll().get(0).getArticles().get(0).getId());
	}
}