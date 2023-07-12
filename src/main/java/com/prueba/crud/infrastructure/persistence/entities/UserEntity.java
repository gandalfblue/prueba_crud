package com.prueba.crud.infrastructure.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Usuario class.
 * Clase que representa a la colección Usuario creada en la base de datos.
 * @author 100 de cilantro
 */
@Document(collection = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable{

    private static final long serialVersionUID = -595183205195778907L;

    @Id
    private Long id;

    @NotBlank
    private String nameComplete;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    @NumberFormat
    private Integer dni;

    @NotBlank
    private String password;

    @NotBlank
    private String rol;
}