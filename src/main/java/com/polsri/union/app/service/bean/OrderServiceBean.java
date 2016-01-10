package com.polsri.union.app.service.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.polsri.union.app.dao.OrderDao;
import com.polsri.union.app.dao.OrderDetailDao;
import com.polsri.union.app.dao.StockDao;
import com.polsri.union.app.domain.Order;
import com.polsri.union.app.domain.OrderDetail;
import com.polsri.union.app.domain.Stock;
import com.polsri.union.app.dto.OrderDto;
import com.polsri.union.app.service.OrderService;
import com.polsri.union.app.util.core.UUIDKeyProcessor;
import com.polsri.union.app.util.response.ErrorCategory;

@Service
@Transactional(readOnly = true)
public class OrderServiceBean implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderDetailDao orderDetailDao;
	@Autowired
	private StockDao stockDao;

	@Override
	@Transactional(readOnly = false)
	public Map<String, String> setTransaction(OrderDto orderDto) {
		// TODO Auto-generated method stub
		Map<String, String> hash = new HashMap<String, String>();
		try {
			String orderId = UUIDKeyProcessor.generateUUID();
			String transactionNumber = orderDao.generateKey();
			BigDecimal stockValue = null;
			Stock stock = null;
			// inserting master transaction
			orderDao.insert(new Order(orderId, transactionNumber, orderDto.getTotalPayment(),
					orderDto.getTransactionDateTime(), orderDto.getUser()));
			// inserting detail transaction
			if (!CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
				for (OrderDetail detail : orderDto.getOrderDetails()) {
					stock = stockDao.findStockById(detail.getGoodsId());
					if (stock != null) {
						stockValue = stock.getQuantity();
						BigDecimal finalStock = stockValue.subtract(detail.getQuantity());
						if (finalStock.compareTo(BigDecimal.ZERO) == -1) {
							System.out.println("quantity :" + detail.getQuantity());
							System.out.println("stock :" + stock);
						} else {
							detail.setOrderId(orderId);
							orderDetailDao.insert(detail);
							stock.setQuantity(finalStock);
							stockDao.update(stock);
						}
					}
				}
			}
			// generate key
			orderDao.insertKeySequence();
			hash.put("code", "1");
			hash.put("generated_id", orderId);
			hash.put("message", "transaction_done");
		} catch (Exception ex) {
			ex.printStackTrace();
			hash.put("exception", ErrorCategory.VALIDATION_ERROR.toString());
			hash.put("message", ex.getMessage());
			hash.put("code", "-1");

		}
		return hash;
	}

	@Override
	@Transactional(readOnly = false)
	public Map<String, String> updateTransaction(OrderDto orderDto) {
		// TODO Auto-generated method stub
		Map<String, String> hash = new HashMap<String, String>();
		try {
			Stock stock = null;
			BigDecimal stockValue = null;
			// update order
			orderDao.update(new Order(orderDto.getOrderId(), orderDto.getTransactionNumber(),
					orderDto.getTotalPayment(), orderDto.getTransactionDateTime(), orderDto.getUser()));
			// delete all order detail based on order id
			orderDetailDao.delete(new OrderDetail(orderDto.getOrderId(), null, null));
			// looping and inserting new detail
			// inserting detail transaction
			if (!CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
				for (OrderDetail detail : orderDto.getOrderDetails()) {
					stock = stockDao.findStockById(detail.getGoodsId());
					if (stock != null) {
						stockValue = stock.getQuantity();
						BigDecimal finalStock = stockValue.subtract(detail.getQuantity());
						if (finalStock.compareTo(BigDecimal.ZERO) == -1) {
							System.out.println("quantity :" + detail.getQuantity());
							System.out.println("stock :" + stock);
						} else {
							detail.setOrderId(orderDto.getOrderId());
							orderDetailDao.insert(detail);
							stock.setQuantity(finalStock);
							System.out.println(stock.toString());
							stockDao.update(stock);
						}
					}
				}
			}
			hash.put("code", "1");
			hash.put("message", "transaction_done");
		} catch (Exception ex) {
			if (ex.getMessage().equals("0012")) {// out of stock
				hash.put("exception", ErrorCategory.VALIDATION_ERROR.toString());
				hash.put("message", "out of stock");
			} else {
				hash.put("exception", ex.getClass().getName());
				hash.put("message", ex.getMessage());
			}
			hash.put("code", "-1");

		}
		return hash;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteTransaction(String orderId) {
		// TODO Auto-generated method stub
		orderDao.delete(new Order(orderId, null, null, null, null));
		orderDetailDao.delete(new OrderDetail(orderId, null, null));
	}

	@Override
	public OrderDto findOrder(String orderId) {
		// TODO Auto-generated method stub
		Order order = orderDao.findById(orderId);
		List<OrderDetail> orderDetails = orderDetailDao.findPerOrder(orderId);
		return new OrderDto(order.getOrderId(), order.getTransactionNumber(), order.getTotalPayment(),
				order.getTransactionDateTime(), order.getUser(), orderDetails);
	}

	@Override
	public List<OrderDetail> findChildDetail(String orderId) {
		// TODO Auto-generated method stub
		return orderDetailDao.findPerOrder(orderId);
	}

	@Override
	public Order findOrderParent(String orderId) {
		// TODO Auto-generated method stub
		return orderDao.findById(orderId);
	}

	@Override
	public List<OrderDto> findAllOrder() {
		// TODO Auto-generated method stub
		OrderDto dto = null;
		List<Order> orders = orderDao.findAll();
		List<OrderDto> list = new ArrayList<OrderDto>();
		for (Order order : orders) {
			dto = new OrderDto();
			List<OrderDetail> orderDetails = orderDetailDao.findPerOrder(order.getOrderId());
			dto.setOrderId(order.getOrderId());
			dto.setTotalPayment(order.getTotalPayment());
			dto.setTransactionDateTime(dto.getTransactionDateTime());
			dto.setTransactionNumber(order.getTransactionNumber());
			dto.setUser(order.getUser());
			dto.setOrderDetails(orderDetails);
			list.add(dto);
		}
		return list;
	}

	@Override
	public String generateCode() {
		// TODO Auto-generated method stub
		return orderDao.generateKey();
	}

}
