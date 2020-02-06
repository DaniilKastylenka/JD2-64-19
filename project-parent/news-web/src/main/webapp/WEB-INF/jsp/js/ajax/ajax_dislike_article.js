$(document).on("click", "#article-dislike-btn", function () {
    $.get("${pageContext.request.contextPath}/dislikeArticle?articleId=${article.id}", function (response) {
        $("#article-dislikes").text(response.split(":")[0]);
        $("#article-likes").text(response.split(":")[1]);
        if (response.split(":")[2]==="true") {
            $("#article-dislike-btn").removeClass("article-dislike-btn").addClass("article-disliked-btn");
            $("#article-like-btn").removeClass("article-liked-btn").addClass("article-like-btn")
        } else if (response.split(":")[3]==="false"){
            $("#article-dislike-btn").removeClass("article-disliked-btn").addClass("article-dislike-btn");
        }
    });
});