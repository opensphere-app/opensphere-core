package com.anish.opensphere.dto;

public record LoginResponseDto(
    String accessToken,
    String refreshToken,
    String tokenType,
    long accessExpirationMs,
    long refreshExpirationMs
) { }