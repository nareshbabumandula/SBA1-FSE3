<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
	<hr />
	<jsp:include page="header.jsp" />
	<h2>Order Summary:</h2>
	Your order id: ${orderid}, Please use this order id for tracking your
	order.
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>