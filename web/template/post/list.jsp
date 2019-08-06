<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.golony.blog.PostDto" %><%--
  Created by IntelliJ IDEA.
  User: bb016
  Date: 2019-07-22
  Time: 오후 5:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<PostDto> listObj = (ArrayList<PostDto>) request.getAttribute("list");
    int length = (int) request.getAttribute("length");
    int pageNum = Integer.parseInt(request.getParameter("page"));
%>

<html>
<head>
    <title>글 목록</title>
    <style>
        table {
            width:50%;
            border: 2px solid black;
        }

        td, th {
            border: 1px solid black;
        }

        .page {
            display: inline;
        }
    </style>
</head>
<body>
    <h2>Post List</h2>
    <p><a href="/index.do">Home</a> </p>
    <p><a href="/post/write.do">글 작성</a></p>
    <p>게시글 수: <%=listObj.size()%></p>

    <table>
        <tr>
            <th>글 번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>

        <%
            for (int i=0; i<listObj.size(); i++){
        %>
            <tr>
                <td><%=listObj.get(i).getbId()%></td>
                <td><a href="/post/content.do?id=<%=listObj.get(i).getbId()%>"><%=listObj.get(i).getTitle()%></a></td>
                <td><%=listObj.get(i).getName()%></td>
                <td><%=listObj.get(i).getDate()%></td>
                <td><%=listObj.get(i).getHit()%></td>
            </tr>
        <%
            }
        %>
    </table>

    <% for (int i=0; i<length/10; i++){
        if (i != pageNum){
    %>
        <a class="page" href="<%=String.format("/post/list.do?page=%d", i)%>"><%=i+1%></a>
    <%
            } else {
    %>
            <p class="page"><%=i+1%></p>
    <%
            }
    }
    %>
</body>
</html>
