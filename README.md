# gaddex-test

Steps to execute the docker

1. first or all create a build of the application

./gradlew build -x test

2. create image

docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/gs-spring-boot-gaddex .

3. execute it

docker run -p 8080:8080 springio/gs-spring-boot-gaddex


-----
Endpoints:
-
- GET
(for get all)
http://localhost:8080/api/events/
(for get just one)
http://localhost:8080/api/events/{id}
- POST
http://localhost:8080/api/events/
- PUT
http://localhost:8080/api/events/{id}
- DELETE
http://localhost:8080/api/events/{id}

Example of json to post:

    {
        "Description": "come to the Razz Party",
        "Title": "Razz Party",
        "Location": [2.456456,1.45645646],
        "Date": "2021-01-01",
        "Image": "AA=="

    }
