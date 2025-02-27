package com.ropulva.tms.service;

import com.ropulva.tms.dto.TaskDto;
import com.ropulva.tms.dto.TaskSaveDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITaskService {

    ResponseEntity<List<TaskDto>> getTasks();

    ResponseEntity<TaskDto> getTask(Long id);

    ResponseEntity<TaskDto> createTask(TaskDto taskDto);

    ResponseEntity<TaskDto> updateTask(TaskSaveDto taskSaveDto);

    ResponseEntity<TaskDto> updateTaskDone(Long id);

    ResponseEntity<TaskDto> updateTaskUndone(Long id);

    ResponseEntity<Void> deleteTask(Long id);
}
