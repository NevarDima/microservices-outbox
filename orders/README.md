Transactional Outbox design pattern in Spring Boot Microservices.

This project consists of 2 projects, orders and delivery services.

**! To build this project you need to have docker installed in your OS !**

How to build:

- Orders-service

1) maven install
2) docker compose
3) curl --location --request POST 'http://localhost:8083/connectors' \
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
- Delivery-service
1) maven install
2) docker compose