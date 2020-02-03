<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="${param.lang}">
<head>
    <fmt:setLocale scope="session" value="${param.lang}"/>
    <fmt:setBundle basename="messages"/>
    <title>${article.title}</title>
    <meta charset="UTF-8">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        <%@include file="js/ajax/ajax_like_article.js" %>
    </script>
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
        <td align="right" style="padding-right: 145px; color: #878b8f">
            <div>
                <fmt:message key="article.author"/>: ${article.author.username}
                <fmt:formatDate pattern="dd.MM.yyy 'at' HH:mm" value="${article.publicationDate}"/>
            </div>

            <c:if test="${article.updatedDate != null}">
                <div>
                    Updated <fmt:formatDate pattern="dd.MM.yyy 'at' HH:mm" value="${article.updatedDate}"/>
                </div>
            </c:if>
        </td>
    </tr>
</table>

<div style="margin-left: 140px; width: 60px; height: 30px" align="center">
    <button id="article-like-btn" class="article-like-btn">Like</button>
</div>
<div id="article-likes" style="font-size: 14px; margin-left: 140px  ">${article.numberOfLikes} like(s)</div>


<h2 align="center">Write your comment</h2>

<table class="comment-tbl">
    <form method="post" action="${pageContext.request.contextPath}/writeComment?articleId=${article.id}">
        <tr>
            <td align="left">
                <div style="font-size: 20px">${sessionScope.user.username}:</div>
            </td>
        </tr>
        <tr>
            <td style="border: 1px solid #d4d4d4; border-radius: 15px"><textarea class="comment-text-place" name="text"
                                                                                 placeholder="write your comment"
                                                                                 required
                                                                                 maxlength="500"></textarea></td>
        </tr>
        <tr>
            <td align="left" style="width: 100px; height: 35px"><input type="submit" class="submit-btn" placeholder="submit"></td>
        </tr>
    </form>
</table>

<h2 align="center">Comments</h2>

<table class="comment-tbl">
    <col width="70%">
    <col width="15%">
    <col width="15%">
    <c:forEach items="${commentList}" var="comment">
        <c:if test="${article.id == comment.article.id}">
            <tr>
                <td align="left" style="color: #5e5e5e;">${comment.user.username} • <fmt:formatDate
                        pattern="dd.MM.yyy 'at' HH:mm" value="${comment.date}"/>
                </td>
                <td style="color: #5e5e5e; text-align: center">
                    <c:if test="${comment.user == sessionScope.user or sessionScope.user.role.name == 'admin'}">
                        <a class="delete-btn"
                           href="${pageContext.request.contextPath}/deleteComment?commentId=${comment.id}">DELETE</a>
                    </c:if>
                </td>
                <td valign="middle" align="right" style="color: #5e5e5e;">
                    <script>
                        <%@include file="js/ajax/ajax_like_comment.js" %>
                    </script>
                    <div style="height: 20px; width: 40px;" align="center">
                        <button id="comment-btn${comment.id}" class="comment-like-btn">Like</button>
                    </div>
                    <div id="comment-likes${comment.id}" style="font-size: 12px">${comment.numberOfLikes} like(s)</div>
                </td>
            </tr>
            <tr>
                <td style="word-wrap: break-word; padding-bottom: 30px; color: #565656;" valign="center" colspan="3">
                    <c:out
                            value="${comment.text}"/></td>
            </tr>
        </c:if>
    </c:forEach>
</table>

<%@include file="/WEB-INF/include/footer.jsp" %>

</body>
</html>
