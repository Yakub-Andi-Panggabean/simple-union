package com.polsri.union.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polsri.union.app.domain.Stock;
import com.polsri.union.app.dto.StockDto;
import com.polsri.union.app.service.StockService;
import com.polsri.union.app.util.response.ErrorCategory;
import com.polsri.union.app.util.response.GenericMultipleResponse;
import com.polsri.union.app.util.response.GenericResponse;
import com.polsri.union.app.util.response.GenericSingleResponse;

@Controller
@RequestMapping(value = StockController.BASE_PATH)
public class StockController {
	public static final String BASE_PATH = "/api/stock";
	private static final String PARAMETERIZED_PATH = "/{goodsId:.+}";

	@Autowired
	private StockService service;

	@RequestMapping(value = PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> deleteStock(@PathVariable("goodsId") String stockId) {
		GenericResponse response = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			service.delete(stockId);
			httpStatus = HttpStatus.OK;
		} catch (Exception ex) {
			errorMessages.add(ex.getCause().getMessage());
			success = false;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			errorCategory = ErrorCategory.UNSPECIFIED_ERROR;
		}
		response = new GenericResponse(errorCategory, errorMessages, success);
		return new ResponseEntity<GenericResponse>(response, httpStatus);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> addNewStock(@Valid @RequestBody StockDto dto, Errors errors) {
		GenericResponse response = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		if (errors.hasErrors()) {
			for (int i = 0; i < errors.getAllErrors().size(); i++) {
				errorMessages.add(errors.getFieldErrors().get(i).getField() + " "
						+ errors.getAllErrors().get(i).getDefaultMessage());
			}
			success = false;
			errorCategory = ErrorCategory.VALIDATION_ERROR;
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
		} else {
			try {
				Stock stock = new Stock();
				BeanUtils.copyProperties(dto, stock);
				service.insert(stock);
				httpStatus = HttpStatus.OK;
			} catch (Exception ex) {
				ex.printStackTrace();
				errorMessages.add(ex.getMessage());
				success = false;
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorCategory = ErrorCategory.UNSPECIFIED_ERROR;
			}
		}
		response = new GenericResponse(errorCategory, errorMessages, success);
		return new ResponseEntity<GenericResponse>(response, httpStatus);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> updateStock(@Valid @RequestBody StockDto dto, Errors errors) {
		GenericResponse response = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		if (errors.hasErrors()) {
			for (int i = 0; i < errors.getAllErrors().size(); i++) {
				errorMessages.add(errors.getFieldErrors().get(i).getField() + " "
						+ errors.getAllErrors().get(i).getDefaultMessage());
			}
			success = false;
			errorCategory = ErrorCategory.VALIDATION_ERROR;
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
		} else {
			try {
				Stock stock = new Stock();
				BeanUtils.copyProperties(dto, stock);
				service.update(stock);
				httpStatus = HttpStatus.OK;
			} catch (Exception ex) {
				errorMessages.add(ex.getMessage());
				success = false;
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorCategory = ErrorCategory.UNSPECIFIED_ERROR;
			}
		}
		response = new GenericResponse(errorCategory, errorMessages, success);
		return new ResponseEntity<GenericResponse>(response, httpStatus);
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericMultipleResponse<StockDto>> findAllStocks() {
		List<StockDto> list = new ArrayList<StockDto>();
		StockDto dto = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			List<Stock> stocks = service.findAll();
			for (Stock stock : stocks) {
				dto = new StockDto();
				BeanUtils.copyProperties(stock, dto);
				list.add(dto);
			}
			httpStatus = HttpStatus.OK;
		} catch (Exception ex) {
			if (ex instanceof NullPointerException) {
				httpStatus = HttpStatus.NOT_FOUND;
				errorCategory = ErrorCategory.VALIDATION_ERROR;
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorCategory = ErrorCategory.UNSPECIFIED_ERROR;
			}
			errorMessages.add(ex.getMessage());
			success = false;
		}
		GenericMultipleResponse<StockDto> response = new GenericMultipleResponse<StockDto>(errorCategory, errorMessages,
				success, list, null);
		return new ResponseEntity<GenericMultipleResponse<StockDto>>(response, httpStatus);
	}

	@RequestMapping(value = PARAMETERIZED_PATH, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericSingleResponse<StockDto>> findStockById(@PathVariable("goodsId") String stockId) {
		StockDto dto = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			Stock stock = service.findById(stockId);
			System.out.println(stock.toString());
			dto = new StockDto();
			BeanUtils.copyProperties(stock, dto);
			httpStatus = HttpStatus.OK;
		} catch (Exception ex) {
			if (ex instanceof NullPointerException) {
				httpStatus = HttpStatus.NOT_FOUND;
				errorCategory = ErrorCategory.VALIDATION_ERROR;
			} else {
				if (ex instanceof EmptyResultDataAccessException) {
					httpStatus = HttpStatus.NOT_FOUND;
					errorCategory = ErrorCategory.DATA_NOT_FOUND;
				} else {
					httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
					errorCategory = ErrorCategory.UNSPECIFIED_ERROR;
				}
			}
			errorMessages.add(ex.getMessage());
			success = false;
		}
		GenericSingleResponse<StockDto> response = new GenericSingleResponse<StockDto>(dto, errorCategory,
				errorMessages, success);
		return new ResponseEntity<GenericSingleResponse<StockDto>>(response, httpStatus);
	}

}
