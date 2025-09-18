package com.javaTest.pipeline.entity;

import com.javaTest.build.entity.Build;
import com.javaTest.common.Status;
import com.javaTest.deploy.entity.Deploy;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Entity
@Getter
@NoArgsConstructor
public class Pipeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "build_id")
    private Build build;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deploy_id")
    private Deploy deploy;

    @Builder
    public Pipeline(String name, Status status, Deploy deploy, Build build) {
        this.name = name;
        this.status = status;
        this.deploy = deploy;
        this.build = build;
    }

}
