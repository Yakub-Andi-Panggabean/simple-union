package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Price;

@Repository
public class PriceDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SELECT_JOIN = "select a.*,b.goods_name from goods_price a,goods b where a.goods_id=b.goods_id ";
	private static final String SELECT_BY_JOIN = SELECT_JOIN + " and a.goods_id=?";

	public void insert(Price price) {
		jdbcTemplate.update(price.generateInsertQuery(), price.getGoodsId(), price.getPrice(), price.getCreatedBy(),
				price.getCreatedDate(), price.getUpdatedBy(), price.getUpdatedDate());
	}

	public void update(Price price) {
		jdbcTemplate.update(price.generateUpdateQuery(), price.getGoodsId(), price.getPrice(), price.getCreatedBy(),
				price.getCreatedDate(), price.getUpdatedBy(), price.getUpdatedDate(), price.getGoodsId());
	}

	public void delete(Price price) {
		jdbcTemplate.update(price.generateDeleteQuery(), price.getGoodsId());
	}

	public Price findById(String goodsId) {
		return jdbcTemplate.queryForObject(SELECT_BY_JOIN, new Object[] { goodsId }, Price.obtainRowMapper());
	}

	public List<Price> findAll() {
		return jdbcTemplate.query(SELECT_JOIN, Price.obtainRowMapper());
	}

}
