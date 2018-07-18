package com.spider.lottery.controller.admin;

import com.spider.lottery.pojo.SsqHistory;
import com.spider.lottery.ssq.SpiderHistory;
import com.spider.lottery.ssqservice.SsqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description：
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/10
 * Time：11:27
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Controller
public class ActivityController {
    @Autowired
    private SsqService ssqService;

    @Autowired
    private SpiderHistory spiderHistory;

    /**
     * 查询固定时期各项目缺陷
     *
     * @return json格式的查询数据
     * @throws Exception
     */
    @RequestMapping(value = "/admin/gettotalpro.do", produces = "application/json;charset=utf-8")
    @ResponseBody
    public int getResult() throws Exception {

        SsqHistory ssqhistory = new SsqHistory();
        ssqhistory.setIssue("2016001");
        ssqhistory.setDate("2016-03-24");
        ssqhistory.setRed("01 02 03 04 05 06");
        ssqhistory.setRed1("01");
        ssqhistory.setRed2("02");
        ssqhistory.setRed3("03");
        ssqhistory.setRed4("04");
        ssqhistory.setRed5("05");
        ssqhistory.setRed6("06");
        ssqhistory.setBlue("07");

        System.out.println(ssqhistory);


        int ssqhisR = ssqService.insertSSQHistory(ssqhistory);
        System.out.println(ssqhisR);
        return ssqhisR;

    }

    @RequestMapping(value = "/admin/start.do", produces = "application/json;charset=utf-8")
    @ResponseBody
    public int start() throws Exception {

        spiderHistory.pushData();
 /*       //设置POST请求
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
        return 0;
    }
}