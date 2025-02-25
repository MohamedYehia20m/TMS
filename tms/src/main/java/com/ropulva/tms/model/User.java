package com.ropulva.tms.model;

import com.ropulva.tms.enums.Department;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @ManyToOne
    @JoinColumn(name = "workspace_id" , nullable = false)
    private Workspace workspace;

    @Column(length = 11)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;
}
