<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<h2>Add New User</h2>
	<form action="user?action=add" method="post">
		<div>
			<div>
				<label for="userId">Enter User Id</label>
			</div>
			<div>
				<input type="text" id="userid" name="userid">
			</div>
		</div>
		<div>
			<div>
				<label for="password">Enter Password</label>
			</div>
			<div>
				<input type="text" id="password" name="password">
			</div>
		</div>
		<div>
			<div>
				<label for="type">Choose Type</label>
			</div>
			<div>
				<select id="type" name="type">
					<option value="admin">Admin</option>
					<option value="visitor">Visitor</option>
				</select>>
			</div>
		</div>
		<div>
			<div>
				<input type="submit" value="Save">
			</div>
		</div>
	</form>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>