package com.polsri.union.app.dto;

import java.io.Serializable;
import java.util.Date;

public class GoodsDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 477757947561827476L;
	private String goodsId;
	private String goodsCode;
	private String goodsName;
	private String brand;
	private int active;
	private String category;
	private String supplier;
	private String categoryId;
	private String supplierId;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	public GoodsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodsDto(String goodsId, String goodsCode, String goodsName, String brand, int active, String category,
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
	public String toString() {
		return "GoodsDto [goodsId=" + goodsId + ", goodsCode=" + goodsCode + ", goodsName=" + goodsName + ", brand="
				+ brand + ", active=" + active + ", category=" + category + ", supplier=" + supplier + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy
				+ "]";
	}

}
