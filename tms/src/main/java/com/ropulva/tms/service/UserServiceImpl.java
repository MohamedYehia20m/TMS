package com.ropulva.tms.service;

import com.ropulva.tms.dto.UserDto;
import com.ropulva.tms.dto.UserSaveDto;
import com.ropulva.tms.model.User;
import com.ropulva.tms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {
    final UserRepository userRepository;
    final ModelMapper modelMapper;

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    public UserDto getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return modelMapper.map(userOptional.get(), UserDto.class);
        }
        else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public UserDto createUser(UserDto userDto) {
        User user = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(user, UserDto.class);
    }

    public UserSaveDto updateUser(UserSaveDto userSaveDto) {
        if (userSaveDto.getId() == null) {
            throw new RuntimeException("User id is required");
        }

        User existingUser = modelMapper.map(userSaveDto, User.class);
        User savedUser = userRepository.save(existingUser);

        return modelMapper.map(savedUser, UserSaveDto.class);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
