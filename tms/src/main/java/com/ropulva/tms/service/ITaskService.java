package com.ropulva.tms.service;

import com.ropulva.tms.dto.TaskDto;
import com.ropulva.tms.dto.TaskSaveDto;

import java.util.List;

public interface ITaskService {

    List<TaskDto> getTasks();

    TaskDto getTask(Long id);

    TaskDto createTask(TaskDto taskDto);

    TaskSaveDto updateTask(TaskSaveDto taskSaveDto);

    void deleteTask(Long id);
}
