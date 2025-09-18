package com.javaTest.deploy.entity;

import com.javaTest.common.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Entity
@Getter
@NoArgsConstructor
public class Deploy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Deploy(String name, Status status) {
        this.name = name;
        this.status = status;
    }
}
