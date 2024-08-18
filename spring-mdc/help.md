# MDC Spring Test Project

How to use TaskDecorator to copy MDC data to @Async threads



From 

https://moelholm.com/blog/2017/07/24/spring-43-using-a-taskdecorator-to-copy-mdc-data-to-async-threads

```shell
mvn archetype:generate -DgroupId=com.syedm.app -DartifactId=spring-mdc -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

By default springboot uses logback. Therefore it has to be excluded. Details are in :

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto.logging.log4j

Other useful links are as follows

|Url | Description| Stars |
|---|---|---|
| https://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/PatternLayout.html | pattern layout explanation and examples (official) | *** |
| https://www.loggly.com/ultimate-guide/java-logging-basics/ | Java Logging Basics | *** |
| https://www.baeldung.com/spring-boot-logging | Logging in spring boot, default logback logging, logback configuration, Log4j2 configuration (exclusion of logback), Lombok and Slf4 | *** |
| https://medium.com/sipios/how-to-use-log4j-and-mdc-in-java-spring-boot-application-26b1a77f5c3e | Log4j and MDC, use with @Async and ThreadPoolTaskExecutor Bean, decoration using decorate method and setting async executor with task decorator | *** |
| https://moelholm.com/blog/2017/07/24/spring-43-using-a-taskdecorator-to-copy-mdc-data-to-async-threads |
| https://www.baeldung.com/java-log4j-properties-guide | log4j properties |
| https://howtodoinjava.com/log4j2/log4j2-with-slf4j/ | basic Java (not spring boot)  dependencies and configuration | 
https://examples.javacodegeeks.com/java-development/enterprise-java/slf4j/slf4j-configuration-file-example/
https://howtodoinjava.com/logback/logging-with-mapped-diagnostic-context/

https://howtodoinjava.com/log4j2/useful-conversion-pattern-examples/
https://www.loggly.com/ultimate-guide/java-logging-basics/
https://www.baeldung.com/spring-boot-logging
https://medium.com/sipios/how-to-use-log4j-and-mdc-in-java-spring-boot-application-26b1a77f5c3e


Common mistakes which I had made
- Not declaring MDCFilter as component
- Not overriding the doFilter method
- log4j2.xml not in resources folder causing PatternLayout to not take effect



