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
<%@include file="/WEB-INF/include/header.jsp" %>

<h1 style="padding-top: 20px; padding-left: 115px">My account</h1>

<a href="${pageContext.request.contextPath}/deleteUser?userId=${sessionScope.user.id}" style="color: red">Delete my account</a>


<%@include file="/WEB-INF/include/footer.jsp"%>
</body>
</html>
