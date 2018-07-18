package com.spider.lottery.ssq;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.spider.lottery.pojo.JsonOriBean;
import com.spider.lottery.pojo.SsqThred;
import com.spider.lottery.ssqservice.SsqThredService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Description：
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/8/7
 * Time：16:09
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */




public class testajaxssq implements PageProcessor {



    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
            .addHeader("accept", "application/json, text/javascript")
            .addHeader("Accept-Encoding", "gzip, deflate, sdch,br")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Host", "expert.78500.cn")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Connection", "keep-alive")
            .addHeader("Pragma", "no-cache")
            .addHeader("X-Requested-With", "XMLHttpRequest");

    public static final String URL_LIST = "http://expert.78500.cn/new/ssq/2017085";

    public static String refUrl;

    public void process(Page page) {

 /*       try{
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        SpiderThrRedPage spiderthrpage = (SpiderThrRedPage) context.getBean("SpiderThrRedPage");
        try {
            spiderthrpage.pushPage(refUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //模拟post请求

         /* String qishuversion = spiderthrpage.returnQiShu();
          int pageNo = spiderthrpage.returnPageNo();

                //模拟post请求
                Request req = new Request();
                String setUrl = "http://expert.78500.cn/new/ssq/"+qishuversion+"/?page=" + pageNo + "&sort_num=0";
                req.setMethod(HttpConstant.Method.GET);
                req.setUrl(setUrl);
                //req.setRequestBody(HttpRequestBody.json("{page: 3, sort_num: 0}", "utf-8"));
                //Thread.sleep(3000);
                //page.addTargetRequest(req);
                //req.setRequestBody(HttpRequestBody.json("{page: 3, sort_num: 0}", "utf-8"));
                //Thread.sleep(3000);
                //page.addTargetRequest(req);

            } catch (Exception e) {
                e.printStackTrace();
            }*/
        /*} else if (page.getUrl().regex(URL_LIST).match() && pageNum <= 200) {
            try {
                Thread.sleep(5000);
                List<String> urls = page.getHtml().xpath("/*//*[@class='post_item']//div[@class='post_item_body']/h3/a/@href").all();
                page.addTargetRequests(urls);
                //模拟post请求
                Request req = new Request();
                req.setMethod(HttpConstant.Method.POST);
                req.setUrl("https://www.cnblogs.com/mvc/AggSite/PostList.aspx");
                req.setRequestBody(HttpRequestBody.json("{CategoryType: 'SiteHome', ParentCategoryId: 0, CategoryId: 808, PageIndex: " + ++pageNum
                        + ", TotalPostCount: 4000,ItemListActionName:'PostList'}", "utf-8"));
                page.addTargetRequest(req);
                System.out.println("CurrPage:" + pageNum + "#######################################");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 获取页面需要的内容,这里只取了标题，其他信息同理。*/
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

        }
    }


    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {


        /*try {
            loop1:
            for (int qishu = 2017085; qishu < 2017089; qishu++) {
                refUrl = "http://expert.78500.cn/new/ssq/" + qishu;
                Spider.create(new testajaxssq()).thread(5)
                        .addUrl("http://expert.78500.cn/new/ssq/" + qishu)
                        .addPipeline(new ConsolePipeline())
                        .run();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /* try {
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

                        Spider.create(new testajaxssq()).thread(5)
                                .addUrl("http://expert.78500.cn/new/ssq/"+qishu+"/?page=" + i + "&sort_num=0")
                                .addPipeline(new ConsolePipeline())
                                .run();
                    }

                }else {
                    Spider.create(new testajaxssq()).thread(5)
                            .addUrl("http://expert.78500.cn/new/ssq/"+qishu)
                            .addPipeline(new ConsolePipeline())
                            .run();

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/

  /*      ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        CalPercModeTask calPercModeTask = (CalPercModeTask) context.getBean("CalPercModeTask");

        try {
            calPercModeTask.pushIcrementData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int percMode=calPercModeTask.getIncreDcount();
        System.out.println("共计" + percMode + "条历史数据匹配连续错误率");

    }*/



        SsqThred ssqThred = new SsqThred();
       ssqThred.setIssue("2033100");
        ssqThred.setExpertname("测试");
        ssqThred.setRedthree("01 23 32");
        ssqThred.setUrl("www.baidu.com");
        ssqThred.setRedfive("01 23 32");
        ssqThred.setBluethree("01 23 32");
        ssqThred.setRedk("01");
        ssqThred.setThreeredk("01 23 32");
        ssqThred.setSixredk("01 23 32");
        ssqThred.setBluethreek("01 23 32");
        ssqThred.setRed("01 23 32");
        ssqThred.setRed1("01");
        ssqThred.setRed2("01");
        ssqThred.setRed3("01");
        ssqThred.setRed4("01");
        ssqThred.setRed5("01");
        ssqThred.setRed6("01");
        ssqThred.setBlue("01");
        ssqThred.setHistorymaxerrorpd(1000);
        ssqThred.setPresenterrorscore(1000);
        SsqThredService ssqThredService = new SsqThredService();
        int ssqthrR = ssqThredService.insertSSQThred(ssqThred);

        double i = Math.round(5.2644555 * 100) * 0.01d;
        System.out.println(i);

    }
}
