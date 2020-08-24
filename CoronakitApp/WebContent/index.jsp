<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Home</title>
</head>
<body>
	<h2>Admin Login</h2>
	<form action="login?action=login" method="post">
		<div>
			<div>
				<label for="loginid">Enter login Id</label>
			</div>
			<div>
				<input type="text" id="loginid" name="loginid">
			</div>
		</div>
		<div>
			<div>
				<label for="password">Enter password</label>
			</div>
			<div>
				<input type="text" id="password" name="password">
			</div>
		</div>
		<div>
			<div>
				<input type="submit" value="Login">
			</div>
		</div>
	</form>
	<hr />
	<h2>Visitor</h2>
	<div>
		<a href="visitorhome.jsp">visitor</a>
	</div>
</body>
</html>