package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.RoleMenuDao;
import com.polsri.union.app.domain.Menu;
import com.polsri.union.app.domain.Role;
import com.polsri.union.app.domain.RoleMenu;
import com.polsri.union.app.service.RoleMenuService;

@Service
@Transactional(readOnly = true)
public class RoleMenuServiceBean implements RoleMenuService {

	@Autowired
	private RoleMenuDao roleMenuDao;

	@Override
	@Transactional(readOnly = false)
	public void divorceRoleMenu(String params) {
		// TODO Auto-generated method stub
		roleMenuDao.deleteRoleMenu(params);
	}

	@Override
	@Transactional(readOnly = false)
	public void uniteRoleMenu(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		roleMenuDao.insertRoleMenu(roleMenu);
	}

	@Override
	public Role findRoleByMenu(String menuId) {
		// TODO Auto-generated method stub
		return roleMenuDao.findRoleByMenu(menuId);
	}

	@Override
	public List<Menu> findMenuByRole(String roleId) {
		// TODO Auto-generated method stub
		return roleMenuDao.findMenuByRole(roleId);
	}

}
