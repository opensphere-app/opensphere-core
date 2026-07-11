package com.anish.opensphere.dto;

public record LoginResponseDto(
    String accessToken,
    String refreshToken,
    String tokenType,
    int accessExpirationMs,
    int refreshExpirationMs
) { }