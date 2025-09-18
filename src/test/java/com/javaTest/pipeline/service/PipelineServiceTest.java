package com.javaTest.pipeline.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PipelineServiceTest {

    @Autowired
    PipelineService pipelineService;

    @Test
    void execute() {
        pipelineService.execute();
    }
}