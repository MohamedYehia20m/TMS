package com.ropulva.tms.model;

import com.ropulva.tms.enums.Department;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "workspaces")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

}
