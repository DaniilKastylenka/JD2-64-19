<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Article List</title>
</head>
<body>

<%=request.getAttribute("articleList")%>

<table>
    <tr>
        <td>ID</td>
        <td>Title</td>
        <td>Text</td>
        <td>Author</td>
        <td>Publication date</td>
        <td>Likes</td>
        <td>Dislikes</td>
        <td>Comments</td>
    </tr>
    <c:forEach items="${articleList}" var="article">
        <tr>
            <td><c:out value="${article.id}"/></td>
            <td><c:out value="${article.title}"/></td>
            <td><c:out value="${article.text}"/></td>
            <td><c:out value="${article.author.nickname}"/></td>
            <td><c:out value="${article.publicationDate}"/></td>
            <td><c:out value="${article.likes}"/></td>
            <td><c:out value="${article.dislikes}"/></td>
            <td><c:out value="${article.comments}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
