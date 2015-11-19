/**
 * http://usejsdoc.org/
 */
'use strict'

var module,dependencies;

dependencies=['ngRoute'];

module=angular.module('UnionApp.menu.navigation',dependencies);

module.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/phones', {
        templateUrl: 'resources/pages/purchase.html',
        controller: 'PhoneListCtrl'
      }).
      when('/phones/:phoneId', {
        templateUrl: 'resources/pages/order.html',
        controller: 'PhoneDetailCtrl'
      }).
      otherwise({
        redirectTo: 'resources/pages/purchase.html'
      });
  }]);