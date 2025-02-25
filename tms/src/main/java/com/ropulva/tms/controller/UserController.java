package com.ropulva.tms.controller;

import com.ropulva.tms.dto.UserDto;
import com.ropulva.tms.dto.UserSaveDto;
import com.ropulva.tms.model.User;
import com.ropulva.tms.service.UserServiceImpl;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<User> getUsers() {
        return userServiceImpl.getUsers().stream().map(userDto -> modelMapper.map(userDto, User.class)).toList();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return modelMapper.map(userServiceImpl.getUser(id), User.class);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return modelMapper.map(userServiceImpl.createUser(userDto), User.class);
    }

    @PutMapping()
    public UserDto updateUser(@RequestBody UserSaveDto userSaveDto) {
        UserSaveDto updatedUserSaveDto =  userServiceImpl.updateUser(userSaveDto);
        return modelMapper.map(updatedUserSaveDto, UserDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
    }

}
