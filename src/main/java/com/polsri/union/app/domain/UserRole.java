package com.polsri.union.app.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class UserRole extends QueryDomainGeneratorBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2454491472671504943L;
	private String userId;
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return "user_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "user_role";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("userId", "user_id");
		fields.put("roleId", "role_id");
		return fields;
	}
}
