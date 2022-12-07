<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2022/12/7
  Time: 21:33
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
        <a id="selectrepair" href="javascript:void(0);">维修记录</a>
    </div>
    <div class="repair">
        <a id="torepaire" href="javascript:void(0);">我要接单</a>
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
    var username = `${user.account}`;
    $("#selectrepair").click(function (){
        $.ajax({
            url:"/worker/myRepair",
            data:{
                Account:username
            },
            type:"POST",
            success:function (result){
                $("#table1").empty();
                var repair_table =  $("<table border='0' align='center' style='border-spacing: 20px;color: white;text-align: center;background-color: gray'></table>");
                var repair_table_head = $("<tr style='background-color: gray;color:white;height: 70px;border-radius: 25px'><td>楼栋</td><td>房间</td><td>状态</td><td>报修学生</td><td>维修物品</td><td>操作</td></tr>");
                repair_table_head.appendTo(repair_table);
                $.each(result,function (index,item){
                    var state1 = item.state;
                    var neo;
                    if(state1 === 0){
                        neo = "未接单";
                    }else if(state1 === 1){
                        neo = "未完成";
                    }else if(state1 ===2){
                        neo = "已完成";
                    }
                    var finishbtn = $("<button style='background-color: greenyellow;border-radius: 5px;height: 35px;width: 80px;margin-left: 0px'>完成订单</button>");
                    var abandonbtn = $("<button style='background-color: red;border-radius: 5px;height: 35px;width: 80px;margin-left: 0px'>放弃订单</button>");
                    finishbtn.attr("onclick","finish("+item.id+")");
                    abandonbtn.attr("onclick","abandon("+item.id+")");
                    var IdTd = $("<td></td>").append(item.building);
                    var roomTd = $("<td></td>").append(item.room);
                    if(neo === "未完成"){
                        var stateTd = $("<td style='color: yellow'></td>").append(neo);
                    }else{
                        var stateTd = $("<td style='color: greenyellow'></td>").append(neo);
                    }

                    var studentTd = $("<td></td>").append(item.student.name);
                    var goodTd = $("<td></td>").append(item.goods);
                    var btnId = $("<td style='width: 190px;text-align: left'></td>").append(finishbtn).append(abandonbtn);
                    $("<tr style='font-size: 20px'></tr>").append(IdTd).append(roomTd).append(stateTd).append(studentTd).append(goodTd).append(btnId).appendTo(repair_table);
                    repair_table.appendTo($("#table1"))
                })
            }
        })
    })
    function finish(id){
        $.ajax({
            url:"/worker/finishRepair",
            type: "POST",
            data: {
                repairId:id,
            },
            success:function (){
                $.ajax({
                    url:"/worker/myRepair",
                    data:{
                        Account:username
                    },
                    type:"POST",
                    success:function (result){
                        $("#table1").empty();
                        var repair_table =  $("<table border='0' align='center' style='border-spacing: 20px;color: white;text-align: center;background-color: gray'></table>");
                        var repair_table_head = $("<tr style='background-color: gray;color:white;height: 70px;border-radius: 25px'><td>楼栋</td><td>房间</td><td>状态</td><td>报修学生</td><td>维修物品</td><td>操作</td></tr>");
                        repair_table_head.appendTo(repair_table);
                        $.each(result,function (index,item){
                            var state1 = item.state;
                            var neo;
                            if(state1 === 0){
                                neo = "未接单";
                            }else if(state1 === 1){
                                neo = "未完成";
                            }else if(state1 ===2){
                                neo = "已完成";
                            }
                            var finishbtn = $("<button style='background-color: greenyellow;border-radius: 5px;height: 35px;width: 80px;margin-left: 0px'>完成订单</button>");
                            var abandonbtn = $("<button style='background-color: red;border-radius: 5px;height: 35px;width: 80px;margin-left: 0px'>放弃订单</button>");
                            finishbtn.attr("onclick","finish("+item.id+")");
                            abandonbtn.attr("onclick","abandon("+item.id+")");
                            var IdTd = $("<td></td>").append(item.building);
                            var roomTd = $("<td></td>").append(item.room);
                            if(neo === "未完成"){
                                var stateTd = $("<td style='color: yellow'></td>").append(neo);
                            }else{
                                var stateTd = $("<td style='color: greenyellow'></td>").append(neo);
                            }

                            var studentTd = $("<td></td>").append(item.student.name);
                            var goodTd = $("<td></td>").append(item.goods);
                            var btnId = $("<td style='width: 190px;text-align: left'></td>").append(finishbtn).append(abandonbtn);
                            $("<tr style='font-size: 20px'></tr>").append(IdTd).append(roomTd).append(stateTd).append(studentTd).append(goodTd).append(btnId).appendTo(repair_table);
                            repair_table.appendTo($("#table1"))
                        })
                    }
                })
            }
        })
    }
    function abandon(id){
        console.log(id);
    }
</script>
