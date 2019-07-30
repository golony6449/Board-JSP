<%--
  Created by IntelliJ IDEA.
  User: bb016
  Date: 2019-07-22
  Time: 오후 5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<form action="/user/register.do" method="post">
    <p>ID: <input type="text" name="id"></p>
    <p>PW: <input type="password" name="pw"></p>
    <p>Name: <input type="text" name="name"></p>
    <input type="submit" value="회원가입">
</form>
</body>
</html>
