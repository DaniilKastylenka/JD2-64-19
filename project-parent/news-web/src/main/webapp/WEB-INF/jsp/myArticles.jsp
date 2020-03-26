<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <fmt:setBundle basename="messages"/>
    <title><fmt:message key="articles.my"/></title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>

<div class="articles-block">
    <h1 align="center"><a style="font-size: 40px" class="article-title-link"
                          href="${pageContext.request.contextPath}/myArticles?page=1"><fmt:message
            key="articles.my"/></a>
    </h1>

    <form action="${pageContext.request.contextPath}/searchMyArticles" autocomplete="off">
        <table style="margin-left: auto; margin-right: auto">
            <tr>
                <td>
                    <input class="search-bar" type="search" name="searchRequest"
                           placeholder="<fmt:message key="search"/> "
                           onfocus="this.placeholder=''" onblur="this.placeholder='<fmt:message key="search"/>'"
                           required>
                </td>
            </tr>
        </table>
    </form>

    <c:if test="${sections != null}">
        <div align="center">
            <a style="color:#555555; font-size: 30px"> | </a>
            <c:forEach items="${sections}" var="section">
                <a class="article-title-link"
                   <c:if test="${pageContext.request.getParameter('sectionId')==section.id}">style="font-size: 33px;color: #007bff" </c:if>
                   href="${pageContext.request.contextPath}/myArticlesBySection?sectionId=${section.id}&page=1"><fmt:message
                        key="section.${section.name}"/></a>
                <a style="color: #555555; font-size: 30px"> | </a>
            </c:forEach>
        </div>
    </c:if>

    <div align="center" style="padding-top: 40px">
        <c:choose>
            <c:when test="${pageContext.request.parameterMap.containsKey('sectionId')}">
                <%@include file="/WEB-INF/include/pageButtonsWithSectionIdInMyArticles.jsp" %>
            </c:when>
            <c:otherwise>
                <%@include file="/WEB-INF/include/pageButtonsInMyArticles.jsp" %>
            </c:otherwise>
        </c:choose>
    </div>

    <c:choose>
        <c:when test="${fn:length(articleList) < 1 and param.searchRequest!=null}">
            <div align="center" style="margin-left: 35%; margin-right: 35%; font-size: 20px; color: #555555; ">
                <fmt:message
                        key="no.articles"/> "${param.searchRequest}"
            </div>
        </c:when>
        <c:when test="${fn:length(articleList) >= 1 and param.searchRequest!=null}">
            <div align="center" style="color: #555555;margin-left: 35%; margin-right: 35%; font-size:20px"><fmt:message
                    key="search.result"/> "${param.searchRequest}"
            </div>
        </c:when>
    </c:choose>

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
                                               href="${pageContext.request.contextPath}/myArticlesBySection?sectionId=${article.section.id}&page=1"><fmt:message
                                key="section.${article.section.name}"/> </a>
                        </div>
                        <div style="border-bottom: 1px solid #e3e3e3"><fmt:message
                                key="articles.author"/>: ${article.author.username}</div>
                        <div style="border-bottom: 1px solid #e3e3e3"><fmt:message key="articles.published"/>:
                            <fmt:formatDate
                                    value="${article.publicationDate}" pattern="dd.MM.yyy • HH:mm"/></div>
                        <c:if test="${article.updatedDate!=null}">
                            <div style="border-bottom: 1px solid #e3e3e3"><fmt:message key="articles.updated"/>:
                                <fmt:formatDate
                                        value="${article.updatedDate}" pattern="dd.MM • HH:mm"/></div>
                        </c:if>
                        <div style="border-bottom: 1px solid #e3e3e3"><fmt:message
                                key="articles.likes"/>: ${article.likes}</div>
                        <div><fmt:message key="articles.dislikes"/>: ${article.dislikes}</div>
                    </td>
                </tr>
                <tr>
                    <td align="center" class="art-btns">
                        <a class="delete-btn"
                           href="${pageContext.request.contextPath}/deleteArticle?articleId=${article.id}"><fmt:message
                                key="delete.btn"/></a> <a style="color: #555555">|</a>
                        <a class="update-btn"
                           href="${pageContext.request.contextPath}/updateArticle?articleId=${article.id}"><fmt:message
                                key="update.btn"/></a>
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
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
