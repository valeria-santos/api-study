package com.github.project.study.api.controllers;

import com.github.project.study.api.dtos.UserDto;
import com.github.project.study.api.entities.User;
import com.github.project.study.api.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User userCreated = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @GetMapping("{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            UserDto userDto = modelMapper.map(user.get(), UserDto.class);
            return ResponseEntity.status(HttpStatus.OK).body(userDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
