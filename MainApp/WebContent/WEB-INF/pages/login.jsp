<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="styles/style.css">
	</head>
	<body>
	
		<div class="loginbox">
		<img src="styles/login.png" class="avatar">
			<h1>Login</h1>
			<form method="POST" action="?action=login">
				<p>Username:</p>
				<input type="text" name="username" id="username" placeholder="Enter username">
				<p>Password:</p>
				<input type="password" name="password" id="password" placeholder="Enter password">
				<h5 style="color: #A93226;"><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></h5>
				<input type="submit" name="submit" value="Login">
				<a href="?action=signup">Don't have an account?</a>
			</form>
		</div>
	</body>
</html>