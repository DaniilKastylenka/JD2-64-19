<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setBundle basename="messages"/>
    <title><fmt:message key="create.article"/></title>
    <meta charset=" UTF-8">
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>

<h1 align="center" style="padding-bottom: 30px"><fmt:message key="create.article"/></h1>

<form method="POST" action="${pageContext.request.contextPath}/createArticle">
    <table class="create-article-tbl">
        <col width="80%">
        <tr>
            <td><h2><fmt:message key="create.article.title"/></h2></td>
            <td><h2><label for="section"><fmt:message key="create.article.section"/></label></h2>
        </tr>
        <tr>
            <td style="border: 1px solid #d4d4d4; border-radius: 15px">
                <textarea class="article-title-place" name="title"
                          placeholder="<fmt:message key="create.article.title"/>"
                          maxlength="255"
                          required>${title}</textarea>
            </td>
            <td valign="top">
                <div class="custom-select" style="width: 200px">
                    <select id="section" name="sectionId">
                        <option value="0"><fmt:message key="create.article.choose.section"/></option>
                        <c:forEach items="${sections}" var="section">
                            <option style="color: black" value="${section.id}">
                                <fmt:message key="section.${section.name}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div style="color: red">
                    <c:if test="${error != null}">
                        <fmt:message key="create.article.choose.section.error"/>
                    </c:if>
                </div>
                <script>
                    <%@include file="js/custom-select.js"%>
                </script>
            </td>
        </tr>
        <tr>
            <td colspan="2"><h2><fmt:message key="create.article.text"/></h2></td>
        </tr>
        <tr>
            <td colspan="2"
                style="border: 1px solid #d4d4d4; border-bottom-left-radius: 10px; border-top-left-radius: 10px">
                <textarea class="article-text-place" name="text" placeholder="<fmt:message key="create.article.text"/>"
                          required
                          maxlength="65535">${text}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="submit-btn" value="<fmt:message key="create.btn"/>"></td>
        </tr>
    </table>
</form>

<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>