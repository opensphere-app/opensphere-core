package com.anish.opensphere.service.impl;

import com.anish.opensphere.dto.LoginRequestDto;
import com.anish.opensphere.dto.LoginResponseDto;
import com.anish.opensphere.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        return null; // TODO
    }
}
