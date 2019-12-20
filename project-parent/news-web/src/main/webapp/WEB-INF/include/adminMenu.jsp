<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<nav role='navigation'>
    <ul>
        <li><a href="${pageContext.request.contextPath}/home"><fmt:message key="menu.home"/> </a></li>
        <li>
            <a href="${pageContext.request.contextPath}/articleList"><fmt:message key="menu.articles"/> </a>
        </li>
        <li><a href="#"><fmt:message key="menu.localisation"/> </a>
            <ul>
                <li><a href="?lang=en"><fmt:message key="menu.en"/> </a></li>
                <li><a href="?lang=ru"><fmt:message key="menu.ru"/> </a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/logout" style="color: #ff1d00"><fmt:message
                key="menu.logout"/> </a></li>
    </ul>
</nav>

