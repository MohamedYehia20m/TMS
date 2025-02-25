package com.ropulva.tms.controller;

import com.ropulva.tms.dto.WorkspaceDto;
import com.ropulva.tms.dto.WorkspaceSaveDto;
import com.ropulva.tms.model.Task;
import com.ropulva.tms.model.Workspace;
import com.ropulva.tms.service.WorkspaceServiceImpl;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces")
public class WorkspaceController {
    private final WorkspaceServiceImpl workspaceServiceImpl;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Workspace> getWorkspaces() {
        return workspaceServiceImpl.getWorkspaces().stream().map(workspaceDto -> modelMapper.map(workspaceDto, Workspace.class)).toList();
    }

    @GetMapping("/{id}")
    public Workspace getWorkspace(@PathVariable Long id) {
        return modelMapper.map(workspaceServiceImpl.getWorkspace(id), Workspace.class);
    }

    @PostMapping
    public WorkspaceDto createWorkspace(@RequestBody WorkspaceDto workspaceDto) {
        return workspaceServiceImpl.createWorkspace(workspaceDto);
    }

    @PutMapping()
    public WorkspaceDto updateWorkspace(@RequestBody WorkspaceSaveDto workspaceSaveDto) {
        WorkspaceSaveDto updatedWorkspaceSaveDto =  workspaceServiceImpl.updateWorkspace(workspaceSaveDto);
        return modelMapper.map(updatedWorkspaceSaveDto, WorkspaceDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkspace(@PathVariable Long id) {
        workspaceServiceImpl.deleteWorkspace(id);
    }

}
