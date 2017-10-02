<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript"></script>
<title>添加页面</title>
<script type="text/javascript">
	$(function(){
		$("#blruCheck").blur(function(){
			var pname = $(this).val();
			$.post("${ pageContext.request.contextPath }/ProductServlet",{'method':'checkPName','pname':pname},function(data){
				if(data == '1'){
					$("#mySubmit").removeAttr("disabled");			/* 让提交按钮 有效 */
					$("#info").css("color","green");
					$("#info").text('商品名可以用');
				}else{
					$("#mySubmit").attr("disabled","disabled");		/* 让提交按钮  失效 */
					$("#info").css("color","red");
					$("#info").text('商品名不可以用');
				}
			});
		});
	});

</script>
</head>
<body>
	<h1>商品添加</h1>
	<form action="${pageContext.request.contextPath }/ProductServlet" method="post">
		<input type="hidden" name="method" value="addProduct">
		商品名称：<input type="text" name="pname" id="blruCheck"><span id="info"></span><br/>
		市场价格：<input type="number" name="market_price"><br/>
		商城价格：<input type="number" name="shop_price"><br/>
		商品产区：<input type="text" name="area"><br/>
		商品描述：<input type="text" name="pdesc"><br/>
		<input type="submit" value="提交" id="mySubmit" >
	</form>
	<a href="${pageContext.request.contextPath }/IndexServlet?method=index">主页面</a>
</body>
</html>