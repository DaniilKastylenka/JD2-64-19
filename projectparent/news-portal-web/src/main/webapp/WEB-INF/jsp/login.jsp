<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
</head>
<body>
<%@include file="/WEB-INF/include/header.jsp"%>
<%@include file="/WEB-INF/include/menu.jsp"%>
<h3>Login page</h3>

<p style="color: red">${errorString}</p>

<form method="post" action="${pageContext.request.contextPath}/login">
    <table>
        <tr>
            <td>Username</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" ></td>
        </tr>
    </table>
</form>
<%@include file="/WEB-INF/include/footer.jsp"%>
</body>
</html>
