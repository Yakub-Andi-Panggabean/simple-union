/**
 * http://usejsdoc.org/
 */
'user strict'

var module,dependencies;

dependencies=['ngMaterial','ngCookies','UnionApp.supplier.service'];

module=angular.module('UnionApp.supplier.controller',dependencies);

module.controller('MasterSupplierController',function($scope,$cookies,$cookieStore,$mdToast,SupplierService){
	
	$scope.suppliers;
	$scope.isFalse=false;
	$scope.isTrue=true;
	$scope.supplier = {};
	
	var supplier={
			supplierId:null,
			supplierName:null,
			pic:null,
			address:null,
			active:0,
			createdDate:null,
			createdBy:null,
			updatedDate:null,
			updatedBy:null
	};
	
	 $scope.showMessage=function(message,position) {
		    $mdToast.show(
		      $mdToast.simple()
		        .textContent(message)
		        .position(position)
		        .hideDelay(3000)
		    );
		  }
	 
	 $scope.addNewSupplier=function(){
		 $scope.loading=true;
		 if($scope.supplier.supplierName == ""||$scope.supplier.supplierName == null){
				$scope.showMessage("Name may not be empty","bottom right");  
				$scope.loading=false;
		 }else if($scope.supplier.pic == ""||$scope.supplier.pic == null){
			 $scope.showMessage("Pic may not be empty","bottom right");
			 $scope.loading=false;
		 }else if($scope.supplier.address == ""||$scope.supplier.address == null){
			 $scope.showMessage("Address may not be empty","bottom right");
			 $scope.loading=false;
		 }else{
			 supplier.supplierId=null;
			 supplier.supplierName=$scope.supplier.supplierName;
			 supplier.pic=$scope.supplier.pic;
			 supplier.address=$scope.supplier.address;
			 supplier.active=0;
			 supplier.createdDate=new Date();
			 supplier.createdBy=$cookieStore.get("principal");
			 supplier.updatedDate=null;
			 supplier.updatedBy=null;
			 
			 SupplierService.addNewSupplier(supplier)
			 .success(function(response){
				 $scope.showMessage("successfully inserted","bottom right");  
			     $scope.retrieveAllSuppliers();
			 })
			 .error(function(data,status){
				 console.error('error', status, data);
			 })
			 .finally(function(){
				  console.log("finish inserting supplier");
				  $scope.loading=false;
			 });
		 }
	 }

	 /**
		 * retrieve all supplier
		 */
		$scope.retrieveAllSuppliers=function(){
			SupplierService.findAllSuppliers().then(function(response){
		   	  //console.log(response.data.contents);
		   	  $scope.suppliers=response.data.contents;
		   });
		}
		
		/**
		 * delete supplier
		 */
		$scope.deleteSupplier=function(supplierId){
			$scope.loading=true;
			SupplierService.deleteSupplier(supplierId)
			.success(function(response){
				 $scope.showMessage("deleted successfully","bottom right");  
			     $scope.retrieveAllSuppliers();
			}).error(function(data,status){
				  console.error('error', status, data);
			}).finally(function(){
				  console.log("finish inserting category");
				  $scope.loading=false;
			});
		}
		
		/**
		 * updated supplier
		 */
		$scope.updateSupplier=function(params,active){		
				$scope.loading=true;
				supplier.supplierId=params.supplierId;
				supplier.supplierName=params.supplierName;
				if(active ==1||active ==0){
					supplier.active=$scope.switchChange(active);
				}else{
					supplier.active=params.active;
				}
				 supplier.supplierName=params.supplierName;
				 supplier.pic=params.pic;
				 supplier.address=params.address; 
				 supplier.createdDate=params.createdDate;
				 supplier.createdBy=params.createdBy;
				 supplier.updatedDate=new Date;
				 supplier.updatedBy=$cookieStore.get('principal');
				
				console.log(supplier);
				SupplierService.updateSupplier(supplier)
				.success(function(response){
					if(active==0||active==1){
						if(active==0){
							 $scope.showMessage("Supplier Activated","bottom right");  
						}else{
							 $scope.showMessage("Supplier Deactivated","bottom right");  
						}
					}else{
						 $scope.showMessage("successfully updated","bottom right");  
					}
				     $scope.retrieveAllSuppliers();
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