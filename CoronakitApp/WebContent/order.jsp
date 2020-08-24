<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Product</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr>
	<h2>Product Order</h2>
	<form action="product?action=placeorder" method="post">

		<div>
			<div>
				<label for="productId">Enter Product Id</label>
			</div>
			<div>
				<input type="text" id="productid" name="productid"
					value="${product.Id }">
			</div>
		</div>
		<div>
			<div>
				<label for="productName">Enter Product Name</label>
			</div>
			<div>
				<input type="text" id="productname" name="productname"
					value="${product.productName }">
			</div>
		</div>
		<div>
			<div>
				<label for="productCost">Enter Product Cost</label>
			</div>
			<div>
				<input type="text" id="productcost" name="productcost"
					value="${product.cost }">
			</div>
		</div>
		<div>
			<div>
				<label for="productDescription">Enter Product Description</label>
			</div>
			<div>
				<input type="text" id="productdescription" name="productdescription"
					value="${product.productDescription }">
			</div>
		</div>
		<div>
			<div>
				<label for="w3review">Shipping Address:</label>
			</div>
			<div>
				<textarea id="address" name="address" rows="10" cols="50">
  			Enter Address here...
  			</textarea>
			</div>
		</div>
		<div>
			<input type="submit" value="Place Order">
		</div>
	</form>
	<hr />
</body>
</html>