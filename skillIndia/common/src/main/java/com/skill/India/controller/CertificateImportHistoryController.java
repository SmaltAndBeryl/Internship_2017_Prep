/**
 * 
 */
package com.skill.India.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skill.India.dto.CertificateImportHistorydto;
import com.skill.India.service.CertificateImportHistoryService;
import com.skill.India.service.DataImportDownloadCertificateService;

/**
 * @author Rachit Goyal
 *
 */
@RestController
public class CertificateImportHistoryController {
	@Autowired
	private CertificateImportHistoryService certificateImportHistoryService;
	
	@Autowired
	private DataImportDownloadCertificateService dataImportDownloadCertificateService;
	
	@RequestMapping("/certificateImportHistory")
	public Collection<CertificateImportHistorydto> getcertificateImportHistorydto(){
	return certificateImportHistoryService.getUpdateHistory();
	}
		
}
