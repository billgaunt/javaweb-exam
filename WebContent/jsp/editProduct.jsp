<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>修改页面</h1>
	<form action="${pageContext.request.contextPath }/ProductServlet" method="post">
		<input type="hidden" name="method" value="updateProduct">
		<input type="hidden" name="pid" value="${producer.pid }">
		商品名称：<input type="text" name="pname" value="${producer.pname }"><br/>
		市场价格：<input type="number" name="market_price" value="${producer.market_price }"><br/>
		商城价格：<input type="number" name="shop_price" value="${producer.shop_price }"><br/>
		商品产区：<input type="text" name="area" value="${producer.area }"><br/>
		商品描述：<input type="text" name="pdesc" value="${producer.pdesc }"><br/>
		<input type="submit" value="提交" id="mySubmit" >
	</form>
	<a href="${pageContext.request.contextPath }/IndexServlet?method=index">主页面</a>
</body>
</html>