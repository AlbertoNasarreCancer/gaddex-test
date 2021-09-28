# gaddex-test

Steps to execute the docker

1. create image

docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/gs-spring-boot-gaddex .

2. execute it

docker run -p 8080:8080 springio/gs-spring-boot-gaddex
