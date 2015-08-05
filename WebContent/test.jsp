<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>      

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>


	<c:out value="${null }" default="default value" /><br>
	<c:out value="${null }">기본 값</c:out>
	<c:set var="username1" value="윤지상"></c:set><br>
	${username1 }<br>
	${pageScope.username1 }<br>
	${requestScope.username1 }<br>
	
	<c:set var="username1" value="윤지상" scope="request"></c:set><br>
	${requestScope.username1 }<br>
	${pageScope.username1 }<br>
	
	<c:remove var="username1"/>
	<c:out value="${username1 }">username1 is null</c:out><br>
	<c:out value="${requestScope.username1 }">username1 is null</c:out>
	
	<c:if test="${username1 == null }" var="result"></c:if><br>
	<c:out value="${result }">result is null</c:out>
	
	<c:set var="value" value="2"></c:set><br>
	
	<c:choose>
		<c:when test="${value == 1 }"> value == 1</c:when>
				
	</c:choose>
	
	
	<% pageContext.setAttribute("nameList", new String[]{"TEmp1","temp2","temp3"}); %>
	
	<c:forEach var="name" items="${nameList }">
		<li>${name }</li>
	</c:forEach>
	
	<br>
	<c:forEach var="name" items="${nameList }" begin="2" end="3">
		<li>${name }</li>
	</c:forEach>
	
	<br> 
	
	<% 
		ArrayList <String> list = new ArrayList<String>();
		list.add("첫번째");
		list.add("두번째");
		list.add("세번째");
		list.add("네번째");
		pageContext.setAttribute("list", list);
	%>
	
	<c:forEach var="name" items="${list }">
		<li>${name }</li> 
	</c:forEach>
	
	
	<c:url var="calcUrl" value="http://localhost:8080/web03/calc">
		<c:param name="v1" value="1"></c:param>
		<c:param name="v2" value="2"></c:param>
		<c:param name="op" value="+"></c:param>
	</c:url>
	<a href="${calcUrl }">계산</a>
	
	
	<c:import url="http://www.zdnet.co.kr/Include2/ZDNetKorea_News.xml" var="xmlData"></c:import>
	<textarea rows="10" cols="80">
		${xmlData } 
	</textarea>
	
	<c:redirect url="http://www.daum.net"></c:redirect>
	
	 
	
	
	
</body>
</html>