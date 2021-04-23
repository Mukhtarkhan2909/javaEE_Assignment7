<%@ page import="com.example.Assignment7.service.ForumService" %>
<%@ page import="com.example.Assignment7.entity.Post" %>
<%@ include file="header.jsp" %>

<div class="container">
    <%
        ForumService service = new ForumService();
        Post post = service.getPosts().get(1);
    %>
    <br><br><br><br>
    <form name="beanTest">
        <div class="form-group">
            <label for="exampleFormControlInput1">Email address</label>
            <input type="email" name="title" class="form-control" id="exampleFormControlInput1" value="<%=post.getTitle()%>">
        </div>
        <br>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Example textarea</label>
            <textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="3"><%=post.getContent()%></textarea>
        </div>
    </form>
    <br>
    <div class="container form-group mt-2">
        <a href="#" onclick="history.go(-1)"><button class="btn btn-dark">Back</button></a>
        <a href="#"><button class="btn btn-success">Update</button></a>
    </div>
</div>
<%@ include file="header.jsp" %>
