
var table = $('#clerks_table').DataTable({
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
        { "data": 'clerkId' },
        { "data": 'sex' },
        { "data": 'clerkName' },
        { "data": 'mobile'},
        { "data": 'saleNum'}
    ],
    "bFilter": false,
    "bSort": true,
    "bInfo": true,
    "bProcessing": false, // 加载条
    "iDisplayLength": 10,
    "bServerSide": true,
    "sAjaxSource": "http://localhost:8080/clerks",
    "fnServerData": retrieveData,
    "columnDefs": [{
            "targets": 5,//编辑
            "data": null,
            "defaultContent": "<i id='editrow' class='fa fa-edit' data-toggle='modal' data-target='#clerkModal' style='cursor: pointer'></i>&nbsp;&nbsp;  " +
            "<i id='delrow' class='fa fa-trash-o' style='cursor: pointer'></i>"
        },{
            orderable:false,//禁用排序
            targets:[4]   //指定的列
        }
    ]
});

$(function () {
    $('#clerks_table tbody').on( 'click', 'i#delrow', function () {
        var id = table.row($(this).parents('tr') ).data().clerkId;
        layer.confirm('您确定要删除？', {
            btn: ['删除','取消']
        }, function(){
            var url = "http://localhost:8080/clerk/"+id;
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
    $('#clerks_table tbody').on( 'click', 'i#editrow', function () {
        var id = table.row($(this).parents('tr') ).data().clerkId;
        var url = "http://localhost:8080/clerk/"+id;
        $.ajax({
            url: url,
            type: 'GET',
            contentType: 'application/json',
            dataType:'json',
            success: function(data) {
                $("#c_Id").val(data.clerkId);
                $("#c_name").val(data.clerkName);
                $("#c_birthday").val(data.birthday);
                $("#c_mobile").val(data.mobile);
                $("#c_note").val(data.note);
                switch (data.sex){
                    case "男":$("input[value='男']").prop('checked',true);break;
                    case "女":$("input[value='女']").prop('checked',true);break;
                    case "未知":$("input[value='未知']").prop('checked',true);break;
                }
            }
        });
    }) ;
});

function retrieveData(sSource, aoData, fnCallback ) {
    // alert(JSON.stringify(aoData));
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
   return $("#c_put").click(function () {
       var data={
           "clerkId":$("#c_Id").val(),
           "clerkName":$("#c_name").val(),
           "birthday":$("#c_birthday").val(),
           "sex":$('input:radio[name="c_sex"]:checked').val(),
           "mobile":$("#c_mobile").val(),
           "note":$("#c_note").val()
       };
       var url="http://localhost:8080/clerk";
       $.ajax({
           url: url,
           type: 'PUT',
           data: JSON.stringify(data),
           contentType: 'application/json',
           success: function(){
               table.draw();
               layer.msg('修改成功！', {icon: 1});
               $('#clerkModal').modal('hide');
           },
           error:function () {
               layer.msg('修改失败！', {icon: 2});
           }
       });
   });
});

