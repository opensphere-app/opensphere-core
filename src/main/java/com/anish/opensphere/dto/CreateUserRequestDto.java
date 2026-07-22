package com.anish.opensphere.dto;

import java.util.Set;

public record CreateUserRequestDto(
        String email,
        String name,
        String password,
        Set<Long> roleIds
) { }