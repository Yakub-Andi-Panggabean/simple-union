/**
 * http://usejsdoc.org/
 */

'use strict'

var BASE_PATH="/api";
var BASE_PATH_SUPPLIER=BASE_PATH+"/supplier";

var module,dependencies;

dependencies=[];

module=angular.module('UnionApp.supplier.service',dependencies);

module.factory('SupplierService',function($http){
	var factories={};
	
	factories.findAllSuppliers=function(){
		return $http.get(BASE_PATH_SUPPLIER);
	}
	
	factories.findCategoriesById=function(id){
		return $http.get(BASE_PATH_SUPPLIER+"/"+id);
	}
	
	factories.addNewSupplier=function(supplier){
		return $http.post(BASE_PATH_SUPPLIER,supplier);
	}
	
	factories.updateSupplier=function(supplier){
		return $http.put(BASE_PATH_SUPPLIER,supplier);
	}
	
	factories.deleteSupplier=function(id){
		return $http.delete(BASE_PATH_SUPPLIER+"/"+id);
	}
	
	return factories;
});

