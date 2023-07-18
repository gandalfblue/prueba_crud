package com.prueba.crud.infrastructure.delivery.impl;

import com.prueba.crud.core.user.User;
import com.prueba.crud.core.user.ports.UserRepositoryService;
import com.prueba.crud.core.user.use_cases.CreateUserUseCaseImpl;
import com.prueba.crud.core.user.use_cases.DeleteUserUseCaseImpl;
import com.prueba.crud.core.user.use_cases.GetAllUsersUseCaseImpl;
import com.prueba.crud.core.user.use_cases.UpdateUserUseCaseImpl;
import com.prueba.crud.infrastructure.delivery.converters.UserRestConverter;
import com.prueba.crud.infrastructure.delivery.responses.UserResponse;
import com.prueba.crud.infrastructure.delivery.rest.UserRest;
import com.prueba.crud.infrastructure.shared.constants.CommonConstants;
import com.prueba.crud.infrastructure.shared.exceptions.UserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
class UserControllerImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GetAllUsersUseCaseImpl getAllUsersUseCase;

    @MockBean
    private UserRepositoryService userRepositoryService;

    @MockBean
    private UserRestConverter userRestConverter;
    @MockBean
    private CreateUserUseCaseImpl createUserUseCaseImpl;

    @MockBean
    private DeleteUserUseCaseImpl deleteUserUseCaseImpl;

    @MockBean
    private UpdateUserUseCaseImpl updateUserUseCaseImpl;

    @MockBean
    private UserControllerImpl userController;

    @MockBean
    UserResponse userResponse;


    private static final String ID = "U-0001";
    private static final String NAMECOMPLETE = "Andres Felipe Lozada";
    private static final String EMAIL = "ciendecilantro.agile@gmail.com";
    private static final String PASSWORD = "123456789";
    private static final Integer DNI = 123456789;
    private static final String ROL = "Desarrollador";

    @BeforeEach
    public void configurationInitial() {
        userRepositoryService = mock(UserRepositoryService.class);
        userRestConverter = mock(UserRestConverter.class);
        userResponse = mock(UserResponse.class);
        getAllUsersUseCase = new GetAllUsersUseCaseImpl(userRepositoryService, userRestConverter);
        userController = new UserControllerImpl(getAllUsersUseCase,createUserUseCaseImpl,
                deleteUserUseCaseImpl,updateUserUseCaseImpl,userRestConverter);
    }

    @Test
    void getAllUsers() throws UserException {

        var usuario = new User();
        usuario.setId(ID);
        usuario.setNameComplete(NAMECOMPLETE);
        usuario.setEmail(EMAIL);
        usuario.setPassword(PASSWORD);
        usuario.setRol(ROL);
        usuario.setDni(DNI);

        var usuario2 = new User();
        usuario2.setId(ID);
        usuario2.setNameComplete(NAMECOMPLETE);
        usuario2.setEmail(EMAIL);
        usuario2.setPassword(PASSWORD);
        usuario2.setRol(ROL);
        usuario2.setDni(DNI);

        var usuarioRest = new UserRest();
        usuarioRest.setId(ID);
        usuarioRest.setNameComplete(NAMECOMPLETE);
        usuarioRest.setEmail(EMAIL);
        usuarioRest.setPassword(PASSWORD);
        usuarioRest.setRol(ROL);
        usuarioRest.setDni(DNI);

        var usuarioRest2 = new UserRest();
        usuarioRest2.setId(ID);
        usuarioRest2.setNameComplete(NAMECOMPLETE);
        usuarioRest2.setEmail(EMAIL);
        usuarioRest2.setPassword(PASSWORD);
        usuarioRest2.setRol(ROL);
        usuarioRest2.setDni(DNI);

        Mockito.when(userRepositoryService.getAllUsers()).thenReturn(Flux.just(usuario, usuario2));
        Mockito.when(getAllUsersUseCase.execute()).thenReturn(Flux.just(usuarioRest, usuarioRest2));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getAllUsers")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBodyList(UserResponse.class)
                .value(respuesta -> {
                    Assertions.assertEquals(respuesta.size(), 2);
                });
        verify(getAllUsersUseCase).execute();
    }

    @Test
    void createUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}