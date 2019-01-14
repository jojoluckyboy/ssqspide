package com.spider.lottery.mapper;

import com.spider.lottery.pojo.SanDExpertData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SanDExpertDataMapper {
    List<SanDExpertData> selectSanDExpertData();
    List<SanDExpertData> selectSanDExpertDatabyexpert(@Param("columns") String columns);
//    int insertSandexpertData(@Param("issue") String issue,@Param("columns1") String columns1,@Param("columns1_data") String columns1_data
//    , @Param("columns2") String columns2,@Param("columns2_data") String columns2_data
//            , @Param("columns3") String columns3,@Param("columns1_data") String columns3_data);

    int insertSandexpertData(SanDExpertData record);
}