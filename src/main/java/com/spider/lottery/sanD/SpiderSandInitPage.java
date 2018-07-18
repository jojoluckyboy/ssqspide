package com.spider.lottery.sanD;

import com.spider.lottery.sanDservice.SandDankillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Description：
 * 抓取网上专家预测3红数据
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/17
 * Time：10:53
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
public class SpiderSandInitPage implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000).setTimeOut(8000).setCycleRetryTimes(3)
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Safari/537.36");



    @Override
    public Site getSite() {
        return site;
    }
/*
    @ExtractBy("//div[@class='postDesc']//span[@id='post-date']/text()")
    private String test;



    private int i;

    public int getDcount(){
        return this.i;

    }*/

    @Autowired
    private SandDankillService sandDankillService;

    public String qishuversion;
    public int pageNo;


    @Override
    public void process(Page page) {

        qishuversion = page.getHtml().xpath("//tbody[@id=\"info\"]").regex(".html\" target=\"_blank\">([0-9]{7})</a>").get();

        List<String> pageSize = page.getHtml().regex("class=\"gray\">[0-9]{1,2}</a>").replace("class=\"gray\">", "")
                .replace("</a>", "").all();
        if (pageSize.size() != 0) {
            pageNo = pageSize.size() + 1;
        } else {
            pageNo = 0;
        }


            }



    public void pushPage(String refUrl) throws Exception {


        try {
            Request request = new Request(refUrl);
            Thread.sleep(2000);
            Spider.create(this).thread(1)
                    .addRequest(request)
                    .addPipeline(new ConsolePipeline())
                    .run();
            Thread.interrupted();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public String returnQiShu(){

        return qishuversion;
    }
    public int returnPageNo(){

        return pageNo;
    }



    public static void main(String[] args) {


 /*       //设置POST请求
        Request request = new Request("http://expert.78500.cn/new/ssq/");
        //只有POST请求才可以添加附加参数
        request.setMethod(HttpConstant.Method.GET);*/

        System.out.println("时间戳" + System.currentTimeMillis());

        try {
            loop1:
            for (int qishu = 2018085; qishu < 2018086; qishu++) {
                Spider.create(new SpiderSandInitPage()).thread(5)
                        .addUrl("http://expert.78500.cn/new/3d/" + qishu)
                        .addPipeline(new ConsolePipeline())
                        .run();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}


