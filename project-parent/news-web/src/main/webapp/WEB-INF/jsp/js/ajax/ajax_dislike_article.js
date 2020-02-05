$(document).on("click", ".article-dislike-btn", function () {
    $.get("${pageContext.request.contextPath}/dislikeArticle?articleId=${article.id}", function (response) {
        $("#article-dislikes").text(response.split(":")[0]);
        $("#article-likes").text(response.split(":")[1]);

    });
});