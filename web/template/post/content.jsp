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
   boolean auth = false;

   if (session.getAttribute("id").equals(dto.getName())){
       auth = true;
   }
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
    <a href="/post/list.do">목록</a>
    <a href="/post/edit.do?id=<%=dto.getbId()%>">수정</a>
<p><%=session.getAttribute("id")%></p>
    <p><%=dto.getName()%></p>
<%
    if (auth){
%>
    <form action="/post/delete.do" method="post">
        <input type="number" name="id" value="<%=dto.getbId()%>" hidden>
        <input type="submit" value="삭제">
    </form>
<%
    }
%>
</body>
</html>
