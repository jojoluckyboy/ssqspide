package com.spider.lottery.pretask;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.spider.lottery.pojo.SanDJsonOriBean;
import com.spider.lottery.pojo.SandDExpertGroup;
import com.spider.lottery.sanD.SpiderSandInitPage;
import com.spider.lottery.sanDservice.SandDExpertGroupService;
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
 * 清空准备数据库，实时抓取已经做出预测的专家,保留总体记录大于1000，最近记录大于100的专家
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/17
 * Time：10:53
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */



@Service
public class SanDExpertGroupTask implements PageProcessor {


    @Autowired
    private SandDankillService sandDankillService;

    @Autowired
    private SandDExpertGroupService sandDExpertGroupService;
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

        String issue = "2017300";
        SandDExpertGroup sandDExpertGroup = new SandDExpertGroup();

        site.getCharset();

        try {
            String oriJson = page.getHtml().regex("<body>(.*?)</body>").get().toString();
/*            String oriJson111 = new String(oriJson.getBytes(),"gbk");
            String oriJsonurl=oriJson.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            String urlStr = URLDecoder.decode(oriJsonurl, "utf-8");*/

            JSONObject jsonAry = JSONObject.parseObject(oriJson);
            String gsonStr = jsonAry.toString().replace("<b>", "").replace("</b>", "").replace("<br/>", "")
                    .replace("<b class='blue'>", "").replace("\\s", " ").replace("<br />", ", ").replace("\n", "");

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



                int sumN=0, sumTN=0;

                try {

                    sumN = sandDExpertGroupService.selectSanDkillbyCountExpert(expertName);
                    sumTN = sandDExpertGroupService.selectSanDkillbyCounttime(expertName,issue);
                }catch(Exception e){
                    sumN = 0;
                    sumTN = 0;
                    e.printStackTrace();
                }




                if(sumN>1000 && sumTN>220) {

                    //独胆
                    String singDan = jsonlist.get(j).getRet().getR2().toString();

                    //五码组选
                    String fiveDan = jsonlist.get(j).getRet().getR5().toString();
                    //定3跨
                    String kuaDan = jsonlist.get(j).getRet().getS11().toString();
                    //定4和
                    String sumDan = jsonlist.get(j).getRet().getS12().toString();


                    sandDExpertGroup.setIssue(qishuversion);
                    sandDExpertGroup.setExpertname(expertName);
                    sandDExpertGroup.setSingDan(singDan);
                    sandDExpertGroup.setFiveDanV(fiveDan);
                    sandDExpertGroup.setSumDanV(sumDan);
                    sandDExpertGroup.setKuaDanV(kuaDan);


                    if (sandDExpertGroupService == null) {
                        System.out.println("数据服务空异常");
                        break loop3;
                    }
                    int sandDankillR = sandDExpertGroupService.insertSandDExpertGroup(sandDExpertGroup);

                    if (sandDankillR == 1) {

                        d = d + 1;

                    }
                }else{

                        continue;
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


            int qishu = 2018172;

                if(sandDExpertGroupService.deleteSandDExpertGroup() ==0){

                    System.out.println("数据表清空");

                }


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



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {


            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
            SanDExpertGroupTask sanDExpertGroupTask = (SanDExpertGroupTask) context.getBean("SanDExpertGroupTask");


            try {
                sanDExpertGroupTask.pushData();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //*记录导入了多少历史数据*//**//*
            int Result=sanDExpertGroupTask.getDcount();

            System.out.println("共计获取" + Result+ "条专家记录");


    }


}


