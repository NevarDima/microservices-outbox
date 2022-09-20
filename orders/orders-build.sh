#!/bin/bash

echo --- Up mysql database in docker container ---
docker-compose up mysql-service -d

echo --- Up zookeeper service in docker container ---
docker-compose up zookeeper-service -d

echo --- Up kafka service in docker container ---
docker-compose up kafka-service -d

echo --- Up connect service in docker container ---
docker-compose up connect-service -d

echo --- Mvn clean orders-service ---
mvn clean

echo --- Mvn install orders-service ---
mvn install

echo --- Up orders service in docker container ---
docker-compose up orders-service -d

echo --- Activate debezium connector ---
curl --location --request POST 'http://localhost:8083/connectors' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "outbox-connector",
    "config": {
        "connector.class": "io.debezium.connector.mysql.MySqlConnector",
        "tasks.max": "1",
        "database.hostname": "mysql-service",
        "database.port": "3306",
        "database.user": "root",
        "database.password": "root",
        "database.name": "orders_db",
        "database.server.name": "orders_server",
        "table.include.list":"orders_db.outbox",
        "database.history.kafka.bootstrap.servers": "kafka-service:9092",
        "database.history.kafka.topic": "schema-changes.outbox",
        "transforms": "unwrap",
        "transforms.unwrap.type": "io.debezium.transforms.ExtractNewRecordState"
    }
}'