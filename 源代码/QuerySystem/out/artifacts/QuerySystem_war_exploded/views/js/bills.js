
var table = $('#bills_table').DataTable({
    "language": {
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    },
    "aLengthMenu": [5,10, 25, 50],
    "paging": true,
    "searching": true,
    "columns": [
        { "data": 'inforceTime' },
        { "data": 'baodanNo' },
        { "data": 'polName' },
        { "data": 'holderName'},
        { "data": 'clerkID'}
    ],
    "bFilter": false,
    "bSort": true,
    "bInfo": true,
    "bProcessing": false, // 加载条
    "iDisplayLength": 10,
    "bServerSide": true,//这个用来指明是通过服务端来取数据
    "sAjaxSource": "http://localhost:8080/bills",//这个是请求的地址，Rest API or JSP的action
    "fnServerData": retrieveData, // 获取数据的处理函数
    "columnDefs": [{
        "targets": 5,//编辑
        "data": null,
        "defaultContent": "<i id='editrow' class='fa fa-edit' data-toggle='modal' data-target='#billModal' style='cursor: pointer'></i>&nbsp;&nbsp;  " +
            "<i id='delrow' class='fa fa-trash-o' style='cursor: pointer'></i>"
        }
// //      ,{
// //           orderable:false,//禁用排序
// //           targets:[3]   //指定的列
// //      }
    ]
});
$(function () {
    $('#bills_table tbody').on( 'click', 'i#delrow', function () {
        var id = table.row($(this).parents('tr') ).data().id;
        layer.confirm('您确定要删除？', {
            btn: ['删除','取消']
        }, function(){
            var url = "http://localhost:8080/bill/"+id;
            $.ajax({
                url: url,
                type: 'DELETE',
                contentType: 'application/json',
                success: function() {
                    table.draw();
                    layer.msg('删除成功！', {icon: 1});
                },
                error:function () {
                    layer.msg('删除失败！', {icon: 2});
                }
            });
        }, function(){
        });
    });
});

$(function () {
    $('#bills_table tbody').on( 'click', 'i#editrow', function () {
        var id = table.row($(this).parents('tr') ).data().id;
        var url = "http://localhost:8080/bill/"+id;
        $.ajax({
            url: url,
            type: 'GET',
            contentType: 'application/json',
            dataType:'json',
            success: function(data) {
                $("#b_id").val(data.id);
                $("#holderName").val(data.holderName) ;
                $("#birthDate").val(data.birthDate) ;
                $("#mobile").val(data.mobile) ;
                $("#money").val(data.money) ;
                $("#clerkId").val(data.clerkID);
                if(data.insureds[0].level == '1'){
                    $("#insuredName").val(data.insureds[0].insuredName) ;
                    $("#rel").val(data.insureds[0].rel) ;
                    $("#insuredName2").val(data.insureds[1].insuredName) ;
                    $("#rel2").val(data.insureds[1].rel) ;
                }else{
                    $("#insuredName").val(data.insureds[1].insuredName) ;
                    $("#rel").val(data.insureds[1].rel) ;
                    $("#insuredName2").val(data.insureds[0].insuredName) ;
                    $("#rel2").val(data.insureds[0].rel) ;
                }
                switch (data.sex){
                    case "男":$("input:radio[name='b_sex']:checked").val("男");break;
                    case "女":$("input:radio[name='b_sex']:checked").val("女");break;

                }
                switch (data.polName){
                    case "意外险":$("#polName").val(0); break;
                    case "健康险":$("#polName").val(1); break;
                    case "补充医疗险":$("#polName").val(2); break;
                    case "分红险":$("#polName").val(3); break;
                }
            }
        });
    }) ;
});

function retrieveData(sSource, aoData, fnCallback ) {
//        alert(JSON.stringify(aoData));
    $.ajax({
        url : sSource,
        data : {"aoData":JSON.stringify(aoData)},
        type : 'GET',
        dataType : 'json',
        success : function(result) {
            fnCallback(result);
        },
        error : function(msg) {
            layer.msg('查询失败！', {icon: 2});
        }
    });
};

$(function () {
   $("#b_put").click(function () {
       var data={
           "id":$("#b_id").val(),
           "holderName":$("#holderName").val(),
           "sex":$('input:radio[name="b_sex"]:checked').val(),
           "birthDate":$("#birthDate").val(),
           "mobile":$("#mobile").val(),
           "polName":$("#polName").find("option:selected").text(),
           "money":$("#money").val(),
           "insureds":[
               {
                   "insuredName":$("#insuredName").val(),
                   "rel":$("#rel").val(),
                   "level":1
               },
               {
                   "insuredName":$("#insuredName2").val(),
                   "rel":$("#rel2").val(),
                   "level":2
               }
            ]
       };
       var url = "http://localhost:8080/bill";
       $.ajax({
           url: url,
           type: 'PUT',
           data: JSON.stringify(data),
           contentType: "application/json",
           success: function() {
               table.draw();
               layer.msg('修改成功！', {icon: 1});
               $('#billModal').modal('hide');
           },
           error:function () {
               layer.msg('修改失败！', {icon: 2});
           }
       });
   }) ;
});

$(function () {
    laydate.render({
        elem: '#birthDate'
    });
});

