package com.prueba.crud.infrastructure.delivery.impl;

import com.prueba.crud.core.user.use_cases.CreateUserUseCaseImpl;
import com.prueba.crud.core.user.use_cases.DeleteUserUseCaseImpl;
import com.prueba.crud.core.user.use_cases.GetAllUsersUseCaseImpl;
import com.prueba.crud.core.user.use_cases.UpdateUserUseCaseImpl;
import com.prueba.crud.infrastructure.delivery.UserController;
import com.prueba.crud.infrastructure.delivery.converters.UserRestConverter;
import com.prueba.crud.infrastructure.delivery.responses.UserResponse;
import com.prueba.crud.infrastructure.delivery.rest.UserRest;
import com.prueba.crud.infrastructure.shared.constants.CommonConstants;
import com.prueba.crud.infrastructure.shared.constants.RestConstants;
import com.prueba.crud.infrastructure.shared.exceptions.UserException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_USER)
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

	private final GetAllUsersUseCaseImpl getAllUsersUseCaseImpl;

	private final CreateUserUseCaseImpl createUserUseCaseImpl;

	private final DeleteUserUseCaseImpl deleteUserUseCaseImpl;

	private final UpdateUserUseCaseImpl updateUserUseCaseImpl;

	private final UserRestConverter userRestConverter;

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get a all users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Founds the users",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = UserRest.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Users not founds",
					content = @Content) })
	public UserResponse<Flux<UserRest>> getAllUsers() throws UserException {
		return new UserResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				getAllUsersUseCaseImpl.execute());
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create a user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Created a user",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = UserRest.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "User not found",
					content = @Content) })
	public UserResponse<Mono<UserRest>> createUser(@Valid @RequestBody UserRest user) throws UserException {
		return new UserResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				createUserUseCaseImpl
						.execute(userRestConverter.mapToEntity(user)));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Delete user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the user",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = UserRest.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "User not found",
					content = @Content) })
	public UserResponse<Mono<Boolean>> deleteUser(@Valid @RequestBody UserRest userRest) throws UserException {
		return new UserResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				deleteUserUseCaseImpl.execute(userRestConverter.mapToEntity(userRest)));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the user",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = UserRest.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "User not found",
					content = @Content) })
	public UserResponse<Mono<UserRest>> updateUser(@Valid @RequestBody UserRest user) throws UserException {
		return new UserResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				updateUserUseCaseImpl
						.execute(userRestConverter.mapToEntity(user)));
	}
}
