package com.anish.opensphere.service;

import com.anish.opensphere.entity.Permission;

import java.util.Optional;

public interface PermissionService {

    Optional<Permission> getPermissionById(long id);
}