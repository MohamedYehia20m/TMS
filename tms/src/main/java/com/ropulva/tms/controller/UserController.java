package com.ropulva.tms.controller;

import com.ropulva.tms.dto.UserDto;
import com.ropulva.tms.dto.UserSaveDto;
import com.ropulva.tms.service.UserServiceImpl;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;

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

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserSaveDto userSaveDto) {
        return userServiceImpl.updateUser(userSaveDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userServiceImpl.deleteUser(id);
    }
}