<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<li>
    <a href="#"><fmt:message key="menu.users"/></a>
    <ul>
        <li><a href="${pageContext.request.contextPath}/userList"><fmt:message key="menu.all.users"/></a></li>
        <li><a href="${pageContext.request.contextPath}/createAuthorAccount"><fmt:message key="menu.add.new.author"/></a></li>
    </ul>
</li>
<li>
    <a href="#"><fmt:message key="menu.articles"/> </a>
    <ul>
        <li><a href="${pageContext.request.contextPath}/articleList?page=1"><fmt:message key="menu.all.articles"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/myArticles?page=1"><fmt:message key="menu.my.articles"/></a></li>
        <li><a href="${pageContext.request.contextPath}/createArticle"><fmt:message
                key="menu.create.article"/></a></li>
    </ul>
</li>
