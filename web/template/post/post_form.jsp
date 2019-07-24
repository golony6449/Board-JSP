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
    <title><%="글 제목"%> 수정</title>
</head>
<body>
    <form action="" method="post">
        <input type="text" name="title">
        <input type="text" name="content">
        <input type="submit" value="저장">
        <%--첨부파일--%>
    </form>
    <a href="/template/post/list.jsp">돌아가기</a>
</body>
</html>
