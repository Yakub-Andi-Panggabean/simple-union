package com.polsri.union.app.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

@Repository
public class Price extends QueryDomainGeneratorBean {
	private String goodsId;
	private BigDecimal price;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;

	// join column
	private String goodsName;

	public Price(String goodsId, String goodsName, BigDecimal price, String createdBy, Date createdDate,
			String updatedBy, Date updatedDate) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.price = price;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	public Price() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return "goods_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "goods_price";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("goodsId", "goods_id");
		fields.put("price", "price");
		fields.put("createdDate", "created_date");
		fields.put("createdBy", "created_by");
		fields.put("updatedDate", "updated_date");
		fields.put("updatedBy", "updated_by");
		return fields;
	}

	public static final RowMapper<Price> obtainRowMapper() {
		return new RowMapper<Price>() {
			@Override
			public Price mapRow(ResultSet rs, int rowCount) throws SQLException {
				// TODO Auto-generated method stub
				return new Price(rs.getString("goods_id"), rs.getString("goods_name"), rs.getBigDecimal("price"),
						rs.getString("created_by"), rs.getDate("created_date"), rs.getString("updated_by"),
						rs.getDate("updated_date"));
			}

		};
	}

}
