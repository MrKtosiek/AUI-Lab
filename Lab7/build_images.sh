#!/bin/bash

docker build -t lab7-books-microservice:latest ./Library_Books
docker build -t lab7-genres-microservice:latest ./Library_Genres
docker build -t lab7-gateway:latest ./Library_Gateway
docker build -t lab7-angular-app:latest ./library-angular-app
docker build -t lab7-eurekaserver:latest ./Discovery_Service