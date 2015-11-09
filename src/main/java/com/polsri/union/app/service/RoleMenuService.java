package com.polsri.union.app.service;

import java.util.List;

import com.polsri.union.app.domain.Menu;
import com.polsri.union.app.domain.Role;
import com.polsri.union.app.domain.RoleMenu;

public interface RoleMenuService {

	void divorceRoleMenu(String params);

	void uniteRoleMenu(RoleMenu roleMenu);

	Role findRoleByMenu(String menuId);

	List<Menu> findMenuByRole(String roleId);

}
