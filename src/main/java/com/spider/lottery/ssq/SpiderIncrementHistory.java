package com.spider.lottery.ssq;

import com.spider.lottery.pojo.SsqHistory;
import com.spider.lottery.ssqservice.SsqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：
 *增量抓取网站双色球开奖数据
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/6/26
 * Time：9:34
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
public class SpiderIncrementHistory implements PageProcessor {

   //@Resource(name="SsqService")
    @Autowired
    private SsqService ssqService;


    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(5000).addHeader("Content-Type", "application/x-www-form-urlencoded")
              .addHeader("Connection", "keep-alive")
              .setCharset("UTF-8");





    @Override
    public Site getSite() {
        return site;
    }

    @ExtractBy("//div[@class='postDesc']//span[@id='post-date']/text()")
    private String qishuMatch;



    private int i;
    private int j;
    private int k;



    public int getIncreDcount(){
        return this.k;

    }

    @Override
    public void process(Page page) {



        List<String> qishulist = page.getHtml().css("tbody.list-tr").regex("<td>[0-9]{7}</td>")
                .replace("<td>","").replace("</td>","").all();
        page.putField("qishulist",qishulist);

        List<SsqHistory> lastQishu = ssqService.selectSSQDbLast();
        String qishu = lastQishu.get(0).getIssue();



        loop1:for ( i=0;i<qishulist.size();i++) {
            String qishuMatch = page.getHtml().xpath("/html/body/table/tbody[2]/tr[" + (i + 1) + "]/td[1]/text()").get();

            if (qishuMatch.equals(qishu)) {
                j = i;
                break loop1;
            }
            if (i==qishulist.size()-1 && (!qishuMatch.equals(qishu))) {
                j = 0;
            }


        }

        if (j == 0){

            System.out.println("无更新数据");
        }
        else{
            loop2:for ( k=0;k<j;k++)
            {
                String qishuIncre = page.getHtml().xpath("/html/body/table/tbody[2]/tr[" + (j-k) + "]/td[1]/text()").get();
                if (qishuIncre.compareTo(qishu)>0)
                {
                    String qishudate =  page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(j-k)+"]/td[2]/text()").get();

                    String red1 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(j-k)+"]/td[3]/div/span[1]/text()").get();
                    String red2 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(j-k)+"]/td[3]/div/span[2]/text()").get();
                    String red3 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(j-k)+"]/td[3]/div/span[3]/text()").get();
                    String red4 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(j-k)+"]/td[3]/div/span[4]/text()").get();
                    String red5 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(j-k)+"]/td[3]/div/span[5]/text()").get();
                    String red6 = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(j-k)+"]/td[3]/div/span[6]/text()").get();
                    String blue = page.getHtml().xpath("/html/body/table/tbody[2]/tr["+(j-k)+"]/td[3]/div/span[7]/text()").get();
                    // System.out.println(red1+" "+red2+" "+red3+" "+red4+" "+red5+" "+red6+"+"+blue);

                    SsqHistory ssqhistory = new SsqHistory();
                    ssqhistory.setIssue(qishuIncre);
                    ssqhistory.setDate(qishudate);
                    ssqhistory.setRed(red1 + " " +red2+ " " +red3+ " " +red4+ " " +red5+ " " +red6);
                    ssqhistory.setRed1(red1);
                    ssqhistory.setRed2(red2);
                    ssqhistory.setRed3(red3);
                    ssqhistory.setRed4(red4);
                    ssqhistory.setRed5(red5);
                    ssqhistory.setRed6(red6);
                    ssqhistory.setBlue(blue);


                    if(ssqService == null){
                        System.out.println("数据服务空异常");
                    }
                    int ssqhisR = ssqService.insertSSQHistory(ssqhistory);

                }
                else {

                    System.out.println("当前期数小于数据库历史期数,当前期数为"+qishuIncre);
                    break loop2;
                }


            }

        }

        }






    public void pushIcrementData() throws Exception {
        //设置POST请求
        Request request = new Request("http://kaijiang.78500.cn/ssq/");
        //只有POST请求才可以添加附加参数
        request.setMethod(HttpConstant.Method.POST);
        //设置POST参数

        Map<String, Object> Params = new HashMap<String, Object>();
        Params.put("startqi", "");
        Params.put("endqi", "");
        Params.put("year", "2003");
        Params.put("action", "range");

        try {
            request.setRequestBody(HttpRequestBody.form(Params, "utf-8"));
            Spider.create(this).thread(5)
                    .addRequest(request)
                    .addPipeline(new ConsolePipeline())
                    .run();
        } catch (Exception e) {
            e.printStackTrace();
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
        Request request = new Request("http://kaijiang.78500.cn/ssq/");
        //只有POST请求才可以添加附加参数
        request.setMethod(HttpConstant.Method.POST);


        //设置POST参数

        Map<String,Object> Params = new HashMap<String,Object>();
        Params.put("startqi","2014008");
        Params.put("endqi","2016001");
        Params.put("year","2003");
        Params.put("action","range");

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
            request.setRequestBody(HttpRequestBody.form(Params,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/*        List<String> titlelist = page.getHtml().xpath("//span[@class='link_title']/a/text()").all();
        List<String> readlist = page.getHtml().xpath("//span[@class='link_view']/text()").all();
        List<String> pinlunlist = page.getHtml().xpath("//span[@class='link_comments']/text()").all();*/


        // 开始执行


        try {
            Spider.create(new SpiderIncrementHistory()).thread(1)
                    .addRequest(request)
                    .addPipeline(new ConsolePipeline())
                    .run();
        }
        catch(Exception e) {
            e.printStackTrace();
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


