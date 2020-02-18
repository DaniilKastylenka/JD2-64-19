<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setBundle basename="messages"/>
    <title><fmt:message key="articles.articles"/></title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>
<h1 align="center"><a style="font-size: 40px" class="article-title-link"
                      href="${pageContext.request.contextPath}/articleList?page=1"><fmt:message
        key="articles.articles"/></a></h1>

<form action="${pageContext.request.contextPath}/searchArticle" autocomplete="off">
    <table style="margin-left: auto; margin-right: auto">
        <tr>
            <td>
                <input class="search-bar" type="search" name="searchRequest" placeholder="<fmt:message key="search"/> "
                       onfocus="this.placeholder=''" onblur="this.placeholder='<fmt:message key="search"/> '" required>
            </td>
        </tr>
    </table>
</form>

<c:if test="${sections != null}">
    <div align="center">
        <a style="font-size: 30px"> | </a>
        <c:forEach items="${sections}" var="section">
            <a class="article-title-link"
               <c:if test="${pageContext.request.getParameter('sectionId')==section.id}">style="color: #007bff; font-size: 33px" </c:if>
               href="${pageContext.request.contextPath}/articleListBySection?sectionId=${section.id}&page=1"><fmt:message
                    key="section.${section.name}"/></a>
            <a style="font-size: 30px"> | </a>
        </c:forEach>
    </div>
</c:if>


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
                                       href="${pageContext.request.contextPath}/articleListBySection?page=1&sectionId=${article.section.id}"><fmt:message
                        key="section.${article.section.name}"/></a>
                </div>
                <div style="border-bottom: 1px solid #e3e3e3"><fmt:message key="articles.author"/>
                    : ${article.author.username}</div>
                <div style="border-bottom: 1px solid #e3e3e3"><fmt:message key="articles.published"/>: <fmt:formatDate
                        value="${article.publicationDate}" pattern="dd.MM.yyy • HH:mm"/></div>
                <c:if test="${article.updatedDate!=null}">
                    <div style="border-bottom: 1px solid #e3e3e3"><fmt:message key="articles.updated"/>: <fmt:formatDate
                            value="${article.updatedDate}" pattern="dd.MM • HH:mm"/></div>
                </c:if>
                <div style="border-bottom: 1px solid #e3e3e3"><fmt:message
                        key="articles.likes"/>: ${article.likes}</div>
                <div><fmt:message key="articles.dislikes"/>: ${article.dislikes}</div>
            </td>
        </tr>
        <tr>
            <td align="center" class="art-btns">
                <c:if test="${(sessionScope.user.role.name=='admin' or sessionScope.user==article.author)}">

                    <a class="delete-btn"
                       href="${pageContext.request.contextPath}/deleteArticle?articleId=${article.id}"><fmt:message
                            key="delete.btn"/> </a> |
                    <a class="update-btn"
                       href="${pageContext.request.contextPath}/updateArticle?articleId=${article.id}"><fmt:message
                            key="update.btn"/></a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<div align="center" style="padding-bottom: 50px">
    <c:choose>
        <c:when test="${pageContext.request.parameterMap.containsKey('sectionId')}">
            <%@include file="/WEB-INF/include/pageButtonsWithSectionId.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="/WEB-INF/include/pageButtons.jsp" %>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
