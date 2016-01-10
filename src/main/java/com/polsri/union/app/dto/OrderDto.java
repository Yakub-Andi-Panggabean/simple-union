package com.polsri.union.app.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.polsri.union.app.domain.OrderDetail;

public class OrderDto {
	private String orderId;
	private String transactionNumber;
	private BigDecimal totalPayment;
	private Date transactionDateTime;
	private String user;
	private List<OrderDetail> orderDetails;

	public OrderDto(String orderId, String transactionNumber, BigDecimal totalPayment, Date transactionDateTime,
			String user, List<OrderDetail> orderDetails) {
		super();
		this.orderId = orderId;
		this.transactionNumber = transactionNumber;
		this.totalPayment = totalPayment;
		this.transactionDateTime = transactionDateTime;
		this.user = user;
		this.orderDetails = orderDetails;
	}

	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "OrderDto [orderId=" + orderId + ", transactionNumber=" + transactionNumber + ", totalPayment="
				+ totalPayment + ", transactionDateTime=" + transactionDateTime + ", user=" + user + ", orderDetails="
				+ orderDetails + "]";
	}

}
