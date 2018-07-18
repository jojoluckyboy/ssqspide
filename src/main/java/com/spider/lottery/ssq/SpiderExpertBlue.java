package com.spider.lottery.ssq;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.spider.lottery.pojo.JsonOriBean;
import com.spider.lottery.pojo.SsqBlue;
import com.spider.lottery.ssqservice.SsqBlueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Description：
 * 抓取网上专家预测蓝球数据
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/17
 * Time：10:53
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
public class SpiderExpertBlue implements PageProcessor {


    @Autowired
    private SsqBlueService ssqBlueService;
    public int d;


    public int getDcount(){
        return this.d;

    }


    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000).setTimeOut(8000).setCycleRetryTimes(3)
          	.addHeader("accept", "application/json, text/javascript")
		    .addHeader("Accept-Encoding", "gzip, deflate, sdch,br")
		    .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
		    .addHeader("Host", "expert.78500.cn")
		    .addHeader("Cache-Control", "no-cache")
		    .addHeader("Connection", "keep-alive")
	        .addHeader("Pragma", "no-cache")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
	        .addHeader("X-Requested-With", "XMLHttpRequest");


    @Override
    public Site getSite() {
        return site;
    }

    public  String qishuversion;
    public  String blueR;



    @Override
    public void process(Page page) {

                   SsqBlue ssqBlue = new SsqBlue();

                    try {
                        String oriJson = page.getHtml().regex("<body>(.*?)</body>").get();
                        JSONObject jsonAry = JSONObject.parseObject(oriJson);
                        String gsonStr = jsonAry.toString().replace("<b>", "").replace("</b>", "").replace("<br/>", "")
                                .replace("<b class='blue'>", "").replace("\\s", " ").replace("\n", " ");

                        Gson gson = new Gson();
                        JsonOriBean jsoribean = gson.fromJson(gsonStr, JsonOriBean.class);


                        List<JsonOriBean.JsonThredBean> jsonlist = jsoribean.getList();

                        if (jsonlist.size() == 0) {
                            return;
                        }
                            loop3:
                            for (int j = 0; j < jsonlist.size(); j++) {
                                String expertName = jsonlist.get(j).getNickname().toString();
                                //蓝3胆
                                String threeBlueD = jsonlist.get(j).getRet().getR5().toString();

                                //杀1蓝
                                String BlueK = jsonlist.get(j).getRet().getS0().toString();
                                //杀3蓝
                                String threeBlueK = jsonlist.get(j).getRet().getS1().toString();


                                ssqBlue.setIssue(qishuversion);
                                ssqBlue.setExpertname(expertName);
                                ssqBlue.setBlue(blueR);
                                ssqBlue.setBluethree(threeBlueD);
                                ssqBlue.setBluekillone(BlueK);
                                ssqBlue.setBluekillthree(threeBlueK);


                                if (ssqBlueService == null) {
                                    System.out.println("数据服务空异常");
                                    break loop3;
                                }
                                int ssqBlueR = ssqBlueService.insertSSQBlue(ssqBlue);

                                if (ssqBlueR == 1) {

                                    d = d + 1;

                                }

                            }

                        } catch(Exception e){
                            e.printStackTrace();
                        }


            }


    public void pushData(){

        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
            SpiderThrRedPage spiderthrpage = (SpiderThrRedPage) context.getBean("SpiderThrRedPage");

            loop1:
            for (int qishu = 2017001; qishu < 2017155; qishu++) {


                try {
                    spiderthrpage.pushPage("http://expert.78500.cn/new/ssq/" + qishu);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                int pageNo = spiderthrpage.returnPageNo();
                qishuversion = spiderthrpage.returnQiShu();
                blueR = spiderthrpage.returnBlue();
                if (pageNo!=0){
                    loop2:
                    for(int i = 1; i < pageNo+1; i++){
                        Thread.sleep(2000);
                        Spider.create(this).thread(5)
                                .addUrl("http://expert.78500.cn/new/ssq/"+qishu+"/?page=" + i + "&sort_num=0")
                                .addPipeline(new ConsolePipeline())
                                .run();
                    }

                }else {
                    Thread.sleep(2000);
                    Spider.create(this).thread(5)
                            .addUrl("http://expert.78500.cn/new/ssq/"+qishu)
                            .addPipeline(new ConsolePipeline())
                            .run();

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


     /*   try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
            SpiderThrRedPage spiderthrpage = (SpiderThrRedPage) context.getBean("SpiderThrRedPage");

            loop1:
            for (int qishu = 2014034; qishu < 2014153; qishu++) {


                try {
                    spiderthrpage.pushPage("http://expert.78500.cn/new/ssq/" + qishu);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                int pageNo = spiderthrpage.returnPageNo();

                if (pageNo!=0){
                    loop2:
                    for(int i = 1; i < pageNo+1; i++){

                        Spider.create(new SpiderExpertBlue()).thread(5)
                                .addUrl("http://expert.78500.cn/new/ssq/"+qishu+"/?page=" + i + "&sort_num=0")
                                .addPipeline(new ConsolePipeline())
                                .run();
                    }

                }else {
                    Spider.create(new SpiderExpertBlue()).thread(5)
                            .addUrl("http://expert.78500.cn/new/ssq/"+qishu)
                            .addPipeline(new ConsolePipeline())
                            .run();

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }


}


