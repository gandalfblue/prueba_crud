package com.prueba.crud.infrastructure.delivery.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRest implements Serializable {

	private static final long serialVersionUID = -3599603517077196676L;

	private String id;

	@NotEmpty
	@NotBlank
	private String nameComplete;

	@NotEmpty
	@NotBlank
	private String email;

	@NotNull
	private Integer dni;

	@NotEmpty
	@NotBlank
	private String password;

	@NotEmpty
	@NotBlank
	private String rol;
}
