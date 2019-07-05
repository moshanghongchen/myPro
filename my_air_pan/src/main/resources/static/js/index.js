$(function(){
    document.onkeydown=function(e){	//对整个页面文档监听
        var keyNum=window.event ? e.keyCode :e.which;		//获取被按下的键值
        //判断如果用户按下了回车键（keycody=13）
        if(keyNum==13){debugger;
            var input=document.activeElement;
            var txts = window.document.getElementsByTagName("input");
            for(var index=$(txts).index($(input))+1;index<txts.length;index++){
                if(!$(txts[index]).val()){
                    $(txts[index]).focus();
                    break;
                }
            }
            return;
        }
    }
});



function ajax_Submit() {
    var fileBean={};
    fileBean.fileName=$("#fileName").val();
    fileBean.id=$("#id").val();
    $.ajax({
        url:"fileController/upLoadFile",
        data:fileBean,
        type:"post",
        // dataType:"json",
        //         // contentType:"application/json",
    })

}