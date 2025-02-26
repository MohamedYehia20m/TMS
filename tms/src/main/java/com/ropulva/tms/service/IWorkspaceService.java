package com.ropulva.tms.service;

import com.ropulva.tms.dto.WorkspaceDto;
import com.ropulva.tms.dto.WorkspaceSaveDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IWorkspaceService {

    ResponseEntity<List<WorkspaceDto>> getWorkspaces();

    ResponseEntity<WorkspaceDto> getWorkspace(Long id);

    ResponseEntity<WorkspaceDto> createWorkspace(WorkspaceDto workspaceDto);

    ResponseEntity<WorkspaceDto> updateWorkspace(WorkspaceSaveDto workspaceSaveDto);

    ResponseEntity<Void> deleteWorkspace(Long id);
}
