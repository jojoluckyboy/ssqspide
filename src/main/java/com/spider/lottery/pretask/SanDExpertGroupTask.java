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

import java.util.Arrays;
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

        String issue = "2018341";
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



       /*         int sumN=0, sumTN=0;

                try {

                    sumN = sandDExpertGroupService.selectSanDkillbyCountExpert(expertName);
                    sumTN = sandDExpertGroupService.selectSanDkillbyCounttime(expertName,issue);
                }catch(Exception e){
                    sumN = 0;
                    sumTN = 0;
                    e.printStackTrace();
                }*/

                List<String> ename = Arrays.asList("龙山一怪", "黑白猜", "鲁宾汉", "高斯", "马上定胆", "飞扬哥", "风花雪月",
                        "领衔玩彩", "领衔彩军", "露西娅", "雄霸天下", "隐鳞藏彩", "隐胆藏彩", "闪拳", "金银铁胆", "金胆出炉",
                        "酒纯的香", "逐鹿中原", "连连碰", "连环定胆", "运河散人", "迎彩运", "购彩小子", "贝贝托", "豹变南山",
                        "谋码高手", "言必有中", "衡山说彩", "虹桥客", "蓝球之家", "英明胆王", "胜多负少", "胆码神算", "胆中生",
                        "老杨说彩", "美女说号", "组号高手", "红尘", "精益求胆", "章文红", "稳操胜券", "程程", "程咬金", "神马胆",
                        "神手破解", "神之子", "知瞳研彩", "真有戏", "看胆杀蓝", "看胆中金", "看就准", "相濡以沫", "直拿下",
                        "百城之富", "百万雄击", "白发百中", "疯狂彩迷", "玩彩顽童", "玩彩大师", "王赢家", "独胆天涯", "独树一帜",
                        "爵士", "爱金豆子", "焦点", "潇雨", "温柔看胆", "混世魔王", "测胆王", "没输过钱", "水煮鱼", "水上黄昏",
                        "每天一彩", "此码必出", "林兴洛", "李逵", "杀蓝之家", "杀红助手", "杀破狼", "杀号出众", "末世", "朋哥爱彩",
                        "暮浅", "星曜", "易经彩", "旺财宝贝", "旷世熊", "数字控", "捉星神手", "指路人", "技术彩民", "打豆豆",
                        "才子佳人", "彩迷星探", "彩色蜡笔", "彩色系", "彩票果果", "彩神人", "彩研师", "彩界老大", "彩王布阵",
                        "彩之骄子", "当场出彩", "开心买彩", "布衣说号", "小鬼看胆", "实验山地", "定胆老手", "定胆招财",
                        "定胆专家", "安然", "安德", "安庆彩哥", "守望", "存胆库", "子牙神算", "妹子说彩", "天蝎王", "天职",
                        "天涯彩", "天才彩女", "天天向下", "大骆驼", "夜眼雄鹰", "围号狙手", "唯我独彩", "唐儿", "和值高手",
                        "后区密码", "名扬天下", "只若初见", "只争朝夕", "古蓝天者", "双眼龙", "又中一码", "博通", "南方神彩",
                        "南宫傲", "半醉人间", "半仙", "十拿九稳", "北海", "剑胆柔情", "刘邦测号", "刘棋", "凌波仙子", "冒险",
                        "八卦测码", "全力夺金", "克敌制胜", "元始天尊", "偷闲看彩", "信码由缰", "人定胜天", "亡灵天涯",
                        "乱世佳人", "乡巴老", "乐透达人", "中奖秘笈", "中奖有我", "中奖之路", "中号在身", "东方花琦", "专长杀号",
                        "专研独胆", "不老松", "三石", "一纸乱言", "一如既往", "一代彩神", "一人留", "FC达人");


                if(ename.contains(expertName)) {

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


            int qishu = 2019009;

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


