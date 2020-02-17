<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/articleList?page=1"><fmt:message key="menu.articles"/></a>
        </li>
        <li><a href="#"><fmt:message key="menu.localisation"/></a>
            <ul>
                <li>
                    <a href="<c:url value="${requestScope['javax.servlet.forward.request_uri.param']}">
                    <c:forEach items="${param}" var="entry">
                    <c:if test="${entry.key!='lang'}">
                    <c:param name="${entry.key}" value="${entry.value}"/></c:if>
</c:forEach>
<c:param name="lang" value="en"/>
</c:url>">
                        English
                    </a>
                </li>
                <li>
                    <a href="<c:url value="${requestScope['javax.servlet.forward.request_uri.param']}">
                    <c:forEach items="${param}" var="entry">
                    <c:if test="${entry.key != 'lang'}">
                    <c:param name="${entry.key}" value="${entry.value}"/></c:if>
</c:forEach>
<c:param name="lang" value="ru"/>
</c:url>">
                        Русский
                    </a>
                </li>
            </ul>
        </li>
        <li><a style="color: green" href="#"><fmt:message key="menu.hello"/></a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/login"><fmt:message key="login.login"/></a></li>
                <li><a href="${pageContext.request.contextPath}/register"><fmt:message key="reg.reg"/></a></li>
            </ul>
        </li>
    </ul>
