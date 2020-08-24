<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
<hr/>
<jsp:include page="header.jsp"/>
	<h2>Update Product</h2>
	<form action="product?action=update" method="post">
		<div>
			<div><label for="productId">Enter Product Id</label> </div>
			<div><input type="text" id="productId" name="ProductId"> </div>
		</div>
		<div>
			<div><label for="productName">Enter Product Name</label> </div>
			<div><input type="text" id="productName" name="ProductName"> </div>
		</div>
		<div>
			<div><label for="productCost">Enter Product Cost</label> </div>
			<div><input type="text" id="productCost" name="ProductCost"> </div>
		</div>
		<div>
			<div><label for="productDescription">Enter Product Description</label> </div>
			<div><input type="text" id="productDescription" name="ProductDescription"> </div>
		</div>
		<div>
			<div><input type="submit" value="Update"> </div>
		</div>
	</form>
	<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>