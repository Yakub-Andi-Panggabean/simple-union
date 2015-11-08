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

import com.polsri.union.app.domain.Menu;
import com.polsri.union.app.dto.MenuDto;
import com.polsri.union.app.service.MenuService;
import com.polsri.union.app.util.response.ErrorCategory;
import com.polsri.union.app.util.response.GenericMultipleResponse;
import com.polsri.union.app.util.response.GenericResponse;
import com.polsri.union.app.util.response.GenericSingleResponse;

@Controller
@RequestMapping(value = MenuController.BASE_PATH)
public class MenuController {

	public static final String BASE_PATH = "api/menu";

	private static final String PARAMETERIZED_PATH = "/{menuId:.+}";

	@Autowired
	private MenuService service;

	@RequestMapping(value = PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> deleteMenu(@PathVariable("menuId") String menuId) {
		GenericResponse response = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			service.deleteMenu(menuId);
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
	public ResponseEntity<GenericResponse> addNewUser(@Valid @RequestBody MenuDto dto, Errors errors) {
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
				Menu menu = new Menu();
				BeanUtils.copyProperties(dto, menu);
				service.insertMenu(menu);
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
	public ResponseEntity<GenericResponse> updateUser(@Valid @RequestBody MenuDto dto, Errors errors) {
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
				Menu menu = new Menu();
				BeanUtils.copyProperties(dto, menu);
				service.updateMenu(menu);
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

	@RequestMapping(value = PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericSingleResponse<MenuDto>> findMenuById(@PathVariable("menuId") String id) {
		MenuDto dto = null;
		MenuDto childMenu = null;
		List<MenuDto> childs = new ArrayList<MenuDto>();
		List<String> errorMessages = new ArrayList<String>();
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		try {
			dto = new MenuDto();
			Menu menu = service.findMenuById(id);
			BeanUtils.copyProperties(menu, dto);
			if (dto.getParent() == null) {// if null its mean that it is a
											// parent
				for (Menu child : service.findChildMenu(menu.getMenuId())) {
					childMenu = new MenuDto();
					BeanUtils.copyProperties(child, childMenu);
					childs.add(childMenu);
				}
				dto.setChilds(childs);
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

		GenericSingleResponse<MenuDto> response = new GenericSingleResponse<MenuDto>(dto, errorCategory, errorMessages,
				success);
		return new ResponseEntity<GenericSingleResponse<MenuDto>>(response, httpStatus);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericMultipleResponse<MenuDto>> findAvalilableMenu() {
		MenuDto dto = null;
		List<MenuDto> activeMenus = new ArrayList<MenuDto>();
		List<MenuDto> childs = new ArrayList<MenuDto>();
		List<String> errorMessages = new ArrayList<String>();
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		try {
			// find list of all active menus
			for (Menu menu : service.findAllMenus()) {
				dto = new MenuDto();
				BeanUtils.copyProperties(menu, dto);
				activeMenus.add(dto);
			}

			if (!activeMenus.isEmpty()) {
				for (MenuDto menu : activeMenus) {
					if (menu.getParent() == null) {
						List<Menu> listOfChild = service.findChildMenu(menu.getMenuId());
						for (Menu child : listOfChild) {
							childs.add(new MenuDto(child.getMenuId(), child.getLabel(), child.getParent(),
									child.getRelativeUrl(), child.getActive(), child.getCreatedDate(),
									child.getCreatedBy(), child.getUpdatedDate(), child.getUpdatedBy(), null));
						}
						menu.setChilds(childs);
					}
				}
			}

			httpStatus = HttpStatus.OK;
		} catch (Exception ex) {
			ex.printStackTrace();
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

		GenericMultipleResponse<MenuDto> response = new GenericMultipleResponse<>(errorCategory, errorMessages, success,
				activeMenus, null);
		return new ResponseEntity<GenericMultipleResponse<MenuDto>>(response, httpStatus);
	}

}
