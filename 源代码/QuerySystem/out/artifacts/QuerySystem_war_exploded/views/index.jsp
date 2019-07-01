<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/4/2
  Time: 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if(request.getSession().getAttribute("aId") == null) {
  response.sendRedirect("login");
}%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>查询系统 - 首页</title>
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
                  <h4 class="username">${admin.username}</h4>
                  <div class="btn-group margin-bottom-2x" style="margin-top: 10px" role="group">
                    <button id="c_password" type="button" class="btn btn-default" data-toggle="modal" data-target="#passwordModal"><i class="fa fa-pencil"></i>修改密码
                    </button>
                    <button id="a_logout" type="button" class="btn btn-default"><i class="fa fa-sign-out"></i>退出
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
              <div class="title">保险单管理系统</div>
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
            <li class="panel panel-default dropdown">
              <a data-toggle="collapse" href="#dropdown-element">
                <span class="icon fa fa-desktop"></span><span class="title">员工管理</span>
              </a>
              <div id="dropdown-element" class="panel-collapse collapse">
                <div class="panel-body">
                  <ul class="nav navbar-nav">
                    <li><a href="allclerks">销售员查询</a>
                    </li>
                    <li><a href="newclerk">新增销售员</a>
                    </li>
                  </ul>
                </div>
              </div>
            </li>
            <li class="panel panel-default dropdown">
              <a data-toggle="collapse" href="#dropdown-table">
                <span class="icon fa fa-table"></span><span class="title">保单查询</span>
              </a>
              <div id="dropdown-table" class="panel-collapse collapse">
                <div class="panel-body">
                  <ul class="nav navbar-nav">
                    <li><a href="allbills">全部保单</a>
                    </li>
                  </ul>
                </div>
              </div>
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
                    <div class="title">${clerkNum}</div>
                    <div class="sub-title">销售人员</div>
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
                    <div class="title">${saleNum}</div>
                    <div class="sub-title">销售总量</div>
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
          <div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
            <canvas id="chart2" class="chart" style="margin-top: 30px"></canvas>
          </div>
        </div>
      </div>
  </div>
  <footer class="footer navbar-fixed-bottom " style="background: cadetblue">
    <div class="modal-footer" style="text-align: center; padding: 10px" >
      <span><small style="color: white">2018 @Copyright.</small></span>
    </div>
  </footer>
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
            <div class="layui-input-block">
              <input id="old-password" type="password" name="old-password" lay-verify="pass" placeholder="请输入原密码" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" for="new-password">新密码</label>
            <div class="layui-input-block">
              <input id="new-password" type="password" name="new-password" lay-verify="pass" placeholder="请输入新密码" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" for="re-password">重复密码</label>
            <div class="layui-input-block">
              <input id="re-password" type="password" name="re-password" lay-verify="pass" placeholder="请输入重复密码" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-input-block">
              <button id="ps_btn" class="layui-btn" lay-submit="" lay-filter="*">修改</button>
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
<script src="../views/js/bootstrap.min.js"></script>
<script src="../views/layui/lay/modules/layer.js"></script>
<script src="../views/layui/layui.js" charset="utf-8"></script>
<script src="../views/js/Chart.js"></script>
<script src="../views/js/main.js"></script>
<script>
  $(function () {
      var chart1 = new Chart($("#chart1"), {
          type: 'line',
          data: {
              labels: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
              datasets: [{
                  label: '销售保单数量',
//                data: [12, 19, 3, 5, 2, 3, 7],
                  data:${chart1},
                  backgroundColor: [
                      'rgba(54, 162, 235, 0.2)'
                  ],
                  borderColor: [
                      'rgba(79, 193, 193, 1)'
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
      var chart2 = new Chart($("#chart2"), {
          type: 'pie',
          data: {
              labels: ["意外险", "健康险", "补充医疗险", "分红险"],
              datasets: [{
                  data:${chart2},
                  backgroundColor: [
                      'rgba(255, 99, 132, 1)',
                      'rgba(54, 162, 235, 1)',
                      'rgba(255, 206, 86, 1)',
                      'rgba(75, 192, 192, 1)'
                  ],
                  borderColor: [
                      'rgba(255,99,132,1)',
                      'rgba(54, 162, 235, 1)',
                      'rgba(255, 206, 86, 1)',
                      'rgba(75, 192, 192, 1)'
                  ],
                  borderWidth: 1
              }]
          },
          options: {
              title: {
                  display: true,
                  text: '险种销售统计'
              },
              cutoutPercentage:50
          }
      });
  });
</script>
</body>
</html>
