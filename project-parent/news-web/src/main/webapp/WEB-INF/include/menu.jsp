<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    <%@include file="menu_style/style.css" %>
</style>

<nav role='navigation'>
    <ul>
        <c:if test="${sessionScope.user.role != 'user'}">
            <%@include file="menuForAdminAndAuthor.jsp" %>
        </c:if>
        <c:if test="${sessionScope.user.role == 'user'}">
            <%@include file="menuForUser.jsp" %>
        </c:if>
        <li><a href="#"><fmt:message key="menu.localisation"/></a>
            <ul>
                <li><a href="?lang=en"><fmt:message key="menu.en"/></a></li>
                <li><a href="?lang=ru"><fmt:message key="menu.ru"/></a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/home"
               style="color: green">Hello ${sessionScope.user.username}</a>
            <ul>

                <li><a href="${pageContext.request.contextPath}/home"><fmt:message key="menu.home"/> </a></li>

                <li><a href="${pageContext.request.contextPath}/logout" style="color: #ff1d00"><fmt:message
                        key="menu.logout"/></a></li>

            </ul>
        </li>

    </ul>
</nav>