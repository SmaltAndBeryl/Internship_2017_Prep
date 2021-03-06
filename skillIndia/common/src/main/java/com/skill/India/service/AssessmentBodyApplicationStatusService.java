package com.skill.India.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skill.India.dao.AssessmentBodyApplicationStatusDao;
import com.skill.India.dto.AssessmentBodyApplicationStatusDto;



@Service
public class AssessmentBodyApplicationStatusService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentBodyApplicationStatusService.class);
	
	@Autowired
	private AssessmentBodyApplicationStatusDao updateDao;
	
	public Collection<AssessmentBodyApplicationStatusDto> getUpdateRowMapper(int applicationId){
		LOGGER.debug("Request Received from Controller");
		LOGGER.debug("In AssessmentBodyApplicationStatusService - getUpdateRowMapper");
		LOGGER.debug("Parameters Received from front end are - 'applicationId': "+applicationId);
		LOGGER.debug("Making a Request to Dao to get data");
		return updateDao.getUpateRowMapper(applicationId) ;
	}

}
