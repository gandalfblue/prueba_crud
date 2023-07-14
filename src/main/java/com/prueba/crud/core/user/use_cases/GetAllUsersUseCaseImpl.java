package com.prueba.crud.core.user.use_cases;

import com.prueba.crud.core.user.ports.UserRepositoryService;
import com.prueba.crud.infrastructure.delivery.converters.UserRestConverter;
import com.prueba.crud.infrastructure.delivery.rest.UserRest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase {

	private final UserRepositoryService userRepositoryService;
	private final UserRestConverter userRestConverter;

	@Override
	public Flux<UserRest> execute() {
		return userRepositoryService
				.getAllUsers()
				.map(userRestConverter::mapToRest);
	}
}
