/**
 * http://usejsdoc.org/
 */
'use strict'

var module,dependencies;

dependencies=['ngRoute'];

module=angular.module('NavigationModule',dependencies);

module.config(['$routeProvider',//'$locationProvider',
  function($routeProvider,$locationProvider) {
    $routeProvider.
      when('/master/menu', {
        templateUrl: 'resources/pages/purchase.html'//,
        //controller: 'PhoneListCtrl'
      }).
      when('/master/order', {
        templateUrl: 'resources/pages/order.html'//,
        //controller: 'PhoneDetailCtrl'
      }).
      otherwise({
        redirectTo: 'resources/pages/purchase.html'
      });
   
    //$locationProvider.html5Mode(true);
    //$locationProvider.hashPrefix('!');

  }]);