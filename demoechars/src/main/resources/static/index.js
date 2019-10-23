$(function () {
    $("#date").val(getNow());
    inittable1();
})
//表单提交
function submit(){
    var name = $("#name").val();
    var data = Number($("#data").val());
    var date = $("#date").val();
    if(isNaN(data)){
        debugger;
        alert("请输入数字，默认单位斤");
        return;
    }
    $.ajax({
        url:"/save",
        data:{"name":name,"data":data,"date":date},
        success:function(){
            location.reload();
        }
    })


}

function inittable1(){


    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        toolbox:{
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                }, //区域缩放，区域缩放还原
                dataView: {
                    readOnly: false
                }, //数据视图
                magicType: {
                    type: ['line', 'bar','pie']
                },  //切换为折线图，切换为柱状图
                restore: {},  //还原
                saveAsImage: {}   //保存为图片
            }
        },
        title: {
            text: '腹肌养成计划'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:[]
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        // toolbox: {
        //     feature: {
        //         saveAsImage: {}
        //     }
        // },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: []
        },
        yAxis: {
            type: 'value',
            // min: 'dataMin',
            scale:true,
            axisLabel: {
                formatter: '{value} 斤'
            }
        },
        series: []
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }


    $.ajax({
        url:"/showAll?type=1",
        dataType:"json",
        success:function(data){
            if(!data||data.length==0){
                console.log("未查询到数据")
                return;
            }
            var names=[];
            var field=[];
            for(var index=0;index<data.length;index++){
                var entity=data[index];
                names.push(entity.name);
                field.push({
                    name:entity.name,
                    type:'line',
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#8ec6ad'
                            }, {
                                offset: 1,
                                color: '#ffe'
                            }])
                        }
                    },
                    smooth: true,
                    data:entity.datas,
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                })
            }

            option.legend.data=names;
            option.xAxis.data=entity.dates;
            option.series= field;

            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        }
    });
}
function initTable(){
    $('#showTZ').bootstrapTable({
        url: '/queryAll',   //url一般是请求后台的url地址,调用ajax获取数据。此处我用本地的json数据来填充表格。
        method: "get",                     //使用get请求到服务器获取数据
        dataType: "json",
        contentType: 'application/json,charset=utf-8',
        // toolbar: "#toolbar",                //一个jQuery 选择器，指明自定义的toolbar 例如:#toolbar, .toolbar.
        height: document.body.clientHeight-165,   //动态获取高度值，可以使表格自适应页面
        queryParams: {},
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        showRefresh: true,                  //是否显示刷新按钮
        pageNumber: 1,                   //初始化加载第一页，默认第一页
        pageSize: 10,                    //每页的记录行数（*）
        pageList: [10, 25, 50, 100],     //可供选择的每页的行数（*）
        columns: [{
            field: 'name',
            title: '姓名',
            align: 'center'
        },{
            field: 'date',
            title: '日期',
            align: 'center'
        },{
            field: 'data',
            title: '数据',
            align: 'center'
        },{
        field: 'type',
            title: '类型',
            align: 'center'
    }]
    });
}

function getNow(){
    var date = new Date();
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    month=month>=10?month:"0"+month;
    var day=date.getDate();
    day=day>10?day:"0"+day;

    return year+"-"+month+"-"+day;
}