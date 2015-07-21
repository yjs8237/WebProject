<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String v1 = "";
	String v2 = "";
	String result = "";
	String[] selected = {"","","",""};
	
	if(request.getParameter("v1") != null){
		v1 = request.getParameter("v1");
		v2 = request.getParameter("v2");
		String op = request.getParameter("op");
		
		result = calculate(Integer.parseInt(v1), Integer.parseInt(v2), op);
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2> JSP Practice </h2>
	<form action="Calculator.jsp" method="get">
		<input type="text" name="v1" size="4" value="<%=v1%>">
		<select name="op" >
			<option value="+" <%=selected[0] %>>+</option>
			<option value="-" <%=selected[0] %>>-</option>
			<option value="*" <%=selected[0] %>>*</option>
			<option value="/" <%=selected[0] %>>/</option>
		</select>
		<input type="text" name="v2" size ="4" value="<%=v2 %>">
		<input type="submit" value="=">
		<input type="text" size="8" value="<%=result %>"> <br>		
	</form>
	
</body>
</html>
<%!
	private String calculate(int a, int b, String op){
		int r = 0;
		
		if(op.equals("+")){
			r = a+b;
		}else if(op.equals("-")){
			r = a-b;
		} else if(op.equals("*")){
			r = a*b;
		} else if(op.equals("/")){
			r = a/b;
		}
		return String.valueOf(r);
	}


 %>