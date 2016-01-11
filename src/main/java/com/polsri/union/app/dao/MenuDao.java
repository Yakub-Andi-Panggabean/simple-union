package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Menu;
import com.polsri.union.app.util.core.UUIDKeyProcessor;

@Repository
public class MenuDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertMenu(Menu menu) {
		jdbcTemplate.update(menu.generateInsertQuery(),
				new Object[] { UUIDKeyProcessor.generateUUID(), menu.getLabel(), menu.getParent(),
						menu.getRelativeUrl(), menu.getActive(), menu.getCreatedDate(), menu.getCreatedBy(),
						menu.getUpdatedDate(), menu.getUpdatedBy() });
	}

	public void updateMenu(Menu menu) {
            System.out.println("active :"+menu.getActive());
		jdbcTemplate
				.update(menu.generateUpdateQuery(),
						new Object[] { menu.getMenuId(), menu.getLabel(),menu.getParent(), menu.getRelativeUrl(), menu.getActive(),
								menu.getCreatedDate(), menu.getCreatedBy(), menu.getUpdatedDate(),
								menu.getUpdatedBy(),menu.getMenuId() });
	}

	public void deleteMenu(Menu menu) {
		jdbcTemplate.update(menu.generateUpdateByQuery(), menu.getMenuId());
	}

	public Menu findMenuById(String menuId) {
		return jdbcTemplate.queryForObject(new Menu().generateSelectByQuery(false, "menu_id"), new Object[] { menuId },
				Menu.obtainRowMapper());
	}

	public List<Menu> findAllMenus() {
		return jdbcTemplate.query(new Menu().generateSelectAllQuery(false), new Object[] {},
				Menu.obtainRowMapper());
	}

	public List<Menu> findChildMenu(String parentId) {
		return jdbcTemplate.query(new Menu().generateSelectByQuery(false, "parent_id").concat("and active = 1"),
				new Object[] { parentId }, Menu.obtainRowMapper());
	}

}
