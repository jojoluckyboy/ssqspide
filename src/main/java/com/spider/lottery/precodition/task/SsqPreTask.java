package com.spider.lottery.precodition.task;

import com.spider.lottery.ssq.SpiderHistory;
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
public class SsqPreTask {

/*    @Autowired
    private SsqService ssqService;*/



    /**
     * 前置准备导入历史数据
     *
     * @return 0 or 1
     * @throws Exception
     */


    public static void main(String[] args) {

       // SpiderHistory spiderHistory = (SpiderHistory) SpringContextUtil.getApplicationContext().getBean("SpiderHistory");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        SpiderHistory spiderHistory = (SpiderHistory) context.getBean("SpiderHistory");
        try {
            spiderHistory.pushData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*记录导入了多少历史数据*/
        int Result=spiderHistory.getDcount();
        System.out.println("共计"+ Result+"条历史数据成功入库");
/*        //设置POST请求
        Request request = new Request("http://kaijiang.78500.cn/ssq/");
        //只有POST请求才可以添加附加参数
        request.setMethod(HttpConstant.Method.POST);
        //设置POST参数

        Map<String, Object> Params = new HashMap<String, Object>();
        Params.put("startqi", "2014008");
        Params.put("endqi", "2016001");
        Params.put("year", "2003");
        Params.put("action", "range");

        try {
            request.setRequestBody(HttpRequestBody.form(Params, "utf-8"));
            Spider.create(spiderHistory).thread(1)
                    .addRequest(request)
                    .addPipeline(new ConsolePipeline())
                    .run();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

/*        if (result == 0) {

            System.out.println("导入数据完成");

        } else {
            System.out.println("导入数据过程错误");
        }*/
    }

}
