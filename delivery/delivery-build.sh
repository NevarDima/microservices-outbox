#!/bin/bash

echo --- Mvn clean install delivery-service ---
mvn clean install

echo --- Docker compose delivery-service ---
docker-compose up -d