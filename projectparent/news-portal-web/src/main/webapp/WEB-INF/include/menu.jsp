<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${user.role == 'author'}">
    <%@include file="authorMenu.jsp" %>
</c:if>

<c:if test="${user.role == 'admin'}">
    <%@include file="adminMenu.jsp" %>
</c:if>

<c:if test="${user.role == 'user'}">
    <%@include file="userMenu.jsp" %>
</c:if>