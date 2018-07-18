package com.spider.lottery.ssq;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.spider.lottery.pojo.JsonOriBean;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.List;

/**
 * Description：
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/8/7
 * Time：15:58
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service

public class testajax implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
            .addHeader("accept", "application/json, text/javascript")
            .addHeader("Accept-Encoding", "gzip, deflate, sdch")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Host", "expert.78500.cn")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Connection", "keep-alive")
            .addHeader("Pragma", "no-cache")
            .addHeader("Referer", "http://expert.78500.cn/new/ssq/2017085/")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
            //.addHeader("Cookie", "UM_distinctid=15c6837d7d058-0223650d8dd98a-414a0229-13c680-15c6837d7d186; bdshare_firstime=1496397133444; CBBSESSID=uej380i94sr4c6ogq1aehj0sp3; CNZZDATA3497249=cnzz_eid%3D1015688222-1496393933-http%253A%252F%252Fwww.78500.cn%252F%26ntime%3D1502792935")
            .addHeader("X-Requested-With", "XMLHttpRequest");


    public void process(Page page) {


        //模拟post请求
                  try{
                      //模拟post请求
                      Request req = new Request();
                      String setUrl = "http://expert.78500.cn/new/ssq/2017085/?page=3&sort_num=0";
                   req.addHeader("accept", "application/json, text/javascript");
                      req.addHeader("Accept-Encoding", "gzip, deflate, sdch,br");
                      req.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
                      req.addHeader("Host", "expert.78500.cn");
                      req.addHeader("Cache-Control", "no-cache");
                      req.addHeader("Connection", "keep-alive");
                      req.addHeader("Pragma", "no-cache");
                      req.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
                      req.addHeader("X-Requested-With", "XMLHttpRequest");

                      req.setMethod(HttpConstant.Method.GET);
                     // req.setUrl(setUrl);
                      page.addTargetRequest("http://expert.78500.cn/new/ssq/2017085/?page=3&sort_num=0");
                             } catch (Exception e) {
                                 e.printStackTrace();
                             }

        String oriJson = page.getHtml().regex("<body>(.*?)</body>").get();
        JSONObject jsonAry = JSONObject.parseObject(oriJson);
        String gsonStr = jsonAry.toString().replace("<b>", "").replace("</b>", "").replace("<br/>", "")
                .replace("<b class='blue'>", "").replace("\\s", " ").replace("\n", " ");
        String testjson = gsonStr.substring(1, 271);
        Gson gson = new Gson();
        JsonOriBean jsoribean = gson.fromJson(gsonStr, JsonOriBean.class);

        System.out.println("抓取的内容1：" + jsoribean.getList());
        List<JsonOriBean.JsonThredBean> jsonlist = jsoribean.getList();
        for (int j = 0; j < 1; j++) {
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
            System.out.println(threeBlueK);

        }
    }


    public Site getSite() {

        return site;
    }

    public static void main(String[] args) {
/*        try {
            loop1:
            for (int qishu = 2017085; qishu < 2017086; qishu++) {
                Spider.create(new testajax()).thread(5)
                        .addUrl("http://expert.78500.cn/new/ssq/" + qishu)
                        .addPipeline(new ConsolePipeline())
                        .run();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Spider.create(new testajax()).thread(5)
                .addUrl("http://expert.78500.cn/new/ssq/2017085")
                .addPipeline(new ConsolePipeline())
                .run();
    }
}
