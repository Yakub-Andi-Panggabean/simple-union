package com.polsri.union.app.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8503105056340332959L;
	private String userId;
	@NotBlank
	@NotNull
	private String username;
	@NotBlank
	@NotNull
	private String password;
	private int active;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(String userId, String username, String password, int active, Date createdDate, String createdBy,
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

}
