package ar.ungs.shop.rest_controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import ar.ungs.shop.services.DatabaseSeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.ungs.shop.bussines_controllers.ArticlesControllerImpl;
import ar.ungs.shop.dtos.ArticleDto;

@RestController
@RequestMapping(value = ArticlesResource.ARTICLES)
public class ArticlesResource {

	public static final String ARTICLES = "/articles";
	
	public static final String CODE = "/{id}";
	
	private ArticlesControllerImpl controller;

	@Autowired
	private DatabaseSeederService seeder;

	@PostConstruct
	private void init() {
		seeder.seedDatabase();
	}

	public ArticlesResource() {
	}
	
	@Autowired
	public void setController(ArticlesControllerImpl controller) {
		this.controller = controller;
	}

	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@PostMapping
	public ArticleDto save(@Valid @RequestBody ArticleDto article) {
		return controller.save(article);
	}
	
	@PutMapping(value = CODE)
	public ArticleDto update(@PathVariable(value = "id") Integer id, @RequestBody ArticleDto article) {
		return controller.update(id, article);
	}
	
	@DeleteMapping(value = CODE)
	public void delete(@PathVariable(value = "id") Integer id) {
		controller.discontinue(id);
	}
	
	@GetMapping
	public List<ArticleDto> readAll() {
		return controller.readAll();
	}
}