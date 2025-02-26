package com.ropulva.tms.service;

import com.ropulva.tms.dto.TaskDto;
import com.ropulva.tms.dto.WorkspaceDto;
import com.ropulva.tms.dto.WorkspaceSaveDto;
import com.ropulva.tms.model.Task;
import com.ropulva.tms.model.Workspace;
import com.ropulva.tms.repository.WorkspaceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkspaceServiceImpl implements IWorkspaceService {
    final WorkspaceRepository workspaceRepository;
    final ModelMapper modelMapper;

    public ResponseEntity<List<WorkspaceDto>> getWorkspaces() {
        try{
            List<Workspace> workspaces = workspaceRepository.findAll();
            return ResponseEntity.status(200).body(workspaces.stream().map(workspace -> modelMapper.map(workspace, WorkspaceDto.class)).toList());
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<WorkspaceDto> getWorkspace(Long id) {
        try {
            Optional<Workspace> workspaceOptional = workspaceRepository.findById(id);
            if (workspaceOptional.isPresent()) {
                Workspace workspace = workspaceOptional.get();
                return ResponseEntity.status(200).body(modelMapper.map(workspace, WorkspaceDto.class));
            } else {
                return ResponseEntity.status(404).build();
            }
        }
        catch (DataIntegrityViolationException | OptimisticLockingFailureException e) {
            return ResponseEntity.status(409).build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<WorkspaceDto> createWorkspace(WorkspaceDto workspaceDto) {
        try {
            Workspace workspace = workspaceRepository.save(modelMapper.map(workspaceDto, Workspace.class));
            return ResponseEntity.status(201).body(modelMapper.map(workspace, WorkspaceDto.class));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).build();
        }
        catch (IllegalStateException e) {
            return ResponseEntity.status(409).build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<WorkspaceDto> updateWorkspace(WorkspaceSaveDto workspaceSaveDto) {
        try {
            Optional<Workspace> workspaceOptional = workspaceRepository.findById(workspaceSaveDto.getId());
            if (workspaceOptional.isPresent()) {
                Workspace updatedWorkspace = workspaceRepository.save(modelMapper.map(workspaceSaveDto, Workspace.class));
                return ResponseEntity.status(200).body(modelMapper.map(updatedWorkspace, WorkspaceDto.class));
            } else {
                return ResponseEntity.status(404).build();
            }
        }
        catch (DataIntegrityViolationException | OptimisticLockingFailureException | IllegalStateException e) {
            return ResponseEntity.status(409).build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    public ResponseEntity<Void> deleteWorkspace(Long id) {
        try {
            Optional<Workspace> workspaceOptional  = workspaceRepository.findById(id);
            if (workspaceOptional.isPresent()) {
                workspaceRepository.delete(workspaceOptional.get());
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.status(404).build();
            }
        }
        catch (DataIntegrityViolationException | OptimisticLockingFailureException e) {
            return ResponseEntity.status(409).build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
