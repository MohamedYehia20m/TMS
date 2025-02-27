package com.ropulva.tms.dto;

import com.ropulva.tms.enums.Role;
import com.ropulva.tms.model.Workspace;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserSaveDto {

    private Long id;

    private String username;

    private Role role;

    private Workspace workspace;

    private String phone;

    private String email;
}
