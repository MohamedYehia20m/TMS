package com.ropulva.tms.repository;

import com.ropulva.tms.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
}
