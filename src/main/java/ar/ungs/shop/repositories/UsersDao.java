package ar.ungs.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.ungs.shop.entities.UserEntity;

@Repository
public interface UsersDao extends JpaRepository<UserEntity, Integer> {

	UserEntity findByName(String name);
}
