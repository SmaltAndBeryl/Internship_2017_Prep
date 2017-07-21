package com.skill.India.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class DataImportDownloadCertificateService {

	public void dataImportDownloadCertificate(HttpServletResponse response,String fileName)
	{
		try {
	    	FileInputStream is = new FileInputStream(new File("G://S&B//trunk//skillIndia//server//src//main//resources//static//data.zip"));
	    	response.setContentType("application/zip");
	    	response.setHeader("Content-disposition", "attachment; filename="+ fileName+".zip");
	    	org.apache.commons.io.IOUtils.copy(is,response.getOutputStream());
	    } 
		catch (IOException ex) { 	
	      throw new RuntimeException("IOError writing file to output stream");
	    }
		catch (Exception e) {
			// Error message 
		}
	}
}
