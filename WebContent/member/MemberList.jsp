<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member" %>
<%@ page import="java.util.ArrayList" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/Header.jsp"></jsp:include>
	<p><a href='add'>신규회원</a> </p>
	<%
		ArrayList <Member> members = (ArrayList<Member>)request.getAttribute("members");
		for(Member member : members){
	%>
	<%=member.getNo() %>
	<a href='update?no=<%=member.getNo()%>'><%= member.getName() %></a>
	<%=member.getHeight()%>
	<%=member.getPhonenum()%>
	<a href='delete?no=<%=member.getNo()%>'>[Delete]</a><br>
	
	<%
		}
	%>

<jsp:include page="/Tail.jsp"></jsp:include>

</body>
</html>