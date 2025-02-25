package com.ropulva.tms.service;

import com.ropulva.tms.dto.TaskDto;
import com.ropulva.tms.dto.TaskSaveDto;
import com.ropulva.tms.model.Task;
import com.ropulva.tms.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements ITaskService {
    final TaskRepository taskRepository;
    final ModelMapper modelMapper;

    public List<TaskDto> getTasks() {
        List<Task> taskEntities = taskRepository.findAll();
        return taskEntities.stream().map(task -> modelMapper.map(task, TaskDto.class)).toList();
    }

    public TaskDto getTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            return modelMapper.map(taskOptional.get(), TaskDto.class);
        } else {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task =  taskRepository.save(modelMapper.map(taskDto, Task.class));
        return modelMapper.map(task, TaskDto.class);
    }

    public TaskSaveDto updateTask(TaskSaveDto taskSaveDto) {
        if (taskSaveDto.getId() == null) {
            throw new EntityNotFoundException("Task id is required");
        }

        Task existingTask = modelMapper.map(taskSaveDto, Task.class);
        Task savedTask = taskRepository.save(existingTask);

        return modelMapper.map(savedTask, TaskSaveDto.class);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
