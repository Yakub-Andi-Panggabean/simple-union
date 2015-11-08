package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Menu;
import com.polsri.union.app.util.core.UUIDKeyProcessor;

@Repository
public class MenuDao {

	private static final StringBuilder FIND_MENU_BY_ROLE = new StringBuilder(
			"SELECT a.* FROM menu a,role_menu b WHERE a.menu_id=b.menu_id AND b.role_id=?");

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertMenu(Menu menu) {
		jdbcTemplate.update(menu.generateInsertQuery(),
				new Object[] { UUIDKeyProcessor.generateUUID(), menu.getLabel(), menu.getParent(),
						menu.getRelativeUrl(), menu.getActive(), menu.getCreatedDate(), menu.getCreatedBy(),
						menu.getUpdatedDate(), menu.getUpdatedBy() });
	}

	public void updateMenu(Menu menu) {
		jdbcTemplate
				.update(menu.generateUpdateByQuery(),
						new Object[] { menu.getMenuId(), menu.getLabel(), menu.getRelativeUrl(), menu.getParent(),
								menu.getCreatedDate(), menu.getCreatedBy(), menu.getUpdatedDate(),
								menu.getUpdatedBy() });
	}

	public void deleteMenu(Menu menu) {
		jdbcTemplate.update(menu.generateUpdateByQuery(), menu.getMenuId());
	}

	public Menu findMenuById(String menuId) {
		return jdbcTemplate.queryForObject(new Menu().generateSelectByQuery(false, "menu_id"), new Object[] { menuId },
				Menu.obtainRowMapper());
	}

	public List<Menu> findAllMenus() {
		return jdbcTemplate.query(new Menu().generateSelectByQuery(false, "active"), new Object[] { 1 },
				Menu.obtainRowMapper());
	}

	public List<Menu> findChildMenu(String parentId) {
		return jdbcTemplate.query(new Menu().generateSelectByQuery(false, "parent_id"), new Object[] { parentId },
				Menu.obtainRowMapper());
	}

	public List<Menu> findMenusByRole(String roleId) {
		return jdbcTemplate.query(FIND_MENU_BY_ROLE.toString(), new Object[] { roleId }, Menu.obtainRowMapper());
	}

}
