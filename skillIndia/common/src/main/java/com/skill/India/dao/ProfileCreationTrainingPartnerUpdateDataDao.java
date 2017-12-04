package com.skill.India.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skill.India.common.AbstractTransactionalDao;
import com.skill.India.config.ProfileCreationTrainingPartnerConfigSql;
import com.skill.India.dto.ProfileCreationTrainingPartnerCenterDetailsDto;
import com.skill.India.dto.ProfileCreationTrainingPartnerInstituteGrantDto;
import com.skill.India.dto.ProfileCreationTrainingPartnerInstituteRecognitionDto;
import com.skill.India.dto.ProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto;
import com.skill.India.dto.ProfileCreationTrainingPartnerOrganizationDetailsDto;
import com.skill.India.dto.ProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto;

@Repository
public class ProfileCreationTrainingPartnerUpdateDataDao extends AbstractTransactionalDao{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileCreationTrainingPartnerUpdateDataDao.class);
	@Autowired
	private ProfileCreationTrainingPartnerConfigSql profileCreationTrainingPartnerConfigSql;

	
	/*
	 * update table Training Partner Organization Details 
	 * 
	 */
	
	public int updateIntoTrainingPartnerOrganizationDetails(ProfileCreationTrainingPartnerOrganizationDetailsDto profileCreationTrainingPartnerOrganizationDetailsDto)
	{
		int updateStaus = 0;
		try{
			LOGGER.info("Trying to construct database query to update data of organisation details in Training Partner");
			Map<String, Object> parameters=new HashMap<String, Object>();
			
			parameters.put("applicationId",profileCreationTrainingPartnerOrganizationDetailsDto.getApplicationId());
			parameters.put("trainingPartnerRegistrationId",profileCreationTrainingPartnerOrganizationDetailsDto.getTrainingPartnerRegistrationId());
			parameters.put("organizationName",profileCreationTrainingPartnerOrganizationDetailsDto.getOrganizationName());
			parameters.put("sPOCName",profileCreationTrainingPartnerOrganizationDetailsDto.getsPOCName());
			parameters.put("address",profileCreationTrainingPartnerOrganizationDetailsDto.getAddress());
			parameters.put("city",profileCreationTrainingPartnerOrganizationDetailsDto.getCity());
			parameters.put("state",profileCreationTrainingPartnerOrganizationDetailsDto.getState());
			parameters.put("pincode",profileCreationTrainingPartnerOrganizationDetailsDto.getPincode());
			parameters.put("mobileNumber",profileCreationTrainingPartnerOrganizationDetailsDto.getMobileNumber());
			parameters.put("alternateMobileNumber",profileCreationTrainingPartnerOrganizationDetailsDto.getAlternateMobileNumber());
			parameters.put("landlineNumber",profileCreationTrainingPartnerOrganizationDetailsDto.getLandlineNumber());
			parameters.put("alternateLandlineNumber",profileCreationTrainingPartnerOrganizationDetailsDto.getAlternateLandlineNumber());
			parameters.put("faxNumber",profileCreationTrainingPartnerOrganizationDetailsDto.getFaxNumber());
			parameters.put("websites",profileCreationTrainingPartnerOrganizationDetailsDto.getWebsites());
			parameters.put("yearOfEstablishment",profileCreationTrainingPartnerOrganizationDetailsDto.getYearOfEstablishment());
			parameters.put("qualificationPacks",profileCreationTrainingPartnerOrganizationDetailsDto.getQualificationPacks());
			parameters.put("nSDCFunded",profileCreationTrainingPartnerOrganizationDetailsDto.getnSDCFunded());
			parameters.put("mediumOfInstructions",profileCreationTrainingPartnerOrganizationDetailsDto.getMediumOfInstructions());
			parameters.put("selfOwnedInstitution",profileCreationTrainingPartnerOrganizationDetailsDto.getSelfOwnedInstitution());
			parameters.put("franchiseOwnedInstitution",profileCreationTrainingPartnerOrganizationDetailsDto.getFranchiseOwnedInstitution());
			parameters.put("mobileTrainingInstitution",profileCreationTrainingPartnerOrganizationDetailsDto.getMobileTrainingInstitution());
			parameters.put("panNumber",profileCreationTrainingPartnerOrganizationDetailsDto.getPanNumber());
			parameters.put("tanNumber",profileCreationTrainingPartnerOrganizationDetailsDto.getTanNumber());
			parameters.put("turnOverOfInstitution",profileCreationTrainingPartnerOrganizationDetailsDto.getTurnOverOfInstitution());
			parameters.put("instituteReceivedAnyGrant",profileCreationTrainingPartnerOrganizationDetailsDto.getInstituteReceivedAnyGrant());
			parameters.put("instituteReceivedAnyRecognition",profileCreationTrainingPartnerOrganizationDetailsDto.getInstituteReceivedAnyRecognition());
			parameters.put("priorExperienceOfInstitutionInSkillDevelopment",profileCreationTrainingPartnerOrganizationDetailsDto.getPriorExperienceOfInstitutionInSkillDevelopment());
			parameters.put("anyPriorExperienceOfInstitutionInSkillTraining",profileCreationTrainingPartnerOrganizationDetailsDto.getAnyPriorExperienceOfInstitutionInSkillTraining());			
			LOGGER.debug("Trying to execute database query to update data of organisation details in Training Partner");
			updateStaus = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdateIntoTrainingPartnerOrganizationDetails(),parameters);

		}
		catch(Exception e)
		{
			LOGGER.error("Exception occured while updating data of organisation details in Training Partner. Exception is -" +e);
			
			updateStaus = -1;
		}
		return updateStaus;
	}

	
	/*
	 * Update Data in PC TP Center Level Details
	 *  will be inserting TPREGID 
	 */
	
	
	
		public int updateIntoTrainingPartnerCenterLevelDetails(ProfileCreationTrainingPartnerCenterDetailsDto profileCreationTrainingPartnerCenterDetailsDto)
		{
			int status = 0;
			try{
				Map<String, Object> parameters=new HashMap<String, Object>();
				
				/*
				 * Get TPREGID using App Id nd pass it on to This function
				 */
				parameters.put("trainingPartnerCenterId",profileCreationTrainingPartnerCenterDetailsDto.getTrainingPartnerCenterId());
				parameters.put("trainingPartnerRegistrationId",profileCreationTrainingPartnerCenterDetailsDto.getTrainingPartnerRegistrationId());
				parameters.put("nameOperationHead", profileCreationTrainingPartnerCenterDetailsDto.getNameOperationHead());
				parameters.put("designationOperationHead", profileCreationTrainingPartnerCenterDetailsDto.getDesignationOperationHead());
				parameters.put("emailOperationHead", profileCreationTrainingPartnerCenterDetailsDto.getEmailOperationHead());
				parameters.put("contactNumberOperationHead", profileCreationTrainingPartnerCenterDetailsDto.getContactNumberOperationHead());
				parameters.put("educationalQualificationOperationHead", profileCreationTrainingPartnerCenterDetailsDto.getEducationalQualificationOperationHead());
				parameters.put("experienceOperationHead", profileCreationTrainingPartnerCenterDetailsDto.getExperienceOperationHead());
				parameters.put("cvOperationHeadPath", profileCreationTrainingPartnerCenterDetailsDto.getCvOperationHeadPath());
				parameters.put("nameAffiliationCoordinator", profileCreationTrainingPartnerCenterDetailsDto.getNameAffiliationCoordinator());
				parameters.put("designationAffiliationCoordinator", profileCreationTrainingPartnerCenterDetailsDto.getDesignationAffiliationCoordinator());
				parameters.put("emailAffiliationCoordinator", profileCreationTrainingPartnerCenterDetailsDto.getEmailAffiliationCoordinator());
				parameters.put("contactNumberAffiliationCoordinator", profileCreationTrainingPartnerCenterDetailsDto.getContactNumberAffiliationCoordinator());
				parameters.put("educationalQualificationAffiliationCoordinator", profileCreationTrainingPartnerCenterDetailsDto.getEducationalQualificationAffiliationCoordinator());
				parameters.put("experienceAffiliationCoordinator", profileCreationTrainingPartnerCenterDetailsDto.getExperienceAffiliationCoordinator());
				parameters.put("cvAffiliationCoordinatorPath", profileCreationTrainingPartnerCenterDetailsDto.getCvAffiliationCoordinatorPath());
				parameters.put("nameSPOC", profileCreationTrainingPartnerCenterDetailsDto.getNameSPOC());
				parameters.put("designationSPOC", profileCreationTrainingPartnerCenterDetailsDto.getDesignationSPOC());
				parameters.put("emailSPOC", profileCreationTrainingPartnerCenterDetailsDto.getEmailSPOC());
				parameters.put("contactNumberSPOC", profileCreationTrainingPartnerCenterDetailsDto.getContactNumberSPOC());
				parameters.put("educationalQualificationSPOC", profileCreationTrainingPartnerCenterDetailsDto.getEducationalQualificationSPOC());
				parameters.put("experienceSPOC", profileCreationTrainingPartnerCenterDetailsDto.getExperienceSPOC());
				parameters.put("cvSPOCPath", profileCreationTrainingPartnerCenterDetailsDto.getCvSPOCPath());
				parameters.put("nameOfCenter",profileCreationTrainingPartnerCenterDetailsDto.getNameOfCenter().toUpperCase());
				parameters.put("numberOfPermanentOfficeManager",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfPermanentOfficeManager());
				parameters.put("numberOftemporaryOfficeManager",profileCreationTrainingPartnerCenterDetailsDto.getNumberOftemporaryOfficeManager());
				parameters.put("numberOfPermanentOfficeStaff",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfPermanentOfficeStaff());
				parameters.put("numberOfTemporaryOfficeStaff",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfTemporaryOfficeStaff());
				parameters.put("numberOfPermanentLabAssistants",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfPermanentLabAssistants());
				parameters.put("numberOfTemporaryLabAssistants",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfTemporaryLabAssistants());
				parameters.put("numberOfPermanentAccountants",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfPermanentAccountants());
				parameters.put("numberOfTemporaryAccountants",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfTemporaryAccountants());
				parameters.put("numberOfPermanentSupportStaff",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfPermanentSupportStaff());
				parameters.put("numberOfTemporarySupportStaff",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfTemporarySupportStaff());
				parameters.put("numberOfPermanentOtherEmployees",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfPermanentOtherEmployees());
				parameters.put("numberOfTemporaryOtherEmployees",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfTemporaryOtherEmployees());
				parameters.put("areaOfInstitute",profileCreationTrainingPartnerCenterDetailsDto.getAreaOfInstitute());
				parameters.put("buildingType",profileCreationTrainingPartnerCenterDetailsDto.getBuildingType());
				parameters.put("sizeOfClassrooms",profileCreationTrainingPartnerCenterDetailsDto.getSizeOfClassrooms());
				parameters.put("classroomPicsPath",profileCreationTrainingPartnerCenterDetailsDto.getClassroomPicsPath());
				parameters.put("numberOfClassrooms",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfClassrooms());
				parameters.put("sizeOfLabs",profileCreationTrainingPartnerCenterDetailsDto.getSizeOfLabs());
				parameters.put("labPicsPath",profileCreationTrainingPartnerCenterDetailsDto.getLabPicsPath());
				parameters.put("numberOfLabs",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfLabs());
				parameters.put("sizeOfWorkshops",profileCreationTrainingPartnerCenterDetailsDto.getSizeOfWorkshops());
				parameters.put("workshopPicsPath",profileCreationTrainingPartnerCenterDetailsDto.getWorkshopPicsPath());
				parameters.put("numberOfWorkshops",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfWorkshops());
				parameters.put("mandatoryToolKitpresent",profileCreationTrainingPartnerCenterDetailsDto.getMandatoryToolKitpresent());
				parameters.put("mandatoryToolKitAnnexurePath",profileCreationTrainingPartnerCenterDetailsDto.getMandatoryToolKitAnnexurePath());
				parameters.put("mandatoryToolKitPicsPath",profileCreationTrainingPartnerCenterDetailsDto.getMandatoryToolKitPicsPath());
				parameters.put("safeDrinkingWater",profileCreationTrainingPartnerCenterDetailsDto.getSafeDrinkingWater());
				parameters.put("powerBackup",profileCreationTrainingPartnerCenterDetailsDto.getPowerBackup());
				parameters.put("separateToilets",profileCreationTrainingPartnerCenterDetailsDto.getSeparateToilets());
				parameters.put("transportFacility",profileCreationTrainingPartnerCenterDetailsDto.getTransportFacility());
				parameters.put("fireSafety",profileCreationTrainingPartnerCenterDetailsDto.getFireSafety());
				parameters.put("presenceOfLibrary",profileCreationTrainingPartnerCenterDetailsDto.getPresenceOfLibrary());
				parameters.put("numberOfTechnicalBooks",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfNonTechnicalBooks());
				parameters.put("numberOfNonTechnicalBooks",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfNonTechnicalBooks());
				parameters.put("numberOfMagazines",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfMagazines());
				parameters.put("numberOfDailies",profileCreationTrainingPartnerCenterDetailsDto.getNumberOfDailies());
				parameters.put("remarksOnInfrastructureDetails",profileCreationTrainingPartnerCenterDetailsDto.getRemarksOnInfrastructureDetails());
				parameters.put("sufficientClassroomIlluminationLevel",profileCreationTrainingPartnerCenterDetailsDto.getSufficientClassroomIlluminationLevel());
				parameters.put("sufficientClassroomVentilationLevel",profileCreationTrainingPartnerCenterDetailsDto.getSufficientClassroomVentilationLevel());
				parameters.put("sufficientCenterCleanliness",profileCreationTrainingPartnerCenterDetailsDto.getSufficientCenterCleanliness());
				parameters.put("centerWeatherProtected",profileCreationTrainingPartnerCenterDetailsDto.getCenterWeatherProtected());
				parameters.put("remarksOnLearningEnviornment",profileCreationTrainingPartnerCenterDetailsDto.getRemarksOnLearningEnviornment());
				parameters.put("printedBrochureOrProspectus",profileCreationTrainingPartnerCenterDetailsDto.getPrintedBrochureOrProspectus());
				parameters.put("documentedPolicyAndProcedures",profileCreationTrainingPartnerCenterDetailsDto.getDocumentedPolicyAndProcedures());
				parameters.put("concessionPolicy",profileCreationTrainingPartnerCenterDetailsDto.getConcessionPolicy());
				parameters.put("safeCustodyOfStudentDocuments",profileCreationTrainingPartnerCenterDetailsDto.getSafeCustodyOfStudentDocuments());
				parameters.put("studentAgreementWithInstitution",profileCreationTrainingPartnerCenterDetailsDto.getStudentAgreementWithInstitution());
				parameters.put("remarksOnStudentAdmissionDetails",profileCreationTrainingPartnerCenterDetailsDto.getRemarksOnStudentAdmissionDetails());
				parameters.put("isActive",profileCreationTrainingPartnerCenterDetailsDto.getIsActive());
				
				status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdateIntoTrainingPartnerCenterLevelDetails(),parameters);

			}
			catch(Exception e)
			{
				LOGGER.error("An Exception occured while updating traing center details" + e);
				status = -1;
			}
			return status;
		}
	
		
		/*
		 * Update data in Institute grant Table
		 */
	
		public int updateIntoTrainingPartnerInstituteGrant(ProfileCreationTrainingPartnerInstituteGrantDto profileCreationTrainingPartnerInstituteGrantDto)
		{
			int status = 0;
			try{
				Map<String, Object> parameters=new HashMap<String, Object>();

				parameters.put("instituteGrantId",profileCreationTrainingPartnerInstituteGrantDto.getInstituteGrantId());
				parameters.put("trainingPartnerRegistrationId",profileCreationTrainingPartnerInstituteGrantDto.getTrainingPartnerRegistrationId());
				parameters.put("nameOfMinistry",profileCreationTrainingPartnerInstituteGrantDto.getNameOfMinistry().toUpperCase());
				parameters.put("natureOfWork",profileCreationTrainingPartnerInstituteGrantDto.getNatureOfWork());
				parameters.put("remarks",profileCreationTrainingPartnerInstituteGrantDto.getRemarks());
				parameters.put("isActive",profileCreationTrainingPartnerInstituteGrantDto.getIsActive());
				
				status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdateIntoTrainingPartnerInstituteGrant(),parameters);

			}
			catch(Exception e)
			{
				LOGGER.error("An exception occured while updating institute grant in database " + e);
				status = -1;
			}
			return status;
	
		}
	
		
		/*
		 * Update data in Institute Recognition Table
		 */
	
		public int updateIntoTrainingPartnerInstituteRecognition(ProfileCreationTrainingPartnerInstituteRecognitionDto profileCreationTrainingPartnerInstituteRecognitionDto)
		{
			int status =0;
			try{
				Map<String, Object> parameters=new HashMap<String, Object>();
				
				parameters.put("instituteRecognitionId", profileCreationTrainingPartnerInstituteRecognitionDto.getInstituteRecognitionId());
				parameters.put("trainingPartnerRegistrationId",profileCreationTrainingPartnerInstituteRecognitionDto.getTrainingPartnerRegistrationId());
				parameters.put("nameOfRecognizingBody",profileCreationTrainingPartnerInstituteRecognitionDto.getNameOfRecognizingBody().toUpperCase());
				parameters.put("recognitionNumber",profileCreationTrainingPartnerInstituteRecognitionDto.getRecognitionNumber());
				parameters.put("yearOfRecognition",profileCreationTrainingPartnerInstituteRecognitionDto.getYearOfRecognition());
				parameters.put("validityOfRecognition",profileCreationTrainingPartnerInstituteRecognitionDto.getValidityOfRecognition());
				parameters.put("isActive",profileCreationTrainingPartnerInstituteRecognitionDto.getIsActive());
				
				status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdateIntoTrainingPartnerInstituteRecognition(),parameters);

			}
			catch(Exception e)
			{
				LOGGER.error("An exception occured while updating recognitions of training partner");
				
				status = -1;
			}
			return status;
		}
	
	
		/*
		 * Update data in Training Partner Prior Experience In Skill Training Table
		 */
		
		public int updateIntoTrainingPartnerPriorExperienceInSkillTraining(ProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto profileCreationTrainingPartnerPriorExperienceInSkillTrainingDto)
		{
			int status = 0;
			try{
				Map<String, Object> parameters=new HashMap<String, Object>();

				parameters.put("priorExperienceInSkillTrainingId",profileCreationTrainingPartnerPriorExperienceInSkillTrainingDto.getPriorExperienceInSkillTrainingId());
				parameters.put("trainingPartnerRegistrationId",profileCreationTrainingPartnerPriorExperienceInSkillTrainingDto.getTrainingPartnerRegistrationId());
				parameters.put("courseName",profileCreationTrainingPartnerPriorExperienceInSkillTrainingDto.getCourseName().toUpperCase());
				parameters.put("numberOfBatchesPerYear",profileCreationTrainingPartnerPriorExperienceInSkillTrainingDto.getNumberOfBatchesPerYear());
				parameters.put("numberOfStudentsInEachBatch",profileCreationTrainingPartnerPriorExperienceInSkillTrainingDto.getNumberOfStudentsInEachBatch());
				parameters.put("isActive",profileCreationTrainingPartnerPriorExperienceInSkillTrainingDto.getIsActive());
				
				status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdateIntoTrainingPartnerPriorExperienceInSkillTraining(),parameters);

			}catch(Exception e)
			{
				LOGGER.error("An exception occured while updating Prior experience of training partner " + e);
				status = -1;
			}
			return status;
	
		}
		
		
		
		

		/*
		 * Update data in Training Partner ManagementAndStaffAndOfficialsDetails Table 
		 */
		
		public int updateIntoTrainingPartnerManagementAndStaffAndOfficialsDetails(ProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto)
		{
			int status = 0;
			try{
				Map<String, Object> parameters=new HashMap<String, Object>();

				parameters.put("managementAndStaffId",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getManagementAndStaffId());
				parameters.put("trainingPartnerRegistrationId",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getTrainingPartnerRegistrationId());
				//parameters.put("trainingPartnerCenterId",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getTrainingPartnerCenterId());
				//parameters.put("type",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getType());
				parameters.put("name",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getName());
				parameters.put("designation",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getDesignation());
				parameters.put("emailId",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getEmailId().toLowerCase());
				parameters.put("contactNumber",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getContactNumber());
				parameters.put("educationalQualification",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getEducationalQualification());
				//parameters.put("regularOrVisiting",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getRegularOrVisiting());
				parameters.put("experience",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getExperience());
				parameters.put("cVPath",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getcVPath());
				//parameters.put("certificatePath",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getCertificatePath());
				parameters.put("isActive",profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.getIsActive());
				
				status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdateIntoTrainingPartnerManagementAndStaffAndOfficialsDetails(),parameters);

			}
			catch(Exception e)
			{
				LOGGER.error("An exception occured while updating management staff details of training partner " + e);
				status = -1;
			}
			return status;
		}
		
		/*
		 * Update cvPath & certificatePath in TrainingPartnerManagementAndStaffAndOfficialsDetails
		 */
	
		public int updatePathsIntoTrainingPartnerManagementAndStaffAndOfficialsDetails(HashMap<String, String> setPaths,int managementAndStaffId,String trainingPartnerCenterId)
		{
			int status = 0;
			try{
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("cVPath", setPaths.get("cVPath"));
			parameters.put("certificatePath", setPaths.get("certificatePath"));
			parameters.put("trainingPartnerCenterId", trainingPartnerCenterId);
			parameters.put("managementAndStaffId", String.valueOf(managementAndStaffId));
			
			
			status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdatePathsIntoTrainingPartnerManagementAndStaffAndOfficialsDetails(), parameters);
			}
			catch(Exception e)
			{
				LOGGER.error("An exception occured while updating office details of traing partner management staff details " + e);
				status = -1;
			}
			return status;
		}

	
		/*
		 * Update Path's in TrainingPartnerManagementAndStaffAndOfficialsDetails
		 */
	
		public int updatePathsIntoTrainingPartnerCenterLevelDetails(HashMap<String, String> setPaths,int trainingPartnerCenterId)
		{
			int status = 0;
			try{
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("classroomPicsPath", setPaths.get("classroomPicsPath"));
			parameters.put("labPicsPath", setPaths.get("labPicsPath"));
			parameters.put("workshopPicsPath", setPaths.get("workshopPicsPath"));
			parameters.put("mandatoryToolKitAnnexurePath", setPaths.get("mandatoryToolKitAnnexurePath"));
			parameters.put("mandatoryToolKitPicsPath", setPaths.get("mandatoryToolKitPicsPath"));
			parameters.put("trainingPartnerCenterId", String.valueOf(trainingPartnerCenterId));
			status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdatePathsIntoTrainingPartnerCenterLevelDetails(), parameters);
			}
			catch(Exception e)
			{
				LOGGER.error("An exception occured while updating path of training partner management staff details");
				status = -1;
			}
			return status;
		}
		
		/*Method to update pan number path*/
		
		public int updatePanPath(String panPath, int applicationId)
		{
			int status = 0;
			try
			{
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("panNumberPath" , panPath);
				parameters.put("applicationId" , applicationId);
				status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdatePanPath(), parameters);
				LOGGER.debug("Value of status in panNumber update " + status);
			}
			catch(Exception e)
			{
				status = -2;
				LOGGER.error("An exception occured while updating pan path of training partner " + e);
			}
			return status;
		}
		
		/*
		 * Method to update NSDC certificate path*/
		public int updateNsdcCertificatePath(String nsdcCertifiactePath, int applicationId)
		{
			int status = 0;
			try
			{
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("nSDCFundedCertificatePath" , nsdcCertifiactePath);
				parameters.put("nSDCFunded" , "Yes" );
				parameters.put("applicationId" , applicationId);
				status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdateNSDCCertificatePath(), parameters);
			}
			catch(Exception e)
			{
				status = -2;
				LOGGER.error("An exception occured while updating nsdc certificate path of training partner " + e);
			}
			return status;
		}
		
		/*
		 * Method to update self owned institution annexure path*/
		public int updateSelfOwnedAnnexure(String selfOwnedAnnexurePath, int applicationId)
		{
			int status = 0;
			try
			{
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("selfOwnedInstitutionAnnexurePath" , selfOwnedAnnexurePath);
				parameters.put("applicationId" , applicationId);
				status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdateSelfOwnedAnnexurePath(), parameters);
			}
			catch(Exception e)
			{
				status = -2;
				LOGGER.error("An exception occured while updating self owned institution path of training partner " + e);
			}
			return status;
		}
		
		/*
		 * Method to update Franchisee Annexure Path*/
		public int updateFrnachiseeAnnexurePath(String franchiseeAnnexurePath, int applicationId)
		{
			int status = 0;
			try
			{
				Map<String,Object> parameters = new HashMap<String,Object>();
				parameters.put("franchiseeAnnexurePath", franchiseeAnnexurePath);
				parameters.put("applicationId" , applicationId);
				status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdateFranchiseeAnnexurePath(), parameters);
			}
			catch(Exception e)
			{
				status = -2;
				LOGGER.error("An exception occured while updating franchise model annexure path" + e);
			}
			return status;
		}
		
		/*
		 * Method to update Mobile Annexure Path*/
		public int updateMobileAnnexurePath(String mobileTrainingInstitutionAnnexurePath, int applicationId)
		{
			int status = 0;
			try
			{
				Map<String,Object> parameters = new HashMap<String,Object>();
				parameters.put("mobileTrainingInstitutionAnnexurePath", mobileTrainingInstitutionAnnexurePath);
				parameters.put("applicationId" , applicationId);
				status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdateMobileAnnexurePath(), parameters);
			}
			catch(Exception e)
			{
				status = -2;
				LOGGER.error("An exception occured while updating mobile model annexure path" + e);
			}
			return status;
		}
		
		/*
		 * Method to update Tan number path*/
		
		public int updateTanPath(String tanPath, int applicationId)
		{
			int status = 0;
			try
			{
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("tanNumberPath" , tanPath);
				parameters.put("applicationId" , applicationId);
				status = getJdbcTemplate().update(profileCreationTrainingPartnerConfigSql.getUpdateTanPath(), parameters);
				LOGGER.debug("Value of status in panNumber update " + status);
			}
			catch(Exception e)
			{
				status = -2;
				LOGGER.error("An exception occured while updating tan path of training partner " + e);
			}
			return status;
		}
		
		
		
	
}
