<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign up</title>
		<link rel="stylesheet" type="text/css" href="styles/style.css">
	</head>
	<body>
		<div class="loginbox" style = "height:600px; transform: translate(-50%, 80%);">
		<img src="styles/login.png" class="avatar">
			<h1>Sign up</h1>
			<form method="POST" action="?action=signup">
				<p>Name:</p>
				<input type="text" name="name" id="name" placeholder="Enter name">
				<p>Surname:</p>
				<input type="text" name="surname" id="surname" placeholder="Enter surname">
				<p>Username:</p>
				<input type="text" name="username" id="username" placeholder="Enter username">
				<p>Password:</p>
				<input type="password" name="password" id="password" placeholder="Enter password">
				<p>Confirm password:</p>
				<input type="password" name="confirmPassword" id="confirmPassword" placeholder="Confirm password">
				<p>Mail:</p>
				<input type="text" name="mail" id="mail" placeholder="Enter e-mail">
				<br />
				<h5 style="color: #A93226;"><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></h5>
				<br />
				<input type="submit" name="submit" value="Sign up">
			</form>
		</div>
	</body>
</html>