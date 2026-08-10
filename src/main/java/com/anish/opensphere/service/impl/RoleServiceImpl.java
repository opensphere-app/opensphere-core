package com.anish.opensphere.service.impl;

import com.anish.opensphere.repository.RoleRepository;
import com.anish.opensphere.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;



}