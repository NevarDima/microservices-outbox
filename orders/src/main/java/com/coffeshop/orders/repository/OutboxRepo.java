package com.coffeshop.orders.repository;

import com.coffeshop.orders.model.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepo extends JpaRepository<Outbox, Long> {

}
