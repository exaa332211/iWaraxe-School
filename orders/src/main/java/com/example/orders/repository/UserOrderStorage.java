package com.example.orders.repository;

import com.example.orders.model.UserOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserOrderStorage extends CrudRepository<UserOrder, Long> {

	List<UserOrder> getAllByUserId(Long userId);
}
