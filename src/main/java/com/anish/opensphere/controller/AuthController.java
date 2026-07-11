package com.anish.opensphere.controller;

import com.anish.opensphere.dto.ApiResponseDto;
import com.anish.opensphere.dto.LoginRequestDto;
import com.anish.opensphere.dto.LoginResponseDto;
import com.anish.opensphere.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);
        return ResponseEntity.ok().body(
                new ApiResponseDto(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        "User authenticated successfully",
                        loginResponseDto
                )
        );
    }


}