package com.ropulva.tms.controller;

import com.ropulva.tms.dto.TaskDto;
import com.ropulva.tms.dto.TaskSaveDto;
import com.ropulva.tms.dto.UserDto;
import com.ropulva.tms.model.Task;
import com.ropulva.tms.service.TaskServiceImpl;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskServiceImpl taskServiceImpl;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks() {
        return taskServiceImpl.getTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        return taskServiceImpl.getTask(id);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        return taskServiceImpl.createTask(taskDto);
    }

    @PutMapping()
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskSaveDto taskSaveDto) {
        return taskServiceImpl.updateTask(taskSaveDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return taskServiceImpl.deleteTask(id);
    }
}
