package com.example.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.List;

@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrder {

	@Id
	private Long id;

	private Long userId;

	private String status;

	private Date deliveryDate;

	private String address;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;
}
