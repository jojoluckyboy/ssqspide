package com.spider.lottery.ssqservice;

import com.spider.lottery.mapper.SsqThredMapper;
import com.spider.lottery.pojo.SsqThred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description：
 * 双色球三红预测数据持久层服务
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/6
 * Time：14:56
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
@Transactional
public class SsqThredService {

    @Autowired
    private SsqThredMapper SsqThredMapper;
    /**
     * 插入双色球历史数据
     *
     * @return 1
     * @throws Exception
     */
    public int insertSSQThred(SsqThred record) {

        return SsqThredMapper.insertSSQThred(record);


    }


    /**
     * 返回当前数据库中预测三胆最后一行数据
     *
     * @return 当前数据库中最近一次的期数
     * @throws Exception
     */


    /**
     * 返回当前数据库中最后一行数据
     *
     * @return 当前数据库中最近一次的期数
     * @throws Exception
     */
    public List<SsqThred> selectSSQThrDbLast() {

        return SsqThredMapper.selectSSQThrDbLast();


    }


}
