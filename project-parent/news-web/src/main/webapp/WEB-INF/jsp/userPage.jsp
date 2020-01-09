<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Account</title>
    <fmt:setLocale scope="session" value="${param.lang}"/>
    <fmt:setBundle basename="messages"/>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>

<h1>My account</h1>
<div><a class="update-btn" href="${pageContext.request.contextPath}/changePassword">CHANGE MY PASSWORD</a></div>
<div><a class="delete-btn" href="${pageContext.request.contextPath}/deleteUser?userId=${sessionScope.user.id}">DELETE MY ACCOUNT</a></div>


<%@include file="/WEB-INF/include/footer.jsp"%>
</body>
</html>
