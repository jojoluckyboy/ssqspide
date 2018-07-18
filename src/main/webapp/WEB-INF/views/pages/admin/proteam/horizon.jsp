<%--
  Created by IntelliJ IDEA.
  User: Jerry
  Date: 2017/5/2
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link rel="stylesheet" href="<c:url value='/css/vendor/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/vendor/dataTables.bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/vendor/bootstrap-datetimepicker.min.css'/>"

    <jsp:include page="../../../pages/common/meta.jsp"/>
    <title>项目组缺陷横向展示</title>



</head>
<body class="pd20">

<form id="paramForm" class="form-inline" role="form">
    <%--<div class="form-list">--%>
    <%--<div class="form-item clearfix mr20">--%>
    <%--<div class="col-xs-4 col-ml-4">--%>
    <%--<label class="item-name col-xs-3">配置项名称：</label>--%>
    <%--<div class="col-xs-9">--%>
    <%--<div class="ipt-box">--%>
    <%--<input type="text" placeholder="请输入配置项名称" class="ipt-txt" name="nameLike" value=""/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>

    <%--</div>--%>

    <div style="width:30%;margin-top: 30px;margin-left: 80px"  class="input-group date form_date col-lg-4" data-date="" data-date-format="yyyy-mm-dd">


            <span style="width: 2%" class="input-group-addon">开始时间</span>
            <input class="form-control" placeholder="请输入日期" id="theStartTime" name="startTimeV"
                   size="20" type="text" value="" readonly>
            <span style="width: 2%" class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
            <span style="width: 2%" class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>

    </div>

        <div style="width: 30%;margin-top: 30px;margin-left: 80px" class="input-group date form_date col-lg-4" data-date="" data-date-format="yyyy-mm-dd">

            <div class="col-lg-2"></div>
            <span style="width: 2%" class="input-group-addon">结束时间</span><input class="form-control " placeholder="请输入日期" id="theEndTime" name="endTimeV"
                                                              size="20" type="text" value="" readonly>
            <span style="width: 2%" class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
            <span style="width: 2%" class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>

        </div>

        </div>

        <div style="margin-top: 30px;margin-left:30px"  class="form-group">

            <input  type="button" id="search" value="查询" class="btn btn-primary btn-default " onclick="searchAllproj()">

        </div>







    <%--     <div id="free" style="display:none">
             <div class="padding5 font14gray b_b">时间：
                 <input placeholder="请输入日期" id="theStartTime" name="" value="" class="datepicker required" readonly="readonly">
                 至<input placeholder="请输入日期" id="theEndTime" name="" class="datepicker required" value="" readonly="readonly">
                 <input value="查询" type="button" class="btn" id="searchByDate"/>
             </div>
         </div>--%>
<%--    <div class="mt10">
        <div class="text-center">
            <input type="button" id="search1" value="查询" class="btn mr20">
        </div>
    </div>
    </div>--%>
</form>

   <!--- <script type="text/javascript">
        function searchAllproj() {
            $.ajax({
                type:"post",
                async:true,
                url:"/admin/gettotalpro.do",
                data:$('#paramForm').serialize(),
                dataType:"text",
                success:function(res) {
                    alert(data);
                    if(res=="yes") {
                        location.href="/admin/gettotalpro.do";
                    }
                }
            })
        }
    </script> -->

<script src="<c:url value='/js/lib/jquery/jquery-1.12.3.min.js'/>"></script>
<script src="<c:url value='/js/lib/bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/lib/bootstrap/js/bootstrap-datetimepicker.js'/>"></script>
<script src="<c:url value='/js/lib/bootstrap/js/locales/bootstrap-datetimepicker.uk.js'/>"></script>
<script src="<c:url value='/js/lib/bootstrap/js/datetimepickerutil.js'/>"></script>


<!-- echarts的引入 -->
<script src="<c:url value='/js/lib/echarts3/echarts.min.js'/>"></script>

<!-- 1.定义div初始化视图 -->
<div style="height: 410px;width: 600px;min-height: 100px;margin-top: 80px;float: left;display:inline" id="projhorizon"></div>
<script type="text/javascript">

    //加载dom之后初始化Echarts实例
    var echartsP1 = echarts.init(document.getElementById("projhorizon"));

    //为饼图绑定点击事件
    echartsP1.on('click', oneclick);

    optionP1 = {
        title: {
            subtext: '项目组缺陷分布图'
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: ['新建', '打开','拒绝','延迟修复','已解决待确认','已关闭']
        },
        grid: {
            left: '4%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis:  {
            type: 'value',
     //       max:1000
        },
        yAxis: {
            type: 'category',
            data: ['项目一','项目二','项目三','项目四','项目五','项目六','项目七']
        },
        series: [

            {
                name: '新建',
                type: 'bar',
                stack: '总量',
                barMinHeight: 5,
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                data: [320, 302, 301, 334, 390, 330, 320]
            },
            {
                name: '打开',
                type: 'bar',
                stack: '总量',
                barMinHeight: 5,
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },

                data: [120, 132, 101, 134, 90, 230, 210]
            },
            {
                name: '拒绝',
                type: 'bar',
                stack: '总量',
                barMinHeight: 5,
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                data: [220, 182, 191, 234, 290, 330, 310]
            },
            {
                name: '延迟修复',
                type: 'bar',
                stack: '总量',
                barMinHeight: 5,
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                itemStyle:{
                    normal:{color:'#FFB90F'}
                },
                data: [150, 212, 201, 154, 190, 330, 410]
            },
            {
                name: '已解决待确认',
                type: 'bar',
                stack: '总量',
                barMinHeight: 5,
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                data: [150, 212, 201, 154, 190, 330, 410]
            },
            {
                name: '已关闭',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                data: [320, 302, 301, 334, 390, 330, 320]
            }

        ]
    };


    function searchAllproj() {
        var pname = [];
        var stateXj = [];
        var stateDk = [];
        var stateJj = [];
        var stateYc = [];
        var stateDqr = [];
        var stateGb = [];
        var sum = [];
        var avg=0;
        var bugMax=0;
        var bugMin=0;
        $.ajax({
            type: "post",
            async: true,
            url: "/admin/gettotalpro.do",
            data: $('#paramForm').serialize(),
            dataType: "json",

            success: function (result) {

                /*   处理各状态bug数量，如果为0则换成-字符避免产生bar*/
                for (var i = result.length-1; i >=0; i--) {

                    pname.push(result[i].project);

                    if(result[i].xj==0)
                    {
                        stateXj.push("-");

                    }else {

                        stateXj.push(result[i].xj);
                    }

                    if(result[i].dk==0)
                    {
                        stateDk.push("-");

                    }else {

                        stateDk.push(result[i].dk);
                    }

                    if(result[i].jj==0)
                    {
                        stateJj.push("-");

                    }else {

                        stateJj.push(result[i].jj);
                    }

                    if(result[i].yc==0)
                    {
                        stateYc.push("-");

                    }else {

                        stateYc.push(result[i].yc);
                    }

                    if(result[i].dqr==0)
                    {
                        stateDqr.push("-");

                    }else {

                        stateDqr.push(result[i].dqr);
                    }

                    if(result[i].gb==0)
                    {
                        stateGb.push("-");

                    }else {

                        stateGb.push(result[i].gb);
                    }
                    sum.push(result[i].zh);


                }


           /*     获取bug的平均值，如果最大与最小差距超过50，返回2倍的平均值并10位取整
                  否则就返回当前最大bug数的1.2倍十位取整*/

                for  (var j = 0; j < sum.length; j++) {

                    avg=avg+sum[j];
                }

                bugMax=Math.max.apply(null,sum);
                bugMin=Math.min.apply(null,sum);
                if(bugMax-bugMin>50)
                {
                    avg= 20*parseInt(1+avg/(j*10));

                }else {

                    avg= 10*parseInt(bugMax*1.2/10);
                }


                // 一种写法
                /*optionP1.yAxis.data = pname;
                 optionP1.series[0].data = stateXj;
                 optionP1.series[1].data = stateDk;
                 optionP1.series[2].data = stateJj;
                 optionP1.series[3].data = stateYc;
                 optionP1.series[4].data = stateDqr;
                 optionP1.series[5].data = stateGb;*/


                    var optionP1 = {
                        //无数据时自定义提示

                        noDataLoadingOption:{
                            text : '本次搜索暂无数据',
                            effect : 'bubble',
                            effectOption:{
                                //backgroundColor:"#fff"
                            },
                            textStyle : {
                                fontSize : 20,
                                fontStyle:'normal',
                                fontWeight:'bold'
                            }
                        },
                        yAxis: {
                            data: pname //重写y轴
                        },
                        xAxis:  {
                            type: 'value',
                            //       max:1000
                            max:avg
                        },
                        series: [
                            {
                                name: '新建',

                                data: stateXj

                            },
                            {
                                name: '打开',

                                data: stateDk

                            },
                            {
                                name: '拒绝',

                                data: stateJj

                            },
                            {
                                name: '延迟修复',

                                data: stateYc

                            },

                            {
                                name: '已解决待确认',

                                data: stateDqr

                            },
                            {
                                name: '已关闭',

                                data: stateGb

                            },


                        ]

                    };






                  echartsP1.setOption(optionP1);
            }


        })


        // 在数据为动态加载之前建筑loading动画
       // echartsP1.showLoading();

        // 对数据进行重新，变成动态数据

        // 隐藏加载动画
        echartsP1.hideLoading();

    }
    // 3.载入Echarts图标
    echartsP1.setOption(optionP1);

    //饼图的绑定事件
    function oneclick(param) {

      //  console.log(param.name);
        var pname1 = "";
        var stateQt = "";
        var stateDj = "";
        var stateGj = "";
        var stateXqbf = "";
        var stateCf = "";

        var dev1="";
        var dev1defect="";
        var dev2="";
        var dev2defect="";
        var dev3="";
        var dev3defect="";

        //console.log($('#paramForm').serialize()+"&project="+param.name);

        $.ajax({
            type: "post",
            async: true,
            url: "/admin/getDtype.do",
            data: $('#paramForm').serialize()+"&project="+param.name,
            dataType: "json",

            success: function (result) {

                pname1 = param.name;
                stateQt=result[0].qt;
                stateDj=result[0].dj;
                stateGj=result[0].gj;
                stateXqbf=result[0].xqbf;
                stateCf =result[0].cf;

                dev1 =result[1].dname;
                dev1defect =result[1].zh;
                dev2 =result[2].dname;
                dev2defect =result[2].zh;
                dev3 =result[3].dname;
                dev3defect =result[3].zh;

                var optionP2 = {

                    title : {
                        text: pname1+'项目缺陷类型',
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data:[dev1,dev2,dev3,'低级bug','关键指标','其他','需求不符','重复缺陷']
                    },
                    series : [
                        {
                            name:'统计TOP3',

                            data:[
                                {value:dev1defect, name:dev1, selected:true},
                                {value:dev2defect, name:dev2},
                                {value:dev3defect, name:dev3}
                            ]
                        },
                        {
                            name:'评估类型',
                            type:'pie',
                            radius: ['40%', '55%'],

                            data:[
                                {value:stateDj, name:'低级bug'},
                                {value:stateGj, name:'关键指标'},
                                {value:stateQt, name:'其他'},
                                {value:stateXqbf, name:'需求不符'},
                                {value:stateCf, name:'重复缺陷'}

                            ]
                        }
                    ]


                };

                echartsP2.setOption(optionP2);

            }

        })
    }

</script>

<div style="height: 410px;width: 600px;min-height: 100px;margin-top: 80px;float: left;display:inline" id="test1"></div>
<script type="text/javascript">

    var echartsP2 = echarts.init(document.getElementById("test1"));

    optionP2 = {
        title : {
            text: '项目缺陷类型分布',
            subtext: '缺陷类型分布图',
            x:'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['开发人员一','开发人员二','开发人员三','低级bug','关键指标','其他','需求不符','重复缺陷']
        },
        series: [
            {
                name:'统计TOP3',
                type:'pie',
                selectedMode: 'single',
                radius: [0, '30%'],

                label: {
                    normal: {
                        position: 'inner'
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:335, name:'开发人员一', selected:true},
                    {value:679, name:'开发人员二'},
                    {value:1548, name:'开发人员三'}
                ]
            },
            {
                name:'评估类型',
                type:'pie',
                radius: ['40%', '55%'],

                data:[
                    {value:335, name:'低级bug'},
                    {value:310, name:'关键指标'},
                    {value:234, name:'其他'},
                    {value:135, name:'需求不符'},
                    {value:1048, name:'重复缺陷'}

                ]
            }
        ]
    };


    echartsP2.setOption(optionP2);


</script>

</body>
</html>
