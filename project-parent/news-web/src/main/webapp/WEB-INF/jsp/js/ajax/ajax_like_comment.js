$(document).on("click", "#comment-btn${comment.id}", function () {
    $.get("${pageContext.request.contextPath}/likeComment?commentId=${comment.id}", function (responseText) {
        $("#comment-likes${comment.id}").text(responseText)
    });
});