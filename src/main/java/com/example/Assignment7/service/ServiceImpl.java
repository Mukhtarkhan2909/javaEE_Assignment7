package com.example.Assignment7.service;

import com.example.Assignment7.database.DBConnection;
import com.example.Assignment7.entity.Comment;
import com.example.Assignment7.entity.Post;
import com.example.Assignment7.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements Service {

    @Override
    public User login(String username, String password) {
        User user = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("select * from users where username = ? and password = ?;");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setUsername(rs.getString(3));
                u.setPassword(rs.getString(6));
                u.setEmail(rs.getString(5));
                u.setPhone(rs.getString(7));
                user = u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    @Override
    public User getPostOwner(int postId) {
        User user = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("select users.* " +
                    "from posts inner join users on users.id = posts.user_id " +
                    "where posts.id = ?;");
            pstmt.setInt(1, postId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setUsername(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setPhone(rs.getString(6));
                user = u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    @Override
    public User getCommentOwner(int commentId) {
        User user = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("select users.* " +
                    "from comments inner join users on users.id = comments.user_id " +
                    "where comments.id = ?;");
            pstmt.setInt(1, commentId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setUsername(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setPhone(rs.getString(6));
                user = u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    @Override
    public boolean addPost(Post post) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean state = false;
        int count = 0;
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("insert into posts values (?,?,?,?,?,?,?);");
            pstmt.setInt(1, post.getId());
            pstmt.setInt(2, post.getUserId());
            pstmt.setString(3, post.getTitle());
            pstmt.setString(4, post.getContent());
            pstmt.setDate(5, (Date) post.getPublicationDate());
            pstmt.setInt(6, post.getLikes());
            pstmt.setInt(7, post.getDislikes());
            count = pstmt.executeUpdate();
            if (count >= 0) {
                state = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
        }
        return state;
    }

    @Override
    public boolean deletePost(int postId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean state = false;
        int count = 0;
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("delete from posts where post_id = ?;");
            pstmt.setInt(1, postId);
            count = pstmt.executeUpdate();
            if (count >= 0) {
                state = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
        }
        return state;
    }

    @Override
    public boolean updatePost(Post post) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean state = false;
        int result = 0;
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("update posts set user_id = ?, title = ?, " +
                    "content = ?, publication_date = ?, likes = ?, " +
                    "dislikes = ? where post_id = ?;");
            pstmt.setInt(1, post.getUserId());
            pstmt.setString(2, post.getTitle());
            pstmt.setString(3, post.getContent());
            pstmt.setDate(4, (Date) post.getPublicationDate());
            pstmt.setInt(5, post.getLikes());
            pstmt.setInt(6, post.getDislikes());
            pstmt.setInt(7, post.getId());
            result = pstmt.executeUpdate();
            if (result >= 0) {
                state = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
        }
        return state;
    }

    @Override
    public List<Post> listAllPosts() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean state = false;
        List<Post> list = new ArrayList<>();
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("select * from posts;");
            state = pstmt.execute();
            if (state) {
                rs = pstmt.getResultSet();
                while (rs.next()) {
                    Post post = new Post();
                    post.setId(rs.getInt(1));
                    post.setUserId(rs.getInt(2));
                    post.setTitle(rs.getString(3));
                    post.setContent(rs.getString(4));
                    post.setPublicationDate(rs.getDate(5));
                    post.setLikes(rs.getInt(6));
                    post.setDislikes(rs.getInt(7));
                    list.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
        }
        return list;
    }

    @Override
    public List<Post> listAllPostsOfUser(int userId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean state = false;
        List<Post> list = new ArrayList<>();
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("select * from posts where user_id = ?;");
            pstmt.setInt(1, userId);
            state = pstmt.execute();
            if (state) {
                rs = pstmt.getResultSet();
                while (rs.next()) {
                    Post post = new Post();
                    post.setId(rs.getInt(1));
                    post.setUserId(rs.getInt(2));
                    post.setTitle(rs.getString(3));
                    post.setContent(rs.getString(4));
                    post.setPublicationDate(rs.getDate(5));
                    post.setLikes(rs.getInt(6));
                    post.setDislikes(rs.getInt(7));
                    list.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
        }
        return list;
    }

    @Override
    public boolean addComment(Comment comment) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean state = false;
        int count = 0;
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("insert into comments values (?,?,?,?,?);");
            pstmt.setInt(1, comment.getId());
            pstmt.setInt(2, comment.getUserId());
            pstmt.setInt(3, comment.getPostId());
            pstmt.setString(4, comment.getContent());
            pstmt.setDate(5, (Date) comment.getPublicationDate());
            count = pstmt.executeUpdate();
            if (count >= 0) {
                state = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
        }
        return state;
    }

    @Override
    public boolean deleteComment(int commentId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean state = false;
        int count = 0;
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("delete from comments where comment_id = ?;");
            pstmt.setInt(1, commentId);
            count = pstmt.executeUpdate();
            if (count >= 0) {
                state = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
        }
        return state;
    }

    @Override
    public boolean updateComment(Comment comment) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean state = false;
        int result = 0;
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("update comments set user_id = ?, post_id = ?, " +
                    "content = ?, publication_date = ? where comment_id = ?;");
            pstmt.setInt(1, comment.getUserId());
            pstmt.setInt(2, comment.getPostId());
            pstmt.setString(3, comment.getContent());
            pstmt.setDate(4, (Date) comment.getPublicationDate());
            pstmt.setInt(5, comment.getId());
            result = pstmt.executeUpdate();
            if (result >= 0) {
                state = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
        }
        return state;
    }

    @Override
    public List<Comment> listAllComments() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean state = false;
        List<Comment> list = new ArrayList<>();
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("select * from comments;");
            state = pstmt.execute();
            if (state) {
                rs = pstmt.getResultSet();
                while (rs.next()) {
                    Comment comment = new Comment();
                    comment.setId(rs.getInt(1));
                    comment.setUserId(rs.getInt(2));
                    comment.setPostId(rs.getInt(3));
                    comment.setContent(rs.getString(4));
                    comment.setPublicationDate(rs.getDate(5));
                    list.add(comment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
        }
        return list;
    }

    @Override
    public List<Comment> listAllCommentsOfPost(int postId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean state = false;
        List<Comment> list = new ArrayList<>();
        try {
            Class.forName(DBConnection.DRIVERCLASS);
            con = DriverManager.getConnection(DBConnection.DBURL, DBConnection.USER, DBConnection.PASSWORD);
            pstmt = con.prepareStatement("select * from comments where post_id = ?;");
            pstmt.setInt(1, postId);
            state = pstmt.execute();
            if (state) {
                rs = pstmt.getResultSet();
                while (rs.next()) {
                    Comment comment = new Comment();
                    comment.setId(rs.getInt(1));
                    comment.setUserId(rs.getInt(2));
                    comment.setPostId(rs.getInt(3));
                    comment.setContent(rs.getString(4));
                    comment.setPublicationDate(rs.getDate(5));
                    list.add(comment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionClose(con, pstmt);
        }
        return list;
    }

    private void connectionClose(Connection con, PreparedStatement pstmt) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
