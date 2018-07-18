<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
   Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
  --%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>统一开发框架首页</title>
    <link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>" type="image/x-icon"/>
    <link rel="stylesheet" href="<c:url value='/css/vendor/layout-default-latest.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">

</head>
<body style="overflow: hidden; position: relative">

<jsp:include page="common/header.jsp"/>
<div id="container">
    <div class="pane ui-layout-center">
        <div id="page-wrapper">
            <div id="content-main" class="J_mainContent">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="" frameborder="0"></iframe>
            </div>
            <div class="footer">
                版权所有：浙江汇信科技有限公司<span class="ml40">技术支持：浙江汇信科技有限公司</span>
            </div>
        </div>
    </div>

    <div class="pane ui-layout-west">
        <div class="nano">
            <div class="nano-content">
                <div class="sidebar">
                    <div class="sidebar-nav">
                        <ul id="side-menu" class="metismenu">
                            <li class="list-item">
                                <p class="center"><span class="scene-icon active"></span></p>
                                <a href="javascript:void(0)">
                                    <span class="nav-label">项目缺陷展示</span>
                                </a>
                                <ul class="nav-second-level">

                                    <li>
                                        <a class="J_menuItem cur" href="<c:url value='/admin/horizon.do'/>">
                                            项目组缺陷横向比较
                                        </a>
                                    </li>
                                    <li>
                                        <a class="J_menuItem" href="<c:url value='/admin/protopic.do'/>">
                                            项目组缺陷纵向比较
                                        </a>
                                    </li>
                                    <li>
                                        <a class="J_menuItem" href="<c:url value='/admin/developer.do'/>">
                                            开发者缺陷比较
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="list-item">
                                <p class="center"><span class="config-icon"></span></p>
                                <a href="javascript:void(0)">
                                    <span class="nav-label">配置管理</span>
                                </a>
                                <ul class="nav-second-level">
                                    <li>
                                        <a class="J_menuItem" href="<c:url value='/admin/param/page'/> ">
                                            项目组配置
                                        </a>
                                    </li>
                                    <li>
                                        <a class="J_menuItem" href="<c:url value='/admin/scene/page'/>">
                                            系统
                                        </a>
                                    </li>
                                </ul>
                            </li>

                        </ul>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 通用页面 -->
<jsp:include page="common/common.jsp"/>
<script src="<c:url value='/js/index/index_main.js'/>"></script>
</body>
</html>