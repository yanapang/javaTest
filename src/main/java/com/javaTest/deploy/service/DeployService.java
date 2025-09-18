package com.javaTest.deploy.service;

import com.javaTest.deploy.repository.DeployRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeployService {

    private final DeployRepository deployRepository;
    
    @Async
    public CompletableFuture<String> deploy() {
        log.debug("========== [Deploy executed] ==========");

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            return "Deploy ok";
        });
    }
}
