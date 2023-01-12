# springboot-board

### Environments
- Spring Boot 2.7.7
- JDK 11
- Spring JDBC
- MySQL
- Thymeleaf

### How to Run
1. write your own application.yml
    ```bash
    $ cd src/main/resources
    $ cp application.example.yml application.yml
    ```
2. gradle build and run
    ```bash
   $ ./gradlew build
   $ cd build/libs
   $ java -jar springboot-board-0.0.1-SNAPSHOT.jar
   ```

### Features
1. CREATE
   `/board/save`
2. READ
    - 글 목록 : `/board/`
    - 글 상세보기 : `/board/{id}`
3. UPDATE
   `/board/update/{id}`
4. DELETE
   `/board/delete/{id}`


### TODO
- mouseover preview