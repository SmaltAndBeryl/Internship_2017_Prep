package com.skill.India.dto;

public class ProfileCreationAssessmentStaffDetailsDto {

	private String name;
	private String jobRoleCode;
	private String designation;
	private String contactNumber;
	private String emailId;
	private String state;
	private String district;
	private String educationalQualification;
	private String experience;
	private String cVPath;
	private String certificatePath;
	
	/**
	 * 
	 */
	public ProfileCreationAssessmentStaffDetailsDto() {
		super();
	}
	/**
	 * @param name
	 * @param jobRoleCode
	 * @param designation
	 * @param contactNumber
	 * @param emailId
	 * @param state
	 * @param district
	 * @param educationalQualification
	 * @param experience
	 * @param cVPath
	 * @param certificatePath
	 */
	public ProfileCreationAssessmentStaffDetailsDto(String name,
			String jobRoleCode, String designation, String contactNumber,
			String emailId, String state, String district,
			String educationalQualification, String experience, String cVPath,
			String certificatePath) {
		super();
		this.name = name;
		this.jobRoleCode = jobRoleCode;
		this.designation = designation;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.state = state;
		this.district = district;
		this.educationalQualification = educationalQualification;
		this.experience = experience;
		this.cVPath = cVPath;
		this.certificatePath = certificatePath;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the jobRoleCode
	 */
	public String getJobRoleCode() {
		return jobRoleCode;
	}
	/**
	 * @param jobRoleCode the jobRoleCode to set
	 */
	public void setJobRoleCode(String jobRoleCode) {
		this.jobRoleCode = jobRoleCode;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return the educationalQualification
	 */
	public String getEducationalQualification() {
		return educationalQualification;
	}
	/**
	 * @param educationalQualification the educationalQualification to set
	 */
	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}
	/**
	 * @return the experience
	 */
	public String getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}
	/**
	 * @return the cVPath
	 */
	public String getcVPath() {
		return cVPath;
	}
	/**
	 * @param cVPath the cVPath to set
	 */
	public void setcVPath(String cVPath) {
		this.cVPath = cVPath;
	}
	/**
	 * @return the certificatePath
	 */
	public String getCertificatePath() {
		return certificatePath;
	}
	/**
	 * @param certificatePath the certificatePath to set
	 */
	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}
	
	
	
}