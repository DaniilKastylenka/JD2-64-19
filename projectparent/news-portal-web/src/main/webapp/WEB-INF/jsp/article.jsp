<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${article.title}</title>
</head>
<body>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/menu.jsp" %>
<h2 style="text-align: center">${article.title}</h2>

<p style="width: 80%; padding:50px">${article.text}</p>

<div style="text-align: end">
    Author: ${article.author.username},
    <fmt:formatDate pattern="dd:MM:yyy | HH:mm" value="${article.date}"/>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
