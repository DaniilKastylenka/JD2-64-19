<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fmt:setLocale scope="session" value="${param.lang}"/>
    <fmt:setBundle basename="messages"/>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>
<%@include file="/WEB-INF/include/header.jsp" %>

<div style="color: red">${errorString}</div>
<form method="post" action="${pageContext.request.contextPath}/createAuthorAccount">
    <table>
        <tr>
            <td>Username</td>
            <td><input type="text" name="username" placeholder="username"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" placeholder="password"></td>
        </tr>
        <tr>
            <td>Repeat password</td>
            <td><input type="password" name="repeatPassword" placeholder="repeat password"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="Create"></td>
        </tr>
    </table>
</form>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
