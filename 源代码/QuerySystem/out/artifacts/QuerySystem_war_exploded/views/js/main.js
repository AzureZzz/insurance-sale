$(function() {
    $(".navbar-expand-toggle").click(function() {
        $(".app-container").toggleClass("expanded");
        return $(".navbar-expand-toggle").toggleClass("fa-rotate-90");
    });
    return $(".navbar-right-expand-toggle").click(function() {
        $(".navbar-right").toggleClass("expanded");
        return $(".navbar-right-expand-toggle").toggleClass("fa-rotate-90");
    });
});

$(function() {
    return $(".side-menu .nav .dropdown").on('show.bs.collapse', function() {
        return $(".side-menu .nav .dropdown .collapse").collapse('hide');
    });
});

$(function () {
    $("#login").click(function () {
        var data={
            "adminId":$("#adminId").val(),
            "password":$("#password").val()
        };
        var url="http://localhost:8080/login";
        $.ajax({
            url: url,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType:'json',
            success: function(data){
                if(data.msg != "登陆成功！"){
                    layer.msg(data.msg, {icon: 2});
                }else{
                    window.location.href="index";
                }
            },
            error:function (data) {
                layer.msg(data.msg, {icon: 2});
            }
        });
    });
});

$(function () {
    $("#a_logout").click(function(){
        layer.confirm('您确定要退出？', {
            btn: ['确定','取消'] ,
            skin: 'layui-layer-lan'
        }, function(){
            var url = "http://localhost:8080/logout";
            $.ajax({
                url: url,
                type: 'POST',
                success: function(){
                    layer.msg('退出成功！', {icon: 1});
                    setTimeout(function () {
                        window.location.href="logout";
                    }, 1000);
                }
            });
        }, function(){
        });
    });
});



$(function () {
    $("#ps_btn").click(function () {
        var old_password = $("#old-password").val();
        var new_password = $("#new-password").val();
        var re_password = $("#re-password").val();
        var data = {
            "oldPassword":old_password,
            "newPassword":new_password
        };
        var url = "http://localhost:8080/admin";
        if(re_password != new_password){
            layer.msg('两次密码不一致！', {icon: 2});
        }else{
            $.ajax({
                url:url,
                type:'POST',
                data:JSON.stringify(data),
                dataType:'json',
                contentType: 'application/json',
                success:function (data) {
                    if(data.msg=="修改成功"){
                        layer.msg('修改成功！', {icon: 1});
                        setTimeout(function () {
                            window.location.href="login";
                        }, 1000);
                    }else{
                        layer.msg('原密码错误！', {icon: 2});
                    }
                }
            });
        }
    });
});


layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;

    //日期
    laydate.render({
        elem: '#c_birthday'
    });

    laydate.render({
        elem: '#birthDate'
    });

    //创建一个编辑器
    var editIndex = layedit.build('LAY_demo_editor');

    //自定义验证规则
    form.verify({
        pass: [/(.+){6,12}$/, '密码必须6到12位']
        ,content: function(value){
            layedit.sync(editIndex);
        }
    });

    //监听提交
    form.on('submit(*)', function(){
        return false;
    });
});
