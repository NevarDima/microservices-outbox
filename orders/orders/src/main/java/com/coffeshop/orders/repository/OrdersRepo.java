package com.coffeshop.orders.repository;

import com.coffeshop.orders.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepo extends JpaRepository<CustomerOrder, Long> {

}
