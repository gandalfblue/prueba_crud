package com.prueba.crud.core.user.use_cases;

import com.prueba.crud.core.user.User;
import com.prueba.crud.infrastructure.delivery.rest.UserRest;
import com.prueba.crud.infrastructure.shared.exceptions.UserException;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface CreateUserUseCase {
	public Mono<UserRest> execute(User user) throws UserException;
}
