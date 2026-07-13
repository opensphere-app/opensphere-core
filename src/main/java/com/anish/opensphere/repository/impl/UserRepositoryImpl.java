package com.anish.opensphere.repository.impl;

import com.anish.opensphere.entity.User;
import com.anish.opensphere.exception.UserException;
import com.anish.opensphere.repository.UserRepository;
import com.anish.opensphere.util.Sql;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final JdbcClient jdbcClient;

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return jdbcClient.sql(Sql.UserQueries.GET_USER_BY_EMAIL)
                    .param(email)
                    .param(Boolean.TRUE)
                    .query(User.class)
                    .optional();
        } catch (DataAccessException e) {
            log.error("Error fetching user by email={}: {}",
                    email, e.getMessage(), e);
            throw new UserException("Error fetching user by email");
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try {
            return jdbcClient.sql(Sql.UserQueries.CHECK_EMAIL_EXISTS)
                    .param(email)
                    .param(Boolean.TRUE)
                    .query(Long.class)
                    .single() > 0;
        } catch (DataAccessException e) {
            log.error("Error verifying existence for email={}: {}", email,
                    e.getMessage(), e);
            throw new UserException("Error verifying email");
        }
    }

    @Override
    public Optional<User> findByPrincipalId(long principalId) {
        try {
            return jdbcClient.sql(Sql.UserQueries.GET_USER_BY_PRINCIPAL_ID)
                    .param(principalId)
                    .param(Boolean.TRUE)
                    .query(User.class)
                    .optional();
        } catch (DataAccessException e) {
            log.error("Error fetching user by principal ID={}: {}", principalId, e.getMessage(), e);
            throw new UserException("Error fetching user with principal ID");
        }
    }

    @Override
    public User createUser(User user) {
        try {
            jdbcClient.sql(Sql.UserQueries.CREATE_USER)
                    .param(user.name())
                    .param(user.email())
                    .param(user.password())
                    .param(user.active())
                    .param(user.createdBy())
                    .param(user.createdAt())
                    .param(user.updatedBy())
                    .param(user.updatedAt())
                    .param(user.principal().id())
                    .update();
            return user;
        } catch (DataAccessException e) {
            log.error("Error creating user with email={}: {}", user.email(), e.getMessage(), e);
            throw new UserException("Error creating user");
        }
    }

    @Override
    public User deleteUser(long id, long deletedBy) {
        try {
            return jdbcClient.sql(Sql.UserQueries.DELETE_USER)
                    .param(Boolean.FALSE)
                    .param(deletedBy)
                    .param(Instant.now().toEpochMilli())
                    .param(id)
                    .query(User.class)
                    .single();
        } catch (DataAccessException e) {
            log.error("Error deleting user with id={}: {}", id, e.getMessage(), e);
            throw new UserException("Error deleting user");
        }
    }

//    @Override
//    public boolean existsRolesByRoleName(String roleName) {
//        try {
//
//        } catch (DataAccessException e) {
//            log.error("Error fetching roles by role name={}: {}", roleName, e.getMessage(), e);
//            throw new UserException("Error fetching roles by role name");
//        }
//    }
//    Optional<User> findByEmail(String email);
//
//    boolean existsByEmail(String email);
//
//    Optional<User> findByPrincipalId(long principalId);
//
//    boolean existsRolesByRoleName(String roleName);
}