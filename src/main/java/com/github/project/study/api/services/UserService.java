package com.github.project.study.api.services;

import com.github.project.study.api.entities.User;
import com.github.project.study.api.exceptions.InvalidUsernameOrEmailException;
import com.github.project.study.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public User save(User user) {
        boolean usernameInUse = userRepository.findByUsername(user.getUsername())
                .filter(actualUser -> !actualUser.equals(user))
                .isPresent();
        boolean emailInUse = userRepository.findByEmail(user.getEmail())
                .filter(actualUser -> !actualUser.equals(user))
                .isPresent();
        if (usernameInUse || emailInUse) {
            throw new InvalidUsernameOrEmailException("Username or email not available.");
        }
        user.setCreationDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
}
