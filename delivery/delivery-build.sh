#!/bin/bash

echo --- Mvn clean delivery-service ---
mvn clean

echo --- Mvn install delivery-service ---
mvn install

echo --- Docker compose delivery-service ---
docker-compose up -d