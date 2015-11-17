<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myMenuApp">

  <head>
    <script src="resources/js/lodash.js"></script>
    <link href="http://fonts.googleapis.com/css?family=Ubuntu:500,700" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="resources/css/angular-material.min.css" />
    <link rel="stylesheet" href="resources/css/font-awesome.min.css" />
    <link rel="stylesheet" href="resources/css/style.css" />
  </head>
  <body>
    <div layout="column" style="height: 100%">
         <div flex="15" style="background-color: #eff4ff">First item in row</div>
         <div flex="85">
         <div>
            <div ui-view=""></div>
         </div>
         </div>
     </div>
  <script  src="resources/js/angular.min.js"></script>
  <script  src="resources/js/angular-animate.min.js"></script>
  <script  src="resources/js/angular-aria.min.js"></script>
  <script  src="resources/js/angular-material.min.js"></script>
  <script  src="resources/js/angular-ui-router.js"></script>
  <!-- angular -->
  <script src="resources/apps/app.modules.js"></script>
  <script src="resources/apps/modules/menu/menu.service.js"></script>
  <script src="resources/apps/modules/menu/home.controller.js"></script>
  <script src="resources/apps/modules/menu/menu_toggle.directive.js"></script>
  <script src="resources/apps/modules/menu/menulink.directive.js"></script>
  <script src="resources/apps/modules/menu/app.routes.js"></script>
</body>

</html>