package me.fernanbae.model;

public class Comment {

    private final int commentId;
    private final int postId;
    private final String body;

    public Comment(int commentId, int postId, String body, User user) {
        this.commentId = commentId;
        this.postId = postId;
        this.body = body;
        this.user = user;
    }

    private final User user;

    public int getCommentId() {
        return commentId;
    }

    public int getPostId() {
        return postId;
    }

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }
}
