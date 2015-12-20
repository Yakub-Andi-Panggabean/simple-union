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
        templateUrl: 'resources/apps/modules/menu/view/MasterMenu.html'//,
        //controller: 'PhoneListCtrl'
      }).
      when('/master/order', {
        templateUrl: 'resources/apps/modules/order/view/order.html'//,
        //controller: 'PhoneDetailCtrl'
      }).
      when('/master/goods', {
        templateUrl: 'resources/apps/modules/goods/view/goods.html'//,
        //controller: 'PhoneDetailCtrl'
      }).
      when('/master/category', {
          templateUrl: 'resources/apps/modules/goods/view/category.html'//,
          //controller: 'PhoneDetailCtrl'
      }).
      when('/master/stock', {
        templateUrl: 'resources/apps/modules/stock/view/stock.html'//,
        //controller: 'PhoneDetailCtrl'
      }).
      when('/master/supplier', {
          templateUrl: 'resources/apps/modules/supplier/view/supplier.html'//,
          //controller: 'PhoneDetailCtrl'
      }).
      when('/price/setting_price', {
          templateUrl: 'resources/apps/modules/price/view/setting_price.html',
          controller: 'MasterMenuController'
        }).
      when('/transaction/discount', {
            templateUrl: 'resources/apps/modules/discount/view/discount.html',
            controller: 'MasterMenuController'
      }).
      when('/authorization', {
          templateUrl: 'resources/apps/modules/menu/view/MenuAdd.html',
          controller: 'MasterMenuController'
        }).
      otherwise({
        redirectTo: '/'
      });
   
    //$locationProvider.html5Mode(true);
    //$locationProvider.hashPrefix('!');

  }]);