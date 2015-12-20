package com.polsri.union.app.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class Goods extends QueryDomainGeneratorBean {
	private String goodsId;
	private String goodsCode;
	private String goodsName;
	private String brand;
	private int active;
	private String category;
	private String categoryId;
	private String supplier;
	private String supplierId;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Goods(String goodsId, String goodsCode, String goodsName, String brand, int active, String category,
			String supplier, String categoryId, String supplierId, Date createdDate, String createdBy, Date updatedDate,
			String updatedBy) {
		super();
		this.goodsId = goodsId;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.brand = brand;
		this.active = active;
		this.category = category;
		this.supplier = supplier;
		this.categoryId = categoryId;
		this.supplierId = supplierId;
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

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	@Override
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return "goods_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "goods";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("goodsId", "goods_id");
		fields.put("goodsCode", "goods_code");
		fields.put("goodsName", "goods_name");
		fields.put("brand", "brand");
		fields.put("active", "active");
		fields.put("createdDate", "created_date");
		fields.put("createdBy", "created_by");
		fields.put("updatedDate", "updated_date");
		fields.put("updatedBy", "updated_by");
		return fields;
	}

	public static RowMapper<Goods> obtainRowMapper() {
		return new RowMapper<Goods>() {
			@Override
			public Goods mapRow(ResultSet rs, int col) throws SQLException {
				// TODO Auto-generated method stub

				return new Goods(rs.getString("goods_id"), rs.getString("goods_code"), rs.getString("goods_name"),
						rs.getString("brand"), rs.getInt("active"), rs.getString("category"), rs.getString("supplier"),
						rs.getString("category_id"), rs.getString("supplier_id"), rs.getDate("created_date"),
						rs.getString("created_by"), rs.getDate("updated_date"), rs.getString("updated_by"));
			}

		};
	}

}
