package com.prueba.crud.core.user.use_cases;

import com.prueba.crud.core.user.User;
import com.prueba.crud.core.user.ports.UserRepositoryService;
import com.prueba.crud.infrastructure.delivery.converters.UserRestConverter;
import com.prueba.crud.infrastructure.delivery.rest.UserRest;
import com.prueba.crud.infrastructure.shared.exceptions.BadRequestException;
import com.prueba.crud.infrastructure.shared.exceptions.UserException;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

	private final UserRepositoryService userRepositoryService;
	private final UserRestConverter userRestConverter;

	@Override
	public Mono<UserRest> execute(User user) throws UserException {

		return userRepositoryService.doesUserDniExists(user.getDni()).
				flatMap(userExist ->{
							if (userExist) Mono.error(new BadRequestException("Ya existe creado este usuario"));
							return userRepositoryService
									.saveUser(user)
									.map(userRestConverter::mapToRest);
				});
	}
}
