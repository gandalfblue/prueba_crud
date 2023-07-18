package com.prueba.crud.core.user.use_cases;

import com.prueba.crud.core.user.User;
import com.prueba.crud.core.user.ports.UserRepositoryService;
import com.prueba.crud.infrastructure.delivery.converters.UserRestConverter;
import com.prueba.crud.infrastructure.delivery.rest.UserRest;
import com.prueba.crud.infrastructure.shared.exceptions.BadRequestException;
import com.prueba.crud.infrastructure.shared.exceptions.NotFoundException;
import com.prueba.crud.infrastructure.shared.exceptions.UserException;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

	private final UserRepositoryService userRepositoryService;
	private final UserRestConverter userRestConverter;

	@Override
	public Mono<UserRest> execute(User user) throws UserException {
		return userRepositoryService.doesUserDniExists(user.getDni()).
				flatMap(userExist ->{
							if (!userExist) Mono.error(new NotFoundException("404 No existe el usuario en la DB"));
							return userRepositoryService
									.updateUser(user)
									.map(userRestConverter::mapToRest);
				});
	}
}
