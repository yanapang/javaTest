package com.javaTest.pipeline.repository;


import com.javaTest.pipeline.entity.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PipelineRepository extends JpaRepository<Pipeline, Long> {
}
