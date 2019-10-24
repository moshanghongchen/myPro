$(function () {
    $("#date2").val(getNow());
    inittable2();
})
//表单提交
function submit(){
    var name = $("#name2").val();
    var data = Number($("#data2").val());
    var date = $("#date2").val();
    if(isNaN(data)){
        debugger;
        alert("请输入数字，默认单位斤");
        return;
    }
    $.ajax({
        url:"/save",
        data:{"name":name,"data":data,"date":date,"type":"2"},
        success:function(){
            location.reload();
        }
    })


}

function inittable2(){
    var dom = document.getElementById("containerYD");
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
                formatter: '{value} km'
            }
        },
        series: []
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }


    $.ajax({
        url:"/showAll?type=2",
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

function getNow(){
    var date = new Date();
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    month=month>=10?month:"0"+month;
    var day=date.getDate();
    day=day>10?day:"0"+day;

    return year+"-"+month+"-"+day;
}