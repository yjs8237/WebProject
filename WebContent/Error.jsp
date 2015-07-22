<%@ page import="spms.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// JSP 내장 객체 session 을 통해서 member 객체를 가져온다.
	String exceptionStr = (String)session.getAttribute("error");
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>시스템 에러</title>
</head>
<body>
<p>문제 발생</p>
<%=exceptionStr %><br>
</body>
</html>