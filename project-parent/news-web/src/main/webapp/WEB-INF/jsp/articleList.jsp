<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head lang="${param.lang}">
    <fmt:setLocale scope="session" value="${param.lang}"/>
    <fmt:setBundle basename="messages"/>
    <title><fmt:message key="article.list.page.title"/></title>
    <meta charset="UTF-8">
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>
<%@include file="/WEB-INF/include/header.jsp" %>


<h1 style="padding-top: 20px; padding-left: 115px"><fmt:message key="article.list.page.title"/></h1>

<table border="1px" style="width: 100%; border: black; padding-top: 24px">
    <tr>
        <td><fmt:message key="article.list.id"/></td>
        <td><fmt:message key="article.list.section"/></td>
        <td><fmt:message key="article.list.title"/></td>
        <td><fmt:message key="article.list.text"/></td>
        <td><fmt:message key="article.list.author"/></td>
        <td><fmt:message key="article.list.date"/></td>
        <td><fmt:message key="article.list.likes"/></td>
        <c:if test="${sessionScope.user.role!='user'}">
            <td>Actions</td>
        </c:if>

    </tr>
    <c:forEach items="${articleList}" var="article">
        <tr>
            <td><c:out value="${article.id}"/></td>
            <td><c:out value="${article.section.name}"/></td>
            <td><a href="${pageContext.request.contextPath}/article?articleId=${article.id}">${article.title}</a></td>
            <td><c:out value="${article.text}"/></td>
            <td><c:out value="${article.author.username}"/></td>
            <td><fmt:formatDate pattern="dd.MM.yyy | HH:mm" value="${article.date}"/></td>
            <td><c:out value="${article.likes}"/></td>
            <c:if test="${sessionScope.user.role!='user'}">
                <td>
                    <c:if test="${(sessionScope.user.role=='admin' or sessionScope.user==article.author)}">
                        <a style="color: red" href="${pageContext.request.contextPath}/deleteArticle?articleId=${article.id}">DELETE</a>|
                        <a style="color: #007bff" href="${pageContext.request.contextPath}/updateArticle?articleId=${article.id}">UPDATE</a>
                    </c:if>

                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<%@include file="/WEB-INF/include/footer.jsp" %>

</body>
</html>
