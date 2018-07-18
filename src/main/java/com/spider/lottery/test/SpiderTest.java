package com.spider.lottery.test;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.spider.lottery.pojo.JsonOriBean;
import com.spider.lottery.pojo.SsqBlue;
import com.spider.lottery.ssq.SpiderExpertBlue;
import com.spider.lottery.ssq.SpiderThrRedPage;
import com.spider.lottery.ssqservice.SsqBlueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description：
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2018/2/12
 * Time：17:18
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
/*@RunWith(Spring.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-lottery.xml")
@Transactional
public class SpiderTest implements PageProcessor {

    @Resource
    private SpiderThrRedPage spiderthrpage;



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
                    ssqBlue.setIssue(qishuversion);
                    ssqBlue.setExpertname(expertName);
//                                ssqBlue.setRedthree(threeRedD);
//                                ssqBlue.setUrl(refUrl);
//                                ssqBlue.setRedfive(fiveRedD);
//                                ssqBlue.setBluethree(threeBlueD);
//                                ssqBlue.setRedk(RedK);
//                                ssqBlue.setThreeredk(threeRedK);
//                                ssqBlue.setSixredk(sixRedK);
//                                ssqBlue.setBluethreek(threeBlueK);

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


    @Test
    public void testinsert() {
        try {
        loop1:
        for (int qishu = 2014001; qishu < 2014003; qishu++) {


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
    }
    }
}
