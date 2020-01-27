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
        <td colspan="2">
            <h2 style="text-align: center; font-size: 40px;">
                ${article.title}
            </h2>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <p style="width: 75%; margin: auto; padding-top: 30px; text-align: justify; font-size: 18px">${article.text}</p>
        </td>
    </tr>
    <tr>
        <td>
            <div class="container-login100-form-btn m-t-17" style="padding-left: 100px">

                <a href="${pageContext.request.contextPath}/likeArticle?articleId=${article.id}">
                    <button class="like-btn">
                        LIKE ${article.numberOfLikes}
                    </button>
                </a>

            </div>
        </td>

        <td>
            <div style="text-align: end; padding-right: 115px">
                <fmt:message key="article.author"/>: ${article.author.username}, <fmt:message key="article.date"/>:
                <fmt:formatDate pattern="dd.MM.yyy | HH:mm" value="${article.publicationDate}"/>
            </div>

            <c:if test="${article.updatedDate != null}">
                <div style="text-align: end; padding-right: 115px">
                    Updated: <fmt:formatDate pattern="dd.MM.yyy | HH:mm" value="${article.updatedDate}"/>
                </div>
            </c:if>
        </td>
    </tr>
</table>

<table class="comment-tbl" border="1px">
    <col width="20%">
    <col width="50%">
    <col width="30%">
    <form method="post" action="${pageContext.request.contextPath}/writeComment?articleId=${article.id}">
        <tr>
            <td style="text-align: center">${sessionScope.user.username}:</td>
            <td><textarea class="comment-text-place" name="text" placeholder="write your comment" required
                          maxlength="255"></textarea></td>
            <td style="text-align: center"><input type="submit" style="border-radius: 5px"></td>
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
                <td style="text-align: center">${comment.user.username}</td>
                <td style="word-wrap: break-word"><c:out value="${comment.text}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/likeComment?commentId=${comment.id}">
                        <button class="like-btn">
                            LIKE ${comment.numberOfLikes}
                        </button>
                    </a>
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
