<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Pragma" content="no-cache">
    <title>Black Parade</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/ripples.min.css" rel="stylesheet">
	<link href="resources/css/material-wfont.min.css" rel="stylesheet">
	
    <link rel="shortcut icon" href="assets/flat_ui/images/favicon.ico">
	
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
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="resources/js/ripples.min.js"></script>
	<script src="resources/js/material.min.js"></script>
	<script src="assets/lib_js/md5.js"></script>
  
	<body style="height:100%;">
	
	<div class="row" style="padding-top:100px">
		<div class="well col-sm-4 col-sm-offset-4">
			<div>
				<center>
					<img src="resources/images/logo.jpg" alt="Welcome to The Black Parade" width="200px">
				</center>
				<hr/>
			</div>
			
			<div>
				<input class="form-control floating-label" type="text" id="username" placeholder="username"  maxlength="30" value="">
				<input class="form-control floating-label" type="password" id="password" placeholder="password" maxlength="30" value="">
			</div>
			
			<br/>
			<div class="login-notification">
				<a class="btn btn-primary btn-block" style="background-color:#262729">Sign In</a>
			</div>
		</div>
		<br/>
	</div>
	
	</body>
</html>