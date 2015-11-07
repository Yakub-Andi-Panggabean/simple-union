package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Menu;

@Repository
public class MenuDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertMenu(Menu menu) {
		jdbcTemplate
				.update(menu.generateInsertQuery(),
						new Object[] { menu.getMenuId(), menu.getLabel(), menu.getRelativeUrl(), menu.getParent(),
								menu.getCreatedDate(), menu.getCreatedBy(), menu.getUpdatedDate(),
								menu.getUpdatedBy() });
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

	public List<Menu> findAllMenus(int start, int length) {
		return jdbcTemplate.query(new Menu().generateSelectByQuery(true, "active"), new Object[] { 1, start, length },
				Menu.obtainRowMapper());
	}

	public List<Menu> findChildMenu(String parentId) {
		return jdbcTemplate.query(new Menu().generateSelectByQuery(false, "menu_id"), new Object[] { parentId },
				Menu.obtainRowMapper());
	}

}
