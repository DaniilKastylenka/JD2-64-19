<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    <%@include file="menu_style/style.css" %>
</style>
<fmt:setLocale scope="session" value="${param.lang}"/>
<fmt:setBundle basename="messages"/>
    <nav role='navigation'>
        <ul>
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li>
                <a href="#">Articles</a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/articleList">All articles</a></li>
                    <li><a href="${pageContext.request.contextPath}/myArticles">My articles</a></li>
                    <li><a href="${pageContext.request.contextPath}/createArticle">Create article</a></li>
                </ul>

            </li>
            <li><a href="#">Localisation</a>
                <ul>
                    <li><a href="?lang=en">English</a></li>
                    <li><a href="?lang=ru">Russian</a></li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/logout" style="color: #ff1d00">Logout</a></li>
        </ul>
    </nav>


