package com.ropulva.tms.service;

import com.ropulva.tms.dto.WorkspaceDto;
import com.ropulva.tms.dto.WorkspaceSaveDto;
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
        List<Workspace> workspaces = workspaceRepository.findAll();
        return ResponseEntity.ok(workspaces.stream()
                .map(workspace -> modelMapper.map(workspace, WorkspaceDto.class))
                .toList());
    }

    public ResponseEntity<WorkspaceDto> getWorkspace(Long id) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workspace not found with id: " + id));
        return ResponseEntity.ok(modelMapper.map(workspace, WorkspaceDto.class));
    }

    public ResponseEntity<WorkspaceDto> createWorkspace(WorkspaceDto workspaceDto) {
        try {
            Workspace workspace = workspaceRepository.save(modelMapper.map(workspaceDto, Workspace.class));
            return ResponseEntity.ok(modelMapper.map(workspace, WorkspaceDto.class));
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid task data");
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Invalid task data");
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Invalid task data, constraint violation");
        }
        catch (OptimisticLockingFailureException e) {
            throw new OptimisticLockingFailureException("Invalid task data, optimistic locking failure");
        }
    }

    public ResponseEntity<WorkspaceDto> updateWorkspace(WorkspaceSaveDto workspaceSaveDto) {
        try {
            Workspace existingWorkspace = workspaceRepository.findById(workspaceSaveDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Workspace not found with id: " + workspaceSaveDto.getId()));

                Workspace updatedWorkspace = workspaceRepository.save(modelMapper.map(workspaceSaveDto, Workspace.class));
                return ResponseEntity.ok(modelMapper.map(updatedWorkspace, WorkspaceDto.class));
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid task data");
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Invalid task data");
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Invalid task data, constraint violation");
        }
        catch (OptimisticLockingFailureException e) {
            throw new OptimisticLockingFailureException("Invalid task data, optimistic locking failure");
        }
    }

    public ResponseEntity<Void> deleteWorkspace(Long id) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workspace not found with id: " + id));
        workspaceRepository.delete(workspace);
        return ResponseEntity.noContent().build();
    }
}
