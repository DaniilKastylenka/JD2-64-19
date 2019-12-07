<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Create Article</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/articleCreate">
    <label>Title <input type="text" name="title"/></label>
    <br>
    <label>Text <input type="text" name="text"/></label>
    <br>
    <label>Author <input type="text" name="author"/></label>
    <br>
    <input type="submit" value="Create"/>
</form>

</body>
</html>
