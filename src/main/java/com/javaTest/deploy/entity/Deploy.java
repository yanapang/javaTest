package com.javaTest.deploy.entity;

import com.javaTest.common.Status;
import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Deploy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;
}
