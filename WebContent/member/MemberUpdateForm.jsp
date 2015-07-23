<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member" %>
<%@ page import="java.util.ArrayList" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member");
	%>
	<jsp:include page="/Header.jsp"></jsp:include>
	
	<p>회원등록</p>
	
		<form action='add' method='post'>
		번호:<input type='text' name='mno' value="<%=member.getNo() %>"><br>
		이름:<input type='text' name='name' value="<%=member.getName() %>"><br>
		전화번호:<input type='text' name='number' value="<%=member.getPhonenum() %>"><br>
		키:<input type='text' name='height' value="<%=member.getHeight() %>"><br>
		이메일:<input type='text' name='email' value="<%=member.getEmail() %>"><br>
		<input type='submit' value='추가'>
		<input type='reset' value='취소'>
	</from>
	

<jsp:include page="/Tail.jsp"></jsp:include>

</body>
</html>