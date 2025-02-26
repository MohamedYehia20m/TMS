package com.ropulva.tms.controller;

import com.ropulva.tms.dto.WorkspaceDto;
import com.ropulva.tms.dto.WorkspaceSaveDto;
import com.ropulva.tms.model.Task;
import com.ropulva.tms.model.Workspace;
import com.ropulva.tms.service.WorkspaceServiceImpl;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces")
public class WorkspaceController {
    private final WorkspaceServiceImpl workspaceServiceImpl;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<WorkspaceDto>> getWorkspaces() {
        return workspaceServiceImpl.getWorkspaces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkspaceDto> getWorkspace(@PathVariable Long id) {
        return workspaceServiceImpl.getWorkspace(id);
    }

    @PostMapping
    public ResponseEntity<WorkspaceDto> createWorkspace(@RequestBody WorkspaceDto workspaceDto) {
        return workspaceServiceImpl.createWorkspace(workspaceDto);
    }

    @PutMapping()
    public ResponseEntity<WorkspaceDto> updateWorkspace(@RequestBody WorkspaceSaveDto workspaceSaveDto) {
        return workspaceServiceImpl.updateWorkspace(workspaceSaveDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable Long id) {
        return workspaceServiceImpl.deleteWorkspace(id);
    }

}
