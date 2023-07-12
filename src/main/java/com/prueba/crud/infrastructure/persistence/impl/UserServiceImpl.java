package com.prueba.crud.infrastructure.persistence.impl;

import com.prueba.crud.core.user.User;
import com.prueba.crud.core.user.ports.UserRepositoryService;
import com.prueba.crud.infrastructure.persistence.converters.UserRepositoryConverter;
import com.prueba.crud.infrastructure.persistence.entities.UserEntity;
import com.prueba.crud.infrastructure.persistence.repositories.UserRepository;
import com.prueba.crud.infrastructure.shared.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
@Validated
public class UserServiceImpl implements UserRepositoryService {

	private final UserRepository userRepository;

	private final UserRepositoryConverter userRepositoryConverter;


	@Override
	public Flux<User> getAllUsers() {
		return userRepository
				.findAll()
				.map(user -> userRepositoryConverter.mapToEntity(user));
	}

	@Override
	public Mono<UserEntity> saveUser(User user) {
		return doesUserDniExists(user.getDni())
				.flatMap(userNoExist ->{
							if (!userNoExist) {
								return userRepository
										.save(userRepositoryConverter.mapToDocument(user));

							}
							return Mono.error(new BadRequestException("Ya existe un usuario con ese DNI"));
				});

	};

	@Override
	public Mono<Boolean> deleteUser(User user) {
		return doesUserDniExists(user.getDni())
				.flatMap(userNoExist ->{
					if (!userNoExist) {
						return userRepository
									.deleteUser(user)
									.switchIfEmpty(Mono.defer(() -> userRepository.deleteUser(user)))
									.hasElement();}
					return Mono.error(new BadRequestException("El usuario no existe"));
				});
	}

	@Override
	public Mono<UserEntity> updateUser(User user) {
		return userRepository
				.save(userRepositoryConverter.mapToDocument(user))
				.onErrorResume(error -> {
					if(error.equals(HttpStatus.NOT_FOUND)){
						return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND));
					}
					return Mono.error(new BadRequestException("Su petición no se pudo completar"));
				});
	}

	@Override
	public Mono<Boolean> doesUserDniExists(Integer dni) {
		return userRepository
				.findByDni(dni)
				.hasElement();
	}
}
