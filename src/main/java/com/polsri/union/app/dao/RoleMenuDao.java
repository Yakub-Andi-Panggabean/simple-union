package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Menu;
import com.polsri.union.app.domain.Role;
import com.polsri.union.app.domain.RoleMenu;

@Repository
public class RoleMenuDao {

	private static final String FIND_ROLE_BY_MENU = "SELECT a.* FROM role a,role_menu b WHERE a.role_id=b.role_id AND b.menu_id=? ";
	private static final String FIND_MENU_BY_ROLE = "SELECT a.* FROM menu a,role_menu b WHERE a.menu_id=b.menu_id AND b.role_id=?";
	private static final String DELETE_BY_MENU_OR_ROLE = "DELETE FROM role_menu  WHERE menu_id = ? OR role_id = ? ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertRoleMenu(RoleMenu roleMenu) {
		System.out.println(roleMenu.generateInsertQuery());
		jdbcTemplate.update(roleMenu.generateInsertQuery(), roleMenu.getRoleId(), roleMenu.getMenuId());
	}

	public void deleteRoleMenu(String params) {
		jdbcTemplate.update(DELETE_BY_MENU_OR_ROLE, params, params);
	}

	public List<Menu> findMenuByRole(String roleId) {
		return jdbcTemplate.query(FIND_MENU_BY_ROLE, new Object[] { roleId }, Menu.obtainRowMapper());
	}

	public Role findRoleByMenu(String menuId) {
		return jdbcTemplate.queryForObject(FIND_ROLE_BY_MENU, new Object[] { menuId }, Role.obtainRowMapper());
	}

}
