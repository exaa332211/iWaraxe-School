package com.example.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

	@Id
	private Long id;

	private String name;

	private Integer amount;

	private Double price;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private UserOrder order;
}
