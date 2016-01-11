/**
 * http://usejsdoc.org/
 */
'use strict'

var BASE_PATH="/api";
var BASE_PATH_UPDATE=BASE_PATH+"/menu";
var BASE_PATH_ROLE_MENU=BASE_PATH+"/rolemenu/menu";

var module,dependencies;

dependencies=[];

module=angular.module('UnionApp.menu.service',dependencies);


module.factory('MenuService',function($http){
	var factories={};
	factories.getAvailableMenus=function(){
		return $http.get(BASE_PATH_ROLE_MENU);
	} 
        
        factories.getAllMenu=function(){
            return $http.get(BASE_PATH_UPDATE);
        }
	
	factories.addNewMenu=function(menu){
		return $http.post(BASE_PATH_UPDATE,menu);
	}
	
	factories.deleteMenu=function(id){
		return $http.delete(BASE_PATH_UPDATE);
	}
	
	factories.updateMenu=function(menu){
		return $http.put(BASE_PATH_UPDATE,menu);
	}
	
	return factories;
});