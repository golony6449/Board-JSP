<%--
  Created by IntelliJ IDEA.
  User: bb016
  Date: 2019-07-22
  Time: 오후 5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<form action="trylogin.do" method="post">
    <p>ID <input type="text" name="id"> </p>
    <p>PW <input type="password" name="pw"></p>
    <input type="submit" value="로그인">
</form>
<a href="/user/join.jsp">회원가입</a>
</body>
</html>
