package com.prueba.crud.core.user.use_cases;

import com.prueba.crud.core.user.User;
import com.prueba.crud.infrastructure.shared.exceptions.UserException;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeleteUserUseCase {
	public Mono<Boolean> execute(User user) throws UserException;
}
