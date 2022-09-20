create table orders_db.outbox
(
    id         int AUTO_INCREMENT primary key,
    event      varchar(1000),
    event_id   int,
    payload    json,
    created_at timestamp
);

create table orders_db.customer_order
(
    id       int AUTO_INCREMENT primary key,
    name     varchar(1000),
    quantity int
);