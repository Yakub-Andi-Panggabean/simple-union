package com.polsri.union.app.service;

import java.util.List;

import com.polsri.union.app.domain.Role;

public interface RoleService {

	void insertRole(Role role);

	void updateRole(Role role);

	void deleteRole(String roleId);

	Role findRoleById(String roleId);

	List<Role> findAllRoles(int start, int length);

	Long countRoles();
}
