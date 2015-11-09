package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.UserRoleDao;
import com.polsri.union.app.domain.Role;
import com.polsri.union.app.domain.User;
import com.polsri.union.app.domain.UserRole;
import com.polsri.union.app.service.UserRoleService;

@Service
@Transactional(readOnly = true)
public class UserRoleServiceBean implements UserRoleService {

	@Autowired
	private UserRoleDao dao;

	@Override
	@Transactional(readOnly = false)
	public void divorceUserRole(String params) {
		// TODO Auto-generated method stub
		dao.deleteUserRole(params);
	}

	@Override
	@Transactional(readOnly = false)
	public void uniteUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		dao.insertUserRole(userRole);
	}

	@Override
	public List<User> findUsersByRole(String roleId) {
		// TODO Auto-generated method stub
		return dao.findUserByRole(roleId);
	}

	@Override
	public Role findRoleByUser(String roleId) {
		// TODO Auto-generated method stub
		return dao.findRoleByUser(roleId);
	}

}
