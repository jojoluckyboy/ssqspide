package com.spider.lottery.ssq;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.spider.lottery.pojo.JsonOriBean;
import com.spider.lottery.pojo.SsqThred;
import com.spider.lottery.ssqservice.SsqThredService;
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
 * 抓取网上专家预测3红数据
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/17
 * Time：10:53
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
public class SpiderExpertThrRed implements PageProcessor {



    @Autowired
    private SsqThredService ssqThredService;
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



    @Override
    public void process(Page page) {

                   SsqThred ssqThred = new SsqThred();

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
                                String refUrl = jsonlist.get(j).getUrl().toString();
                                //红3胆
                                String threeRedD = jsonlist.get(j).getRet().getR2().toString();
                                //红5胆
                                String fiveRedD = jsonlist.get(j).getRet().getR3().toString();
                                if (fiveRedD.equals("--")) {
                                    fiveRedD = fiveRedD;

                                } else {
                                    String fiveRedD1 = fiveRedD.substring(0, 8);
                                    String fiveRedD2 = fiveRedD.substring(8, 13);
                                    fiveRedD = fiveRedD1 + " " + fiveRedD2;
                                }

                                //蓝3胆
                                String threeBlueD = jsonlist.get(j).getRet().getR5().toString();
                                //杀1红
                                String RedK = jsonlist.get(j).getRet().getR7().toString();
                                //杀3红
                                String threeRedK = jsonlist.get(j).getRet().getR8().toString();

                                //杀6红
                                String sixRedK = jsonlist.get(j).getRet().getR9().toString();

                                if (sixRedK.equals("--")) {
                                    sixRedK = sixRedK;

                                } else {
                                    String sixRedK1 = sixRedK.substring(0, 8);
                                    String sixRedK2 = sixRedK.substring(8, 16);
                                    sixRedK = sixRedK1 + " " + sixRedK2;
                                }

                                //杀3蓝
                                String threeBlueK = jsonlist.get(j).getRet().getS1().toString();
                                ssqThred.setIssue(qishuversion);
                                ssqThred.setExpertname(expertName);
                                ssqThred.setRedthree(threeRedD);
                                ssqThred.setUrl(refUrl);
                                ssqThred.setRedfive(fiveRedD);
                                ssqThred.setBluethree(threeBlueD);
                                ssqThred.setRedk(RedK);
                                ssqThred.setThreeredk(threeRedK);
                                ssqThred.setSixredk(sixRedK);
                                ssqThred.setBluethreek(threeBlueK);

                                if (ssqThredService == null) {
                                    System.out.println("数据服务空异常");
                                    break loop3;
                                }
                                int ssqthrR = ssqThredService.insertSSQThred(ssqThred);

                                if (ssqthrR == 1) {

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
            for (int qishu = 2017097; qishu < 2017100; qishu++) {


                try {
                    spiderthrpage.pushPage("http://expert.78500.cn/new/ssq/" + qishu);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                int pageNo = spiderthrpage.returnPageNo();
                qishuversion = spiderthrpage.returnQiShu();
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


        /*try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
            SpiderThrRedPage spiderthrpage = (SpiderThrRedPage) context.getBean("SpiderThrRedPage");

            loop1:
            for (int qishu = 2017085; qishu < 2017089; qishu++) {


                try {
                    spiderthrpage.pushPage("http://expert.78500.cn/new/ssq/" + qishu);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                int pageNo = spiderthrpage.returnPageNo();

                if (pageNo!=0){
                    loop2:
                    for(int i = 1; i < pageNo+1; i++){

                        Spider.create(new SpiderExpertThrRed()).thread(5)
                                .addUrl("http://expert.78500.cn/new/ssq/"+qishu+"/?page=" + i + "&sort_num=0")
                                .addPipeline(new ConsolePipeline())
                                .run();
                    }

                }else {
                    Spider.create(new SpiderExpertThrRed()).thread(5)
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


