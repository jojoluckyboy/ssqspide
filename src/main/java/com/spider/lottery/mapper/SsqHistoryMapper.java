package com.spider.lottery.mapper;

import com.spider.lottery.pojo.SsqHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SsqHistoryMapper {
    int deleteHistoryByPrimaryKey(String issue);

    int insertSSQHistory(SsqHistory record);

    SsqHistory selectSSQHistoryByPrimaryKey(String issue);

    List<SsqHistory> selectSSQHistoryAll();

    int updateHistoryByPrimaryKey (SsqHistory record);

    List<SsqHistory> selectSSQDbLast();

    List<SsqHistory> selectSSQHDbLast(@Param("issue") String issue);

    List<SsqHistory> selectbluebyissue(@Param("issue") String issue);



}