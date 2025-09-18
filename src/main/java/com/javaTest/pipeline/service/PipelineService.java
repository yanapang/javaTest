package com.javaTest.pipeline.service;

import com.javaTest.build.service.BuildService;
import com.javaTest.deploy.service.DeployService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PipelineService {

    private final BuildService buildService;
    private final DeployService deployService;

    public String execute() {
        log.debug("execute pipeline");

        try {
            var build = buildService.build();
            var deploy = deployService.deploy();

            log.debug(String.format("Build Result : %s", build));
            log.debug(String.format("Deploy Result : %s", deploy));

        } catch (Exception ex) {
            log.error("Pipeline failed", ex);
        }
        return "ok";
    }

}
