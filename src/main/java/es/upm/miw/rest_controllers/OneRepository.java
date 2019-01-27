package es.upm.miw.rest_controllers;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OneRepository extends MongoRepository<One,String> {
}
