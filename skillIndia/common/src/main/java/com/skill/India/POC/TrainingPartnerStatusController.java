package com.skill.India.POC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TrainingPartnerStatusController {

	@RequestMapping("/trainingPartnerHomePage")  
	  public  String user() {
	    return "MasterTemplate2Tp";
	  }
}
