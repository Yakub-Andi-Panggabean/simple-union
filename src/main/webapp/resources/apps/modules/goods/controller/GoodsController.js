/**
 * http://usejsdoc.org/
 */
'use strict'

var module,dependencies;

dependencies=['ngMaterial','ngCookies','UnionApp.category.service','UnionApp.supplier.service','UnionApp.goods.service'];

module=angular.module('UnionApp.goods.controller',dependencies);

module.controller('GoodsController',function($cookies,$cookieStore,$mdToast,$scope,CategoryService,SupplierService,GoodsService){
	
	$scope.goods={};
	$scope.goodsList={};
	$scope.categories={};
	$scope.suppliers={};
	$scope.isFalse=false;
	$scope.isTrue=true;
	var goods={
			goodsId: null,
			goodsCode: null,
			goodsName: null,
			brand: null,
			active: 0,
			category: null,
			supplier: null,
			createdDate: null,
			createdBy: null,
			updatedDate: null,
			updatedBy: null	
	};
	
	
	$scope.initializeView=function(){
		$scope.findAllCategories();
		$scope.findAllSuppliers();
		$scope.retrieveAllGoods();
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
	 * retrieving data for categories dropdown
	 */
	$scope.findAllCategories=function(){
		return CategoryService.getAllCategories()
		.success(function(response){
			$scope.categories=response.contents;
			//console.log(response.contents);
		})
		.error(function(data,status){
			console.error('error',data,status);
		}).finally(function(){
			console.log('process_done');
		});
	}
	
	/**
	 *  retrieving data for supplier dropdown
	 */
	$scope.findAllSuppliers=function(){
		return SupplierService.findAllSuppliers()
		.success(function(response){
			$scope.suppliers=response.contents;
			//console.log(response.contents);
		})
		.error(function(data,status){
			console.error('error',data,status);
		}).finally(function(){
			console.log('process_done');
		});
	}
	
	/**
	 * retrieve all goods
	 */
	$scope.retrieveAllGoods=function(){
		return GoodsService.findAllGoods()
		.success(function(response){
			  $scope.goodsList=response.contents;
		})
		.error(function(data,status){
			console.error('error',data,status);
		})
		.finally(function(){
			console.log('process_done');
		});
	}
	
	/**
	 * add new goods
	 */
	$scope.addNewGoods=function(){
		if($scope.goods.goodsCode==''||$scope.goods.goodsCode==null){
			$scope.showMessage("goods code may not be empty","bottom right"); 
		}else if($scope.goods.goodsName==''||$scope.goods.goodsName==null){
			$scope.showMessage("goods name may not be empty","bottom right"); 
		}else if($scope.goods.supplier==''||$scope.goods.supplier==null){
			$scope.showMessage("goods supplier may not be empty","bottom right"); 
		}else if($scope.goods.category==''||$scope.goods.category==null){
			$scope.showMessage("goods category may not be empty","bottom right"); 
		}else{
			$scope.loading=true;
			goods.goodsId= null;
			goods.goodsCode= $scope.goods.goodsCode;
			goods.goodsName= $scope.goods.goodsName;
			goods.brand= $scope.goods.brand;
			goods.active= $scope.goods.active;
			goods.category= $scope.goods.category;
			goods.supplier= $scope.goods.supplier;
			goods.createdDate=new Date();
			goods.createdBy=$cookieStore.get('principal');
			goods.updatedDate=null;
			goods.updatedBy=null;	
			GoodsService.addNewGoods(goods)
			.success(function(response){
				 $scope.showMessage("successfully inserted","bottom right"); 
				 $scope.retrieveAllGoods();
			})
			.error(function(data,status){
				console.log('error',data,status);
			})
			.finally(function(){
				console.log("finish inserting goods");
				$scope.loading=false;
			});
		}
	}
	
	/**
	 * update goods
	 */
	$scope.updateGoods=function(params,active){
		$scope.loading=true;
		goods.goodsId= params.goodsId;
		goods.goodsCode= params.goodsCode;
		goods.goodsName= params.goodsName;
		goods.brand= params.brand;
		if(active ==1||active ==0){
			goods.active=$scope.switchChange(active);
		}else{
			goods.active=params.active;
		}
		if(params.newCategoryId==null){
			goods.category= params.categoryId;
		}else{
			goods.category= params.newCategoryId;
		}
		if(params.newSupplierId==null){
			goods.supplier= params.supplierId;
		}else{
			goods.supplier=params.newSupplierId;
		}
		goods.createdDate=params.createdDate;
		goods.createdBy=params.createdBy;
		goods.updatedDate=new Date();
		goods.updatedBy=$cookieStore.get('principal');	
		GoodsService.updateGoods(goods)
		.success(function(response){
			if(active==0||active==1){
				if(active==0){
					 $scope.showMessage("Goods Activated","bottom right");  
				}else{
					 $scope.showMessage("Goods Deactivated","bottom right");  
				}
			}else{
				 $scope.showMessage("successfully updated","bottom right");  
			}
			$scope.retrieveAllGoods();
		})
		.error(function(data,status){
			console.log('error',data,status);
		})
		.finally(function(){
			console.log("finish updated goods");
			$scope.loading=false;
		});
	}
	
	/**
	 * delete goods
	 */
	$scope.deleteGoods=function(goodsId){
		$scope.loading=true;
		GoodsService.deleteGoods(goodsId)
		.success(function(response){
			 $scope.showMessage("successfully deleted","bottom right");
			 $scope.retrieveAllGoods();
		})
		.error(function(data,status){
			console.log('error',data,status);
		})
		.finally(function(){
			console.log("finish delete goods");
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
	
	  $scope.findGoods=function(){
		  console.log("hit");
	  }
	
});