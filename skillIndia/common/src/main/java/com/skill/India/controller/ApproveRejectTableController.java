package com.skill.India.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skill.India.dto.ApproveRejectTableDto;
import com.skill.India.dto.CommentDto;
import com.skill.India.dto.ManageRegistrationApplicationDto;
import com.skill.India.dto.MessageDto;
import com.skill.India.service.ApproveRejectTableService;
	

	@RestController
	public class ApproveRejectTableController
	{
		
		private static final Logger LOGGER = LoggerFactory.getLogger(ApproveRejectTableController.class);
		@Autowired
		private ApproveRejectTableService approveRejectTableService;
			
		
		@RequestMapping("/approve")
		public Collection<ApproveRejectTableDto> approveRejectTableDtos(@RequestParam("applicationState") String applicationState)
		{
			LOGGER.info("Trying to get Data from Application table");
		return approveRejectTableService.getUpdateRowMapper(applicationState);
		}
		
		@RequestMapping(value="/affiliationActionOfAnApplicant",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody MessageDto setAfflilationOfBody(@RequestBody ManageRegistrationApplicationDto manageRegistrationApplicationDto)
		{
			MessageDto approveRegistartionMessage = new MessageDto();
			LOGGER.info("Trying to Affilate an Applicant."); 
			int updatedAffilationOfAApplicant=0;
			
				  updatedAffilationOfAApplicant = approveRejectTableService.updateAffilationOfAApplicant(manageRegistrationApplicationDto);
				  if(updatedAffilationOfAApplicant>0)
					{
			
					  LOGGER.info("Success");
					  
					  approveRegistartionMessage.setSuccessMessage("Success");
					  
					}
				  else 
				  {
					  approveRegistartionMessage.setErrorMessage("Error");
					  LOGGER.info("Error");
					  
				  }
				  return approveRegistartionMessage;
				 
		 }
		
		 @RequestMapping(value= "/setManageRegistrationsComment",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
		 public @ResponseBody MessageDto setComment(@RequestBody CommentDto commentDto){
			 MessageDto approveRegistartionMessage = new MessageDto();
			 try
			 {
				 
				 String commentResult =approveRejectTableService.editUserApplication(commentDto.getApplicationId(), commentDto.getComment());
				 approveRegistartionMessage.setSuccessMessage("Changes made successfully");
				 LOGGER.info("Result of inserting comments in Database is -"+ commentResult);
			 }
			 catch(Exception e)
			 {
				 approveRegistartionMessage.setErrorMessage("Exception inserting comments in Database");
				 LOGGER.info("Exception in inserting comments to Database ", e);
				 
			 }
			 return approveRegistartionMessage;
			
		 }
		 	 
		 
		 
		  
		 
	}
		 
	 