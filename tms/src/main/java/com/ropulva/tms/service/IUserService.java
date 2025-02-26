package com.ropulva.tms.service;

import com.ropulva.tms.dto.UserDto;
import com.ropulva.tms.dto.UserSaveDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    ResponseEntity<List<UserDto>> getUsers();

    ResponseEntity<UserDto> getUser(Long id);

    ResponseEntity<UserDto> createUser(UserDto userDto);

    ResponseEntity<UserDto> updateUser(UserSaveDto userSaveDto);

    ResponseEntity<Void> deleteUser(Long id);
}
