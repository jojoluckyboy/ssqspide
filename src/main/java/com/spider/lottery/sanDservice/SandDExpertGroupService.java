package com.spider.lottery.sanDservice;

import com.spider.lottery.mapper.ExpertGroupListMapper;
import com.spider.lottery.mapper.SanDExpertDataMapper;
import com.spider.lottery.mapper.SandDExpertGroupMapper;
import com.spider.lottery.pojo.ExpertGroupList;
import com.spider.lottery.pojo.SanDExpertData;
import com.spider.lottery.pojo.SandDExpertGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description：
 * 3D数据持久层服务
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/6
 * Time：14:56
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
@Transactional
public class SandDExpertGroupService {

    @Autowired
    private SandDExpertGroupMapper sandDExpertGroupMapper;

    @Autowired
    private SanDExpertDataMapper sanDExpertDataMapper;

    @Autowired
    private ExpertGroupListMapper expertGroupListMapper;


    /**
     * 插入3D历史数据
     *
     * @return 1
     * @throws Exception
     */
    public int insertSandDExpertGroup(SandDExpertGroup record) {

        return sandDExpertGroupMapper.insertSandDExpertGroup(record);


    }


    /**
     * 返回查询的数据
     *
     * @return 当前数据库中最近一次的期数
     * @throws Exception
     */
    public List<SandDExpertGroup> selectSandDExpertGroup() {

        return sandDExpertGroupMapper.selectSandDExpertGroup();


    }


    /**
     * 删除表中的数据
     *
     * @return 当前数据库中最近一次的期数
     * @throws Exception
     */
    public int deleteSandDExpertGroup() {

        return sandDExpertGroupMapper.deleteSandDExpertGroup();


    }


    /**
     * 统计表中的数据
     *
     * @return 当前数据库中最近一次的期数
     * @throws Exception
     */
    public int selectSanDkillbyCountExpert(@Param("expertname") String expertname) {

        return sandDExpertGroupMapper.selectSanDkillbyCountExpert(expertname);


    }

    /**
     * 统计表中的数据
     *
     * @return 当前数据库中最近一次的期数
     * @throws Exception
     */
    public int selectSanDkillbyCounttime(@Param("expertname") String expertname,@Param("issue") String issue) {

        return sandDExpertGroupMapper.selectSanDkillbyCounttime(expertname,issue);


    }


    /**
     * 查询表中的数据
     *
     * @return 当前数据库中最近一次的期数
     * @throws Exception
     */
    public List<SanDExpertData>  selectSanDExpertData() {

        return sanDExpertDataMapper.selectSanDExpertData();


    }


    public List<SanDExpertData>  selectSanDExpertDatabyexpert(@Param("columns") String columns) {

        return sanDExpertDataMapper.selectSanDExpertDatabyexpert(columns);


    }

/*
    public int insertSandexpertData(@Param("issue") String issue,@Param("columns1") String columns1,@Param("columns1_data") String columns1_data
            , @Param("columns2") String columns2,@Param("columns2_data") String columns2_data
            , @Param("columns3") String columns3,@Param("columns1_data") String columns3_data) {

        return sanDExpertDataMapper.insertSandexpertData(issue,columns1,columns1_data,columns2,columns2_data,columns3
                ,columns3_data);


    }*/
    public int insertSandexpertData(SanDExpertData record) {

        return sanDExpertDataMapper.insertSandexpertData(record);


}

    public List<ExpertGroupList> selectExpertGroupList() {

        return expertGroupListMapper.selectExpertGroupList();


    }

}
