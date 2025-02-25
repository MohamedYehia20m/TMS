package com.ropulva.tms.service;

import com.ropulva.tms.dto.UserDto;
import com.ropulva.tms.dto.UserSaveDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getUsers();

    UserDto getUser(Long id);

    UserDto createUser(UserDto userDto);

    UserSaveDto updateUser(UserSaveDto userSaveDto);

    void deleteUser(Long id);
}
