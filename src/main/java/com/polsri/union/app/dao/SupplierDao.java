package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Supplier;
import com.polsri.union.app.util.core.UUIDKeyProcessor;

@Repository
public class SupplierDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(Supplier supplier) {
		jdbcTemplate.update(supplier.generateInsertQuery(), UUIDKeyProcessor.generateUUID(), supplier.getSupplierName(),
				supplier.getPic(), supplier.getAddress(), supplier.getActive(), supplier.getCreatedDate(),
				supplier.getCreatedBy(), supplier.getUpdatedDate(), supplier.getUpdatedBy());
	}

	public void update(Supplier supplier) {
		jdbcTemplate.update(supplier.generateUpdateQuery(), supplier.getSupplierId(), supplier.getSupplierName(),
				supplier.getPic(), supplier.getAddress(), supplier.getActive(), supplier.getCreatedDate(),
				supplier.getCreatedBy(), supplier.getUpdatedDate(), supplier.getUpdatedBy(), supplier.getSupplierId());
	}

	public void delete(Supplier supplier) {
		jdbcTemplate.update(supplier.generateDeleteQuery(), supplier.getSupplierId());
	}

	public Supplier findById(String id) {
		return jdbcTemplate.queryForObject(new Supplier().generateSelectByQuery(false, "supplier_id"),
				new Object[] { id }, Supplier.obtainRowMapper());
	}

	public List<Supplier> findAll() {
		return jdbcTemplate.query(new Supplier().generateSelectAllQuery(false), Supplier.obtainRowMapper());
	}
}
