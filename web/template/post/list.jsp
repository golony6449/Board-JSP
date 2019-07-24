<%--
  Created by IntelliJ IDEA.
  User: bb016
  Date: 2019-07-22
  Time: 오후 5:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    </style>
</head>
<body>
    <h2>Post List</h2>
    <p><a href="/template/common/index.jsp">Home</a> </p>
    <p><a href="/template/post/post_form.jsp">글 작성</a></p>

    <table>
        <tr>
            <th>글 번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>

        <%
            for (int i=0; i<10; i++){
        %>
            <tr>
                <td><%="글번호"%></td>
                <td><a href="/template/post/content.jsp"><%="내용"%></a></td>
                <td><%="작성자"%></td>
                <td><%="작성일"%></td>
            </tr>
        <%
            }
        %>
<%--페이지 번호로 DB 정보 분할 출력--%>
    </table>
</body>
</html>
