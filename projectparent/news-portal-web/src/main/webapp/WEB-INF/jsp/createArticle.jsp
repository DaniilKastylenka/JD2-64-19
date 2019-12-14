<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Create Article</title>
</head>
<body>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/menu.jsp" %>

<form method="post" action="${pageContext.request.contextPath}/createArticle">
    <table>
        <tr>
            <td>Section</td>
            <td><input type="text" name="section"></td>
        </tr>
        <tr>
            <td>Ttile</td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td>Text</td>
            <td><input type="text" name="text"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"></td>
        </tr>
    </table>
</form>
<%@include file="/WEB-INF/include/footer.jsp" %>

</body>
</html>
