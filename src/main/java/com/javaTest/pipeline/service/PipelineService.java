package com.javaTest.pipeline.service;

import com.javaTest.build.service.BuildService;
import com.javaTest.common.Status;
import com.javaTest.deploy.service.DeployService;
import com.javaTest.pipeline.entity.Pipeline;
import com.javaTest.pipeline.model.PipelineSaveRequest;
import com.javaTest.pipeline.repository.PipelineRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PipelineService {

    private final BuildService buildService;
    private final DeployService deployService;

    private final PipelineRepository pipelineRepository;

    public List<Pipeline> retrievePipelines() {
        return pipelineRepository.findAll();
    }

    @Transactional
    public Pipeline save(PipelineSaveRequest pipeline) {
        var entity = Pipeline.builder()
                .name(pipeline.name())
                .status(Status.READY)
                .build(buildService.getBuild(pipeline.buildId()))
                .deploy(deployService.getDeploy(pipeline.deployId()))
                .build();

        return pipelineRepository.save(entity);
    }


    public String execute() {
        log.debug("execute pipeline");

        try {
            var build = buildService.build();
            var deploy = deployService.deploy();

            build.whenComplete((Result, throwable) ->
                    log.debug(String.format(" ======== Build Result : %s", build))
            );

            deploy.whenComplete((Result, throwable) ->
                    log.debug(String.format(" ======== Deploy Result : %s", deploy))
            );

        } catch (Exception ex) {
            log.error("Pipeline failed", ex);
        }
        return "ok";
    }

}
