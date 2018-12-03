package com.medical.model;

import java.io.Serializable;
import java.util.Date;

import com.medical.model.common.Persistable;


public class Patient implements Persistable, Serializable {

	private String pId;
	private String name;
	private String mobile;
	private String aadhaar;
	private String email;
	private String gender;
	private String allergies;
	private Date dateOfBrith;
	private String password;
	private String profilePicPath;
	private Integer tandCAccepted;
	private Date createdDate;
	private Date updatedDate;
	private String patientAddress;

	@Override
	public String getId() {
		return pId;
	}

	@Override
	public void setId(String id) {
		this.pId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public Date getDateOfBrith() {
		return dateOfBrith;
	}

	public void setDateOfBrith(Date dateOfBrith) {
		this.dateOfBrith = dateOfBrith;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}

	public Integer getTandCAccepted() {
		return tandCAccepted;
	}

	public void setTandCAccepted(Integer tandCAccepted) {
		this.tandCAccepted = tandCAccepted;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	@Override
	public String toString() {
		return "Patient [pId=" + pId + ", name=" + name + ", mobile=" + mobile + ", aadhaar=" + aadhaar + ", email="
				+ email + ", gender=" + gender + ", allergies=" + allergies + ", dateOfBrith=" + dateOfBrith
				+ ", password=" + password + ", profilePicPath=" + profilePicPath + ", tandCAccepted=" + tandCAccepted
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", patientAddress=" + patientAddress
				+ "]";
	}

}
