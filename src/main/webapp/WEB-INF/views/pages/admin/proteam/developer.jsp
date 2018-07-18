<%--
  Created by IntelliJ IDEA.
  User: Jerry
  Date: 2017/5/16
  Time: 9:37
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
    <title>开发人员缺陷展示</title>

</head>
<body class="pd20">

<form id="paramFormDev" class="form-inline" role="form">
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
        <label class="col-sm-3 control-label label-txt" for="dev_search_ajax">开发人员</label>
        <div class="col-lg-9">
            <span style="float: right" class="glyphicon glyphicon-user form-control-feedback"></span>
            <input type="text" class="form-control" id="dev_search_ajax" autocomplete="off" name="devName" placeholder="姓名拼音" autofocus
                   data-provide="typeahead">
        </div>
    </div>

    <div style="margin-top: 30px;margin-left:30px" class="form-group">

        <input type="button" id="search" value="查询" class="btn btn-primary btn-default " onclick="submitDev()">

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
<div style="height: 600px;width: 1100px;min-height: 100px;margin-top: 60px;float: left;display:inline"
     id="dmonthview"></div>
<script type="text/javascript">

    $(document).ready(function($) {

        // Workaround for bug in mouse item selection
        $.fn.typeahead.Constructor.prototype.blur = function() {
            var that = this;
            setTimeout(function () { that.hide() }, 250);
        };

        $('#dev_search_ajax').typeahead({
            source: function (query, process) {

                $.ajax({
                    url: '/getdevNamelist.do',
                    type: 'post',
                    data: {query: query},
                    dataType: 'json',
                    success: function (result) {

                        var resultList=[];
                        for (var i = 0; i < result.length; i++) {
                            resultList.push(result[i].devName+"   "+result[i].disName);
                        }

                        return process(resultList);

                    }
                });
            },

            highlighter: function(item) {
                return "==>" + item + "<==";
            },

            updater: function (item) {
                  return  item.toString().split(" ",1);
              },


           /* matcher: function (obj) {
                var item = JSON.parse(obj);
                return ~item.name.toLowerCase().indexOf(this.query.toLowerCase())
            },
            sorter: function (items) {
                var beginswith = [], caseSensitive = [], caseInsensitive = [], item;
                while (aItem = items.shift()) {
                    var item = JSON.parse(aItem);
                    if (!item.name.toLowerCase().indexOf(this.query.toLowerCase())) beginswith.push(JSON.stringify(item));
                    else if (~item.name.indexOf(this.query)) caseSensitive.push(JSON.stringify(item));
                    else caseInsensitive.push(JSON.stringify(item));
                }
                return beginswith.concat(caseSensitive, caseInsensitive)
            },
            highlighter: function (obj) {
                var item = JSON.parse(obj);
                var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&')
                return item.name.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
                    return '<strong style="font-size:16px;">' + match + '</strong>'
                })
            },
            updater: function (obj) {
                var item = JSON.parse(obj);
                $('#product_search').attr('data-value', item.id);
                return item.name;
            },*/

            delay: 500,
            minLength: 1,
            items: 10,   //显示10条
            delay: 0,  //延迟时间

        });

    })

    //加载dom之后初始化Echarts实例
    var echartsDv1 = echarts.init(document.getElementById("dmonthview"));


    optionD1 = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true},
                dataZoom: {
                    show: true,
                    title: {
                        dataZoom: '区域缩放',
                        dataZoomReset: '区域缩放后退'
                    }
                }
            }
        },
        color: ['#2f4554', '#c23531', '#ca8622', '#d48265'],
        legend: {
            x: '100px',
            data: ['缺陷数量', '低级bug', '关键指标', '重复出现缺陷','缺陷数量占比']
        },
        xAxis: [
            {
                type: 'category',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '缺陷数量',
                min: 0,
                max: 100,
                interval: 20,
                axisLabel: {
                    formatter: '{value}'
                }
            },
            {
                type: 'value',
                name: '',
                min: 0,
                max: 50,
                interval: 5,
                axisLabel: {
                    formatter: '{value}%'
                }
            }
        ],
        series: [
            {
                name: '缺陷数量',
                type: 'bar',
                data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 35.6, 62.2, 32.6, 20.0, 6.4, 3.3]
            },
            {
                name: '低级bug',
                type: 'bar',
                stack: 'serious',
                data: [2.6, 5.9, 9.0, 26.4, 28.7, 20.7, 15.6, 12.2, 48.7, 18.8, 6.0, 2.3]
            },
            {
                name: '关键指标',
                type: 'bar',
                stack: 'serious',
                data: [2.6, 5.9, 9.0, 26.4, 28.7, 10.7, 15.6, 12.2, 48.7, 18.8, 6.0, 2.3]
            },

            {
                name: '重复出现缺陷',
                type: 'bar',
                data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 35.6, 22.2, 32.6, 20.0, 6.4, 3.3]
            },
                 {
             name:'缺陷数量占比',
             type:'line',
             yAxisIndex: 1,
             data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
             },
        ]
    };


    function submitDev() {
        var months = [];
        var stateZh = [];
        var stateDj = [];
        var stateGj = [];
        var stateCf = [];
        var sum = 0;
        var avg = [];

        $.ajax({
            type: "post",
            async: true,
            url: "/admin/getDevstat.do",
            data: $('#paramFormDev').serialize(),
            dataType: "json",
            success: function (result) {


                /*   处理各状态bug数量，如果为0则换成-字符避免产生bar*/
                for (var i = 0; i < result.length; i++) {

                    months.push(result[i].months);

                    if (result[i].zh == 0) {
                        stateZh.push("-");

                    } else {

                        stateZh.push(result[i].zh);
                    }

                    if (result[i].dj == 0) {
                        stateDj.push("-");

                    } else {

                        stateDj.push(result[i].dj);
                    }

                    if (result[i].gj == 0) {
                        stateGj.push("-");

                    } else {

                        stateGj.push(result[i].gj);
                    }

                    if (result[i].cf == 0) {
                        stateCf.push("-");

                    } else {

                        stateCf.push(result[i].cf);
                    }
                    sum = sum+parseInt(result[i].zh);

                }

                for (var j = 0; j < result.length; j++){

                    if (sum == 0) {
                        avg.push(0);

                    } else {

                        avg.push(parseFloat(parseInt(result[j].zh)*100/sum).toFixed(1));
                    }

                }


                var optionD1 = {
                    //无数据时自定义提示

                    xAxis: [
                        {
                            type: 'category',
                            data: months,
                            axisPointer: {
                                type: 'shadow'
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '缺陷数量',
                            min: 0,
                            max: 50,
                            interval: 10,
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        {
                            type: 'value',
                            name: '缺陷数量占比',
                            min: 0,
                            max: 50,
                            interval: 5,
                            axisLabel: {
                                formatter: '{value}'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '缺陷数量',
                            type: 'bar',
                            data: stateZh
                        },
                        {
                            name: '低级bug',
                            type: 'bar',
                            stack: 'serious',
                            data: stateDj
                        },
                        {
                            name: '关键指标',
                            type: 'bar',
                            stack: 'serious',
                            data: stateGj
                        },

                        {
                            name: '重复出现缺陷',
                            type: 'bar',
                            data: stateCf
                        },

                        {
                            name:'缺陷数量占比',
                            type:'line',
                            yAxisIndex: 1,
                            data:avg
                        },


                    ]

                };

                echartsDv1.setOption(optionD1);
            }


        })
    }

    echartsDv1.setOption(optionD1);


</script>


</body>
</html>
