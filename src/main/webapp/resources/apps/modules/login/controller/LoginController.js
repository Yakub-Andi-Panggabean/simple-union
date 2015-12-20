/**
 * http://usejsdoc.org/
 */
'use strict'

var module,dependency;

dependency=['ngCookies','UnionApp.auth.service'];

module=angular.module('UnionApp.login.controller',dependency);

module.controller('LoginController',function($scope,$cookies,$cookieStore,BasicAuthService){
   
	$scope.signin=function(){
		var username=$scope.username;
		var password=$scope.password;
		$cookieStore.remove("credential");
		var token=BasicAuthService.encode(username + ':' + password);
		token='Basic '+token;
		console.log(username+ ':' +password);
		console.log(token);
		//$location.path('/home');
		$cookieStore.put("credential", token);
		$cookieStore.put("principal",username);
		window.location='/home';
	}
	
	
	//decoding basic token
	//BasicAuthService.decode(input);
});