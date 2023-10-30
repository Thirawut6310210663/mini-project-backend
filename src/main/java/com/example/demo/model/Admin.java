package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;	
	private String adminfirstName;
	private String adminlastName;
	private String adminUsername;
	private Integer adminPassword;
	
	public Admin() {
		super();
	}
	public Admin(Integer adminId, String adminfirstName, String adminlastName, String adminUsername,
			Integer adminPassword) {
		super();
		this.adminId = adminId;
		this.adminfirstName = adminfirstName;
		this.adminlastName = adminlastName;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminfirstName() {
		return adminfirstName;
	}
	public void setAdminfirstName(String adminfirstName) {
		this.adminfirstName = adminfirstName;
	}
	public String getAdminlastName() {
		return adminlastName;
	}
	public void setAdminlastName(String adminlastName) {
		this.adminlastName = adminlastName;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public Integer getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(Integer adminPassword) {
		this.adminPassword = adminPassword;
	}
	
}