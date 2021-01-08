package ar.ungs.shop.services;

import java.util.LinkedList;
import java.util.List;

import ar.ungs.shop.entities.ArticleEntity;
import ar.ungs.shop.entities.UserEntity;

public class DatabaseGraph {

	private List<UserEntity> users;

	private List<ArticleEntity> articles;

	public DatabaseGraph() {
		setUsers(new LinkedList<>());
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<ArticleEntity> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleEntity> articles) {
		this.articles = articles;
	}
}