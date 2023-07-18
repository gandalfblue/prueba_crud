package com.prueba.crud.core.user.use_cases;

import com.prueba.crud.core.user.User;
import com.prueba.crud.core.user.ports.UserRepositoryService;
import com.prueba.crud.infrastructure.delivery.converters.UserRestConverter;
import com.prueba.crud.infrastructure.shared.exceptions.BadRequestException;
import com.prueba.crud.infrastructure.shared.exceptions.NotFoundException;
import com.prueba.crud.infrastructure.shared.exceptions.UserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

	private final UserRepositoryService userRepositoryService;

	@Override
	public Mono<Void> execute(User user) throws UserException {
		return userRepositoryService.doesUserDniExists(user.getDni()).
				flatMap(userExist ->{
							if (!userExist) Mono.just(new NotFoundException("404 User Not Found"));
							return userRepositoryService
									.deleteUser(user);
				});
	}
}
