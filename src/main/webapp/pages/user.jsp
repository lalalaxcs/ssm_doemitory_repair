<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2022/11/30
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../jquery-3.4.1.js"></script>
    <style type="text/css">
        boody{
            margin: 0;
            padding: 0;
        }
        .top .repair{
            margin: 25px 50px;
        }
        .top .myrepair{
            margin: 25px 50px;
        }
        a{
            text-decoration-line: none;

        }
        a:link{
            color: white;
        }
        a:visited{
            color: white;
        }
        a:hover{
            color: skyblue;
        }
        table{
            font-size: 30px;
        }
        #table1{
            margin-top: 50px;
        }
        #content{
            padding: 1px;
        }
    </style>
</head>
<body>
    <div class="top" style="width: 100%;height: 10%;background-color: gray;display: flex">
        <div class="myrepair">
            <a id="selectrepair" href="javascript:void(0);">报修记录</a>
        </div>
        <div class="repair">
            <a id="torepaire" href="javascript:void(0);">我要报修</a>
        </div>
        <div class="user" style="color: white;margin-top: 25px;margin-left: 1000px">
            用户:${user.account}
        </div>
    </div>
    <div id="content" style="width: 100%;height: 90%;background-color: papayawhip">
        <div id="table1">
            <div style="text-align: center;display: block;font-size: 40px;line-height: 600px">
                宿舍报修系统
            </div>
        </div>
    </div>
</body>
</html>
<script>
    var myrepair = document.getElementById("selectrepair");
    var content = document.getElementById("content");
    var username = `${user.account}`;
    $("#selectrepair").click(function (){
        $.ajax({
            url:"/student/myrepair",
            type:"POST",
            data:{
                "username":username
            },
            success:function (result){
                $("#table1").empty();
                var repair_table =  $("<table border='0' align='center' style='border-spacing: 10px;color: white;text-align: center;background-color: gray'></table>");
                var repair_table_head = $("<tr style='background-color: gray;color:white;height: 70px;border-radius: 25px'><td>楼栋</td><td>房间</td><td>状态</td><td>维修工人姓名</td><td>维修项目</td></tr>");
                repair_table_head.appendTo(repair_table);
                $.each(result,function (index,item){
                    var state1 = item.state;
                    var neo;
                    if(state1 === 0){
                        neo = "未接单";
                    }else if(state1 === 1){
                        neo = "已接单";
                    }else if(state1 ===2){
                        neo = "已完成";
                    }
                    var IdTd = $("<td></td>").append(item.building);
                    var roomTd = $("<td></td>").append(item.room);
                    if(neo === "未接单"){
                        var stateTd = $("<td style='color: red'></td>").append(neo);
                    }else if(neo === "已接单"){
                        var stateTd = $("<td style='color: white'></td>").append(neo);
                    }else {
                        var stateTd = $("<td style='color: greenyellow'></td>").append(neo);
                    }
                    if(item.worker === null){
                        var workerTd = $("<td></td>").append("暂无接单人员");
                    }else{
                        var workerTd = $("<td></td>").append(item.worker.name);
                    }
                    var goodTd = $("<td></td>").append(item.goods);
                    $("<tr style='font-size: 20px'></tr>").append(IdTd).append(roomTd).append(stateTd).append(workerTd).append(goodTd).appendTo(repair_table);
                    repair_table.appendTo($("#table1"))
                })
            }
        })
    })
    $("#torepaire").click(function (){
        $("#table1").empty();
        $("#table1").html("楼栋号: " +"<input type='text' name='buliding' id='buliding'><br>"+
            "房间号: "+"<input type='text' name='room' id='room'><br>"+
            "维修物品: "+"<input type='text' name='goods' id='goods'><br>"+
            "<button id='addRepair' style='width: 70px;height: 30px'>提交</button>")
        $("#table1").attr("style","text-align:center");
        $("#addRepair").attr("onclick","Add()");
    })
    function Add(){
        console.log($("#buliding").val());
        console.log($("#room").val());
        console.log($("#goods").val());
        var buliding = $("#buliding").val();
        var room = $("#room").val();
        var goods = $("#goods").val();
        var obj = {};
        obj.building = buliding;
        obj.room  = room;
        obj.goods = goods;
        $.ajax({
            url:'/student/addRepair/'+username,
            type: "POST",
            contentType:"application/json;charset=utf-8",
            dataType:"JSON",
            data:JSON.stringify(obj),
            success:function (result){
                console.log(result);
                if(result.rows === 1){
                    alert("添加成功");
                }else{
                    alert("添加失败");
                }
            },
        })
    }
</script>
