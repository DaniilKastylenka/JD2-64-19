$(document).on("click", ".article-like-btn", function () {
    $.get("${pageContext.request.contextPath}/likeArticle?articleId=${article.id}", function (responseText) {
        $("#article-likes").text(responseText)
    });
});