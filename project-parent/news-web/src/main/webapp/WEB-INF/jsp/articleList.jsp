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


<h1><fmt:message key="article.list.page.title"/></h1>

<table border="1px" class="article-list-tbl">
    <tr>
        <td><fmt:message key="article.list.id"/></td>
        <td><fmt:message key="article.list.section"/></td>
        <td><fmt:message key="article.list.title"/></td>
        <td><fmt:message key="article.list.text"/></td>
        <td><fmt:message key="article.list.author"/></td>
        <td><fmt:message key="article.list.date"/></td>
        <td>updated date</td>
        <td><fmt:message key="article.list.likes"/></td>
        <c:if test="${sessionScope.user.role!='user'}">
            <td>Actions</td>
        </c:if>

    </tr>
    <c:forEach items="${articleList}" var="article">
        <tr>
            <td valign="top"><c:out value="${article.id}"/></td>
            <td valign="top"><c:out value="${article.section.name}"/></td>
            <td valign="top"><a href="${pageContext.request.contextPath}/article?articleId=${article.id}"><p
                    class="title-link">${article.title}</p></a></td>
            <td valign="top"><p style="overflow: hidden; height: 90px"><c:out value="${article.text}"/></p></td>
            <td valign="top"><c:out value="${article.author.username}"/></td>
            <td valign="top"><fmt:formatDate pattern="dd.MM.yyy | HH:mm" value="${article.publicationDate}"/></td>
            <td valign="top"><fmt:formatDate pattern="dd.MM.yyy | HH:mm" value="${article.updatedDate}"/></td>
            <td valign="top"><c:out value="${article.numberOfLikes}"/></td>
            <c:if test="${sessionScope.user.role!='user'}">
                <td valign="top">
                    <c:if test="${(sessionScope.user.role=='admin' or sessionScope.user==article.author)}">
                        <a class="delete-btn"
                           href="${pageContext.request.contextPath}/deleteArticle?articleId=${article.id}">DELETE</a> |
                        <a class="update-btn"
                           href="${pageContext.request.contextPath}/updateArticle?articleId=${article.id}">UPDATE</a>
                    </c:if>

                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<%@include file="/WEB-INF/include/footer.jsp" %>

</body>
</html>
