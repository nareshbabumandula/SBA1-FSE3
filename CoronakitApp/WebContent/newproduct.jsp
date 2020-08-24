<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Product(Admin)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr>
	<h2>Add New Product</h2>
	<form action="product?action=add" method="post">
		<div>
			<div>
				<label for="productId">Enter Product Id</label>
			</div>
			<div>
				<input type="text" id="productid" name="productid">
			</div>
		</div>
		<div>
			<div>
				<label for="productName">Enter Product Name</label>
			</div>
			<div>
				<input type="text" id="productname" name="productname">
			</div>
		</div>
		<div>
			<div>
				<label for="productCost">Enter Product Cost</label>
			</div>
			<div>
				<input type="text" id="productcost" name="productcost">
			</div>
		</div>
		<div>
			<div>
				<label for="productDescription">Enter Product Description</label>
			</div>
			<div>
				<input type="text" id="productdescription" name="productdescription">
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