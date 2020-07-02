# Surmiser
Surmiser was develop to demo the use of [Quarkus](https://quarkus.io), the Supersonic Subatomic Java Framework, to create a cloud native microservice that exposes a few APIs for testing. The app surmises about a person's name to predict their gender, age, and nationality. The service is powered by [Genderize.io](https://genderize.io), [Agify.io](https://agify.io), and [Nationalize.io](https://nationalize.io), which it uses to produce a determination based on the supplied name.

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
    GET	/api/history
    GET /api/history?name={name}
    GET /health
    GET /health/live
    GET /health/ready

The APIs can also be tested using the interactive swagger documentation located at `localhost:8080/swagger-ui`.

## Running the application in dev mode
You can run the application in dev mode that enables live coding using:
```
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

|Method       | Address                                                                                                    |
|------------:|:-----------------------------------------------------------------------------------------------------------|
|PayPal:      | [https://www.paypal.me/stevenjdh](https://www.paypal.me/stevenjdh "Steven's Paypal Page")                  |
|Bitcoin:     | 3GyeQvN6imXEHVcdwrZwKHLZNGdnXeDfw2                                                                         |
|Litecoin:    | MAJtR4ccdyUQtiiBpg9PwF2AZ6Xbk5ioLm                                                                         |
|Ethereum:    | 0xa62b53c1d49f9C481e20E5675fbffDab2Fcda82E                                                                 |
|Dash:        | Xw5bDL93fFNHe9FAGHV4hjoGfDpfwsqAAj                                                                         |
|Zcash:       | t1a2Kr3jFv8WksgPBcMZFwiYM8Hn5QCMAs5                                                                        |
|PIVX:        | DQq2qeny1TveZDcZFWwQVGdKchFGtzeieU                                                                         |
|Ripple:      | rLHzPsX6oXkzU2qL12kHCH8G8cnZv1rBJh<br />Destination Tag: 2357564055                                        |
|Monero:      | 4GdoN7NCTi8a5gZug7PrwZNKjvHFmKeV11L6pNJPgj5QNEHsN6eeX3D<br />&#8618;aAQFwZ1ufD4LYCZKArktt113W7QjWvQ7CWDXrwM8yCGgEdhV3Wt|


// Steven Jenkins De Haro ("StevenJDH" on GitHub)
