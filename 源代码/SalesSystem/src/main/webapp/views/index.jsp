<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/4/2
  Time: 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if(request.getSession().getAttribute("cId") == null) {
  response.sendRedirect("login");
}%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>销售系统 - 首页</title>
  <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0"/>
  <!-- CSS-Libs -->
  <link rel="stylesheet" href="../views/css/bootstrap.min.css">
  <link rel="stylesheet" href="../views/css/font-awesome.min.css">
  <link rel="stylesheet" href="../views/css/animate.min.css">
  <link rel="stylesheet" href="../views/layui/css/modules/layer/default/layer.css">
  <link rel="stylesheet" href="../views/layui/css/layui.css"  media="all">
  <link rel="stylesheet" href="../views/css/style.css">

</head>
<body>
<div class="app-container">
  <div class="row content-container">
    <nav class="navbar navbar-default navbar-fixed-top navbar-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-expand-toggle">
            <i class="fa fa-bars icon"></i>
          </button>
          <ol class="breadcrumb navbar-breadcrumb">
            <li class="active">总览</li>
          </ol>
          <button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
            <i class="fa fa-th icon"></i>
          </button>
        </div>
        <ul class="nav navbar-nav navbar-right">
          <button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
            <i class="fa fa-times icon"></i>
          </button>
          <li class="dropdown profile">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">我的信息<span
                    class="caret"></span></a>
            <ul class="dropdown-menu animated fadeInDown">
              <li class="profile-img img-circle">
                <img src="../views/images/head.png" class="profile-img">
              </li>
              <li>
                <div class="profile-info">
                  <h4 class="username">${clerk.clerkId}</h4>
                  <div class="btn-group margin-bottom-2x" style="margin-top: 10px" role="group">
                    <button id="c_info" type="button" class="btn btn-default" data-toggle="modal" data-target="#clerkModal"><i class="fa fa-user"></i>编辑
                    </button>
                    <button id="c_password" type="button" class="btn btn-default" data-toggle="modal" data-target="#passwordModal"><i class="fa fa-pencil"></i>修改密码
                    </button>
                    <button id="c_logout" type="button" class="btn btn-default"><i class="fa fa-sign-out"></i>退出
                    </button>
                  </div>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
    <div class="side-menu sidebar-inverse">
      <nav class="navbar navbar-default" role="navigation">
        <div class="side-menu-container">
          <div class="navbar-header">
            <a class="navbar-brand" href="/index">
              <div class="icon fa fa-shield"></div>
              <div class="title">保险单销售系统</div>
            </a>
            <button type="button" class="navbar-expand-toggle pull-right visible-xs">
              <i class="fa fa-times icon"></i>
            </button>
          </div>
          <ul class="nav navbar-nav">
            <li class="active">
              <a href="/index">
                <span class="icon fa fa-tachometer"></span><span class="title">总览</span>
              </a>
            </li>
            <li>
              <a href="newbill">
                <span class="icon fa fa-desktop"></span><span class="title">新增保单</span>
              </a>
            </li>
            <li>
              <a href="mybills">
                <span class="icon fa fa-table"></span><span class="title">销售记录</span>
              </a>
            </li>
          </ul>
        </div>
      </nav>
    </div>
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6">
          <a href="#">
            <div class="green summary-inline">
              <div class="card-body">
                <i class="icon fa fa-user fa-4x"></i>
                <div class="content">
                  <div class="title">${todayNum}</div>
                  <div class="sub-title">今日出单</div>
                </div>
                <div class="clear-both"></div>
              </div>
            </div>
          </a>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6">
          <a href="#">
            <div class="blue summary-inline">
              <div class="card-body">
                <i class="icon fa fa-tags fa-4x"></i>
                <div class="content">
                  <div class="title">${allNum}</div>
                  <div class="sub-title">出单总量</div>
                </div>
                <div class="clear-both"></div>
              </div>
            </div>
          </a>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-offset-1 col-lg-5 col-md-offset-1 col-md-5 col-sm-offset-1 col-sm-5 col-xs-12">
          <canvas id="chart1" class="chart" style="margin-top: 30px"></canvas>
        </div>
        <%--<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">--%>
          <%--<canvas id="chart2" class="chart" style="margin-top: 30px"></canvas>--%>
        <%--</div>--%>
      </div>
    </div>
  </div>
  <footer class="footer navbar-fixed-bottom " style="background: cadetblue">
    <div class="modal-footer" style="text-align: center; padding: 10px" >
      <span><small style="color: white">2018 @Copyright.</small></span>
    </div>
  </footer>
</div>
<!-- clerk-info-Modal -->
<div class="modal fade" id="clerkModal" tabindex="-1" role="dialog" aria-labelledby="clerkModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <fieldset class="layui-elem-field layui-field-title" data-dismiss="modal" aria-label="Close" style="margin-top: 10px;">
          <legend>业务员信息</legend>
        </fieldset>
        <form class="layui-form layui-form-pane" action="">
          <div class="layui-form-item">
            <label class="layui-form-label">业务员ID</label>
            <div class="layui-input-inline">
              <input id="c_Id" readonly="" type="text" name="c_Id" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" for="c_name">昵称</label>
            <div class="layui-input-inline">
              <input id="c_name" type="text" name="c_name" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
              <input type="radio" name="c_sex" value="男" title="男" checked="">
              <input type="radio" name="c_sex" value="女" title="女">
              <input type="radio" name="c_sex" value="未知" title="未知">
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label" for="c_birthday">生日</label>
              <div class="layui-input-block">
                <input id="c_birthday" type="text" name="c_birthday" autocomplete="off" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label" for="c_mobile">手机号码</label>
              <div class="layui-input-block">
                <input id="c_mobile" type="tel" name="c_mobile" lay-verify="phone" autocomplete="off" class="layui-input">
              </div>
            </div>
          </div>
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label" for="c_note">个人说明</label>
            <div class="layui-input-block">
              <textarea id="c_note" name="c_note" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-input-block">
              <button id="c_put" class="layui-btn" lay-submit="" lay-filter="*">修改</button>
              <button data-dismiss="modal" class="layui-btn layui-btn-primary">取消</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- change-password-Modal-->
<div class="modal fade" id="passwordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <fieldset class="layui-elem-field layui-field-title" data-dismiss="modal" aria-label="Close" style="margin-top: 10px;">
          <legend>修改密码</legend>
        </fieldset>
        <form class="layui-form layui-form-pane" action="">
          <div class="layui-form-item">
            <label class="layui-form-label" for="old-password">原密码</label>
            <div class="layui-input-inline">
              <input id="old-password" type="password" name="old-password" lay-verify="pass" placeholder="请输入原密码" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" for="new-password">新密码</label>
            <div class="layui-input-inline">
              <input id="new-password" type="password" name="new-password" lay-verify="pass" placeholder="请输入新密码" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" for="re-password">重复密码</label>
            <div class="layui-input-inline">
              <input id="re-password" type="password" name="re-password" lay-verify="pass" placeholder="请输入重复密码" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-input-block">
              <button id="mps_btn" class="layui-btn" lay-submit="" lay-filter="*">修改</button>
              <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>


<!-- JS-Libs  -->
<script src="../views/js/jquery-3.3.1.min.js"></script>
<script src="../views/layui/lay/modules/layer.js"></script>
<script src="../views/layui/layui.js" charset="utf-8"></script>
<script src="../views/js/bootstrap.min.js"></script>
<script src="../views/js/Chart.js"></script>
<script src="../views/js/main.js"></script>
<script>
    $(function () {
        var chart1 = new Chart($("#chart1"), {
            type: 'line',
            data: {
                labels: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
                datasets: [{
                    label: '本人销售保单数量',
                data: ${weekNum},
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                },
                title: {
                    display: true,
                    text: '本周销售统计'
                }
            }
        });
    });
</script>
</body>
</html>
