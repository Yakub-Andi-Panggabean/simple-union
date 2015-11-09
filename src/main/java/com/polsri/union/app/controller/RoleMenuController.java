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

import com.polsri.union.app.domain.Menu;
import com.polsri.union.app.domain.Role;
import com.polsri.union.app.domain.RoleMenu;
import com.polsri.union.app.dto.MenuDto;
import com.polsri.union.app.dto.RoleDto;
import com.polsri.union.app.service.MenuService;
import com.polsri.union.app.service.RoleMenuService;
import com.polsri.union.app.util.response.ErrorCategory;
import com.polsri.union.app.util.response.GenericMultipleResponse;
import com.polsri.union.app.util.response.GenericResponse;
import com.polsri.union.app.util.response.GenericSingleResponse;

@Controller
@RequestMapping(value = RoleMenuController.BASE_PATH)
public class RoleMenuController {
	public static final String BASE_PATH = "/api/rolemenu";
	private static final String PARAMETERIZED_PATH = "/{id}";
	private static final String MENU_PARAMETERIZED_PATH = "/menu/{id}";
	private static final String ROLE_PARAMETERIZED_PATH = "/role/{id}";

	@Autowired
	private RoleMenuService service;

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse> divorceRoleMenu(@PathVariable("id") String param) {
		GenericResponse response = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			service.divorceRoleMenu(param);
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
	public ResponseEntity<GenericResponse> uniteRoleMenu(@RequestBody RoleMenu roleMenu) {
		GenericResponse response = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			System.out.println("role menu to string :" + roleMenu.toString());
			service.uniteRoleMenu(roleMenu);
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

	@RequestMapping(value = ROLE_PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericSingleResponse<RoleDto>> findRoleByMenu(@PathVariable("id") String menuId) {
		RoleDto roleDto = null;
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		List<String> errorMessages = new ArrayList<String>();
		try {
			Role role = service.findRoleByMenu(menuId);
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

	@RequestMapping(value = MENU_PARAMETERIZED_PATH, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericMultipleResponse<MenuDto>> findAvalilableMenu(@PathVariable("id") String roleId) {
		MenuDto dto = null;
		List<MenuDto> activeMenus = new ArrayList<MenuDto>();
		List<MenuDto> childs = new ArrayList<MenuDto>();
		List<String> errorMessages = new ArrayList<String>();
		boolean success = true;
		ErrorCategory errorCategory = null;
		HttpStatus httpStatus = null;
		try {
			// find list of all active menus
			for (Menu menu : service.findMenuByRole(roleId)) {
				dto = new MenuDto();
				BeanUtils.copyProperties(menu, dto);
				activeMenus.add(dto);
			}

			if (!activeMenus.isEmpty()) {
				for (MenuDto menu : activeMenus) {
					if (menu.getParent() == null) {
						List<Menu> listOfChild = menuService.findChildMenu(menu.getMenuId());
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
