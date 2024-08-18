
Create project

```shell

D:\repos\java\maven>mvn archetype:generate -DgroupId=com.syedm.app -DartifactId=oauth-spring -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------< org.apache.maven:standalone-pom >-------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] --------------------------------[ pom ]---------------------------------
[INFO]
[INFO] >>> maven-archetype-plugin:3.2.0:generate (default-cli) > generate-sources @ standalone-pom >>>
[INFO]
[INFO] <<< maven-archetype-plugin:3.2.0:generate (default-cli) < generate-sources @ standalone-pom <<<
[INFO]
[INFO]
[INFO] --- maven-archetype-plugin:3.2.0:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Batch mode
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Archetype: maven-archetype-quickstart:1.4
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: com.syedm.app
[INFO] Parameter: artifactId, Value: oauth-spring
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Parameter: package, Value: com.syedm.app
[INFO] Parameter: packageInPathFormat, Value: com/syedm/app
[INFO] Parameter: package, Value: com.syedm.app
[INFO] Parameter: groupId, Value: com.syedm.app
[INFO] Parameter: artifactId, Value: oauth-spring
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[WARNING] The directory D:\repos\java\maven\oauth-spring already exists.
[INFO] Project created from Archetype in dir: D:\repos\java\maven\oauth-spring
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.927 s
[INFO] Finished at: 2024-02-06T16:57:52-05:00
[INFO] ------------------------------------------------------------------------
```

dependencies could not be found so had to 

a) add version to spring-boot-starter-web
b) run : mvn clean install -U
c) Retreive client id and client secret by going to developer settings->OAuthApps->New Oauth App
   callback url was : http://localhost:8080/login/oauth2/code/github
   app nam : ghub api test
d) client id : a54c44f1ac5fdbba103f
   client secret : 88278294588e9c6ab777210969e0065c0cb98039 