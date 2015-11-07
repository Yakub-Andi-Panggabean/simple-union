package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Role;

@Repository
public class RoleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertRole(Role role) {
		jdbcTemplate.update(role.generateInsertQuery(), role.getRoleId(), role.getRoleName(), role.getCreatedDate(),
				role.getCreatedBy(), role.getUpdatedDate(), role.getUpdatedBy());
	}

	public void updateRole(Role role) {
		jdbcTemplate.update(role.generateUpdateQuery(), role.getRoleId(), role.getRoleName(), role.getCreatedDate(),
				role.getCreatedBy(), role.getUpdatedDate(), role.getUpdatedBy(), role.getRoleId());
	}

	public void deleteRole(Role role) {
		jdbcTemplate.update(role.generateUpdateQuery(), role.getRoleId());
	}

	public Long countRole() {
		return jdbcTemplate.queryForObject(new Role().generateCountQuery(), Long.class);
	}

	public List<Role> findAllRoles(int start, int length) {
		return jdbcTemplate.query(new Role().generateSelectAllQuery(true), new Object[] { start, length },
				Role.obtainRowMapper());
	}

	public Role findRoleByRoleId(String roleId) {
		return jdbcTemplate.queryForObject(new Role().generateSelectByQuery(false, "role_id"), new Object[] { roleId },
				Role.obtainRowMapper());
	}

}
