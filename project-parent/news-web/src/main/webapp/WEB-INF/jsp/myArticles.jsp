<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="articles.my"/> </title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>

<h1 align="center"><a class="article-title-link" href="${pageContext.request.contextPath}/myArticles?page=1"><fmt:message key="articles.my"/></a></h1>

<div align="center">
    <a style="font-size: 30px"> | </a>
    <c:forEach items="${sections}" var="section">
        <a class="article-title-link"
           <c:if test="${pageContext.request.getParameter('sectionId')==section.id}">style="font-size: 33px;color: #007bff" </c:if>
           href="${pageContext.request.contextPath}/myArticlesBySection?sectionId=${section.id}&page=1"><fmt:message key="section.${section.name}"/></a>
        <a style="font-size: 30px"> | </a>
    </c:forEach>
</div>

<div align="center" style="padding-top: 40px">
    <c:choose>
        <c:when test="${pageContext.request.parameterMap.containsKey('sectionId')}">
            <%@include file="/WEB-INF/include/pageButtonsWithSectionId.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="/WEB-INF/include/pageButtons.jsp" %>
        </c:otherwise>
    </c:choose>
</div>

<table class="articles-list">
    <col width="70%"/>
    <col width="30%"/>
    <c:forEach items="${articleList}" var="article">
        <c:if test="${article.author.username == sessionScope.user.username}">
            <tr>
                <td align="center" class="art-title-brd" colspan="2"><h1><a class="article-title-link"
                                                                            href="${pageContext.request.contextPath}/article?articleId=${article.id}">${article.title}</a>
                </h1>
                </td>
            </tr>
            <tr>
                <td class="art-text-brd" rowspan="2"><p class="article-list-text">${article.text}</p></td>
                <td class="art-info" align="left" valign="center">
                    <div style="border-bottom: 1px solid #a1a1a1; font-size: 30px"
                         align="center"><a class="article-title-link"
                                           href="${pageContext.request.contextPath}/myArticlesBySection?sectionId=${article.section.id}&page=1"><fmt:message key="section.${article.section.name}"/> </a>
                    </div>
                    <div style="border-bottom: 1px solid #e3e3e3"><fmt:message key="articles.author"/>: ${article.author.username}</div>
                    <div style="border-bottom: 1px solid #e3e3e3"><fmt:message key="articles.published"/>: <fmt:formatDate
                            value="${article.publicationDate}" pattern="dd.MM.yyy • hh:mm"/></div>
                    <c:if test="${article.updatedDate!=null}">
                        <div style="border-bottom: 1px solid #e3e3e3"><fmt:message key="articles.updated"/>: <fmt:formatDate
                                value="${article.updatedDate}" pattern="dd.MM • hh:mm"/></div>
                    </c:if>
                    <div style="border-bottom: 1px solid #e3e3e3"><fmt:message key="articles.likes"/>: ${article.likes}</div>
                    <div><fmt:message key="articles.dislikes"/>: ${article.dislikes}</div>
                </td>
            </tr>
            <tr>
                <td align="center" class="art-btns">
                    <a class="delete-btn"
                       href="${pageContext.request.contextPath}/deleteArticle?articleId=${article.id}"><fmt:message key="delete.btn"/></a> |
                    <a class="update-btn"
                       href="${pageContext.request.contextPath}/updateArticle?articleId=${article.id}"><fmt:message key="update.btn"/></a>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>
<div align="center" style="padding-bottom: 50px;">
    <c:choose>
        <c:when test="${pageContext.request.parameterMap.containsKey('sectionId')}">
            <%@include file="/WEB-INF/include/pageButtonsWithSectionIdInMyArticles.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="/WEB-INF/include/pageButtonsInMyArticles.jsp" %>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
