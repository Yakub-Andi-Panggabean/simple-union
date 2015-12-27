package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Discount;

@Repository
public class DiscountDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SELECT_JOIN_GOODS = "select a.*,b.goods_name,goods_code from goods b,goods_discount a where a.goods_id = b.goods_id";
	private static final String SELECT_JOIN_GOODS_BY_ID = SELECT_JOIN_GOODS.concat(" and a.goods_id = ?");

	public void insert(Discount discount) {
		jdbcTemplate.update(discount.generateInsertQuery(), discount.getGoodsId(), discount.getStart(),
				discount.getEnd(), discount.getDiscount(), discount.getDescription(), discount.getCreatedDate(),
				discount.getCreatedBy(), discount.getUpdatedDate(), discount.getUpdatedBy());
	}

	public void update(Discount discount) {
		jdbcTemplate.update(discount.generateUpdateQuery(), discount.getGoodsId(), discount.getStart(),
				discount.getEnd(), discount.getDiscount(), discount.getDescription(), discount.getCreatedDate(),
				discount.getCreatedBy(), discount.getUpdatedDate(), discount.getUpdatedBy(), discount.getGoodsId());
	}

	public void delete(Discount discount) {
		jdbcTemplate.update(discount.generateDeleteQuery(), discount.getGoodsId());
	}

	public Discount findById(String id) {
		return jdbcTemplate.queryForObject(SELECT_JOIN_GOODS_BY_ID, new Object[] { id }, Discount.obtainRowMapper());
	}

	public List<Discount> findAll() {
		return jdbcTemplate.query(SELECT_JOIN_GOODS, Discount.obtainRowMapper());
	}

}
