#!/bin/sh

kill -9 `pidof java`
git pull origin 
./gradlew clean build
nohup java -jar build/libs/fullbang-0.0.1-SNAPSHOT.jar &