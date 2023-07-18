package com.prueba.crud.infrastructure.persistence.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Usuario class.
 * Clase que representa a la colección Usuario creada en la base de datos.
 * @author 100 de cilantro
 */
@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity implements Serializable{

    private static final long serialVersionUID = -595183205195778907L;

    @Id
    private String id;

    @NotBlank
    private String nameComplete;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    @NumberFormat
    @NotNull
    private Integer dni;

    @NotBlank
    private String password;

    @NotBlank
    private String rol;
}