package com.javaTest.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class asyncService {

    /**
     * Simple async method that returns a string after a delay
     */
    public CompletableFuture<String> getAsyncMessage(String input) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // Simulate some work
                log.info("Processing async message: {}", input);
                return "Processed: " + input;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted", e);
            }
        });
    }

    /**
     * Async method that performs a calculation
     */
    public CompletableFuture<Integer> calculateAsync(int a, int b) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500); // Simulate calculation time
                log.info("Calculating {} + {}", a, b);
                return a + b;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted", e);
            }
        });
    }

    /**
     * Async method that combines two CompletableFutures
     */
    public CompletableFuture<String> combineAsync(String first, String second) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(300);
                return "First: " + first;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted", e);
            }
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(400);
                return "Second: " + second;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted", e);
            }
        });

        return future1.thenCombine(future2, (result1, result2) -> result1 + " | " + result2);
    }

    /**
     * Async method that can fail
     */
    public CompletableFuture<String> riskyAsync(boolean shouldFail) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200);
                if (shouldFail) {
                    throw new RuntimeException("Intentional failure");
                }
                return "Success!";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted", e);
            }
        });
    }

}