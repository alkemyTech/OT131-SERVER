package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UsersDtoResponse;

import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.LoginUsersDTO;
import com.alkemy.ong.dto.UsersNoAuthDto;
import com.alkemy.ong.dto.UsersOkDto;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.service.UsersServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import static com.alkemy.ong.util.Constants.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping(value = REQ_MAPP_CLASS_USER)
public class UsersController {

    @Autowired
    UsersServiceImpl usersService;

    @Operation(summary = USERS_AUTH_SUMARY )
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = USERS_LOGIN_OK,
                content = {
                    @Content(mediaType = MEDIA_TYPE_APP_JSON,
                            schema = @Schema(implementation = Users.class))}),

        @ApiResponse(responseCode = CODE_UNATHORIZED, description = USERS_LOGIN_UNAUTHORIZED,
                content = @Content),})

    @PostMapping(value = REQ_MAPP_POST_LOGIN_USER)
    private ResponseEntity<?> userAuthLogin(@Valid @RequestBody LoginUsersDTO loginUser) throws Exception {
        try {
            if (usersService.login(loginUser) != null) {
                return new ResponseEntity<>(usersService.login(loginUser), HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UsersNoAuthDto());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UsersNoAuthDto(e.getMessage()));
        }

    }

    @Operation(summary = USERS_PUT_SUMARY)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = USERS_PUT_OK,
                content = {
                    @Content(mediaType = MEDIA_TYPE_APP_JSON,
                            schema = @Schema(implementation = Users.class))}),

        @ApiResponse(responseCode = CODE_NOT_FOUND, description = USERS_NOT_FOUND,
                content = @Content),})

    @PutMapping(REQ_MAPP_DELETE_LOGIN_USER)
    private ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody NewUsersDTO dto) {
        UsersDtoResponse result = usersService.update(id, dto);
        return result == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok().body(result);

    }

    @Operation(summary = USERS_DELETE_SUMARY)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = USERS_LOGIN_OK,
                content = {
                    @Content(mediaType = MEDIA_TYPE_APP_JSON,
                            schema = @Schema(implementation = Users.class))}),

        @ApiResponse(responseCode = CODE_FORBIDDEN, description = USERS_FORBIDDEN,
                content = @Content),
        @ApiResponse(responseCode = CODE_NOT_FOUND, description = USERS_NOT_FOUND,
                content = @Content)})

    @DeleteMapping(value = REQ_MAPP_DELETE_LOGIN_USER)
    private ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) throws Exception {
        try {
            usersService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }

    }

    @Operation(summary = USERS_REGISTER_SUMARY )
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = USERS_REGISTER_OK_DESCRIPTION,
                content = {
                    @Content(mediaType = MEDIA_TYPE_APP_JSON,
                            schema = @Schema(implementation = NewUsersDTO.class))}),

        @ApiResponse(responseCode = CODE_BAD_REQUEST, description = USERS_BAD_REQUEST,
                content = @Content),})

    @PostMapping(value = REQ_MAPP_POST_REGISTER_USER)
    public ResponseEntity<?> register(@Valid @RequestBody NewUsersDTO userDTO) {
        return new ResponseEntity<>(usersService.register(userDTO), HttpStatus.CREATED);
    }

    @Operation(summary = USERS_ME_DESCRIPTION)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = USERS_ME_OK_DESCRIPTION,
                content = {
                    }),

        @ApiResponse(responseCode = CODE_BAD_REQUEST, description = USERS_BAD_REQUEST,
                content = @Content),})
    @GetMapping(REQ_MAPP_GET_AUTH_ME_USER)
    public ResponseEntity<?> authMe(@RequestHeader(name = "authorization") String token) {
        return ResponseEntity.ok(usersService.extractPayload(token));
    }

    @Operation(summary = USERS_LIST_USERS)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = USERS_LIST_OK,
                content = {}),
        @ApiResponse(responseCode = CODE_BAD_REQUEST, description = USERS_BAD_REQUEST,
                content = @Content),})
    @GetMapping(value = REQ_MAPP_GET_LIST_USER)
    public List<UsersOkDto> listUsers() {
        return usersService.listUsers();
    }

}
