<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <ul>
        <c:if test="${sessionScope.user.role.name == 'author'}">
            <%@include file="menuForAuthor.jsp" %>
        </c:if>
        <c:if test="${sessionScope.user.role.name == 'admin'}">
            <%@include file="menuForAdmin.jsp" %>
        </c:if>
        <c:if test="${sessionScope.user.role.name == 'user'}">
            <%@include file="menuForUser.jsp" %>
        </c:if>
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
        <li><a href="#"
               style="color: green"><fmt:message key="menu.hello"/> ${sessionScope.user.username}</a>
            <ul>

                <li><a href="${pageContext.request.contextPath}/userPage"><fmt:message key="menu.account"/></a></li>

                <li><a href="${pageContext.request.contextPath}/logout" style="color: #ff1d00"><fmt:message
                        key="menu.logout"/></a></li>

            </ul>
        </li>
    </ul>


