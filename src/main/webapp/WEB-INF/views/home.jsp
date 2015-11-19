<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="UnionApp">

  <head>
    <!--<base href="/"> -->
    <script src="resources/js/lodash.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Metrophobic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="resources/css/angular-material.min.css" />
    <link rel="stylesheet" href="resources/css/font-awesome.min.css" />
    <link rel="stylesheet" href="resources/css/style.css" />
    <link rel="stylesheet" href="resources/css/abn_tree.css" />
  </head>
  <body class="md-body-1">
    <div layout="column" style="height: 100%">
         <div flex="15" class='header'></div>
         <div flex="85" class='content_container' ng-controller='MenuController'>
         <div layout="row">
            <div flex="15" id="menu_container">
               <div  ng-cloack>
                   <abn-tree 
                       tree-data         = "my_data"
                       tree-control      = "my_tree"
                       icon-leaf         = "fa fa-file-o"
                       icon-expand       = "fa fa-folder-o"
                       icon-collapse     = "fa fa-folder-open-o"
                       on-select         = "choosePage(branch)"
                       expand-level      = "2"
                       initial-selection = "Vegetable">      
                   </abn-tree>
               </div>
            </div>
            <div flex="85">
            <div class="view_container">
            <div class="view_content" ng-view>
            </div>
            </div>
         </div>
         </div>
     </div>
     </div>
   <script  src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular.js"></script>
   <!-- <script  src="resources/js/angular.min.js"></script> -->
   <script  src="resources/js/angular-route.min.js"></script>
   <script  src="resources/js/angular-animate.js"></script>
   <script  src="resources/js/angular-aria.min.js"></script>
   <script  src="resources/js/angular-material.min.js"></script>
   <script  src="resources/js/angular-ui-router.js"></script>
   <script  src="resources/js/abn_tree_directive.js"></script>
   
   <!-- Main App -->
   <script  src="resources/apps/App.js"></script>
   
  <!-- Menu Module -->
  <script  src="resources/apps/modules/menu/MenuModule.js"></script>
  <script  src="resources/apps/modules/menu/controller/MenuController.js"></script>
  <script  src="resources/apps/modules/menu/service/MenuService.js"></script>
  
  <!-- Navigation Module -->
  <script  src="resources/apps/modules/navigation/NavigationModule.js"></script>
  
  <!-- Stock Module -->
  <script  src="resources/apps/modules/stock/StockModule.js"></script>
  <script  src="resources/apps/modules/stock/controller/StockController.js"></script>
   
</body>

</html>