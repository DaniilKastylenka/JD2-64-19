$(document).on("click", "#article-like-btn", function () {
    $.get("${pageContext.request.contextPath}/likeArticle?articleId=${article.id}", function (response) {
        $("#article-likes").text(response.split(":")[0]);
        $("#article-dislikes").text(response.split(":")[1]);
        if (response.split(":")[2]==="true") {
            $("#article-like-btn").removeClass("article-like-btn").addClass("article-liked-btn");
            $("#article-dislike-btn").removeClass("article-disliked-btn").addClass("article-dislike-btn")
        } else if (response.split(":")[3]==="false"){
            $("#article-like-btn").removeClass("article-liked-btn").addClass("article-like-btn");
        }
    });
});

