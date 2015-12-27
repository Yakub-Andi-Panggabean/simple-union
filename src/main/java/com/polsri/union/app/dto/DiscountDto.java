package com.polsri.union.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DiscountDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3590793385369786969L;
	private String goodsId;
	private Date start;
	private Date end;
	private BigDecimal discount;
	private String description;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	// join field
	private String goodsName;
	private String goodsCode;

	public DiscountDto(String goodsId, Date start, Date end, BigDecimal discount, String description, Date createdDate,
			String createdBy, Date updatedDate, String updatedBy, String goodsName, String goodsCode) {
		super();
		this.goodsId = goodsId;
		this.start = start;
		this.end = end;
		this.discount = discount;
		this.description = description;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.goodsName = goodsName;
		this.goodsCode = goodsCode;
	}

	public DiscountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	@Override
	public String toString() {
		return "DiscountDto [goodsId=" + goodsId + ", start=" + start + ", end=" + end + ", discount=" + discount
				+ ", description=" + description + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy + ", goodsName=" + goodsName
				+ ", goodsCode=" + goodsCode + "]";
	}

}
