<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Articles</title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>
<h1 align="center">Articles</h1>

<table class="articles-list">
    <col width="70%"/>
    <col width="30%"/>
    <c:forEach items="${articleList}" var="article">
        <tr>
            <td align="center" class="art-title-brd" colspan="2"><h1><a style="font-size: 30px"
                                                                        href="${pageContext.request.contextPath}/article?articleId=${article.id}">${article.title}</a>
            </h1>
            </td>
        </tr>
        <tr>
            <td class="art-text-brd" rowspan="2"><p class="article-list-text">${article.text}</p></td>
            <td class="art-info" align="left" valign="center">
                <div style="border-bottom: 1px solid #a1a1a1; font-size: 30px"
                     align="center">${article.section.name}</div>
                <div style="border-bottom: 1px solid #e3e3e3">Author: ${article.author.username}</div>
                <div style="border-bottom: 1px solid #e3e3e3">Published: <fmt:formatDate
                        value="${article.publicationDate}" pattern="dd.MM.yyy 'at' hh:mm"/></div>
                <c:if test="${article.updatedDate!=null}">
                    <div style="border-bottom: 1px solid #e3e3e3">Updated: <fmt:formatDate
                            value="${article.updatedDate}" pattern="dd.MM 'at' hh:mm"/></div>
                </c:if>
                <div style="border-bottom: 1px solid #e3e3e3">Likes: ${article.likes}</div>
                <div>Dislikes: ${article.dislikes}</div>
            </td>
        </tr>
        <tr>
            <c:if test="${(sessionScope.user.role.name=='admin' or sessionScope.user==article.author)}">
                <td align="center" class="art-btns">
                    <a class="delete-btn"
                       href="${pageContext.request.contextPath}/deleteArticle?articleId=${article.id}">DELETE</a> |
                    <a class="update-btn"
                       href="${pageContext.request.contextPath}/updateArticle?articleId=${article.id}">UPDATE</a>
                </td>
            </c:if>

        </tr>
    </c:forEach>
</table>


<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
