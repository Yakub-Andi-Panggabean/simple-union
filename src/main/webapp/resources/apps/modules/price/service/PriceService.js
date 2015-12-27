/**
 * http://usejsdoc.org/
 */
'use strict'

var BASE_PATH="/api/price";

var module,dependencies;

dependencies=[];

module=angular.module('UnionApp.price.service',dependencies);

module.factory('PriceService',function($http){
	
	var factories={};
	
	factories.setNewPrice=function(price){
		return $http.post(BASE_PATH,price);
	}
	
	factories.updatePrice=function(price){
		return $http.put(BASE_PATH,price);
	}
	
	factories.deletePrice=function(goodsId){
		return $http.delete(BASE_PATH+"/"+goodsId);
	}
	
	factories.findById=function(goodsId){
		return $http.get(BASE_PATH+"/"+goodsId);
	}
	
	factories.findAll=function(){
		return $http.get(BASE_PATH);
	}
	
	return factories;
	
});
