package com.ropulva.tms.service;

import com.ropulva.tms.dto.TaskDto;
import com.ropulva.tms.dto.TaskSaveDto;
import com.ropulva.tms.enums.Status;
import com.ropulva.tms.model.Task;
import com.ropulva.tms.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements ITaskService {
    final TaskRepository taskRepository;
    final ModelMapper modelMapper;

    public ResponseEntity<List<TaskDto>> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks.stream().map(task -> modelMapper.map(task, TaskDto.class)).toList());
    }

    public ResponseEntity<TaskDto> getTask(Long id) {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
            return ResponseEntity.ok(modelMapper.map(task, TaskDto.class));
    }

    public ResponseEntity<TaskDto> createTask(TaskDto taskDto) {
        try {
            Task task = taskRepository.save(modelMapper.map(taskDto, Task.class));
            return ResponseEntity.ok(modelMapper.map(task, TaskDto.class));
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid task data, illegal argument");
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Invalid task data");
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Invalid task data, constraint violation ");
        }
        catch (OptimisticLockingFailureException e) {
            throw new OptimisticLockingFailureException("Invalid task data, optimistic locking failure");
        }
    }

    public ResponseEntity<TaskDto> updateTask(TaskSaveDto taskSaveDto) {
        try {
            Task existingTask = taskRepository.findById(taskSaveDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskSaveDto.getId()));
            Task updatedTask = taskRepository.save(modelMapper.map(taskSaveDto, Task.class));
            return ResponseEntity.status(200).body(modelMapper.map(updatedTask, TaskDto.class));
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

    public ResponseEntity<TaskDto> updateTaskDone(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        task.setStatus(Status.DONE);
        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.status(200).body(modelMapper.map(updatedTask, TaskDto.class));
    }

    public ResponseEntity<TaskDto> updateTaskUndone(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        task.setStatus(Status.PENDING);
        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.status(200).body(modelMapper.map(updatedTask, TaskDto.class));
    }

    public ResponseEntity<Void> deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);
        return ResponseEntity.noContent().build();
    }
}
