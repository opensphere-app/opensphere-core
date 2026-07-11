package com.anish.opensphere.service;

import com.anish.opensphere.dto.LoginRequestDto;
import com.anish.opensphere.dto.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);
}