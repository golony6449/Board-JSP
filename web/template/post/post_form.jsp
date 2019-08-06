<%@ page import="dev.golony.blog.PostDto" %><%--
  Created by IntelliJ IDEA.
  User: bb016
  Date: 2019-07-22
  Time: 오후 5:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PostDto dto = null;
    String actionURL = null;
    String buttonText = null;


        dto = (PostDto) request.getAttribute("dto");

        if (dto == null){
            dto = new PostDto();
            actionURL = "/post/write.do";
            buttonText = "등록";
        } else{
            actionURL = "/post/edit.do";
            buttonText = "수정";
        }
%>

<html>
<head>
    <title><%="글 제목"%> 수정</title>
</head>
<body>
    <form id="form" action="<%=actionURL%>" method="post">
        <p>제목</p>
        <input type="number" name="bId" value="<%=dto.getbId()%>" hidden>
        <input type="text" name="title" value="<%=dto.getTitle()%>">
        <p>내용</p>
        <input type="text" name="content" value="<%=dto.getContent()%>">
<%--        <input type="submit" value="저장">--%>
        <%--첨부파일--%>
    </form>

    <button onclick="document.getElementById('form').submit()"><%=buttonText%></button>

    <a href="/post/list.do">돌아가기</a>

</body>
</html>
