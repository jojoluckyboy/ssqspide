package com.spider.lottery.ssq;

import com.spider.lottery.pojo.SsqRealTime;
import com.spider.lottery.ssqservice.SsqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Description：
 *抓取网站上实时统计数据
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/6/26
 * Time：9:34
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
public class SpiderSsqRealTimeStic implements PageProcessor {


    @Autowired
    private SsqService ssqService;
    //public static final String url_history = "(.*).78500.cn/(.*)";//校验地址正则

    // 部分一：抓取网站的相关配置，包括编码、重试次数、抓取间隔、超时时间、请求消息头、UA信息等
//    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(60000).addHeader("Content-Type", "application/x-www-form-urlencoded")
//            .addHeader("Content-Length", "42");

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(5000).addHeader("Content-Type", "application/x-www-form-urlencoded")
              .addHeader("Connection", "keep-alive")
      //      .addCookie("Cookie", "UM_distinctid=15c6837d7d058-0223650d8dd98a-414a0229-13c680-15c6837d7d186; bdshare_firstime=1498097316898; CBBSESSID=gqfv1q39095hccafde5if3js00; CNZZDATA3497249=cnzz_eid%3D790657241-1498092335-http%253A%252F%252Fwww.78500.cn%252F%26ntime%3D1498713486")  //  此处为模拟登陆后的指;
      //      .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0")
              .setCharset("UTF-8");






    @Override
    public Site getSite() {
        return site;
    }



    private int rcount;

    public int getDcount(){
        return this.rcount;

    }

    @Override
    public void process(Page page) {


        String issueversion = page.getHtml().xpath("//p[@class=\"kjhao\"]/span[1]").regex("<span class=\"s_desc\">.*?([0-9]{7}).*?</span>").get();
        String red1 = page.getHtml().xpath("//p[@class=\"kjhao\"]/span[2]").regex("<span class=\"s_red\">(.*?)</span>").get();
        String red2 = page.getHtml().xpath("//p[@class=\"kjhao\"]/span[3]").regex("<span class=\"s_red\">(.*?)</span>").get();
        String red3 = page.getHtml().xpath("//p[@class=\"kjhao\"]/span[4]").regex("<span class=\"s_red\">(.*?)</span>").get();
        String red4 = page.getHtml().xpath("//p[@class=\"kjhao\"]/span[5]").regex("<span class=\"s_red\">(.*?)</span>").get();
        String red5 = page.getHtml().xpath("//p[@class=\"kjhao\"]/span[6]").regex("<span class=\"s_red\">(.*?)</span>").get();
        String red6 = page.getHtml().xpath("//p[@class=\"kjhao\"]/span[7]").regex("<span class=\"s_red\">(.*?)</span>").get();
        String blue = page.getHtml().xpath("//p[@class=\"kjhao\"]/span[8]").regex("<span class=\"s_blue\">(.*?)</span>").get();
        String red = red1+" "+red2+" "+red3+" "+red4+" "+red5+" "+red6;
        String oneV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[1]").replace("<td>","").replace("</td>","").get();
        String twoV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[2]").replace("<td>","").replace("</td>","").get();
        String threeV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[3]").replace("<td>","").replace("</td>","").get();
        String fourV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[4]").replace("<td>","").replace("</td>","").get();
        String fiveV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[5]").replace("<td>","").replace("</td>","").get();
        String sixV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[6]").replace("<td>","").replace("</td>","").get();
        String sevenV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[7]").replace("<td>","").replace("</td>","").get();
        String eightV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[8]").replace("<td>","").replace("</td>","").get();
        String nineV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[9]").replace("<td>","").replace("</td>","").get();
        String tenV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[10]").replace("<td>","").replace("</td>","").get();

        String elevenV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[11]").replace("<td>","").replace("</td>","").get();
        String twelveV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[12]").replace("<td>","").replace("</td>","").get();
        String thirteenV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[13]").replace("<td>","").replace("</td>","").get();
        String fourteenV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[14]").replace("<td>","").replace("</td>","").get();
        String fifteenV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[15]").replace("<td>","").replace("</td>","").get();
        String sixteenV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[16]").replace("<td>","").replace("</td>","").get();
        String seventeenV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[17]").replace("<td>","").replace("</td>","").get();
        String eighteenV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[18]").replace("<td>","").replace("</td>","").get();
        String nineteenV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[19]").replace("<td>","").replace("</td>","").get();
        String twentyV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[20]").replace("<td>","").replace("</td>","").get();

        String twentyoneV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[21]").replace("<td>","").replace("</td>","").get();
        String twentytwoV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[22]").replace("<td>","").replace("</td>","").get();
        String twentythreeV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[23]").replace("<td>","").replace("</td>","").get();
        String twentyfourV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[24]").replace("<td>","").replace("</td>","").get();
        String twentyfiveV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[25]").replace("<td>","").replace("</td>","").get();
        String twentysixV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[26]").replace("<td>","").replace("</td>","").get();
        String twentysevenV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[27]").replace("<td>","").replace("</td>","").get();
        String twentyeightV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[28]").replace("<td>","").replace("</td>","").get();
        String twentynineV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[29]").replace("<td>","").replace("</td>","").get();
        String thirtyV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[30]").replace("<td>","").replace("</td>","").get();

        String thirtyoneV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[31]").replace("<td>","").replace("</td>","").get();
        String thirtytwoV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[32]").replace("<td>","").replace("</td>","").get();
        String thirtythreeV = page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td[33]").replace("<td>","").replace("</td>","").get();

        String red1locate = "0";
        String red2locate = "0";
        String red3locate = "0";
        String red4locate = "0";
        String red5locate = "0";
        String red6locate = "0";

        List<Integer> locate = new ArrayList<Integer>();

        loop1:
        for (int i=1;i<34;i++){
            if(red1.equals(page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td["+i+"]")
                    .replace("<td>","").replace("</td>","").get())){
                locate.add(i);
                 break loop1;
            }
        }

        loop2:
        for (int i=1;i<34;i++){
            if(red2.equals(page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td["+i+"]")
                    .replace("<td>","").replace("</td>","").get())){
                locate.add(i);
                break loop2;
            }
        }

        loop3:
        for (int i=1;i<34;i++){
            if(red3.equals(page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td["+i+"]")
                    .replace("<td>","").replace("</td>","").get())){
                locate.add(i);
                break loop3;
            }
        }

        loop4:
        for (int i=1;i<34;i++){
            if(red4.equals(page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td["+i+"]")
                    .replace("<td>","").replace("</td>","").get())){
                locate.add(i);
                break loop4;
            }
        }

        loop5:
        for (int i=1;i<34;i++){
            if(red5.equals(page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td["+i+"]")
                    .replace("<td>","").replace("</td>","").get())){
                locate.add(i);
                break loop5;
            }
        }

        loop6:
        for (int i=1;i<34;i++){
            if(red6.equals(page.getHtml().xpath("//div[@class=\"table_main_ssq\"]/table[2]/tbody/tr[2]/td["+i+"]")
                    .replace("<td>","").replace("</td>","").get())){
                locate.add(i);
                break loop6;
            }
        }

        Collections.sort(locate);
        red1locate = String.valueOf(locate.get(0));
        red2locate = String.valueOf(locate.get(1));
        red3locate = String.valueOf(locate.get(2));
        red4locate = String.valueOf(locate.get(3));
        red5locate = String.valueOf(locate.get(4));
        red6locate = String.valueOf(locate.get(5));

        String locateComp = red1locate +" "+red2locate +" "+red3locate +" "+red4locate +" "+red5locate +" "+red6locate;
        int reds1 = Integer.parseInt(red1);
        int reds2 = Integer.parseInt(red2);
        int reds3 = Integer.parseInt(red3);
        int reds4 = Integer.parseInt(red4);
        int reds5 = Integer.parseInt(red5);
        int reds6 = Integer.parseInt(red6);

        String redComp = reds1 +" "+reds2 +" "+reds3 +" "+reds4 +" "+reds5 +" "+reds6;

        SsqRealTime ssqRealTime = new SsqRealTime();
        ssqRealTime.setIssue(issueversion);
        ssqRealTime.setRed(red);
        ssqRealTime.setRed1(red1);
        ssqRealTime.setRed2(red2);
        ssqRealTime.setRed3(red3);
        ssqRealTime.setRed4(red4);
        ssqRealTime.setRed5(red5);
        ssqRealTime.setRed6(red6);
        ssqRealTime.setBlue(blue);

        ssqRealTime.setOne1(oneV);
        ssqRealTime.setTwo(twoV);
        ssqRealTime.setThree(threeV);
        ssqRealTime.setFour(fourV);
        ssqRealTime.setFive(fiveV);
        ssqRealTime.setSix(sixV);
        ssqRealTime.setSeven(sevenV);
        ssqRealTime.setEight(eightV);
        ssqRealTime.setNine(nineV);
        ssqRealTime.setTen(tenV);

        ssqRealTime.setEleven(elevenV);
        ssqRealTime.setTwelve(twelveV);
        ssqRealTime.setThirteen(thirteenV);
        ssqRealTime.setFourteen(fourteenV);
        ssqRealTime.setFifteen(fifteenV);
        ssqRealTime.setSixteen(sixteenV);
        ssqRealTime.setSeventeen(seventeenV);
        ssqRealTime.setEighteen(eighteenV);
        ssqRealTime.setNineteen(nineteenV);
        ssqRealTime.setTwenty(twentyV);

        ssqRealTime.setTwentyone(twentyoneV);
        ssqRealTime.setTwentytwo(twentytwoV);
        ssqRealTime.setTwentythree(twentythreeV);
        ssqRealTime.setTwentyfour(twentyfourV);
        ssqRealTime.setTwentyfive(twentyfiveV);
        ssqRealTime.setTwentysix(twentysixV);
        ssqRealTime.setTwentyseven(twentysevenV);
        ssqRealTime.setTwentyeight(twentyeightV);
        ssqRealTime.setTwentynine(twentynineV);
        ssqRealTime.setThirty(thirtyV);

        ssqRealTime.setThirtyone(thirtyoneV);
        ssqRealTime.setThirtytwo(thirtytwoV);
        ssqRealTime.setThirtythree(thirtythreeV);

        ssqRealTime.setRed1locate(red1locate);
        ssqRealTime.setRed2locate(red2locate);
        ssqRealTime.setRed3locate(red3locate);
        ssqRealTime.setRed4locate(red4locate);
        ssqRealTime.setRed5locate(red5locate);
        ssqRealTime.setRed6locate(red6locate);

        ssqRealTime.setLocateComp(locateComp);
        ssqRealTime.setRedComp(redComp);

        int Result = ssqService.insertSSQRealTime(ssqRealTime) ;
        rcount =rcount+Result;

        /* for (int i=0;i<qishulist.size();i++)
        {


       String qishudate = page.getHtml().css("tbody.list-tr")
               .regex("<td>"+qishulist.get(i)+"</td> \n" +
                       "  <td>\\d{4}-\\d{2}-\\d{2}</td>")
               .replace("<td>","").replace("</td>","").replace(".*\\n  ","").get();
            System.out.println(qishudate);



                }
         System.out.println(qishulist.size());*/

/*
        loop1:for ( i=0;i<qishulist.size();i++)
        {
            String qishuMatch = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(qishulist.size()-i)+"]/td[1]/text()").get();

            if(qishuMatch.equals(qishulist.get(qishulist.size()-i-1)))
            {
                String qishudate =  page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(qishulist.size()-i)+"]/td[2]/text()").get();
             //   System.out.println(qishudate);
                String red1 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(qishulist.size()-i)+"]/td[3]/div/span[1]/text()").get();
                String red2 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(qishulist.size()-i)+"]/td[3]/div/span[2]/text()").get();
                String red3 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(qishulist.size()-i)+"]/td[3]/div/span[3]/text()").get();
                String red4 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(qishulist.size()-i)+"]/td[3]/div/span[4]/text()").get();
                String red5 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(qishulist.size()-i)+"]/td[3]/div/span[5]/text()").get();
                String red6 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(qishulist.size()-i)+"]/td[3]/div/span[6]/text()").get();
                String blue = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(qishulist.size()-i)+"]/td[3]/div/span[7]/text()").get();
             // System.out.println(red1+" "+red2+" "+red3+" "+red4+" "+red5+" "+red6+"+"+blue);

                SsqHistory ssqhistory = new SsqHistory();
                ssqhistory.setIssue(qishuMatch);
                ssqhistory.setDate(qishudate);
                ssqhistory.setRed(red1 + " " +red2+ " " +red3+ " " +red4+ " " +red5+ " " +red6);
                ssqhistory.setRed1(red1);
                ssqhistory.setRed2(red2);
                ssqhistory.setRed3(red3);
                ssqhistory.setRed4(red4);
                ssqhistory.setRed5(red5);
                ssqhistory.setRed6(red6);
                ssqhistory.setBlue(blue);

            //  System.out.println(ssqhistory);

                if(ssqService == null){
                    System.out.println("数据服务空异常");
                }
                int ssqhisR = ssqService.insertSSQHistory(ssqhistory);


            //    System.out.println(ssqhisR);

            }
            else {

                System.out.println("期数不匹配,当前期数为"+qishuMatch);
                break loop1;
              }
            }

*/


    }

    public void pushData(int startq,int endq) throws Exception {
        //设置POST请求
        Request request = new Request("http://www.78500.cn/ssq/table/setred.html");
        //只有POST请求才可以添加附加参数
        request.setMethod(HttpConstant.Method.POST);
        //设置POST参数

        for(int j=startq;j<=endq;j++) {

            Map<String, Object> Params = new HashMap<String, Object>();
            Params.put("qishu", String.valueOf(j));

            try {
                request.setRequestBody(HttpRequestBody.form(Params, "utf-8"));
                Spider.create(this).thread(1)
                        .addRequest(request)
                        .addPipeline(new ConsolePipeline())
                        .run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String []args){
 /*       Request request=new Request("http://kaijiang.78500.cn/ssq/");
//        Request request=new Request("http://www.icinfo.cn/index.action");


        Map<String, Object> nameValuePair = new HashMap<String, Object>();
        NameValuePair[] values = new NameValuePair[4];
        values[0] = new BasicNameValuePair("startqi", "2014008");
        values[1] = new BasicNameValuePair("endqi", "2016001");
        values[2] = new BasicNameValuePair("year", "2003");
        values[3] = new BasicNameValuePair("action", "range");

        nameValuePair.put("nameValuePair", values);


        request.setExtras(nameValuePair);
        request.setMethod(HttpConstant.Method.POST);
*/

        //设置POST请求
        Request request = new Request("http://www.78500.cn/ssq/table/setred.html");
        //只有POST请求才可以添加附加参数
        request.setMethod(HttpConstant.Method.POST);


        //设置POST参数

        for(int j=2012001;j<2012153;j++) {

            Map<String, Object> Params = new HashMap<String, Object>();
            Params.put("qishu", String.valueOf(j));

/*        List<NameValuePair> nvs = new ArrayList<NameValuePair>();
        nvs.add(new BasicNameValuePair("startqi", "2014008"));
        nvs.add(new BasicNameValuePair("endqi", "2016001"));
        nvs.add(new BasicNameValuePair("year", "2003"));
        nvs.add(new BasicNameValuePair("action", "range"));

        //转换为键值对数组
        NameValuePair[] values = nvs.toArray(new NameValuePair[] {});

        //将键值对数组添加到map中
        Map<String, Object> params = new HashMap<String, Object>();
        //key必须是：nameValuePair
        params.put("nameValuePair", values);*/

            //设置request参数
            //request.setExtras(params);

            //   request.setRequestBody(HttpRequestBody.custom("startqi=2014008&endqi=2016001&action=range".getBytes(),"application/x-www-form-urlencoded","gb2312"));

            try {
                request.setRequestBody(HttpRequestBody.form(Params, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

/*        List<String> titlelist = page.getHtml().xpath("//span[@class='link_title']/a/text()").all();
        List<String> readlist = page.getHtml().xpath("//span[@class='link_view']/text()").all();
        List<String> pinlunlist = page.getHtml().xpath("//span[@class='link_comments']/text()").all();*/


            // 开始执行


            try {
                Spider.create(new SpiderSsqRealTimeStic()).thread(1)
                        .addRequest(request)
                        .addPipeline(new ConsolePipeline())
                        .run();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
    /*// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

        @Override
        // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
        public void process(Page page) {
            // 部分二：定义如何抽取页面信息，并保存下来
            page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
            page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
            if (page.getResultItems().get("name") == null) {
                //skip this page
                page.setSkip(true);
            }
            page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

            // 部分三：从页面发现后续的url地址来抓取
            page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        }

        @Override
        public Site getSite() {
            return site;
        }

        public static void main(String[] args) {

            Spider.create(new spiderHistory())
                    //从"https://github.com/code4craft"开始抓
                    .addUrl("https://github.com/code4craft")
                    .addPipeline(new ConsolePipeline())
                    //开启5个线程抓取
                    .thread(5)
                    //启动爬虫
                    .run();
        }*/



}


