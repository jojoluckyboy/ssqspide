package com.spider.lottery.mapper;

import com.spider.lottery.pojo.SanDHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SanDHistoryMapper {
    int deleteHistoryByPrimaryKey(String issue);

    int insertSanDHistory(SanDHistory record);
    List<SanDHistory> selectSanDbLast();
    List<SanDHistory> selectSanDhisSpice();
    List<SanDHistory> selectSanDhisByIssue(@Param("issue") String issue);
    List<SanDHistory> selectSanDhisById(@Param("id") int id);

    int updateSanDById(@Param("duplicateDan") int duplicateDan,@Param("oposeDan") int oposeDan,
                      @Param("compDan") int compDan,@Param("secutiveDan") int secutiveDan,
                      @Param("nextPlusDan") int nextPlusDan,@Param("id") int id);


}