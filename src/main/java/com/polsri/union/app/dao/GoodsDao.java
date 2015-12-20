package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Goods;

@Repository
public class GoodsDao {

	private static final String BASE_SELECT_JOIN = "select a.*,b.supplier_id,b.supplier_name supplier,c.category_id,c.category_name category"
			+ " from goods a, supplier b, category c, goods_category d, goods_supplier e "
			+ " where d.goods_id=a.goods_id " + " and e.goods_id=a.goods_id " + " and c.category_id=d.category_id "
			+ " and b.supplier_id=e.supplier_id " + " and c.active = 1 " + " and b.active = 1 ";

	private static final String SINGLE_SELECT = BASE_SELECT_JOIN + " and a.goods_id = ?";
	private static final String MULTIPLE_SELECT = BASE_SELECT_JOIN + " order by a.goods_name limit ?,?";
	private static final String COUNT_SELECT = "select count(*) from (" + BASE_SELECT_JOIN + ") z ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(Goods goods) {
		jdbcTemplate.update(goods.generateInsertQuery(), goods.getGoodsId(), goods.getGoodsCode(), goods.getGoodsName(),
				goods.getBrand(), goods.getActive(), goods.getCreatedDate(), goods.getCreatedBy(),
				goods.getUpdatedDate(), goods.getUpdatedBy());
	}

	public void update(Goods goods) {
		jdbcTemplate.update(goods.generateUpdateQuery(), goods.getGoodsId(), goods.getGoodsCode(), goods.getGoodsName(),
				goods.getBrand(), goods.getActive(), goods.getCreatedDate(), goods.getCreatedBy(),
				goods.getUpdatedDate(), goods.getUpdatedBy(), goods.getGoodsId());
	}

	public void delete(Goods goods) {
		jdbcTemplate.update(goods.generateDeleteQuery(), goods.getGoodsId());
	}

	public Goods findById(String id) {
		return jdbcTemplate.queryForObject(SINGLE_SELECT, new Object[] { id }, Goods.obtainRowMapper());
	}

	public List<Goods> findAll(int start, int limit) {
		return jdbcTemplate.query(MULTIPLE_SELECT, new Object[] { start, limit }, Goods.obtainRowMapper());
	}

	public Long count() {
		return jdbcTemplate.queryForObject(COUNT_SELECT, Long.class);
	}

}
