package com.polsri.union.app.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polsri.union.app.dao.RoleDao;
import com.polsri.union.app.domain.Role;
import com.polsri.union.app.service.RoleService;
import com.polsri.union.app.util.core.UUIDKeyProcessor;

@Service
@Transactional(readOnly = true)
public class RoleServiceBean implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional(readOnly = false)
	public void insertRole(Role role) {
		// TODO Auto-generated method stub
		role.setRoleId(UUIDKeyProcessor.generateUUID());
		roleDao.insertRole(role);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.updateRole(role);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteRole(String roleId) {
		// TODO Auto-generated method stub
		roleDao.deleteRole(new Role(roleId, null, null, null, null, null));
	}

	@Override
	public Role findRoleById(String roleId) {
		// TODO Auto-generated method stub
		return roleDao.findRoleByRoleId(roleId);
	}

	@Override
	public List<Role> findAllRoles(int start, int length) {
		// TODO Auto-generated method stub
		return roleDao.findAllRoles(start, length);
	}

	@Override
	public Long countRoles() {
		// TODO Auto-generated method stub
		return roleDao.countRole();
	}

}
