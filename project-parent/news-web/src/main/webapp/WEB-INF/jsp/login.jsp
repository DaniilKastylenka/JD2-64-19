<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="login.login"/></title>
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>
<%@include file="/WEB-INF/include/loginForm.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
