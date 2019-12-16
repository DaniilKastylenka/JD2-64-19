<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
</head>
<body>
<%@include file="/WEB-INF/include/header.jsp" %>
<h3><fmt:message key="login.title"/></h3>

<p style="color: red">${errorString}</p>

<form method="post" action="${pageContext.request.contextPath}/login">
    <table>
        <tr>
            <td><fmt:message key="login.username"/></td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td><fmt:message key="login.password"/></td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>
    </table>
</form>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
