# Java Sandbox Test Project

## Purpose
This repository is a general-purpose sandbox for prototyping and validating service logic, integrations, and architectural patterns before applying them to production projects.

Use it to:
- Experiment with frameworks, libraries, and patterns.
- Measure performance and behavior with realistic logging.
- Validate designs with small, focused experiments and tests.

> Today the repo includes an async service example using `CompletableFuture`. In the future, add more experiments (persistence, messaging, caching, resilience, etc.).

## Project Structure
Domain-based organization to keep experiments isolated. The structure may evolve per experiment.
```
src/
├── main/java/com/javaTest/
│   ├── JavaTestApplication.java          # Spring Boot main application
│   └── <domain>/                         # Each experiment lives under its own domain package
│       └── ...
├── test/java/com/javaTest/<domain>/
│   └── ...                               # Unit/integration tests per domain
└── main/resources/
    └── application.yml                   # Logging and runtime config
```

### Current Example (subject to change)
- `async/asyncService.java` — simple async patterns (`CompletableFuture`) for demonstration.
- `async/AsyncServiceTest.java` — logs start/end timestamps and compares sequential vs parallel execution time.

## Getting Started
### Prerequisites
- Java 21
- Gradle 8.x
- Spring Boot 3.2.x

### Build and Test
```bash
# Run all tests
./gradlew test

# Run a specific test class
./gradlew test --tests "com.javaTest.async.AsyncServiceTest"

# Run with more insight
./gradlew test --info
```

## How to Add a New Experiment
1. Create a new domain package under `src/main/java/com/javaTest/<your-domain>/`.
2. Add minimal code to demonstrate or validate the idea.
3. Add tests under `src/test/java/com/javaTest/<your-domain>/`.
4. If necessary, configure logging or properties in `src/main/resources/application.yml`.
5. Document the experiment briefly in this README (section: Experiments).

### Suggested Naming
- Packages: `com.javaTest.<domain>`
- Tests: `<ClassName>Test` (JUnit 5)
- Keep experiments small, focused, and disposable.

## Logging
Logging is configured via `src/main/resources/application.yml`.
- Default levels can be adjusted per package (e.g., `com.javaTest: DEBUG`).
- Tests may log timings, start/end markers, and results for easy comparison.

## Dependencies
This project intentionally keeps dependencies minimal:
- Spring Boot starter(s)
- JUnit 5 (testing)
- Lombok (optional, for boilerplate reduction)

You can add experiment-specific dependencies in `build.gradle` as needed. Prefer isolating additions to what the experiment requires.

## Experiments (Living List)
- Async patterns with `CompletableFuture` (sequential vs parallel timing)
- Add your next items here: persistence (JPA), messaging (Kafka/RabbitMQ), caching (Caffeine/Redis), REST clients (WebClient/Feign), resilience (Retry/Circuit Breaker), etc.

## Guidelines
- Keep changes scoped to the experiment domain.
- Prefer clear logs over assertions when the goal is behavioral exploration.
- Add assertions when validating invariants or performance thresholds.
- Clean up experiments when they’re no longer useful.

## Status
- Active: Experimental / Sandbox
- Goal: Validate ideas quickly, then promote proven patterns to production repositories.
