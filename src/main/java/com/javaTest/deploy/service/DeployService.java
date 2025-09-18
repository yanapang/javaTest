package com.javaTest.deploy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeployService {

    public String deploy() throws InterruptedException {
        log.debug("========== [Deploy executed] ==========");

        Thread.sleep(5000);

        return "Deploy ok";
    }
}
