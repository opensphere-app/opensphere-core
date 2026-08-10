package com.anish.opensphere.service.impl;

import com.anish.opensphere.entity.Permission;
import com.anish.opensphere.repository.PermissionRepository;
import com.anish.opensphere.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public Optional<Permission> findById(long id) {
        return permissionRepository.findById(id);
    }
}