/**
 * http://usejsdoc.org/
 */
var module,dependencies;

dependencies=['ngMaterial','UnionApp.menu.service'];

module=angular.module('UnionApp.menu.addcontroller',dependencies);

module.controller('MasterMenuController', function($scope, $mdDialog,MenuService) {
	
	 $scope.showAdd = function(ev) {
		    $mdDialog.show({
		      locals: { name: 'Bob' },
		      controller: 'DialogController',
		      templateUrl: 'resources/apps/modules/menu/view/MenuAdd.html',
		      targetEvent: ev,
		    });
		  };
	 
});

module.controller('DialogController', function($scope,MenuService) {
		    	  
		    	  //menuData.label= $scope.label;
		    	  
		    	  $scope.addNewMenu=function(){
		    		  var menuData={
				  			    'menuId': '',
				  			    'label': $scope.label,
				  			    'parent': $scope.parent,
				  			    'relativeUrl': $scope.relativeUrl,
				  			    'active': 1,
				  			    'createdDate': '2012-12-12',
				  			    'createdBy': 'xxx',
				  			    'updatedDate': null,
				  			    'updatedBy': null
				  			};
		    		  
		    		  console.log('add new menu'+ JSON.stringify(menuData));
		    		  MenuService.addNewMenu(menuData).then(function(response){
	    				  console.log("response ----> "+JSON.stringify(response.status));
	    				  if(response.status===200){
	    					  if(response.data.success===true){
	    						  alert("successfully inserted");  
	    					  }else{
	    						  alert(response.data.errorMessages);
	    					  }
	    				  }else{
	    					  alert(response.data.errorMessages);
	    				  }
	    			  });
		    	  }
	 
});