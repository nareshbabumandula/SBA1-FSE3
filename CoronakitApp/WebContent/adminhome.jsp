<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<div>
		<a href="product?action=list&type=admin">List Products</a>
	</div>
	<div>
		<a href="newproduct.jsp">Add Product</a>
	</div>
	<div>
		<a href="newuser.jsp">Add User</a>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>