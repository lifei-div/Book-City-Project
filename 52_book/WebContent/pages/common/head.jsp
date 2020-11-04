<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
	pageContext.setAttribute("basePath", basePath);
%>

<!--写base标签，永远固定相对路径跳转的结果-->
<!-- http://localhost:8080:52_book/ -->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery.js"></script>