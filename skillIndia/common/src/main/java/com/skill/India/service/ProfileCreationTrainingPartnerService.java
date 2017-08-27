package com.skill.India.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skill.India.common.SessionUserUtility;
import com.skill.India.common.UserIdCheckInTrainingPartnerRegistration;
import com.skill.India.dao.ProfileCreationTrainingPartnerDao;
import com.skill.India.dto.ProfileCreationTrainingPartnerDto;

@Service
public class ProfileCreationTrainingPartnerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileCreationTrainingPartnerService.class);
	
	@Autowired
	private ProfileCreationTrainingPartnerDao profileCreationTrainingPartnerDao;

	@Autowired
	private UserIdCheckInTrainingPartnerRegistration userIdCheckInTrainingPartnerRegistration;
	
	@Autowired
	private SessionUserUtility sessionUserUtility;

	public ProfileCreationTrainingPartnerDto profileCreationTrainingPartner()
	{
		LOGGER.info("Request Received from Controller");
		LOGGER.info("In ProfileCreationTrainingPartnerService - profileCreationTrainingPartner");
			
		try{
			LOGGER.info("In TRY block");
			LOGGER.info("getting user Id from session");
			LOGGER.info("Making a Request to Dao to get data");
			String userId=sessionUserUtility.getSessionMangementfromSession().getUsername();
			LOGGER.info("Response received from Dao");
		/*
		 * First checking if userId exists in tpReg table or not then getting data from respective tables
		 */
		LOGGER.info("Checking Existense of User Id in Training Partner Table");
		LOGGER.info("Making a Request to Dao to get data");
		int userIdExists=userIdCheckInTrainingPartnerRegistration.userIdCheckInTrainingPartnerRegistration(userId);
		LOGGER.info("Response received from Dao");
		if(userIdExists==1)
		{
			LOGGER.info("Creating Collection to get data of Training Partner");
			LOGGER.info("Making a Request to Dao to get data");
		Collection<ProfileCreationTrainingPartnerDto> records= profileCreationTrainingPartnerDao.profileCreationGetDataFromTrainingPartnerRegistration(userId);
		LOGGER.info("Data Received Successfully in collection");
		LOGGER.info("Iterating every field from the received data ");
		LOGGER.info("Returning Every Field to Controller");
		for(ProfileCreationTrainingPartnerDto getRecord:records)
			return getRecord;
		}
		else if(userIdExists==0)
		{
			/*
			 * Get data from user table
			 */
			LOGGER.info("Creating Collection to get data of Training Partner");
			LOGGER.info("Making a Request to Dao to get data");
			Collection<ProfileCreationTrainingPartnerDto> records= profileCreationTrainingPartnerDao.profileCreationGetDataFromUser(userId);
			LOGGER.info("Data Received Successfully in collection");
			LOGGER.info("Iterating every field from the received data ");
			for(ProfileCreationTrainingPartnerDto getRecord:records)
				return getRecord;
		}

		return null;
		}
		catch(Exception e)
		{
			LOGGER.info("In CATCH Block");
			LOGGER.error("Error : Encountered an Exception - ",e);
			LOGGER.info("Returning NULL");
			e.printStackTrace();
			return null;
		}

	}
}
