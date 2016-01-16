/**
 * http://usejsdoc.org/
 */
'use strict'

var module, deps;

deps = ['angularBootstrapNavTree', 'ngAnimate', 'UnionApp.menu.service', 'ngCookies', 'ngMaterial'];

if (angular.version.full.indexOf("1.2") >= 0) {
    deps.push('ngAnimate');
}
module = angular.module('UnionApp.menu.controller', deps);

module.run(function($http, $cookieStore) {
    $http.defaults.headers.common.Authorization = $cookieStore.get('credential');
});


module.controller('MenuController', function($scope, $location, $mdDialog, $cookieStore, $mdToast, MenuService) {
    var treedata_avm,tree;
    $scope.my_data=[];
    $scope.my_tree={};
    $scope.choosePage = function(branch) {
        $location.path(branch.relativeUrl);
    };

    //mock data
    treedata_avm = [];
    $scope.username=$cookieStore.get('principal');

    //replacing harcoded menu with menu from database
    $scope.retrieveAvailableMenu = function() {
        MenuService.getAvailableMenus().then(function(response) {
            //console.log(response.data.contents);
            $scope.my_data = response.data.contents;
        });
    };
    
     $scope.try_async_load = function() {
        $scope.my_data = [];
        $scope.doing_async = true;
        MenuService.getAvailableMenus().then(function(response) {
           console.log('xxx');
           //console.log(response.data.contents);
           //$scope.tree = response.data.contents;
           //$scope.tree.expand_all();
           //console.log($scope.tree);
        });
    };


    $scope.isFalse = false;
    $scope.isTrue = true;
    var menuEntity = {
        menuId: null,
        label: null,
        parent: null,
        relativeUrl: null,
        active: 0,
        createdDate: null,
        createdBy: null,
        updatedDate: null,
        updatedBy: null
    };

    $scope.showAdd = function(ev) {
        $mdDialog.show({
            locals: {name: 'Bob'},
            controller: 'MasterMenuController',
            templateUrl: 'resources/apps/modules/menu/view/MenuAdd.html',
            targetEvent: ev,
        });
    };

    $scope.showMessage = function(message, position) {
        $mdToast.show(
                $mdToast.simple()
                .textContent(message)
                .position(position)
                .hideDelay(3000)
                );
    };

    $scope.closeDialog = function() {
        $mdDialog.hide();
    };

    $scope.retrieveAllMenu = function() {
        MenuService.getAllMenu()
                .success(function(response) {
                    $scope.menulist = response.contents;
                }).error(function(data, status) {
            console.log('error ,' + data, status);
        }).finally(function() {

        });
    };

    $scope.addNewMenu = function() {
        var menuData = {
            'menuId': '',
            'label': $scope.label,
            'parent': $scope.parent,
            'relativeUrl': $scope.relativeUrl,
            'active': 1,
            'createdDate': '2012-12-12',
            'createdBy': 'xxx',
            'updatedDate': null,
            'updatedBy': null
        };

        console.log('add new menu' + JSON.stringify(menuData));
        MenuService.addNewMenu(menuData).then(function(response) {
            console.log("response ----> " + JSON.stringify(response.status));
            if (response.status === 200) {
                if (response.data.success === true) {
                    alert("successfully inserted");
                } else {
                    alert(response.data.errorMessages);
                }
            } else {
                alert(response.data.errorMessages);
            }
        });
    };

    $scope.switchChange = function(active) {
        if (active === 0) {
            active = 1;
        } else {
            active = 0;
        }
        return active;
    };

    $scope.expand_tree=function(){
        $scope.my_tree.expand_all();
    };
    
    $scope.collapse_tree=function(){
        $scope.my_tree.collapse_all();
    };

    /**
     * updated new category
     */
    $scope.updateMenu = function(params, active) {
        $scope.loading = true;
        menuEntity.menuId = params.menuId;
        menuEntity.label = params.label;
        if (active == 1 || active == 0) {
            menuEntity.active = $scope.switchChange(active);
        } else {
            menuEntity.active = params.active;
        }
        menuEntity.parent = params.parent;
        menuEntity.relativeUrl = params.relativeUrl;
        menuEntity.createdDate = params.createdDate;
        menuEntity.createdBy = params.createdBy;
        menuEntity.updatedDate = new Date();
        menuEntity.updatedBy = $cookieStore.get("principal");

        console.log(menuEntity);
        MenuService.updateMenu(menuEntity)
                .success(function(response) {
                    if (active == 0 || active == 1) {
                        if (active == 0) {
                            $scope.showMessage("Menu Activated", "bottom right");
                        } else {
                            $scope.showMessage("Menu Deactivated", "bottom right");
                        }
                    } else {
                        $scope.showMessage("successfully updated", "bottom right");
                    }
                }).error(function(data, status) {
            console.error('error', status, data);
        }).finally(function() {
            $scope.try_async_load();
            console.log("finish updating category");
            $scope.loading = false;
            location.reload();
        });
    };
    
     var originatorEv;
     $scope.openMenu = function($mdOpenMenu, ev) {
      originatorEv = ev;
      $mdOpenMenu(ev);
    };
});


