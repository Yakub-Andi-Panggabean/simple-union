package com.polsri.union.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StockDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5452914051594667591L;
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

	public StockDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockDto(String goodsId, String goodsName, BigDecimal quantity, BigDecimal maxQuantity, String location,
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
		return "StockDto [goodsId=" + goodsId + ", quantity=" + quantity + ", maxQuantity=" + maxQuantity
				+ ", location=" + location + ", subLocation=" + subLocation + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy + "]";
	}

}
