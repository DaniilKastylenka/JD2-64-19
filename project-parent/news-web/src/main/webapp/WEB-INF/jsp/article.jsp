<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="${param.lang}">
<head>
    <fmt:setLocale scope="session" value="${param.lang}"/>
    <fmt:setBundle basename="messages"/>
    <title>${article.title}</title>
    <meta charset="UTF-8">
</head>
<body>


<%@include file="/WEB-INF/include/menu.jsp" %>

<table class="article-tbl">
    <tr>
        <td>
            <h2 style="text-align: center; font-size: 40px;">
                ${article.title}
            </h2>
        </td>
    </tr>
    <tr>
        <td>
            <p style="width: 75%; margin: auto; padding-top: 30px; text-align: justify; font-size: 18px">${article.text}</p>
        </td>
    </tr>
    <tr>
        <td align="right">
            <div>
                <fmt:message key="article.author"/>: ${article.author.username}, <fmt:message key="article.date"/>:
                <fmt:formatDate pattern="dd.MM.yyy | HH:mm" value="${article.publicationDate}"/>
            </div>

            <c:if test="${article.updatedDate != null}">
                <div>
                    Updated: <fmt:formatDate pattern="dd.MM.yyy | HH:mm" value="${article.updatedDate}"/>
                </div>
            </c:if>
        </td>
    </tr>
</table>

<div style="margin-left: 140px;">
    <a href="${pageContext.request.contextPath}/likeArticle?articleId=${article.id}" class="article-like-btn">Like</a>
    <div style="font-size: 14px">${article.numberOfLikes} like<c:if test="${article.numberOfLikes>1}">s</c:if></div>
</div>

<table class="comment-tbl">
    <col width="20%">
    <col width="50%">
    <col width="30%">
    <form method="post" action="${pageContext.request.contextPath}/writeComment?articleId=${article.id}">
        <tr>
            <td style="text-align: center">${sessionScope.user.username}:</td>
            <td><textarea class="comment-text-place" name="text" placeholder="write your comment" required
                          maxlength="255"></textarea></td>
            <td style="text-align: center"><input type="submit" class="submit-btn" placeholder="submit"></td>
        </tr>
    </form>
</table>


<table class="comment-tbl">
    <col width="20%">
    <col width="50%">
    <col width="15%">
    <col width="15%">
    <c:forEach items="${commentList}" var="comment">
        <c:if test="${article.id == comment.article.id}">
            <tr>
                <td align="right" valign="top">${comment.user.username}:</td>
                <td style="word-wrap: break-word" valign="top"><c:out value="${comment.text}"/></td>
                <td valign="middle" align="center">
                    <a href="${pageContext.request.contextPath}/likeComment?commentId=${comment.id}" class="comment-like-btn">
                            Like
                    </a>
                    <div style="font-size: 12px">${comment.numberOfLikes} like</div>
                </td>
                <td style="text-align: center">
                    <c:if test="${comment.user == sessionScope.user or sessionScope.user.role.name == 'admin'}">
                        <a class="delete-btn"
                           href="${pageContext.request.contextPath}/deleteComment?commentId=${comment.id}">DELETE</a>
                    </c:if>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>

<%@include file="/WEB-INF/include/footer.jsp" %>

</body>
</html>
