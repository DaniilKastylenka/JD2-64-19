<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fmt:setBundle basename="messages"/>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>

<form method="POST" action="${pageContext.request.contextPath}/createAuthorAccount" autocomplete="off">
    <table class="login-table">
        <tr>
            <td align="center" class="login-head"><fmt:message key="reg.create.author"/></td>
        </tr>
        <tr>
            <td align="center" style="padding-bottom: 20px">
                <input class="login-data-space" autofocus placeholder="<fmt:message key="reg.username"/>" type="text"
                       name="username" value="${username}">
            </td>
        </tr>
        <tr>
            <td align="center" style="padding-bottom: 20px">
                <input class="login-data-space" placeholder="<fmt:message key="reg.password"/>" type="password"
                       name="password">
            </td>
        </tr>
        <tr>
            <td align="center" style="padding-bottom: 5px">
                <input class="login-data-space" placeholder="<fmt:message key="reg.repeat.password"/>" type="password"
                       name="repeatPass">
            </td>
        </tr>
        <tr>
            <td style="height: 20px; color: #ff7777; font-size: 16px">
                <c:if test="${errorString != null}">
                    <fmt:message key="reg.error.${errorString}"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <td><input class="login-btn" type="submit" value="<fmt:message key="reg.create"/> "/></td>
        </tr>
    </table>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
