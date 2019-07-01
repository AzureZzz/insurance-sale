
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
        { "data": 'holderName'}
    ],
    "bFilter": false,
    "bSort": true,
    "bInfo": true,
    "bProcessing": false, // 加载条
    "iDisplayLength": 10,
    "bServerSide": true,//这个用来指明是通过服务端来取数据
    "sAjaxSource": "http://localhost:8079/bills",//这个是请求的地址，Rest API or JSP的action
    "fnServerData": retrieveData, // 获取数据的处理函数
    "columnDefs": [{
            "targets": 4,//编辑
            "data": null,
            "defaultContent": "<i id='editrow' class='fa fa-edit' data-toggle='modal' data-target='#billModal' style='cursor: pointer'></i>"
        }
    ]
});
function retrieveData(sSource, aoData, fnCallback ) {
    $.ajax({
        url : sSource,
        data : {"aoData":JSON.stringify(aoData)},
        type : 'GET',
        dataType : 'json',
        success : function(result) {
            fnCallback(result);
        },
        error : function(msg) {
            alert(msg);
        }
    });
};


$(function () {
    $('#bills_table tbody').on( 'click', 'i#editrow', function () {
        var id = table.row($(this).parents('tr') ).data().id;
        var url = "http://localhost:8079/bill/"+id;
        $.ajax({
            url: url,
            type: 'GET',
            contentType: 'application/json',
            dataType:'json',
            success: function(data) {
                $("#holderName").val(data.holderName) ;
                $("#s_birthDate").val(data.birthDate) ;
                $("#mobile").val(data.mobile) ;
                $("#money").val(data.money) ;
                $("#polName").val(data.sex);
                $("#sex").val(data.polName);
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
            }
        });
    }) ;
});

$(function () {
    laydate.render({
        elem: '#birthDate'
    });
});

