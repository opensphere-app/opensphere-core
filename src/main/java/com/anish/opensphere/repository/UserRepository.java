package com.anish.opensphere.repository;

import com.anish.opensphere.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> getUserByPrincipalId(long principalId);

    User createUser(User user);

    User deleteUser(long id, long deletedBy);
//    boolean existsRolesByRoleName(String roleName);
}