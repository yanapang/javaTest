package com.javaTest.build.service;

import com.javaTest.build.entity.Build;
import com.javaTest.build.repository.BuildRepository;
import com.javaTest.common.Status;
import com.javaTest.deploy.entity.Deploy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuildService {

    private final BuildRepository buildRepository;

    @Async
    public CompletableFuture<String> build() {
        log.debug("========== [Build executed] ==========");

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            return "Build ok";
        }).whenComplete((result, throwable) -> {
                    var testDeploy = Build.builder()
                            .name("TEST_DEPLOY")
                            .status(Status.SUCCESS)
                            .build();
                    buildRepository.save(testDeploy);
                }
        );
    }
}