package com.spider.lottery.sanD;

import com.spider.lottery.pojo.ExpertGroupList;
import com.spider.lottery.pojo.SanDExpertData;
import com.spider.lottery.pojo.SandDankill;
import com.spider.lottery.sanDservice.SanDCalService;
import com.spider.lottery.sanDservice.SandDExpertGroupService;
import com.spider.lottery.sanDservice.SandDankillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * 初始话种子选手的数据
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/17
 * Time：10:53
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */



@Service
public class SanDInitExpertData  {


    @Autowired
    private SandDankillService sandDankillService;

    @Autowired
    private SanDCalService sanDCalService;


    @Autowired
    private SandDExpertGroupService sandDExpertGroupService;

    public int d;


    public int getDcount() {
        return this.d;

    }

    /*平均值计算*/
    public double AVG(String[] orired )throws Exception {

        int n = 0;
        int sum = 0;

        for (int i = 0; i < orired.length; i++) {
            n = n + 1;
            sum = sum + Integer.parseInt(orired[i]);

        }

        double AVG =  (double) Math.round((double)(sum*100/n)) / 100;
        return AVG;

    }

    /*标准差计算*/
    public double standardDeviation (String[] orired )throws Exception {

        int n = 0;
        int sum = 0;

        List<Integer> compareList = new ArrayList<Integer>();

        for (int i = 0; i < orired.length; i++) {
            n = n + 1;
            sum = sum + Integer.parseInt(orired[i]);

        }

        double average = sum/n;  //求出数组的平均数

        int total=0;
        for(int j=0;j < orired.length; j++){
            total += (Double.parseDouble(orired[j])-average)*(Double.parseDouble(orired[j])-average);   //求出方差，如果要计算方差的话这一步就可以了
        }
        double standardDeviation =  (double) Math.round((Math.sqrt((double)total*10000/n))) / 100;   //求出标准差
        return standardDeviation;

    }

    public int getQD(int singDan) {
        //奇数小数1  偶数小数2 奇数大数3 偶数大数4
        int getQD = 1;
        if((singDan & 1) != 1 && singDan >4){
            getQD = 4;
        }
        if((singDan & 1) != 1 && singDan <5){
            getQD = 2;
        }

        if((singDan & 1) == 1 && singDan >4){
            getQD = 3;
        }
        if((singDan & 1) == 1 && singDan <5){
            getQD = 1;
        }
        return getQD;

    }

    public String qishuversion;


    public void inialData() {

        SanDExpertData sanDExpertData = new SanDExpertData();

        List<ExpertGroupList> expertgroup = sandDExpertGroupService.selectExpertGroupList();

        for (int i = 2019008; i < 2019010; i++) {

            for (int j = 0; j < expertgroup.size(); j++) {

                String expertname = expertgroup.get(j).getExpertname();
                //String expertname = "半醉人间";
                List<SandDankill> expertdata = sanDCalService.selectxinbyExpIssue(String.valueOf(i), expertname);
                if (expertdata.size() == 0) {
                    String stringValnull = "null,null,null,null,null,null,null,null,null,null,null,null,null,null,null," +
                            "null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null";
                    switch (expertname) {
                        case "龙山一怪":
                            sanDExpertData.setLSYG(stringValnull);
                            break;
                        case "黑白猜":
                            sanDExpertData.setHBC(stringValnull);
                            break;
                        case "鲁宾汉":
                            sanDExpertData.setGS(stringValnull);
                            break;
                        case "高斯":
                            sanDExpertData.setLBH(stringValnull);
                            break;
                        case "马上定胆":
                            sanDExpertData.setMSDD(stringValnull);
                            break;
                        case "飞扬哥":
                            sanDExpertData.setFYG(stringValnull);
                            break;
                        case "风花雪月":
                            sanDExpertData.setFHXY(stringValnull);
                            break;
                        case "领衔玩彩":
                            sanDExpertData.setLXWC(stringValnull);
                            break;
                        case "领衔彩军":
                            sanDExpertData.setLXCJ(stringValnull);
                            break;
                        case "露西娅":
                            sanDExpertData.setLXY(stringValnull);
                            break;
                        case "雄霸天下":
                            sanDExpertData.setXBTX(stringValnull);
                            break;
                        case "隐鳞藏彩":
                            sanDExpertData.setYLCC(stringValnull);
                            break;
                        case "隐胆藏彩":
                            sanDExpertData.setYDCC(stringValnull);
                            break;
                        case "闪拳":
                            sanDExpertData.setSQ(stringValnull);
                            break;
                        case "金银铁胆":
                            sanDExpertData.setJYTD(stringValnull);
                            break;
                        case "金胆出炉":
                            sanDExpertData.setJDCL(stringValnull);
                            break;
                        case "酒纯的香":
                            sanDExpertData.setJCDX(stringValnull);
                            break;
                        case "逐鹿中原":
                            sanDExpertData.setZLZY(stringValnull);
                            break;
                        case "连连碰":
                            sanDExpertData.setLLB(stringValnull);
                            break;
                        case "连环定胆":
                            sanDExpertData.setLHDD(stringValnull);
                            break;
                        case "运河散人":
                            sanDExpertData.setYHSR(stringValnull);
                            break;
                        case "迎彩运":
                            sanDExpertData.setYCY(stringValnull);
                            break;
                        case "购彩小子":
                            sanDExpertData.setGCXZ(stringValnull);
                            break;
                        case "贝贝托":
                            sanDExpertData.setBBT(stringValnull);
                            break;
                        case "豹变南山":
                            sanDExpertData.setBBNS(stringValnull);
                            break;
                        case "谋码高手":
                            sanDExpertData.setMMGS(stringValnull);
                            break;
                        case "言必有中":
                            sanDExpertData.setYBYZ(stringValnull);
                            break;
                        case "衡山说彩":
                            sanDExpertData.setHSSC(stringValnull);
                            break;
                        case "虹桥客":
                            sanDExpertData.setHQK(stringValnull);
                            break;
                        case "蓝球之家":
                            sanDExpertData.setLQZJ(stringValnull);
                            break;
                        case "英明胆王":
                            sanDExpertData.setYMDW(stringValnull);
                            break;
                        case "胜多负少":
                            sanDExpertData.setSDFS(stringValnull);
                            break;
                        case "胆码神算":
                            sanDExpertData.setDMSS(stringValnull);
                            break;
                        case "胆中生":
                            sanDExpertData.setDZS(stringValnull);
                            break;
                        case "老杨说彩":
                            sanDExpertData.setLYSC(stringValnull);
                            break;
                        case "美女说号":
                            sanDExpertData.setMNSH(stringValnull);
                            break;
                        case "组号高手":
                            sanDExpertData.setZHGS(stringValnull);
                            break;
                        case "红尘":
                            sanDExpertData.setHC(stringValnull);
                            break;
                        case "精益求胆":
                            sanDExpertData.setJYQD(stringValnull);
                            break;
                        case "章文红":
                            sanDExpertData.setZWH(stringValnull);
                            break;
                        case "稳操胜券":
                            sanDExpertData.setWCSQ(stringValnull);
                            break;
                        case "程程":
                            sanDExpertData.setCC(stringValnull);
                            break;
                        case "程咬金":
                            sanDExpertData.setCYJ(stringValnull);
                            break;
                        case "神马胆":
                            sanDExpertData.setSMD(stringValnull);
                            break;
                        case "神手破解":
                            sanDExpertData.setSSPJ(stringValnull);
                            break;
                        case "神之子":
                            sanDExpertData.setSZZ(stringValnull);
                            break;
                        case "知瞳研彩":
                            sanDExpertData.setZTYC(stringValnull);
                            break;
                        case "真有戏":
                            sanDExpertData.setZYX(stringValnull);
                            break;
                        case "看胆杀蓝":
                            sanDExpertData.setKDSL(stringValnull);
                            break;
                        case "看胆中金":
                            sanDExpertData.setKDZJ(stringValnull);
                            break;
                        case "看就准":
                            sanDExpertData.setKJZ(stringValnull);
                            break;
                        case "相濡以沫":
                            sanDExpertData.setXRYM(stringValnull);
                            break;
                        case "直拿下":
                            sanDExpertData.setZNX(stringValnull);
                            break;
                        case "百城之富":
                            sanDExpertData.setBCZF(stringValnull);
                            break;
                        case "百万雄击":
                            sanDExpertData.setBWXJ(stringValnull);
                            break;
                        case "白发百中":
                            sanDExpertData.setBFBZ(stringValnull);
                            break;
                        case "疯狂彩迷":
                            sanDExpertData.setFKCM(stringValnull);
                            break;
                        case "玩彩顽童":
                            sanDExpertData.setWCWT(stringValnull);
                            break;
                        case "玩彩大师":
                            sanDExpertData.setWCDS(stringValnull);
                            break;
                        case "王赢家":
                            sanDExpertData.setWYJ(stringValnull);
                            break;
                        case "独胆天涯":
                            sanDExpertData.setDDTY(stringValnull);
                            break;
                        case "独树一帜":
                            sanDExpertData.setDSYZ(stringValnull);
                            break;
                        case "爵士":
                            sanDExpertData.setJS(stringValnull);
                            break;
                        case "爱金豆子":
                            sanDExpertData.setAJDZ(stringValnull);
                            break;
                        case "焦点":
                            sanDExpertData.setJD(stringValnull);
                            break;
                        case "潇雨":
                            sanDExpertData.setXIAOY(stringValnull);
                            break;
                        case "温柔看胆":
                            sanDExpertData.setWRKD(stringValnull);
                            break;
                        case "混世魔王":
                            sanDExpertData.setHSMW(stringValnull);
                            break;
                        case "测胆王":
                            sanDExpertData.setCDW(stringValnull);
                            break;
                        case "没输过钱":
                            sanDExpertData.setMSGQ(stringValnull);
                            break;
                        case "水煮鱼":
                            sanDExpertData.setSZY(stringValnull);
                            break;
                        case "水上黄昏":
                            sanDExpertData.setSSHH(stringValnull);
                            break;
                        case "每天一彩":
                            sanDExpertData.setMTYC(stringValnull);
                            break;
                        case "此码必出":
                            sanDExpertData.setCMBC(stringValnull);
                            break;
                        case "林兴洛":
                            sanDExpertData.setLXL(stringValnull);
                            break;
                        case "李逵":
                            sanDExpertData.setLK(stringValnull);
                            break;
                        case "杀蓝之家":
                            sanDExpertData.setSLZJ(stringValnull);
                            break;
                        case "杀红助手":
                            sanDExpertData.setSHZS(stringValnull);
                            break;
                        case "杀破狼":
                            sanDExpertData.setSPL(stringValnull);
                            break;
                        case "杀号出众":
                            sanDExpertData.setSHCZ(stringValnull);
                            break;
                        case "末世":
                            sanDExpertData.setMS(stringValnull);
                            break;
                        case "朋哥爱彩":
                            sanDExpertData.setPGAC(stringValnull);
                            break;
                        case "暮浅":
                            sanDExpertData.setMQ(stringValnull);
                            break;
                        case "星曜":
                            sanDExpertData.setXINY(stringValnull);
                            break;
                        case "易经彩":
                            sanDExpertData.setYJC(stringValnull);
                            break;
                        case "旺财宝贝":
                            sanDExpertData.setWCBB(stringValnull);
                            break;
                        case "旷世熊":
                            sanDExpertData.setKSX(stringValnull);
                            break;
                        case "数字控":
                            sanDExpertData.setSZK(stringValnull);
                            break;
                        case "捉星神手":
                            sanDExpertData.setZXSS(stringValnull);
                            break;
                        case "指路人":
                            sanDExpertData.setZLR(stringValnull);
                            break;
                        case "技术彩民":
                            sanDExpertData.setJSCM(stringValnull);
                            break;
                        case "打豆豆":
                            sanDExpertData.setDDD(stringValnull);
                            break;
                        case "才子佳人":
                            sanDExpertData.setCZJR(stringValnull);
                            break;
                        case "彩迷星探":
                            sanDExpertData.setCMXT(stringValnull);
                            break;
                        case "彩色蜡笔":
                            sanDExpertData.setCSLB(stringValnull);
                            break;
                        case "彩色系":
                            sanDExpertData.setCSX(stringValnull);
                            break;
                        case "彩票果果":
                            sanDExpertData.setCPGG(stringValnull);
                            break;
                        case "彩神人":
                            sanDExpertData.setCSR(stringValnull);
                            break;
                        case "彩研师":
                            sanDExpertData.setCYS(stringValnull);
                            break;
                        case "彩界老大":
                            sanDExpertData.setCJLD(stringValnull);
                            break;
                        case "彩王布阵":
                            sanDExpertData.setCWBZ(stringValnull);
                            break;
                        case "彩之骄子":
                            sanDExpertData.setCZJZ(stringValnull);
                            break;
                        case "当场出彩":
                            sanDExpertData.setDCCC(stringValnull);
                            break;
                        case "开心买彩":
                            sanDExpertData.setKXMC(stringValnull);
                            break;
                        case "布衣说号":
                            sanDExpertData.setBYSH(stringValnull);
                            break;
                        case "小鬼看胆":
                            sanDExpertData.setXGKD(stringValnull);
                            break;
                        case "实验山地":
                            sanDExpertData.setSYSD(stringValnull);
                            break;
                        case "定胆老手":
                            sanDExpertData.setDDLS(stringValnull);
                            break;
                        case "定胆招财":
                            sanDExpertData.setDDZC(stringValnull);
                            break;
                        case "定胆专家":
                            sanDExpertData.setDDZJ(stringValnull);
                            break;
                        case "安然":
                            sanDExpertData.setAR(stringValnull);
                            break;
                        case "安德":
                            sanDExpertData.setAD(stringValnull);
                            break;
                        case "安庆彩哥":
                            sanDExpertData.setAQCG(stringValnull);
                            break;
                        case "守望":
                            sanDExpertData.setSW(stringValnull);
                            break;
                        case "存胆库":
                            sanDExpertData.setCDK(stringValnull);
                            break;
                        case "子牙神算":
                            sanDExpertData.setZYSS(stringValnull);
                            break;
                        case "妹子说彩":
                            sanDExpertData.setMZSC(stringValnull);
                            break;
                        case "天蝎王":
                            sanDExpertData.setTXW(stringValnull);
                            break;
                        case "天职":
                            sanDExpertData.setTZ(stringValnull);
                            break;
                        case "天涯彩":
                            sanDExpertData.setTYC(stringValnull);
                            break;
                        case "天才彩女":
                            sanDExpertData.setTCCN(stringValnull);
                            break;
                        case "天天向下":
                            sanDExpertData.setTTXX(stringValnull);
                            break;
                        case "大骆驼":
                            sanDExpertData.setDLT(stringValnull);
                            break;
                        case "夜眼雄鹰":
                            sanDExpertData.setYYXY(stringValnull);
                            break;
                        case "围号狙手":
                            sanDExpertData.setWHJS(stringValnull);
                            break;
                        case "唯我独彩":
                            sanDExpertData.setWWDC(stringValnull);
                            break;
                        case "唐儿":
                            sanDExpertData.setTE(stringValnull);
                            break;
                        case "和值高手":
                            sanDExpertData.setHZGS(stringValnull);
                            break;
                        case "后区密码":
                            sanDExpertData.setHQMM(stringValnull);
                            break;
                        case "名扬天下":
                            sanDExpertData.setMYTX(stringValnull);
                            break;
                        case "只若初见":
                            sanDExpertData.setZRCJ(stringValnull);
                            break;
                        case "只争朝夕":
                            sanDExpertData.setZZCX(stringValnull);
                            break;
                        case "古蓝天者":
                            sanDExpertData.setGLTZ(stringValnull);
                            break;
                        case "双眼龙":
                            sanDExpertData.setSYL(stringValnull);
                            break;
                        case "又中一码":
                            sanDExpertData.setYZYM(stringValnull);
                            break;
                        case "博通":
                            sanDExpertData.setBT(stringValnull);
                            break;
                        case "南方神彩":
                            sanDExpertData.setNFCS(stringValnull);
                            break;
                        case "南宫傲":
                            sanDExpertData.setNGA(stringValnull);
                            break;
                        case "半醉人间":
                            sanDExpertData.setBZRJ(stringValnull);
                            break;
                        case "半仙":
                            sanDExpertData.setBX(stringValnull);
                            break;
                        case "十拿九稳":
                            sanDExpertData.setSNJW(stringValnull);
                            break;
                        case "北海":
                            sanDExpertData.setBH(stringValnull);
                            break;
                        case "剑胆柔情":
                            sanDExpertData.setJDRQ(stringValnull);
                            break;
                        case "刘邦测号":
                            sanDExpertData.setLBCH(stringValnull);
                            break;
                        case "刘棋":
                            sanDExpertData.setLQ(stringValnull);
                            break;
                        case "凌波仙子":
                            sanDExpertData.setLBXZ(stringValnull);
                            break;
                        case "冒险":
                            sanDExpertData.setMX(stringValnull);
                            break;
                        case "八卦测码":
                            sanDExpertData.setBGCM(stringValnull);
                            break;
                        case "全力夺金":
                            sanDExpertData.setQLDJ(stringValnull);
                            break;
                        case "克敌制胜":
                            sanDExpertData.setKDZS(stringValnull);
                            break;
                        case "元始天尊":
                            sanDExpertData.setYSTZ(stringValnull);
                            break;
                        case "偷闲看彩":
                            sanDExpertData.setTXKC(stringValnull);
                            break;
                        case "信码由缰":
                            sanDExpertData.setXMYJ(stringValnull);
                            break;
                        case "人定胜天":
                            sanDExpertData.setRDST(stringValnull);
                            break;
                        case "亡灵天涯":
                            sanDExpertData.setWLTY(stringValnull);
                            break;
                        case "乱世佳人":
                            sanDExpertData.setLSJR(stringValnull);
                            break;
                        case "乡巴老":
                            sanDExpertData.setXBL(stringValnull);
                            break;
                        case "乐透达人":
                            sanDExpertData.setLTDR(stringValnull);
                            break;
                        case "中奖秘笈":
                            sanDExpertData.setZJMJ(stringValnull);
                            break;
                        case "中奖有我":
                            sanDExpertData.setZJYW(stringValnull);
                            break;
                        case "中奖之路":
                            sanDExpertData.setZJZL(stringValnull);
                            break;
                        case "中号在身":
                            sanDExpertData.setZHZS(stringValnull);
                            break;
                        case "东方花琦":
                            sanDExpertData.setDFHQ(stringValnull);
                            break;
                        case "专长杀号":
                            sanDExpertData.setZCSH(stringValnull);
                            break;
                        case "专研独胆":
                            sanDExpertData.setZYDD(stringValnull);
                            break;
                        case "不老松":
                            sanDExpertData.setBLS(stringValnull);
                            break;
                        case "三石":
                            sanDExpertData.setSS(stringValnull);
                            break;
                        case "一纸乱言":
                            sanDExpertData.setYZLY(stringValnull);
                            break;
                        case "一如既往":
                            sanDExpertData.setYRJW(stringValnull);
                            break;
                        case "一代彩神":
                            sanDExpertData.setYDCS(stringValnull);
                            break;
                        case "一人留":
                            sanDExpertData.setYRL(stringValnull);
                            break;
                        case "FC达人":
                            sanDExpertData.setFCDR(stringValnull);
                            break;
                    }

                }

                if (expertdata.size() > 0) {
                    String sanDanhit = String.valueOf(expertdata.get(0).getSingDanhit());
                    String fiveDanhit = String.valueOf(expertdata.get(0).getFiveDanhit());
                    String sumDanhit = String.valueOf(expertdata.get(0).getSumDanhit());
                    String kuaDanhit = String.valueOf(expertdata.get(0).getKuaDanhit());

                    String singDanTemp = String.valueOf(expertdata.get(0).getSingDan());
                    String singDan = "null";
                    String singDanQD = "null";
                    if (singDanTemp == "--" || singDanTemp.equals("--") ) {
                        singDan = "null";
                        singDanQD = "null";
                    } else {
                        singDan = singDanTemp;
                        singDanQD = String.valueOf(getQD(Integer.valueOf(singDanTemp)));
                    }

                    String spicerock = String.valueOf(expertdata.get(0).getSpicerock());
                    String singDankd = String.valueOf(expertdata.get(0).getSingDankd());
                    String singDFortMACD = String.valueOf(expertdata.get(0).getSingDFortMACD());
                    String singDFortstat = String.valueOf(expertdata.get(0).getSingDFortstat());

                    String[] orired = expertdata.get(0).getFiveDanV().split(" ");

                    String fiveDanVTemp = expertdata.get(0).getFiveDanV();

                    String fiveDanAVG = "null";
                    String fiveDanSTD = "null";
                    String fiveDandiv = "null";
                    String fiveDanskewn = "null";
                    String fiveDanacV = "null";
                    String fiveDFortstat = "null";
                    String fiveDFortMACD = "null";
                    if (fiveDanVTemp == "--") {
                        fiveDanAVG = "null";
                        fiveDanSTD = "null";
                        fiveDandiv = "null";
                        fiveDanskewn = "null";
                        fiveDanacV = "null";
                        fiveDFortstat = "null";
                        fiveDFortMACD = "null";


                    } else {

                        try {
                            fiveDanAVG = String.valueOf(AVG(orired));
                            fiveDanSTD = String.valueOf(standardDeviation(orired));
                            String fiveDandivTemp = String.valueOf(expertdata.get(0).getFiveDandiv());
                            if (fiveDandivTemp == "888") {
                                fiveDandiv = "null";
                            } else {
                                fiveDandiv = fiveDandivTemp;
                            }

                            String fiveDanskewnTemp = String.valueOf(expertdata.get(0).getFiveDanskewn());
                            if (fiveDanskewnTemp == "888") {
                                fiveDanskewn = "null";
                            } else {
                                fiveDanskewn = fiveDanskewnTemp;
                            }

                            String fiveDanacVTemp = String.valueOf(expertdata.get(0).getFiveDanacV());
                            if (fiveDanacVTemp == "888") {
                                fiveDanacV = "null";
                            } else {
                                fiveDanacV = fiveDanacVTemp;
                            }

                            String fiveDFortstatTemp = String.valueOf(expertdata.get(0).getFiveDFortstat());
                            if (fiveDFortstatTemp == "888") {
                                fiveDFortstat = "null";
                            } else {
                                fiveDFortstat = fiveDFortstatTemp;
                            }

                            String fiveDFortMACDTemp = String.valueOf(expertdata.get(0).getFiveDFortMACD());
                            if (fiveDFortMACDTemp == "888") {
                                fiveDFortMACD = "null";
                            } else {
                                fiveDFortMACD = fiveDFortMACDTemp;
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                    String[] oriredsum = expertdata.get(0).getSumDanV().split(" ");

                    String sumDanVTemp = expertdata.get(0).getSumDanV();
                    String sumDanAVG = "null";
                    String sumDanSTD = "null";
                    String sumDandiv = "null";
                    String sumDanskewn = "null";
                    String sumDanacV = "null";
                    String sumDFortstat = "null";
                    String sumDFortMACD = "null";
                    if (sumDanVTemp == "--") {
                        sumDanAVG = "null";
                        sumDanSTD = "null";
                        sumDandiv = "null";
                        sumDanskewn = "null";
                        sumDanacV = "null";
                        sumDFortstat = "null";
                        sumDFortMACD = "null";


                    } else {

                        try {
                            sumDanAVG = String.valueOf(AVG(oriredsum));
                            sumDanSTD = String.valueOf(standardDeviation(oriredsum));
                            String sumDandivTemp = String.valueOf(expertdata.get(0).getSumDandiv());
                            if (sumDandivTemp == "888") {
                                sumDandiv = "null";
                            } else {
                                sumDandiv = sumDandivTemp;
                            }

                            String sumDanskewnTemp = String.valueOf(expertdata.get(0).getSumDanskewn());
                            if (sumDanskewnTemp == "888") {
                                sumDanskewn = "null";
                            } else {
                                sumDanskewn = sumDanskewnTemp;
                            }

                            String sumDanacVTemp = String.valueOf(expertdata.get(0).getSumDanacV());
                            if (sumDanacVTemp == "888") {
                                sumDanacV = "null";
                            } else {
                                sumDanacV = sumDanacVTemp;
                            }

                            String sumDFortstatTemp = String.valueOf(expertdata.get(0).getSumDFortstat());
                            if (sumDFortstatTemp == "888") {
                                sumDFortstat = "null";
                            } else {
                                sumDFortstat = sumDFortstatTemp;
                            }

                            String sumDFortMACDTemp = String.valueOf(expertdata.get(0).getSumDFortMACD());
                            if (sumDFortMACDTemp == "888") {
                                sumDFortMACD = "null";
                            } else {
                                sumDFortMACD = sumDFortMACDTemp;
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    String[] oriredkua = expertdata.get(0).getKuaDanV().split(" ");

                    String kuaDanVTemp = expertdata.get(0).getKuaDanV();

                    String kuaDanAVG = "null";
                    String kuaDanSTD = "null";
                    String kuaDandiv = "null";
                    String kuaDanskewn = "null";
                    String kuaDanacV = "null";
                    String kuaDFortstat = "null";
                    String kuaDFortMACD = "null";
                    if (kuaDanVTemp == "--") {
                        kuaDanAVG = "null";
                        kuaDanSTD = "null";
                        kuaDandiv = "null";
                        kuaDanskewn = "null";
                        kuaDanacV = "null";
                        kuaDFortstat = "null";
                        kuaDFortMACD = "null";


                    } else {

                        try {
                            kuaDanAVG = String.valueOf(AVG(oriredkua));
                            kuaDanSTD = String.valueOf(standardDeviation(oriredkua));
                            String kuaDandivTemp = String.valueOf(expertdata.get(0).getKuaDandiv());
                            if (kuaDandivTemp == "888") {
                                kuaDandiv = "null";
                            } else {
                                kuaDandiv = kuaDandivTemp;
                            }

                            String kuaDanskewnTemp = String.valueOf(expertdata.get(0).getKuaDanskewn());
                            if (kuaDanskewnTemp == "888") {
                                kuaDanskewn = "null";
                            } else {
                                kuaDanskewn = kuaDanskewnTemp;
                            }

                            String kuaDanacVTemp = String.valueOf(expertdata.get(0).getKuaDanacV());
                            if (kuaDanacVTemp == "888") {
                                kuaDanacV = "null";
                            } else {
                                kuaDanacV = kuaDanacVTemp;
                            }

                            String kuaDFortstatTemp = String.valueOf(expertdata.get(0).getKuaDFortstat());
                            if (kuaDFortstatTemp == "888") {
                                kuaDFortstat = "null";
                            } else {
                                kuaDFortstat = kuaDFortstatTemp;
                            }

                            String kuaDFortMACDTemp = String.valueOf(expertdata.get(0).getKuaDFortMACD());
                            if (kuaDFortMACDTemp == "888") {
                                kuaDFortMACD = "null";
                            } else {
                                kuaDFortMACD = kuaDFortMACDTemp;
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }


                    String stringVal = sanDanhit + "," + fiveDanhit + "," + sumDanhit + "," + kuaDanhit + "," + singDan
                            + "," + spicerock + "," + singDankd + "," + singDanQD + "," + singDFortMACD + "," + singDFortstat
                            + "," + fiveDanAVG + "," + fiveDanSTD + "," + fiveDandiv + "," + fiveDanskewn + "," +
                            fiveDanacV + "," + fiveDFortstat + "," + fiveDFortMACD + "," + sumDanAVG + "," + sumDanSTD
                            + "," + sumDandiv + "," + sumDanskewn + "," + sumDanacV + "," + sumDFortstat + "," +
                            sumDFortMACD + "," + kuaDanAVG + "," + kuaDanSTD + "," + kuaDandiv + "," + kuaDanskewn + "," +
                            kuaDanacV + "," + kuaDFortstat + "," + kuaDFortMACD;

//                if(expertname.equals("龙山一怪") ){
//
//                    sanDExpertData.setLSYG(stringVal);
//                }
//
//                if(expertname.equals("黑白猜") ){
//
//                        sanDExpertData.setHBC(stringVal);
//                }
//
//                if(expertname.equals("鲁宾汉") ){
//
//                        sanDExpertData.setLBH(stringVal);
//                    }
                    switch (expertname) {
                        case "龙山一怪":
                            sanDExpertData.setLSYG(stringVal);
                            break;
                        case "黑白猜":
                            sanDExpertData.setHBC(stringVal);
                            break;
                        case "鲁宾汉":
                            sanDExpertData.setGS(stringVal);
                            break;
                        case "高斯":
                            sanDExpertData.setLBH(stringVal);
                            break;
                        case "马上定胆":
                            sanDExpertData.setMSDD(stringVal);
                            break;
                        case "飞扬哥":
                            sanDExpertData.setFYG(stringVal);
                            break;
                        case "风花雪月":
                            sanDExpertData.setFHXY(stringVal);
                            break;
                        case "领衔玩彩":
                            sanDExpertData.setLXWC(stringVal);
                            break;
                        case "领衔彩军":
                            sanDExpertData.setLXCJ(stringVal);
                            break;
                        case "露西娅":
                            sanDExpertData.setLXY(stringVal);
                            break;
                        case "雄霸天下":
                            sanDExpertData.setXBTX(stringVal);
                            break;
                        case "隐鳞藏彩":
                            sanDExpertData.setYLCC(stringVal);
                            break;
                        case "隐胆藏彩":
                            sanDExpertData.setYDCC(stringVal);
                            break;
                        case "闪拳":
                            sanDExpertData.setSQ(stringVal);
                            break;
                        case "金银铁胆":
                            sanDExpertData.setJYTD(stringVal);
                            break;
                        case "金胆出炉":
                            sanDExpertData.setJDCL(stringVal);
                            break;
                        case "酒纯的香":
                            sanDExpertData.setJCDX(stringVal);
                            break;
                        case "逐鹿中原":
                            sanDExpertData.setZLZY(stringVal);
                            break;
                        case "连连碰":
                            sanDExpertData.setLLB(stringVal);
                            break;
                        case "连环定胆":
                            sanDExpertData.setLHDD(stringVal);
                            break;
                        case "运河散人":
                            sanDExpertData.setYHSR(stringVal);
                            break;
                        case "迎彩运":
                            sanDExpertData.setYCY(stringVal);
                            break;
                        case "购彩小子":
                            sanDExpertData.setGCXZ(stringVal);
                            break;
                        case "贝贝托":
                            sanDExpertData.setBBT(stringVal);
                            break;
                        case "豹变南山":
                            sanDExpertData.setBBNS(stringVal);
                            break;
                        case "谋码高手":
                            sanDExpertData.setMMGS(stringVal);
                            break;
                        case "言必有中":
                            sanDExpertData.setYBYZ(stringVal);
                            break;
                        case "衡山说彩":
                            sanDExpertData.setHSSC(stringVal);
                            break;
                        case "虹桥客":
                            sanDExpertData.setHQK(stringVal);
                            break;
                        case "蓝球之家":
                            sanDExpertData.setLQZJ(stringVal);
                            break;
                        case "英明胆王":
                            sanDExpertData.setYMDW(stringVal);
                            break;
                        case "胜多负少":
                            sanDExpertData.setSDFS(stringVal);
                            break;
                        case "胆码神算":
                            sanDExpertData.setDMSS(stringVal);
                            break;
                        case "胆中生":
                            sanDExpertData.setDZS(stringVal);
                            break;
                        case "老杨说彩":
                            sanDExpertData.setLYSC(stringVal);
                            break;
                        case "美女说号":
                            sanDExpertData.setMNSH(stringVal);
                            break;
                        case "组号高手":
                            sanDExpertData.setZHGS(stringVal);
                            break;
                        case "红尘":
                            sanDExpertData.setHC(stringVal);
                            break;
                        case "精益求胆":
                            sanDExpertData.setJYQD(stringVal);
                            break;
                        case "章文红":
                            sanDExpertData.setZWH(stringVal);
                            break;
                        case "稳操胜券":
                            sanDExpertData.setWCSQ(stringVal);
                            break;
                        case "程程":
                            sanDExpertData.setCC(stringVal);
                            break;
                        case "程咬金":
                            sanDExpertData.setCYJ(stringVal);
                            break;
                        case "神马胆":
                            sanDExpertData.setSMD(stringVal);
                            break;
                        case "神手破解":
                            sanDExpertData.setSSPJ(stringVal);
                            break;
                        case "神之子":
                            sanDExpertData.setSZZ(stringVal);
                            break;
                        case "知瞳研彩":
                            sanDExpertData.setZTYC(stringVal);
                            break;
                        case "真有戏":
                            sanDExpertData.setZYX(stringVal);
                            break;
                        case "看胆杀蓝":
                            sanDExpertData.setKDSL(stringVal);
                            break;
                        case "看胆中金":
                            sanDExpertData.setKDZJ(stringVal);
                            break;
                        case "看就准":
                            sanDExpertData.setKJZ(stringVal);
                            break;
                        case "相濡以沫":
                            sanDExpertData.setXRYM(stringVal);
                            break;
                        case "直拿下":
                            sanDExpertData.setZNX(stringVal);
                            break;
                        case "百城之富":
                            sanDExpertData.setBCZF(stringVal);
                            break;
                        case "百万雄击":
                            sanDExpertData.setBWXJ(stringVal);
                            break;
                        case "白发百中":
                            sanDExpertData.setBFBZ(stringVal);
                            break;
                        case "疯狂彩迷":
                            sanDExpertData.setFKCM(stringVal);
                            break;
                        case "玩彩顽童":
                            sanDExpertData.setWCWT(stringVal);
                            break;
                        case "玩彩大师":
                            sanDExpertData.setWCDS(stringVal);
                            break;
                        case "王赢家":
                            sanDExpertData.setWYJ(stringVal);
                            break;
                        case "独胆天涯":
                            sanDExpertData.setDDTY(stringVal);
                            break;
                        case "独树一帜":
                            sanDExpertData.setDSYZ(stringVal);
                            break;
                        case "爵士":
                            sanDExpertData.setJS(stringVal);
                            break;
                        case "爱金豆子":
                            sanDExpertData.setAJDZ(stringVal);
                            break;
                        case "焦点":
                            sanDExpertData.setJD(stringVal);
                            break;
                        case "潇雨":
                            sanDExpertData.setXIAOY(stringVal);
                            break;
                        case "温柔看胆":
                            sanDExpertData.setWRKD(stringVal);
                            break;
                        case "混世魔王":
                            sanDExpertData.setHSMW(stringVal);
                            break;
                        case "测胆王":
                            sanDExpertData.setCDW(stringVal);
                            break;
                        case "没输过钱":
                            sanDExpertData.setMSGQ(stringVal);
                            break;
                        case "水煮鱼":
                            sanDExpertData.setSZY(stringVal);
                            break;
                        case "水上黄昏":
                            sanDExpertData.setSSHH(stringVal);
                            break;
                        case "每天一彩":
                            sanDExpertData.setMTYC(stringVal);
                            break;
                        case "此码必出":
                            sanDExpertData.setCMBC(stringVal);
                            break;
                        case "林兴洛":
                            sanDExpertData.setLXL(stringVal);
                            break;
                        case "李逵":
                            sanDExpertData.setLK(stringVal);
                            break;
                        case "杀蓝之家":
                            sanDExpertData.setSLZJ(stringVal);
                            break;
                        case "杀红助手":
                            sanDExpertData.setSHZS(stringVal);
                            break;
                        case "杀破狼":
                            sanDExpertData.setSPL(stringVal);
                            break;
                        case "杀号出众":
                            sanDExpertData.setSHCZ(stringVal);
                            break;
                        case "末世":
                            sanDExpertData.setMS(stringVal);
                            break;
                        case "朋哥爱彩":
                            sanDExpertData.setPGAC(stringVal);
                            break;
                        case "暮浅":
                            sanDExpertData.setMQ(stringVal);
                            break;
                        case "星曜":
                            sanDExpertData.setXINY(stringVal);
                            break;
                        case "易经彩":
                            sanDExpertData.setYJC(stringVal);
                            break;
                        case "旺财宝贝":
                            sanDExpertData.setWCBB(stringVal);
                            break;
                        case "旷世熊":
                            sanDExpertData.setKSX(stringVal);
                            break;
                        case "数字控":
                            sanDExpertData.setSZK(stringVal);
                            break;
                        case "捉星神手":
                            sanDExpertData.setZXSS(stringVal);
                            break;
                        case "指路人":
                            sanDExpertData.setZLR(stringVal);
                            break;
                        case "技术彩民":
                            sanDExpertData.setJSCM(stringVal);
                            break;
                        case "打豆豆":
                            sanDExpertData.setDDD(stringVal);
                            break;
                        case "才子佳人":
                            sanDExpertData.setCZJR(stringVal);
                            break;
                        case "彩迷星探":
                            sanDExpertData.setCMXT(stringVal);
                            break;
                        case "彩色蜡笔":
                            sanDExpertData.setCSLB(stringVal);
                            break;
                        case "彩色系":
                            sanDExpertData.setCSX(stringVal);
                            break;
                        case "彩票果果":
                            sanDExpertData.setCPGG(stringVal);
                            break;
                        case "彩神人":
                            sanDExpertData.setCSR(stringVal);
                            break;
                        case "彩研师":
                            sanDExpertData.setCYS(stringVal);
                            break;
                        case "彩界老大":
                            sanDExpertData.setCJLD(stringVal);
                            break;
                        case "彩王布阵":
                            sanDExpertData.setCWBZ(stringVal);
                            break;
                        case "彩之骄子":
                            sanDExpertData.setCZJZ(stringVal);
                            break;
                        case "当场出彩":
                            sanDExpertData.setDCCC(stringVal);
                            break;
                        case "开心买彩":
                            sanDExpertData.setKXMC(stringVal);
                            break;
                        case "布衣说号":
                            sanDExpertData.setBYSH(stringVal);
                            break;
                        case "小鬼看胆":
                            sanDExpertData.setXGKD(stringVal);
                            break;
                        case "实验山地":
                            sanDExpertData.setSYSD(stringVal);
                            break;
                        case "定胆老手":
                            sanDExpertData.setDDLS(stringVal);
                            break;
                        case "定胆招财":
                            sanDExpertData.setDDZC(stringVal);
                            break;
                        case "定胆专家":
                            sanDExpertData.setDDZJ(stringVal);
                            break;
                        case "安然":
                            sanDExpertData.setAR(stringVal);
                            break;
                        case "安德":
                            sanDExpertData.setAD(stringVal);
                            break;
                        case "安庆彩哥":
                            sanDExpertData.setAQCG(stringVal);
                            break;
                        case "守望":
                            sanDExpertData.setSW(stringVal);
                            break;
                        case "存胆库":
                            sanDExpertData.setCDK(stringVal);
                            break;
                        case "子牙神算":
                            sanDExpertData.setZYSS(stringVal);
                            break;
                        case "妹子说彩":
                            sanDExpertData.setMZSC(stringVal);
                            break;
                        case "天蝎王":
                            sanDExpertData.setTXW(stringVal);
                            break;
                        case "天职":
                            sanDExpertData.setTZ(stringVal);
                            break;
                        case "天涯彩":
                            sanDExpertData.setTYC(stringVal);
                            break;
                        case "天才彩女":
                            sanDExpertData.setTCCN(stringVal);
                            break;
                        case "天天向下":
                            sanDExpertData.setTTXX(stringVal);
                            break;
                        case "大骆驼":
                            sanDExpertData.setDLT(stringVal);
                            break;
                        case "夜眼雄鹰":
                            sanDExpertData.setYYXY(stringVal);
                            break;
                        case "围号狙手":
                            sanDExpertData.setWHJS(stringVal);
                            break;
                        case "唯我独彩":
                            sanDExpertData.setWWDC(stringVal);
                            break;
                        case "唐儿":
                            sanDExpertData.setTE(stringVal);
                            break;
                        case "和值高手":
                            sanDExpertData.setHZGS(stringVal);
                            break;
                        case "后区密码":
                            sanDExpertData.setHQMM(stringVal);
                            break;
                        case "名扬天下":
                            sanDExpertData.setMYTX(stringVal);
                            break;
                        case "只若初见":
                            sanDExpertData.setZRCJ(stringVal);
                            break;
                        case "只争朝夕":
                            sanDExpertData.setZZCX(stringVal);
                            break;
                        case "古蓝天者":
                            sanDExpertData.setGLTZ(stringVal);
                            break;
                        case "双眼龙":
                            sanDExpertData.setSYL(stringVal);
                            break;
                        case "又中一码":
                            sanDExpertData.setYZYM(stringVal);
                            break;
                        case "博通":
                            sanDExpertData.setBT(stringVal);
                            break;
                        case "南方神彩":
                            sanDExpertData.setNFCS(stringVal);
                            break;
                        case "南宫傲":
                            sanDExpertData.setNGA(stringVal);
                            break;
                        case "半醉人间":
                            sanDExpertData.setBZRJ(stringVal);
                            break;
                        case "半仙":
                            sanDExpertData.setBX(stringVal);
                            break;
                        case "十拿九稳":
                            sanDExpertData.setSNJW(stringVal);
                            break;
                        case "北海":
                            sanDExpertData.setBH(stringVal);
                            break;
                        case "剑胆柔情":
                            sanDExpertData.setJDRQ(stringVal);
                            break;
                        case "刘邦测号":
                            sanDExpertData.setLBCH(stringVal);
                            break;
                        case "刘棋":
                            sanDExpertData.setLQ(stringVal);
                            break;
                        case "凌波仙子":
                            sanDExpertData.setLBXZ(stringVal);
                            break;
                        case "冒险":
                            sanDExpertData.setMX(stringVal);
                            break;
                        case "八卦测码":
                            sanDExpertData.setBGCM(stringVal);
                            break;
                        case "全力夺金":
                            sanDExpertData.setQLDJ(stringVal);
                            break;
                        case "克敌制胜":
                            sanDExpertData.setKDZS(stringVal);
                            break;
                        case "元始天尊":
                            sanDExpertData.setYSTZ(stringVal);
                            break;
                        case "偷闲看彩":
                            sanDExpertData.setTXKC(stringVal);
                            break;
                        case "信码由缰":
                            sanDExpertData.setXMYJ(stringVal);
                            break;
                        case "人定胜天":
                            sanDExpertData.setRDST(stringVal);
                            break;
                        case "亡灵天涯":
                            sanDExpertData.setWLTY(stringVal);
                            break;
                        case "乱世佳人":
                            sanDExpertData.setLSJR(stringVal);
                            break;
                        case "乡巴老":
                            sanDExpertData.setXBL(stringVal);
                            break;
                        case "乐透达人":
                            sanDExpertData.setLTDR(stringVal);
                            break;
                        case "中奖秘笈":
                            sanDExpertData.setZJMJ(stringVal);
                            break;
                        case "中奖有我":
                            sanDExpertData.setZJYW(stringVal);
                            break;
                        case "中奖之路":
                            sanDExpertData.setZJZL(stringVal);
                            break;
                        case "中号在身":
                            sanDExpertData.setZHZS(stringVal);
                            break;
                        case "东方花琦":
                            sanDExpertData.setDFHQ(stringVal);
                            break;
                        case "专长杀号":
                            sanDExpertData.setZCSH(stringVal);
                            break;
                        case "专研独胆":
                            sanDExpertData.setZYDD(stringVal);
                            break;
                        case "不老松":
                            sanDExpertData.setBLS(stringVal);
                            break;
                        case "三石":
                            sanDExpertData.setSS(stringVal);
                            break;
                        case "一纸乱言":
                            sanDExpertData.setYZLY(stringVal);
                            break;
                        case "一如既往":
                            sanDExpertData.setYRJW(stringVal);
                            break;
                        case "一代彩神":
                            sanDExpertData.setYDCS(stringVal);
                            break;
                        case "一人留":
                            sanDExpertData.setYRL(stringVal);
                            break;
                        case "FC达人":
                            sanDExpertData.setFCDR(stringVal);
                            break;

                    }


                }
            }
            sanDExpertData.setIssue(String.valueOf(i));
            int Result = sandDExpertGroupService.insertSandexpertData(sanDExpertData);
            if (Result == 1) {
                d = d + 1;
            }
        }
        System.out.println("共计" + d + "条专家数据更新");
    }


    public static void main(String[] args) {




       try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
            SanDInitExpertData sanDInitExpertData = (SanDInitExpertData) context.getBean("SanDInitExpertData");
            sanDInitExpertData.inialData();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}


