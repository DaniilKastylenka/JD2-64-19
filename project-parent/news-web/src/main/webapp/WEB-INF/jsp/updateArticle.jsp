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
<%@include file="/WEB-INF/include/menu.jsp"%>
<%@include file="/WEB-INF/include/header.jsp"%>
<h1 style="padding-top: 20px; padding-left: 115px">Update article</h1>
<form method="post" action="${pageContext.request.contextPath}/updateArticle?articleId=${param.articleId}">
    <table>
        <tr>
            <td><label for="section">Section</label></td>
            <td>
                <select id="section" name="sectionId">
                    <c:forEach items="${sections}" var="section">
                        <option value="${section.id}">
                                ${section.name}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><label>Title</label></td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td><label>Text</label></td>
            <td><input type="text" name="text"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>
    </table>
</form>
<%@include file="/WEB-INF/include/footer.jsp"%>
</body>
</html>
