package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.OrderDetail;

@Repository
public class OrderDetailDao {

	private static final String SELECT_ALL = "select a.*,b.goods_name,b.goods_code from order_transaction_detail a, goods b where a.goods_id =  b.goods_id";
	private static final String SELECT_BY = SELECT_ALL + " and a.order_id = ? ";
	private static final String SELECT_BY_SINGLE = SELECT_BY + " and a.goods_id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(OrderDetail orderDetail) {
		jdbcTemplate.update(orderDetail.generateInsertQuery(), orderDetail.getOrderId(), orderDetail.getGoodsId(),
				orderDetail.getQuantity());
	}

	public void update(OrderDetail orderDetail) {
		jdbcTemplate.update(orderDetail.generateUpdateQuery(), orderDetail.getOrderId(), orderDetail.getGoodsId(),
				orderDetail.getQuantity(), orderDetail.getOrderId());
	}

	public void delete(OrderDetail orderDetail) {
		jdbcTemplate.update(orderDetail.generateDeleteQuery(), orderDetail.getOrderId());
	}

	public List<OrderDetail> findPerOrder(String orderId) {
		return jdbcTemplate.query(SELECT_BY, new Object[] { orderId }, OrderDetail.obtainRowMapper());
	}

	public OrderDetail findSingleItem(String orderId, String goodsId) {
		return jdbcTemplate.queryForObject(SELECT_BY_SINGLE, new Object[] { orderId, goodsId },
				OrderDetail.obtainRowMapper());
	}

}
