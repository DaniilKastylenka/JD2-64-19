<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${pageContext.request.getParameter('page')>1}">
    <a class="page-btn"
       href="${pageContext.request.contextPath}/myArticles?page=${pageContext.request.getParameter("page")-1}">< </a>
</c:if>

<c:forEach items="${pages}" var="page">
    <a class="page-btn"
       <c:if test="${pageContext.request.getParameter('page')==page}">style="color: #007bff" </c:if>
       href="${pageContext.request.contextPath}/myArticles?page=${page}"> ${page} </a>
</c:forEach>

<c:if test="${pageContext.request.getParameter('page') < totalPages}">
    <a class="page-btn"
       href="${pageContext.request.contextPath}/myArticles?page=${pageContext.request.getParameter("page")+1}">></a>
</c:if>
