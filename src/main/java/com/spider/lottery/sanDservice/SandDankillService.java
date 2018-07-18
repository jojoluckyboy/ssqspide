package com.spider.lottery.sanDservice;

import com.spider.lottery.mapper.SandDankillMapper;
import com.spider.lottery.pojo.SandDankill;
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
public class SandDankillService {

    @Autowired
    private SandDankillMapper sandDankillMapper;

    /**
     * 插入3D历史数据
     *
     * @return 1
     * @throws Exception
     */
    public int insertSandDankill(SandDankill record) {

        return sandDankillMapper.insertSandDankill(record);


    }


    /**
     * 返回3D历史数据当前数据库中最后一行数据
     *
     * @return 当前数据库中最近一次的期数
     * @throws Exception
     */
    public List<SandDankill> selectSanDkillDbLast() {

        return sandDankillMapper.selectSanDkillDbLast();


    }


}
