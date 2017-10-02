<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript"></script>
<title>列表</title>
<script type="text/javascript">
	/* 写js代码 */
</script>
</head>
<body>
	<h1>商品列表</h1>
	<table border="1" cellpadding="0" cellspacing="0" width="100%" height="500">
		<thead>
			<tr>
				<th>商品序号</th>
				<th>商品名称</th>
				<th>市场价格</th>
				<th>商商城价格</th>
				<th>商品产区</th>
				<th>商品描述</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<!-- 循环遍历数据 -->
			<c:forEach var="p" items="${pageBean.list }" varStatus="sta">
				<tr>
					<td>${sta.count }</td>
					<td>${p.pname }</td>
					<td>${p.market_price }</td>
					<td>${p.shop_price }</td>
					<td>${p.area }</td>
					<td>${p.pdesc }</td>
					<td>
						<a href="${pageContext.request.contextPath }/ProductServlet?method=toUpdateProduct&pid=${p.pid }">修改</a>
							|
						<a href="javascript:if(confirm('确定删除？'))location.href='${pageContext.request.contextPath }/ProductServlet?method=deleteProduct&pid=${p.pid }'">删除</a><!-- deletePro('${p.pid }'); -->
					</td>
				</tr>
			</c:forEach>
				<tr align="center">
					<!-- 分页 -->
					<td colspan="7">
						<!-- 展示页面信息 -->
						第${pageBean.currPage }/${pageBean.totalPage }页&nbsp;&nbsp;共${pageBean.totalCount }条数据&nbsp;&nbsp;每页${pageBean.pageSize }条数据
						<a href="${pageContext.request.contextPath }/ProductServlet?method=findByPage&currPage=1">首页</a>
						<!-- 当前页为为1时， 隐藏 上一页 按钮-->
						<c:if test="${pageBean.currPage != 1 }">
							<a href="${pageContext.request.contextPath }/ProductServlet?method=findByPage&currPage=${pageBean.currPage-1}">上一页</a>
						</c:if>
						<!-- 根据总页数决定展示的页数 -->
						<c:forEach var="i" begin="1" end="${pageBean.totalPage}">
							<!-- 当前页和i相同时只展示效果，而没有点击功能 -->
							<c:if test="${pageBean.currPage == i }">
								[${i }]
							</c:if>
							<c:if test="${pageBean.currPage != i }">
								<a href="${pageContext.request.contextPath }/ProductServlet?method=findByPage&currPage=${i}">${i}</a>
							</c:if>
						</c:forEach>
						<!-- 当前页为最后一页时， 隐藏 下一页 按钮-->
						<c:if test="${pageBean.currPage != pageBean.totalPage }">
							<a href="${pageContext.request.contextPath }/ProductServlet?method=findByPage&currPage=${pageBean.currPage+1}">上一页</a>
						</c:if>
						<a href="${pageContext.request.contextPath }/ProductServlet?method=findByPage&currPage=${pageBean.totalPage}">尾页</a>
					</td>
				</tr>
		</tbody>
	</table>
	
	<a href="${pageContext.request.contextPath }/IndexServlet?method=index">主页面</a>
</body>
</html>