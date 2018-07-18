package com.spider.lottery.sanD;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.spider.lottery.pojo.SanDJsonOriBean;
import com.spider.lottery.pojo.SandDankill;
import com.spider.lottery.sanDservice.SandDankillService;
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
public class SpiderInitExpertSanD implements PageProcessor {


    @Autowired
    private SandDankillService sandDankillService;
    public int d;


    public int getDcount(){
        return this.d;

    }


    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000).setTimeOut(8000).setCycleRetryTimes(3).setCharset("gb2312")
            .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
            .addHeader("Host", "expert.78500.cn")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Connection", "keep-alive")
            .addHeader("Pragma", "no-cache")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Safari/537.36")
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addHeader("Content-Type","text/html; charset=gb2312");


    @Override
    public Site getSite() {
        return site;
    }

    public  String qishuversion;




    @Override
    public void process(Page page) {


        SandDankill sandDankill = new SandDankill();

        site.getCharset();

        try {
            String oriJson = page.getHtml().regex("<body>(.*?)</body>").get().toString();
/*            String oriJson111 = new String(oriJson.getBytes(),"gbk");
            String oriJsonurl=oriJson.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            String urlStr = URLDecoder.decode(oriJsonurl, "utf-8");*/

            JSONObject jsonAry = JSONObject.parseObject(oriJson);
            String gsonStr = jsonAry.toString().replace("<b>", "").replace("</b>", "").replace("<br/>", "")
                    .replace("<b class='blue'>", "").replace("\\s", " ").replace("\n", " ");

            Gson gson = new Gson();
            SanDJsonOriBean sanDJsonOriBean = gson.fromJson(gsonStr, SanDJsonOriBean.class);


            List<SanDJsonOriBean.JsonSanDBean> jsonlist = sanDJsonOriBean.getList();

            if (jsonlist.size() == 0) {
                return;
            }
            loop3:
            for (int j = 0; j < jsonlist.size(); j++) {
                //专家
                String expertName = jsonlist.get(j).getNickname().toString();
                //独胆
                String singDan = jsonlist.get(j).getRet().getR2().toString();

                //五码组选
                String fiveDan = jsonlist.get(j).getRet().getR5().toString();
                //定3跨
                String kuaDan = jsonlist.get(j).getRet().getS11().toString();
                //定4和
                String sumDan = jsonlist.get(j).getRet().getS12().toString();


                sandDankill.setIssue(qishuversion);
                sandDankill.setExpertname(expertName);
                sandDankill.setSingDan(singDan);
                sandDankill.setFiveDanV(fiveDan);
                sandDankill.setSumDanV(sumDan);
                sandDankill.setKuaDanV(kuaDan);



                if (sandDankillService == null) {
                    System.out.println("数据服务空异常");
                    break loop3;
                }
                int sandDankillR = sandDankillService.insertSandDankill(sandDankill);

                if (sandDankillR == 1) {

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
            SpiderSandInitPage spiderSandInitPage = (SpiderSandInitPage) context.getBean("SpiderSandInitPage");

            loop1:
            for (int qishu = 2018108; qishu < 2018110; qishu++) {


                try {
                    spiderSandInitPage.pushPage("http://expert.78500.cn/new/3d/" + qishu);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                int pageNo = spiderSandInitPage.returnPageNo();
                qishuversion = spiderSandInitPage.returnQiShu();

                if (pageNo!=0){
                    loop2:
                    for(int i = 1; i < pageNo+1; i++){
                        Thread.sleep(2000);
                        Spider.create(this).thread(5)
                                .addUrl("http://expert.78500.cn/new/3d/"+qishu+"/?page=" + i + "&sort_num=0")
                                .addPipeline(new ConsolePipeline())
                                .run();
                    }

                }else {
                    Thread.sleep(2000);
                    Spider.create(this).thread(5)
                            .addUrl("http://expert.78500.cn/new/3d/"+qishu)
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


