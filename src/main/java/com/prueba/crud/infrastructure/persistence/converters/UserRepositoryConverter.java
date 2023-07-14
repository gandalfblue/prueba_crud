package com.prueba.crud.infrastructure.persistence.converters;

import com.prueba.crud.core.user.User;
import com.prueba.crud.infrastructure.persistence.entities.UserEntity;
import com.prueba.crud.infrastructure.shared.RepositoryConverter;

public class UserRepositoryConverter implements RepositoryConverter<UserEntity, User> {

	@Override
	public UserEntity mapToDocument(final User persistenceObject) {
		return new UserEntity(persistenceObject.getId(), persistenceObject.getNameComplete(),
				persistenceObject.getEmail(),persistenceObject.getDni(),
				persistenceObject.getRol(), persistenceObject.getPassword());
	}

	@Override
	public User mapToEntity(final UserEntity entityObject) {
		return new User(entityObject.getId(), entityObject.getNameComplete(),
				entityObject.getEmail(), entityObject.getDni(), entityObject.getRol(), entityObject.getPassword());
	}
}
