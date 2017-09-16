package com.skill.India.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skill.India.common.ReadApplicationConstants;
import com.skill.India.common.SessionUserUtility;
import com.skill.India.dao.GetUserRoleDao;
import com.skill.India.dao.ProfileCreationTPABCommonDao;
import com.skill.India.dao.ProfileCreationTrainingPartnerInsertDataDao;
import com.skill.India.dao.ProfileCreationTrainingPartnerUpdateDataDao;
import com.skill.India.dto.ProfileCreationAssessmentBodyAffiliationDetailsDto;
import com.skill.India.dto.ProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto;
import com.skill.India.dto.ProfileCreationAssessmentBodyRecognitionsDto;
import com.skill.India.dto.ProfileCreationAssessmentBodyRegionalOfficeDetailsDto;
import com.skill.India.dto.ProfileCreationAssessmentBodyRegistrationDetailsDto;
import com.skill.India.dto.ProfileCreationAssessmentStaffDetailsDto;
import com.skill.India.dto.ProfileCreationAssessmentsExperienceInTechnicalDomainDto;
import com.skill.India.dto.ProfileCreationTrainingPartnerCenterDetailsDto;
import com.skill.India.dto.ProfileCreationTrainingPartnerInstituteGrantDto;
import com.skill.India.dto.ProfileCreationTrainingPartnerInstituteRecognitionDto;
import com.skill.India.dto.ProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto;
import com.skill.India.dto.ProfileCreationTrainingPartnerOrganizationDetailsDto;
import com.skill.India.dto.ProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto;

@Service
public class ProfileCreationSaveAsDraftAndSubmitService {

	@Autowired
	private SessionUserUtility sessionUserUtility;
	
	@Autowired
	private ProfileCreationTPABCommonDao profileCreationTPABCommonDao;
	
	@Autowired
	private GetUserRoleDao getUserRoleDao;
	
	@Autowired
	private ReadApplicationConstants readApplicationConstants;
	
	@Autowired
	private ProfileCreationSaveFile profileCreationSaveFile;
	
	@Autowired
	private ProfileCreationTrainingPartnerInsertDataDao profileCreationTrainingPartnerInsertDataDao;
	
	@Autowired
	private ProfileCreationTrainingPartnerUpdateDataDao profileCreationTrainingPartnerUpdateDataDao;
	
	public String profileCreationSaveAsDraftAndSubmit(String type,HashMap<String, HashMap<String, HashMap<String, String>>> userData,HashMap<String, HashMap<String, HashMap<String, MultipartFile>>> userUploads,HashMap<String, HashMap<String, HashMap<String, String>>> userDeletes)
	{
		try{
			/*
			 * Inserting or updating Application Table
			 */
			
		int userExists=sessionUserUtility.getApplicationId(sessionUserUtility.getSessionMangementfromSession().getUsername());
		
		if(userExists==-1)
		{
			/*
			 * User is logged in the system for first time
			 */
			int applicationTableStatus=profileCreationTPABCommonDao.insertIntoApplication(type);
			
			if(applicationTableStatus ==-1)
			{
				/*
				 * Error in inserting record in ApplicationTable
				 */
				return null;
			}
		}
		else if(userExists==-2)
		{
			/*
			 * An error occurred while getting applicationId 
			 */
			return null;
		}
		else
		{
			/*
			 * User already visited profile creation page once
			 */
			int applicationTableStatus=profileCreationTPABCommonDao.updateIntoApplication(type);
			if(applicationTableStatus ==-1)
			{
				/*
				 * Error in updating record in ApplicationTable
				 */
				return null;
			}
		}
		
		
		/*
		 * Application Table operation Ends here
		 */
		
		/*
		 * Getting applicationId 
		 */
		
		int applicationId = sessionUserUtility.getApplicationId(sessionUserUtility.getSessionMangementfromSession().getUsername());
		
		if(applicationId==-1)
		{
			/*
			 * ApplicationId corresponding userId doesn't exists
			 * (this case must not arise)
			 */
			return null;
		}
		else if(applicationId==-2)
		{
			/*
			 * Error in fetching ApplicationId  
			 */
			return null;
		}
		
		/*
		 * End of getting ApplicationId
		 */
		
		/*
		 * Getting UserRole
		 */
		
		String userRole=getUserRoleDao.getUserRole();
		if(userRole.equalsIgnoreCase("TP"))
		{
			/*
			 * Iterating HashMap to set TP different dto's 
			 */
			
			HashMap<String, HashMap<String, String>> trainingPartnerOrganizationDetails=userData.get("TrainingPartnerOrganizationDetails");
			HashMap<String, HashMap<String, MultipartFile>> trainingPartnerOrganizationDetailsFiles=userUploads.get("TrainingPartnerOrganizationDetails");
			
			HashMap<String, HashMap<String, String>> trainingPartnerCenterLevelDetails=userData.get("TrainingPartnerCenterLevelDetails");
			HashMap<String, HashMap<String, MultipartFile>> trainingPartnerCenterLevelDetailsFiles=userUploads.get("TrainingPartnerCenterLevelDetails");
			HashMap<String, HashMap<String, String>> trainingPartnerCenterLevelDetailsDeletes=userDeletes.get("TrainingPartnerCenterLevelDetails");
			
			HashMap<String, HashMap<String, String>> trainingPartnerInstituteGrant=userData.get("TrainingPartnerInstituteGrant");
			HashMap<String, HashMap<String, String>> trainingPartnerInstituteGrantDeletes=userDeletes.get("TrainingPartnerInstituteGrant");
			
			HashMap<String, HashMap<String, String>> trainingPartnerInstituteRecognition=userData.get("TrainingPartnerInstituteRecognition");
			HashMap<String, HashMap<String, String>> trainingPartnerInstituteRecognitionDeletes=userDeletes.get("TrainingPartnerInstituteRecognition");
			
			HashMap<String, HashMap<String, String>> trainingPartnerPriorExperienceInSkillTraining=userData.get("TrainingPartnerPriorExperienceInSkillTraining");
			HashMap<String, HashMap<String, String>> trainingPartnerPriorExperienceInSkillTrainingDeletes=userDeletes.get("TrainingPartnerPriorExperienceInSkillTraining");
			 
			HashMap<String, HashMap<String, String>> trainingPartnerManagementAndStaffAndOfficialsDetails=userData.get("TrainingPartnerManagementAndStaffAndOfficialsDetails");
			HashMap<String, HashMap<String, MultipartFile>> trainingPartnerManagementAndStaffAndOfficialsDetailsFiles=userUploads.get("TrainingPartnerManagementAndStaffAndOfficialsDetails");
			HashMap<String, HashMap<String, String>> trainingPartnerManagementAndStaffAndOfficialsDetailsDeletes=userDeletes.get("TrainingPartnerManagementAndStaffAndOfficialsDetails");
			
			/*
			 * Setting different Dto's
			 */
			
			/*
			 * ProfileCreationTrainingPartnerOrganizationDetailsDto Setting starts
			 */
			
			ArrayList<ProfileCreationTrainingPartnerOrganizationDetailsDto> trainingPartnerOrganizationDetailsList = new ArrayList<ProfileCreationTrainingPartnerOrganizationDetailsDto>();
			
			for(Map.Entry<String, HashMap<String, String>> trainingPartnerOrganizationDetailsIterator:trainingPartnerOrganizationDetails.entrySet())
			{
				/*
				 * Setting the incoming data in  respective DTO's ProfileCreationTrainingPartnerOrganizationDetailsDto
				 */
				HashMap<String, String> setProfileCreationTrainingPartnerOrganizationDetailsDto= new HashMap<String, String>();
				setProfileCreationTrainingPartnerOrganizationDetailsDto=trainingPartnerOrganizationDetailsIterator.getValue();
				trainingPartnerOrganizationDetailsList.add(new ProfileCreationTrainingPartnerOrganizationDetailsDto(
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("trainingPartnerRegistrationId"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("applicationId"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("organizationName"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("sPOCName"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("address"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("city"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("state"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("pincode"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("mobileNumber"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("alternateMobileNumber"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("landlineNumber"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("alternateLandlineNumber"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("faxNumber"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("websites"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("yearOfEstablishment"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("qualificationPacks"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("qualificationPacksAnnexurePath"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("nSDCFunded"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("nSDCFundedCertificatePath"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("mediumOfInstructions"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("selfOwnedInstitution"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("selfOwnedInstitutionAnnexurePath"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("franchiseOwnedInstitution"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("franchiseOwnedInstitutionAnnexurePath"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("mobileTrainingInstitution"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("mobileTrainingInstitutionAnnexurePath"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("panNumber"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("panNumberPath"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("tanNumber"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("tanNumberPath"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("turnOverOfInstitution"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("turnOverOfInstitutionBalanceSheetPath"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("instituteReceivedAnyGrant"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("instituteReceivedAnyRecognition"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("priorExperienceOfInstitutionInSkillDevelopment"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("anyPriorExperienceOfInstitutionInSkillTraining"),
						setProfileCreationTrainingPartnerOrganizationDetailsDto.get("trainingStaffDetailsAnnexurePath")
						));
			}
			
			/*
			 * End of setting of ProfileCreationTrainingPartnerOrganizationDetailsDto Setting
			 */
			
			
			/*
			 * ProfileCreationTrainingPartnerCenterLevelDetailsDto Setting starts 
			 */
			
			ArrayList<ProfileCreationTrainingPartnerCenterDetailsDto> trainingPartnerCenterDetailsList = new ArrayList<ProfileCreationTrainingPartnerCenterDetailsDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationTrainingPartnerCenterDetailsIterator : trainingPartnerCenterLevelDetails.entrySet())
			{
				/*
				 * Setting the incoming data in  respective DTO's ProfileCreationTrainingPartnerCenterDetailsDto
				 */
				HashMap<String, String> setProfileCreationTrainingPartnerCenterDetailsDto=new HashMap<String, String>();
				setProfileCreationTrainingPartnerCenterDetailsDto=profileCreationTrainingPartnerCenterDetailsIterator.getValue();
				trainingPartnerCenterDetailsList.add(new ProfileCreationTrainingPartnerCenterDetailsDto(
						setProfileCreationTrainingPartnerCenterDetailsDto.get("trainingPartnerCenterId"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("trainingPartnerRegistrationId"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("nameOfCenter"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfPermanentOfficeManager"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOftemporaryOfficeManager"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfPermanentOfficeStaff"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfTemporaryOfficeStaff"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfPermanentLabAssistants"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfTemporaryLabAssistants"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfPermanentAccountants"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfTemporaryAccountants"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfPermanentSupportStaff"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfTemporarySupportStaff"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfPermanentOtherEmployees"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfTemporaryOtherEmployees"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("areaOfInstitute"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("buildingType"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("sizeOfClassrooms"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("classroomPicsPath"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfClassrooms"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("sizeOfLabs"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("labPicsPath"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfLabs"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("sizeOfWorkshops"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("workshopPicsPath"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfWorkshops"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("mandatoryToolKitpresent"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("mandatoryToolKitAnnexurePath"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("mandatoryToolKitPicsPath"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("safeDrinkingWater"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("powerBackup"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("separateToilets"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("transportFacility"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("presenceOfLibrary"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfTechnicalBooks"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfNonTechnicalBooks"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfMagazines"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("numberOfDailies"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("remarksOnInfrastructureDetails"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("sufficientClassroomIlluminationLevel"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("sufficientClassroomVentilationLevel"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("sufficientCenterCleanliness"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("centerWeatherProtected"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("remarksOnLearningEnviornment"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("printedBrochureOrProspectus"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("documentedPolicyAndProcedures"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("concessionPolicy"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("safeCustodyOfStudentDocuments"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("studentAgreementWithInstitution"),		
						setProfileCreationTrainingPartnerCenterDetailsDto.get("remarksOnStudentAdmissionDetails")
						));
			}
			
			/*
			 * End of Setting of ProfileCreationTrainingPartnerCenterDetailsDto
			 */
			
			/*
			 * ProfileCreationTrainingPartnerInstituteGrantDto Setting Starts
			 */
			
			ArrayList<ProfileCreationTrainingPartnerInstituteGrantDto> trainingPartnerInstituteGrantList = new ArrayList<ProfileCreationTrainingPartnerInstituteGrantDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationTrainingPartnerInstituteGrantIterator : trainingPartnerInstituteGrant.entrySet())
			{
				/*
				 * Setting the incoming data in  respective DTO's ProfileCreationTrainingPartnerInstituteGrantDto
				 */
				HashMap<String, String> setProfileCreationTrainingPartnerInstituteGrantDto=new HashMap<String, String>();
				setProfileCreationTrainingPartnerInstituteGrantDto=profileCreationTrainingPartnerInstituteGrantIterator.getValue();
				trainingPartnerInstituteGrantList.add(new ProfileCreationTrainingPartnerInstituteGrantDto(
						setProfileCreationTrainingPartnerInstituteGrantDto.get("instituteGrantId"),
						setProfileCreationTrainingPartnerInstituteGrantDto.get("trainingPartnerRegistrationId"),
						setProfileCreationTrainingPartnerInstituteGrantDto.get("nameOfMinistry"),
						setProfileCreationTrainingPartnerInstituteGrantDto.get("natureOfWork"),
						setProfileCreationTrainingPartnerInstituteGrantDto.get("remarks")
						));	
			}
			
			/*
			 * Setting of ProfileCreationTrainingPartnerInstituteGrantDto ends
			 */
			
			
			/*
			 *ProfileCreationTrainingPartnerInstituteRecognitionDto Setting Starts 
			 */
			
			ArrayList<ProfileCreationTrainingPartnerInstituteRecognitionDto> trainingPartnerInstituteRecognitionList=new ArrayList<ProfileCreationTrainingPartnerInstituteRecognitionDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationTrainingPartnerInstituteRecognitionIterator : trainingPartnerInstituteRecognition.entrySet())
			{
				HashMap<String, String> setProfileCreationTrainingPartnerInstituteRecognitionDto = new HashMap<String, String>();
				setProfileCreationTrainingPartnerInstituteRecognitionDto=profileCreationTrainingPartnerInstituteRecognitionIterator.getValue();
				trainingPartnerInstituteRecognitionList.add(new ProfileCreationTrainingPartnerInstituteRecognitionDto(
						setProfileCreationTrainingPartnerInstituteRecognitionDto.get("instituteRecognitionId"),
						setProfileCreationTrainingPartnerInstituteRecognitionDto.get("trainingPartnerRegistrationId"),
						setProfileCreationTrainingPartnerInstituteRecognitionDto.get("nameOfRecognizingBody"),
						setProfileCreationTrainingPartnerInstituteRecognitionDto.get("recognitionNumber"),
						setProfileCreationTrainingPartnerInstituteRecognitionDto.get("yearOfRecognition"),
						setProfileCreationTrainingPartnerInstituteRecognitionDto.get("validityOfRecognition")
						));
			}
			
			/*
			 * Setting of ProfileCreationTrainingPartnerInstituteRecognitionDto ends 
			 */
			
			/*
			 * ProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto Setting Starts
			 */
			
			ArrayList<ProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto> trainingPartnerPriorExperienceInSkillTrainingList = new ArrayList<ProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationTrainingPartnerPriorExperienceInSkillTrainingIterator : trainingPartnerPriorExperienceInSkillTraining.entrySet())
			{
			HashMap<String, String> setProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto = new HashMap<String, String>();
			setProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto=profileCreationTrainingPartnerPriorExperienceInSkillTrainingIterator.getValue();
			trainingPartnerPriorExperienceInSkillTrainingList.add(new ProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto(
					setProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto.get("priorExperienceInSkillTrainingId"),
					setProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto.get("trainingPartnerRegistrationId"),
					setProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto.get("courseName"),
					setProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto.get("numberOfBatchesPerYear"),
					setProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto.get("numberOfStudentsInEachBatch")	
					));
			}
			
			/*
			 * Setting of ProfileCreationTrainingPartnerPriorExperienceInSkillTrainingDto Ends
			 */
			
			
			/*
			 * ProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto Setting Starts
			 */
			
			ArrayList<ProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto> trainingPartnerManagementAndStaffAndOfficialsDetailsList = new ArrayList<ProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsIterator : trainingPartnerManagementAndStaffAndOfficialsDetails.entrySet())
			{
			HashMap<String, String> setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto = new HashMap<String, String>();
			setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto=profileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsIterator.getValue();
			trainingPartnerManagementAndStaffAndOfficialsDetailsList.add(new ProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto(
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("managementAndStaffId"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("trainingPartnerRegistrationId"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("trainingPartnerCenterId"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("type"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("name"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("designation"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("emailId"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("contactNumber"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("educationalQualification"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("regularOrVisiting"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("experience"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("cVPath"),
					setProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto.get("certificatePath")					
					));
			}
			
			/*
			 * Setting of ProfileCreationTrainingPartnerManagementAndStaffAndOfficialsDetailsDto Ends
			 */
			
			/*
			 * Setting of Dto's Ends here
			 */
			
			
			if(userRole.equalsIgnoreCase("TP"))
			{
				File createTrainingPartnerFolder = new File(readApplicationConstants.getProfileCreationTrainingPartnerFolder()+"//"+ applicationId);
				
				File createInstituteGrantFolder = new File(createTrainingPartnerFolder.getAbsolutePath()+"//InstituteGrant");
				File createDirectorsAndManagementTeamMembersFolder = new File(createTrainingPartnerFolder.getAbsolutePath()+"//DirectorsAndManagementTeamMembers");
				File createTrainingStaffFolder = new File(createTrainingPartnerFolder.getAbsolutePath()+"//TrainingStaff");
				File createAnnexuresFolder = new File(createTrainingPartnerFolder.getAbsolutePath()+"//Annexures");
				File createTrainingStaffCVFolder = new File(createTrainingStaffFolder.getAbsolutePath()+"//CV");
				File createTrainingStaffCertificateFolder = new File(createTrainingStaffFolder.getAbsolutePath()+"//Certificate");
				File createCentersFolder = new File(createTrainingPartnerFolder.getAbsolutePath()+"//Centers");
				
				if(!createTrainingPartnerFolder.exists())
				{
					
					if(!createTrainingPartnerFolder.mkdirs())
					{
					// directory creation failed 
					}
					if(!createInstituteGrantFolder.mkdir())
					{
					// directory creation failed
					}
					if(!createDirectorsAndManagementTeamMembersFolder.mkdir())
					{
					// directory creation failed
					}		
					if(!createTrainingStaffFolder.mkdir())
					{
					// directory creation failed
					}
					if(!createAnnexuresFolder.mkdir())
					{
					// directory creation failed
					}
					if(!createTrainingStaffCVFolder.mkdir())
					{
					// directory creation failed
					}
					if(!createTrainingStaffCertificateFolder.mkdir())
					{
					// directory creation failed	
					}
					if(!createCentersFolder.mkdir())
					{
					// directory creation failed	
					}
				}
				else
				{
					// Do whatever is required Folder with name of applicationId is present 
				}
				
				/*
				 * End of Creating Folder Structure for Organization level details for saving files 
				 */
				
				
				/*
				 * Iterating Arraylist trainingPartnerOrganizationDetailsList  
				 */
				
				for(int temp=0;temp<trainingPartnerOrganizationDetailsList.size();temp++)
				{
					/*
					 * Set paths of qualificationPacksAnnexurePath,nSDCFundedCertificatePath
					 * selfOwnedInstitutionAnnexurePath, franchiseOwnedInstitutionAnnexurePath
					 * mobileTrainingInstitutionAnnexurePath, panNumberPath, tanNumberPath
					 * turnOverOfInstitutionBalanceSheetPath
					 * 
					 * Iterate trainingPartnerOrganizationDetailsFiles and save files
					 */
					
					HashMap<String, MultipartFile> getAllFiles = new HashMap<String, MultipartFile>();
					getAllFiles = trainingPartnerOrganizationDetailsFiles.get("Record"+temp++);
					
					/*
					 * Declaring HashMap to set paths of various files to store in DB 
					 */
					
					HashMap<String, String> setPaths = new HashMap<String, String>();
					
					
					/*
					 * Now start extracting Pan Number from getAllFiles hashmap
					 */
					
					if(getAllFiles.get("PanNumber")==null)
					{
						/*
						 * No file is coming for Pan Number
						 */
						
						setPaths.put("panNumberPath",trainingPartnerOrganizationDetailsList.get(temp).getPanNumberPath());
					}
					else
					{
						/*
						 * Setting path where to save Pan number file 
						 */
						
						Path path=Paths.get(createTrainingPartnerFolder.getAbsolutePath()+"//PanNumber.pdf");
						
						/*
						 * Call ProfileCreationSaveFile with actual parameters to save file 
						 */
						
						String status=profileCreationSaveFile.saveFile(getAllFiles.get("PanNumber"), path);
						
						if(status==null)
						{
							/*
							 * Error Saving file on system
							 */
						}
						
						/*
						 * Setting panNumberPath 
						 */
						
						setPaths.put("panNumberPath",path.toString());
						
					}
					
					/*
					 * Extracting of Pan Number Ends 
					 */
				
					/*
					 * Now start extracting Tan Number from getAllFiles hashmap
					 */
					
					if(getAllFiles.get("TanNumber")==null)
					{
						/*
						 * No file is coming for Tan Number
						 */
						
						setPaths.put("tanNumberPath",trainingPartnerOrganizationDetailsList.get(temp).getTanNumberPath());
					}
					else
					{
						/*
						 * Setting path where to save Tan number file 
						 */
						
						Path path=Paths.get(createTrainingPartnerFolder.getAbsolutePath()+"//TanNumber.pdf");
						
						/*
						 * Call ProfileCreationSaveFile with actual parameters to save file 
						 */
						
						String status=profileCreationSaveFile.saveFile(getAllFiles.get("TanNumber"), path);
						
						if(status==null)
						{
							/*
							 * Error Saving file on system
							 */
						}
						
						/*
						 * Set tanNumberPath accordingly 
						 */
						
						setPaths.put("tanNumberPath",path.toString());
					}
					
					/*
					 * Extracting of Tan Number Ends 
					 */
					
					
					/*
					 * Now start extracting Turn Over from getAllFiles hashmap
					 */
					
					if(getAllFiles.get("TurnOver")==null)
					{
						/*
						 * No file is coming for TurnOver
						 */
						
						setPaths.put("turnOverOfInstitutionBalanceSheetPath",trainingPartnerOrganizationDetailsList.get(temp).getTurnOverOfInstitutionBalanceSheetPath());
					}
					else
					{
						/*
						 * Setting path where to save TurnOver file 
						 */
						
						Path path=Paths.get(createTrainingPartnerFolder.getAbsolutePath()+"//TurnOver.pdf");
						
						/*
						 * Call ProfileCreationSaveFile with actual parameters to save file 
						 */
						
						String status=profileCreationSaveFile.saveFile(getAllFiles.get("TurnOver"), path);
						
						if(status==null)
						{
							/*
							 * Error Saving file on system
							 */
						}
						
						
						/*
						 * Set turnOverOfInstitutionBalanceSheetPath accordingly 
						 */
						
						setPaths.put("turnOverOfInstitutionBalanceSheetPath", path.toString());
					}
					
					/*
					 * Extracting of TurnOver Ends 
					 */
					
					
					/*
					 * Now start extracting QualificationPackAnnexure from getAllFiles hashmap
					 */
					
					if(getAllFiles.get("QualificationPacksAnnexure")==null)
					{
						/*
						 * No file is coming for QualificationPackAnnexure
						 */
						
						setPaths.put("qualificationPacksAnnexurePath",trainingPartnerOrganizationDetailsList.get(temp).getQualificationPacksAnnexurePath());
					}
					else
					{
						/*
						 * Setting path where to save QualificationPacksAnnexure file 
						 */
						
						Path path=Paths.get(createAnnexuresFolder.getAbsolutePath()+"//QualificationPacksAnnexure.pdf");
						
						/*
						 * Call ProfileCreationSaveFile with actual parameters to save file 
						 */
						String status=profileCreationSaveFile.saveFile(getAllFiles.get("QualificationPacksAnnexure"), path);
						
						if(status==null)
						{
							/*
							 * Error Saving file on system
							 */
						}
						
						/*
						 * Set qualificationPacksAnnexurePath accordingly 
						 */
						
						setPaths.put("qualificationPacksAnnexurePath", path.toString() );
					}
					
					/*
					 * Extracting of QualificationPackAnnexure Ends 
					 */
					
					
					/*
					 * Now start extracting NSDCFundedCertificate from getAllFiles hashmap
					 */
					
					if(getAllFiles.get("NSDCFundedCertificate")==null)
					{
						/*
						 * No file is coming for Tan Number
						 */
						
						setPaths.put("nSDCFundedCertificatePath",trainingPartnerOrganizationDetailsList.get(temp).getnSDCFundedCertificatePath());
					}
					else
					{
						/*
						 * Setting path where to save NSDCFundedCertificate file 
						 */
						
						Path path=Paths.get(createTrainingPartnerFolder.getAbsolutePath()+"//NSDCFundedCertificate.pdf");
						
						/*
						 * Call ProfileCreationSaveFile with actual parameters to save file 
						 */
						
						String status=profileCreationSaveFile.saveFile(getAllFiles.get("NSDCFundedCertificate"), path);
						
						if(status==null)
						{
							/*
							 * Error Saving file on system
							 */
						}
						
						/*
						 * Set nSDCFundedCertificatePath accordingly 
						 */
						
						setPaths.put("nSDCFundedCertificatePath", path.toString());
					}
					
					/*
					 * Extracting of NSDCFundedCertificate Ends 
					 */
					
					/*
					 * Now start extracting SelfOwnedInstitutionAnnexure from getAllFiles hashmap
					 */
					
					if(getAllFiles.get("SelfOwnedInstitutionAnnexure")==null)
					{
						/*
						 * No file is coming for SelfOwnedInstitutionAnnexure
						 */
						
						setPaths.put("selfOwnedInstitutionAnnexurePath",trainingPartnerOrganizationDetailsList.get(temp).getSelfOwnedInstitutionAnnexurePath());
					}
					else
					{
						/*
						 * Setting path where to save SelfOwnedInstitutionAnnexure file 
						 */
						
						Path path=Paths.get(createAnnexuresFolder.getAbsolutePath()+"//SelfOwnedInstitutionAnnexure.pdf");
						
						/*
						 * Call ProfileCreationSaveFile with actual parameters to save file 
						 */
						
						String status=profileCreationSaveFile.saveFile(getAllFiles.get("SelfOwnedInstitutionAnnexure"), path);
						
						if(status==null)
						{
							/*
							 * Error Saving file on system
							 */
						}
						
						/*
						 * Set selfOwnedInstitutionAnnexurePath accordingly 
						 */
						
						setPaths.put("selfOwnedInstitutionAnnexurePath", path.toString());
					}
					
					/*
					 * Extracting of SelfOwnedInstitutionAnnexure Ends 
					 */
					
					/*
					 * Now start extracting FranchiseOwnedInstitutionAnnexure from getAllFiles hashmap
					 */
					
					if(getAllFiles.get("FranchiseOwnedInstitutionAnnexure")==null)
					{
						/*
						 * No file is coming for FranchiseOwnedInstitutionAnnexure
						 */
						
						setPaths.put("franchiseOwnedInstitutionAnnexurePath",trainingPartnerOrganizationDetailsList.get(temp).getFranchiseOwnedInstitutionAnnexurePath());
					}
					else
					{
						/*
						 * Setting path where to save FranchiseOwnedInstitutionAnnexure file 
						 */
						
						Path path=Paths.get(createAnnexuresFolder.getAbsolutePath()+"//FranchiseOwnedInstitutionAnnexure.pdf");
						
						/*
						 * Call ProfileCreationSaveFile with actual parameters to save file 
						 */
						String status=profileCreationSaveFile.saveFile(getAllFiles.get("FranchiseOwnedInstitutionAnnexure"), path);
						
						if(status==null)
						{
							/*
							 * Error Saving file on system
							 */
						}
						/*
						 * Set franchiseOwnedInstitutionAnnexurePath accordingly 
						 */
						
						setPaths.put("franchiseOwnedInstitutionAnnexurePath", path.toString());
					}
					
					/*
					 * Extracting of FranchiseOwnedInstitutionAnnexure Ends 
					 */
					
					/*
					 * Now start extracting MobileTrainingInstitutionAnnexure from getAllFiles hashmap
					 */
					
					if(getAllFiles.get("MobileTrainingInstitutionAnnexure")==null)
					{
						/*
						 * No file is coming for MobileTrainingInstitutionAnnexure
						 */
						
						setPaths.put("mobileTrainingInstitutionAnnexurePath",trainingPartnerOrganizationDetailsList.get(temp).getMobileTrainingInstitutionAnnexurePath());
					}
					else
					{
						/*
						 * Setting path where to save MobileTrainingInstitutionAnnexure file 
						 */
						
						Path path=Paths.get(createAnnexuresFolder.getAbsolutePath()+"//MobileTrainingInstitutionAnnexure.pdf");
						
						/*
						 * Call ProfileCreationSaveFile with actual parameters to save file 
						 */
						String status=profileCreationSaveFile.saveFile(getAllFiles.get("MobileTrainingInstitutionAnnexure"), path);
						
						if(status==null)
						{
							/*
							 * Error Saving file on system
							 */
						}
						/*
						 * Set mobileTrainingInstitutionAnnexurePath accordingly 
						 */
						setPaths.put("mobileTrainingInstitutionAnnexurePath", path.toString());
					}
					
					/*
					 * Extracting of MobileTrainingInstitutionAnnexure Ends 
					 */
					
					
					/*
					 * Now start extracting trainingStaffDetailsAnnexure from getAllFiles hashmap
					 */
					
					if(getAllFiles.get("TrainingStaffDetailsAnnexure")==null)
					{
						/*
						 * No file is coming for MobileTrainingInstitutionAnnexure
						 */
						
						setPaths.put("trainingStaffDetailsAnnexurePath",trainingPartnerOrganizationDetailsList.get(temp).getTrainingStaffDetailsAnnexurePath());
					}
					else
					{
						/*
						 * Setting path where to save trainingStaffDetailsAnnexure file 
						 */
						
						Path path=Paths.get(createAnnexuresFolder.getAbsolutePath()+"//TrainingStaffDetailsAnnexure.pdf");
						
						/*
						 * Call ProfileCreationSaveFile with actual parameters to save file 
						 */
						String status=profileCreationSaveFile.saveFile(getAllFiles.get("TrainingStaffDetailsAnnexure"), path);
						
						if(status==null)
						{
							/*
							 * Error Saving file on system
							 */
						}
						/*
						 * Set trainingStaffDetailsAnnexurePath accordingly 
						 */
						setPaths.put("trainingStaffDetailsAnnexurePath", path.toString());
					}
					
					/*
					 * Extracting of TrainingStaffDetailsAnnexure Ends 
					 */
					
		
					/*
					 * Setting Database of TrainingPartnerOrganizationDetails
					 */
					
					
					if(userExists==-1)
					{
						/*
						 * Insert into database
						 */
						
						int status=profileCreationTrainingPartnerInsertDataDao.insertIntoTrainingPartnerOrganizationDetails(trainingPartnerOrganizationDetailsList.get(temp),setPaths);
						if(status>0)
						{
							/*
							 * Successfully inserted into database 
							 */
						}
						else if(status==-1)
						{
							/*
							 * An exception Occurred while inserting data in database
							 */
						}
						
					}
					else if(userExists==-2)
					{
						/*
						 * Error Occurred while getting ApplicationId
						 */
					}
					else
					{
						/*
						 * Run update query
						 */
					}
					
					
				}
			
			
			
			
			
			
			}
			
			
			
			
		}
		else if(userRole.equalsIgnoreCase("AB"))
		{
			/*
			 * Iterating HashMap to set AB different dto's 
			 */
			
			HashMap<String, HashMap<String, String>> assessmentBodyRegistrationDetails=userData.get("AssessmentBodyRegistrationDetails");
			HashMap<String, HashMap<String, String>> assessmentBodyRecognitions=userData.get("AssessmentBodyRecognitions");
			HashMap<String, HashMap<String, String>> assessmentsExperienceInTechnicalDomain=userData.get("AssessmentsExperienceInTechnicalDomain");
			HashMap<String, HashMap<String, String>> assessmentBodyDirectorsAndManagementTeamDetail=userData.get("AssessmentBodyDirectorsAndManagementTeamDetails"); 
			HashMap<String, HashMap<String, String>> assessmentStaffDetails=userData.get("AssessmentStaffDetails");
			HashMap<String, HashMap<String, String>> assessmentBodyRegionalOfficeDetails=userData.get("AssessmentBodyRegionalOfficeDetails");
			HashMap<String, HashMap<String, String>> assessmentBodyAffiliationDetails=userData.get("AssessmentBodyAffiliationDetails"); 
			
			/*
			 * Setting different Dto's
			 */
			
			/*
			 * ProfileCreationAssessmentBodyRegistrationDetailsDto Setting Starts
			 */
			
			ArrayList<ProfileCreationAssessmentBodyRegistrationDetailsDto> assessmentBodyRegistrationDetailsList = new ArrayList<ProfileCreationAssessmentBodyRegistrationDetailsDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationAssessmentBodyRegistrationDetailsIterator : assessmentBodyRegistrationDetails.entrySet())
			{
			HashMap<String, String> setProfileCreationAssessmentBodyRegistrationDetailsDto = new HashMap<String, String>();
			setProfileCreationAssessmentBodyRegistrationDetailsDto=profileCreationAssessmentBodyRegistrationDetailsIterator.getValue();
			assessmentBodyRegistrationDetailsList.add(new ProfileCreationAssessmentBodyRegistrationDetailsDto(
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("assessmentBodyRegistrationId"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("applicationId"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("organizationName"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("sPOCName"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("address"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("city"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("state"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("pincode"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("mobileNumber"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("alternateMobileNumber"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("landlineNumber"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("alternateLandlineNumber"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("faxNumber"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("websites"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("yearOfEstablishment"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("priorAssessmentExperience"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("panNumber"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("panNumberPath"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("tanNumber"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("tanNumberPath"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("insituteReceivedAnyRecognition"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("numberOfTechnicalAssessors"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("numberOfNonTechnicalAssessors"),
					setProfileCreationAssessmentBodyRegistrationDetailsDto.get("affiliatedToAnySectorSkillCouncil")
					));
			}
			
			/*
			 * Setting of ProfileCreationAssessmentBodyRegistrationDetailsDto Ends Here
			 */

			
			
			/*
			 * ProfileCreationAssessmentBodyRecognitionsDto Setting Starts
			 */
			
			ArrayList<ProfileCreationAssessmentBodyRecognitionsDto> assessmentBodyRecognitionsList = new ArrayList<ProfileCreationAssessmentBodyRecognitionsDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationAssessmentBodyRecognitionsIterator : assessmentBodyRecognitions.entrySet())
			{
			HashMap<String, String> setProfileCreationAssessmentBodyRecognitionsDto = new HashMap<String, String>();
			setProfileCreationAssessmentBodyRecognitionsDto=profileCreationAssessmentBodyRecognitionsIterator.getValue();
			assessmentBodyRecognitionsList.add(new ProfileCreationAssessmentBodyRecognitionsDto(
					setProfileCreationAssessmentBodyRecognitionsDto.get("assessmentBodyRecognitionId"),
					setProfileCreationAssessmentBodyRecognitionsDto.get("assessmentBodyRegistrationId"),
					setProfileCreationAssessmentBodyRecognitionsDto.get("nameOfRecognitionBody"),
					setProfileCreationAssessmentBodyRecognitionsDto.get("recognitionNumber"),
					setProfileCreationAssessmentBodyRecognitionsDto.get("yearOfRecognition"),
					setProfileCreationAssessmentBodyRecognitionsDto.get("validityOfRecognition")
					));
			}
			
			/*
			 * Setting of ProfileCreationAssessmentBodyRecognitionsDto Ends Here
			 */
			
			
			
			/*
			 * ProfileCreationAssessmentsExperienceInTechnicalDomainDto Setting Starts
			 */
			
			ArrayList<ProfileCreationAssessmentsExperienceInTechnicalDomainDto> assessmentsExperienceInTechnicalDomainList = new ArrayList<ProfileCreationAssessmentsExperienceInTechnicalDomainDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationAssessmentsExperienceInTechnicalDomainIterator : assessmentsExperienceInTechnicalDomain.entrySet())
			{
			HashMap<String, String> setProfileCreationAssessmentsExperienceInTechnicalDomainDto = new HashMap<String, String>();
			setProfileCreationAssessmentsExperienceInTechnicalDomainDto=profileCreationAssessmentsExperienceInTechnicalDomainIterator.getValue();
			assessmentsExperienceInTechnicalDomainList.add(new ProfileCreationAssessmentsExperienceInTechnicalDomainDto(
					setProfileCreationAssessmentsExperienceInTechnicalDomainDto.get("assessmentExperienceId"),
					setProfileCreationAssessmentsExperienceInTechnicalDomainDto.get("assessmentBodyRegistrationId"),
					setProfileCreationAssessmentsExperienceInTechnicalDomainDto.get("domain"),
					setProfileCreationAssessmentsExperienceInTechnicalDomainDto.get("numberOfAssessmentsDone")
					));
			}
			
			/*
			 * Setting of ProfileCreationAssessmentsExperienceInTechnicalDomainDto Ends Here
			 */
			
			
			
			/*
			 * ProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto Setting Starts
			 */
			
			ArrayList<ProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto> assessmentBodyDirectorsAndManagementTeamDetailsList = new ArrayList<ProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationAssessmentBodyDirectorsAndManagementTeamDetailsIterator : assessmentBodyDirectorsAndManagementTeamDetail.entrySet())
			{
			HashMap<String, String> setProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto = new HashMap<String, String>();
			setProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto=profileCreationAssessmentBodyDirectorsAndManagementTeamDetailsIterator.getValue();
			assessmentBodyDirectorsAndManagementTeamDetailsList.add(new ProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto(
					setProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto.get("directorsAndManagementId"),
					setProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto.get("assessmentBodyRegistrationId"),
					setProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto.get("name"),
					setProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto.get("designation"),
					setProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto.get("contactNumber"),
					setProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto.get("emailId"),
					setProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto.get("educationalQualification"),
					setProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto.get("experience"),
					setProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto.get("cVPath")
					));
			}
			
			/*
			 * Setting of ProfileCreationAssessmentBodyDirectorsAndManagementTeamDetailsDto Ends Here
			 */
			
			
			/*
			 * ProfileCreationAssessmentStaffDetailsDto Setting Starts
			 */
			
			ArrayList<ProfileCreationAssessmentStaffDetailsDto> assessmentStaffDetailsList = new ArrayList<ProfileCreationAssessmentStaffDetailsDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationAssessmentStaffDetailsIterator : assessmentStaffDetails.entrySet())
			{
			HashMap<String, String> setProfileCreationAssessmentStaffDetailsDto = new HashMap<String, String>();
			setProfileCreationAssessmentStaffDetailsDto=profileCreationAssessmentStaffDetailsIterator.getValue();
			assessmentStaffDetailsList.add(new ProfileCreationAssessmentStaffDetailsDto(
					setProfileCreationAssessmentStaffDetailsDto.get("assessmentStaffId"),
					setProfileCreationAssessmentStaffDetailsDto.get("assessmentBodyRegistrationId"),
					setProfileCreationAssessmentStaffDetailsDto.get("name"),
					setProfileCreationAssessmentStaffDetailsDto.get("jobRoleCode"),
					setProfileCreationAssessmentStaffDetailsDto.get("designation"),
					setProfileCreationAssessmentStaffDetailsDto.get("contactNumber"),
					setProfileCreationAssessmentStaffDetailsDto.get("emailId"),
					setProfileCreationAssessmentStaffDetailsDto.get("state"),
					setProfileCreationAssessmentStaffDetailsDto.get("city"),
					setProfileCreationAssessmentStaffDetailsDto.get("educationalQualification"),
					setProfileCreationAssessmentStaffDetailsDto.get("experience"),
					setProfileCreationAssessmentStaffDetailsDto.get("cVPath"),
					setProfileCreationAssessmentStaffDetailsDto.get("certificatePath")
					));
			}
			
			/*
			 * Setting of ProfileCreationAssessmentStaffDetailsDto Ends Here
			 */
			
			
			
			/*
			 * ProfileCreationAssessmentBodyRegionalOfficeDetailsDto Setting Starts
			 */
			
			ArrayList<ProfileCreationAssessmentBodyRegionalOfficeDetailsDto> assessmentBodyRegionalOfficeDetailsList = new ArrayList<ProfileCreationAssessmentBodyRegionalOfficeDetailsDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationAssessmentBodyRegionalOfficeDetailsIterator : assessmentBodyRegionalOfficeDetails.entrySet())
			{
			HashMap<String, String> setProfileCreationAssessmentBodyRegionalOfficeDetailsDto = new HashMap<String, String>();
			setProfileCreationAssessmentBodyRegionalOfficeDetailsDto=profileCreationAssessmentBodyRegionalOfficeDetailsIterator.getValue();
			assessmentBodyRegionalOfficeDetailsList.add(new ProfileCreationAssessmentBodyRegionalOfficeDetailsDto(
					setProfileCreationAssessmentBodyRegionalOfficeDetailsDto.get("regionalOfficeId"),
					setProfileCreationAssessmentBodyRegionalOfficeDetailsDto.get("assessmentBodyRegistrationId"),
					setProfileCreationAssessmentBodyRegionalOfficeDetailsDto.get("address"),
					setProfileCreationAssessmentBodyRegionalOfficeDetailsDto.get("state"),
					setProfileCreationAssessmentBodyRegionalOfficeDetailsDto.get("pincode"),
					setProfileCreationAssessmentBodyRegionalOfficeDetailsDto.get("contactNumber"),
					setProfileCreationAssessmentBodyRegionalOfficeDetailsDto.get("alternateContactNumber")
					));
			}
			
			/*
			 * Setting of ProfileCreationAssessmentBodyRegionalOfficeDetailsDto Ends Here
			 */
			
			
			/*
			 * ProfileCreationAssessmentBodyAffiliationDetailsDto Setting Starts
			 */
			
			ArrayList<ProfileCreationAssessmentBodyAffiliationDetailsDto> assessmentBodyAffiliationDetailsList = new ArrayList<ProfileCreationAssessmentBodyAffiliationDetailsDto>();
			
			for(Map.Entry<String, HashMap<String, String>> profileCreationAssessmentBodyAffiliationDetailsIterator : assessmentBodyAffiliationDetails.entrySet())
			{
			HashMap<String, String> setProfileCreationAssessmentBodyAffiliationDetailsDto = new HashMap<String, String>();
			setProfileCreationAssessmentBodyAffiliationDetailsDto=profileCreationAssessmentBodyAffiliationDetailsIterator.getValue();
			assessmentBodyAffiliationDetailsList.add(new ProfileCreationAssessmentBodyAffiliationDetailsDto(
					setProfileCreationAssessmentBodyAffiliationDetailsDto.get("affiliationId"),
					setProfileCreationAssessmentBodyAffiliationDetailsDto.get("assessmentBodyRegistrationId"),
					setProfileCreationAssessmentBodyAffiliationDetailsDto.get("nameOfSectorSkillCouncil")

					));
			}
			
			/*
			 * Setting of ProfileCreationAssessmentBodyAffiliationDetailsDto Ends Here
			 */
			
			/*
			 * Creating Folder Structure for Organization level details for saving files 
			 */
			

			
		}
		else 
		{
			/*
			 * Error in getting the User Role 
			 */
		}
		
		
		return null;
		}
		catch(Exception e)
		
		{
			e.printStackTrace();
			return null;
		}
	}
}
