package com.spider.lottery.mapper;

import com.spider.lottery.pojo.SanDHistory;

import java.util.List;

public interface SanDHistoryMapper {
    int deleteHistoryByPrimaryKey(String issue);

    int insertSanDHistory(SanDHistory record);
    List<SanDHistory> selectSanDbLast();
    List<SanDHistory> selectSanDhisSpice();

}