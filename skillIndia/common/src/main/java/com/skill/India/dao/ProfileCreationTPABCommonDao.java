package com.skill.India.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.skill.India.common.AbstractTransactionalDao;
import com.skill.India.common.SessionUserUtility;
import com.skill.India.config.ProfileCreationTPABCommonConfigSql;
import com.skill.India.dto.ProfileCreationGetDataFromUserDto;

@Repository
public class ProfileCreationTPABCommonDao extends AbstractTransactionalDao{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileCreationTPABCommonDao.class);
	@Autowired
	private ProfileCreationTPABCommonConfigSql profileCreationTPABCommonConfigSql;
	
	@Autowired
	private SessionUserUtility sessionUserUtility;

	/*
	 * Getting data from User table 
	 */
	
	
private static final ProfileCreationGetDataFromUserRowMapper ROW_MAPPER_USERDETAILS = new ProfileCreationGetDataFromUserRowMapper();
	
	public Collection<ProfileCreationGetDataFromUserDto> profileCreationGetDataFromUser()
	{
		try{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userId",sessionUserUtility.getSessionMangementfromSession().getUsername());
		return getJdbcTemplate().query(profileCreationTPABCommonConfigSql.getGetDataFromUser(),parameters,ROW_MAPPER_USERDETAILS);
		}
		catch(Exception e)
		{
			LOGGER.error("An Exception occured while fetching data for user " +e);
			return null;
		}
 	}
	
	public static class ProfileCreationGetDataFromUserRowMapper implements RowMapper<ProfileCreationGetDataFromUserDto> {

		@Override
		public ProfileCreationGetDataFromUserDto mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {

			String organizationName = resultSet.getString("organizationName");
			String sPOCName = resultSet.getString("sPOCName");
			String userId = resultSet.getString("userId");
			return new ProfileCreationGetDataFromUserDto(organizationName,sPOCName,
					userId);
		}

}

	
	/*
	 * Insert into Application table 
	 */

	public int insertIntoApplication(String type)
	{
		try{
		long millis=System.currentTimeMillis();
		java.sql.Date date=new java.sql.Date(millis);
		Map<String, Object> parameters=new HashMap<String, Object>();
		if(type.equalsIgnoreCase("Draft"))
		{
			parameters.put("applicationState","Draft");
		}
		else if(type.equalsIgnoreCase("Submit"))
		{
		parameters.put("applicationState","Submit");
		}
		parameters.put("userId",sessionUserUtility.getSessionMangementfromSession().getUsername());
		parameters.put("activeFlag","1");
		parameters.put("dateOfSubmission",date);
		return getJdbcTemplate().update(profileCreationTPABCommonConfigSql.getInsertIntoApplication(), parameters);
		}
		catch(Exception e)
		{
			/*
			 * In case error occurs 
			 */
			LOGGER.error("An Exception Occured "+ e);
			return -1;
		}
	}
	

	/*
	 * Update into Application table 
	 */

	public int updateIntoApplication(String type)
	{
		try{
		long millis=System.currentTimeMillis();
		java.sql.Date date=new java.sql.Date(millis);
		Map<String, Object> parameters=new HashMap<String, Object>();
		if(type.equalsIgnoreCase("Draft"))
		{
			parameters.put("applicationState",type);
		}
		else if(type.equalsIgnoreCase("Submit"))
		{
			parameters.put("applicationState",type);
		}
		parameters.put("userId",sessionUserUtility.getSessionMangementfromSession().getUsername());
		parameters.put("activeFlag","1");
		parameters.put("dateOfSubmission",date);
		parameters.put("applicationId", sessionUserUtility.getApplicationId(sessionUserUtility.getSessionMangementfromSession().getUsername()));
		return getJdbcTemplate().update(profileCreationTPABCommonConfigSql.getUpdateIntoApplication(), parameters);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	
	
	
}
