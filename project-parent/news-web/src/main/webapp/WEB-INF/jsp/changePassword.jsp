<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Change password</title>
    <fmt:setLocale scope="session" value="${param.lang}"/>
    <fmt:setBundle basename="messages"/>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>

<h1>Change password</h1>

<div style="color: red;">${errorString}</div>
<form method="post" action="${pageContext.request.contextPath}/changePassword">
    <table border="1px" style="border: black">
        <tr>
            <td>Old password</td>
            <td><input type="password" name="oldPassword" placeholder="old password"></td>
        </tr>
        <tr>
            <td>New password</td>
            <td><input type="password" name="newPassword" placeholder="new password"></td>
        </tr>
        <tr>
            <td>Repeat</td>
            <td><input type="password" name="repeatPassword" placeholder="repeat new password"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>
    </table>
</form>


<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
