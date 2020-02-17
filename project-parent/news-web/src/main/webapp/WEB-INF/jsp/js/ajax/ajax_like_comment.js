$(document).on("click", "#comment-like-btn${comment.id}", function () {
    $.get("${pageContext.request.contextPath}/likeComment?commentId=${comment.id}", function (response) {
        if ('${sessionScope.user==null}' === 'true') {
            window.location.href =  '${pageContext.request.contextPath}/login';
            return window.jQuery;
        }
        $("#comment-likes${comment.id}").text(response.split(":")[0]);
        $("#comment-dislikes${comment.id}").text(response.split(":")[1]);
        if (response.split(":")[2]==="true"){
            $("#comment-like-btn${comment.id}").removeClass("comment-like-btn").addClass("comment-liked-btn");
            $("#comment-dislike-btn${comment.id}").removeClass("comment-disliked-btn").addClass("comment-dislike-btn")
        } else if (response.split(":")[3]==="false"){
            $("#comment-like-btn${comment.id}").removeClass("comment-liked-btn").addClass("comment-like-btn")
        }
    });
});