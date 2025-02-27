package com.ropulva.tms.service;

import com.ropulva.tms.dto.UserDto;
import com.ropulva.tms.dto.UserSaveDto;
import com.ropulva.tms.model.User;
import com.ropulva.tms.repository.UserRepository;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {
    final UserRepository userRepository;
    final ModelMapper modelMapper;
    private Logger logger;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<List<UserDto>> getUsers() {
        try{
            List<User> users = userRepository.findAll();
            return ResponseEntity.status(200).body(users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList());
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<UserDto> getUser(Long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return ResponseEntity.status(200).body(modelMapper.map(user, UserDto.class));
            } else {
                return ResponseEntity.status(404).build();
            }
        }
        catch (DataIntegrityViolationException | OptimisticLockingFailureException e) {
            return ResponseEntity.status(409).build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<UserDto> getUserByEmail(String email) {
        try {
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isPresent()) {
                return ResponseEntity.status(200).body(modelMapper.map(user, UserDto.class));
            } else {
                return ResponseEntity.status(404).build();
            }
        }
        catch (DataIntegrityViolationException | OptimisticLockingFailureException e) {
            return ResponseEntity.status(409).build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        try {
            User user = modelMapper.map(userDto, User.class);
            // Encode password
            user.setPhone(passwordEncoder.encode(user.getPhone())); // phone is used as password
            User savedUser = userRepository.save(user);
            return ResponseEntity.status(201).body(modelMapper.map(savedUser, UserDto.class));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).build();
        }
        catch (IllegalStateException e) {
            return ResponseEntity.status(409).build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }


    public ResponseEntity<UserDto> updateUser(UserSaveDto userSaveDto) {
        try {
            Optional<User> userOptional = userRepository.findById(userSaveDto.getId());
            if (userOptional.isPresent()) {
                User updatedUser = userRepository.save(modelMapper.map(userSaveDto, User.class));
                return ResponseEntity.status(200).body(modelMapper.map(updatedUser, UserDto.class));
            } else {
                return ResponseEntity.status(404).build();
            }
        }
        catch (DataIntegrityViolationException | OptimisticLockingFailureException | IllegalStateException e) {
            return ResponseEntity.status(409).build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                userRepository.delete(userOptional.get());
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.status(404).build();
            }
        }
        catch (DataIntegrityViolationException | OptimisticLockingFailureException e) {
            return ResponseEntity.status(409).build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

}
