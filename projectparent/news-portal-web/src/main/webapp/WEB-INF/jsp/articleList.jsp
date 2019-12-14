<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Article List</title>
</head>
<body>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/menu.jsp" %>

<table border="1px" style="width: 100%; border: black">
    <tr>
        <td>ID</td>
        <td>Section</td>
        <td>Title</td>
        <td>Text</td>
        <td>Author</td>
        <td>Date</td>
        <td>Likes</td>
        <td>Dislikes</td>
        <td>Comments</td>
    </tr>
    <c:forEach items="${articleList}" var="article">
        <tr>
            <td><c:out value="${article.id}"/></td>
            <td><c:out value="${article.section}"/></td>
            <td><c:out value="${article.title}"/></td>
            <td><c:out value="${article.text}"/></td>
            <td><c:out value="${article.author.username}"/></td>
            <td><fmt:formatDate pattern="dd.MM.yyy | HH:mm" value="${article.date}"/> </td>
            <td><c:out value="${article.likes}"/></td>
            <td><c:out value="${article.dislikes}"/></td>
            <td><c:out value="${article.comments}"/></td>
        </tr>
    </c:forEach>
</table>
<%@include file="/WEB-INF/include/footer.jsp" %>

</body>
</html>
