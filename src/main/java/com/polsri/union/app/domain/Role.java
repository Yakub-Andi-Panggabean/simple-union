package com.polsri.union.app.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class Role extends QueryDomainGeneratorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2452735766902998050L;
	private String roleId;
	private String roleName;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
		return "role_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "role";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("roleId", "role_id");
		fields.put("roleName", "role_name");
		fields.put("createdDate", "created_date");
		fields.put("createdBy", "created_by");
		fields.put("updatedDate", "updated_date");
		fields.put("updatedBy", "updated_by");
		return fields;
	}

	@Override
	public String toString() {
		return "Roles [roleId=" + roleId + ", roleName=" + roleName + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy + "]";
	}

}
