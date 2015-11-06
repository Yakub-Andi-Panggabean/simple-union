package com.polsri.union.app.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.polsri.union.app.util.constant.Gender;
import com.polsri.union.app.util.constant.Religion;
import com.polsri.union.app.util.core.QueryDomainGeneratorBean;

public class UserDetail extends QueryDomainGeneratorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2711819878131673406L;
	private String userId;
	private String name;
	private String lastname;
	private Gender gender;
	private String address;
	private Religion religion;
	private int age;
	private Date birthdate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Religion getReligion() {
		return religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return "user_id";
	}

	@Override
	public String findTableName() {
		// TODO Auto-generated method stub
		return "user_detail";
	}

	@Override
	public Map<String, String> mapTableField() {
		// TODO Auto-generated method stub
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("userId", "user_id");
		fields.put("name", "name");
		fields.put("lastname", "lastname");
		fields.put("gender", "gender");
		fields.put("address", "address");
		fields.put("religion", "religion");
		fields.put("age", "age");
		fields.put("birthdate", "birth_date");
		return fields;
	}
}
