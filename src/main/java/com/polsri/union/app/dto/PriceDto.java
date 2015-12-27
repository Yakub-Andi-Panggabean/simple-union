package com.polsri.union.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PriceDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -412060240510795682L;
	private String goodsId;
	private BigDecimal price;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String goodsName;

	public PriceDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PriceDto(String goodsId, BigDecimal price, String createdBy, Date createdDate, String updatedBy,
			Date updatedDate, String goodsName) {
		super();
		this.goodsId = goodsId;
		this.price = price;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.goodsName = goodsName;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Override
	public String toString() {
		return "PriceDto [goodsId=" + goodsId + ", price=" + price + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", goodsName=" + goodsName
				+ "]";
	}

}
