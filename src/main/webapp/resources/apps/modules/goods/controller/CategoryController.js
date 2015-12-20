/**
 * http://usejsdoc.org/
 */
var module,dependencies;

dependencies=['ngMaterial','ngCookies','UnionApp.category.service'];

module=angular.module('UnionApp.category.controller',dependencies);

module.controller('MasterCategoryController',function($scope,$cookies, $mdDialog,$cookieStore,$mdToast,CategoryService){
	
	$scope.categories;
	$scope.category={};
	$scope.isFalse=false;
	$scope.isTrue=true;
	
	var category={
			categoryId:null,
			categoryName:null,
			active:0,
			createdDate:null,
			createdBy:null,
			updatedDate:null,
			updatedBy:null
	}
	
	 $scope.showMessage=function(message,position) {
	    $mdToast.show(
	      $mdToast.simple()
	        .textContent(message)
	        .position(position)
	        .hideDelay(3000)
	    );
	  }

	/**
	 * adding new category
	 */
	$scope.addNewCategory=function(){		
		if($scope.category.name ==""||$scope.category.name ==null){
			$scope.showMessage("Name may not be empty","bottom right");  
		}else{
			$scope.loading=true;
			category.categoryName=$scope.category.name;
			category.active=0;
			category.createdDate=new Date();
			category.createdBy=$cookieStore.get("principal");
			
			//console.log(category);
			CategoryService.addNewCategory(category)
			.success(function(response){
				 $scope.showMessage("successfully inserted","bottom right");  
			     $scope.retrieveAllCategories();
			}).error(function(data, status) {
			    console.error('error', status, data);
			}).finally(function() {
			    console.log("finish inserting category");
			    $scope.loading=false;
			});
		}
	}
	
	
	/**
	 * retrieve all categories
	 */
	$scope.retrieveAllCategories=function(){
	   CategoryService.getAllCategories().then(function(response){
	   	  //console.log(response.data.contents);
	   	  $scope.categories=response.data.contents;
	   });
	}
	
	/**
	 * delete categories
	 */
	$scope.deleteCategory=function(categoryId){
		$scope.loading=true;
		CategoryService.deleteCategory(categoryId)
		.success(function(response){
			 $scope.showMessage("successfully deleted","bottom right");  
		     $scope.retrieveAllCategories();
		}).error(function(data,status){
			  console.error('error', status, data);
		}).finally(function(){
			  console.log("finish inserting category");
			  $scope.loading=false;
		});
	}
	
	/**
	 * updated new category
	 */
	$scope.updateCategory=function(params,active){		
			$scope.loading=true;
			category.categoryId=params.categoryId;
			category.categoryName=params.categoryName;
			if(active ==1||active ==0){
				category.active=$scope.switchChange(active);
			}else{
				category.active=params.active;
			}
			category.createdDate=params.createdDate;
			category.createdBy=params.createdBy;
			category.updatedDate=new Date();
			category.updatedBy=$cookieStore.get("principal");
			
			console.log(category);
			CategoryService.updateCategory(category)
			.success(function(response){
				if(active==0||active==1){
					if(active==0){
						 $scope.showMessage("Category Activated","bottom right");  
					}else{
						 $scope.showMessage("Category Deactivated","bottom right");  
					}
				}else{
					 $scope.showMessage("successfully updated","bottom right");  
				}
			     $scope.retrieveAllCategories();
			}).error(function(data, status) {
			    console.error('error', status, data);
			}).finally(function() {
			    console.log("finish updating category");
			    $scope.loading=false;
			});
		}
	
	$scope.switchChange=function(active){
		if(active==0){
			active=1;
		}else{
			active=0;
		}
		return active;
	}
});