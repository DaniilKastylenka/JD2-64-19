<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale scope="session" value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<a href="${pageContext.request.contextPath}/articleList?page=1"><h1 class="news-header"><fmt:message key="header.title"/></h1></a>

