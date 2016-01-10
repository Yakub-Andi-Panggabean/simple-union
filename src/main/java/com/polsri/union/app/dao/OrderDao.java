package com.polsri.union.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Order;

@Repository
public class OrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_KEY_SEQUENCE = "insert into increment_key(date_key) values(?)";
	private static final String GENERATE_KEY = "select generate_unique_key()";

	private static final String SELECT_ORDER_BY = new Order().generateSelectAllQuery(false)
			+ " where order_id = ? or transaction_number = ?";

	public void insert(Order order) {
		jdbcTemplate.update(order.generateInsertQuery(), order.getOrderId(), order.getTransactionNumber(),
				order.getTotalPayment(), order.getTransactionDateTime(), order.getUser());
	}

	public void update(Order order) {
		jdbcTemplate.update(order.generateUpdateQuery(), order.getOrderId(), order.getTransactionNumber(),
				order.getTotalPayment(), order.getTransactionDateTime(), order.getUser(), order.getOrderId());
	}

	public void delete(Order order) {
		jdbcTemplate.update(order.generateDeleteQuery(), order.getOrderId());
	}

	public Order findById(String orderId) {
		return jdbcTemplate.queryForObject(SELECT_ORDER_BY, new Object[] { orderId, orderId }, Order.obtainRowMapper());
	}

	public List<Order> findAll() {
		return jdbcTemplate.query(new Order().generateSelectAllQuery(false), Order.obtainRowMapper());
	}

	public void insertKeySequence() {
		jdbcTemplate.update(INSERT_KEY_SEQUENCE, new Date());
	}

	public String generateKey() {
		return jdbcTemplate.queryForObject(GENERATE_KEY, String.class);
	}

}
