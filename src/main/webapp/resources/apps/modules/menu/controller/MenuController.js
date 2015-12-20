/**
 * http://usejsdoc.org/
 */
'use strict'

var module, deps;

deps = ['angularBootstrapNavTree','ngAnimate','UnionApp.menu.service','ngCookies'];

if (angular.version.full.indexOf("1.2") >= 0) {
  deps.push('ngAnimate');
}

module = angular.module('UnionApp.menu.controller', deps);

module.run(function($http,$cookieStore) {
	  $http.defaults.headers.common.Authorization = $cookieStore.get('credential');
});


module.controller('MenuController', function($scope, $timeout,$location,$cookieStore,MenuService) {
  var tree, treedata_avm;
  $scope.choosePage=function(branch){
	  $location.path(branch.relativeUrl);
  };

  //mock data
  treedata_avm = [
    {
      label: 'purchasing',
      children: [
        {
          label: 'Order',
          data: {
            description: "man's best friend"
          }
        }
      ]
    }, {
      label: 'Stock',
      data: {
        definition: "A plant or part of a plant used as food, typically as accompaniment to meat or fish, such as a cabbage, potato, carrot, or bean.",
        data_can_contain_anything: true
      },
      onSelect: function(branch) {
        return $scope.output = "Vegetable: " + branch.data.definition;
      },
      children: [
        {
          label: 'Oranges'
        }
      ]
    }, {
      label: 'Reporting',
      children: [
        {
          label: 'Stock',
          children: ['Daily Stock', 'Monthly Stock', 'Annual Stock']
        }, {
          label: 'Purchase',
          children: ['Purchase', 'Purchase Order', 'Purchase Request']
        }
      ]
    }
  ];
  
  //replacing harcoded menu with menu from database
  MenuService.getAvailableMenus().then(function(response){
	  console.log(response.data.contents);
	  $scope.my_data=response.data.contents;
  });
  
  
  $scope.my_data = treedata_avm;
});


