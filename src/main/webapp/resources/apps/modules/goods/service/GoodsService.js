/**
 * http://usejsdoc.org/
 */
'user strict'

var BASE_PATH="/api";
var BASE_PATH_GOODS=BASE_PATH+"/goods";

var module,dependencies;

dependencies=[];

module=angular.module('UnionApp.goods.service',dependencies);


module.factory('GoodsService',function($http){
	var factories={};
	
	factories.addNewGoods=function(goods){
		return $http.post(BASE_PATH_GOODS,goods);
	}
	
	factories.updateGoods=function(goods){
		return $http.put(BASE_PATH_GOODS,goods);
	}
	
	factories.deleteGoods=function(goodsId){
		return $http.delete(BASE_PATH_GOODS+"/"+goodsId);
	}
	
	factories.findGoodsById=function(goodsId){
		return $http.get(BASE_PATH_GOODS+"/"+goodsId);
	}
	
	factories.findAllGoods=function(){
		return $http.get(BASE_PATH_GOODS);
	}
	
	return factories;
	
});

