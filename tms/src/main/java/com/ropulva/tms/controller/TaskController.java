package com.ropulva.tms.controller;

import com.ropulva.tms.dto.TaskDto;
import com.ropulva.tms.dto.TaskSaveDto;
import com.ropulva.tms.model.Task;
import com.ropulva.tms.service.TaskServiceImpl;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskServiceImpl taskServiceImpl;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Task> getTasks() {
        return taskServiceImpl.getTasks().stream().map(taskDto -> modelMapper.map(taskDto, Task.class)).toList();

    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return modelMapper.map(taskServiceImpl.getTask(id), Task.class);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        TaskDto taskDto = modelMapper.map(task, TaskDto.class);
        return modelMapper.map(taskServiceImpl.createTask(taskDto), Task.class);
    }

    @PutMapping()
    public TaskDto updateTask(@RequestBody TaskSaveDto taskSaveDto) {
        TaskSaveDto updatedTaskSaveDto =  taskServiceImpl.updateTask(taskSaveDto);
        return modelMapper.map(updatedTaskSaveDto, TaskDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskServiceImpl.deleteTask(id);
    }
}
