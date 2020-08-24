<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr>
	<div>
		<c:choose>
			<c:when test="${products == null || products.isEmpty() }">
				<p>No Products Available</p>
			</c:when>
			<c:otherwise>
				<table border="1" cellspacing="5px" cellpadding="5px">
					<tr>
						<th></th>
						<th>Id</th>
						<th>ProductName</th>
						<th>Cost</th>
						<th>ProductDescription</th>
					</tr>
					<c:forEach items="${products }" var="product">
						<tr>
							<td><input type="checkbox" name="productid"
								value="${product.id }"></td>
							<td>${product.id }</td>
							<td><c:if test="${type == 'visitor'}">
									<c:set var="productdetails">
										<c:url value="product">
											<c:param name="action" value="productdetails" />
											<c:param name="productid" value="${product.id }" />
										</c:url>
									</c:set>
									<a href="${productdetails}">${product.productName }</a>
								</c:if> <c:if test="${type == 'admin'}">
								${product.productName }
							</c:if></td>
							<td>${product.cost }</td>
							<td>${product.productDescription }</td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
		<table>
			<c:if test="${type == 'admin'}">
				<tr>
					<td>
						<button id="addproduct" type="button">Add Product</button>
						<button id="updateproduct" type="button">Update Product</button>
						<button id="deleteproduct" type="button">Delete Product</button>
					</td>
				</tr>
			</c:if>
			<c:if test="${type == 'visitor'}">
				<tr>
					<td>
						<button id="showkit" type="button">Show Kit</button>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("button").click(function() {
				var buttonId = $(this).attr('id');

				if (buttonId == 'addproduct') {
					openPage('newproduct.jsp');
					return;
				}

				if (buttonId == 'updateproduct') {
					updateProduct($("input[name='productid']:checked").val());
					return;
				}

				if (buttonId == 'deleteproduct') {
					var selectedIds = [];
					$.each($("input[name='productid']:checked"), function() {
						selectedIds.push($(this).val());
					});
					deleteProduct(selectedIds.join(", "));
					return;
				}

				if (buttonId == 'showkit') {
					showKit();
					return;
				}
			});
			function addProduct() {
				$.ajax({
					type : "GET",
					url : 'newproduct.jsp'
				});
			}

			function updateProduct(productId) {
				$.ajax({
					type : "GET",
					url : 'product?action=load&productid=' + productId,
					success : function(data, textStatus) {
						$("body").html(data);
					}
				});
			}

			function deleteProduct(ids) {
				$.ajax({
					type : "GET",
					url : 'product?action=delete&ids=' + ids,
					success : function(data, textStatus) {
						$("body").html(data);
					}
				});
			}

			function showKit() {
				$.ajax({
					type : "GET",
					url : 'kit?action=show',
					success : function(data, textStatus) {
						$("body").html(data);
					}
				});
			}
			
			function openPage(pageURL) {
				alert("openpage");
				window.location.href = pageURL;
			}
		});
	</script>
</body>
</html>