package com.spider.lottery.sanDIncrem;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.spider.lottery.pojo.SanDHistory;
import com.spider.lottery.pojo.SanDJsonOriBean;
import com.spider.lottery.pojo.SandDankill;
import com.spider.lottery.sanD.SpiderSandInitPage;
import com.spider.lottery.sanDservice.SandDankillService;
import com.spider.lottery.sanDservice.SandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

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
public class SpiderIncremExpertSanD implements PageProcessor {

    @Autowired
    private SandDankillService sandDankillService;


    @Autowired
    private SandService sandService ;

    @Autowired
    private SpiderSandInitPage spiderSandInitPage ;


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

        try {
            String oriJson = page.getHtml().regex("<body>(.*?)</body>").get();
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

                sandDankill.setSpicerock(888);
                sandDankill.setSingDankd(888);
                sandDankill.setSingDanconhit(888);
                sandDankill.setSingDanhit(888);
                sandDankill.setSingDanlasthit(888);
                sandDankill.setSingDanEMA3(888);
                sandDankill.setSingDanEMA7(888);
                sandDankill.setSingDanEMA3(888);
                sandDankill.setSingDanDEA(888);
                sandDankill.setSingDanDIF(888);
                sandDankill.setSingDanMACD(888);
                sandDankill.setSingDanlastMACD(888);
                sandDankill.setSingDFortEMA3(888);
                sandDankill.setSingDFortEMA7(888);
                sandDankill.setSingDFortDEA(888);
                sandDankill.setSingDFortDIF(888);
                sandDankill.setSingDFortMACD(888);
                sandDankill.setSingDFortlastMACD(888);
                sandDankill.setSingDFortstat(888);

                sandDankill.setFiveDandiv(888);
                sandDankill.setFiveDanskewn(888);
                sandDankill.setFiveDanacV(888);
                sandDankill.setFiveDanhit(888);
                sandDankill.setFiveDanlasthit(888);
                sandDankill.setFiveDanconhit(888);
                sandDankill.setFiveDFortEMA3(888);
                sandDankill.setFiveDFortEMA7(888);
                sandDankill.setFiveDanDIF(888);
                sandDankill.setFiveDanDEA(888);
                sandDankill.setFiveDFortMACD(888);
                sandDankill.setFiveDFortlastMACD(888);
                sandDankill.setFiveDFortstat(888);

                sandDankill.setSumDandiv(888);
                sandDankill.setSumDanskewn(888);
                sandDankill.setSumDanacV(888);
                sandDankill.setSumDanhit(888);
                sandDankill.setSumDanlasthit(888);
                sandDankill.setSumDanconhit(888);
                sandDankill.setSumDanDEA(888);
                sandDankill.setSumDanDIF(888);
                sandDankill.setSumDFortEMA3(888);
                sandDankill.setSumDFortEMA7(888);
                sandDankill.setSumDFortlastMACD(888);
                sandDankill.setSumDFortMACD(888);
                sandDankill.setSumDFortstat(888);

                sandDankill.setKuaDandiv(888);
                sandDankill.setKuaDanskewn(888);
                sandDankill.setKuaDanacV(888);
                sandDankill.setKuaDanhit(888);
                sandDankill.setKuaDanlasthit(888);
                sandDankill.setKuaDanconhit(888);
                sandDankill.setKuaDanDEA(888);
                sandDankill.setKuaDanDIF(888);
                sandDankill.setKuaDFortEMA3(888);
                sandDankill.setKuaDFortEMA7(888);
                sandDankill.setKuaDFortMACD(888);
                sandDankill.setKuaDFortlastMACD(888);
                sandDankill.setKuaDFortstat(888);


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

    public void pushIcrementDataW() throws Exception {



       List<SandDankill> lastThrQishu = sandDankillService.selectSanDkillDbLast();
        int qishuTr = Integer.valueOf(lastThrQishu.get(0).getIssue());


        List<SanDHistory> lastQishu = sandService.selectSanDbLast();
        int qishuH = Integer.valueOf(lastQishu.get(0).getIssue());


        loop1:
        // for (int qishu = qishuTr+1; qishu < qishuH+1; qishu++) {
        if(qishuTr==qishuH){
            try {

                spiderSandInitPage.pushPage("http://expert.78500.cn/new/3d/" + qishuTr);

            } catch (Exception e) {

                e.printStackTrace();
            }
            int pageNo = spiderSandInitPage.returnPageNo();
            qishuversion = spiderSandInitPage.returnQiShu();

            if (pageNo!=0){
                loop2:
                for(int i = 1; i < pageNo+1; i++){

                    Thread.sleep(1000);
                    try {
                        Spider.create(this).thread(5)
                                .addUrl("http://expert.78500.cn/new/3d/"+qishuTr+"/?page=" + i + "&sort_num=0")
                                .addPipeline(new ConsolePipeline())
                                .run();
                        Thread.interrupted();
                    }catch (Exception e) {

                        e.printStackTrace();

                    }

                }

            } else {
                Thread.sleep(2000);
                try {
                    Spider.create(this).thread(5)
                            .addUrl("http://expert.78500.cn/new/3d/" + qishuTr)
                            .addPipeline(new ConsolePipeline())
                            .run();
                    Thread.interrupted();
                }catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }
        for (int qishu = qishuTr+1; qishu < qishuH+1; qishu++) {


            try {
                System.out.println("执行---http://expert.78500.cn/new/3d/" + qishu);
                spiderSandInitPage.pushPage("http://expert.78500.cn/new/3d/" + qishu);

            } catch (Exception e) {
                System.out.println("出错1");
                e.printStackTrace();
            }
            int pageNo = spiderSandInitPage.returnPageNo();
            qishuversion = spiderSandInitPage.returnQiShu();

            if (pageNo!=0){
                loop2:
                for(int i = 1; i < pageNo+1; i++){

                    System.out.println("执行---http://expert.78500.cn/new/3d/"+qishu+"/?page=" + i + "&sort_num=0");
                    Thread.sleep(1000);
                    try {
                        Spider.create(this).thread(1)
                                .addUrl("http://expert.78500.cn/new/3d/" + qishu + "/?page=" + i + "&sort_num=0")
                                .addPipeline(new ConsolePipeline())
                                .run();
                        Thread.interrupted();
                    }catch (Exception e) {
                        System.out.println("出错2");
                        e.printStackTrace();

                    }

                }

            } else {
                Thread.sleep(2000);
                try {
                    Spider.create(this).thread(1)
                            .addUrl("http://expert.78500.cn/new/3d/" + qishu)
                            .addPipeline(new ConsolePipeline())
                            .run();
                    Thread.interrupted();
                }catch (Exception e) {
                    System.out.println("出错3");
                    e.printStackTrace();

                }

            }
        }


    }


    }





