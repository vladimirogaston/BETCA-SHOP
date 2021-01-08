package ar.ungs.shop.dtos;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ar.ungs.shop.dtos.validations.ListNotEmpty;
import ar.ungs.shop.entities.ShoppingCartEntity;

public class ShoppingCartDto {

	@JsonInclude(value = Include.NON_NULL)
	private Integer id;
	
	@Max(value = 20)
	@NotBlank
	private String user;
	
	@Max(value = 20)
	@NotBlank
	private String address;
	
	@ListNotEmpty
	private List<ArticleItemDto> articles;

	public ShoppingCartDto() {}
	
	public ShoppingCartDto(ShoppingCartEntity saved) {
		setId(saved.getId());
		setUser(saved.getUser());
		setAddress(saved.getAddress());
		setArticles(saved.getArticles().stream().map(ArticleItemDto::new).collect(Collectors.toList()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ArticleItemDto> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleItemDto> articles) {
		this.articles = articles;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
