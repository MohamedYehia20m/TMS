package com.ropulva.tms.dto;

import com.ropulva.tms.enums.Status;
import com.ropulva.tms.model.Workspace;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TaskSaveDto {

    private Long id;

    private String title;

    private String description;

    private Workspace workspace;

    private Status status;
}
