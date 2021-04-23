<%@ include file="header.jsp" %>

<jsp:useBean id="post" class="com.example.Assignment7.entity.Post" scope="page" />
<jsp:setProperty name="post" property="*"/>
<div class="container">
    <br><br><br><br>
    <form name="beanTest">
        <div class="form-group">
            <label for="exampleFormControlInput1">Email address</label>
            <input type="email" name="title" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
        </div>
        <br>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Example textarea</label>
            <textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
    </form>
    <br>
    <div class="container form-group mt-2">
        <a href="#" onclick="history.go(-1)"><button class="btn btn-dark">Back</button></a>
        <a href="#"><button class="btn btn-success">Add</button></a>
    </div>
</div>
<%@ include file="header.jsp" %>
