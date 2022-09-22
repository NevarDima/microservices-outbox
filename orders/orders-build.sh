#!/bin/bash

echo --- Mvn clean install orders-service ---
mvn clean install

echo --- Up all services in docker container ---
docker-compose up -d

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