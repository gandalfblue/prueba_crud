package com.prueba.crud.infrastructure.configuration;

import com.prueba.crud.core.user.use_cases.CreateUserUseCaseImpl;
import com.prueba.crud.core.user.use_cases.DeleteUserUseCaseImpl;
import com.prueba.crud.core.user.use_cases.GetAllUsersUseCaseImpl;
import com.prueba.crud.core.user.use_cases.UpdateUserUseCaseImpl;
import com.prueba.crud.infrastructure.delivery.converters.UserRestConverter;
import com.prueba.crud.infrastructure.persistence.converters.UserRepositoryConverter;
import com.prueba.crud.infrastructure.persistence.impl.UserServiceImpl;
import com.prueba.crud.infrastructure.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

	private final UserRepository userRepository;

	public UserConfiguration(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public UserRepositoryConverter userRepositoryConverterBean() {
		return new UserRepositoryConverter();
	}

	@Bean
	public UserRestConverter userRestConverterBean() {
		return new UserRestConverter();
	}

	@Bean
	public UserServiceImpl userServiceImplBean() {
		return new UserServiceImpl(userRepository, userRepositoryConverterBean());
	}

	@Bean
	public GetAllUsersUseCaseImpl getAllCategoriesUseCaseBean() {
		return new GetAllUsersUseCaseImpl(userServiceImplBean(), userRestConverterBean() );
	}
	
	@Bean
	public CreateUserUseCaseImpl createUserUseCaseBean() {
		return new CreateUserUseCaseImpl(userServiceImplBean(), userRestConverterBean());
	}

	@Bean
	public DeleteUserUseCaseImpl deleteUserUseCaseBean() {
		return new DeleteUserUseCaseImpl(userServiceImplBean());
	}

	@Bean
	public UpdateUserUseCaseImpl updateUserUseCaseBean() {
		return new UpdateUserUseCaseImpl(userServiceImplBean(), userRestConverterBean());
	}
}