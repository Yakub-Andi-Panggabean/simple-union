package com.polsri.union.app.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class Stock extends QueryDomainGeneratorBean {
	private String goodsId;
	private String goodsName;
	private BigDecimal quantity;
	private BigDecimal maxQuantity;
	private String location;
	private String subLocation;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stock(String goodsId, String goodsName, BigDecimal quantity, BigDecimal maxQuantity, String location,
			String subLocation, Date createdDate, String createdBy, Date updatedDate, String updatedBy) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.quantity = quantity;
		this.maxQuantity = maxQuantity;
		this.location = location;
		this.subLocation = subLocation;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
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

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(BigDecimal maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSubLocation() {
		return subLocation;
	}

	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "Stock [goodsId=" + goodsId + ", quantity=" + quantity + ", maxQuantity=" + maxQuantity + ", location="
				+ location + ", subLocation=" + subLocation + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy + "]";
	}

	@Override
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return "goods_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "stock";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("goodsId", "goods_id");
		fields.put("quantity", "quantity");
		fields.put("maxQuantity", "max_quantity");
		fields.put("location", "location");
		fields.put("subLocation", "sub_location");
		fields.put("createdDate", "created_date");
		fields.put("createdBy", "created_by");
		fields.put("updatedDate", "updated_date");
		fields.put("updatedBy", "updated_by");
		return fields;
	}

	public static RowMapper<Stock> obtainRowMapper() {
		return new RowMapper<Stock>() {

			@Override
			public Stock mapRow(ResultSet rs, int rowcount) throws SQLException {
				// TODO Auto-generated method stub
				return new Stock(rs.getString("goods_id"), rs.getString("goods_name"), rs.getBigDecimal("quantity"),
						rs.getBigDecimal("max_quantity"), rs.getString("location"), rs.getString("sub_location"),
						rs.getDate("created_date"), rs.getString("created_by"), rs.getDate("updated_date"),
						rs.getString("updated_by"));
			}

		};
	}

}
