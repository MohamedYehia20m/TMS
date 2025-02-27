package com.ropulva.tms.service;

import com.ropulva.tms.dto.TaskDto;
import com.ropulva.tms.dto.TaskSaveDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITaskService {

    ResponseEntity<List<TaskDto>> getTasks();

    ResponseEntity<TaskDto> getTask(Long id) throws Exception;

    ResponseEntity<TaskDto> createTask(TaskDto taskDto) throws Exception;

    ResponseEntity<TaskDto> updateTask(TaskSaveDto taskSaveDto) throws Exception;

    ResponseEntity<TaskDto> updateTaskDone(Long id);

    ResponseEntity<TaskDto> updateTaskUndone(Long id);

    ResponseEntity<Void> deleteTask(Long id);
}
