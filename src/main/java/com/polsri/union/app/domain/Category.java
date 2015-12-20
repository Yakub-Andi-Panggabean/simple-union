package com.polsri.union.app.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class Category extends QueryDomainGeneratorBean {

	private String categoryId;
	private String categoryName;
	private int active;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String categoryId, String categoryName, int active, Date createdDate, String createdBy,
			Date updatedDate, String updatedBy) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.active = active;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
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
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return "category_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "category";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("categoryId", "category_id");
		fields.put("categoryName", "category_name");
		fields.put("active", "active");
		fields.put("createdDate", "created_date");
		fields.put("createdBy", "created_by");
		fields.put("updatedDate", "updated_date");
		fields.put("updatedBy", "updated_by");
		return fields;
	}

	public static RowMapper<Category> obtainRowMapper() {
		return new RowMapper<Category>() {

			@Override
			public Category mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				return new Category(rs.getString("category_id"), rs.getString("category_name"), rs.getInt("active"),
						rs.getDate("created_date"), rs.getString("created_by"), rs.getDate("updated_date"),
						rs.getString("updated_by"));
			}
		};
	}

}
