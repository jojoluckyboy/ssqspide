package com.spider.lottery.sanDservice;

import com.spider.lottery.mapper.SandDankillMapper;
import com.spider.lottery.pojo.SandDankill;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * 双色球数据持久层服务
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/6
 * Time：14:56
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
public class SanDHisMatchService {

    @Autowired
    private SandDankillMapper sandDankillMapper;


    /**
     * 插入双色球历史匹配数据
     *
     * @return 1
     * @throws Exception
     */
    public int updateSanDMatch(@Param("time1") String time1, @Param("time2")String time2) {

        return sandDankillMapper.updateSanDMatch(time1,time2);


    }

    /**
     * 返回3D专家预测数据未匹配历史记录
     *
     * @return 当前数据库中最近一次的期数
     * @throws Exception
     */
    public List<SandDankill> selectSanDkillHisMatchfirst() {

        return sandDankillMapper.selectSanDkillHisMatchfirst();


    }





}
