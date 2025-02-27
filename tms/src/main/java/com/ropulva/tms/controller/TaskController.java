package com.ropulva.tms.controller;

import com.ropulva.tms.dto.TaskDto;
import com.ropulva.tms.dto.TaskSaveDto;
import com.ropulva.tms.service.TaskServiceImpl;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskServiceImpl taskServiceImpl;

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

    @PutMapping("Done/{id}")
    public ResponseEntity<TaskDto> updateTaskDone(@PathVariable Long id) {
        return taskServiceImpl.updateTaskDone(id);
    }

    @PutMapping("Undone/{id}")
    public ResponseEntity<TaskDto> updateTaskUndone(@PathVariable Long id) {
        return taskServiceImpl.updateTaskUndone(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return taskServiceImpl.deleteTask(id);
    }
}
