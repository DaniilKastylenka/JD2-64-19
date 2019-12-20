<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale scope="session" value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<style>
    <%@include file="menu_style/style.css" %>
</style>

<c:if test="${user.role == 'author'}">
    <%@include file="authorMenu.jsp" %>
</c:if>

<c:if test="${user.role == 'admin'}">
    <%@include file="adminMenu.jsp" %>
</c:if>

<c:if test="${user.role == 'user'}">
    <%@include file="userMenu.jsp" %>
</c:if>