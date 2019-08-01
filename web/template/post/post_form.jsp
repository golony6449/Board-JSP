<%--
  Created by IntelliJ IDEA.
  User: bb016
  Date: 2019-07-22
  Time: 오후 5:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%

    %>
    <title><%="글 제목"%> 수정</title>
</head>
<body>
    <form action="/post/write.do" method="post">
        <p>제목</p>
        <input type="text" name="title">
        <p>내용</p>
        <input type="text" name="content">
        <p></p>
        <input type="submit" value="저장">
        <%--첨부파일--%>
    </form>
    <a href="/post/list.do">돌아가기</a>
</body>
</html>
