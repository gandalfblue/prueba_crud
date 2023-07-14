package com.prueba.crud.core.user.use_cases;

import com.prueba.crud.core.user.User;
import com.prueba.crud.core.user.ports.UserRepositoryService;
import com.prueba.crud.infrastructure.delivery.converters.UserRestConverter;
import com.prueba.crud.infrastructure.shared.exceptions.BadRequestException;
import com.prueba.crud.infrastructure.shared.exceptions.UserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

	private final UserRepositoryService userRepositoryService;

	@Override
	public Mono<Boolean> execute(User user) throws UserException {

		return userRepositoryService.doesUserDniExists(user.getDni()).
				flatMap(userExist ->{
							if (!userExist) Mono.error(new BadRequestException("No existe el usuario a eliminar"));
							return userRepositoryService
									.deleteUser(user)
									.hasElement();
				});
	}
}
