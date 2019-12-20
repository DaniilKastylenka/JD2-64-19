<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${param.lang}">
<head>
    <fmt:setLocale scope="session" value="${param.lang}"/>
    <fmt:setBundle basename="messages"/>
    <title>${article.title}</title>
    <meta charset="UTF-8">
</head>
<body>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/menu.jsp" %>
<h2 style="text-align: center; font-size: 40px">${article.title}</h2>

<p style="width: 80%; padding:115px">${article.text}</p>

<div style="text-align: end; padding-right: 115px">
    <fmt:message key="article.author"/>: ${article.author.username}, <fmt:message key="article.date"/>:
    <fmt:formatDate pattern="dd:MM:yyy | HH:mm" value="${article.date}"/>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
