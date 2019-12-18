<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>myArticles</title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>
<%@include file="/WEB-INF/include/header.jsp" %>

<h1 style="padding-top: 20px; padding-left: 115px">My articles</h1>

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
        <td>Actions</td>
    </tr>
    <c:forEach items="${articles}" var="article">
        <c:if test="${article.author.username == user.username}">
            <tr>
                <td><c:out value="${article.id}"/></td>
                <td><c:out value="${article.section.name}"/></td>
                <td><a href="${pageContext.request.contextPath}/article?id=${article.id}">${article.title}</a></td>
                <td><c:out value="${article.text}"/></td>
                <td><c:out value="${article.author.username}"/></td>
                <td><fmt:formatDate pattern="dd.MM.yyy | HH:mm" value="${article.date}"/></td>
                <td><c:out value="${article.likes}"/></td>
                <td><c:out value="${article.dislikes}"/></td>
                <td><c:out value="${article.comments}"/></td>
                <td><a href="${pageContext.request.contextPath}/articleDelete?id=${article.id}">Delete</a></td>
            </tr>
        </c:if>
    </c:forEach>
</table>

<%@include file="/WEB-INF/include/footer.jsp" %>

</body>
</html>
