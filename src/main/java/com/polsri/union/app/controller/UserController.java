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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polsri.union.app.domain.User;
import com.polsri.union.app.dto.UserDto;
import com.polsri.union.app.service.UserService;
import com.polsri.union.app.util.response.ErrorCategory;
import com.polsri.union.app.util.response.GenericMultipleResponse;
import com.polsri.union.app.util.response.GenericResponse;
import com.polsri.union.app.util.response.GenericSingleResponse;
import com.polsri.union.app.util.response.PageInformation;

/**
 * 
 * @author yakub andi panggabean
 * @since 2015/11/06
 */

@Controller
@RequestMapping(value = UserController.BASE_PATH)
public class UserController {
	public static final String BASE_PATH = "/api/user";
	private static final String PARAMETERIZED_PATH = "/{username:.+}";

	@Autowired
	private UserService service;

	/**
	 * @function deleting user by id
	 * @param username
	 * @return json
	 */
	@RequestMapping(value = PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> deleteUser(@PathVariable("username") String userid) {
		GenericResponse response = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			service.delete(userid);
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

	/**
	 * @function inserting new user
	 * @param dto
	 * @param errors
	 * @return json
	 */
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> addNewUser(@Valid @RequestBody UserDto dto, Errors errors) {
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
				User trueUser = new User();
				BeanUtils.copyProperties(dto, trueUser);
				service.insert(trueUser);
				httpStatus = HttpStatus.OK;
			} catch (Exception ex) {
				errorMessages.add(ex.getCause().getMessage());
				success = false;
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorCategory = ErrorCategory.UNSPECIFIED_ERROR;
			}
		}
		response = new GenericResponse(errorCategory, errorMessages, success);
		return new ResponseEntity<GenericResponse>(response, httpStatus);
	}

	/**
	 * @function updating user
	 * @param dto
	 * @param errors
	 * @return json
	 */
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> updateUser(@Valid @RequestBody UserDto dto, Errors errors) {
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
				User trueUser = new User();
				BeanUtils.copyProperties(dto, trueUser);
				service.update(trueUser);
				httpStatus = HttpStatus.OK;
			} catch (Exception ex) {
				errorMessages.add(ex.getCause().getMessage());
				success = false;
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorCategory = ErrorCategory.UNSPECIFIED_ERROR;
			}
		}
		response = new GenericResponse(errorCategory, errorMessages, success);
		return new ResponseEntity<GenericResponse>(response, httpStatus);
	}

	/**
	 * @function retrieving by username
	 * @param username
	 * @return json
	 */
	@RequestMapping(value = PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericSingleResponse<UserDto>> findUserByUserName(
			@PathVariable("username") String username) {
		UserDto userDto = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			User user = service.findByUserName(username);
			userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
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
		GenericSingleResponse<UserDto> response = new GenericSingleResponse<UserDto>(userDto, errorCategory,
				errorMessages, success);
		return new ResponseEntity<GenericSingleResponse<UserDto>>(response, httpStatus);
	}

	/**
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericMultipleResponse<UserDto>> findAllUser(
			@RequestParam(value = "start", defaultValue = "0") int start,
			@RequestParam(value = "length", defaultValue = "20") int limit) {
		List<UserDto> list = new ArrayList<UserDto>();
		UserDto userDto = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			int startingPage = start - 1;// the start of pagination which is 0
											// to 1
			List<User> users = service.findAllUser(startingPage, limit);
			for (User user : users) {
				userDto = new UserDto();
				BeanUtils.copyProperties(user, userDto);
				list.add(userDto);
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
		GenericMultipleResponse<UserDto> response = new GenericMultipleResponse<UserDto>(errorCategory, errorMessages,
				success, list, new PageInformation(start, limit, service.countTotalUser()));
		return new ResponseEntity<GenericMultipleResponse<UserDto>>(response, httpStatus);
	}

}
