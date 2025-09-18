package com.javaTest.build.entity;

import com.javaTest.common.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Entity
@NoArgsConstructor
@Getter
public class Build {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Build(String name, Status status) {
        this.name = name;
        this.status = status;
    }
}
