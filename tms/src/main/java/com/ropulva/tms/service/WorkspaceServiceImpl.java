package com.ropulva.tms.service;

import com.ropulva.tms.dto.WorkspaceDto;
import com.ropulva.tms.dto.WorkspaceSaveDto;
import com.ropulva.tms.model.Workspace;
import com.ropulva.tms.repository.WorkspaceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkspaceServiceImpl implements IWorkspaceService {
    final WorkspaceRepository workspaceRepository;
    final ModelMapper modelMapper;

    public List<WorkspaceDto> getWorkspaces() {
        List<Workspace> workspacesEntities = workspaceRepository.findAll();
        return workspacesEntities.stream().map(workspace -> modelMapper.map(workspace,WorkspaceDto.class)).toList();
    }

    public WorkspaceDto getWorkspace(Long id) {
        Optional<Workspace> workspaceOptional = workspaceRepository.findById(id);
        if (workspaceOptional.isPresent()) {
            return modelMapper.map(workspaceOptional.get(), WorkspaceDto.class);
        }
        else {
            throw new EntityNotFoundException("Workspace not found with id: " + id);
        }
    }

    public WorkspaceDto createWorkspace(WorkspaceDto workspaceDto) {
        Workspace workspace = workspaceRepository.save(modelMapper.map(workspaceDto, Workspace.class));
        return modelMapper.map(workspace, WorkspaceDto.class);
    }

    public WorkspaceSaveDto updateWorkspace(WorkspaceSaveDto workspaceSaveDto) {
        if (workspaceSaveDto.getId() == null) {
            throw new RuntimeException("Workspace id is required");
        }

        Workspace existingWorkspace = modelMapper.map(workspaceSaveDto, Workspace.class);
        Workspace savedWorkspace = workspaceRepository.save(existingWorkspace);

        return modelMapper.map(savedWorkspace, WorkspaceSaveDto.class);
    }

    public void deleteWorkspace(Long id) {
        workspaceRepository.deleteById(id);
    }
}
