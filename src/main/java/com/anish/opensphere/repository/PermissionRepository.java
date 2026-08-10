package com.anish.opensphere.repository;

import com.anish.opensphere.entity.Permission;

import java.util.Optional;

public interface PermissionRepository {

    Optional<Permission> getPermissionById(long id);
}