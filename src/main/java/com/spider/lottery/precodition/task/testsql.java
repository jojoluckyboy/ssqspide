package com.spider.lottery.precodition.task;

import com.spider.lottery.pojo.SsqThred;
import com.spider.lottery.ssqservice.SsqCalService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Description：
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/12/11
 * Time：14:29
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
public class testsql {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        SsqCalService ssqCalService = (SsqCalService) context.getBean("SsqCalService");
        int expID0 = 210344;
        String expertname = "一码当先";

        List<SsqThred> findbyexpert = ssqCalService.selectIDbyExplimit(expID0, expertname);

        System.out.println("测试");
    }

}
