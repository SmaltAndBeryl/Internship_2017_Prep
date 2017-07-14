package com.skill.India.service;

import java.util.Collection;

import org.codehaus.groovy.classgen.ReturnAdder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skill.India.dao.LoginDao;
import com.skill.India.dto.LoginDto;
import com.skill.India.dto.LoginReceiveDataDto;

@Service
public class LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
	@Autowired
	private LoginDao loginDao;
	private int userExistenceValidation;
	
	private LoginDto loginDto;


	public LoginDto checkUser(LoginReceiveDataDto loginReceiveDataDto)
	{
		LOGGER.info(loginReceiveDataDto.getUserId(),loginReceiveDataDto.getPassword());
		
	userExistenceValidation=loginDao.userExistence(loginReceiveDataDto.getUserId(),loginReceiveDataDto.getPassword());
		
		if(userExistenceValidation==1){
			Collection<LoginDto> login=loginDao.getLoginRowMapper(loginReceiveDataDto.getUserId(), loginReceiveDataDto.getPassword());
			for(LoginDto l:login)
			{
				return l;
			}
		}
	
		else{
					loginDto = new LoginDto(null, null, null);
			}
		return loginDto;
	}
	
}	
	
	





