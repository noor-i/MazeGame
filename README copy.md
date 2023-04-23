# Design

Phase 1 documents

## Branches

 - main branch
 - dev branch
    -- to be used for all the initial files and updates.
    -- merge with master branch afterwards. 

## Contribution Guide

Adhere to the rules mentioned in the following page for commit messages: 
[Semantic Commit Messages](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)

## Automation for Artifacts and Tests using Maven
Make sure to have at least the following version or above:
Apache Maven 3.9.1 

Make sure to have the following version:
Java JDK version 11

### To run the tests:
Go to the source folder and run the following command
```mvn test```

### To build the artifacts - Jar file

Go to project folder and run the following command
```mvn clean install```

### To run the Jar file
Go to target folder inside project folder and run the following command
```java -jar group6_project-1.0-SNAPSHOT.jar```

### To create the Javadoc
Go to project folder and run the following command
```mvn javadoc:javadoc``` 
* Will be placed inside target/site/apidoc/