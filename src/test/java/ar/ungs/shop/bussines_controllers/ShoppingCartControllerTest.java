package ar.ungs.shop.bussines_controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.ungs.shop.dtos.ArticleItemDto;
import ar.ungs.shop.entities.ArticleEntity;
import ar.ungs.shop.entities.ArticleItemEntity;
import ar.ungs.shop.repositories.ArticleDao;
import ar.ungs.shop.repositories.ShoppingCartDao;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ShoppingCartControllerTest {

	@MockBean
	private ArticleDao articlesDao;
	
	@MockBean
	private ShoppingCartDao shoppingDao;
	
	@Autowired
	private ShoppingCartControllerImpl controller;

	@Test
	void test() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArticleEntity article = ArticleEntity.makeTestEntity();
		article.setId(1);
		ArticleItemEntity entity = ArticleItemEntity.makeTestEntity(article);
		entity.setId(1);
		ArticleItemDto dto = new ArticleItemDto(entity);
		Mockito.when(articlesDao.findById(article.getId())).thenReturn(Optional.ofNullable(article));	
		Method method = controller.getClass().getDeclaredMethod("makeValidItem", new Class[] {ArticleItemDto.class});
		method.setAccessible(true);
		ArticleItemEntity res = (ArticleItemEntity) method.invoke(controller, dto);
		Assertions.assertNotNull(res);
	}
	
	@Test
	void testThrowsNotFoundExceptionByNotFoundArticle() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArticleEntity article = ArticleEntity.makeTestEntity();
		article.setId(13);
		ArticleItemEntity entity = ArticleItemEntity.makeTestEntity(article);
		entity.setId(13);
		ArticleItemDto dto = new ArticleItemDto(entity);
		
		Mockito.when(articlesDao.findById(article.getId())).thenReturn(Optional.empty());
		excecuteTest(dto);
	}
	
	@Test
	void testThrowsBadRequestByCountVsStock() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArticleEntity article = ArticleEntity.makeTestEntity();
		article.setId(13);
		article.setStock(0);
		ArticleItemEntity entity = ArticleItemEntity.makeTestEntity(article);
		entity.setCount(19);
		entity.setId(13);
		ArticleItemDto dto = new ArticleItemDto(entity);		
		Mockito.when(articlesDao.findById(article.getId())).thenReturn(Optional.ofNullable(article));
		
		excecuteTest(dto);
	}
		
	private void excecuteTest(ArticleItemDto dto) throws NoSuchMethodException, SecurityException {
		Method method = controller.getClass().getDeclaredMethod("makeValidItem", new Class[] {ArticleItemDto.class});
		method.setAccessible(true);
		Assertions.assertThrows(InvocationTargetException.class, ()->{
			method.invoke(controller, dto);	
		});
	}
}