package com.spider.lottery.precodition.task;

import com.spider.lottery.ssq.SpiderExpertBlue;
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
public class SsqExpertThrRed {



    /**
     * 前置准备导入专家预测数据
     *
     * @return 0 or 1
     * @throws Exception
     */


    public static void main(String[] args) {

        //预先导入红球预测和篮球预测的号码
/*        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        SpiderExpertThrRed spiderExpertThrRed = (SpiderExpertThrRed) context.getBean("SpiderExpertThrRed");
        try {
            spiderExpertThrRed.pushData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*//*记录导入了多少历史数据*//**//*
        int Result=spiderExpertThrRed.getDcount();
        System.out.println("共计"+ Result+"条历史数据成功入库");*/

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        SpiderExpertBlue spiderExpertBlue = (SpiderExpertBlue) context.getBean("SpiderExpertBlue");
        try {
            spiderExpertBlue.pushData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //*记录导入了多少历史数据*//**//*
        int Result=spiderExpertBlue.getDcount();
        System.out.println("共计"+ Result+"条历史数据成功入库");

    }


}
