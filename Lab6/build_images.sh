#!/bin/bash

docker build -t lab6-books-microservice:lab7 ./Library_Books
docker build -t lab6-genres-microservice:lab7 ./Library_Genres
docker build -t lab6-gateway:lab7 ./Library_Gateway
docker build -t lab6-angular-app:lab7 ./library-angular-app