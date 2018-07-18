<%--
  Created by IntelliJ IDEA.
  User: Jerry
  Date: 2017/5/15
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>项目组缺陷纵向展示</title>



</head>
<body class="pd20">

<form id="paramFormDevgroup" class="form-inline" role="form">
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

    <div style="width:22%;margin-top: 30px;margin-left: 30px" class="input-group date form_date col-lg-3" data-date=""
         data-date-format="yyyy-mm-dd">


        <span style="width: 1% " class="input-group-addon">开始时间</span>
        <input class="form-control" placeholder="请输入日期" id="theStartTime" name="dstartTimeV"
               size="20" type="text" value="" readonly>
        <span style="width: 2%" class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span style="width: 2%" class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>

    </div>

    <div style="width: 25%;margin-top: 30px;margin-left: 30px" class="input-group date form_date col-lg-3" data-date=""
         data-date-format="yyyy-mm-dd">

        <div class="col-lg-1"></div>
        <span style="width: 1%" class="input-group-addon">结束时间</span><input class="form-control " placeholder="请输入日期"
                                                                            id="theEndTime" name="dendTimeV"
                                                                            size="20" type="text" value="" readonly>
        <span style="width: 2%" class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span style="width: 2%" class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>

    </div>

    <div style="margin-top: 30px;margin-left:30px" class="form-group has-feedback">
        <label class="col-sm-4 control-label label-txt" >开发团队</label>
        <div class="col-lg-8">
            <select class="form-control" name="devGroup">
                <option>APP组</option>
                <option>数据服务组</option>
            </select>
        </div>
    </div>

    <div style="margin-top: 30px;margin-left:50px" class="form-group">

        <input type="button" id="search" value="查询" class="btn btn-primary btn-default " onclick="submitDgroup()">

    </div>


</form>

<script src="<c:url value='/js/lib/jquery/jquery-1.12.3.min.js'/>"></script>
<script src="<c:url value='/js/lib/bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/lib/bootstrap/js/bootstrap-datetimepicker.js'/>"></script>
<script src="<c:url value='/js/lib/bootstrap/js/locales/bootstrap-datetimepicker.uk.js'/>"></script>
<script src="<c:url value='/js/lib/bootstrap/js/datetimepickerutil.js'/>"></script>

<script src="<c:url value='/js/lib/bootstrap/js/bootstrap3-typeahead.js'/>"></script>

<!-- echarts的引入 -->
<script src="<c:url value='/js/lib/echarts3/echarts.min.js'/>"></script>

<!-- 1.定义div初始化视图 -->
<div style="height: 410px;width: 600px;min-height: 100px;margin-top: 80px;float: left;display:inline"
     id="dgroupview"></div>
<script type="text/javascript">

    //加载dom之后初始化Echarts实例
    var echartsDg1 = echarts.init(document.getElementById("dgroupview"));

    //为饼图绑定点击事件
    echartsDg1.on('click', oneclick);

    optionG1 = {

        title: {
            subtext: '项目组缺陷分布图'
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        color: ['#61a0a8','#2f4554', '#c23531', '#d48265', '#ca8622','#2f4554','#c23531','#61a0a8'],
        legend: {
            x: '160px',
            data:['项目','提交缺陷','低级bug','关键指标','重复出现']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['开发人员一','开发人员二','开发人员三','开发人员四','开发人员五']
            }
        ],
        yAxis : [
            {
                type: 'value',
                min: 0,
                max: 200,
                interval: 20,
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series : [
            {
                name:'项目',
                type:'bar',
                stack: '项目一',
                data:['', '', '', '', '']
            },
            {
                name:'提交缺陷',
                type:'bar',
                stack: '项目一',
                data:[20, 32, 31, 34, 30]

            },
            {
                name:'低级bug',
                type:'bar',
                stack: '项目一',
                data:[20, 32, 11, 14, 20]
            },
            {
                name:'关键指标',
                type:'bar',
                stack: '项目一',
                data:[20, 12, 11, 34, 20]
            },

            {
                name: '重复出现',
                type: 'bar',
                data: [26, 11, 6, 2, 7],
                stack: '项目一',
            },
            {
                name:'项目',
                type:'bar',
                stack: '项目二',
                data:['', '', '', '', '']
            },
            {
                name:'提交缺陷',
                type:'bar',
                stack: '项目二',
                data:[30, 32, 21, 34, 20]

            },
            {
                name:'低级bug',
                type:'bar',
                stack: '项目二',
                data:[2, 3, 1, 4, 26]
            },
            {
                name:'关键指标',
                type:'bar',
                stack: '项目二',
                data:[10, 2, 11, 4, 10]
            },

            {
                name: '重复出现',
                type: 'bar',
                data: [6, 10, 4, 2, 3],
                stack: '项目二',
            },
            {
                name:'top3人员',
                type:'pie',
                tooltip : {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                center: [540,160],
                radius : [0, 60],
                itemStyle :　{
                    normal : {
                        labelLine : {
                            length : 20
                        }
                    }
                },
                data:[
                    {value:51, name:'开发人员一'},
                    {value:48, name:'开发人员二'},
                    {value:12, name:'开发人员三'},

                ]
            }

        ]
    };
    function submitDgroup() {
        var dname = [];
        var project=[];
        var stateP = [];
        var stateZh1 = [];
        var stateZh2 = [];
        var stateZh3 = [];
        var stateDj1 = [];
        var stateDj2 = [];
        var stateDj3 = [];
        var stateGj1 = [];
        var stateGj2 = [];
        var stateGj3 = [];
        var stateCf1 = [];
        var stateCf2 = [];
        var stateCf3 = [];
        var pnum = 0;
        var devgroup = '';
        var series=[];
        var resultSort=[];
        var pie=[];
        var result2=[];
        var result3=[];
        var tempdname='';
        var tempzh=0;

        $.ajax({
            type: "post",
            async: true,
            url: "/admin/getDevgroup.do",
            data: $('#paramFormDevgroup').serialize(),
            dataType: "json",
            success: function (result) {

                devgroup = $('#paramFormDevgroup').serializeArray()[2].value;

                for (var i = 0, j = result.length; i < j; i++) {
                    if (dname.indexOf(result[i].dname) === -1)
                    {
                        dname.push(result[i].dname);
                                }
                        }


                for (var i = 0, j = result.length; i < j; i++) {
                    if (project.indexOf(result[i].project) === -1)
                    {
                        project.push(result[i].project);
                    }
                }


                pnum =result[0].pnum;

                /*
                 @function     JsonSort 对json排序
                 @param        json     用来排序的json
                 @param        key      排序的键值
                 */
                function JsonSort(json,key) {

                    for (var j = 1, jl = json.length; j < jl; j++) {
                        var temp = json[j],
                            val = temp[key],
                            i = j - 1;
                        while (i >= 0 && json[i][key] < val) {
                            json[i + 1] = json[i];
                            i = i - 1;
                        }
                        json[i + 1] = temp;

                    }

                    return json;

                }

                if (pnum ==1)
                {
                    for(var i = 0;i<result.length;i++){
                        stateP.push('');

                        if (result[i].zh == 0) {
                            stateZh1.push("-");

                        } else {

                            stateZh1.push(result[i].zh);
                        }

                        if (result[i].dj == 0) {
                            stateDj1.push("-");

                        } else {

                            stateDj1.push(result[i].dj);
                        }
                        if (result[i].gj == 0) {
                            stateGj1.push("-");

                        } else {

                            stateGj1.push(result[i].dj);
                        }
                        if (result[i].cf == 0) {
                            stateCf1.push("-");

                        } else {

                            stateCf1.push(result[i].dj);
                        }


                    }


                    resultSort = JsonSort(result,'zh');


                    if(resultSort.length>2){

                        pie =  {
                            name:'top3人员',
                            type:'pie',
                            tooltip : {
                                trigger: 'item',
                                formatter: '{a} <br/>{b} : {c} ({d}%)'
                            },
                            center: [540,160],
                            radius : [0, 60],
                            itemStyle :　{
                                normal : {
                                    labelLine : {
                                        length : 20
                                    }
                                }
                            },
                            data:[
                                {value:resultSort[0].zh, name:resultSort[0].dname},
                                {value:resultSort[1].zh, name:resultSort[1].dname},
                                {value:resultSort[2].zh, name:resultSort[2].dname}

                            ]
                        };

                    }else {

                        pie =  {
                            name:'top3人员',
                            type:'pie',
                            tooltip : {
                                trigger: 'item',
                                formatter: '{a} <br/>{b} : {c} ({d}%)'
                            },
                            center: [540,160],
                            radius : [0, 60],
                            itemStyle :　{
                                normal : {
                                    labelLine : {
                                        length : 20
                                    }
                                }
                            },
                            data:[
                                {value:resultSort[0].zh, name:resultSort[0].dname},
                                {value:resultSort[1].zh, name:resultSort[1].dname}


                            ]
                        };

                    }

                    series = [
                        {
                            name:'项目一：'+project[0],
                            type:'bar',
                            stack: '项目一',
                            data:stateP
                        },
                        {
                            name:'提交缺陷',
                            type:'bar',
                            stack: '项目一',
                            barWidth: 40,
                            data:stateZh1

                        },
                        {
                            name:'低级bug',
                            type:'bar',
                            barWidth: 40,
                            stack: '项目一',
                            data:stateDj1
                        },
                        {
                            name:'关键指标',
                            type:'bar',
                            barWidth: 40,
                            stack: '项目一',
                            data:stateGj1
                        },

                        {
                            name: '重复出现',
                            type: 'bar',
                            barWidth: 40,
                            stack: '项目一',
                            data:stateCf1
                        }

                    ]

                    series.push(pie);
                    resultSort = [];

                };

   //             debugger;

                if (pnum ==2)
                {
                    for(var i = 0;i<result.length;i=i+2){
                        stateP.push('');
                        tempdname = result[i].dname;
                        tempzh = result[i].zh+result[i+1].zh;
                        var obj={};
                        obj.dname = tempdname;
                        obj.zh = tempzh;
                        result2.push(obj);



                        if (result[i].zh == 0) {
                            stateZh1.push("-");

                        } else {

                            stateZh1.push(result[i].zh);
                        }

                        if (result[i].dj == 0) {
                            stateDj1.push("-");

                        } else {

                            stateDj1.push(result[i].dj);
                        }
                        if (result[i].gj == 0) {
                            stateGj1.push("-");

                        } else {

                            stateGj1.push(result[i].dj);
                        }
                        if (result[i].cf == 0) {
                            stateCf1.push("-");

                        } else {

                            stateCf1.push(result[i].dj);
                        }

                    }

                    for(var i = 1;i<result.length;i=i+2){
                        stateP.push('');

                        if (result[i].zh == 0) {
                            stateZh2.push("-");

                        } else {

                            stateZh2.push(result[i].zh);
                        }

                        if (result[i].dj == 0) {
                            stateDj2.push("-");

                        } else {

                            stateDj2.push(result[i].dj);
                        }
                        if (result[i].gj == 0) {
                            stateGj2.push("-");

                        } else {

                            stateGj2.push(result[i].dj);
                        }
                        if (result[i].cf == 0) {
                            stateCf2.push("-");

                        } else {

                            stateCf2.push(result[i].dj);
                        }

                    }


                    resultSort = JsonSort(result2,'zh');


                    if(resultSort.length>2){

                        pie =  {
                            name:'top3人员',
                            type:'pie',
                            tooltip : {
                                trigger: 'item',
                                formatter: '{a} <br/>{b} : {c} ({d}%)'
                            },
                            center: [540,160],
                            radius : [0, 60],
                            itemStyle :　{
                                normal : {
                                    labelLine : {
                                        length : 20
                                    }
                                }
                            },
                            data:[
                                {value:resultSort[0].zh, name:resultSort[0].dname},
                                {value:resultSort[1].zh, name:resultSort[1].dname},
                                {value:resultSort[2].zh, name:resultSort[2].dname}

                            ]
                        };

                    }else {

                        pie =  {
                            name:'top3人员',
                            type:'pie',
                            tooltip : {
                                trigger: 'item',
                                formatter: '{a} <br/>{b} : {c} ({d}%)'
                            },
                            center: [540,160],
                            radius : [0, 60],
                            itemStyle :　{
                                normal : {
                                    labelLine : {
                                        length : 20
                                    }
                                }
                            },
                            data:[
                                {value:resultSort[0].zh, name:resultSort[0].dname},
                                {value:resultSort[1].zh, name:resultSort[1].dname}


                            ]
                        };

                    }

                    series = [
                        {
                            name:'项目一：'+project[0],
                            type:'bar',
                            stack: '项目一',
                            data:stateP
                        },
                        {
                            name:'提交缺陷',
                            type:'bar',
                            stack: '项目一',
                            data:stateZh1

                        },
                        {
                            name:'低级bug',
                            type:'bar',
                            stack: '项目一',
                            data:stateDj1
                        },
                        {
                            name:'关键指标',
                            type:'bar',
                            stack: '项目一',
                            data:stateGj1
                        },

                        {
                            name: '重复出现',
                            type: 'bar',
                            stack: '项目一',
                            data:stateCf1
                        },
                        {
                            name:'项目二：'+project[1],
                            type:'bar',
                            stack: '项目二',
                            data:stateP
                        },
                        {
                            name:'提交缺陷',
                            type:'bar',
                            stack: '项目二',
                            data:stateZh2

                        },
                        {
                            name:'低级bug',
                            type:'bar',
                            stack: '项目二',
                            data:stateDj2
                        },
                        {
                            name:'关键指标',
                            type:'bar',
                            stack: '项目二',
                            data:stateGj2
                        },

                        {
                            name: '重复出现',
                            type: 'bar',
                            stack: '项目二',
                            data:stateCf2
                        }

                    ]
                    series.push(pie);
                    tempdname = [];
                    tempzh = 0;
                    result2=[];
                    resultSort=[];
                }

                if (pnum >=3)
                {
                    for(var i = 0;i<result.length;i=i+3){
                        stateP.push('');
                        tempdname = result[i].dname;
                        tempzh = result[i].zh+result[i+1].zh+result[i+2].zh;
                        obj={};
                        obj.dname = tempdname;
                        obj.zh = tempzh;
                        result3.push(obj);



                        if (result[i].zh == 0) {
                            stateZh1.push("-");

                        } else {

                            stateZh1.push(result[i].zh);
                        }

                        if (result[i].dj == 0) {
                            stateDj1.push("-");

                        } else {

                            stateDj1.push(result[i].dj);
                        }
                        if (result[i].gj == 0) {
                            stateGj1.push("-");

                        } else {

                            stateGj1.push(result[i].dj);
                        }
                        if (result[i].cf == 0) {
                            stateCf1.push("-");

                        } else {

                            stateCf1.push(result[i].dj);
                        }

                    }

                    for(var i = 1;i<result.length;i=i+3){
                        stateP.push('');

                        if (result[i].zh == 0) {
                            stateZh2.push("-");

                        } else {

                            stateZh2.push(result[i].zh);
                        }

                        if (result[i].dj == 0) {
                            stateDj2.push("-");

                        } else {

                            stateDj2.push(result[i].dj);
                        }
                        if (result[i].gj == 0) {
                            stateGj2.push("-");

                        } else {

                            stateGj2.push(result[i].dj);
                        }
                        if (result[i].cf == 0) {
                            stateCf2.push("-");

                        } else {

                            stateCf2.push(result[i].dj);
                        }

                    }

                    for(var i = 2;i<result.length;i=i+3){
                        stateP.push('');

                        if (result[i].zh == 0) {
                            stateZh3.push("-");

                        } else {

                            stateZh3.push(result[i].zh);
                        }

                        if (result[i].dj == 0) {
                            stateDj3.push("-");

                        } else {

                            stateDj3.push(result[i].dj);
                        }
                        if (result[i].gj == 0) {
                            stateGj3.push("-");

                        } else {

                            stateGj3.push(result[i].dj);
                        }
                        if (result[i].cf == 0) {
                            stateCf3.push("-");

                        } else {

                            stateCf3.push(result[i].dj);
                        }

                    }


                    resultSort = JsonSort(result3,'zh');


                    if(resultSort.length>2){

                        pie =  {
                            name:'top3人员',
                            type:'pie',
                            tooltip : {
                                trigger: 'item',
                                formatter: '{a} <br/>{b} : {c} ({d}%)'
                            },
                            center: [540,160],
                            radius : [0, 60],
                            itemStyle :　{
                                normal : {
                                    labelLine : {
                                        length : 20
                                    }
                                }
                            },
                            data:[
                                {value:resultSort[0].zh, name:resultSort[0].dname},
                                {value:resultSort[1].zh, name:resultSort[1].dname},
                                {value:resultSort[2].zh, name:resultSort[2].dname}

                            ]
                        };

                    }else {

                        pie =  {
                            name:'top3人员',
                            type:'pie',
                            tooltip : {
                                trigger: 'item',
                                formatter: '{a} <br/>{b} : {c} ({d}%)'
                            },
                            center: [540,160],
                            radius : [0, 60],
                            itemStyle :　{
                                normal : {
                                    labelLine : {
                                        length : 20
                                    }
                                }
                            },
                            data:[
                                {value:resultSort[0].zh, name:resultSort[0].dname},
                                {value:resultSort[1].zh, name:resultSort[1].dname}


                            ]
                        };

                    }

                    series = [
                        {
                            name:'项目一：'+project[0],
                            type:'bar',
                            stack: '项目一',
                            data:stateP
                        },
                        {
                            name:'提交缺陷',
                            type:'bar',
                            stack: '项目一',
                            data:stateZh1

                        },
                        {
                            name:'低级bug',
                            type:'bar',
                            stack: '项目一',
                            data:stateDj1
                        },
                        {
                            name:'关键指标',
                            type:'bar',
                            stack: '项目一',
                            data:stateGj1
                        },

                        {
                            name: '重复出现',
                            type: 'bar',
                            stack: '项目一',
                            data:stateCf1
                        },
                        {
                            name:'项目二：'+project[1],
                            type:'bar',
                            stack: '项目二',
                            data:stateP
                        },
                        {
                            name:'提交缺陷',
                            type:'bar',
                            stack: '项目二',
                            data:stateZh2

                        },
                        {
                            name:'低级bug',
                            type:'bar',
                            stack: '项目二',
                            data:stateDj2
                        },
                        {
                            name:'关键指标',
                            type:'bar',
                            stack: '项目二',
                            data:stateGj2
                        },

                        {
                            name: '重复出现',
                            type: 'bar',
                            stack: '项目二',
                            data:stateCf2
                        },
                        {
                            name:'项目三：'+project[2],
                            type:'bar',
                            stack: '项目三',
                            data:stateP
                        },
                        {
                            name:'提交缺陷',
                            type:'bar',
                            stack: '项目三',
                            data:stateZh3

                        },
                        {
                            name:'低级bug',
                            type:'bar',
                            stack: '项目三',
                            data:stateDj3
                        },
                        {
                            name:'关键指标',
                            type:'bar',
                            stack: '项目三',
                            data:stateGj3
                        },

                        {
                            name: '重复出现',
                            type: 'bar',
                            stack: '项目三',
                            data:stateCf3
                        }

                    ]
                    series.push(pie);
                    tempdname = [];
                    tempzh = 0;
                    result3=[];
                    resultSort=[];
                }

    //            debugger;

                var optionG2 = {

                    title: {
                        subtext: devgroup+'缺陷分布图'
                    },
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    color: ['#61a0a8','#2f4554', '#c23531', '#d48265', '#ca8622','#2f4554','#c23531','#61a0a8'],
                    legend: {
                        x: '160px',
                        data:['项目','提交缺陷','低级bug','关键指标','重复出现']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : dname
                        }
                    ],
                    yAxis : [
                        {
                            type: 'value',
                            min: 0,
                            max: 200,
                            interval: 20,
                            axisLabel: {
                                formatter: '{value}'
                            }
                        }
                    ],


                    series : series
                };


                echartsDg1.setOption(optionG2,true);
            }


        })
    }

    echartsDg1.setOption(optionG1);

    //饼图的绑定事件
    function oneclick(param) {

        var pname = "";
        var stateQt = "";
        var stateDj = "";
        var stateGj = "";
        var stateXqbf = "";
        var stateCf = "";

        var stateXj = "";
        var stateGb = "";
        var stateDqr = "";


        $.ajax({
            type: "post",
            async: true,
            url: "/admin/getDevbyperiod.do",
            data: $('#paramFormDevgroup').serialize()+"&dname="+param.name,
            dataType: "json",

            success: function (result) {

                pname =param.name;
                stateQt=result[0].qt;
                stateDj=result[0].dj;
                stateGj=result[0].gj;
                stateXqbf=result[0].xqbf;
                stateCf =result[0].cf;

                stateXj=result[0].xj;
                stateGb=result[0].gb;
                stateDqr =result[0].dqr;

                var optionDGr2 = {
                    title : {
                        text: pname+'缺陷状态分布',
                        subtext: '缺陷状态分布图',
                        x:'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data:['新建','关闭','已解决待确认','低级bug','关键指标','其他','需求不符','重复缺陷']
                    },
                    series: [
                        {
                            name:'缺陷状态',
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
                                {value:stateXj, name:'新建', selected:true},
                                {value:stateGb, name:'关闭'},
                                {value:stateDqr, name:'已解决待确认'}
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
                echartsdg2.setOption(optionDGr2,true);
           }
        })
    }
</script>


<div style="height: 410px;width: 600px;min-height: 100px;margin-top: 80px;margin-left: 10px;float:right;display:inline" id="dgroupview2"></div>
<script type="text/javascript">

    var echartsdg2 = echarts.init(document.getElementById("dgroupview2"));

    optionDG2 = {
        title : {
            text: '开发人员缺陷状态分布',
            subtext: '缺陷状态分布图',
            x:'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['新建','关闭','已解决待确认','低级bug','关键指标','其他','需求不符','重复缺陷']
        },
        series: [
            {
                name:'缺陷状态',
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
                    {value:35, name:'新建', selected:true},
                    {value:29, name:'关闭'},
                    {value:8, name:'已解决待确认'}
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


    echartsdg2.setOption(optionDG2);


</script>

</body>
</html>
