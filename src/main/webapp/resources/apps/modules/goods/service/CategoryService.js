/**
 * http://usejsdoc.org/
 */

var BASE_PATH="/api";
var BASE_PATH_CATEGORY=BASE_PATH+"/category";

var module,dependencies;

dependencies=[];

module=angular.module('UnionApp.category.service',dependencies);

module.factory('CategoryService',function($http){
	var factories={};
	
	
	factories.getAllCategories=function(){
		return $http.get(BASE_PATH_CATEGORY);
	}
	
	factories.getCategoryById=function(id){
		return $http.get(BASE_PATH_CATEGORY+"/"+id);
	}
	
	factories.addNewCategory=function(category){
		return $http.post(BASE_PATH_CATEGORY,category);
	}
	
	factories.updateCategory=function(category){
		return $http.put(BASE_PATH_CATEGORY,category);
	}
	
	factories.deleteCategory=function(id){
		return $http.delete(BASE_PATH_CATEGORY+"/"+id);
	}
	
	return factories;
});
