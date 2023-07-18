package com.prueba.crud.infrastructure.delivery;

import com.prueba.crud.infrastructure.delivery.responses.UserResponse;
import com.prueba.crud.infrastructure.delivery.rest.UserRest;
import com.prueba.crud.infrastructure.shared.exceptions.UserException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserController {

	Flux<UserResponse<UserRest>> getAllUsers() throws UserException;
	Mono<UserResponse<UserRest>> createUser(UserRest user) throws UserException;
	Mono<UserResponse<Void>> deleteUser(UserRest userRest) throws UserException;
	Mono<UserResponse<UserRest>> updateUser(UserRest user) throws UserException;
}
