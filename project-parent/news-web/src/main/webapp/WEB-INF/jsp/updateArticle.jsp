<%@ page import="by.it.academy.project.model.Article" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fmt:setLocale scope="session" value="${param.lang}"/>
    <fmt:setBundle basename="messages"/>
    <title>Update article</title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>
<h1>Update article</h1>
<form method="post" action="${pageContext.request.contextPath}/updateArticle?articleId=${param.articleId}">
    <table class="create-article-tbl" border="1px">
        <tr>
            <td><label for="section">Section</label></td>
            <td>
                <select id="section" name="sectionId">
                    <c:forEach items="${sections}" var="section">
                        <option <c:if test="${sectionId == section.id}">selected</c:if> value="${section.id}">
                                ${section.name}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><label>Title</label></td>
            <td><label><textarea class="article-title-place" name="title" required
                                 maxlength="255">${title}</textarea></label></td>
        </tr>
        <tr>
            <td><label>Text</label></td>
            <td><label><textarea class="article-text-place" name="text" required
                                 maxlength="65535">${text}</textarea></label></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>
    </table>
</form>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
