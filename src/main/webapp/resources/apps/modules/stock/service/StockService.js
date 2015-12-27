/**
 * http://usejsdoc.org/
 */
'use strict'

var BASE_PATH="/api";
var BASE_PATH_STOCK=BASE_PATH+"/stock";

var module,dependencies;

dependencies=[];

module=angular.module('UnionApp.stock.service',dependencies);

module.factory('StockService',function($http){
	
	var factories={};
	
	factories.addNewStock=function(stock){
		return $http.post(BASE_PATH_STOCK,stock);
	}
	
	factories.updateStock=function(stock){
		return $http.put(BASE_PATH_STOCK,stock);
	}
	
	factories.deleteStock=function(goodsId){
		return $http.delete(BASE_PATH_STOCK+"/"+goodsId);
	}
	
	factories.findStockById=function(goodsId){
		return $http.get(BASE_PATH_STOCK+"/"+goodsId);
	}
	
	factories.findAllStocks=function(){
		return $http.get(BASE_PATH_STOCK);
	}
	
	return factories;
});