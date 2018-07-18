package com.spider.lottery.mapper;

import com.spider.lottery.pojo.SsqRealTime;

import java.util.List;

public interface SsqRealTimeMapper {

    int insertSSQRealTime(SsqRealTime record);

    List<SsqRealTime> selectSSQRealTDbLast();
}