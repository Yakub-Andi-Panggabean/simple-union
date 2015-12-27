/**
 * http://usejsdoc.org/
 */
'user strict'

var module,dependencies;

dependencies=['ngMaterial','ngCookies','UnionApp.goods.service','UnionApp.price.service'];

module=angular.module('UnionApp.price.controller',dependencies);

module.controller('PriceController',function($scope,$cookies,$cookieStore,$mdDialog,$mdToast,GoodsService,PriceService){
	
	$scope.price={
			  goodsName:" ",
			  goodsCode:" ",
			  goodsId: null,
		      price: null,
		      createdBy: null,
		      createdDate: null,
		      updatedBy: null,
		      updatedDate: null
	};
	
	var price={
			  goodsId: null,
		      price: null,
		      createdBy: null,
		      createdDate: null,
		      updatedBy: null,
		      updatedDate: null
	}
	
	  /**
	   * showing notification toast message
	   */
     $scope.showMessage=function(message,position) {
	 $mdToast.show(
			 $mdToast.simple()
			 .textContent(message)
			 .position(position)
			 .hideDelay(3000)
		);
     }
     
     /**
      * goodsDialog
      */
     $scope.showDialog = function(ev) {
		    $mdDialog.show({
		      controller: 'PriceController',
		      templateUrl: 'resources/apps/modules/price/view/GoodsDialog.html',
		      targetEvent: ev,
		    });
		  };
		  
  /**
   * dialog row double click
   */
  $scope.findGoods=function(params){
	  document.getElementById("price-goods-name").value=params.goodsName;
	  document.getElementById("price-goods-code").value=params.goodsCode;
	  document.getElementById("price-goods-id").value=params.goodsId;
	  
	  console.log(params.goodsId);
	  $mdDialog.hide();
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
	 * setting new price
	 */
	$scope.setPrice=function(price){
		$scope.loading=true;
		PriceService.setNewPrice(price)
		.success(function(response){
			 $scope.showMessage("successfully inserted","bottom right");  
		})
		.error(function(data,status){
			console.error('error',data,status);
		})
		.finally(function(){
			console.log('process_done');
			$scope.loading=false;
		});
	}
	
	/**
	 * updating price
	 */
	$scope.updatePrice=function(price){
		$scope.loading=true;
		PriceService.updatePrice(price)
		.success(function(response){
			 $scope.showMessage("successfully updated","bottom right");  
		})
		.error(function(data,status){
			console.error('error',data,status);
		}).
		finally(function(){
			console.log('process_done');
			$scope.loading=false;
		});
	}
	
	/**
	 * deleting price
	 */
	$scope.deletePrice=function(){
		$scope.loading=true;
		PriceService.deletePrice()
		.success(function(response){
			 $scope.showMessage("successfully deleted","bottom right");  
		})
		.error(function(data,status){
			console.error('error',data,status);
		}).
		finally(function(){
			console.log('process_done');
			$scope.loading=false;
		});
	}
	

  /**
   * retrieve all prices
   */
  $scope.retriveAllPrices=function(){
  	return PriceService.findAll()
  	.success(function(response){
  		  $scope.prices=response.contents;
  	})
  	.error(function(data,status){
  		console.error('error',data,status);
  	})
  	.finally(function(){
  		console.log('process_done');
  	});
  }
  
  /**
   * find by id
   */
  $scope.findById=function(id){
	  PriceService.findById(id)
		.success(function(response){
			$scope.stock=response.content;
		})
		.error(function(data,status){
	  		console.error('error',data,status);
	  	})
	  	.finally(function(){
	  		console.log('process_done');
	  	});
  }
  
  /**
   * processing price
   */
  $scope.processingPrice=function(price){
	    price.goodsId= document.getElementById("price-goods-id").value;
		PriceService.findById(price.goodsId)
		.success(function(response){
			price.goodsId=response.content.goodsId;
			price.createdBy=response.content.createdBy;
			price.createdDate=response.content.createdDate;
			price.updatedDate=new Date();
			price.updatedBy=$cookieStore.get('principal');
			console.log(price);
			$scope.updatePrice(price);
		})
		.error(function(data,status){
	  		console.error('error',data,status);
	  		if(status==404){
	  			price.createdDate=new Date();
	  			price.createdBy=$cookieStore.get("principal");
	  			price.updatedDate=null;
	  			price.updatedBy=null;
	  			console.log(price);
	  			$scope.setPrice(price);
	  		}
	  	})
	  	.finally(function(){
	  		console.log('process_done');
	  		$scope.retriveAllPrices();
	  	});
	  
  }

	
});


