package com.polsri.union.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.domain.Stock;

@Repository
public class StockDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String JOIN_GOODS_SELECT = "select a.*,b.goods_name from stock a,goods b where a.goods_id=b.goods_id";
	private static final String JOIN_GOODS_SELECT_BY = JOIN_GOODS_SELECT + " and a.goods_id = ?";

	public void insert(Stock stock) {
		jdbcTemplate.update(stock.generateInsertQuery(), stock.getGoodsId(), stock.getQuantity(),
				stock.getMaxQuantity(), stock.getLocation(), stock.getSubLocation(), stock.getCreatedDate(),
				stock.getCreatedBy(), stock.getUpdatedDate(), stock.getUpdatedBy());
	}

	public void update(Stock stock) {
		jdbcTemplate.update(stock.generateUpdateQuery(), stock.getGoodsId(), stock.getQuantity(),
				stock.getMaxQuantity(), stock.getLocation(), stock.getSubLocation(), stock.getCreatedDate(),
				stock.getCreatedBy(), stock.getUpdatedDate(), stock.getUpdatedBy(), stock.getGoodsId());
	}

	public void delete(Stock stock) {
		jdbcTemplate.update(stock.generateDeleteQuery(), stock.getGoodsId());
	}

	public Stock findStockById(String goodsId) {
		return jdbcTemplate.queryForObject(JOIN_GOODS_SELECT_BY, new Object[] { goodsId }, Stock.obtainRowMapper());
	}

	public List<Stock> findAllStock() {
		return jdbcTemplate.query(JOIN_GOODS_SELECT, Stock.obtainRowMapper());
	}

}
