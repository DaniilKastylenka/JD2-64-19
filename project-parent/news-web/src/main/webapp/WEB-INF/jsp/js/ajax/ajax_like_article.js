$(document).on("click", ".article-like-btn", function () {
    $.get("${pageContext.request.contextPath}/likeArticle?articleId=${article.id}", function (response) {
        $("#article-likes").text(response.split(":")[0]);
        $("#article-dislikes").text(response.split(":")[1])
    });
});