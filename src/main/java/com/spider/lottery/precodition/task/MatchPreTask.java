package com.spider.lottery.precodition.task;

import com.spider.lottery.ssqservice.SsqThredMatchService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Description：
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/12
 * Time：9:55
 * Copyright© 2003-2016 Zhejiang huixin technology company
 *
 */

@Component
public class MatchPreTask {

/*    @Autowired
    private SsqService ssqService;*/



    /**
     * 前置准备导入历史数据
     *
     * @return 0 or 1
     * @throws Exception
     */

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        SsqThredMatchService ssqThredMatchService = (SsqThredMatchService) context.getBean("SsqThredMatchService");

        /*记录导入了多少历史数据*/
        int Result=ssqThredMatchService.updateSSQThredMatch("2017002","2017100");
        System.out.println("共计"+ Result+"条历史数据成功入库");

    }

}
