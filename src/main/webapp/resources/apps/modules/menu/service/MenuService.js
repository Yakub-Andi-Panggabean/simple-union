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
		return $http.get('/api/rolemenu/menu/6dc5acb6c869423fbdd014b979c04cef');
	}
	return factories;
});