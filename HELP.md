# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.0/maven-plugin/build-image.html)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/3.3.0/reference/testing/testcontainers.html#testing.testcontainers)
* [Testcontainers Kafka Modules Reference Guide](https://java.testcontainers.org/modules/kafka/)
* [Testcontainers Postgres Module Reference Guide](https://java.testcontainers.org/modules/databases/postgres/)
* [Spring Web](https://docs.spring.io/spring-boot/3.3.0/reference/web/servlet.html)
* [Spring Security](https://docs.spring.io/spring-boot/3.3.0/reference/web/spring-security.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.3.0/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/3.3.0/reference/io/validation.html)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/3.3.0/reference/messaging/kafka.html)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/3.3.0/reference/data/nosql.html#data.nosql.redis)
* [Testcontainers](https://java.testcontainers.org/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)

### Testcontainers support

This project uses [Testcontainers at development time](https://docs.spring.io/spring-boot/3.3.0/reference/features/dev-services.html#features.dev-services.testcontainers).

Testcontainers has been configured to use the following Docker images:

* [`confluentinc/cp-kafka:latest`](https://hub.docker.com/r/confluentinc/cp-kafka)
* [`postgres:latest`](https://hub.docker.com/_/postgres)
* [`redis:latest`](https://hub.docker.com/_/redis)

Please review the tags of the used images and set them to the same as you're running in production.

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

