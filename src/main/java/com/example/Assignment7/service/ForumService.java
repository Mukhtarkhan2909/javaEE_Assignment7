package com.example.Assignment7.service;

import com.example.Assignment7.entity.*;
import java.util.List;

public class ForumService {
    ServiceImpl service = new ServiceImpl();
    public User userLogin(String username, String password) {
        return service.login(username, password);
    }
    public boolean addPost(Post post) {
        return service.addPost(post);
    }
    public boolean deletePost(int postId) {
        return service.deletePost(postId);
    }
    public boolean updatePost(Post post) {
        return service.updatePost(post);
    }
    public User getPostOwner(int postId) {
        return service.getPostOwner(postId);
    }
    public User getCommentOwner(int commentId) {
        return service.getCommentOwner(commentId);
    }
    public List<Post> getPosts() {
        return service.listAllPosts();
    }
    public List<Post> getUserPosts(int userId) {
        return service.listAllPostsOfUser(userId);
    }
    public boolean addComment(Comment comment) {
        return service.addComment(comment);
    }
    public boolean deleteComment(int commentId) {
        return service.deleteComment(commentId);
    }
    public boolean updateComment(Comment comment) {
        return service.updateComment(comment);
    }
    public List<Comment> getComments() {
        return service.listAllComments();
    }
    public List<Comment> getPostComments(int postId) {
        return service.listAllCommentsOfPost(postId);
    }
}
