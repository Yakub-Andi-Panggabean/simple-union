/**
 * http://usejsdoc.org/
 */

'use strict'

var BASE_PATH="/api";
var BASE_PATH_ORDER=BASE_PATH+"/transaction";

var module,dependencies;

dependencies=[];

module=angular.module('UnionApp.order.service',dependencies);

module.factory('OrderService',function($http){
	
	var factories={};
	
	factories.doTransaction=function(transaction){
		return $http.post(BASE_PATH_ORDER,transaction);
	}
		
	factories.deleteTransaction=function(transactionId){
		return $http.delete(BASE_PATH_ORDER+"/"+transactionId);
	}
	
	factories.findTransaction=function(transactionId){
		return $http.get(BASE_PATH_ORDER+"/"+transactionId);
	}
	
	factories.findAllTransaction=function(){
		return $http.get(BASE_PATH_ORDER);
	}
	
	factories.generateKey=function(){
		return $http.get(BASE_PATH_ORDER+"/code");
	}
	
	return factories;
	
});