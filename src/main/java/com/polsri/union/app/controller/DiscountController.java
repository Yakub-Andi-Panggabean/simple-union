package com.polsri.union.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.polsri.union.app.domain.Discount;
import com.polsri.union.app.dto.DiscountDto;
import com.polsri.union.app.service.DiscountService;
import com.polsri.union.app.util.response.ErrorCategory;
import com.polsri.union.app.util.response.GenericMultipleResponse;
import com.polsri.union.app.util.response.GenericResponse;
import com.polsri.union.app.util.response.GenericSingleResponse;

@Controller
@RequestMapping(value = DiscountController.BASE_PATH)
public class DiscountController {

	public static final String BASE_PATH = "/api/discount";
	private static final String PARAMETERIZED_PATH = "/{goodsId:.+}";

	@Autowired
	private DiscountService service;

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> addNewDiscount(@Valid @RequestBody DiscountDto dto, Errors errors) {
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
				Discount discount = new Discount();
				BeanUtils.copyProperties(dto, discount);
				service.insert(discount);
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
	public ResponseEntity<GenericResponse> updateDiscount(@Valid @RequestBody DiscountDto dto, Errors errors) {
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
				Discount discount = new Discount();
				BeanUtils.copyProperties(dto, discount);
				service.update(discount);
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

	@RequestMapping(value = PARAMETERIZED_PATH, method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> deleteDiscount(@PathVariable("goodsId") String goodsId) {
		GenericResponse response = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			service.delete(goodsId);
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

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericMultipleResponse<DiscountDto>> findAllDiscounts() {
		List<DiscountDto> list = new ArrayList<DiscountDto>();
		DiscountDto dto = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			List<Discount> discounts = service.findAll();
			for (Discount discount : discounts) {
				dto = new DiscountDto();
				BeanUtils.copyProperties(discount, dto);
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
		GenericMultipleResponse<DiscountDto> response = new GenericMultipleResponse<DiscountDto>(errorCategory,
				errorMessages, success, list, null);
		return new ResponseEntity<GenericMultipleResponse<DiscountDto>>(response, httpStatus);
	}

	@RequestMapping(value = PARAMETERIZED_PATH, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericSingleResponse<DiscountDto>> findById(@PathVariable("goodsId") String goodsId) {
		DiscountDto dto = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			Discount discount = service.findById(goodsId);
			dto = new DiscountDto();
			BeanUtils.copyProperties(discount, dto);
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
		GenericSingleResponse<DiscountDto> response = new GenericSingleResponse<DiscountDto>(dto, errorCategory,
				errorMessages, success);
		return new ResponseEntity<GenericSingleResponse<DiscountDto>>(response, httpStatus);
	}

}
