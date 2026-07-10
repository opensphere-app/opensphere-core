package com.anish.opensphere.entity;

public record Principal(
        long id,
        PrincipalType type,
        String displayName,
        long createdBy,
        long createdAt,
        long updatedBy,
        long updatedAt,
        Long deletedBy,
        Long deletedAt
) { }