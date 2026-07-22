package com.anish.opensphere.service;

import com.anish.opensphere.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByPrincipalId(long principalId);

    User createUser(User user);

    User deleteUser(long id, long deletedBy);
}