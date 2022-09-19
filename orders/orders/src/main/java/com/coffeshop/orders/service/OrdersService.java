package com.coffeshop.orders.service;

import com.coffeshop.orders.model.CustomerOrder;
import com.coffeshop.orders.model.Outbox;
import com.coffeshop.orders.repository.OrdersRepo;
import com.coffeshop.orders.repository.OutboxRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepo repo;
    private final OutboxRepo outboxRepo;

    @Transactional
    public Map<String,Object> createOrder(Map<String,Object> orderMap) {

        CustomerOrder order = new CustomerOrder();
        order.setName(orderMap.get("name").toString());
        order.setQuantity(Integer.parseInt(String.valueOf(orderMap.get("quantity"))));
        repo.save(order);
        log.info("Order with id '{}' was saved to DB", order.getId());

        Outbox outbox = new Outbox();
        outbox.setEvent("order_created");
        outbox.setEventId(order.getId());
        outbox.setPayload(orderMap);
        outbox.setCreatedAt(LocalDateTime.now());
        outboxRepo.save(outbox);
        log.info("Outbox '{}' was saved to DB", outbox);
        outboxRepo.delete(outbox);
        log.info("Outbox '{}' was deleted from DB", outbox);

        return Map.of("orderId",order.getId());

    }
}
