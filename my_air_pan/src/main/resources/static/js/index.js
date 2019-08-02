$(function(){
    console.log("查询文件信息");
    loadTable('fileList',getFileField());
});


function loadTable(tableName,fieldList){
    fieldList.unshift({
        title: "全选",
        field: "select",
        checkbox: true,
        width: 20,//宽度
        align: "center",//水平
        valign: "middle"//垂直
    });
    $("#"+tableName).bootstrapTable({
        method:'get',
        dataType:'json',
        pagination:true,
        showRefresh:true,
        toolbar:'#toolbar',
        sortOrder:'asc',
        pageNumber:1,
        pageSize:10,
        pageList:[5,10,15,'ALL'],
        url:'/fileController/showFileByBootStrap',
        queryParams:function (params) {
            return '';
        },
        showColumns: true,//列选择按钮
        columns:fieldList,
    })
}
function getFileField(){
    var info=[{
        title: "文件名",
        field: "fileName",
    },{
        title: "文件大小",
        field: "fileSize",
        formatter:function (value,row,index) {
            if(typeof(value)=="number"){
                if(value<1024){
                    return value+"B";
                }else if(value<1024*1024){
                    return (value/1024).toFixed(0)+"KB";
                }else {
                    return (value/(1024*1024)).toFixed(2)+"MB"
                }
            }
            return value;
        }
    },{
        title: "更新日期",
        field: "updateTime",
        formatter:function (value,row,index) {
            var date=new Date(value);
            if('Invalid Date'==date){
                return ''
            }
            return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDay();
        }
    }];
    return info;
}

function downloadFile(){
    var selected= $("#fileList").bootstrapTable('getSelections');
    if(selected.length !=1){
        alert("只能选择一条数据！！！");
    }

    var fileId=selected[0].id;
    console.log("开始下载")
    window.location.href='/fileController/downloadFile?fileId='+fileId;
}

