package com.polsri.union.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsCategoryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String INSERT_RELATIONSHIP = "insert into goods_category(goods_id,category_id) values (?,?)";
	private final static String DELETE_RELATIONSHIP = "delete from goods_category where goods_id = ? or category_id = ?";
	private final static String UPDATE_RELATIONSHIP = "update goods_category set category_id = ? where goods_id = ? ";

	public void insert(String goodsId, String categoryId) {
		jdbcTemplate.update(INSERT_RELATIONSHIP, goodsId, categoryId);
	}

	public void delete(String id) {
		jdbcTemplate.update(DELETE_RELATIONSHIP, id, id);
	}

	public void update(String goodsId, String categoryId) {
		jdbcTemplate.update(UPDATE_RELATIONSHIP, categoryId, goodsId);
	}

}
