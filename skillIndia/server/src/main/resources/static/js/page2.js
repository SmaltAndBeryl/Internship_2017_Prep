console.log("Working page2");
var page2 = angular.module('hello');

page2.controller('page2', function($scope, $location, $http) {

console.log("Inside controller");
$scope.messagealert = false;
//refresh();
//Grid for submitted application
$scope.gridOptions = {
    enableGridMenus: false,
    enableSorting: false,
    enableFiltering: false,
    enableCellEdit: false,
    enableColumnMenus: false,
    paginationPageSizes: [10, 2, 50],
    paginationPageSize: 2,
    useExternalPagination: true,
    columnDefs: [{
            name: 'applicationId',
            displayName: '#',
            cellClass: 'sno',
            headerCellClass: 'Institution-Name',
            width: 30
        },
        {
            name: 'organisationName',
            displayName: 'Institution Name',
            cellClass: 'fname',
            headerCellClass: 'Institution-Name'
        },
        {
            name: 'userRole',
            displayName: 'Type',
            cellClass: 'Type',
            headerCellClass: 'Institution-Name'
        },
        {
            name: 'dateOfSubmission',
            displayName: 'Date',
            cellClass: 'layer',
            headerCellClass: 'Institution-Name'
        },
        {
            name: 'userId',
            displayName: 'View Application',
            cellTemplate: '<img src="icon/indexPageIcons/pdf.png" ng-click=grid.appScope.myfunction(row)>',
            headerCellClass: 'Institution-Name',
            cellClass: 'va',
            width: 120
        },
        {
            name: 'Comments',
            displayName: 'Comments',
            enableCellEdit: true,
            headerCellClass: 'Institution-Name',
            cellClass: 'va'
        },
        {
            name: 'Action',
            displayName: 'Action',
            cellTemplate: '<label><img src="icon/indexPageIcons/edit.png"  ng-click=grid.appScope.myfunctionedit(row) ">&nbsp; &nbsp; &nbsp<img src="icon/indexPageIcons/tick.png" ng-click=grid.appScope.myfunctionapprove(row); >  &nbsp; &nbsp; <img src="icon/indexPageIcons/close.png"  ng-click=grid.appScope.myfunctionreject(row) ></label>',
            headerCellClass: 'Institution-Name',
            cellClass: 'va'
        }

    ]
};

//Grid for approved application
$scope.gridOptionsApproved = {
    enableGridMenus: false,
    enableSorting: false,
    enableFiltering: false,
    enableCellEdit: false,
    enableColumnMenus: false,

    columnDefs: [{
            name: 'applicationId',
            displayName: '#',
            cellClass: 'sno',
            headerCellClass: 'Institution-Name',
            width: 30
        },
        {
            name: 'organisationName',
            displayName: 'Institution Name',
            cellClass: 'fname',
            headerCellClass: 'Institution-Name'
        },
        {
            name: 'userRole',
            displayName: 'Type',
            cellClass: 'Type',
            headerCellClass: 'Institution-Name'
        },
        {
            name: 'dateOfSubmission',
            displayName: 'Date',
            cellClass: 'layer',
            headerCellClass: 'Institution-Name'
        }

    ]
};

//Grid for Incomplete applications
$scope.gridOptionsIncomplete = {
    enableGridMenus: false,
    enableSorting: false,
    enableFiltering: false,
    enableCellEdit: false,
    enableColumnMenus: false,

    columnDefs: [{
            name: 'applicationId',
            displayName: '#',
            cellClass: 'sno',
            headerCellClass: 'Institution-Name',
            width: 30
        },
        {
            name: 'organisationName',
            displayName: 'Institution Name',
            cellClass: 'fname',
            headerCellClass: 'Institution-Name'
        },
        {
            name: 'userRole',
            displayName: 'Type',
            cellClass: 'Type',
            headerCellClass: 'Institution-Name'
        },
        {
            name: 'dateOfSubmission',
            displayName: 'Date',
            cellClass: 'layer',
            headerCellClass: 'Institution-Name'
        }
    ]
};

//grid for rejected applications
$scope.gridOptionsRejected = {
    enableGridMenus: false,
    enableSorting: false,
    enableFiltering: false,
    enableCellEdit: false,
    enableColumnMenus: false,

    columnDefs: [{
            name: 'applicationId',
            displayName: '#',
            cellClass: 'sno',
            headerCellClass: 'Institution-Name',
            width: 30
        },
        {
            name: 'organisationName',
            displayName: 'Institution Name',
            cellClass: 'fname',
            headerCellClass: 'Institution-Name'
        },
        {
            name: 'userRole',
            displayName: 'Type',
            cellClass: 'Type',
            headerCellClass: 'Institution-Name'
        },
        {
            name: 'dateOfSubmission',
            displayName: 'Date',
            cellClass: 'layer',
            headerCellClass: 'Institution-Name'
        },
    ]
};

$http.get('/approve')
    .then(function(response) {
        console.log("get successful for part 1");
        $scope.gridOptions.data = response.data.submitted;
        $scope.gridOptionsRejected.data = response.data.rejected;
        $scope.gridOptionsApproved.data = response.data.approved;
        $scope.gridOptionsIncomplete.data = response.data.incomplete;

    });
//function for loader

//function for pdf generation

$scope.myfunction = function(rowData){
    var applicationId = rowData.entity.applicationId;
    console.log("The application Id is " + applicationId);
    console.log("Rolling is " + $scope.rolling);
    $scope.rolling = true;
    $scope.generating = "Generating your PDF, please wait..."
    $http({
        url : '/getUserRoleApplicationId',
        method : 'POST',
        params : { applicationId : applicationId}
    })
        .then(function(response){
            console.log("Application Id posted successfully");
            if(response.data > 0){
                $scope.successText = "PDF generated successfully";
                $scope.successTextColor = "green";
                $scope.rolling = false;
            }
            else if(response.data < 0){
                $scope.successText = "PDF could not be generated successfully due to some error";
                $scope.successTextColor = "orange";
                $scope.rolling = false;
            }
            else if(response.data == 0){
                $scope.successText = "PDF could not be generated successfully";
                $scope.successTextColor = "red";
                $scope.rolling = false;
            }
//            alert("the response is "+ JSON.stringify(response.data));
        })
};
//function for edit functionalities

$scope.myfunctionedit = function(rowData) {
    $scope.messagealert = false;
    console.log("function to get row data");
    var editdetailsOfApplication = {
        applicationState: 'Incomplete',
        applicationId: rowData.entity.applicationId,
        comment: rowData.entity.Comments
    }
    //console.log(dataOfRow[0][0]);
    console.log(rowData.entity.Comments);
    console.log(editdetailsOfApplication);
   
    var manageControllerURI = "/setManageRegistrationsComment";
    console.log(manageControllerURI);
    console.log("click is working");
    $http({
        url: manageControllerURI,
        method: "POST",
        data: angular.toJson(editdetailsOfApplication)
    }).then(
        function(response) {
            $http.get('/approve')
                .then(function(dataResponse) {
                    $scope.gridOptions.data = dataResponse.data.submitted;
                    $scope.gridOptionsIncomplete.data = dataResponse.data.incomplete;
                    $scope.gridOptionsRejected.data = dataResponse.data.rejected;
                    $scope.gridOptionsApproved.data = dataResponse.data.approved;
                    var message = response.data.successMessage;
                    console.log(response.data);
                    $scope.message = response.data.successMessage;
                    $scope.messagealert = true;
                    var success = $scope.message;
                    console.log('THIS IS THE RESPONSE IN THE COMMENT:' + success);
                    alert(success);
                })
        },
        function(errorResponse, status) {
            $scope.message = errorResponse.data.errorMessage;
            $scope.messagealert = true;
            var failure = $scope.message;
            console.log('THIS IS THE RESPONSE IN THE COMMENT:' + failure);
            alert(failure);
        }
    )
};
//function for approve functionalities

$scope.myfunctionapprove = function(rowData) {
    $scope.messagealert = false;
    alert("are you sure you want to accept this application")
    var editDetailsOfApplication = {
        'applicationState': 'Approved',
        'applicationId': rowData.entity.applicationId,
        'activeFlag': 'true',
        'userId': rowData.entity.userId
    }
    console.log(rowData.entity.applicationId);
    console.log(editDetailsOfApplication);
    var manageControllerURI = "/affiliationActionOfAnApplicant";
    console.log(manageControllerURI);
    console.log("click is working");
    $http({
        url: manageControllerURI,
        method: "POST",
        data: angular.toJson(editDetailsOfApplication),
    }).then(function(response) {
            $http.get('/approve')
                .then(function(dataResponse) {
                    $scope.gridOptions.data = dataResponse.data.submitted;
                    $scope.gridOptionsApproved.data = dataResponse.data.approved;
                    $scope.gridOptionsIncomplete.data = dataResponse.data.incomplete;
                    $scope.gridOptionsRejected.data = dataResponse.data.rejected;

                    var message = response.data.successMessage;
                    console.log(response.data);
                    $scope.message = response.data.successMessage;
                    $scope.messagelert = true;
                    var success = $scope.message;
                    console.log('THIS IS THE RESPONSE' + success);
                    alert(success);
                })
        },
        function(errorResponse, status) {
            alert(error.data.errorMessage);
            $scope.message = response.data.errorMessage;
            $scope.messagealert = true;
            var failure = $scope.message;
            console.log('THIS IS THE RESPONSE :' + failure);
            alert(failure);

        }
    )


};


//function for reject functionalities

$scope.myfunctionreject = function(rowData) {
    alert("are you sure you want to accept this application")
    $scope.messagealert = false;



    var rejectApplicationData = {
        'applicationState': 'Rejected',
        'applicationId': rowData.entity.applicationId,
        'userId': rowData.entity.userId,
        'activeFlag': "false"
    }

    var manageControllerURI = "/affiliationActionOfAnApplicant";
    console.log(manageControllerURI);
    console.log("click is working");
    $http({
        url: manageControllerURI,
        method: "POST",
        data: angular.toJson(rejectApplicationData),
    }).then(function(response) {
            $scope.message = response.data.successMessage;
            $scope.messageAlert = true;
            console.log(response.data.successMessage);



            $http.get('/approve')
                .then(function(dataResponse) {
                    $scope.gridOptions.data = dataResponse.data.submitted;
                    $scope.gridOptionsRejected.data = dataResponse.data.rejected;
                    $scope.gridOptionsApproved.data = dataResponse.data.approved;
                    $scope.gridOptionsIncomplete.data = dataResponse.data.incomplete;

                    var message = response.data.successMessage;
                    console.log('THIS IS THE REPOSMSEEESEESESEESE' + response.data);
                    $scope.message = response.data.successMessage;
                    $scope.messagealert = true;
                    var success = $scope.message;
                    console.log('THIS IS THE RESPONSE' + success);

                    alert(success);


                })
        },
        function(errorResponse, status) {
            console.log(error.data.errorMessage);
            $scope.message = error.data.errorMessage
            $scope.messageAlert = true;
            var failure = $scope.message;
            console.log('THIS IS THE RESPONSE :' + failure);
            alert(failure);
        }
    )
};
});