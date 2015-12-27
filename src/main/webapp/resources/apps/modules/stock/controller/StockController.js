/**
 * http://usejsdoc.org/
 */
var module,dependencies;

dependencies=['ngMaterial','ngCookies','UnionApp.goods.service','UnionApp.stock.service'];
module=angular.module('UnionApp.stock.controller',dependencies);

module.controller('StockRootController', function($scope,$cookies,$cookieStore,$mdDialog,$mdToast,GoodsService,StockService) {
	
	  $scope.stock={
			  goodsName:" ",
			  goodsCode:" ",
			  maxQuantity:" ",
			  location:" ",
			  subLocation:" "
	  };
	  
	  $scope.id="";
	  
	  var stock={
			    goodsId:null,
			    goodsName:null,
				goodsCode:null,
				quantity: 0,
				maxQuantity: 0,
				location: null,
				subLocation: null,
				createdDate: null,
				createdBy: null,
				updatedDate: null,
				updatedBy: null
	  };
	
	  $scope.myDate = new Date();
	  
	  $scope.minDate = new Date(
	      $scope.myDate.getFullYear(),
	      $scope.myDate.getMonth() - 2,
	      $scope.myDate.getDate());
	  
	  $scope.maxDate = new Date(
	      $scope.myDate.getFullYear(),
	      $scope.myDate.getMonth() + 2,
	      $scope.myDate.getDate());
	  
	  $scope.onlyWeekendsPredicate = function(date) {
	    var day = date.getDay();
	    return day === 0 || day === 6;
	  }
	  
	  $scope.showDialog = function(ev) {
		    $mdDialog.show({
		      controller: 'StockRootController',
		      templateUrl: 'resources/apps/modules/stock/view/GoodsDialog.html',
		      targetEvent: ev,
		    });
		  };
		  
	
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
		   * dialog row double click
		   */
		  $scope.findGoods=function(params){
			  document.getElementById("stock-goods-name").value=params.goodsName;
			  document.getElementById("stock-goods-code").value=params.goodsCode;
			  document.getElementById("stock-goods-id").value=params.goodsId;
			  StockService.findStockById(params.goodsId)
				.success(function(response){
					console.log(response.content.maxQuantity);
					 document.getElementById("stock-max-quantity").value=response.content.maxQuantity;
					 document.getElementById("stock-location").value=response.content.location;
					 document.getElementById("stock-sub-location").value=response.content.subLocation;
				})
				.error(function(data,status){
					console.error('error',data,status);
				})
				.finally(function(){
					console.log('stock_process_done');
				});
			  //$scope.stock.goodsId=params.goodsId;
			  //$scope.stock.goodsName=params.goodsName;
			  //$scope.stock.goodsCode=params.goodsCode;
			  //$scope.id=params.goodsId;
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
			
			$scope.retriveAllStocks=function(){
				return StockService.findAllStocks()
				.success(function(response){
					  $scope.stocks=response.contents;
				})
				.error(function(data,status){
					console.error('error',data,status);
				})
				.finally(function(){
					console.log('process_done');
				});
			}
			
			/**
			 * inserting new stock
			 */ 
			$scope.addNewStock=function(stock){
				$scope.loading=true;
				StockService.addNewStock(stock)
				.success(function(response){
					 $scope.showMessage("successfully inserted","bottom right");  
				})
				.error(function(data,status){
					console.error('error',data,status);
				})
				.finally(function(){
					$scope.retriveAllStocks();
					console.log('process_done');
					$scope.loading=false;
				});
			}
			
			/**
			 * updating stock
			 */ 
			$scope.updateStock=function(stock){
				$scope.loading=true;
				
				StockService.updateStock(stock)
				.success(function(response){
					 $scope.showMessage("successfully updated","bottom right");  
				})
				.error(function(data,status){
					console.error('error',data,status);
				})
				.finally(function(){
					$scope.retriveAllStocks();
					console.log('process_done');
					$scope.loading=false;
				});
			}
			
			
			$scope.processingStock=function(stock){
				var id=document.getElementById("stock-goods-id").value;
				var name=document.getElementById("stock-goods-name").value;
				var code=document.getElementById("stock-goods-code").value;
				var maxQuantity=document.getElementById("stock-max-quantity").value;
				var location=document.getElementById("stock-location").value;
				var subLocation=document.getElementById("stock-sub-location").value;
				stock.goodsId=id.trim();
				stock.goodsName=name;
				stock.goodsCode=code;
				stock.maxQuantity=maxQuantity;
				stock.location=location;
				stock.subLocation=subLocation;
				if(parseInt(stock.quantity) > parseInt(stock.maxQuantity)){
					$scope.showMessage("quantity is greater than max quantity","bottom right"); 
				}else{
					StockService.findStockById(stock.goodsId)
					.success(function(response){
						//console.log(response);
						var recentQuantity=parseInt(response.content.quantity);
						stock.quantity=parseInt(stock.quantity)+recentQuantity;
						//console.log(stock);
						if(parseInt(stock.quantity) > parseInt(stock.maxQuantity)){
							stock.quantity=stock.quantity;
							$scope.showMessage("quantity is greater than max quantity","bottom right"); 
						}else{
							stock.updatedDate=new Date();
							stock.updatedBy=$cookieStore.get("principal");
							$scope.updateStock(stock);
						}
					})
					.error(function(data,status){
						//console.error('error',data,status);
						if(status === 404){//if http status 404 means that there is no record in database
							stock.createdDate=new Date();
							stock.createdBy=$cookieStore.get("principal");
							$scope.addNewStock(stock);
						}
					})
					.finally(function(){
						console.log('stock_process_done');
					});
				}
			}
	  
});