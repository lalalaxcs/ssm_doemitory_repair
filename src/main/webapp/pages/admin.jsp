<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2022/11/30
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../jquery-3.4.1.js"></script>
    <style type="text/css">
        body{
            margin: 0;
            padding: 0;
        }
        .all{
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: column;
        }
        .top{
            text-align: center;
            height: 10%;
            background-color: antiquewhite;
        }
        .mid{
            height: 90%;
            display: flex;
            flex-direction: row;
        }
        .left-tag{
            max-width: 154px;
            display: flex;
            flex-direction: column;
            flex-grow: 1;
            background-color: gray;
        }
        #content{
            /*text-align: center;*/
            flex-grow: 9;
            background-color: ghostwhite;
        }
        .top span{
            font-size: 30px;
            line-height: 70px;
        }
        .left-tag a{
            margin-top: 50px;
            font-size: 30px;
            color: white;
            text-align: center;
            text-decoration-line: none;
        }
        .left-tag a:link{
            color: white;
        }
        .left-tag a:visited{
            color: white;
        }
        .left-tag a:hover{
            color: skyblue;
        }
        table{
            margin-top: 20px;
        }
        td{
            padding-left: 80px;
            padding-right: 80px;
        }
    </style>
</head>
<body>
    <div class="all">
        <div class="top">
            <span style="margin-right: 100px">宿舍报修管理系统后台</span><span>管理员: ${user.account}</span>
        </div>
        <div class="mid">
            <div class="left-tag">
                <a href="javascript:void(0)" id="selectUser">用户管理</a>
                <a href="javascript:void(0)" id="log">日志管理</a>
            </div>
            <div id="content">
            </div>
        </div>
    </div>
</body>
</html>
<script>
    var pagenow =  1;
    $("#test").click(function (){
        alert("hello")
    })
    $("#selectUser").click(function (){
        $.ajax({
            url:"/admin/selectAllUser",
            type:"POST",
            success:function (result){
                console.log(result);
                $("#content").empty();
                console.log($("#content"))
                var form = $("<form method='post' action='/admin/importUser' enctype='multipart/form-data' ></form>");
                form.append($("<input type='file' name='excelfiles' ><br>"));
                form.append($("<input type='submit' value='批量导入' id='submit1'>"));
                $("#submit1").attr("onclick","loading()");
                form.appendTo($("#content"))
                var user_table = $("<table  border='0' align='left' style='border-spacing: 0px;color: white;text-align: center;background-color: gray;margin-left: 20px'></table>");
                var user_table_head = $("<tr style='background-color: gray;color:white;height: 50px;border-radius: 25px;font-size: 12px'><td>用户id</td><td>用户账号</td><td>用户类型</td><td>操作</td></tr>");
                user_table_head.appendTo(user_table);
                $.each(result,function (index,item){
                    var deletebtn = $("<button style='background-color: red;border-radius: 5px;height: 35px;width: 80px;margin-left: 0px'>删除</button>");
                    var type = item.type;
                    if(item.type === 0){
                        type = "管理员";
                    }else if(item.type === 1){
                        type = "维修工人";
                    }else {
                        type = "学生";
                    }
                    var userIdTd =  $("<td></td>").append(item.id);
                    var userAcTd =  $("<td></td>").append(item.account);
                    var userTypeTd =  $("<td></td>").append(type);
                    deletebtn.attr("onclick","deleteUser(" + item.id+")");
                    var btnTd = $("<td></td>").append(deletebtn);
                    $("<tr style='font-size: 10px'></tr>").append(userIdTd).append(userAcTd).append(userTypeTd).append(btnTd).appendTo(user_table);
                    user_table.appendTo($("#content"));
                })

            }
        })
    })
    function deleteUser(id){
        $.ajax({
            url:"/admin/deleteUser",
            data:{
                id:id
            },
            type:"POST",
            success:function (result){
                console.log("reuslt="+result);
                if(result === 1){
                    alert("删除成功");
                    loading();
                }else{
                    alert("删除失败");
                    loading();
                }
            }
        })
    }
    function loading(){
        $.ajax({
            url:"/admin/selectAllUser",
            type:"POST",
            success:function (result){
                console.log(result);
                $("#content").empty();
                console.log($("#content"))
                var form = $("<form method='post' action='/admin/importUser' enctype='multipart/form-data' ></form>");
                form.append($("<input type='file' name='excelfiles' ><br>"));
                form.append($("<input type='submit' value='批量导入' id='submit1'>"));
                $("#submit1").attr("onclick","loading()");
                form.appendTo($("#content"))
                var user_table = $("<table  border='0' align='left' style='border-spacing: 0px;color: white;text-align: center;background-color: gray;margin-left: 20px'></table>");
                var user_table_head = $("<tr style='background-color: gray;color:white;height: 50px;border-radius: 25px;font-size: 12px'><td>用户id</td><td>用户账号</td><td>用户类型</td><td>操作</td></tr>");
                user_table_head.appendTo(user_table);
                $.each(result,function (index,item){
                    var deletebtn = $("<button style='background-color: red;border-radius: 5px;height: 35px;width: 80px;margin-left: 0px'>删除</button>");
                    var type = item.type;
                    if(item.type === 0){
                        type = "管理员";
                    }else if(item.type === 1){
                        type = "维修工人";
                    }else {
                        type = "学生";
                    }
                    var userIdTd =  $("<td></td>").append(item.id);
                    var userAcTd =  $("<td></td>").append(item.account);
                    var userTypeTd =  $("<td></td>").append(type);
                    deletebtn.attr("onclick","deleteUser(" + item.id+")");
                    var btnTd = $("<td></td>").append(deletebtn);
                    $("<tr style='font-size: 10px'></tr>").append(userIdTd).append(userAcTd).append(userTypeTd).append(btnTd).appendTo(user_table);
                    user_table.appendTo($("#content"));
                })

            }
        })
    }
    function log(pagenow){
        //alert(pagenow);
        $.ajax({
            url:"/admin/log",
            data:{
                pageNow:pagenow
            },
            type:"POST",
            success:function (result){
                console.log(result);
                this.pagenow = result.pageNum;
                $("#content").empty();
                console.log($("#content"))
                var log_table = $("<table  border='0' align='left' style='border-spacing: 0px;color: white;text-align: center;background-color: gray;margin-left: 20px'></table>");
                var log_table_head = $("<tr style='background-color: gray;color:white;height: 50px;border-radius: 25px;font-size: 12px'><td>ip</td><td>时间</td><td>模块名称</td><td>功能</td><td>类型</td><td>请求方法</td></tr>");
                log_table_head.appendTo(log_table);
                $.each(result.list,function (index,item){
                    //var deletebtn = $("<button style='background-color: red;border-radius: 5px;height: 35px;width: 80px;margin-left: 0px'>删除</button>");
                    var logIpTd =  $("<td></td>").append(item.logIp);
                    var logTimeTd =  $("<td></td>").append(item.logTime);
                    var logModuleNameTd =  $("<td></td>").append(item.logModuleName);
                    var logDescTd = $("<td></td>").append(item.logDesc);
                    var logTypeTd = $("<td></td>").append(item.logType);
                    var logMethodTd = $("<td></td>").append(item.logMethod);
                    //deletebtn.attr("onclick","deleteUser(" + item.id+")");
                    //var btnTd = $("<td></td>").append(deletebtn);
                    $("<tr style='font-size: 10px'></tr>").append(logIpTd).append(logTimeTd).append(logModuleNameTd).append(logDescTd).append(logTypeTd).append(logMethodTd).appendTo(log_table);
                    log_table.appendTo($("#content"));
                })
                var prebt = $("<button style='margin-left: 500px;margin-right: 30px'>上一页</button>");
                var nextbt = $("<button >下一页</button>");
                if(result.isFirstPage === true){
                    prebt.attr('disabled',true);
                }
                if(result.isLastPage === true){
                    nextbt.attr('disabled',true);
                }
                var prepage = this.pagenow-1;
                var nextpage = this.pagenow+1;
                prebt.attr("onclick","log("+prepage+")");
                nextbt.attr("onclick","log("+nextpage+")");
                prebt.appendTo($("#content"));
                nextbt.appendTo($("#content"));
            }
        })
    }
    $("#log").click(function (){
        log(pagenow);
    });
</script>
