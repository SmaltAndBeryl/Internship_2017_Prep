package com.skill.India.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.skill.India.common.AbstractTransactionalDao;
import com.skill.India.config.LoginConfig;
import com.skill.India.dto.ApplicationDto;

@Repository
public class GetApplicationStateDao extends AbstractTransactionalDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetApplicationStateDao.class);
	
	@Autowired
	private LoginConfig loginConfig;
	
	private static final ApplicationRowMapper ROW_MAPPER = new ApplicationRowMapper();

	public ApplicationDto getApplicationState(String userId) {
		LOGGER.debug("Request Received from Service");
		LOGGER.debug("In GetApplicationStateDao - getApplicationState");
		LOGGER.debug("Parameters Received from Service are - 'userId': " +userId);
	   	
		try 
		{
			LOGGER.debug("Inside TRY block");
			LOGGER.debug("getting Application State for Application Id");
		
			LOGGER.debug("Creating HashMap object");
			Map<String, Object> parameters = new HashMap<String, Object>();
			LOGGER.debug("object created successfully");
			
			LOGGER.debug("Inserting parameters to HashMap object");
			parameters.put("userId", userId);
			LOGGER.debug("Parameters inserted");
			
			LOGGER.debug("Executing SQL query and returning response");
	        return getJdbcTemplate().queryForObject(
					loginConfig.getGetApplicationState(), parameters,
					ROW_MAPPER);// query for multiple rows from database
		} 
		catch (Exception e) 
		{
			LOGGER.debug("Inside CATCH block");
			
			LOGGER.error("ERROR: Encountered Exception - "+e);
			
			LOGGER.debug("Returning NULL");
//			e.printStackTrace();
			return null;
		}
	}

	private static class ApplicationRowMapper implements
			RowMapper<ApplicationDto> {

		@Override
		public ApplicationDto mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			String applicationId = resultSet.getString("applicationId");
			String applicationState = resultSet.getString("applicationState");
					
			return new ApplicationDto(applicationState, applicationId);

		}

	}

}
