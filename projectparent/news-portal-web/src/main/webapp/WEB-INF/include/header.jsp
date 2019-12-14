<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale scope="session" value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1 style="margin-top: 10px"><fmt:message key="header.title"/></h1>
    </div>
    <div style="text-align: center; padding: 15px">
        <fmt:message key="header.localisation"/>
        <a href="?lang=en" ><fmt:message key="header.localisation.en"/> </a> |
        <a href="?lang=ru" ><fmt:message key="header.localisation.ru"/> </a>
    </div>
    <div style="text-align: end; padding: 15px">
        <c:if test="${user != null}">
            <fmt:message key="header.hello"/><b>${user.username}</b>
            <a href="${pageContext.request.contextPath}/logout">
                <fmt:message key="header.logout"/></a></c:if>
    </div>

</div>

