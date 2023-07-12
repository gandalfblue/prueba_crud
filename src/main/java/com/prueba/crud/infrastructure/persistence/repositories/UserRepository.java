package com.prueba.crud.infrastructure.persistence.repositories;

import com.prueba.crud.core.user.User;
import com.prueba.crud.infrastructure.persistence.entities.UserEntity;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserEntity, Long> {

	public Mono<Boolean> findByDni(Integer name);

	public Mono<User> deleteUser(User user);
}
