<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="reg.reg"/></title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>
<form method="POST" action="${pageContext.request.contextPath}/register">
    <table class="login-table">
        <tr>
            <td align="center" class="login-head"><fmt:message key="reg.reg"/></td>
        </tr>
        <tr>
            <td align="center" style="padding-bottom: 20px">
                <input class="login-data-space" placeholder="<fmt:message key="reg.username"/>" type="text"
                       name="username">
            </td>
        </tr>
        <tr>
            <td align="center" style="padding-bottom: 20px">
                <input class="login-data-space" placeholder="<fmt:message key="reg.password"/>" type="password"
                       name="password">
            </td>
        </tr>
        <tr>
            <td align="center">
                <input class="login-data-space" placeholder="<fmt:message key="reg.repeat.password"/>" type="password"
                       name="repeatPass">
            </td>
        </tr>
        <tr>
            <td style="height: 16px; color: red">
                ${errorString}
            </td>
        </tr>
        <tr>
            <td style="padding-bottom: 40px">
                <div style="font-size: 16px"><fmt:message key="reg.account"/>
                    <a class="reg-link" style="font-size: 16px" href="${pageContext.request.contextPath}/login">
                        <fmt:message key="reg.sign.in"/>
                    </a>
                </div>
            </td>
        </tr>
        <tr>
            <td><input class="login-btn" type="submit" value="<fmt:message key="reg.reg"/> "/></td>
        </tr>
    </table>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>

