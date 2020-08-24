<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<hr />
	<jsp:include page="header.jsp" />
	<c:choose>
		<c:when test="${kit == null }">
			<p>Kit Not availabe</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Id</th>
					<th>ProductName</th>
					<th>Cost</th>
					<th>ProductDescription</th>
				</tr>
				<c:forEach items="${kit.products }" var="product">
					<tr>
						<td>${product.id }</td>
						<td>${product.productName }</td>
						<td>${product.cost }</td>
						<td>${product.productDescription }</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<div>
		<h2>No of Products:</h2>
		${kit.productCount }
		<h2>Total Amount:</h2>
		${kit.totalAmount }
	</div>
	<div>
		<label for="deliveryaddress">Delivery Address:</label>
		<textarea id="deliveryaddress" name="deliveryaddress" rows="4"
			cols="50">
  		Enter Address here...
  		</textarea>
	</div>
	<div>
		<button id="placeorder" type="button">Place Order</button>
	</div>

	<hr />
	<jsp:include page="footer.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {
			$("button").click(function() {
				var buttonId = $(this).attr('id');

				if (buttonId == 'placeorder') {
					placeOrder();
					return;
				}
			});
			function placeOrder() {
				$.ajax({
					type : "GET",
					url : 'kit?action=placeorder&address=' + $('textarea#deliveryaddress').val(),
					success : function(data, textStatus) {
						alert("Sucessfully ordered");
						$("body").html(data);
					}
				});
			}
		});
	</script>
</body>
</html>