package com.skill.India.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skill.India.common.Privilege;
import com.skill.India.common.SessionUserUtility;
import com.skill.India.dto.ApproveRejectTableDto;
import com.skill.India.dto.CommentDto;
import com.skill.India.dto.DataBeanDto;
import com.skill.India.dto.ManageRegistrationApplicationDto;
import com.skill.India.dto.MessageDto;
import com.skill.India.service.ApproveRejectTableService;
import com.skill.India.service.DataBeanService;

@RestController
public class ManageRegistrationsController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ManageRegistrationsController.class);

	@Autowired
	private ApproveRejectTableService approveRejectTableService;
	
	@Autowired
	private SessionUserUtility sessionUser;
	
    @Autowired
    private DataBeanService dataBeanService;

	@Privilege(value={"SCGJ"})
	@RequestMapping("/approve")
	public HashMap<String, ArrayList<ApproveRejectTableDto>> approveRejectTableDtos() {
		LOGGER.info("Trying to get Data from Application table");
		return approveRejectTableService.getUpdateRowMapper();
	}

	@Privilege(value={"SCGJ"})
	@RequestMapping(value = "/affiliationActionOfAnApplicant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MessageDto setAfflilationOfBody(
			@RequestBody ManageRegistrationApplicationDto manageRegistrationApplicationDto) {
		LOGGER.info("Setting the UserId For the Affilation of a Body");
		manageRegistrationApplicationDto.setUserId(sessionUser.getSessionMangementfromSession().getUsername());
		MessageDto approveRegistartionMessage = new MessageDto();
		LOGGER.info("Trying to Affilate an Applicant.");
		int updatedAffilationOfAApplicant = 0;

		updatedAffilationOfAApplicant = approveRejectTableService
				.updateAffilationOfAApplicant(manageRegistrationApplicationDto);
		if (updatedAffilationOfAApplicant > 0) {

			LOGGER.info("Success");

			approveRegistartionMessage.setSuccessMessage("Success");

		} else {
			approveRegistartionMessage.setErrorMessage("Error");
			LOGGER.info("Error");

		}
		return approveRegistartionMessage;

	}

	@Privilege(value={"SCGJ"})
	@RequestMapping(value = "/setManageRegistrationsComment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MessageDto setComment(
			@RequestBody CommentDto commentDto) {
		MessageDto approveRegistartionMessage = new MessageDto();
		try {

			String commentResult = approveRejectTableService
					.editUserApplication(commentDto.getApplicationId(),
							commentDto.getComment(),
							commentDto.getApplicationState());
			approveRegistartionMessage
					.setSuccessMessage("Changes made successfully");
			LOGGER.info("Result of inserting comments in Database is -"
					+ commentResult);
		} catch (Exception e) {
			approveRegistartionMessage
					.setErrorMessage("Exception inserting comments in Database");
			LOGGER.info("Exception in inserting comments to Database ", e);

		}
		return approveRegistartionMessage;

	}
	
	
	/*
	 * DataBeanController
	 */
	
	
    @Privilege(value={"SCGJ"})
    @RequestMapping("/cityData")
    public Collection<DataBeanDto> dataBeanDtoCollection(){
        LOGGER.info("The method to generate PDF has been called...");
        Collection<DataBeanDto> dataBeanDtos = dataBeanService.dataBeanDtoCollection();
        dataBeanService.getDataBeanList();
//        dataBeanService.generatePdf();
        return dataBeanService.dataBeanDtoCollection();
    }

    @Privilege(value={"SCGJ"})
    @RequestMapping("/generatePdf")
    public void pdfBeanArrayList(){
        dataBeanService.generatePdf();
    }
		
}