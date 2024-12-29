## Departments and User Profile Microservices

Minimal microservices example demo - using legacy Spring Cloud / Netflix OSS Project.

The project maintains two microservices, the department service and the user profile service. Each user profile can be linked to any department.
The User Profile Service allows you to retrieve department data by calling the Department Service REST API and complete the profile data response
with department information.

### Technology stack

java 21, Maven 3, Spring Boot 2.7, Spring Cloud 2021.0.3
mongo:5.0, mysql:5.7.43, zipkin-slim:2.23.

_Including utils:_ liquibase, WireMock, Mongodb & Mysql testcontainers, docker-compose._dev_.yml,
micrometer, _checkstyle_ configuration, SpotBugs, PMD etc.

This sample project specifically demonstrates the following features using the Spring Cloud ecosystem.

- Service Registry
- Centralized Configuration
- (Client Side) Load Balancing
- Circuit Breaker pattern

Spring Cloud / Netflix OSS

| Service/Feature           |           Spring Cloud Component           | Netflix Component              |
| ------------------------- | :----------------------------------------: | ------------------------------ |
| Service Discovery         |            Spring Cloud Eureka             | Eureka Server<br>Eureka Client |
| Circuit Breaker           |            Spring Cloud Hystrix            | Hystrix<br>Hystrix Dashboard   |
| Load Balancing            |         Spring Cloud Load Balancer         |                                |
| Centralized Configuration |         Spring Cloud Config Server         |                                |
| Distributed Tracing       | Spring Cloud Sleuth<br>Spring Cloud Zipkin |                                |

### Prerequisites

- Install Docker [https://docs.docker.com/get-docker/](https://docs.docker.com/get-docker/) - at least 1.6.0
- Add new version of Docker Compose [https://docs.docker.com/compose/install/](https://docs.docker.com/compose/install/)
- Spin-up single instance of MongoDB, MySQL and Zipkin by running command:

```
docker compose -f compose.yaml up -d
```

### Code conventions

The code follows [Google Code Conventions](https://google.github.io/styleguide/javaguide.html). Code
quality is measured by:

- Sonarqube
- [PMD](https://pmd.github.io/)
- [CheckStyle](https://checkstyle.sourceforge.io/)
- [SpotBugs](https://spotbugs.github.io/)

### Tests

This project has standard JUnit tests. To run them execute this command:

```text
./mvnw verify -P use-testcontainers
```

## Versioning

Project uses a three-segment [CalVer](https://calver.org/) scheme, with a short year in the major version slot, short month in the minor version slot, and micro/patch version in the third
and final slot.

```
YY.MM.MICRO
```

1. **YY** - short year - 6, 16, 106
1. **MM** - short month - 1, 2 ... 11, 12
1. **MICRO** - "patch" segment
