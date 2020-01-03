<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="${param.lang}">
<head>
    <fmt:setLocale scope="session" value="${param.lang}"/>
    <fmt:setBundle basename="messages"/>
    <title>${article.title}</title>
    <meta charset="UTF-8">
    <style>
        <%@include file="login_style/css/main.css" %>
        <%@include file="login_style/css/util.css" %>
        <%@include file="style/style.css"%>
        textarea {
            height: 100px;
            resize: none;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/menu.jsp" %>
<h2 style="text-align: center; font-size: 40px">${article.title}</h2>

<p style="width: 80%; padding:115px">${article.text}</p>

<div style="text-align: end; padding-right: 115px">
    <fmt:message key="article.author"/>: ${article.author.username}, <fmt:message key="article.date"/>:
    <fmt:formatDate pattern="dd:MM:yyy | HH:mm" value="${article.date}"/>
</div>

<div class="container-login100-form-btn m-t-17" style="padding-left: 100px">

    <a href="${pageContext.request.contextPath}/likeArticle?articleId=${article.id}">
        <button class="article-like-btn">
            LIKE ${article.likes}
        </button>
    </a>

</div>

<table class="comment-tbl" border="1px">
    <form method="post" action="${pageContext.request.contextPath}/writeComment?articleId=${article.id}">
        <tr>
            <td>${sessionScope.user.username}:</td>
            <td><textarea name="text" placeholder="write your comment" required maxlength="255" style="width: 100%"></textarea></td>
            <td><input type="submit" style="border-radius: 5px"></td>
        </tr>
    </form>
</table>
<table class="comment-tbl" border="1px">
    <c:forEach items="${commentList}" var="comment">
        <c:if test="${article.id == comment.article_id}">
            <tr>
                <td>${comment.user.username}</td>
                <td style="word-wrap: break-word"><c:out value="${comment.text}"/></td>
                <td>
                    <c:if test="${sessionScope.user.role == 'admin' or sessionScope.user == comment.user}">
                        <a href="${pageContext.request.contextPath}/deleteComment?commentId=${comment.id}">Delete</a>
                    </c:if>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
