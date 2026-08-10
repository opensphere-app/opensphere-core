package com.anish.opensphere.service;

import com.anish.opensphere.dto.CreateUserRequestDto;
import com.anish.opensphere.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> getUserByPrincipalId(long principalId);

    User createUser(CreateUserRequestDto createUserRequestDto);

    User deleteUser(long id, long deletedBy);
}