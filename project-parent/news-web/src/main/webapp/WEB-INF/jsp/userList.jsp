<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale scope="session" value="${param.lang}"/>
    <fmt:setBundle basename="messages"/>
    <title>All users</title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>

<h1>All users</h1>
<table border="1px" style="width: 100%; border: black">
    <tr>
        <td>User ID</td>
        <td>Username</td>
        <td>Role</td>
        <td>Actions</td>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.username}"/></td>
            <td><c:out value="${user.role.name}"/></td>
            <td>
                <c:if test="${user.role.name != 'admin'}">
                    <a class="delete-btn"
                       href="${pageContext.request.contextPath}/deleteUser?userId=${user.id}">DELETE</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
