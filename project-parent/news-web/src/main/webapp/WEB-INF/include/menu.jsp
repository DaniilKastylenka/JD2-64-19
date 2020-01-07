<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    <%@include file="menu_style/style.css" %>
    a{
        text-decoration: none;
    }
</style>

<nav role='navigation'>
    <ul>
        <c:if test="${sessionScope.user.role == 'author'}">
            <%@include file="menuForAuthor.jsp" %>
        </c:if>
        <c:if test="${sessionScope.user.role == 'admin'}">
            <%@include file="menuForAdmin.jsp" %>
        </c:if>
        <c:if test="${sessionScope.user.role == 'user'}">
            <%@include file="menuForUser.jsp" %>
        </c:if>
        <li><a href="#"><fmt:message key="menu.localisation"/></a>
            <ul>
                <li><a href="?lang=en">English</a></li>
                <li><a href="?lang=ru">Русский</a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/home"
               style="color: green">Hello ${sessionScope.user.username}</a>
            <ul>

                <li><a href="${pageContext.request.contextPath}/home"><fmt:message key="menu.home"/> </a></li>

                <li><a href="${pageContext.request.contextPath}/userPage">Account</a></li>

                <li><a href="${pageContext.request.contextPath}/logout" style="color: #ff1d00"><fmt:message
                        key="menu.logout"/></a></li>

            </ul>
        </li>

    </ul>
</nav>
