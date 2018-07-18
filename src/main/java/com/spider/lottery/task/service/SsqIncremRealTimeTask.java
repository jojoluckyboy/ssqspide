package com.spider.lottery.task.service;

import com.spider.lottery.pojo.SsqRealTime;
import com.spider.lottery.pojo.SsqThred;
import com.spider.lottery.ssq.SpiderSsqRealTimeStic;
import com.spider.lottery.ssqservice.SsqCalService;
import com.spider.lottery.ssqservice.SsqService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description：
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/13
 * Time：10:05
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */


public class SsqIncremRealTimeTask {

    @Autowired
    private SsqService ssqService;

    @Autowired
    private SpiderSsqRealTimeStic spiderSsqRealTimeStic;

    @Autowired
    private SsqCalService ssqCalService ;


    public void increment() throws Exception {

        int startq ;
        int endq ;

        List<SsqThred> ssqcalRc0= ssqCalService.selectSSQThrDbLast();
        endq = Integer.parseInt(ssqcalRc0.get(0).getIssue());

        List<SsqRealTime> ssqcalRc01= ssqCalService.selectSSQRealTDbLast();
        startq = Integer.parseInt(ssqcalRc01.get(0).getIssue());
/*        startq = 2017133;
        endq = 2017135;*/
        System.out.println("调度执行job3");
        spiderSsqRealTimeStic.pushData(startq,endq);

        int IncreLine=spiderSsqRealTimeStic.getDcount();
        System.out.println("新增共计"+IncreLine+"条实时统计数据成功入库");



    }






}

