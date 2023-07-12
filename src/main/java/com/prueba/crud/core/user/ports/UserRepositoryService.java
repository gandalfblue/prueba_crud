package com.prueba.crud.core.user.ports;

import com.prueba.crud.core.user.User;
import com.prueba.crud.infrastructure.persistence.entities.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepositoryService {

	public Flux<User> getAllUsers();
	
	public Mono<UserEntity> saveUser(User user);

	public Mono<Boolean> deleteUser(User user);

	public Mono<UserEntity> updateUser(User user) ;

	public Mono<Boolean> doesUserDniExists(Integer dni);
}
