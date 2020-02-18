<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setBundle basename="messages"/>
    <title>All users</title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>

<h1 align="center" style="padding-bottom: 40px"><fmt:message key="all.users"/> </h1>
<table class="users-tbl">
    <col width="15%">
    <col width="50%">
    <col width="25%">
    <col width="10%">
    <tr>
        <th class="users-tbl-col">ID</th>
        <th class="users-tbl-col"><fmt:message key="username"/> </th>
        <th class="users-tbl-col"><fmt:message key="role"/> </th>
        <th class="users-tbl-col"><fmt:message key="users.actions"/></th>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td class="users-tbl-col"><c:out value="${user.id}"/></td>
            <td class="users-tbl-col"><c:out value="${user.username}"/></td>
            <td class="users-tbl-col"><fmt:message key="role.${user.role.name}"/></td>
            <td class="users-tbl-col" align="center">
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
