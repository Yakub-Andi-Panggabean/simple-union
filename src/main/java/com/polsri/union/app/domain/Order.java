package com.polsri.union.app.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class Order extends QueryDomainGeneratorBean {
	private String orderId;
	private String transactionNumber;
	private BigDecimal totalPayment;
	private Date transactionDateTime;
	private String user;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String orderId, String transactionNumber, BigDecimal totalPayment, Date transactionDateTime,
			String user) {
		super();
		this.orderId = orderId;
		this.transactionNumber = transactionNumber;
		this.totalPayment = totalPayment;
		this.transactionDateTime = transactionDateTime;
		this.user = user;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public BigDecimal getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(BigDecimal totalPayment) {
		this.totalPayment = totalPayment;
	}

	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", transactionNumber=" + transactionNumber + ", totalPayment="
				+ totalPayment + ", transactionDateTime=" + transactionDateTime + ", user=" + user + "]";
	}

	@Override
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return "order_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "order_transaction";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("orderId", "order_id");
		fields.put("transactionNumber", "transaction_number");
		fields.put("totalPayment", "total_payment");
		fields.put("transactionDateTime", "transaction_datetime");
		fields.put("user", "login_user");
		return fields;
	}

	public static final RowMapper<Order> obtainRowMapper() {
		return new RowMapper<Order>() {

			@Override
			public Order mapRow(ResultSet rs, int rowcount) throws SQLException {
				// TODO Auto-generated method stub
				return new Order(rs.getString("order_id"), rs.getString("transaction_number"),
						rs.getBigDecimal("total_payment"), rs.getDate("transaction_datetime"),
						rs.getString("login_user"));
			}
		};
	}

}
