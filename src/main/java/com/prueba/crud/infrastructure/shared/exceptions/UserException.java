package com.prueba.crud.infrastructure.shared.exceptions;

public abstract class UserException extends Exception {

	private static final long serialVersionUID = -126295836812485319L;

	private final int code;


	public UserException(final int code, final String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
