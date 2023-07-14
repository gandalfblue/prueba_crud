package com.prueba.crud.infrastructure.shared;

import org.springframework.stereotype.Component;

import java.io.Serializable;

public interface RepositoryConverter<T extends Serializable, P extends Serializable> {

	default T mapToDocument(final P persistenceObject) {
		throw new UnsupportedOperationException();
	}

	default P mapToEntity(final T tableObject) {
		throw new UnsupportedOperationException();
	}

}
