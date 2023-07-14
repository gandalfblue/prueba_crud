package com.prueba.crud.core.user.use_cases;

import com.prueba.crud.infrastructure.delivery.rest.UserRest;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface GetAllUsersUseCase {
	public Flux<UserRest> execute();
	
}
