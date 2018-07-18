package com.spider.lottery.mapper;

import com.spider.lottery.pojo.SandDankill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SandDankillMapper {
    int deleteSandDankillByPrimaryKey(String issue);

    int insertSandDankill(SandDankill record);

    List<SandDankill> selectSanDkillDbLast();

    int updateSanDMatch(@Param("time1") String time1, @Param("time2")String time2);

    List<SandDankill> selectSanDkillHisMatchfirst();


}