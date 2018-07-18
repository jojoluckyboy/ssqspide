<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="header clearfix index-top">
    <span class="logo fl">
        <img src="<c:url value='/img/logo.png'/>" alt="" class="fl"/>
    </span>
    <c:if test="${param.ref != 'client'}">
        <div class="fr">
            <c:if test="${!empty SESSION_USER_INFO}">
                <div class="login-after login-info">
                    <span class="user-name">
                        <img src="<c:url value='/img/avatar.png'/> " class="avatar" alt="头像">
                        <a href="javascript:void(0);">${empty SESSION_USER_INFO.userName?SESSION_USER_INFO.loginName:SESSION_USER_INFO.userName}</a>
                    </span>
                    <a href="javascript:void(0);" class="log-out pos-rel js-logout">退出 <i class="icon-out"></i></a>
                </div>
            </c:if>
            <c:if test="${empty SESSION_USER_INFO}">
                <div class="entry">
                    <a href="<c:url value='/cas/login'/>" class="login-btn">登录</a>
                </div>
            </c:if>
        </div>
    </c:if>
</div>