package ar.ungs.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.ungs.shop.entities.ShoppingCartEntity;

@Repository
public interface ShoppingCartDao extends JpaRepository<ShoppingCartEntity, Integer>{
}
