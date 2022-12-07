<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2022/11/29
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        body{
            margin: 0;
            padding: 0;
        }
        #photo{
            float: left;
            width: 900px;
            height: 500px;
            background-image: url("1.png");
            background-size: 100%,100%;
            background-repeat: no-repeat;
            /*background-color: #ff9511;*/
        }
    </style>
</head>
<body>
    <div style="display: flex;margin: 200px 300px auto;width: 70%">
        <div id="photo" ></div>
        <div class="login" style="width: 500px;height: 461px;background-color: skyblue">
            <p style="text-align: center">宿舍报修系统</p>
            <form action="/login" method="post" style="text-align: center">
                <input type="text" placeholder="请输入账号" name="accout" style="margin-top: 30px"><br>
                <input type="password" placeholder="请输入密码" name="password" style="margin-top: 30px"/><br>
                <input type="submit" value="登录" style="margin-top: 30px">
            </form>
        </div>
    </div>

</body>
</html>
