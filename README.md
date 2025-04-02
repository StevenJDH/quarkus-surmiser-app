# Surmiser

![build-test](https://github.com/StevenJDH/quarkus-surmiser-app/workflows/build/badge.svg)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/dd8f9d7560294b428992187dd09dfdcf)](https://app.codacy.com/gh/StevenJDH/quarkus-surmiser-app/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=StevenJDH/quarkus-surmiser-app&amp;utm_campaign=Badge_Grade)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_quarkus-surmiser-app&metric=alert_status)](https://sonarcloud.io/dashboard?id=StevenJDH_quarkus-surmiser-app)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_quarkus-surmiser-app&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=StevenJDH_quarkus-surmiser-app)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_quarkus-surmiser-app&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=StevenJDH_quarkus-surmiser-app)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_quarkus-surmiser-app&metric=sqale_index)](https://sonarcloud.io/dashboard?id=StevenJDH_quarkus-surmiser-app)
[![Sonar Violations (long format)](https://img.shields.io/sonar/violations/StevenJDH_quarkus-surmiser-app?format=long&server=https%3A%2F%2Fsonarcloud.io)](https://sonarcloud.io/dashboard?id=StevenJDH_quarkus-surmiser-app)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_quarkus-surmiser-app&metric=security_rating)](https://sonarcloud.io/dashboard?id=StevenJDH_quarkus-surmiser-app)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_quarkus-surmiser-app&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=StevenJDH_quarkus-surmiser-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_quarkus-surmiser-app&metric=coverage)](https://sonarcloud.io/dashboard?id=StevenJDH_quarkus-surmiser-app)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_quarkus-surmiser-app&metric=ncloc)](https://sonarcloud.io/dashboard?id=StevenJDH_quarkus-surmiser-app)
![Maintenance](https://img.shields.io/badge/yes-4FCA21?label=maintained&style=flat)
![GitHub](https://img.shields.io/github/license/StevenJDH/quarkus-surmiser-app)

Surmiser was develop to demo the use of [Quarkus](https://quarkus.io), the Supersonic Subatomic Java Framework, to create a cloud native microservice that exposes a few APIs for testing. The app surmises about a person's name to predict their gender, age, and nationality. The service is powered by [Genderize.io](https://genderize.io), [Agify.io](https://agify.io), and [Nationalize.io](https://nationalize.io), which it uses to produce a determination based on the supplied name.

[![Buy me a coffee](https://img.shields.io/static/v1?label=Buy%20me%20a&message=coffee&color=important&style=flat&logo=buy-me-a-coffee&logoColor=white)](https://www.buymeacoffee.com/stevenjdh)

## Features
* Third-party service consolidation using MicroProfiling.
* In memory database with Hibernate for history persistent storage while testing.
* Logging of requests using an HTTP interceptor.
* Can be run in JVM mode, or it can be compiled to Native code (Linux, Windows, and macOS) for millisecond speeds with a low memory footprint.
* Liveness and Readiness health checks with some being integrated automatically like for the DB.
* Retry, circuit breaker, and fallback fault tolerance strategies using MicroProfiling.
* Automated testing using REST-assured.
* Automatic YAML generation for Kubernetes or containerized environments.

## Endpoints
Below are the API references used in the demo that are exposed on port 8080.

    GET /api/person?name={name}
    GET /api/history
    GET /api/history?name={name}
    GET /q/health
    GET /q/health/live
    GET /q/health/ready

The APIs can also be tested using the interactive swagger documentation located at `localhost:8080/q/swagger-ui`.

## Running the application in dev mode
You can run the application in dev mode that enables live coding using:
```bash
./mvnw quarkus:dev
```

## Packaging and running the application
The application can be packaged using `./mvnw package`.
It produces the `surmiser-app-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/surmiser-app-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable
You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/surmiser-app-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Do you have any questions?
Many commonly asked questions are answered in the FAQ:
[https://github.com/StevenJDH/quarkus-surmiser-app/wiki/FAQ](https://github.com/StevenJDH/quarkus-surmiser-app/wiki/FAQ)

## Want to show your support?

|Method          | Address                                                                                   |
|---------------:|:------------------------------------------------------------------------------------------|
|PayPal:         | [https://www.paypal.me/stevenjdh](https://www.paypal.me/stevenjdh "Steven's Paypal Page") |
|Cryptocurrency: | [Supported options](https://github.com/StevenJDH/StevenJDH/wiki/Donate-Cryptocurrency)    |


// Steven Jenkins De Haro ("StevenJDH" on GitHub)
