package com.example.orders.service;

import com.example.orders.model.UserOrder;
import com.example.orders.repository.UserOrderStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserOrderService {

	private final UserOrderStorage storage;

	public UserOrder placeOrder(UserOrder order) {
		validate(order);
		return storage.save(order);
	}

	public List<UserOrder> loadAllByUserId(Long userId) {
		return storage.getAllByUserId(userId);
	}

	private void validate(UserOrder order) {
		// validation logic
	}
}
