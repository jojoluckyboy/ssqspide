package com.spider.lottery.task.service;

import com.spider.lottery.precodition.task.CalErrModeTask;
import com.spider.lottery.precodition.task.CalHitTask;
import com.spider.lottery.ssq.SpiderIncrementHistory;
import com.spider.lottery.ssq.SpiderIncrementThred;
import com.spider.lottery.ssqservice.SsqCalService;
import com.spider.lottery.ssqservice.SsqService;
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


public class SsqIncremTask {
    @Autowired
    private SsqService ssqService;



    @Autowired
    private SsqCalService ssqCalService ;


    @Autowired
    private SpiderIncrementHistory spiderIncrementHistory;

    @Autowired
    private SpiderIncrementThred spiderIncrementThred;


    @Autowired
    private CalHitTask calhisttask;

    @Autowired
    private CalErrModeTask calerrmodetask;

    /*     @RequestMapping(value = "/admin/incrementtask.do", produces = "application/json;charset=utf-8")
         @ResponseBody*/
    public void increment() throws Exception {
        System.out.println("调度执行job1");
        spiderIncrementHistory.pushIcrementData();

        int IncreLine=spiderIncrementHistory.getIncreDcount();
        System.out.println("新增共计"+IncreLine+"条历史数据成功入库");



    }





}

