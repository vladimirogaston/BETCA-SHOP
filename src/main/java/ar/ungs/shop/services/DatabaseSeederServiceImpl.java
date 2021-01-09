package ar.ungs.shop.services;

import ar.ungs.shop.entities.ArticleEntity;
import ar.ungs.shop.repositories.ArticleDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import ar.ungs.shop.entities.UserEntity;
import ar.ungs.shop.repositories.UsersDao;

import java.io.InputStream;

@Service
public class DatabaseSeederServiceImpl implements DatabaseSeederService {

	private final String ymlFileName = "db.yml";

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private ArticleDao articlesDao;

	private DatabaseGraph graph;

	public DatabaseSeederServiceImpl() {
		this.graph = new DatabaseGraph();
	}

	@Override
	public void seedDatabase() {
		this.graph = loadDatabaseGraph();
		for(UserEntity entity : this.graph.getUsers()) {
			UserEntity saved = usersDao.saveAndFlush(entity);
			LogManager.getLogger().log(Level.INFO, "DB::SEED > " + saved.toString());
		}
		for(ArticleEntity entity : this.graph.getArticles()) {
			ArticleEntity saved = articlesDao.saveAndFlush(entity);
			LogManager.getLogger().log(Level.INFO, "DB::SEED > " + saved.toString());
		}
	}

	@Override
	public void clearDatabase() {
		this.usersDao.deleteAll();
		this.articlesDao.deleteAll();
	}

	private DatabaseGraph loadDatabaseGraph() {
		DatabaseGraph graph = null;
		try {
			LogManager.getLogger(this.getClass()).log(Level.INFO,
					"Seed database operation status: [INITIALIZED - LOADING .YML]");
			Yaml yaml = new Yaml(new Constructor(DatabaseGraph.class));
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(ymlFileName);
			graph = yaml.load(inputStream);
		} catch (Exception e) {
			LogManager.getLogger(this.getClass()).log(Level.ERROR,
					"Seed database operation status: [ABORT - ERROR LOADING .YML, " + e.getMessage() + "]");
		}
		return graph;
	}
}