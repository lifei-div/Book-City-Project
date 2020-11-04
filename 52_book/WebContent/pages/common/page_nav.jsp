<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%--分页条开始  优化地址统一url--%>
		<div id="page_nav">
		   <%--大于首页,显示上一页 --%>
		   <c:if test="${requestScope.page.pageNo >1 }">
		   <a href="${requestScope.page.url }&pageNo=1">首页</a>
		   <a href="${requestScope.page.url }&pageNo=${requestScope.page.pageNo-1 }">上一页</a>
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
		   		          <a href = "${requestScope.page.url }&pageNo=${i}">${i}</a>
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
		   		                  <a href = "${requestScope.page.url }&pageNo=${i}">${i}</a>
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
		   		                  <a href ="${requestScope.page.url }&pageNo=${i}">${i}</a>
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
		   		                  <a href ="${requestScope.page.url }&pageNo=${i}">${i}</a>
		   		                </c:if>
		   		               </c:forEach>
		   		   		    
		   		   		    </c:otherwise>
		   		   		</c:choose>
		   		   </c:when>
		   		</c:choose>
		   <%--页码输出的结束 --%>
		    <%--小于末页,显示下一页 --%>
		   <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal }">
		   <a href="${requestScope.page.url }&pageNo=${requestScope.page.pageNo+1 }">下一页</a>
		   <a href="${requestScope.page.url }&pageNo=${requestScope.page.pageTotal }">末页</a>
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
                    		location.href = "${pageScope.basePath}${requestScope.page.url }&pageNo=" +pageNo;
                		}
                		
                	
                	   }); 
                	});
         </script>   
		</div>
	  <%--分页结束 --%>