package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Category;
import com.polsri.union.app.util.core.UUIDKeyProcessor;

@Repository
public class CategoryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(Category category) {
		jdbcTemplate.update(category.generateInsertQuery(), UUIDKeyProcessor.generateUUID(), category.getCategoryName(),
				category.getActive(), category.getCreatedDate(), category.getCreatedBy(), category.getUpdatedDate(),
				category.getUpdatedBy());
	}

	public void update(Category category) {
		jdbcTemplate.update(category.generateUpdateQuery(), category.getCategoryId(), category.getCategoryName(),
				category.getActive(), category.getCreatedDate(), category.getCreatedBy(), category.getUpdatedDate(),
				category.getUpdatedBy(), category.getCategoryId());
	}

	public void delete(Category category) {
		System.out.println("category id :" + category.getCategoryId());
		jdbcTemplate.update(category.generateDeleteQuery(), category.getCategoryId());
	}

	public Category findById(String categoryId) {
		return jdbcTemplate.queryForObject(new Category().generateSelectByQuery(false, "category_id"),
				new Object[] { categoryId }, Category.obtainRowMapper());
	}

	public List<Category> findAll() {
		return jdbcTemplate.query(new Category().generateSelectAllQuery(false), Category.obtainRowMapper());
	}

}
