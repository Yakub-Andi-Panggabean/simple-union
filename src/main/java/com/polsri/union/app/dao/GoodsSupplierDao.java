package com.polsri.union.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsSupplierDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_RELATIONSHIP = "insert into goods_supplier(goods_id,supplier_id) values (?,?)";
	private static final String DELETE_RELATIONSHIP = "delete from goods_supplier where goods_id = ? or supplier_id = ?";
	private static final String UPDATE_RELATIONSHIP = "update goods_supplier set supplier_id = ? where goods_id = ?";

	public void insert(String goodsId, String supplierId) {
		jdbcTemplate.update(INSERT_RELATIONSHIP, goodsId, supplierId);
	}

	public void delete(String id) {
		jdbcTemplate.update(DELETE_RELATIONSHIP, id, id);
	}

	public void update(String goodsId, String supplierId) {
		System.out.println("goods id : " + goodsId);
		System.out.println("supplier id : " + supplierId);
		jdbcTemplate.update(UPDATE_RELATIONSHIP, supplierId, goodsId);
	}
}
