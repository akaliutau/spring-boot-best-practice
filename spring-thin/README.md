
About 
=======

A minimal spring boot project utilizing spring thin technology (https://github.com/spring-projects-experimental/spring-boot-thin-launcher)
Can be useful in scenarios when there is a need to run multiple jars in the same time or to decrease the run time in distributed environment (by limiting the size of jar to transfer over the network)


Overview
==========

First of all it's necessary to build the whole project if the module which is implied to run has references (f.e. reference to the parent pom).

To run slim jar use the following command:

```
java -Dthin.root=. -jar .\target\spring-min-app-1.0.0-SNAPSHOT.jar
```

As a result the launcher downloads all dependencies into local ./repository directory (affects only the fresh run).

Note the final size of jar file - it's only 10k (vs 8 MB for uber jar)

