<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������Ʈ ���</title>
<style type="text/css">
ul {padding: 10;}
li {list-style:none;}

label {
	float: left;
	text-align: right;
	width : 60px;
}

</style>
</head>
<body>
<jsp:include page="/Header.jsp"></jsp:include>
	<h1> ������Ʈ ��� </h1>
	
	<form action="add.do" method = "post">
		<ul>
			<li>
				<label for="title">����</label>
				<input id="title" type="text" name="title" size="50">
			</li>
			<br> 
			<li>
				<label for="title">����</label>
				<textarea id="content" name="content"  rows="5" cols="40"></textarea>
			</li>
			<br>
			<li>
				<label for="sdate">������</label>
				<input id="startdate" name = "startdate" type = "date" placeholder="��)2015-01-01">
			</li>
			<br>
			<li>
				<label for="edate">������</label>
				<input id="enddate" name = "enddate" type = "date" placeholder="��)2015-01-01">
			</li>
			<br>
			<li>
				<label for="tags">�±�</label>
				<input id="tags" name = "tags" type = "text" placeholder="��) �±�1, �±�2, �±�3" size="50">
			</li>

		</ul>
	<input type="submit" value="�߰�">
	<input type='reset' value='���'>
	</form>

<jsp:include page="/Tail.jsp"></jsp:include>
</body>
</html>