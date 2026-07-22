package com.anish.opensphere.repository.impl;

import com.anish.opensphere.entity.Permission;
import com.anish.opensphere.exception.PermissionException;
import com.anish.opensphere.repository.PermissionRepository;
import com.anish.opensphere.util.Sql;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PermissionRepositoryImpl implements PermissionRepository {

    private final JdbcClient jdbcClient;

    @Override
    public Optional<Permission> findById(long id) {
        try {
            return jdbcClient.sql(Sql.PermissionQueries.GET_PERMISSION_BY_ID)
                    .param(id)
                    .query(Permission.class)
                    .optional();
        } catch (DataAccessException e) {
            log.error("Error fetching permission for id={}: {}", id, e.getMessage(), e);
            throw new PermissionException("Error fetching permission");
        }
    }
}
