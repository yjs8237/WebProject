<%@ page import="spms.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean 
		id="member"
		scope="session"
		class="spms.vo.Member" >
</jsp:useBean>
<%
	// Usebean 사용
	// JSP 내장 객체 session 을 통해서 member 객체를 가져온다.
	//Member member = (Member)session.getAttribute("member");
	
%>    
    
    
    <div style="background-color:#00008b;color:#ffffff;height:20px;padding:5px;">
    	SPMS(Simple Project Management System)
    	<span style="float:right;">
    		<% if(member.getNo() != null) {%>
    		
    		
    		<%=member.getName() %>
    		<a style="color:white;" href="<%request.getContextPath();%>../auth/logout"> 로그아웃</a>
    		<%} %>
    	</span>
    	
    </div>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>