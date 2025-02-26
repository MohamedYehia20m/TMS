package com.ropulva.tms.service;

import com.ropulva.tms.dto.TaskDto;
import com.ropulva.tms.dto.TaskSaveDto;
import com.ropulva.tms.dto.UserDto;
import com.ropulva.tms.enums.Status;
import com.ropulva.tms.model.Task;
import com.ropulva.tms.model.User;
import com.ropulva.tms.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements ITaskService {
    final TaskRepository taskRepository;
    final ModelMapper modelMapper;

    public ResponseEntity<List<TaskDto>> getTasks() {
        try{
            List<Task> tasks = taskRepository.findAll();
            return ResponseEntity.status(200).body(tasks.stream().map(task -> modelMapper.map(task, TaskDto.class)).toList());
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<TaskDto> getTask(Long id) {
        try {
            Optional<Task> taskOptional = taskRepository.findById(id);
            if (taskOptional.isPresent()) {
                Task task = taskOptional.get();
                return ResponseEntity.status(200).body(modelMapper.map(task, TaskDto.class));
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

    public ResponseEntity<TaskDto> createTask(TaskDto taskDto) {
        try {
            Task task = taskRepository.save(modelMapper.map(taskDto, Task.class));
            return ResponseEntity.status(201).body(modelMapper.map(task, TaskDto.class));
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

    public ResponseEntity<TaskDto> updateTask(TaskSaveDto taskSaveDto) {
        try {
            Optional<Task> taskOptional = taskRepository.findById(taskSaveDto.getId());
            if (taskOptional.isPresent()) {
                Task updatedTask = taskRepository.save(modelMapper.map(taskSaveDto, Task.class));
                return ResponseEntity.status(200).body(modelMapper.map(updatedTask, TaskDto.class));
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

    public ResponseEntity<TaskDto> updateTaskDone(Long id) {
        try {
            Optional<Task> taskOptional = taskRepository.findById(id);
            if (taskOptional.isPresent()) {
                Task task = taskOptional.get();
                task.setStatus(Status.DONE);
                Task updatedTask = taskRepository.save(task);
                return ResponseEntity.status(200).body(modelMapper.map(updatedTask, TaskDto.class));
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

    public ResponseEntity<TaskDto> updateTaskUndone(Long id) {
        try {
            Optional<Task> taskOptional = taskRepository.findById(id);
            if (taskOptional.isPresent()) {
                Task task = taskOptional.get();
                task.setStatus(Status.PENDING);
                Task updatedTask = taskRepository.save(task);
                return ResponseEntity.status(200).body(modelMapper.map(updatedTask, TaskDto.class));
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

    public ResponseEntity<Void> deleteTask(Long id) {
        try {
            Optional<Task> taskOptional  = taskRepository.findById(id);
            if (taskOptional.isPresent()) {
                taskRepository.delete(taskOptional.get());
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
