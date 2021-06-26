
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-test</artifactId>
      <scope>test</scope>
    </dependency>
    
When Spring Boot finds Spring Security on the classpath, security is configured using sensible defaults. 
Even with no user(s) defined or password(s) specified or any other effort made by the developer, the inclusion of Spring Security in the project indicates a goal of a secure application.
