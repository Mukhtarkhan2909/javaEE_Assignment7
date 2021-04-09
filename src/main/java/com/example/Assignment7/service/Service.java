package com.example.Assignment7.service;

import com.example.Assignment7.entity.Comment;
import com.example.Assignment7.entity.Post;
import com.example.Assignment7.entity.User;

import java.util.List;

public interface Service {
    User login(String username, String password);
    boolean addPost(Post post);
    boolean deletePost(int postId);
    boolean updatePost(Post post);
    User getPostOwner(int postId);
    User getCommentOwner(int commentId);
    List<Post> listAllPosts();
    List<Post> listAllPostsOfUser(int userId);
    boolean addComment(Comment comment);
    boolean deleteComment(int commentId);
    boolean updateComment(Comment comment);
    List<Comment> listAllComments();
    List<Comment> listAllCommentsOfPost(int postId);
}
