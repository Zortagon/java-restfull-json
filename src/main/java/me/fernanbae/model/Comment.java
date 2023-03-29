package me.fernanbae.model;

public record Comment(int commentId, int postId, String body, User user) { }
