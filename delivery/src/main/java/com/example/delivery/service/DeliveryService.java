package com.example.delivery.service;

import com.example.delivery.model.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeliveryService {

    @KafkaListener(topics = "orders_server.orders_db.outbox", groupId = "consumerId")
    public void receive(KafkaMessage message) {
        log.info("The customer order is: {}",message);
    }
}
