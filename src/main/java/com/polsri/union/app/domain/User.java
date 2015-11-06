package com.polsri.union.app.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class User extends QueryDomainGeneratorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2149383775404983207L;
	private String userId;
	private String username;
	private String password;
	private int active;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userId, String username, String password, int active, Date createdDate, String createdBy,
			Date updatedDate, String updatedBy) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.active = active;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "user_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "user";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("userId", "user_id");
		fields.put("username", "username");
		fields.put("password", "password");
		fields.put("active", "active");
		fields.put("createdDate", "created_date");
		fields.put("createdBy", "created_by");
		fields.put("updatedDate", "updated_date");
		fields.put("updatedBy", "updated_by");
		return fields;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", active=" + active
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate
				+ ", updatedBy=" + updatedBy + "]";
	}

	public static RowMapper<User> obtainRowMapper() {
		return new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				return new User(rs.getString("user_id"), rs.getString("username"), rs.getString("password"),
						rs.getInt("active"), new Date(rs.getDate("created_date").getTime()), rs.getString("created_by"),
						new Date(rs.getDate("created_date").getTime()), rs.getString("updated_by"));
			}
		};
	}

}
