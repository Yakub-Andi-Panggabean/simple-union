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

import com.polsri.union.app.domain.Role;
import com.polsri.union.app.dto.RoleDto;
import com.polsri.union.app.service.RoleService;
import com.polsri.union.app.util.response.ErrorCategory;
import com.polsri.union.app.util.response.GenericMultipleResponse;
import com.polsri.union.app.util.response.GenericResponse;
import com.polsri.union.app.util.response.GenericSingleResponse;
import com.polsri.union.app.util.response.PageInformation;

@Controller
@RequestMapping(value = RoleController.BASE_PATH)
public class RoleController {

	public static final String BASE_PATH = "/api/role";
	public static final String PARAMETERIZED_PATH = "/{roleId}";

	@Autowired
	private RoleService service;

	/**
	 * @function insert new role
	 * @param dto
	 * @param errors
	 * @return json
	 */
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> addNewRole(@Valid @RequestBody RoleDto dto, Errors errors) {
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
				Role role = new Role();
				BeanUtils.copyProperties(dto, role);
				service.insertRole(role);
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
	 * @function updating existing role
	 * @param dto
	 * @param errors
	 * @return
	 */
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> updateRole(@Valid @RequestBody RoleDto dto, Errors errors) {
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
				Role role = new Role();
				BeanUtils.copyProperties(dto, role);
				service.updateRole(role);
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
	 * @function deleting role
	 * @param roleId
	 * @return json
	 */
	@RequestMapping(value = PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> deleteUser(@PathVariable("roleId") String roleId) {
		GenericResponse response = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			service.deleteRole(roleId);
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
	 * @function retrieve role by role id
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericSingleResponse<RoleDto>> findRoleById(@PathVariable("roleId") String roleId) {
		RoleDto roleDto = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			Role role = service.findRoleById(roleId);
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

	/**
	 * @function retrieve all roles
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericMultipleResponse<RoleDto>> findAllRoles(
			@RequestParam(value = "start", defaultValue = "0") int start,
			@RequestParam(value = "length", defaultValue = "20") int limit) {
		List<RoleDto> list = new ArrayList<RoleDto>();
		RoleDto roleDto = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			int startingPage = start - 1;// the start of pagination which is 0
			if (startingPage < 0) {
				startingPage = 0;
			}
			List<Role> roles = service.findAllRoles(startingPage, limit);
			for (Role role : roles) {
				roleDto = new RoleDto();
				BeanUtils.copyProperties(role, roleDto);
				list.add(roleDto);
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
		GenericMultipleResponse<RoleDto> response = new GenericMultipleResponse<RoleDto>(errorCategory, errorMessages,
				success, list, new PageInformation(start, limit, service.countRoles()));
		return new ResponseEntity<GenericMultipleResponse<RoleDto>>(response, httpStatus);
	}

}
