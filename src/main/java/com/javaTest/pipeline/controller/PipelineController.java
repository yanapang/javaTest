package com.javaTest.pipeline.controller;

import com.javaTest.pipeline.service.PipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pipelines")
public class PipelineController {

    private final PipelineService pipelineService;

    @GetMapping("/execute")
    public String execute() {
        return pipelineService.execute();
    }

}
