package com.prueba.crud.core.user.ports;

import com.prueba.crud.core.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepositoryService {

	public Flux<User> getAllUsers();
	
	public Mono<User> saveUser(User user);

	public Mono<Void> deleteUser(User user);

	public Mono<User> updateUser(User user) ;

	public Mono<Boolean> doesUserDniExists(Integer dni);
}
