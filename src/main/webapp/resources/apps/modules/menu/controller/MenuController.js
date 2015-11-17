/**
 * http://usejsdoc.org/
 */
'use strict'

var module, deps;

deps = ['angularBootstrapNavTree','ngAnimate','UnionApp.menu.service'];

if (angular.version.full.indexOf("1.2") >= 0) {
  deps.push('ngAnimate');
}

module = angular.module('UnionApp.menu.controller', deps);

module.controller('MenuController', function($scope, $timeout,MenuService) {
  var apple_selected, tree, treedata_avm;
  $scope.my_tree_handler = function(branch) {
    var _ref;
    $scope.output = "You selected: " + branch.label;
    if ((_ref = branch.data) != null ? _ref.description : void 0) {
      return $scope.output += '(' + branch.data.description + ')';
    }
  };
  apple_selected = function(branch) {
    return $scope.output = "APPLE! : " + branch.label;
  };
  treedata_avm = [
    {

      "menuId": "1a40147050164b1ea4f40a3c55a072fa",
      "label": "purchasing",
      "parent": "",
      "relativeUrl": "/mama/apa/kabar4",
      "state": "mama.apa.kabar4",
      "icon": "fa fa-file-o",
      "type": "link",
      "active": 1,
      "createdDate": 1355245200000,
      "createdBy": "xxx",
      "updatedDate": null,
      "updatedBy": null,
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
  
  
  MenuService.getAvailableMenus().then(function(response){
	  console.log(response.data.contents);
	  $scope.my_data=response.data.contents;
  });
  $scope.my_data = treedata_avm;
});


