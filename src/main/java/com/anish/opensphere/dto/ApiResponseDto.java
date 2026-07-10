package com.anish.opensphere.dto;

public record ApiResponseDto(
        int statusCode,
        String statusMessage,
        String message,
        Object response
) { }