var app = angular.module('StarterApp', ['ngMaterial']);

app.controller('AppController', function($mdSidenav) {
  var vm = this;

  vm.toggleSidenav = function(menuId) {
    $mdSidenav(menuId).toggle();
  };

});