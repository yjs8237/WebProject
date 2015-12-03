<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ page import="spms.vo.Member" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>회원목록</h1>
<p><a href='add.do'>신규 회원</a></p>
<table border="1">
<tr>
  <th><c:choose>
    <c:when test="${orderCond == 'MNO_ASC'}">
      <a href="list.do?orderCond=MNO_DESC">번호↑</a>
    </c:when>
    <c:when test="${orderCond == 'MNO_DESC'}">
      <a href="list.do?orderCond=MNO_ASC">번호↓</a>
    </c:when>
    <c:otherwise>
      <a href="list.do?orderCond=MNO_ASC">번호︎</a>
    </c:otherwise>
  </c:choose></th>
  <th><c:choose>
    <c:when test="${orderCond == 'NAME_ASC'}">
      <a href="list.do?orderCond=NAME_DESC">이름↑</a>
    </c:when>
    <c:when test="${orderCond == 'NAME_DESC'}">
      <a href="list.do?orderCond=NAME_ASC">이름↓</a>
    </c:when>
    <c:otherwise>
      <a href="list.do?orderCond=NAME_ASC">이름</a>
    </c:otherwise>
  </c:choose></th>  
  <th><c:choose>
    <c:when test="${orderCond == 'EMAIL_ASC'}">
      <a href="list.do?orderCond=EMAIL_DESC">이메일↑</a>
    </c:when>
    <c:when test="${orderCond == 'EMAIL_DESC'}">
      <a href="list.do?orderCond=EMAIL_ASC">이메일↓</a>
    </c:when>
    <c:otherwise>
      <a href="list.do?orderCond=EMAIL_ASC">이메일</a>
    </c:otherwise>
  </c:choose></th>
  <th><c:choose>
    <c:when test="${orderCond == 'CREDATE_ASC'}">
      <a href="list.do?orderCond=CREDATE_DESC">등록일↑</a>
    </c:when>
    <c:when test="${orderCond == 'CREDATE_DESC'}">
      <a href="list.do?orderCond=CREDATE_ASC">등록일↓</a>
    </c:when>
    <c:otherwise>
      <a href="list.do?orderCond=CREDATE_ASC">등록일</a>
    </c:otherwise>
  </c:choose></th>
  <th></th>
</tr>
  <c:forEach var="member" items="${members }">
	 	${member.no }, 
	 	<a href="update.do?no=${member.no }">${member.name }</a>,
	 	${member.height }, ${member.phonenum }, ${member.email },
	 	<a href="delete.do?no=${member.no }">[삭제]</a> <br>
	 </c:forEach>
</table>
	 
	 
	
	 
	


<jsp:include page="/Tail.jsp"></jsp:include>

</body>
</html>