package com.spider.lottery.task.service;

import com.spider.lottery.pojo.SsqHistory;
import com.spider.lottery.pojo.SsqThred;
import com.spider.lottery.precodition.task.*;
import com.spider.lottery.ssq.SpiderIncrementThred;
import com.spider.lottery.ssqservice.SsqCalService;
import com.spider.lottery.ssqservice.SsqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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


public class SsqThreIncremTask {
    @Autowired
    private SsqService ssqService;

    @Autowired
    private SpiderIncrementThred spiderIncrementThred;

    @Autowired
    private CalHitTask calhisttask;

    @Autowired
    private CalPercModeTask calPercModeTask;

    @Autowired
    private CalPercTrendTask calPercTrendTask;

    @Autowired
    private CalErrModeTask calerrmodetask;

    @Autowired
    private CalMLTask calMLTask;

    @Autowired
    private CalPerformScoreTask calPerformScoreTask;

    @Autowired
    private SsqCalService ssqCalService ;



    public void increment() throws Exception {

        System.out.println("调度执行job2");
        spiderIncrementThred.pushIcrementDataW();

        int IncreLine=spiderIncrementThred.getDcount();
        System.out.println("新增共计"+IncreLine+"条新增数据成功入库");

        int issueHitV;
        int issueHV;

        List<SsqThred> ssqcalRc= ssqCalService.selectRcByhismaxpd(1000);
        if (ssqcalRc.size()==0){
            List<SsqThred> ssqcalRc0= ssqCalService.selectSSQThrDbLast();
            issueHitV = Integer.parseInt(ssqcalRc0.get(0).getIssue());
        }else {
            issueHitV = Integer.parseInt(ssqcalRc.get(0).getIssue());
        }

        List<SsqHistory> ssqhistory = ssqService.selectSSQDbLast();
        issueHV = Integer.parseInt(ssqhistory.get(ssqhistory.size()-1).getIssue());

        if (issueHitV<=issueHV){

            calhisttask.pushIcrementData();
            int hitMode=calhisttask.getIncreDcount();
            System.out.println("共计"+ hitMode+"条历史数据更新");

            calerrmodetask.pushIcrementData();
            int errorMode=calerrmodetask.getIncreDcount();
            System.out.println("共计" + errorMode + "条历史数据匹配错误模式");

            calPercModeTask.pushIcrementData();
            int percMode=calPercModeTask.getIncreDcount();
            System.out.println("共计" + percMode + "条历史数据匹配连对概率");

            calPercTrendTask.pushIcrementData();
            int percTrend=calPercTrendTask.getIncreDcount()/6;
            int percTrend5=calPercTrendTask.getIncre5Dcount();
            int percTrend10=calPercTrendTask.getIncre10Dcount();
            int percTrend20=calPercTrendTask.getIncre20Dcount();
            int percTrend30=calPercTrendTask.getIncre30Dcount();
            int percTrend50=calPercTrendTask.getIncre50Dcount();
            int percTrend100=calPercTrendTask.getIncre100Dcount();

            System.out.println("共计" + percTrend + "条历史数据匹配近期连对概率");
            System.out.println("其中" + percTrend5 + "条历史数据匹配近期连对5期概率");
            System.out.println("其中" + percTrend10 + "条历史数据匹配近期连对10期概率");
            System.out.println("其中" + percTrend20 + "条历史数据匹配近期连对20期概率");
            System.out.println("其中" + percTrend30 + "条历史数据匹配近期连对30期概率");
            System.out.println("其中" + percTrend50 + "条历史数据匹配近期连对50期概率");
            System.out.println("其中" + percTrend100 + "条历史数据匹配近期连对100期概率");

            calMLTask.pushIcrementData();
            int percML=calMLTask.getIncreDcount();
            System.out.println("共计" + percML + "条历史数据更新了机器学习模型数据");

            calPerformScoreTask.pushIcrementData();
            int percScore=calPerformScoreTask.getIncreDcount();
            System.out.println("共计" + percScore + "条历史数据匹配表现分值");

        }

    }

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        SpiderIncrementThred spiderIncrementThred = (SpiderIncrementThred) context.getBean("SpiderIncrementThred");





        try {
            spiderIncrementThred.pushIcrementDataW();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int IncreLine=spiderIncrementThred.getDcount();
        System.out.println("新增共计"+IncreLine+"条新增数据成功入库");

    }


}
