package com.polsri.union.app.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class Menu extends QueryDomainGeneratorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1779749855721268462L;
	private String menuId;
	private String label;
	private String parent;
	private String relativeUrl;
	private int active;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getRelativeUrl() {
		return relativeUrl;
	}

	public void setRelativeUrl(String relativeUrl) {
		this.relativeUrl = relativeUrl;
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
		return "menu_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "menu";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("menuId", "menu_id");
		fields.put("parent_id", "parent");
		fields.put("relativeUrl", "relative_url");
		fields.put("active", "active");
		fields.put("createdDate", "created_date");
		fields.put("createdBy", "created_by");
		fields.put("updatedDate", "updated_date");
		fields.put("updatedBy", "updated_by");
		return null;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", label=" + label + ", parent=" + parent + ", relativeUrl=" + relativeUrl
				+ ", active=" + active + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDate="
				+ updatedDate + ", updatedBy=" + updatedBy + "]";
	}

}
