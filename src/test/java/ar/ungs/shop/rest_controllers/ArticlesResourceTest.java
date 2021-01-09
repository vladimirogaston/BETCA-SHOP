package ar.ungs.shop.rest_controllers;

import ar.ungs.shop.dtos.ArticleDto;
import ar.ungs.shop.services.DatabaseSeederService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArticlesResourceTest {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseSeederService seeder;

	@BeforeEach
	public void setUp() {
		seeder.clearDatabase();
		seeder.seedDatabase();
	}

    @Test
	void testGetArticlesResourceThrowsExceptionByBadUri() {
		Assertions.assertThrows(HttpClientErrorException.class ,()-> {
				new RestBuilder<>(port).path(ArticlesResource.ARTICLES + "/caja").get().build();
			}
		);
	}

	@Test
	void test() throws JsonProcessingException {
		ArticleDto [] articles = new RestBuilder<ArticleDto[]>(port).clazz(ArticleDto[].class).path(ArticlesResource.ARTICLES).get().build();
		Assertions.assertEquals(articles.length, 2);
	}

	@Test
	void testSecurity() {
		Assertions.assertThrows(HttpClientErrorException.class, ()-> new RestBuilder<ArticleDto>(port)
				.clazz(ArticleDto.class)
				.path(ArticlesResource.ARTICLES)
				.basicAuth("u001", "pu001")
				.body(makeTestDto()).post().build()
		);
	}

	private ArticleDto makeTestDto() {
		ArticleDto target = new ArticleDto();
		target.setDescription("temp");
		target.setBarcode((long) 9988772);
		target.setPrice(20.10);
		target.setRegistrationDate(new Date());
		target.setProvider("Arcor");
		target.setStock(21);
		target.setDiscontinued(false);
		return target;
	}
}
