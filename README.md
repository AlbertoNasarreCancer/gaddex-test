# gaddex-test

Steps to execute the docker

1. first or all create a build of the application

./gradlew build -x test

2. create image

docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/gs-spring-boot-gaddex .

3. execute it

docker run -p 8080:8080 springio/gs-spring-boot-gaddex
