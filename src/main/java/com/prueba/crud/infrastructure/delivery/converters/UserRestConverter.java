package com.prueba.crud.infrastructure.delivery.converters;

import com.prueba.crud.core.user.User;
import com.prueba.crud.infrastructure.delivery.rest.UserRest;
import com.prueba.crud.infrastructure.shared.RestConverter;

public class UserRestConverter implements RestConverter<UserRest, User> {

	@Override
	public User mapToEntity(final UserRest rest) {

		return new User(rest.getId(), rest.getNameComplete(),
				rest.getEmail(), rest.getDni(), rest.getPassword(), rest.getRol());
	}

	@Override
	public UserRest mapToRest(final User entity) {
		return new UserRest(entity.getId(), entity.getNameComplete(),
				entity.getEmail(), entity.getDni(), entity.getPassword(), entity.getRol());
	}

}
