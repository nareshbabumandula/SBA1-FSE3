<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<h2>Product Details</h2>

	<div>
		<h2>Product Name:</h2>
		${product.productName }
	</div>
	<div>
		<h2>Product Cost:</h2>
		${product.cost }
	</div>
	<div>
		<h2>Product Description:</h2>
		${product.productDescription }
	</div>
	<div>
		<button id="addtokit" type="button">Add to Kit</button>
		<button id="buy" type="button">Buy</button>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			$("button").click(function() {
				var buttonId = $(this).attr('id');

				if (buttonId == 'addtokit') {
					addToKit("${product.id }");
					return;
				}

			});
			function addToKit(productid) {
				$.ajax({
					type : "GET",
					url : 'kit?action=add&productid=' + productid,
					success : function(data, textStatus) {
						alert("Sucessfully added to the kit");
						$("body").html(data);
					}
				});
			}

		});
	</script>
</body>
</html>