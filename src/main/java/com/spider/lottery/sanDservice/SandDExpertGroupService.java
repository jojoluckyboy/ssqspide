package com.spider.lottery.sanDservice;

import com.spider.lottery.mapper.SandDExpertGroupMapper;
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


}
