$(document).on("click", "#comment-dislike-btn${comment.id}", function () {
    $.get("${pageContext.request.contextPath}/dislikeComment?commentId=${comment.id}", function (response) {
        if ('${sessionScope.user==null}' === 'true') {
            window.location.href =  '${pageContext.request.contextPath}/login';
            return window.jQuery;
        }
        $("#comment-dislikes${comment.id}").text(response.split(":")[0]);
        $("#comment-likes${comment.id}").text(response.split(":")[1]);
        if (response.split(":")[2]==="true"){
            $("#comment-dislike-btn${comment.id}").removeClass("comment-dislike-btn").addClass("comment-disliked-btn");
            $("#comment-like-btn${comment.id}").removeClass("comment-liked-btn").addClass("comment-like-btn")
        } else if (response.split(":")[3]==="false"){
            $("#comment-dislike-btn${comment.id}").removeClass("comment-disliked-btn").addClass("comment-dislike-btn")
        }
    });
});