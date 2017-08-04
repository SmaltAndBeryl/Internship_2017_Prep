package com.skill.India.controller;

import java.security.Principal;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skill.India.common.SessionUserUtility;
import com.skill.India.dto.GetSPOCNameAndApplicationStateDto;
import com.skill.India.dto.LoginDto;
import com.skill.India.dto.LoginReceiveDataDto;
import com.skill.India.dto.SignUpInsertedUserDto;
import com.skill.India.dto.SignUpReceiveDataDto;
import com.skill.India.service.GetSPOCNameAndApplicationStateService;
import com.skill.India.service.LoginService;
import com.skill.India.service.SignUpService;


@RestController
public class LoginController {
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
    public String userRedirectPage;
    
    @Autowired
	private SignUpService signUpService;    
   
    @Autowired
    private GetSPOCNameAndApplicationStateService getSPOCNameService; 
    
    @Autowired
    private SessionUserUtility sessionUserUtility;
    
    
    @RequestMapping(value="/loginUrl", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public LoginDto getLoginDto(@RequestBody LoginReceiveDataDto loginReceiveDataDto) 
	{	
    	LOGGER.info("Application have to validate the user");
		return loginService.checkUser(loginReceiveDataDto);
		
	}	
	
	@RequestMapping(value="/signup", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public SignUpInsertedUserDto signUp(@RequestBody SignUpReceiveDataDto signUpReceiveDataDto){
		
		LOGGER.info("Application have to record the new user");
	 return signUpService.signUp(signUpReceiveDataDto);
	  
	}
	
	@RequestMapping("/getSPOCNameAndApplicationState")
	public Collection<GetSPOCNameAndApplicationStateDto> getSPOCName()  {
		String userId=sessionUserUtility
				.getSessionMangementfromSession().getUsername();
		return getSPOCNameService.getSPOCNameAndApplicationStateService(userId); 
	}
	
	
	@RequestMapping("/getUserDetails")
	public Principal user(Principal user) {
		try{
		System.out.println("hey  : " +user);
		return user;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
