<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myMenuApp">

  <head>
    <script data-require="lodash.js@3.5.0" data-semver="3.5.0" src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.5.0/lodash.js"></script>
    <link href="http://fonts.googleapis.com/css?family=Ubuntu:500,700" rel="stylesheet" type="text/css" />
    <link data-require="angular-material@0.10.0" data-semver="0.10.1" rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/0.10.1/angular-material.min.css" />
    <link data-require="font-awesome@4.3.0" data-semver="4.3.0" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="resources/css/style.css" />
  </head>
  <body>
  <div class="bs-docs-header" id="content" tabindex="-1">
      <div class="container">
      <!--  -->
      </div>
    </div>
    <div class="fill-height">
      <div ui-view=""></div>
    </div>
    <script data-require="angular.js@~1.4.0" data-semver="1.4.0" src="https://code.angularjs.org/1.4.0/angular.js"></script>
    <script data-require="angular-animate@1.4.0" data-semver="1.4.0" src="https://code.angularjs.org/1.4.0/angular-animate.js"></script>
    <script data-require="angular-aria@1.4.0" data-semver="1.4.0" src="https://code.angularjs.org/1.4.0/angular-aria.js"></script>
    <script data-require="angular-material@0.9.0" data-semver="0.9.4" src="https://ajax.googleapis.com/ajax/libs/angular_material/0.9.4/angular-material.min.js"></script>
    <script data-require="ui-router@0.2.12" data-semver="0.2.15" src="//rawgit.com/angular-ui/ui-router/0.2.15/release/angular-ui-router.js"></script>
  
  <!-- angular -->
  <script src="resources/apps/app.modules.js"></script>
  <script src="resources/apps/menu.service.js"></script>
  <script src="resources/apps/home.controller.js"></script>
  <script src="resources/apps/menu_toggle.directive.js"></script>
  <script src="resources/apps/menulink.directive.js"></script>
  <script src="resources/apps/app.routes.js"></script>
</body>

</html>