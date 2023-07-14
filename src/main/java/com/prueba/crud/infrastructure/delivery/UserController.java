package com.prueba.crud.infrastructure.delivery;

import com.prueba.crud.infrastructure.delivery.responses.UserResponse;
import com.prueba.crud.infrastructure.delivery.rest.UserRest;
import com.prueba.crud.infrastructure.shared.exceptions.UserException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserController {

	UserResponse<Flux<UserRest>> getAllUsers() throws UserException;
	UserResponse<Mono<UserRest>> createUser(UserRest user) throws UserException;
	UserResponse<Mono<Boolean>> deleteUser(UserRest userRest) throws UserException;
	UserResponse<Mono<UserRest>> updateUser(UserRest user) throws UserException;
}
