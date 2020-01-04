<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<li>
    <a href="#">Users</a>
    <ul>
        <li><a href="${pageContext.request.contextPath}/userList">All users</a></li>
        <li><a href="${pageContext.request.contextPath}/createAuthorAccount">Create new author</a></li>
    </ul>
</li>
<li>
    <a href="#"><fmt:message key="menu.articles"/> </a>
    <ul>
        <li><a href="${pageContext.request.contextPath}/articleList"><fmt:message key="menu.all.articles"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/myArticles"><fmt:message key="menu.my.articles"/></a></li>
        <li><a href="${pageContext.request.contextPath}/createArticle"><fmt:message
                key="menu.create.article"/></a></li>
    </ul>

</li>
