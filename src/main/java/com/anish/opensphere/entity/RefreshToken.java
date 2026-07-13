package com.anish.opensphere.entity;

import java.time.Instant;

public record RefreshToken(
    long id,
    String token,
    long userId,
    Instant expiresAt,
    Instant createdAt
) { }