package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.MenuDao;
import com.polsri.union.app.domain.Menu;
import com.polsri.union.app.service.MenuService;
import com.polsri.union.app.util.core.UUIDKeyProcessor;

@Service
@Transactional(readOnly = true)
public class MenuServiceBean implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	@Transactional(readOnly = false)
	public void insertMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.setMenuId(UUIDKeyProcessor.generateUUID());
		menuDao.insertMenu(menu);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		menuDao.updateMenu(menu);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteMenu(String menuId) {
		// TODO Auto-generated method stub
		menuDao.deleteMenu(new Menu(menuId, null, null, null, 0, null, null, null, null));
	}

	@Override
	public Menu findMenuById(String menuId) {
		// TODO Auto-generated method stub
		return menuDao.findMenuById(menuId);
	}

	@Override
	public List<Menu> findAllMenus() {
		// TODO Auto-generated method stub
		return menuDao.findAllMenus();
	}

	@Override
	public List<Menu> findChildMenu(String parentId) {
		// TODO Auto-generated method stub
		return menuDao.findChildMenu(parentId);
	}

}
