<%--
  Created by IntelliJ IDEA.
  User: ahngilung
  Date: 2022/04/29
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    String requestUri = request.getQueryString();
%>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post" action="/login" >
    <input type="hidden" name="requestUri" value="<%=requestUri%>}}">
    아이디: <input type="text" name="id" /><br />
    비밀번호: <input type="password" name="pwd" /><br />
    <input type="submit" value="로그인" />
</form>
</body>
</html>