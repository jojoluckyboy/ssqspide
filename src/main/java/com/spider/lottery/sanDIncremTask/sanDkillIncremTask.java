package com.spider.lottery.sanDIncremTask;

import com.spider.lottery.sanDIncrem.SpiderIncremExpertSanD;
import com.spider.lottery.sanDservice.SandDankillService;
import com.spider.lottery.sanDservice.SandService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description：
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/13
 * Time：10:05
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */


public class sanDkillIncremTask {
    @Autowired
    private SandService sandService;

    @Autowired
    private SandDankillService sandDankillService;

    @Autowired
    private SpiderIncremExpertSanD spiderIncremExpertSanD;

    public void increment() throws Exception {
        System.out.println("调度执行SanD job2");
        spiderIncremExpertSanD.pushIcrementDataW();

        int IncreLine=spiderIncremExpertSanD.getDcount();
        System.out.println("新增共计"+IncreLine+"条3D专家预测数据成功入库");



    }





}

