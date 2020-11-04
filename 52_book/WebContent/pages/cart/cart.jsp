<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%--静态包含，base标签，css样式，jquery文件 --%>
<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">购物车</span>
		<!-- 静态包含 -->
		<%@ include file="/pages/common/login_success_menu.jsp"%>
		<script type="text/javascript">
			$(function () {
				//给删除绑定单击事件
				$("a.deleteItem").click(function () {
				  return confirm("您确定要是删除【"+$(this).parent().parent().find("td:first").text()+"】吗？")
				});
				
				//给清空购物车绑定单击事件
				$("#clearCart").click(function () {
				  return confirm("您确定要清空购物车吗？");
				});
				
				//给输入框绑定失去焦点事件onBlur  === onChange 内容发生改变事件
				$(".updateCount").change(function () {
					//获取商品名称
				    var	name=$(this).parent().parent().find("td:first").text();
					//获取商品数量
					var count = this.value;
					var id = $(this).attr('bookId');
					
				    if(confirm("您确定要将【"+name +"】商品数量修改为:"+count+"吗")){
				    	//发起请求，给服务器保存 
				    	location.href = "http://localhost:8080/52_book/CarServlet?action=updateCount&count="+count+"&id="+id; 				    }else {//取消
				    	//defaultValue 属性表示表单项Dom对象属性，它表示默认的value属性值
						this.value = this.defaultValue;
					}
				});
			});
		 
		</script>
	</div>

	<div id="main">

		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			
             <c:forEach items="${sessionScope.cart.items }" var="entry">
			<tr>
				<td>${entry.value.name }</td>
				<td>
				<input class="updateCount" type="text" style="width: 80px" 
				bookId = "${entry.value.id }"
				value="${entry.value.count }">
				</td>
				<td>${entry.value.price }</td>
				<td>${entry.value.totalPrice }</td>
				<td><a class="deleteItem" href="CarServlet?action=deleteItem&id=${entry.value.id }">删除</a></td>
			</tr>
            </c:forEach>
             <%--购物车非空 输出下面内容 --%>
          <c:if test="${ empty sessionScope.cart.items}">
            <tr>  
				<td colspan="5"><a href= "index.jsp">亲，购物车为空!快去剁手吧！</a></td>
				
			</tr>
          </c:if>
		</table>

        
		 <%--购物车非空 输出下面内容 --%>
		<c:if test="${not empty sessionScope.cart.items}">
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount }</span>件商品
			</span> <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice }</span>元
			</span> <span class="cart_span"><a id="clearCart" href=CarServlet?action=clear>清空购物车</a></span> <span
				class="cart_span"><a href="OrderServlet?action=createOrder">去结账</a></span>
		</div>
		</c:if>
	</div>

	<%--静态包含页脚内容 --%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>