# INEOR ASSIGNMENT

#### Author

Rok Zidarn

#### Instructions

Expose REST API capable of printing out: three countries with the lowest, three countries with the highest population (for year 2018).
Keep it clean and simple (yet with a reasonable design allowing future extendibility). Use any library of your choice, optionally write unit tests. 
Implement as a Maven or Gradle project, using latest Spring Boot.

#### Comments
+ could solve with multiple classes and directly use RestTemplate (https://springframework.guru/using-resttemplate-in-spring/), but I would 
need even more classes

#### Solution

+ run src/main/java/com/ineor/assignment/AssignmentApplication.java
+ exposed endpoint: /result
+ run mvn test