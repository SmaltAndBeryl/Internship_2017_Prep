var trainingPartner = angular.module("hello");

trainingPartner.controller("trainingPartner" , function($scope, $http, $rootScope,$location,$window,$timeout){
    $scope.message = "Hi Training Partner";
    $scope.applicationStatus = "";
    var appState = "Submit";
    $scope.successText = "";
    $scope.tpAppStatus = {
        enableColumnMenus: false,
        enableSorting: false,
        verticalScrollbar: 0,

        columnDefs: [{
                name: 'applicationID',
                displayName: 'Application ID',
                cellClass: 'commoncell',
                width : "10%",
                headerCellClass: 'Application-ID'
            },
            {
                name: 'dateOfSubmission',
                displayName: 'Date of Submission',
                cellClass: 'commoncell',
                width : "11%",
                headerCellClass: 'Date-of-Submission'
            },
            {
                name: 'trainingPartnerName',
                displayName: 'Submitted By',
                width : "20%",
                cellClass: 'commoncell',
                headerCellClass: 'Submitted-By'
            },
            {
                name: 'applicationState',
                displayName: 'Status',
                width : "10%",
                cellClass: function (grid, row, col, rowIndex, colIndex) {
                    var val = grid.getCellValue(row, col);
                    if (val === 'Approved') {
                      return 'statuscellgreen';
                    }
                    else if (val === 'Incomplete') {
                      return 'statuscellred';
                    }
                    else
                    	{
                    	return '';
                    	}
                  },
                headerCellClass: 'Status'
            },
            {
                name: 'Action',
                displayName: 'Action',
                cellClass: 'Prostokt-2-kopia-2-copy-14',
                width: "5%",
                headerCellClass: 'Action-copy',
                cellTemplate: '<img src="icon/indexpageIcons/edit1.png" ng-show="1"class="pointer" ng-click="grid.appScope.editApplication(row)">'
            },
            {
                name: 'comment',
                displayName: 'Comment',
                cellClass: 'comment',
                headerCellClass: 'Comment',
                width : "44%"
                //cellTemplate: '<center ng-click=grid.appScope.myfunction1(row)>'
            }
        ]
    };

    $scope.myfunction1 = function(rowData) {
        var commentCheck = rowData.entity.comment;
        window.alert(commentCheck);

    }
    $scope.editApplication = function(rowData) {
    	
        appState = rowData.entity.applicationState;
        if (appState == "Incomplete") {
        	$scope.applicationStatus =appState;
        	$location.path('/profileCreationTp');
            //console.log("Please Edit the Form");

        } 
        else
        {
        	$scope.successTextColor = "blue";
        	$scope.successText = "You can edit the application only if it is Incomplete";
        	 $timeout(function() {
                 $scope.successText="";
              }, 2000);
        }
        //console.log("Click is working" + appState);
    };

    $http.get('/trainingPartnerApplicationStatus')
        .then(function(response) {
                $scope.tpAppStatus.data = response.data;
                //console.log(response.data);
            },
            function(errorResponse) {
                //console.log("inside error function application status table");
            })
    //tp status ends

    $scope.presentGridOptions = {
        enableColumnMenus: false,
        enableSorting: false,
        verticalScrollbar: 0,

        columnDefs: [{
                name: 'batchId',
                displayName: 'Batch ID',
                cellClass: 'Prostokt-2-kopia-2-copy-10',
                headerCellClass: 'bId',
            },
            {
                name: 'completionDate',
                displayName: 'Completion Date',
                cellClass: 'Prostokt-2-kopia-2-copy-11',
                headerCellClass: 'compDate',
            },
            {
                name: 'instructorName',
                displayName: 'Instructor Name',
                cellClass: 'Prostokt-2-kopia-2-copy-12',
                headerCellClass: 'insName',
            },
            {
                name: 'noOfCandidates',
                displayName: 'Number of Candidates',
                cellClass: 'Prostokt-2-kopia-2-copy-13',
                headerCellClass: 'candidates',
            }
        ]
    };

    $http.get("/trainingPartnerPastBatches")
        .then(function(response) {
            $scope.presentGridOptions.data = response.data.PresentBatches;
        })

    $scope.pastGridOptions = {
        enableColumnMenus: false,
        enableSorting: false,
        verticalScrollbar: 0,
        columnDefs: [{
                name: 'batchId',
                displayName: 'Batch ID',
                cellClass: 'Prostokt-2-kopia-2-copy-10',
                headerCellClass: 'bId',
                width: 100
            },
            {
                name: 'completionDate',
                displayName: 'Completion Date',
                cellClass: 'Prostokt-2-kopia-2-copy-11',
                headerCellClass: 'compDate',
                width: 150
            },
            {
                name: 'instructorName',
                displayName: 'Instructor Name',
                cellClass: 'Prostokt-2-kopia-2-copy-12',
                headerCellClass: 'insName',
                width: 150
            },
            {
                name: 'noOfCandidates',
                displayName: 'Number of Candidates',
                cellClass: 'Prostokt-2-kopia-2-copy-13',
                headerCellClass: 'candidates',
            },
            {
                name: 'Display',
                displayName: 'Download Certificate',
                cellClass: 'download',
                headerCellClass: 'downCer',
                cellTemplate: '<img src="icon/CertificateDownlad/Certificate Download.png" class="pointer" ng-click=grid.appScope.downloadCertificate(row)>'
            }
        ]
    };
    $http.get("/trainingPartnerPastBatches")
        .then(function(response) {
            $scope.pastGridOptions.data = response.data.PastBatches;
        })


    $scope.downloadCertificate = function(rowData) {
        var fileName = rowData.entity.batchId;
        //console.log("the row value is >>>" + rowData.entity.batchId);
        var urldata = "/downloadCertificate/" + fileName;

        $window.open(urldata);
    };

    if($rootScope.authenticated){
          $rootScope.$on("$locationChangeStart", function(event,next,current)
        		  {
        	  //event.preventDefault();
        	  //console.log("Inside it and next is " + next);
        	  //console.log("Printing value outside if " +appState);
        	  var indexOfSlash = next.lastIndexOf("/");
      	    var lengthOfNext = next.length;
      	    var absoluteUrl = next.substr(indexOfSlash,lengthOfNext);
      	    //console.log(absoluteUrl);
        	    
        	  if(absoluteUrl == '/profileCreationTp')
        		  {
        		   //console.log("inside if");
        		  
        		  $location.path('/profileCreationTp');
        		  }
        	  else
        		  {
        		  event.preventDefault();
        		  
        		  }

          });
      }
});