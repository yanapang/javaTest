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

## DB Setting
- Create Docker Container using following commands
```
docker run -d --name mariadbForPrj \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=myPassword \
  -v /my/own/datadir:/var/lib/mysql \
  mariadb
```


## Dependencies
This project intentionally keeps dependencies minimal:
- Spring Boot starter(s)
- JUnit 5 (testing)
- Lombok (optional, for boilerplate reduction)
- Maria DB Client

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
