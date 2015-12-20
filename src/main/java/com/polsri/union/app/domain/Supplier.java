package com.polsri.union.app.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class Supplier extends QueryDomainGeneratorBean {
	private String supplierId;
	private String supplierName;
	private String pic;
	private String address;
	private int active;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Supplier(String supplierId, String supplierName, String pic, String address, int active, Date createdDate,
			String createdBy, Date updatedDate, String updatedBy) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.pic = pic;
		this.address = address;
		this.active = active;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "supplier_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "supplier";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("supplierId", "supplier_id");
		fields.put("supplierName", "supplier_name");
		fields.put("pic", "pic");
		fields.put("address", "address");
		fields.put("active", "active");
		fields.put("createdDate", "created_date");
		fields.put("createdBy", "created_by");
		fields.put("updatedDate", "updated_date");
		fields.put("updatedBy", "updated_by");
		return fields;
	}

	public static RowMapper<Supplier> obtainRowMapper() {
		return new RowMapper<Supplier>() {

			@Override
			public Supplier mapRow(ResultSet rs, int rownum) throws SQLException {
				// TODO Auto-generated method stub
				return new Supplier(rs.getString("supplier_id"), rs.getString("supplier_name"), rs.getString("pic"),
						rs.getString("address"), rs.getInt("active"), rs.getDate("created_date"),
						rs.getString("created_by"), rs.getDate("updated_date"), rs.getString("updated_by"));
			}

		};
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", pic=" + pic + ", address="
				+ address + ", active=" + active + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", updatedDate=" + updatedDate + ", updatedBy=" + updatedBy + "]";
	}

}
