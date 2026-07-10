package com.anish.opensphere.entity;

public record Permission(
        long id,
        String name,
        String description,
        long createdBy,
        long createdAt,
        long updatedBy,
        long updatedAt,
        Long deletedBy,
        Long deletedAt
) { }