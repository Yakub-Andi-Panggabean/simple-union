package com.polsri.union.app.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class RoleMenu extends QueryDomainGeneratorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7084509734577823269L;
	private String roleId;
	private String menuId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return "role_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "role_menu";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("roleId", "role_id");
		fields.put("menuId", "menu_id");
		return fields;
	}

	@Override
	public String toString() {
		return "RoleMenu [roleId=" + roleId + ", menuId=" + menuId + "]";
	}

}
