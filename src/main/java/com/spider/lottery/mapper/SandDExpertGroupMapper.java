package com.spider.lottery.mapper;

import com.spider.lottery.pojo.SandDExpertGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SandDExpertGroupMapper {
    int deleteSandDExpertGroup();
    List<SandDExpertGroup> selectSandDExpertGroup();
    int selectSanDkillbyCountExpert(@Param("expertname") String expertname);
    int selectSanDkillbyCounttime(@Param("expertname") String expertname,@Param("issue") String issue);
    int insertSandDExpertGroup(SandDExpertGroup record);


}