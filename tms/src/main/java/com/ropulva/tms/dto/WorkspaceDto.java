package com.ropulva.tms.dto;

import com.ropulva.tms.enums.Department;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class WorkspaceDto {

    private String name;

    private Department department;
}
