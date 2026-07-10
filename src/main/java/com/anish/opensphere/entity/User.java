package com.anish.opensphere.entity;

import java.util.Set;

public record User(
    long id,

    String email,

    String password,

    Principal principal,

    boolean active,

    long createdAt,

    long createdBy,

    long updatedAt,

    long updatedBy,

    Set<Role> roles
) { }