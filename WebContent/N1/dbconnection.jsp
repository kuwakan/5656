<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/connection/ConnectionOrDie2" method="post" >
				DBに格納する？<br>
				はい<input type="radio" name="inYesOrNo" value="1">
				いいえ<input type="radio" name="inYesOrNo" value="0"><br><br>
				XML作成する？<br>
				はい<input type="radio" name="xYesOrNo" value="1">
				いいえ<input type="radio" name="xYesOrNo" value="0"><br><br>
				<input type="submit" value="ADMINISTRATOR" />

			</form>

			<%
			request.setCharacterEncoding("UTF-8");
			String inYesOrNo = request.getParameter("inYesOrNo");
			String xYesOrNo = request.getParameter("xYesOrNo");

			%>
			<%if("1".equals(inYesOrNo)){%>
				<p>格納している</p>
			<%}else if("0".equals(inYesOrNo)){%>
				<p>格納してない</p>
			<% }%>
			<%if("1".equals(xYesOrNo)){%>
				<p>xmlしている</p>
			<%}else if("0".equals(xYesOrNo)){%>
				<p>xmlしてない</p>
			<% } %>

</body>
</html>