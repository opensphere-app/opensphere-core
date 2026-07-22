package com.anish.opensphere.service.impl;

import com.anish.opensphere.dto.CreateUserRequestDto;
import com.anish.opensphere.entity.Principal;
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

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findByPrincipalId(long principalId) {
        return userRepository.findByPrincipalId(principalId);
    }

    @Transactional()
    public User createUser(CreateUserRequestDto userRequestDto) {

        if(userRepository.existsByEmail(userRequestDto.email())) {
            throw new UserAlreadyExistsException("User with email "
                    + userRequestDto.email() + " already exists");
        }
        // TODO
//
//        Principal principal = new Principal(
//
//        )
//        return userRepository.createUser(user);
    }

    public User deleteUser(long id, long deletedBy) {
        return userRepository.deleteUser(id, deletedBy);
    }
}