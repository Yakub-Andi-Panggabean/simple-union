package com.polsri.union.app.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class Discount extends QueryDomainGeneratorBean {

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

	public Discount(String goodsId, Date start, Date end, BigDecimal discount, String description, Date createdDate,
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

	public Discount() {
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
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return "goods_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "goods_discount";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("goodsId", "goods_id");
		fields.put("start", "start_discount");
		fields.put("end", "end_discount");
		fields.put("discount", "discount");
		fields.put("description", "description");
		fields.put("createdDate", "created_date");
		fields.put("createdBy", "created_by");
		fields.put("updatedDate", "updated_date");
		fields.put("updatedBy", "updated_by");
		return fields;
	}

	@Override
	public String toString() {
		return "Discount [goodsId=" + goodsId + ", start=" + start + ", end=" + end + ", discount=" + discount
				+ ", description=" + description + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy + ", goodsName=" + goodsName
				+ ", goodsCode=" + goodsCode + "]";
	}

	public static RowMapper<Discount> obtainRowMapper() {
		return new RowMapper<Discount>() {

			@Override
			public Discount mapRow(ResultSet rs, int rowCount) throws SQLException {
				// TODO Auto-generated method stub
				return new Discount(rs.getString("goods_id"), rs.getDate("start_discount"), rs.getDate("end_discount"),
						rs.getBigDecimal("discount"), rs.getString("description"), rs.getDate("created_date"),
						rs.getString("created_by"), rs.getDate("created_date"), rs.getString("updated_by"),
						rs.getString("goods_name"), rs.getString("goods_code"));
			}

		};
	}

}
