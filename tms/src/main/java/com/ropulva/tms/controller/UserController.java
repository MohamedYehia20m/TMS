package com.ropulva.tms.controller;

import com.ropulva.tms.dto.UserDto;
import com.ropulva.tms.dto.UserSaveDto;
import com.ropulva.tms.enums.Role;
import com.ropulva.tms.model.User;
import com.ropulva.tms.service.UserServiceImpl;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return userServiceImpl.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return userServiceImpl.getUser(id);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return userServiceImpl.createUser(userDto);
    }

    /*
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        return userServiceImpl.getUserByEmail(authentication.getName());
    }

     */

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserSaveDto userSaveDto) {
        return userServiceImpl.updateUser(userSaveDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userServiceImpl.deleteUser(id);
    }
}