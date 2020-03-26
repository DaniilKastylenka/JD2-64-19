<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="${param.lang}">
<head>
    <fmt:setBundle basename="messages"/>
    <title>${article.title}</title>
    <meta charset="UTF-8">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script>
        <%@include file="js/ajax/ajax_like_article.js" %>
        <%@include file="js/ajax/ajax_dislike_article.js"%>
        <%@include file="js/popup.js"%>
    </script>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>
<div class="article-block">
    <table class="article-tbl">
        <tr>
            <td style="word-wrap: break-word">
                <h2 style="text-align: center; font-size: 40px; color: #555555">
                    ${article.title}
                </h2>
            </td>
        </tr>
        <tr>
            <td>
                <p style="color:#555555; width: 75%; margin: auto; padding-top: 30px; text-align: justify; font-size: 18px; white-space: pre-wrap;">${article.text}</p>
            </td>
        </tr>
        <tr>
            <td align="right" style="padding-right: 145px; color: #555555">
                <div>
                    <fmt:message key="articles.author"/>: ${article.author.username}
                </div>
                <div>
                    <fmt:message key="articles.published"/> <fmt:formatDate pattern="dd.MM.yyy • HH:mm"
                                                                            value="${article.publicationDate}"/>
                </div>

                <c:if test="${article.updatedDate != null}">
                    <div>
                        <fmt:message key="articles.updated"/> <fmt:formatDate pattern="dd.MM.yyy • HH:mm"
                                                                              value="${article.updatedDate}"/>
                    </div>
                </c:if>
                <c:if test="${sessionScope.user.role.name=='admin' or article.author==sessionScope.user}">
                    <div>
                        <a class="delete-btn"
                           href="${pageContext.request.contextPath}/deleteArticle?articleId=${article.id}"><fmt:message
                                key="delete.btn"/></a> |
                        <a class="update-btn"
                           href="${pageContext.request.contextPath}/updateArticle?articleId=${article.id}"><fmt:message
                                key="update.btn"/></a>
                    </div>
                </c:if>
            </td>
        </tr>
    </table>

    <table style="margin-left: 250px; width: 200px; height: 150px">
        <tr>
            <td align="center">
                <div style="width: 60px; height: 30px" align="center">
                    <button id="article-like-btn" class="article-like<c:if test="${isLiked}">d</c:if>-btn">like</button>
                </div>
                <div id="article-likes" style="color: #555555;font-size: 13px;">${article.likes} like(s)</div>
            </td>
            <td align="center">
                <div style="width: 60px; height: 30px" align="center">
                    <button id="article-dislike-btn" class="article-dislike<c:if test="${isDisliked}">d</c:if>-btn">
                        dislike
                    </button>
                </div>
                <div id="article-dislikes" style="color: #555555; font-size: 13px">${article.dislikes} dislike(s)
                </div>
            </td>
        </tr>
    </table>

    <h2 align="center" style="color: #555555"><fmt:message key="article.write.comment"/></h2>

    <form method="post" action="${pageContext.request.contextPath}/writeComment?articleId=${article.id}">
        <table class="comment-tbl" style="padding-bottom: 100px; ">
            <tr>
                <td colspan="2" style="background-color: inherit; border: 1px solid #c4c4c4; border-radius: 15px"><textarea
                        class="comment-text-place"
                        name="text"
                        placeholder="<fmt:message key="write.comment"/>"
                        maxlength="500">${commentText}</textarea>
                </td>
            </tr>
            <tr>
                <td align="left" style="width: 100px; height: 35px">
                    <input class="submit-btn"
                           <c:if test="${sessionScope.user == null}">onclick="PopUpShow()" </c:if>
                           type="submit"
                </td>
                <td style="padding-left: 20px; font-size: 16px; color: #ff4846">
                    <c:if test="${errorLength != null}">
                        <fmr:message key="comment.error.${errorLength}"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form>

    <table class="comment-tbl">
        <col width="55%">
        <col width="15%">
        <col width="15%">
        <col width="15%">
        <c:forEach items="${commentList}" var="comment">
            <c:if test="${article.id == comment.article.id}">
                <tr>
                    <td align="left" style="color: #555555;">${comment.user.username} • <fmt:formatDate
                            pattern="dd.MM.yyy '|' HH:mm" value="${comment.date}"/>
                    </td>
                    <td style="color: #555555; text-align: center">
                        <c:if test="${comment.user == sessionScope.user or sessionScope.user.role.name == 'admin'}">
                            <a class="delete-btn"
                               href="${pageContext.request.contextPath}/deleteComment?commentId=${comment.id}"><fmt:message
                                    key="delete.btn"/></a>
                        </c:if>
                    </td>
                    <td valign="middle" align="center" style="color: #5e5e5e;">
                        <script>
                            <%@include file="js/ajax/ajax_like_comment.js" %>
                        </script>
                        <div style="height: 20px; width: 45px;" align="center">
                            <button id="comment-like-btn${comment.id}"
                                    class="comment-like<c:if test="${comment.liked}">d</c:if>-btn">like
                            </button>
                        </div>
                        <div id="comment-likes${comment.id}" style="color: #555555; font-size: 11px">${comment.likes} like(s)</div>
                    </td>

                    <td valign="middle" align="center" style="color: #5e5e5e;">
                        <script>
                            <%@include file="js/ajax/ajax_dislike_comment.js" %>
                        </script>
                        <div style="height: 20px; width: 45px;" align="center">
                            <button id="comment-dislike-btn${comment.id}"
                                    class="comment-dislike<c:if test="${comment.disliked}">d</c:if>-btn">
                                dislike
                            </button>
                        </div>
                        <div id="comment-dislikes${comment.id}" style="color: #555555; font-size: 11px">${comment.dislikes} dislike(s)
                        </div>
                    </td>
                </tr>
                <tr>
                    <td style="padding-bottom: 15px; word-wrap: break-word; color: #565656;" valign="top" colspan="4">
                        <p style="color: #555555; white-space: pre-wrap">${comment.text}</p>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
<div class="b-popup" id="popup"
        <c:if test="${errorString == null}"> hidden
        </c:if>>
    <div class="b-popup-content">
        <a style="color: #fff;z-index: 1;font-size: 40px; position: absolute; padding-left: 20px"
           href="javascript:PopUpHide()">×</a>
        <%@include file="/WEB-INF/include/loginForm.jsp" %>
    </div>
</div>
</body>
</html>
