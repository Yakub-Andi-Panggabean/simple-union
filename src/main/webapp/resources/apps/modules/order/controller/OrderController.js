/**
 * http://usejsdoc.org/
 */

'use strict'

var module,dependencies;

dependencies=['ngMaterial','ngCookies','UnionApp.order.service','UnionApp.goods.service','UnionApp.price.service','UnionApp.stock.service'];

module=angular.module('UnionApp.order.controller',dependencies);

module.controller('OrderController',function($scope,$cookies, $mdDialog,$cookieStore,$mdToast,GoodsService,OrderService,PriceService,StockService){

	$scope.orderDetails=new Array();
	$scope.currencySymbol = 'RP.';//the currency
	$scope.grandTotal=0;
	
	$scope.order={
			  goodsName:" ",
			  goodsCode:" ",
			  goodsId: null,
			  transactionNumber:null,
			  quantity:0,
			  price:0,
			  totalPrice:0,
			  grandTotal:0
	};
	
	//initialize order object
	var orderValue={
			orderId: null,
			transactionNumber: null,
			totalPayment: null,
		    transactionDateTime:null,
			user:null,
			orderDetails:{} 
	}
	
	/**
	 * toast messaging
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
     * show goods dialog
     */
    $scope.showDialog = function(ev) {
		    $mdDialog.show({
		      controller: 'OrderController',
		      templateUrl: 'resources/apps/modules/order/view/GoodsDialog.html',
		      targetEvent: ev,
		    });
		  };
		  
		  
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
	 * find price by goods id
	 */
	$scope.generateKey=function(){
		return OrderService.generateKey()
		.success(function(response){
			$scope.order.transactionNumber=response.content;
		})
		.error(function(response){
			console.error('error',data,status);
		})
		.finally(function(){
			console.log('process_done');
		});
	}

	
	 /**
	   * dialog row double click
	   */
	  $scope.findGoods=function(params){
		  var price=0;
		  document.getElementById("order-goods-name").value=params.goodsName;
		  document.getElementById("order-goods-code").value=params.goodsCode;
		  document.getElementById("order-goods-id").value=params.goodsId;
		
		  PriceService.findById(params.goodsId)
			.success(function(response){
				  document.getElementById("order-goods-price").value=response.content.price;
			})
			.error(function(data,status){
					console.error('error',data,status);
			})
			.finally(function(){
					console.log('process_done');
			});
		  
		  $mdDialog.hide();
	  }		  
	  
	  
	  /*
	   * push array
	   */
	  $scope.pushArray=function(params){
		  $scope.orderDetails.push({
			  orderId:null,
			  quantity:params.quantity,
			  goodsName:params.goodsName,
			  goodsCode:params.goodsCode,
			  goodsId:params.goodsId,
			  price:params.price,
			  totalPrice:params.totalPrice
		  });
	  }
	  
	  /**
	   * push new product to order detail
	   */
	  $scope.pushDetail=function(params){
		 
		  var i;
		  params.goodsName=document.getElementById("order-goods-name").value;
		  params.goodsCode=document.getElementById("order-goods-code").value;
		  params.goodsId=document.getElementById("order-goods-id").value;
		  params.price=document.getElementById("order-goods-price").value;
		  params.totalPrice=params.price * params.quantity;

		  StockService.findStockById(params.goodsId)//find has goods stock been set or not 
		  .success(function(response){
			  
			  var duplicate=false;
			  var quantity=0;
			  var totalPrice=0;
			  var grandTotal=0;
			  if($scope.orderDetails.length>0){
				  for(i=0;i<$scope.orderDetails.length;i++){
					  if($scope.orderDetails[i].goodsId===params.goodsId){
						 duplicate=true;
						 quantity=parseInt($scope.orderDetails[i].quantity)+parseInt(params.quantity);
						 totalPrice=parseInt($scope.orderDetails[i].totalPrice)+parseInt(params.totalPrice);
						 $scope.orderDetails.splice(i,1);
						 params.quantity=quantity;
						 params.totalPrice=totalPrice;
						 $scope.pushArray(params);
						 break;
					  }
				  }
			  }
			  
			  console.log(quantity);
			  if(!duplicate){
				  $scope.pushArray(params);
			  }
			  
			  for(i=0;i<$scope.orderDetails.length;i++){
				  grandTotal=grandTotal+parseInt($scope.orderDetails[i].totalPrice);
			  }
			  
			  $scope.grandTotal=grandTotal; 
			  params.quantity=0;
			  
		  })
		  .error(function(data,status){
				if(status === 404){
					$scope.showMessage("stock is not set for this item, please check stock stock module first","bottom right"); 
				}
		  })
			 .finally(function(){
					console.log('process_done');
		  });
		  
	  }
	  
	  
	  /**
	   * pop canceled product from product detail
	   */
	  $scope.popOrderDetail=function(goodsId){
		  var i;
		  for(i=0;i<$scope.orderDetails.length;i++){
			  if(goodsId===$scope.orderDetails[i].goodsId){
				  $scope.grandTotal=parseInt($scope.grandTotal)-parseInt($scope.orderDetails[i].totalPrice);
				  $scope.orderDetails.splice(i,1);
			  }
		  }
	  }	
	  
	  
	  /*
	   * save order 
	   */
	  $scope.saveOrder=function(){
		    //orderId: null,
			//transactionNumber: null,
			orderValue.totalPayment= $scope.grandTotal;
		    orderValue.transactionDateTime=new Date();
			orderValue.user=$cookieStore.get("principal");
			orderValue.orderDetails=$scope.orderDetails;
			console.log(orderValue);
			
			OrderService.doTransaction(orderValue)
			.success(function(response){
				 $scope.orderDetails.length=0;
				 $scope.grandTotal=0;
				 $scope.generateKey();
				 $scope.showMessage("transaction success","bottom right"); 
				 window.location.assign('/ReportingServlet?c=PT.XXXX&i='+response.content);
			})
			.error(function(data,status){
				$scope.showMessage("transaction failed","bottom right"); 
				console.log('error',data,status);
			})
			.finally(function(){
				console.log("finish doing transaction");
			});
	  }
	
});
