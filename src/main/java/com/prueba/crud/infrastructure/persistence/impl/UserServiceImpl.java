package com.prueba.crud.infrastructure.persistence.impl;

import com.prueba.crud.core.user.User;
import com.prueba.crud.core.user.ports.UserRepositoryService;
import com.prueba.crud.infrastructure.persistence.converters.UserRepositoryConverter;
import com.prueba.crud.infrastructure.persistence.repositories.UserRepository;
import com.prueba.crud.infrastructure.shared.exceptions.BadRequestException;
import com.prueba.crud.infrastructure.shared.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserRepositoryService {

	private final UserRepository userRepository;

	private final UserRepositoryConverter userRepositoryConverter;

	@Override
	public Flux<User> getAllUsers() {
		return userRepository
				.findAll()
				.map(userRepositoryConverter::mapToEntity);
	}

	@Override
	public Mono<User> saveUser(User user) {
		return doesUserDniExists(user.getDni())
				.flatMap(userNoExist ->{
							if (!userNoExist) {
								return userRepository
										.save(userRepositoryConverter.mapToDocument(user))
										.map(userRepositoryConverter::mapToEntity);

							}
							return Mono.error(new BadRequestException("400 Ya existe un usuario con ese DNI"));
				});

	}

	@Override
	public Mono<Void> deleteUser(User user) {
		return doesUserDniExists(user.getDni())
				.flatMap(userExist ->{
					if (userExist) {
						return userRepository
									.deleteById(userRepositoryConverter.mapToDocument(user).getId());}
					return Mono.error(new NotFoundException("404 User not found"));
				});
	}

	@Override
	public Mono<User> updateUser(User user) {
		return userRepository
				.save(userRepositoryConverter.mapToDocument(user))
				.map(userRepositoryConverter::mapToEntity)
				.onErrorResume(error -> {
					if(error.equals(HttpStatus.NOT_FOUND)){
						return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND));
					}
					return Mono.error(new BadRequestException("400 bad Request"));
				});
	}

	@Override
	public Mono<Boolean> doesUserDniExists(Integer dni) {
		return findByDni(dni)
				.onErrorResume(error -> {
					if(error.equals(HttpStatus.NOT_FOUND)){
						return Mono.error(new NotFoundException("404 User not found"));
					}
					return Mono.error(new BadRequestException("400 bad Request"));
				});
	}

	@Override
	public Mono<Boolean> findByDni(Integer dni) {
		return getAllUsers()
				.filter(user -> dni.equals(user.getDni()))
				.map(filteredUser -> Optional.ofNullable(filteredUser)
						.map(optionalUser -> Boolean.TRUE).orElse(null))
				.hasElements();
	}
}
