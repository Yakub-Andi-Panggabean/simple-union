package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Role;
import com.polsri.union.app.domain.User;
import com.polsri.union.app.domain.UserRole;

@Repository
public class UserRoleDao {

	private static final String FIND_USER_BY_ROLE = "SELECT * FROM user a,user_role b WHERE a.user_id=b.user_id AND b.user_id=? ";
	private static final String FIND_ROLE_BY_USER = "SELECT * FROM role a,user_role b WHERE a.role_id=b.role_id AND b.role_id=?";
	private static final String DELETE_BY_USER_OR_ROLE = "DELETE FROM user_role a WHERE a.user_id = ? or role_id = ? ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertUserRole(UserRole userRole) {
		jdbcTemplate.update(userRole.generateInsertQuery(), userRole.getUserId(), userRole.getRoleId());
	}

	public void deleteUserRole(String params) {
		jdbcTemplate.update(DELETE_BY_USER_OR_ROLE, params, params);
	}

	public List<User> findUserByRole(String roleId) {
		return jdbcTemplate.query(FIND_USER_BY_ROLE, new Object[] { roleId }, User.obtainRowMapper());
	}

	public Role findRoleByUser(String userId) {
		return jdbcTemplate.queryForObject(FIND_ROLE_BY_USER, new Object[] { userId }, Role.obtainRowMapper());
	}

}
