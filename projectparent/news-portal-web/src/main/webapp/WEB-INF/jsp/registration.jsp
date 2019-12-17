<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<%@include file="/WEB-INF/include/header.jsp" %>

<h3>Registration page</h3>

<p style="color: red">${errorMessage}</p>

<form method="post" action="${pageContext.request.contextPath}/register">
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
            <td>Repeat password</td>
            <td><input type="password" name="repeatPass"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>
    </table>
</form>
</body>
</html>
