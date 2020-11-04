<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%--静态包含，base标签，css样式，jquery文件 --%>
	<%@include file = "/pages/common/head.jsp" %>
	<script type="text/javascript">
	$(function () { // 给 加 入 购 物 车 按 钮 绑 定 单 击 事 件 
		$("button.addToCart").click(function () { 
	    /** * 在 事 件 响 应 的 function 函 数 中 ， 有 一 个 this对 象 ， 这 个 this
		对 象 ， 是 当 前 正 在 响 应 事 件 的 dom对 象
		* @type {jQuery} */
		var bookId = $(this).attr("bookId");
	//	location.href = "http://localhost:8080/52_book/CarServlet?action=addItem&id=" + bookId;
		
	//使用AJax异步请求
	
		$.getJSON("http://localhost:8080/52_book/CarServlet",
				"action=ajaxAddItem&id="+ bookId,
				 function (data) {
					$("#cartTotalCount").text("您购物车共有："+data.totalCount+"件商品");
					$("#cartLastName").text(data.lastName);
				});
		}); 
	}); 
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
			     <%--如果用户还没有登录，显示登录和注册菜单 --%>
			    <c:if test="${empty sessionScope.user }">
			    <a href="pages/user/login.jsp">登录</a> | 
				<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
			    </c:if>
			     <%--如果用户已经登录，显示自己信息 --%>
			    <c:if test="${not empty sessionScope.user }">
			    <span>欢迎<span class="um_span">${sessionScope.user.username }</span>光临尚硅谷书城
		        </span> <a href="pages/order/order.jsp">我的订单</a> 
	        	<a href="UserServlet?action=logout">注销</a>&nbsp;&nbsp;
	        	<a href="index.jsp">返回</a>
			    </c:if>
				
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			 
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="Client/BookServlet" method="get">
				        <input type="hidden" name = "action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${param.min }"> 元 - 
						<input id="max" type="text" name="max" value="${param.max }"> 元 
						<input type="sumit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
			    <c:if test="${empty sessionScope.cart.items }">
			        <%--购物车为空 --%>
			     	<span id="cartTotalCount" >	</span>
			     	<div>
			     		<span style="color: red" id="cartLastName">您购物车为空</span>
			     	</div>
			   
			    </c:if>
			
			   <c:if test="${not empty sessionScope.cart.items }">
			    	<%--购物车非空 --%>
			    	<span id="cartTotalCount" style="color: red">您购物车共有：${sessionScope.cart.totalCount }件商品</span>
				<div>
					您刚刚将<span id="cartLastName" style="color: red">${sessionScope.lastName }</span>加入到了购物车中
				</div>
			    </c:if>
				
			</div>
			
			<c:forEach items="${requestScope.page.items }"  var="books">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${books.imgPaht }" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${books.name }</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${books.author }</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">${books.price }</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${books.sales }</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${books.stock }</span>
					</div>
					<div class="book_add">
						<button bookId="${books.id }" class = "addToCart">加入购物车</button>
					</div>
				</div>
			</div>
		   </c:forEach>
		</div>
		
		<!-- 
		<%--分页条开始 --%>
		<div id="page_nav">
		   <%--大于首页,显示上一页 --%>
		   <c:if test="${requestScope.page.pageNo >1 }">
		   <a href="Client/BookServlet?action=page&pageNo=1">首页</a>
		   <a href="Client/BookServlet?action=page&pageNo=${requestScope.page.pageNo-1 }">上一页</a>
		   </c:if>
		   <%--页码输出的开始 --%>
		   		<c:choose >
		   		<%--情况1：如果总页码小于等5的情况，页码的范围是：1-总页码 --%>
		   		    <c:when test="${requestScope.page.pageTotal <= 5 }">
		   		    
		   		       <c:forEach begin = "1" end = "${requestScope.page.pageTotal }" var = "i">
		   		       <c:if test="${ i == requestScope.page.pageNo }">
		   		                  【${i}】
		   		       </c:if>
		   		       
		   		       <c:if test="${ i != requestScope.page.pageNo }">
		   		          <a href = "Client/BookServlet?action=page&pageNo=${i}">${i}</a>
		   		       </c:if>
		   		       </c:forEach>
		   		   </c:when>
		   		   <%--情况2：如果总页码小于等5的情况，页码的范围是：1-总页码 --%>
		   		   <c:when test="${requestScope.page.pageTotal > 5}">
		   		   		<c:choose>
		   		   		<%--小 情 况 1 ： 当 前 页 码 为 前 面 3个 ： 1 ， 2 ， 3的 情 况 ， 页 码 范 围 是 ： 1-5.--%> 
		   		   		    <c:when test="${requestScope.page.pageNo <= 3}">
		   		   		       <c:forEach begin = "1" end = "5" var = "i">
		   		                 <c:if test="${ i == requestScope.page.pageNo }">
		   		                                【${i}】
		   		                 </c:if>
		   		                <c:if test="${ i != requestScope.page.pageNo }">
		   		                  <a href = "Client/BookServlet?action=page&pageNo=${i}">${i}</a>
		   		                </c:if>
		   		               </c:forEach>
		   		   		    </c:when>
		   		   		    <%--小 情 况 2 ： 当 前 页 码 为 最 后 3个 ， 8 ， 9 ， 10 ， 页 码 范 围 是 ： 总 页 码 减 4 总 页 码 --%> 
		   		   		    <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
		   		   		      <c:forEach begin = "${requestScope.page.pageTotal-4 }" end = "${requestScope.page.pageTotal }" var = "i">
		   		                 <c:if test="${ i == requestScope.page.pageNo }">
		   		                                【${i}】
		   		                 </c:if>
		   		                <c:if test="${ i != requestScope.page.pageNo }">
		   		                  <a href ="Client/BookServlet?action=page&pageNo=${i}">${i}</a>
		   		                </c:if>
		   		               </c:forEach>
		   		   		    </c:when> 
		   		   		    <%--小 情 况 3 ： 4 ， 5 ， 6 ， 7 ， 页 码 范 围 是 ： 当 前 页 码 减 2 前 页 码 加 2--%>
		   		   		    <c:otherwise>
		   		   		         <c:forEach begin = "${requestScope.page.pageNo-2}" end = "${requestScope.page.pageNo+2 }" var = "i">
		   		                 <c:if test="${ i == requestScope.page.pageNo }">
		   		                                【${i}】
		   		                 </c:if>
		   		                <c:if test="${ i != requestScope.page.pageNo }">
		   		                  <a href ="Client/BookServlet?action=page&pageNo=${i}">${i}</a>
		   		                </c:if>
		   		               </c:forEach>
		   		   		    
		   		   		    </c:otherwise>
		   		   		</c:choose>
		   		   </c:when>
		   		</c:choose>
		   <%--页码输出的结束 --%>
		    <%--小于末页,显示下一页 --%>
		   <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal }">
		   <a href="Client/BookServlet?action=page&pageNo=${requestScope.page.pageNo+1 }">下一页</a>
		   <a href="Client/BookServlet?action=page&pageNo=${requestScope.page.pageTotal }">末页</a>
		   </c:if>
		   
		      共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
		       到第<input value="${param.pageNo }" name="pn" id="pn_input"/>页
		   <input id="searchPageBtn" type="button" value="确定">
	
	
		     <script type="text/javascript">
                  $(function () { 
               
                	// 跳 到 指 定 的 页 码
                	$("#searchPageBtn").click(function () { 
                		var pageNo = $("#pn_input").val();
                		if(pageNo < 1 && pageNo > ${requestScope.page.pageTotal}){
                			alert("请输入正确页面");
                		}else{
                			<%--var pageTotal = ${requestScope.page.pageTotal};--%> 
                    		<%--alert(pageTotal);--%> 
                    		// javaScript 语 言 中 提 供 了 一 个 location 地 址 栏 对 象
                    		// 它 有 一 个 属 性 叫 href. 它 可 以 获 取 浏 览 器 地 址 栏 中 的 地 址 
                    		// href 属 性 可 读 ， 可 写 
                    		//${pageScope.basePath} 前面 pages/common/head 页面中已经保存在pageScope域中
                    		location.href = "${pageScope.basePath}Client/BookServlet?action=page&pageNo=" +pageNo;
                		}
                		
                	
                	   }); 
                	});
         </script>   
		</div>
	  <%--分页结束 --%>
	  -->
	  
	  <%--分页条开始  优化地址统一url,静态包含--%>
		     <%@include file="/pages/common/page_nav.jsp" %>
	  <%--分页结束 --%>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>