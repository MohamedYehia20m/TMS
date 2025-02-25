package com.ropulva.tms.dto;

import com.ropulva.tms.enums.Department;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class WorkspaceSaveDto {

    private Long id;

    private String name;

    private Department department;
}
