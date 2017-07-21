//console.log("code reacxhed to imort.js");
//var app = angular.module('app', [ 'ui.grid', 'ui.grid.edit', 'ui.grid.cellNav','ui.grid.autoResize']);
var imp = angular
.module('imp', [
    'ui.grid',
    'ui.grid.edit',
    'ui.grid.cellNav',
    'ui.grid.autoResize'
]);

//app.controller('MainCtrl', ['$scope', '$http', function ($scope, $http) {

imp.controller('importController', importController);

importController.$inject = ['$scope', '$http', 'fileUpload', 'zipCertificateUpload'];

function importController($scope, $http, fileUpload, zipCertificateUpload){
  //refresh();
	
	/*
	 * CSV UPLOAD HISTORY TABLE 
	 */
	
	

	$scope.dataImportHistory = {
	  enableGridMenus : false,  
	  enableSorting: false, 
	  enableFiltering: false,
	  enableCellEdit : false,
	  enableColumnMenus : false,
      enableHorizontalScrollbar:0,
	  
columnDefs:[
	  { name: 'SNo',                displayName: 'SNo',                cellClass:'sno',  headerCellClass:'layer', cellTemplate: '<div class="ui-grid-cell-contents">{{grid.renderContainers.body.visibleRowCache.indexOf(row)+1}}</div>'},
      { name: 'csvname',            displayName: 'File Name',          cellClass:'fname',headerCellClass:'File-Name'},
	  { name: 'csvtype',            displayName: 'Type',               cellClass:'type', headerCellClass:'Type'},
	  { name: 'csv_Upload_Date',    displayName: 'Date',               cellClass:'date', headerCellClass:'Date', cellFilter: 'date:\'dd/MM/yyyy\''},
	  { name: 'csv_Upload_UserId',  displayName: 'Uploaded By',        cellClass:'uby',  headerCellClass:'Uploaded-By'},
	  { name: 'View Uploaded File', displayName: 'View Uploaded File', cellClass:'vub',  headerCellClass:'View-Uploaded-File', cellTemplate: '<img src="/images/CSVDownloadIcon.png" ng-click=grid.appScope.download(row)>'}
	       ]
  };


	
	
  $scope.download = function(rowData){
	  var  fileName = rowData.entity.csvname;
	  //console.log("the row value is >>>" + rowData.entity.csvname);
	  var urldata = "/downloadCSVFile/"+ fileName;
	  window.open(urldata);
  };
  
  $scope.uploadCSV = function(){

//	  console.log('file is ');
//	  console.dir(file);
	  var csvType = $scope.csvType;
	  console.log(csvType);
	  var uploadUrl = "/upload";
	  fileUpload.uploadFileToUrl(csvType,uploadUrl);
	  };
  
     $http.get("/importHistory")
      .then(function(response){
    	  $scope.dataImportHistory.data = response.data;
    });
     
     /*
      * UPLOAD CSV BUTTON
      */
     
     $scope.optionValues = [{
         id: 'Batch',
         name: 'Batch'
     },{
         id: 'Candidate',
         name:'Candidate'
     },{
         id: 'Training Partner',
         name: 'Training Partner'
     },{
         id: 'Trainer',
         name: 'Trainer'
     },{
         id: 'Assessment Agency',
         name: 'Assessment Agency'
     },{
         id: 'Centre',
         name: 'Centre'
     },{
         id: 'Employer',
         name: 'Employer'
    },{
         id: 'Assessor',
         name: 'Assessor'
     }];
     
  
     
     /*
      * CERTIFICATE IMPORT HISTORY TABLE 
      */

$scope.certificateImportHistory = {
enableGridMenus : false,
enableSorting: false,
enableFiltering: false,
enableCellEdit : false,
enableColumnMenus : false,
enableHorizontalScrollbar:0,

columnDefs:[
{ name: 'SNo',           		displayName: 'SNo',                cellClass:'sno',   headerCellClass:'layer', cellTemplate: '<div class="ui-grid-cell-contents">{{grid.renderContainers.body.visibleRowCache.indexOf(row)+1}}</div>' },
{ name: 'batchID',          	displayName: 'Batch ID',           cellClass:'date',  headerCellClass:'Date'},
{ name: 'batchEndDate',         displayName: 'Batch End Date',     cellClass:'date',  headerCellClass:'Date'},
{ name: 'certificateName',      displayName: 'File Name',          cellClass:'fname', headerCellClass:'File-Name'},
{ name: 'userId',  				displayName: 'Uploaded By',        cellClass:'uby',   headerCellClass:'Uploaded-By'},
{ name: 'certificateUploadDate',displayName: 'Date',               cellClass:'date',  headerCellClass:'Date'},
{ name: 'View Uploaded File', 	displayName: 'View Uploaded File', cellClass:'vub',   headerCellClass:'View-Uploaded-File', cellTemplate: '<img src="/icon/Certificateicons/Certificate Download.png" ng-click=grid.appScope.downloadCertificate(row)>'}
  ]
};
$scope.downloadCertificate = function(rowData){
	
	 var  fileName = rowData.entity.certificateName;
	  //console.log("the row value is >>>" + rowData.entity.certificateName);
	  var urldata = "/downloadCertificate/"+ fileName;
	
	  window.open(urldata);
};

$http.get("/certificateImportHistory")
.then(function(response){
 $scope.certificateImportHistory.data = response.data;
});

/*
 * UPLOAD CERTIFICATE USING BATCH ID TABLE  
 */

$scope.searchBatch = {
		  enableGridMenus : false,  
		  enableSorting: false, 
		  enableFiltering: false,
		  enableCellEdit : false,
		  enableColumnMenus : false,
	      enableHorizontalScrollbar:0,
		  
	      columnDefs : [
              
              //{ name:'SNo' , 		         displayname:'SNo',					  cellClass:'batch id',	   headerCellClass:'layer' ,				 cellTemplate:"1"},
              { name:'batchId' ,  		 displayname:'Batch id' , 			  cellClass:'batch id', headerCellClass:'upload', width:100},
             // { name:'batchName' , 		 displayname:'Batch Name' , 	      cellClass:'batch id', headerCellClass:'Date',width:150},
              { name:'batchStartDate' , 	 displayname:'Batch Start date',	  cellClass:'date',	   headerCellClass:'Date',width:150},
              { name:'batchEndDate' ,      displayname:'Batch End date',		  cellClass:'date',	   headerCellClass:'Date',width:150},
              { name:'trainingPartnerName',displayname:'Training Partner Name', cellClass:'fname',    headerCellClass:'File-Name',width:150},
              { name:'view', displayName:'Browse file',cellClass:'fname', headerCellClass:'File-Name',width:300,  cellTemplate:  '<label> <input type="file" id="selectFile" accept=".zip" file-model/ ng-click=grid.appScope.selectLocation(row)></label>'},
              {name:'viewNAme', displayName:'Upload File', cellClass:'fname',headerCellClass:'File-Name',width:150,   cellTemplate:  '<label> <img src="icon/indexPageIcons/tick.png" ng-click=grid.appScope.uploadCertificate(row)></label>'}
            ]
	  };



	$scope.getDataOfBatch = function()
	{
		$http.post('/findBatch?batchId='+$scope.batchId)
		.then(function(response) {
			  //console.log("inside ");
			  //console.log(response.data);
			  var err;
			  var exists= document.getElementById("responseMessage");
			  if(response.data[0] == null)
				  {
				  
				  exists.style.color = "Red";
				  $scope.errorMessage="No Record Found";
				  //console.log('YE AAY ANULL');
				  }
				  //console.log("Error");
			  
			  else
				  $scope.errorMessage="";
				  $scope.searchBatch.data = response.data;
		  });
	};
	
	
	$scope.uploadCertificate = function(rowData){
		
		var  batchId = rowData.entity.batchId;
		var url = '/uploadCertificate';
		//console.log(rowData.entity.batchId);
		
//		$http.post('/uploadCertificate')
//		.then(function(response) {
//			
//		  });
//			
//		  console.log("the row value is >>>" + rowData.entity.batchName);
//		  var urldata = "/downloadCertificate/"+ fileName;
//		
//		  window.open(urldata);
//	};
	
		zipCertificateUpload.uploadZip(batchId,url);
	};

}


