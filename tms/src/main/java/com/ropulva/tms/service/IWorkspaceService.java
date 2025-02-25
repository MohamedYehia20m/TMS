package com.ropulva.tms.service;

import com.ropulva.tms.dto.WorkspaceDto;
import com.ropulva.tms.dto.WorkspaceSaveDto;

import java.util.List;

public interface IWorkspaceService {

    List<WorkspaceDto> getWorkspaces();

    WorkspaceDto getWorkspace(Long id);

    WorkspaceDto createWorkspace(WorkspaceDto workspaceDto);

    WorkspaceSaveDto updateWorkspace(WorkspaceSaveDto workspaceSaveDto);

    void deleteWorkspace(Long id);
}
