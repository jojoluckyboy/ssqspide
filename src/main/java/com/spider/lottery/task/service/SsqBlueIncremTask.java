package com.spider.lottery.task.service;

import com.spider.lottery.ssq.SpiderIncrementThred;
import com.spider.lottery.ssqservice.SsqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description：
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/13
 * Time：10:05
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */


public class SsqBlueIncremTask {
    @Autowired
    private SsqService ssqService;

    @Autowired
    private SpiderIncrementThred spiderIncrementThred;




    public void increment() throws Exception {

        System.out.println("调度执行job blue");
        spiderIncrementThred.pushIcrementDataW();

        int IncreLine=spiderIncrementThred.getDcount();
        System.out.println("新增共计"+IncreLine+"条新增数据成功入库");



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
