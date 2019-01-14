package com.spider.lottery.pretask;

import com.spider.lottery.sanD.SpideSanDHistory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Description：
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/17
 * Time：10:51
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
public class sanDhisTask {



    /**
     * 前置准备导入专家预测数据
     *
     * @return 0 or 1
     * @throws Exception
     */


    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        SpideSanDHistory spideSanDHistory = (SpideSanDHistory) context.getBean("SpideSanDHistory");
/*        try {
            spideSanDHistory.pushData();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {
            spideSanDHistory.updateCLD("2018001", "2018309");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //*记录导入了多少历史数据*//**//*
        int Result=spideSanDHistory.getDcount();
        System.out.println("共计"+ Result+"条历史数据成功入库");

    }


}
