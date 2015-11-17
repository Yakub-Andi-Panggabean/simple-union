/**
 * http://usejsdoc.org/
 */
'use strict'

var module,dependencies;

dependencies=[];

module=angular.module('UnionApp.menu.service',dependencies);

module.factory('MenuService',function($http){
	var factories={};
	factories.getAvailableMenus=function(){
		return $http.get('/api/menu');
	}
	return factories;
});