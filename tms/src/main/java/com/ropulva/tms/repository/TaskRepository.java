package com.ropulva.tms.repository;

import com.ropulva.tms.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
