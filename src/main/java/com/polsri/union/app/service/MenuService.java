package com.polsri.union.app.service;

import java.util.List;

import com.polsri.union.app.domain.Menu;

public interface MenuService {

	void insertMenu(Menu menu);

	void updateMenu(Menu menu);

	void deleteMenu(String menuId);

	Menu findMenuById(String menuId);

	List<Menu> findAllMenus();

	List<Menu> findChildMenu(String parentId);

	List<Menu> findMenuByRole(String roleId);

}
