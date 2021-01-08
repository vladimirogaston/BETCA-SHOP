package ar.ungs.shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.ungs.shop.entities.ArticleEntity;

@Repository
public interface ArticleDao extends JpaRepository<ArticleEntity, Integer>{

	ArticleEntity findByBarcode(Long barcode);
	
	@Query("SELECT a FROM ArticleEntity a WHERE a.discontinued = FALSE")
	List<ArticleEntity> findNotDiscontinued();
}
