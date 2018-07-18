package com.spider.lottery.sanDIncremTask;

import com.spider.lottery.pojo.SanDHistory;
import com.spider.lottery.pojo.SandDankill;
import com.spider.lottery.pretask.CalSanDHitTask;
import com.spider.lottery.pretask.CalSanDSkewnTask;
import com.spider.lottery.pretask.CalSanDanMACDTask;
import com.spider.lottery.sanDIncrem.SpiderIncremExpertSanD;
import com.spider.lottery.sanDIncrem.SpiderSanDIncrementHistory;
import com.spider.lottery.sanDservice.SanDHisMatchService;
import com.spider.lottery.sanDservice.SandDankillService;
import com.spider.lottery.sanDservice.SandService;
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


public class sanDIncremTask {
    @Autowired
    private SandService sandService;

    @Autowired
    private SpiderSanDIncrementHistory sanDIncrementHistory;

    @Autowired
    private SandDankillService sandDankillService;

    @Autowired
    private SpiderIncremExpertSanD spiderIncremExpertSanD;

    @Autowired
    private  SanDHisMatchService sanDHisMatchService;

    @Autowired
    private CalSanDHitTask calSanDHitTask;

    @Autowired
    private CalSanDSkewnTask calSanDSkewnTask;

    @Autowired
    private CalSanDanMACDTask calSanDanMACDTask;


    public void increment() throws Exception {
        System.out.println("调度执行SanD job1");
        sanDIncrementHistory.pushIcrementData();

        int IncreLine=sanDIncrementHistory.getIncreDcount();
        System.out.println("新增共计"+IncreLine+"条3D历史数据成功入库");

        System.out.println("调度执行SanD job2");
        spiderIncremExpertSanD.pushIcrementDataW();


        int IncreLinek=spiderIncremExpertSanD.getDcount();
        System.out.println("新增共计"+IncreLinek+"条3D专家预测数据成功入库");

        List<SandDankill> lastThrQishu = sanDHisMatchService.selectSanDkillHisMatchfirst();
        String qishuTr = lastThrQishu.get(0).getIssue();


        List<SanDHistory> lastQishu = sandService.selectSanDbLast();
        String qishuH = lastQishu.get(0).getIssue();

        if(Integer.valueOf(qishuTr)>Integer.valueOf(qishuH)){

            System.out.println("数据倒挂出错");

        } else{

                    //*记录导入了多少历史数据
            int Result=sanDHisMatchService.updateSanDMatch(qishuTr,qishuH);
            System.out.println("共计"+ Result+"条3D历史匹配数据成功入库");


        }

        System.out.println("调度执行SanD job3");
        calSanDHitTask.pushIcrementDataTask();
        int IncreLineHit=calSanDHitTask.getIncreDcount();
        System.out.println("处理"+IncreLineHit+"条3D命中维度计算");

        System.out.println("调度执行SanD job4");
        calSanDSkewnTask.pushIcrementDataTask();
        int IncreLineSkewn=calSanDHitTask.getIncreDcount();
        System.out.println("处理"+IncreLineSkewn+"条3D生克维度计算");

        System.out.println("调度执行SanD job5");
        calSanDanMACDTask.pushIcrementData();
        int IncreLineMACD=calSanDanMACDTask.getIncreDcount();
        System.out.println("处理"+IncreLineMACD+"条运气维度维度计算");



    }



}
