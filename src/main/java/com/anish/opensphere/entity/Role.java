package com.anish.opensphere.entity;

import java.util.Set;

public record Role(
        long id,
        String name,
        String description,
        long createdBy,
        long createdAt,
        long updatedBy,
        long updatedAt,
        Long deletedBy,
        Long deletedAt,
        Set<Permission> permissions
) { }