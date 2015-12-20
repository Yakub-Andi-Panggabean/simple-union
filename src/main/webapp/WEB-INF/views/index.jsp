<!DOCTYPE html>
<html lang="en" ng-app="UnionAppLogin">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Pragma" content="no-cache">
    <title>Black Parade</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/ripples.min.css" rel="stylesheet">
	<link href="resources/css/material-wfont.min.css" rel="stylesheet">
    <!-- <link rel="shortcut icon" href="resources/images/favicon.ico"> -->
	
	<style>
		body {
			background-color:#ffffff;
			font-size:14px;
			color:#555;
		}
		.form-control {
			font-size:18px;
			margin:0 0 20px;
		}
		.login-notification {
			margin:10px;
			text-align: center;
		}
	</style>
	
  </head>

    <script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/ripples.min.js"></script>
	<script src="resources/js/material.min.js"></script>
  
	<body style="height:100%;" ng-controller="LoginController">
	
	<div class="row" style="padding-top:100px">
		<div class="col-sm-4 col-sm-offset-4">
		<div style="box-shadow: 0 3px 6px rgba(0,0,0,.16),0 3px 6px rgba(0,0,0,.23);">
		<div style="background-color: #13467F" class="well">
				<center>
					<img src="resources/images/beard-icon.png" alt="Welcome to The Black Parade" width="200px">
				</center>
			</div>
			<div style="padding:2%">
			<div>
				<input ng-model="username" class="form-control floating-label" style="color:#13467F;" type="text" id="username" name="username" placeholder="username"  maxlength="30" value="">
				<input ng-model="password" class="form-control floating-label" style="color:#13467F;" type="password" id="password" name="password" placeholder="password" maxlength="30" value="">
			</div>
			
			<br/>
			<div class="login-notification">
				<input class="btn btn-primary btn-block" style="background-color:#13467F" name="submit" type="submit" ng-click="signin()"
					value="Sign In" />
			</div>
			</div>
		</div>
		<br/>
		</div>
	</div>
	</body>
	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-cookies.min.js"></script>
	
  <!-- Login App -->
  <script  src="resources/apps/modules/login/LoginModule.js"></script>
  <script  src="resources/apps/modules/login/controller/LoginController.js"></script>
  <script  src="resources/apps/modules/login/service/BasicAuthService.js"></script>
  
	 
</html>