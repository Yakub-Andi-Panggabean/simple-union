package com.polsri.union.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polsri.union.app.dto.OrderDto;
import com.polsri.union.app.service.OrderService;
import com.polsri.union.app.util.response.ErrorCategory;
import com.polsri.union.app.util.response.GenericMultipleResponse;
import com.polsri.union.app.util.response.GenericResponse;
import com.polsri.union.app.util.response.GenericSingleResponse;

@Controller
@RequestMapping(value = OrderController.BASE_PATH)
public class OrderController {

	public static final String BASE_PATH = "/api/transaction";
	private static final String PARAMETERIZED_PATH = "/{transaction_id:.+}";
	private static final String PARAMETERIZED_PATH_CODE = "/code";

	@Autowired
	private OrderService service;

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public GenericSingleResponse<String> orderTransaction(@Valid @RequestBody OrderDto orderDto) {
		Map<String, String> result = service.setTransaction(orderDto);
		boolean success = true;
		ErrorCategory errorCategory = null;
		String generatedId = result.get("generated_id");
		List<String> errorMessages = new ArrayList<String>();
		if (result.get("code").equals("-1")) {
			success = false;
			generatedId = null;
			errorMessages.add(result.get("message"));
			errorCategory = ErrorCategory.COMMUNICATION_FAILURE;
		}
		// return new GenericResponse(errorCategory, errorMessages, success);
		return new GenericSingleResponse<String>(generatedId, errorCategory, errorMessages, success);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public GenericResponse updateOrderTransaction(@Valid @RequestBody OrderDto orderDto) {
		Map<String, String> result = service.updateTransaction(orderDto);
		boolean success = true;
		ErrorCategory errorCategory = null;
		List<String> errorMessages = new ArrayList<String>();
		if (result.get("code").equals("-1")) {
			success = false;
			errorMessages.add(result.get("message"));
			errorCategory = ErrorCategory.COMMUNICATION_FAILURE;
		}
		return new GenericResponse(errorCategory, errorMessages, success);
	}

	@RequestMapping(value = PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public GenericResponse deleteOrderTransaction(@PathVariable("transaction_id") String orderId) {
		service.deleteTransaction(orderId);
		return new GenericResponse(null, null, true);
	}

	@RequestMapping(value = PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public GenericSingleResponse<OrderDto> findById(@PathVariable("transaction_id") String orderId) {
		return new GenericSingleResponse<OrderDto>(service.findOrder(orderId), null, null, true);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public GenericMultipleResponse<OrderDto> findAll() {
		return new GenericMultipleResponse<OrderDto>(null, null, true, service.findAllOrder(), null);
	}

	@RequestMapping(value = PARAMETERIZED_PATH_CODE, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public GenericSingleResponse<String> generateCode() {
		return new GenericSingleResponse<String>(service.generateCode(), null, null, true);
	}

}
