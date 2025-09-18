package com.javaTest.build.entity;

import com.javaTest.common.Status;
import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Build {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;
}
