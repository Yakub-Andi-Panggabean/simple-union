package com.polsri.union.app.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.polsri.union.app.util.constant.Util;

public class MenuDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8294603244128176664L;
	private String menuId;
	private String label;
	private String parent;
	private String relativeUrl;
	private String icon;
	private String type;
	private int active;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	private List<MenuDto> children;

	public MenuDto(String menuId, String label, String parent, String relativeUrl, int active, Date createdDate,
			String createdBy, Date updatedDate, String updatedBy, List<MenuDto> children) {
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
		this.children = children;
		this.icon = Util.ICON;

	}

	public MenuDto() {
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

	public List<MenuDto> getchildren() {
		return children;
	}

	public void setchildren(List<MenuDto> children) {
		this.children = children;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = Util.ICON;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		if (parent == null || parent == "") {
			type = "toogle";
		} else {
			type = "link";
		}
		return type;
	}

}
