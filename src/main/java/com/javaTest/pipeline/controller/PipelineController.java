package com.javaTest.pipeline.controller;

import com.javaTest.pipeline.entity.Pipeline;
import com.javaTest.pipeline.model.PipelineSaveRequest;
import com.javaTest.pipeline.service.PipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pipelines")
public class PipelineController {

    private final PipelineService pipelineService;

    @GetMapping("")
    public List<Pipeline> getAll() {
        return pipelineService.retrievePipelines();
    }

    @PostMapping("")
    public Pipeline save(@RequestBody PipelineSaveRequest pipeline) {
        return pipelineService.save(pipeline);
    }

    @GetMapping("/execute")
    public String execute() {
        return pipelineService.execute();
    }

}
