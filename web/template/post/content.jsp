<%@ page import="dev.golony.blog.PostDto" %><%--
  Created by IntelliJ IDEA.
  User: bb016
  Date: 2019-07-22
  Time: 오후 5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
   PostDto dto = (PostDto)request.getAttribute("post");
%>

<html>
<head>
    <title><%=dto.getTitle()%></title>
</head>
<body>
    <p><%=dto.getTitle()%></p>
    <hr>
    <p><%=dto.getName()%></p>
    <p><%=dto.getDate()%></p>
    <hr>
    <p><%=dto.getContent()%></p>
    <hr>
    <p>조회수: <%=dto.getHit()%></p>
    <a href="/template/post/list.jsp">목록</a>
    <a href="/template/post/post_form.jsp">수정</a>
</body>
</html>
