<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="create.article.page.title"/></title>
    <meta charset="UTF-8">
</head>
<body>

<%@include file="/WEB-INF/include/menu.jsp" %>
<%@include file="/WEB-INF/include/header.jsp" %>
<h1 style="padding-top: 20px; padding-left: 115px"><fmt:message key="create.article.page.title"/></h1>

<form method="post" action="${pageContext.request.contextPath}/createArticle">
    <table>
        <tr>
            <td><label for="section"><fmt:message key="create.article.section"/></label></td>
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
            <td><fmt:message key="create.article.title"/></td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td><fmt:message key="create.article.text"/> </td>
            <td><input type="text" name="text"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>
    </table>
</form>
<%@include file="/WEB-INF/include/footer.jsp" %>

</body>
</html>
