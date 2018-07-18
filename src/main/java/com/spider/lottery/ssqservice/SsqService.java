package com.spider.lottery.ssqservice;

import com.spider.lottery.mapper.SsqHistoryMapper;
import com.spider.lottery.mapper.SsqRealTimeMapper;
import com.spider.lottery.pojo.SsqHistory;
import com.spider.lottery.pojo.SsqRealTime;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class SsqService {

    @Autowired
    private SsqHistoryMapper SsqHistoryMapper;

    @Autowired
    private SsqRealTimeMapper SsqRealTimeMapper;
    /**
     * 插入双色球历史数据
     *
     * @return 1
     * @throws Exception
     */
    public int insertSSQHistory(SsqHistory record) {

        return SsqHistoryMapper.insertSSQHistory(record);


    }

    /**
     * 返回当前数据库中最后一行数据
     *
     * @return 当前数据库中最近一次的期数
     * @throws Exception
     */
    public List<SsqHistory> selectSSQDbLast() {

        return SsqHistoryMapper.selectSSQDbLast();


    }

    /**
     * 返回当前数据库中最后一行数据d
     *
     * @return 当前数据库中最近一次的记录
     * @throws Exception
     */
    public List<SsqHistory> selectSSQHDbLast(@Param("issue") String issue) {

        return SsqHistoryMapper.selectSSQHDbLast(issue);


    }

    /**
     * 插入双色球实时统计排序
     *
     * @return 1
     * @throws Exception
     */
    public int insertSSQRealTime(SsqRealTime record) {

        return SsqRealTimeMapper.insertSSQRealTime(record);


    }


}
