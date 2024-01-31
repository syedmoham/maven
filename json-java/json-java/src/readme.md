# simple project demoing use of json and java

## 1. Initialize maven project using :

```
mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-simple -DarchetypeVersion=1.4
```

## Add dependency for jackson

Add dependency for jackson

```xml
<dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.5.3</version>
</dependency>
```


## Add files
App.java : contains the main code
Organizatin.java contains 


## Add maven jar plugin
```xml
        <plugin>
          <artifactId>maven-jar-plugin</artifactId>
          <version>3.0.2</version>
          <configuration>
            <archive>
              <manifest>
                <addClasspath>true</addClasspath>
                <mainClass>com.syedm.App</mainClass>
              </manifest>
            </archive>
          </configuration>
        </plugin>
```

## compile

```
D:\repos\java\maven\json-java\json-java>mvn compile
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------< com.syedm:json-java >-------------------------
[INFO] Building json-java 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ json-java ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory D:\repos\java\maven\json-java\json-java\src\main\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ json-java ---
[INFO] Nothing to compile - all classes are up to date
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.713 s
[INFO] Finished at: 2022-12-25T13:58:58-05:00
[INFO] ------------------------------------------------------------------------
```

## package


```
D:\repos\java\maven\json-java\json-java>mvn package
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------< com.syedm:json-java >-------------------------
[INFO] Building json-java 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ json-java ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory D:\repos\java\maven\json-java\json-java\src\main\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ json-java ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:3.0.2:testResources (default-testResources) @ json-java ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory D:\repos\java\maven\json-java\json-java\src\test\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ json-java ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ json-java ---
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.syedm.AppTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.005 s - in com.syedm.AppTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- maven-jar-plugin:3.0.2:jar (default-jar) @ json-java ---
[INFO] Building jar: D:\repos\java\maven\json-java\json-java\target\json-java-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.360 s
[INFO] Finished at: 2022-12-25T14:23:27-05:00
[INFO] ------------------------------------------------------------------------
```


## Run jar


