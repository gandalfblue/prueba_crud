package com.prueba.crud.infrastructure.persistence.repositories;

import com.prueba.crud.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserEntity, Long> {

	Mono<Boolean> findByDni(Integer name);
}
