package com.javamentor.qa.platform.controllers;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Tag(name = "UserDto", description = "UserDto API")
public class ResourceUserController {
    private final UserDtoService userDtoService;
    public ResourceUserController(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }

    @Operation(summary = "Получение пользователя по его id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> getUserDtoById(@PathVariable("id") Long id) {

        if (userDtoService.getById(id).isEmpty()) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDtoService.getById(id), HttpStatus.OK);
    }
}

