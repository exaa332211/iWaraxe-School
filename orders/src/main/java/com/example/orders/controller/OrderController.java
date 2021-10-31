package com.example.orders.controller;

import com.example.orders.model.UserOrder;
import com.example.orders.service.UserOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

	private final UserOrderService service;

	@GetMapping("/user/{id}")
	public List<UserOrder> loadOrdersByUser(@PathVariable Long id) {
		return service.loadAllByUserId(id);
	}

	@PostMapping("/create")
	public UserOrder createUserOrder(@RequestBody UserOrder order) {
		return service.placeOrder(order);
	}
}
