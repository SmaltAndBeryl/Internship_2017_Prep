'use strict';

var newApp = angular.module('LoginScreenApp', []);

newApp.controller('loginCtrl',
				['$scope', '$http','$window', 
				 	function($scope, $http, $window){							
					
							$scope.login = function(){
								console.log(angular.toJson($scope.user));
								var loginControllerURI = "/loginUrl";
								console.log(loginControllerURI);
										console.log("click is working");
											$http({
												url : loginControllerURI,
												method : "POST",
												data : angular.toJson($scope.user),
												headers : {
															'Content-type': 'application/json'
															}
												
												}).then(function(response)
													{
												     console.log(response.data);
												     var loginAction=response.data;
												     var userName=loginAction.userId;
												    if(loginAction.userStatus==null || loginAction.userRole==null || loginAction.userId==null) {
												    	$window.alert("User Not Authenticated");
												    }
												    else{
												    	if(loginAction.userStatus=="temp"){
												    		if(loginAction.userRole=="TP")
														    	//window.location = "http://localhost:8080/profileCreationTrainingPartner?userId="+userName;
															window.location = "http://localhost:8080/profileCreationTrainingPartner";
												    		else 
														    	//window.location = "http://localhost:8080/profileCreationTrainingPartner?userId="+userName;
															window.location = "http://localhost:8080/profileCreationTrainingPartner";
												    									}
												    	else if(loginAction.userStatus=="registered"){
													    	if(loginAction.userRole=="TP")	{	
													    		$http.post('/userIdtoApplicationId?userId='+userName).then(function(response){
													    			var applicationId=response.data;
													    			//window.location = "http://localhost:8080/usersHomepage#!/tp?applicationId="+applicationId;
																	window.location = "http://localhost:8080/usersHomepage#!/tp";
													    		});
													    	
													    	}
													    	else if(loginAction.userRole=="AB"){
													    		$http.post('/userIdtoApplicationId?userId='+userName).then(function(response){
													    			var applicationId=response.data;
													    			//window.location = "http://localhost:8080/usersHomepage#!/ab?applicationId="+applicationId;
																	window.location = "http://localhost:8080/usersHomepage#!/ab";
													    		});
													    	}
													    	else if(loginAction.userRole=="SCGJ"){
														    	//window.location = "http://localhost:8080/index?userId="+userName;
																window.location = "http://localhost:8080/index";
													    	}
													    	else
															{
													    		$window.alert("User Not Authenticated");
															}
												    	}
												      }
												
													});
								}
							
							
						}
						
				]);


newApp.controller('signupCtrl',
		['$scope', '$http','$window', 
		 	function($scope, $http, $window){							
			
					$scope.signup = function(){
						console.log(angular.toJson($scope.newUser));
						var signupControllerURI = "/signup"; 
						var userRole=$scope.newUser.userRole;
						console.log(signupControllerURI);
								console.log("click is working");
									$http({
										url : signupControllerURI,
										method : "POST",
										data : angular.toJson($scope.newUser),
										headers : {
													'Content-type': 'application/json'
													}
										
										}).then(function(response)
											{
										     console.log(response.data);
									     var signupAction=response.data;
									     var userName=signupAction.userId;
									     if(userName=="null" || signupAction.organizationName=="null" || signupAction.sPOCName=="null"){
									    	$window.alert("User already exist");
									    	 }
									     else
									    	 {
									    	 if($scope.newUser.userRole=="TP"){
									    	 //window.location = "http://localhost:8080/profileCreationTrainingPartner?userId="+userName;
											 window.location = "http://localhost:8080/profileCreationTrainingPartner";
									    	 }else if($scope.newUser.userRole=="AB")
										    	 //window.location ="http://localhost:8080/profileCreationAssessmentBody?userId="+userName;
											 window.location ="http://localhost:8080/profileCreationAssessmentBody";
									    	 else
										    	 window.location = "http://localhost:8080/";
 
									    	 }
 
        								  });
									}
					
					
				}
				
		]);




