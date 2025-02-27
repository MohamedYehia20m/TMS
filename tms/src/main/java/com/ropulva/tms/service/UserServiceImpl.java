package com.ropulva.tms.service;

import com.ropulva.tms.dto.UserDto;
import com.ropulva.tms.dto.UserSaveDto;
import com.ropulva.tms.model.User;
import com.ropulva.tms.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList());
    }

    public ResponseEntity<UserDto> getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }

    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        try {
            User user = modelMapper.map(userDto, User.class);
            // Encode password
            user.setPhone(passwordEncoder.encode(user.getPhone())); // phone is used as password
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(modelMapper.map(savedUser, UserDto.class));
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid task data");
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Invalid task data");
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Invalid task data, constraint violation");
        }
        catch (OptimisticLockingFailureException e) {
            throw new OptimisticLockingFailureException("Invalid task data, optimistic locking failure");
        }
    }


    public ResponseEntity<UserDto> updateUser(UserSaveDto userSaveDto) {
        try {
            User existingUser = userRepository.findById(userSaveDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userSaveDto.getId()));
            User user = modelMapper.map(userSaveDto, User.class);
            // Encode password
            user.setPhone(passwordEncoder.encode(user.getPhone())); // phone is used as password
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(modelMapper.map(updatedUser, UserDto.class));
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid task data");
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Invalid task data");
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Invalid task data, constraint violation");
        }
        catch (OptimisticLockingFailureException e) {
            throw new OptimisticLockingFailureException("Invalid task data, optimistic locking failure");
        }
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

}
