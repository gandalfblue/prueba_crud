package com.prueba.crud.core.user.use_cases;

import com.prueba.crud.core.user.User;
import com.prueba.crud.core.user.ports.UserRepositoryService;
import com.prueba.crud.infrastructure.delivery.converters.UserRestConverter;
import com.prueba.crud.infrastructure.delivery.rest.UserRest;
import com.prueba.crud.infrastructure.shared.exceptions.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class CreateUserUseCaseImplTest {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private UserRestConverter userRestConverter;

    @Autowired
    private CreateUserUseCaseImpl createUserUseCase;

    private static final String ID = "U-0001";
    private static final String NAMECOMPLETE = "Andres Felipe Lozada";
    private static final String EMAIL = "ciendecilantro.agile@gmail.com";
    private static final String PASSWORD = "123456789";
    private static final Integer DNI = 123456789;
    private static final String ROL = "Desarrollador";

    @BeforeEach
    public void configurationInitial() {
        userRepositoryService = mock(UserRepositoryService.class);
        createUserUseCase = new CreateUserUseCaseImpl(userRepositoryService, userRestConverter);
    }

    @Test
    void crearUsuarioTest() throws UserException {

        var usuario = new User();
        usuario.setId(ID);
        usuario.setNameComplete(NAMECOMPLETE);
        usuario.setEmail(EMAIL);
        usuario.setPassword(PASSWORD);
        usuario.setRol(ROL);
        usuario.setDni(DNI);

        var usuarioRest = new UserRest();
        usuarioRest.setId(ID);
        usuarioRest.setNameComplete(NAMECOMPLETE);
        usuarioRest.setEmail(EMAIL);
        usuarioRest.setPassword(PASSWORD);
        usuarioRest.setRol(ROL);
        usuarioRest.setDni(DNI);

        when(userRepositoryService.doesUserDniExists(Mockito.any(Integer.class))).thenReturn(Mono.just(Boolean.class).hasElement());
        when(userRepositoryService.saveUser(Mockito.any(User.class))).thenReturn(Mono.just(usuario));

        StepVerifier.create(createUserUseCase.execute(usuario))
                .expectNextMatches(user -> {
                    assert user.getId().equalsIgnoreCase(usuarioRest.getId());
                    assert user.getNameComplete().equalsIgnoreCase(usuarioRest.getNameComplete());
                    assert user.getEmail().equalsIgnoreCase(usuarioRest.getEmail());
                    assert user.getPassword().equalsIgnoreCase(usuarioRest.getPassword());
                    assert user.getRol().equalsIgnoreCase(usuarioRest.getRol());
                    assert user.getDni().equals(usuarioRest.getDni());
                    return true;
                })
                .verifyComplete();

        verify(userRepositoryService).saveUser(Mockito.any(User.class));
    }
}