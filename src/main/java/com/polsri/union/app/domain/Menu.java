package com.polsri.union.app.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;
import com.polsri.union.app.util.core.Utility;

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

	public Menu(String menuId, String label, String parent, String relativeUrl, int active, Date createdDate,
			String createdBy, Date updatedDate, String updatedBy) {
		super();
		this.menuId = menuId;
		this.label = label;
		this.parent = parent;
		this.relativeUrl = relativeUrl;
		this.active = active;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		fields.put("label", "label");
		fields.put("parent", "parent_id");
		fields.put("relativeUrl", "relative_url");
		fields.put("active", "active");
		fields.put("createdDate", "created_date");
		fields.put("createdBy", "created_by");
		fields.put("updatedDate", "updated_date");
		fields.put("updatedBy", "updated_by");
		return fields;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", label=" + label + ", parent=" + parent + ", relativeUrl=" + relativeUrl
				+ ", active=" + active + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDate="
				+ updatedDate + ", updatedBy=" + updatedBy + "]";
	}

	public static RowMapper<Menu> obtainRowMapper() {
		return new RowMapper<Menu>() {

			@Override
			public Menu mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				return new Menu(rs.getString("menu_id"), rs.getString("label"), rs.getString("parent_id"),
						rs.getString("relative_url"), rs.getInt("active"),
						rs.getDate("created_date"), rs.getString("created_by"),
						rs.getDate("updated_date"), rs.getString("updated_by"));
			}
		};
	}

}
