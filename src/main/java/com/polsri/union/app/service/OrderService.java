package com.polsri.union.app.service;

import java.util.List;
import java.util.Map;

import com.polsri.union.app.domain.Order;
import com.polsri.union.app.domain.OrderDetail;
import com.polsri.union.app.dto.OrderDto;

public interface OrderService {

	Map<String, String> setTransaction(OrderDto orderDto);

	Map<String, String> updateTransaction(OrderDto orderDto);

	void deleteTransaction(String orderId);

	OrderDto findOrder(String orderId);

	List<OrderDto> findAllOrder();

	List<OrderDetail> findChildDetail(String orderId);

	Order findOrderParent(String orderId);

	String generateCode();
}
