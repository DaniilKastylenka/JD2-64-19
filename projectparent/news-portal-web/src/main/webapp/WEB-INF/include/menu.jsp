<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2 style="padding: 10px;">
    <a href="${pageContext.request.contextPath}/home"><fmt:message key="menu.home"/></a>
    |
    <a href="${pageContext.request.contextPath}/articleList"><fmt:message key="menu.article.list"/></a>
    <c:if test="${user != null && user.role == 'author'}">
        |
        <a href="${pageContext.request.contextPath}/createArticle"><fmt:message key="menu.create.article"/></a>
        |
        <a href="${pageContext.request.contextPath}/myArticles"><fmt:message key="menu.my.articles"/> </a>
    </c:if>

</h2>
