package com.prueba.crud.core.user;

import com.prueba.crud.core.shared.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class User extends SelfValidating<User> implements Serializable{

	private Long id;

	@NotEmpty(message = "Campo nombre no puede ser vacio")
	@NotBlank(message = "Campo nombre no puede ser null")
	private String nameComplete;

	@NotEmpty(message = "Campo email no puede ser vacio")
	@NotBlank(message = "Campo email no puede ser vacio")
	private String email;

	@NotNull(message = "Campo dni no puede ser vacio")
	private Integer dni;

	@NotEmpty(message = "Campo password no puede ser vacio")
	@NotBlank(message = "Campo password no puede ser vacio")
	private String password;

	@NotEmpty(message = "Campo rol no puede ser vacio")
	@NotBlank(message = "Campo rol no puede ser vacio")
	private String rol;
}
