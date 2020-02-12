<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${pageContext.request.getParameter('page')>1}">
    <a class="page-btn"
       href="${pageContext.request.contextPath}/myArticlesBySection?page=${pageContext.request.getParameter("page")-1}&sectionId=${param.sectionId}">< </a>
</c:if>

<c:forEach items="${pages}" var="page">
    <a class="page-btn"
       <c:if test="${pageContext.request.getParameter('page')==page}">style="color: #007bff" </c:if>
       href="${pageContext.request.contextPath}/myArticlesBySection?page=${page}&sectionId=${param.sectionId}"> ${page} </a>
</c:forEach>

<c:if test="${pageContext.request.getParameter('page') < totalPages}">
    <a class="page-btn"
       href="${pageContext.request.contextPath}/myArticlesBySection?page=${pageContext.request.getParameter("page")+1}&sectionId=${param.sectionId}">></a>
</c:if>
