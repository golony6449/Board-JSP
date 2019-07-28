<%--
  Created by IntelliJ IDEA.
  User: bb016
  Date: 2019-07-24
  Time: 오후 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String msg = (String)request.getAttribute("msg");
%>
<html>
<head>
    <title>오류발생</title>
</head>
<body>
<h2>오류가 발생했습니다.</h2>
<%=msg%>
</body>
</html>
