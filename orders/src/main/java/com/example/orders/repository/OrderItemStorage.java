package com.example.orders.repository;

import com.example.orders.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemStorage extends CrudRepository<OrderItem, Long> {

}
