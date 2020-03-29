<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Account</title>
    <fmt:setBundle basename="messages"/>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>

<div class="account-block" align="center">
    <h1 style="color: #555555; padding-bottom: 15px"><fmt:message key="my.account"/></h1>
    <div><a class="update-btn" style="font-size: 20px" href="${pageContext.request.contextPath}/changePassword"><fmt:message key="change.my.password"/></a></div>
    <div><a class="delete-btn" style="font-size: 20px" href="${pageContext.request.contextPath}/deleteUser?userId=${sessionScope.user.id}"><fmt:message key="delete.my.account"/> </a></div>
</div>

<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
