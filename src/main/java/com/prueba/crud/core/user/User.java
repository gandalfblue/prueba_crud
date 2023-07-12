package com.prueba.crud.core.user;

import com.prueba.crud.core.shared.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class User extends SelfValidating<User> implements Serializable{

	@Min(0)
	private Long id;

	@NotEmpty
	private String nameComplete;

	@Indexed(unique = true)
	private String email;

	@Indexed(unique = true)
	private String dni;

	@NotBlank
	private String password;

	@NotBlank
	private String rol;
}
