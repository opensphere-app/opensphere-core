package com.anish.opensphere.repository.impl;

import com.anish.opensphere.dto.RoleRequestDto;
import com.anish.opensphere.dto.RoleResponseDto;
import com.anish.opensphere.exception.RoleException;
import com.anish.opensphere.repository.RoleRepository;
import com.anish.opensphere.util.Sql;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository {

    private final JdbcClient jdbcClient;

    @Override
    public Optional<RoleResponseDto> getRoleById(long id) {
        try {
            return jdbcClient.sql(Sql.RoleQueries.GET_ROLE_BY_ID)
                    .param(id)
                    .query(RoleResponseDto.class)
                    .optional();
        } catch (DataAccessException e) {
            log.error("Error fetching role for id={}: {}", id, e.getMessage(), e);
            throw new RoleException("Error fetching role by ID");
        }
    }

    @Override
    public List<RoleResponseDto> getRoles() {
        try {
            return jdbcClient.sql(Sql.RoleQueries.GET_ROLES)
                    .query(RoleResponseDto.class)
                    .list();
        } catch (DataAccessException e) {
            log.error("Error fetching roles: {}", e.getMessage(), e);
            throw new RoleException("Error fetching roles");
        }
    }

    @Override
    public Optional<RoleResponseDto> getRoleByRoleName(String roleName) {
        try {
            return jdbcClient.sql(Sql.RoleQueries.GET_ROLE_BY_ROLE_NAME)
                    .param(roleName)
                    .query(RoleResponseDto.class)
                    .optional();
        } catch (DataAccessException e) {
            log.error("Error fetching role details by role name {}: {}", roleName, e.getMessage(), e);
            throw new RoleException("Error fetching by role name");
        }
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        try {
            return jdbcClient.sql(Sql.RoleQueries.CHECK_ROLE_EXISTS_BY_NAME)
                    .param(roleName)
                    .query(Long.class)
                    .single() > 0;
        } catch (DataAccessException e) {
            log.error("Error checking role existence for role name {}: {}", roleName, e.getMessage(), e);
            throw new RoleException("Error checking role existence by role name");
        }
    }

    @Override
    public boolean existsByRoleId(long id) {
        try {
            return jdbcClient.sql(Sql.RoleQueries.CHECK_ROLE_EXISTS_BY_ID)
                    .param(id)
                    .query(Long.class)
                    .single() > 0;
        } catch (DataAccessException e) {
            log.error("Error checking role existence for role ID {}: {}", id, e.getMessage(), e);
            throw new RoleException("Error checking role existence by role ID");
        }
    }

    @Override
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto, long createdBy) {
        try {
            Long id = jdbcClient.sql(Sql.RoleQueries.CREATE_ROLE)
                    .param(roleRequestDto.roleName())
                    .param(roleRequestDto.description())
                    .param(createdBy)
                    .param(Instant.now().toEpochMilli())
                    .param(createdBy)
                    .param(Instant.now().toEpochMilli())
                    .query(Long.class)
                    .single();
            return new RoleResponseDto(id, roleRequestDto.roleName(), roleRequestDto.description());
        } catch (DataAccessException e) {
            log.error("Error creating role {}: {}", roleRequestDto.roleName(), e.getMessage(), e);
            throw new RoleException("Error creating role");
        }
    }

    @Override
    public RoleResponseDto updateRole(RoleRequestDto roleRequestDto, long id, long updatedBy) {
        try {
            boolean response = jdbcClient.sql(Sql.RoleQueries.UPDATE_ROLE)
                    .param(roleRequestDto.roleName())
                    .param(roleRequestDto.description())
                    .param(updatedBy)
                    .param(Instant.now().toEpochMilli())
                    .param(id)
                    .update() > 0;
            if (!response) {
                throw new RoleException("Role not found for update");
            }
            return new RoleResponseDto(id, roleRequestDto.roleName(), roleRequestDto.description());
        } catch (DataAccessException e) {
            log.error("Error updating role {}: {}", roleRequestDto.roleName(), e.getMessage(), e);
            throw new RoleException("Error updating role");
        }
    }

    @Override
    public RoleResponseDto deleteRole(long id, long deletedBy) {
        try {
            return jdbcClient.sql(Sql.RoleQueries.DELETE_ROLE)
                    .param(deletedBy)
                    .param(Instant.now().toEpochMilli())
                    .param(id)
                    .query(RoleResponseDto.class)
                    .single();
        } catch (DataAccessException e) {
            log.error("Error deleting role with id {}: {}", id, e.getMessage(), e);
            throw new RoleException("Error deleting role");
        }
    }

}