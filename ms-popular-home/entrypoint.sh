#!/usr/bin/env bash

chmod a+x mvnw

mkdir .m2

#./mvnw clean install -Dmaven.repo.local=/home/spring/ms-popular-home/.m2 -DskipTests

./mvnw spring-boot:run