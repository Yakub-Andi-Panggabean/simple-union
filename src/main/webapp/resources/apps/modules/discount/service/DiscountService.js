/**
 * http://usejsdoc.org/
 */

var BASE_PATH="/api";
var BASE_PATH_DISCOUNT=BASE_PATH+"/discount";

var module,dependencies;

dependencies=[];

module=angular.module('UnionApp.discount.service',dependencies);

module.factory('DiscountService',function($http){
	var factories={};
	
	
	factories.getAll=function(){
		return $http.get(BASE_PATH_DISCOUNT);
	}
	
	factories.findById=function(id){
		return $http.get(BASE_PATH_DISCOUNT+"/"+id);
	}
	
	factories.addNewDiscount=function(category){
		return $http.post(BASE_PATH_DISCOUNT,category);
	}
	
	factories.updateDiscount=function(category){
		return $http.put(BASE_PATH_DISCOUNT,category);
	}
	
	factories.deleteDiscount=function(id){
		return $http.delete(BASE_PATH_DISCOUNT+"/"+id);
	}
	
	return factories;
});
