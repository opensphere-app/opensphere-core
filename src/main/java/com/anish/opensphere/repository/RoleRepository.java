package com.anish.opensphere.repository;

import com.anish.opensphere.dto.RoleRequestDto;
import com.anish.opensphere.dto.RoleResponseDto;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Optional<RoleResponseDto> getRoleById(long id);

    List<RoleResponseDto> getRoles();

    Optional<RoleResponseDto> getRoleByRoleName(String roleName);

    boolean existsByRoleName(String roleName);

    boolean existsByRoleId(long  id);

    RoleResponseDto createRole(RoleRequestDto roleRequestDto, long createdBy);

    RoleResponseDto updateRole(RoleRequestDto roleRequestDto, long id, long updatedBy);

    RoleResponseDto deleteRole(long id, long deletedBy);
}