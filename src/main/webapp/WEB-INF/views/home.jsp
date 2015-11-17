<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="UnionApp">

  <head>
    <script src="resources/js/lodash.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Metrophobic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="resources/css/angular-material.min.css" />
    <link rel="stylesheet" href="resources/css/font-awesome.min.css" />
    <link rel="stylesheet" href="resources/css/style.css" />
    <link rel="stylesheet" href="resources/css/abn_tree.css" />
  </head>
  <body>
    <div layout="column" style="height: 100%">
         <div flex="15" class='header'></div>
         <div flex="85" class='content_container'>
         <div layout="row">
            <div flex="15" id="menu_container">
               <div ng-controller='MenuController' >
                   <abn-tree 
                       tree-data         = "my_data"
                       tree-control      = "my_tree"
                       icon-leaf         = "fa fa-file-o"
                       icon-expand       = "fa fa-folder-o"
                       icon-collapse     = "fa fa-folder-open-o"
                       on-select         = "my_tree_handler(branch)"
                       expand-level      = "2"
                       initial-selection = "Vegetable">      
                   </abn-tree>
               </div>
            </div>
            <div flex="85">
            <div ng-view>
            </div>
         </div>
         </div>
     </div>
     </div>
   <script  src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular.js"></script>
   <!-- <script  src="resources/js/angular.min.js"></script> -->
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
</body>

</html>