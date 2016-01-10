package com.polsri.union.app.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class OrderDetail extends QueryDomainGeneratorBean {
	private String orderId;
	private String goodsId;
	private BigDecimal quantity;

	// private join column
	private String goodsName;
	private String goodsCode;

	public OrderDetail(String orderId, String goodsId, BigDecimal quantity) {
		super();
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.quantity = quantity;
	}

	public OrderDetail(String orderId, String goodsId, BigDecimal quantity, String goodsName, String goodsCode) {
		super();
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.quantity = quantity;
		this.goodsName = goodsName;
		this.goodsCode = goodsCode;
	}

	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	@Override
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return "order_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "order_transaction_detail";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("orderId", "order_id");
		fields.put("goodsId", "goods_id");
		fields.put("quantity", "quantity");
		return fields;
	}

	public static final RowMapper<OrderDetail> obtainRowMapper() {
		return new RowMapper<OrderDetail>() {

			@Override
			public OrderDetail mapRow(ResultSet rs, int rowcount) throws SQLException {
				// TODO Auto-generated method stub
				return new OrderDetail(rs.getString("order_id"), rs.getString("goods_id"), rs.getBigDecimal("quantity"),
						rs.getString("goods_name"), rs.getString("goods_code"));
			}

		};
	}

}
