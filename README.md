## 🚀 Multi‑module Spring Cloud / Netflix OSS sample

Minimal microservices system that demonstrates classic Spring Cloud Netflix components and patterns using a Maven multi‑module build.

The domain contains two business services: a Department Service and a User Profile Service. The User Profile Service enriches user profiles by calling the Department Service.

### ⚙️ Tech stack

Java 21, Maven 3, Spring Boot 2.7.x, Spring Cloud 2021.x

Infra for local dev: MongoDB 5.x, MySQL 5.7.x, Zipkin, Prometheus, Grafana, SonarQube (via `compose.yaml`).

Utilities: Liquibase, Testcontainers, WireMock, Micrometer, Checkstyle, SpotBugs, PMD, Qulice, JaCoCo, Modernizer.

### 🧱 Modules

All modules are declared in the root `pom.xml` as a multi‑module reactor.

| Module                  | Type              | Purpose                                        | Default port | Notes                                                          |
| ----------------------- | ----------------- | ---------------------------------------------- | ------------ | -------------------------------------------------------------- |
| `service-registry`      | Spring Boot app   | Eureka Server for service discovery            | 8080         | Set `SERVER_PORT` to avoid clashes locally                     |
| `config-server`         | Spring Boot app   | Centralized configuration backed by Git        | 8080         | Imports Git repo defined in `config-server/application.yml`    |
| `api-gateway`           | Spring Boot app   | Edge gateway (Zuul) + Hystrix circuit breakers | 8080         | Routes `/api/v1/departments/**` and `/api/v1/user-profiles/**` |
| `hystrix-dashboard`     | Spring Boot app   | Hystrix Dashboard                              | 8080         | Exposes Hystrix stream consumption                             |
| `department-service`    | Spring Boot app   | MySQL + Liquibase backed Department API        | 8080         | JPA, Liquibase migrations under `migrations/`                  |
| `user-profile-service`  | Spring Boot app   | MongoDB backed User Profile API                | 8080         | Calls Department service via gateway/discovery                 |
| `boot-starter-cache`    | Library (starter) | Opinionated cache starter (JCache/Ehcache)     | —            | Auto‑config via `spring.factories`                             |
| `boot-starter-mvc-rest` | Library (starter) | REST/MVC common config (errors, JSON, filters) | —            | Auto‑config via `spring.factories`                             |

Notes:

- By default each service binds to `8080` (see `server.port`). When running multiple apps locally, pass a unique `SERVER_PORT` per service.
- Actuator endpoints are enabled for health/metrics; Prometheus and Zipkin tracing are preconfigured.

### 🔁 Gateway routes

`api-gateway` forwards to registered services via Eureka:

- `lb://department-service` → `/api/v1/departments/**` (Hystrix fallback `/departmentServiceFallback`)
- `lb://user-profile-service` → `/api/v1/user-profiles/**` (Hystrix fallback `/userProfileServiceFallback`)

### ▶️ Quick start

1. Start local infra (MongoDB, MySQL, Zipkin, Prometheus, Grafana, SonarQube):

```shell script
docker compose -f compose.yaml up -d
```

2. Build everything (skipping tests if you just want binaries):

```shell script
./mvnw -q -DskipTests clean install
```

3. Run the services in separate terminals; assign unique ports via `SERVER_PORT`:

```shell script
# Discovery + Config + Dashboard
SERVER_PORT=3001 ./mvnw -pl service-registry -am spring-boot:run
SERVER_PORT=3002 ./mvnw -pl config-server -am spring-boot:run
SERVER_PORT=3003 ./mvnw -pl hystrix-dashboard -am spring-boot:run

# Domain services
SERVER_PORT=3004 ./mvnw -pl department-service -am spring-boot:run
SERVER_PORT=3005 ./mvnw -pl user-profile-service -am spring-boot:run

# Edge gateway
SERVER_PORT=3000 ./mvnw -pl api-gateway -am spring-boot:run
```

Suggested local URLs:

- Service Registry (Eureka): `http://localhost:3001`
- Config Server: `http://localhost:3002`
- Hystrix Dashboard: `http://localhost:3003`
- API Gateway: `http://localhost:3000`
- Zipkin: `http://localhost:9411`
- Prometheus: `http://localhost:9090` (via host network in compose)
- Grafana: `http://localhost:3000` (if not using the gateway port) — adjust as needed

Example requests:

```shell script
# Departments
curl http://localhost:3000/api/v1/departments

# User Profiles (enriched with department info)
curl http://localhost:3000/api/v1/user-profiles
```

### 🧪 Testing

Run full test suite with Testcontainers:

```shell script
./mvnw verify -P use-testcontainers
```

### 🧭 Project structure (high level)

```
sample-netflix-oss-usage
├─ service-registry
├─ config-server
├─ api-gateway
├─ hystrix-dashboard
├─ department-service
├─ user-profile-service
├─ boot-starter-cache
└─ boot-starter-mvc-rest
```

### 📝 Code conventions

The code follows the Google Java Style Guide. Quality gates and analysis are configured via:

- SonarQube, PMD, Checkstyle, SpotBugs, Qulice, JaCoCo, Modernizer

### 📖 Cursor Rules

The project includes Cursor AI assistant rules for consistent code quality:

- **Java Best Practices**: Rules for code organization, design patterns, performance, security, testing, and common pitfalls
- **Spring Boot Best Practices**: Guidelines for Spring Boot application structure, configuration, dependency injection, testing, and API design

These rules help maintain coding standards and provide automated guidance when using the Cursor AI assistant.

### 📦 Versioning

Project uses three‑segment [CalVer](https://calver.org/): `YY.MM.MICRO`.

—

If you run into port or config conflicts locally, override via environment variables:

- `SERVER_PORT`, `EUREKA_SERVER_URL`, `CONFIGSERVER_IMPORT`, DB connection strings, etc.

Happy hacking!
