package com.polsri.union.app.service;

import java.util.List;

import com.polsri.union.app.domain.Role;
import com.polsri.union.app.domain.User;
import com.polsri.union.app.domain.UserRole;

public interface UserRoleService {

	void divorceUserRole(String params);

	void uniteUserRole(UserRole userRole);

	List<User> findUsersByRole(String roleId);

	Role findRoleByUser(String userId);
}
