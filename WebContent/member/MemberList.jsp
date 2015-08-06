<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/Header.jsp"></jsp:include>
	<p><a href='add'>신규회원</a> </p>
	<!-- 
	<jsp:useBean id="members"
					scope="request"
					class="java.util.ArrayList"
					type="java.util.ArrayList<spms.vo.Member>"></jsp:useBean>
	 -->
	 	 
	 <c:forEach var="member" items="${members }">
	 	${member.no }, 
	 	<a href="update?no=${member.no }">${member.name }</a>,
	 	${member.height }, ${member.phonenum }, ${member.email },
	 	<a href="delete?no=${member.no }">[삭제]</a> <br>
	 </c:forEach>


<jsp:include page="/Tail.jsp"></jsp:include>

</body>
</html>