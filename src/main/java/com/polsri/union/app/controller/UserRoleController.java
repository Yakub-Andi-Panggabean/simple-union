package com.polsri.union.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polsri.union.app.domain.Role;
import com.polsri.union.app.domain.User;
import com.polsri.union.app.domain.UserRole;
import com.polsri.union.app.dto.RoleDto;
import com.polsri.union.app.dto.UserDto;
import com.polsri.union.app.service.UserRoleService;
import com.polsri.union.app.util.response.ErrorCategory;
import com.polsri.union.app.util.response.GenericMultipleResponse;
import com.polsri.union.app.util.response.GenericResponse;
import com.polsri.union.app.util.response.GenericSingleResponse;

@Controller
@RequestMapping(value = UserRoleController.BASE_PATH)
public class UserRoleController {

	public static final String BASE_PATH = "/api/userrole";
	private static final String PARAMETERIZED_PATH = "/{id}";
	private static final String USER_PARAMETERIZED_PATH = "/user/{id}";
	private static final String ROLE_PARAMETERIZED_PATH = "/role/{id}";

	@Autowired
	private UserRoleService service;

	@RequestMapping(value = PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> divorceUserRole(@PathVariable("id") String param) {
		GenericResponse response = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			service.divorceUserRole(param);
			httpStatus = HttpStatus.OK;
		} catch (Exception ex) {
			ex.printStackTrace();
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
	public ResponseEntity<GenericResponse> uniteUserRole(@RequestBody UserRole userRole) {
		GenericResponse response = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			service.uniteUserRole(userRole);
			httpStatus = HttpStatus.OK;
		} catch (Exception ex) {
			ex.printStackTrace();
			errorMessages.add(ex.getCause().getMessage());
			success = false;
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			errorCategory = ErrorCategory.UNSPECIFIED_ERROR;
		}
		response = new GenericResponse(errorCategory, errorMessages, success);
		return new ResponseEntity<GenericResponse>(response, httpStatus);
	}

	@RequestMapping(value = USER_PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericMultipleResponse<UserDto>> findUserByRole(@PathVariable("id") String roleId) {
		List<UserDto> list = new ArrayList<UserDto>();
		UserDto userDto = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			List<User> users = service.findUsersByRole(roleId);
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
				success, list, null);
		return new ResponseEntity<GenericMultipleResponse<UserDto>>(response, httpStatus);
	}

	@RequestMapping(value = ROLE_PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericSingleResponse<RoleDto>> findRoleByUser(@PathVariable("id") String userId) {
		RoleDto roleDto = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			Role role = service.findRoleByUser(userId);
			roleDto = new RoleDto();
			BeanUtils.copyProperties(role, roleDto);
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
		GenericSingleResponse<RoleDto> response = new GenericSingleResponse<RoleDto>(roleDto, errorCategory,
				errorMessages, success);
		return new ResponseEntity<GenericSingleResponse<RoleDto>>(response, httpStatus);
	}

}
