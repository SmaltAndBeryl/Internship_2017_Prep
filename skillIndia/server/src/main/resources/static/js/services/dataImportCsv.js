imp.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function (file, csvType, uploadUrl) {
        var fd = new FormData();
        fd.append('csvType', csvType);
        fd.append('file', file);
                
        $http({
        	method: 'POST',
        	url: uploadUrl,
            data: fd,
            headers: {'Content-Type': undefined},
            
            transformRequest: angular.identity,
            transformResponse: [function (data) {
            	console.log(data);
            	thisIsResponse=data;
            	console.log(thisIsResponse);
            	return data;
            }]
        }).then(function(response) {
            
        	console.log("response of success -----");
        	console.log(thisIsResponse);
        	responseOfUpload(thisIsResponse);
            return response.data;
            
        }, function errorCallback(response) {
        	console.log("Error in receiving response from backend------" +response);
            console.log('Error: '+response);
         });
        
    }
}]);