package com.skill.India.service;

import com.skill.India.dao.NonAssignedBatchesUpdateDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alkesh on 7/12/2017.
 */
@Service
public class NonAssignedBatchesUpdateService {
    @Autowired
    private NonAssignedBatchesUpdateDao updateDao;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(NonAssignedBatchesUpdateDao.class);
    public int putUpdateBatches(String batchId, String agencyId,String responseType)
    {
    	LOGGER.debug("Request Received from Controller");
		LOGGER.debug("In NonAssignedBatchesUpdateService - putUpdateBatches");
		LOGGER.debug("Parameters Received from Controller are - 'batchId': "+batchId+" 'agencyId':"+agencyId+" 'responseType':"+responseType);
		
    	try
    	{
    		LOGGER.debug("Inside TRY block");
        	LOGGER.debug("Checking if agency for batch "+batchId+" is proposed already");
        	if(updateDao.checkIfProposedAlready(batchId))
        	{
        		LOGGER.debug("Batch has been propsed any agency already, hence updating");
        		LOGGER.debug("Making a Request to Dao");
        		return updateDao.putUpdateBatches(batchId,agencyId,responseType);
        	}
        	else
        	{
        		LOGGER.debug("Proposing agency "+agencyId+" to batch "+batchId+ " for the first time");
        		LOGGER.debug("Making a Request to Dao");
        		return updateDao.insertProposedBatch(agencyId, batchId, responseType);
        	}
    	}
    	catch(Exception e)
    	{
    		LOGGER.debug("Inside CATCH block");
    		LOGGER.debug("ERROR: Encountered Exception: "+e);
    		return 1;
    	}        
    }
}
