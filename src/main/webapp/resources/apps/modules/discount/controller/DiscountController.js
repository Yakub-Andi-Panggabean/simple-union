/**
 * http://usejsdoc.org/
 */
var module,dependencies;

dependencies=['ngMaterial','ngCookies','UnionApp.discount.service','UnionApp.goods.service'];

module=angular.module('UnionApp.discount.controller',dependencies);

module.controller('DiscountController',function($scope,$cookies, $mdDialog,$cookieStore,$mdToast,DiscountService,GoodsService){
	
	/**
	 * init binding scope
	 */
	$scope.discount={
			goodsName:" ",
			goodsCode:" ",
			goodsId: null,
			start: null,
			end: null,
			discount: null,
			description: null,
			createdDate: null,
			createdBy: null,
			updatedDate: null,
			updatedBy: null
	}
	
	/**
	 * showing toast message
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
		   * show goods dialog
		   */
		  $scope.showDialog = function(ev) {
			    $mdDialog.show({
			      controller: 'DiscountController',
			      templateUrl: 'resources/apps/modules/discount/view/GoodsDialog.html',
			      targetEvent: ev,
			    });
			  };

			  
			  /**
			   * dialog row double click
			   */
			  $scope.findDiscountGoods=function(params){
				  document.getElementById("discount-goods-name").value=params.goodsName;
				  document.getElementById("discount-goods-code").value=params.goodsCode;
				  document.getElementById("discount-goods-id").value=params.goodsId;
				  DiscountService.findById(params.goodsId)
					.success(function(response){
						
					})
					.error(function(data,status){
						console.error('error',data,status);
					})
					.finally(function(){
						console.log('discount_process_done');
					});
				  console.log(params.goodsId);
				  $mdDialog.hide();
			  }
			  
			  
			  $scope.myDate = new Date();
			  
			  $scope.minDate = new Date(
			      $scope.myDate.getFullYear(),
			      $scope.myDate.getMonth() - 2,
			      $scope.myDate.getDate());
			  
			  $scope.maxDate = new Date(
			      $scope.myDate.getFullYear(),
			      $scope.myDate.getMonth() + 2,
			      $scope.myDate.getDate());

			  /**
			   * set new discount
			   */
			  $scope.setNewDiscount=function(discount){
					 $scope.loading=true;
					 
					 DiscountService.addNewDiscount(discount)
						 .success(function(response){
							 $scope.showMessage("successfully inserted","bottom right");  
						     $scope.retrieveAllSuppliers();
						 })
						 .error(function(data,status){
							 console.error('error', status, data);
						 })
						 .finally(function(){
							  console.log("finish inserting discount");
							  $scope.loading=false;
						 });
					 }
				 
	
});