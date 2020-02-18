<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages"/>
<c:if test="${param.lang != null}">
    <% session.setAttribute("locale", request.getParameter("lang")); %>
</c:if>
<c:if test="${sessionScope.locale != null}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<style>
    <%@include file="menu_style/style.css" %>
    <%@include file="/WEB-INF/jsp/style/style.css"%>
</style>
<nav role="navigation">
    <%@include file="header.jsp"%>
    <c:choose>
        <c:when test="${sessionScope.user != null}">
            <%@include file="menuForAuthUser.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="menuForNonAuthUser.jsp" %>
        </c:otherwise>
    </c:choose>
</nav>
