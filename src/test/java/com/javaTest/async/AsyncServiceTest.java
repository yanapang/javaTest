package com.javaTest.async;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Slf4j
class AsyncServiceTest {

    private asyncService asyncService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @BeforeEach
    void setUp() {
        asyncService = new asyncService();
    }

    @Test
    void testAsyncOperationsWithTiming() throws ExecutionException, InterruptedException {
        log.info("=== Starting Async Operations Test ===");
        
        // Test 1: Simple async message
        LocalDateTime start1 = LocalDateTime.now();
        log.info("TEST 1 - getAsyncMessage START: {}", start1.format(formatter));
        
        CompletableFuture<String> future1 = asyncService.getAsyncMessage("Hello World");
        String result1 = future1.get();
        
        LocalDateTime end1 = LocalDateTime.now();
        log.info("TEST 1 - getAsyncMessage END: {}", end1.format(formatter));
        log.info("TEST 1 - Result: {}", result1);
        log.info("TEST 1 - Duration: {} ms", java.time.Duration.between(start1, end1).toMillis());
        
        log.info(""); // Empty line for readability
        
        // Test 2: Async calculation
        LocalDateTime start2 = LocalDateTime.now();
        log.info("TEST 2 - calculateAsync START: {}", start2.format(formatter));
        
        CompletableFuture<Integer> future2 = asyncService.calculateAsync(10, 20);
        Integer result2 = future2.get();
        
        LocalDateTime end2 = LocalDateTime.now();
        log.info("TEST 2 - calculateAsync END: {}", end2.format(formatter));
        log.info("TEST 2 - Result: {}", result2);
        log.info("TEST 2 - Duration: {} ms", java.time.Duration.between(start2, end2).toMillis());
        
        log.info(""); // Empty line for readability
        
        // Test 3: Combined async operations
        LocalDateTime start3 = LocalDateTime.now();
        log.info("TEST 3 - combineAsync START: {}", start3.format(formatter));
        
        CompletableFuture<String> future3 = asyncService.combineAsync("Alpha", "Beta");
        String result3 = future3.get();
        
        LocalDateTime end3 = LocalDateTime.now();
        log.info("TEST 3 - combineAsync END: {}", end3.format(formatter));
        log.info("TEST 3 - Result: {}", result3);
        log.info("TEST 3 - Duration: {} ms", java.time.Duration.between(start3, end3).toMillis());
        
        log.info("=== All Async Operations Completed ===");
    }

    @Test
    void testParallelAsyncOperations() throws ExecutionException, InterruptedException {
        log.info("=== Starting PARALLEL Async Operations Test ===");
        
        // Record overall start time
        LocalDateTime overallStart = LocalDateTime.now();
        log.info("PARALLEL TEST - Overall START: {}", overallStart.format(formatter));
        
        // Start all async operations at the same time (parallel execution)
        log.info("Starting all async operations simultaneously...");
        
        CompletableFuture<String> messageFuture = asyncService.getAsyncMessage("Parallel Message");
        log.info("- getAsyncMessage started");
        
        CompletableFuture<Integer> calcFuture = asyncService.calculateAsync(5, 15);
        log.info("- calculateAsync started");
        
        CompletableFuture<String> combineFuture = asyncService.combineAsync("Parallel", "Execution");
        log.info("- combineAsync started");
        
        CompletableFuture<String> riskyFuture = asyncService.riskyAsync(false);
        log.info("- riskyAsync started");
        
        log.info("All operations started! Now waiting for completion...");
        log.info("");
        
        // Wait for all to complete and collect results
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
            messageFuture, calcFuture, combineFuture, riskyFuture
        );
        
        allFutures.get(); // Wait for all to complete
        
        LocalDateTime overallEnd = LocalDateTime.now();
        log.info("PARALLEL TEST - Overall END: {}", overallEnd.format(formatter));
        
        // Log individual results
        log.info("=== PARALLEL RESULTS ===");
        log.info("Message Result: {}", messageFuture.get());
        log.info("Calculation Result: {}", calcFuture.get());
        log.info("Combine Result: {}", combineFuture.get());
        log.info("Risky Result: {}", riskyFuture.get());
        
        long totalDuration = java.time.Duration.between(overallStart, overallEnd).toMillis();
        log.info("Total Parallel Duration: {} ms", totalDuration);
        
        // Expected sequential time would be: 1000 + 500 + 700 + 200 = 2400ms
        // Parallel should be much faster (around 1000ms - the longest operation)
        log.info("Expected sequential time: ~2400ms, Actual parallel time: {}ms", totalDuration);
        log.info("Time saved by parallel execution: ~{}ms", (2400 - totalDuration));
        
        log.info("=== PARALLEL Async Operations Completed ===");
    }
}
