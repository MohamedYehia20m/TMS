package com.ropulva.tms.dto;

import com.ropulva.tms.model.Workspace;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserDto {

    private String username;

    private Workspace workspace;

    private String phone;

    private String email;
}
