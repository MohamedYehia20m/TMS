package com.ropulva.tms.model;

import com.ropulva.tms.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "tasks" , schema = "public")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true)
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "workspace_id" , nullable = false)
    private Workspace workspace;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
