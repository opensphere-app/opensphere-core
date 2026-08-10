package com.anish.opensphere.service.impl;

import com.anish.opensphere.dto.CreateUserRequestDto;
import com.anish.opensphere.entity.User;
import com.anish.opensphere.exception.UserAlreadyExistsException;
import com.anish.opensphere.repository.UserRepository;
import com.anish.opensphere.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> getUserByPrincipalId(long principalId) {
        return userRepository.getUserByPrincipalId(principalId);
    }

    @Override
    public User createUser(CreateUserRequestDto createUserRequestDto) {
        // TODO
        return null;
    }

    public User deleteUser(long id, long deletedBy) {
        return userRepository.deleteUser(id, deletedBy);
    }
}