<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="login.login"/></title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>
<form method="POST" action="${pageContext.request.contextPath}/login">
    <table class="login-table">
        <tr>
            <td align="center" class="login-head"><fmt:message key="login.login"/></td>
        </tr>
        <tr>
            <td align="center" style="padding-bottom: 20px">
                <input class="login-data-space" value="${username}" placeholder="<fmt:message key="login.username"/>" type="text"
                       name="username">
            </td>
        </tr>
        <tr>
            <td align="center">
                <input class="login-data-space" placeholder="<fmt:message key="login.password"/>" type="password"
                       name="password">
            </td>
        </tr>
        <tr>
            <td style="height: 16px; color: red">
                ${errorString}
            </td>
        </tr>
        <tr>
            <td style="padding-bottom: 40px">
                <div style="font-size: 16px"><fmt:message key="login.no.account"/>
                    <a class="reg-link" style="font-size: 16px" href="${pageContext.request.contextPath}/register">
                    <fmt:message key="login.sign.up"/>
                </a></div>
            </td>
        </tr>
        <tr>
            <td><input class="login-btn" type="submit" value="<fmt:message key="login.login"/> "/></td>
        </tr>
    </table>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
