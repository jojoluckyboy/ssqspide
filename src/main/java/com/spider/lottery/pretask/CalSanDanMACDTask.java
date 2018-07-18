package com.spider.lottery.pretask;

import com.spider.lottery.pojo.SandDankill;
import com.spider.lottery.sanDservice.SanDCalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description：计算概率模型
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/12
 * Time：9:55
 * Copyright© 2003-2016 Zhejiang huixin technology company
 *
 */

@Service
public class CalSanDanMACDTask {

    @Autowired
    private SanDCalService sanDCalService;



          /*强 弱 中  1--36   1 4 7 10 13 16 19 22 25 28 31 34

            0轴下二次金叉 34
            0000101,0001101,0011101,1011101,0111101,1101101,1111101,1110001,1110101,1111001  36
            0001001,0011001,0100101,0101101,0110101,0111001,1000101,1001101,1100101          35
            0010001,0010101,0100001,0101001,0110001,1000001,1001001,1010001,1010101,1011001,1100001,1101001  34
            0轴下金叉  19
            0000001,           20
            0轴下死叉  7
            0000010,0000110,0001110,0011110                  8
            0轴下二次死叉  4
            0010110,0011010,0101010,0101110,0110110,1001010,1011010,1011110,1101110,1111010,1111110       6
            0001010,0010010,0111010,0111110,1000110,1001110,1010110,1101010,1110010,1110110               5
            0100010,0100110,0110010,1000010,1010010,1100010,1100110                                       4


            0轴下金叉上行 22
            0001111,0001011,0010111,0100111,0101011,0101111,0110111,1001011,1001111,1010111,1011111,1100111,1101111,1111111          24
            0000111,0010011,0011011,0011111,0110011,0111011,0111111,1000111,1010011,1011011,1100011,1101011,1110011,1110111,1111011  23
            0000011,0100011,1000011                                                                                                  22


            0轴下死叉下行  1
            0010100,0010000,0011100,0100000,0100100,0111100                                                                 3
            0001000,0011000,0110000,0110100,0111000,1000000,1001100,1010000,1011100,1100000,1100100,1111000,1111100         2
            0000000,0000100,0001100,0101100,0101000,1000100,1001000,1010100,1011000,1101000,1101100,1110000,1110100         1


            0轴上二次金叉 25
            0000101,0001101,0011101,1011101,0111101,1101101,1111101,1110001,1110101,1111001                  27
            0001001,0011001,0100101,0101101,0110101,0111001,1000101,1001101,1100101                          26
            0010001,0010101,0100001,0101001,0110001,1000001,1001001,1010001,1010101,1011001,1100001,1101001  25
            0轴上金叉   28
            0000001,    29

            0轴上死叉   16
            0000010,0000110,0001110,0011110     18


            0轴上二次死叉  13
            0010110,0011010,0101010,0101110,0110110,1001010,1011010,1011110,1101110,1111010,1111110      15
            0001010,0010010,0111010,0111110,1000110,1001110,1010110,1101010,1110010,1110110              14
            0100010,0100110,0110010,1000010,1010010,1100010,1100110                                      13



            0轴上金叉上行 31
            0001111,0001011,0010111,0100111,0101011,0101111,0110111,1001011,1001111,1010111,1011111,1100111,1101111,1111111         33
            0000111,0010011,0011011,0011111,0110011,0111011,0111111,1000111,1010011,1011011,1100011,1101011,1110011,1110111,1111011 32
            0000011,0100011,1000011          31
            0轴上死叉下行  10
            0010100,0010000,0011100,0100000,0100100,0111100                                                             12
            0001000,0011000,0110000,0110100,0111000,1000000,1001100,1010000,1011100,1100000,1100100,1111000,1111100      11
            0000000,0000100,0001100,0101100,0101000,1000100,1001000,1010100,1011000,1101000,1101100,1110000,1110100      10*/


    /*EMA3计算*/
    public double ValEMA3(ArrayList<Double> doubleList )throws Exception {

        double ValEMA3 = (3*doubleList.get(0)+2*doubleList.get(1)+doubleList.get(2))/6;
        return ValEMA3;

    }

    /*EMA7计算*/
    public double ValEMA7(ArrayList<Double> doubleList )throws Exception {

        double ValEMA7 = (7*doubleList.get(0)+6*doubleList.get(1)+5*doubleList.get(2)+4*doubleList.get(3)+
                3*doubleList.get(4)+2*doubleList.get(5)+doubleList.get(6))/28;
        return ValEMA7;

    }

    /*EMA5计算*/
    public double ValEMA5(ArrayList<Double> doubleList )throws Exception {

        double ValEMA5 = (5*doubleList.get(0)+4*doubleList.get(1)+3*doubleList.get(2)+2*doubleList.get(3)+
                doubleList.get(4))/15;
        return ValEMA5;

    }

    /*运气状态赋值计算*/
    public int stateCal(String stateID )throws Exception {
        int stateCal = 0;
        String [] state36 = {"00000101","00001101","00011101","01011101","00111101","01101101","01111101","01110001","01110101","01111001"};
        String [] state35 = {"00001001","00011001","00100101","00101101","00110101","00111001","01000101","01001101","01100101"};
        String [] state34 = {"00010001","00010101","00100001","00101001","00110001","01000001","01001001","01010001","01010101","01011001","01100001","01101001"};
        String [] state33 = {"10001111","10001011","10010111","10100111","10101011","10101111","10110111","11001011","11001111","11010111","11011111","11100111","11101111","11111111"};
        String [] state32 = {"10000111","10010011","10011011","10011111","10110011","10111011","10111111","11000111","11010011","11011011","11100011","11101011","11110011","11110111","11111011"};
        String [] state31 = {"10000011","10100011","11000011"};

        String [] state29 ={"10000001"};
        String [] state27 ={"10000101","10001101","10011101","11011101","10111101","11101101","11111101","11110001","11110101","11111001"};
        String [] state26 ={"10001001","10011001","10100101","10101101","10110101","10111001","11000101","11001101","11100101"};
        String [] state25 ={"10010001","10010101","10100001","10101001","10110001","11000001","11001001","11010001","11010101","11011001","11100001","11101001"};

        String [] state24 ={"00001111","00001011","00010111","00100111","00101011","00101111","00110111","01001011","01001111","01010111","01011111","01100111","01101111","01111111"};
        String [] state23 ={"00000111","00010011","00011011","00011111","00110011","00111011","00111111","01000111","01010011","01011011","01100011","01101011","01110011","01110111","01111011"};
        String [] state22 ={"00000011","00100011","01000011"};

        String [] state20 ={"00000001"};

        String [] state16 ={"10000010","10000110","10001110","10011110"};

        String [] state15 ={"10010110","10011010","10101010","10101110","10110110","11001010","11011010","11011110","11101110","11111010","11111110"};
        String [] state14 ={"10001010","10010010","10111010","10111110","11000110","11001110","11010110","11101010","11110010","11110110"};
        String [] state13 ={"10100010","10100110","10110010","11000010","11010010","11100010","11100110"};

        String [] state12 ={"10010100","10010000","10011100","10100000","10100100","10111100"};
        String [] state11 ={"10001000","10011000","10110000","10110100","10111000","11000000","11001100","11010000","11011100","11100000","11100100","11111000","11111100"};
        String [] state10 ={"10000000","10000100","10001100","10101100","10101000","11000100","11001000","11010100","11011000","11101000","11101100","11110000","11110100"};

        String [] state8 ={"00000010","00000110","00001110","00011110"};

        String [] state6 ={"00010110","00011010","00101010","00101110","00110110","01001010","01011010","01011110","01101110","01111010","01111110"};
        String [] state5 ={"00001010","00010010","00111010","00111110","01000110","01001110","01010110","01101010","01110010","01110110"};
        String [] state4 ={"00100010","00100110","00110010","01000010","01010010","01100010","01100110"};

        String [] state3 ={"00010100","00010000","00011100","00100000","00100100","00111100"};
        String [] state2 ={"00001000","00011000","00110000","00110100","00111000","01000000","01001100","01010000","01011100","01100000","01100100","01111000","01111100"};
        String [] state1 ={"00000000","00000100","00001100","00101100","00101000","01000100","01001000","01010100","01011000","01101000","01101100","01110000","01110100"};

        //String数组转List
        List<String> statelist36= Arrays.asList(state36);
        List<String> statelist35= Arrays.asList(state35);
        List<String> statelist34= Arrays.asList(state34);
        List<String> statelist33= Arrays.asList(state33);
        List<String> statelist32= Arrays.asList(state32);
        List<String> statelist31= Arrays.asList(state31);

        List<String> statelist29= Arrays.asList(state29);

        List<String> statelist27= Arrays.asList(state27);
        List<String> statelist26= Arrays.asList(state26);
        List<String> statelist25= Arrays.asList(state25);

        List<String> statelist24= Arrays.asList(state24);
        List<String> statelist23= Arrays.asList(state23);
        List<String> statelist22= Arrays.asList(state22);

        List<String> statelist20= Arrays.asList(state20);
        List<String> statelist16= Arrays.asList(state16);


        List<String> statelist15= Arrays.asList(state15);
        List<String> statelist14= Arrays.asList(state14);
        List<String> statelist13= Arrays.asList(state13);

        List<String> statelist12= Arrays.asList(state12);
        List<String> statelist11= Arrays.asList(state11);
        List<String> statelist10= Arrays.asList(state10);

        List<String> statelist8= Arrays.asList(state8);

        List<String> statelist6= Arrays.asList(state6);
        List<String> statelist5= Arrays.asList(state5);
        List<String> statelist4= Arrays.asList(state4);

        List<String> statelist3= Arrays.asList(state3);
        List<String> statelist2= Arrays.asList(state2);
        List<String> statelist1= Arrays.asList(state1);




        if(statelist36.contains(stateID)){
            return 36;
        }

        if(statelist35.contains(stateID)){
            return 35;
        }

        if(statelist34.contains(stateID)){
            return 34;
        }

        if(statelist33.contains(stateID)){
            return 33;
        }

        if(statelist32.contains(stateID)){
            return 32;
        }

        if(statelist31.contains(stateID)){
            return 31;
        }

        if(statelist29.contains(stateID)){
            return 29;
        }

        if(statelist27.contains(stateID)){
            return 27;
        }

        if(statelist26.contains(stateID)){
            return 26;
        }

        if(statelist25.contains(stateID)){
            return 25;
        }

        if(statelist24.contains(stateID)){
            return 24;
        }

        if(statelist23.contains(stateID)){
            return 23;
        }

        if(statelist22.contains(stateID)){
            return 22;
        }

        if(statelist20.contains(stateID)){
            return 20;
        }

        if(statelist16.contains(stateID)){
            return 16;
        }
        if(statelist15.contains(stateID)){
            return 15;
        }

        if(statelist14.contains(stateID)){
            return 14;
        }

        if(statelist13.contains(stateID)){
            return 13;
        }

        if(statelist12.contains(stateID)){
            return 12;
        }

        if(statelist11.contains(stateID)){
            return 11;
        }

        if(statelist10.contains(stateID)){
            return 10;
        }
        if(statelist8.contains(stateID)){
            return 8;
        }

        if(statelist6.contains(stateID)){
            return 6;
        }

        if(statelist5.contains(stateID)){
            return 5;
        }

        if(statelist4.contains(stateID)){
            return 4;
        }

        if(statelist3.contains(stateID)){
            return 3;
        }

        if(statelist2.contains(stateID)){
            return 2;
        }

        if(statelist1.contains(stateID)){
            return 1;
        }


        return 0;

    }



    /**
     * 前置准备导入历史数据
     *
     * @return 0 or 1
     * @throws Exception
     */

    private int rcount = 0;


    public void pushIcrementData() throws Exception {


/*        int startid = 1;
        int endid = 600;*/

        int fid = 0;
        String fissue;
        String lissue;
        int startid = sanDCalService.selectIdBySinDEMA3asc(888.0);
        int endid = sanDCalService.selectIdBySinDEMA3desc(888.0);

        SandDankill sandDankill = new SandDankill();

        List<String> findexpert = sanDCalService.selectexpertByID(startid, endid);

        double singDanEMA3 = 0.0;
        double singDanEMA7 = 0.0;
        double singDanDIF = 0.0;
        double singDanDEA = 0.0;
        double singDanMACD = 0.0;
        double singDFortEMA3 = 0.0;
        double singDFortEMA7 = 0.0;
        double singDFortDIF = 0.0;
        double singDFortDEA = 0.0;
        double singDFortMACD = 0.0;
        int singDFortstat = 0;
        double singDanlastMACD = 0.0;
        double singDFortlastMACD = 0.0;

        double fiveDFortEMA3 = 0.0;
        double fiveDFortEMA7 = 0.0;
        double fiveDanDIF = 0.0;
        double fiveDanDEA = 0.0;
        double fiveDFortMACD = 0.0;
        int fiveDFortstat = 0;
        double fiveDFortlastMACD = 0.0;


        double sumDFortEMA3 = 0.0;
        double sumDFortEMA7 = 0.0;
        double sumDanDIF = 0.0;
        double sumDanDEA = 0.0;
        double sumDFortMACD = 0.0;
        int sumDFortstat = 0;
        double sumDFortlastMACD = 0.0;

        double kuaDFortEMA3 = 0.0;
        double kuaDFortEMA7 = 0.0;
        double kuaDanDIF = 0.0;
        double kuaDanDEA = 0.0;
        double kuaDFortMACD = 0.0;
        int kuaDFortstat = 0;
        double kuaDFortlastMACD = 0.0;

        StringBuilder stringTemp = new StringBuilder("");


        Loop1:
        for (int i = 0; i < findexpert.size(); i++) {

             String expertname = findexpert.get(i).toString();
            //String expertname = "小鬼看胆";
            List<SandDankill> findid = sanDCalService.selectIDbyEMA3(expertname, 888.0, endid);
            if (findid.size() == 0) {
                continue;
            }
            int expID = findid.get(0).getId();

            if (findid.size() == 1) {

                List<SandDankill> findLastid = sanDCalService.selectIDbyEMA3last7(expertname, expID);
                if (findLastid.size() < 7) {

                    singDanEMA3 = 0.0;
                    singDanEMA7 = 0.0;
                    singDanDIF = 0.0;
                    singDanDEA = 0.0;
                    singDanMACD = 0.0;
                    singDFortEMA3 = 0.0;
                    singDFortEMA7 = 0.0;
                    singDFortDIF = 0.0;
                    singDFortDEA = 0.0;
                    singDFortMACD = 0.0;
                    singDFortstat = 0;
                    singDanlastMACD = 0.0;
                    singDFortlastMACD = 0.0;

                    fiveDFortEMA3 = 0.0;
                    fiveDFortEMA7 = 0.0;
                    fiveDanDIF = 0.0;
                    fiveDanDEA = 0.0;
                    fiveDFortMACD = 0.0;
                    fiveDFortstat = 0;
                    fiveDFortlastMACD = 0.0;


                    sumDFortEMA3 = 0.0;
                    sumDFortEMA7 = 0.0;
                    sumDanDIF = 0.0;
                    sumDanDEA = 0.0;
                    sumDFortMACD = 0.0;
                    sumDFortstat = 0;
                    sumDFortlastMACD = 0.0;

                    kuaDFortEMA3 = 0.0;
                    kuaDFortEMA7 = 0.0;
                    kuaDanDIF = 0.0;
                    kuaDanDEA = 0.0;
                    kuaDFortMACD = 0.0;
                    kuaDFortstat = 0;
                    kuaDFortlastMACD = 0.0;

                    int Result = sanDCalService.updateSanDEMA(singDanEMA3, singDanEMA7, singDanDIF, singDanDEA, singDanMACD,
                            singDFortEMA3, singDFortEMA7, singDFortDIF, singDFortDEA, singDFortMACD, singDFortstat,
                            singDanlastMACD, singDFortlastMACD, fiveDFortEMA3, fiveDFortEMA7, fiveDanDIF, fiveDanDEA,
                            fiveDFortMACD, fiveDFortstat, fiveDFortlastMACD, sumDFortEMA3, sumDFortEMA7, sumDanDIF, sumDanDEA,
                            sumDFortMACD, sumDFortstat, sumDFortlastMACD, kuaDFortEMA3, kuaDFortEMA7, kuaDanDIF, kuaDanDEA,
                            kuaDFortMACD, kuaDFortstat, kuaDFortlastMACD, expID);
                    if (Result == 1) {
                        rcount = rcount + 1;
                    }
                }

                if (findLastid.size() >= 7) {

                    List<SandDankill> findEMALast3 = sanDCalService.selectIDbyEMA3last3(expertname, expID);
                    List<SandDankill> findEMALast7 = sanDCalService.selectIDbyEMA3last7(expertname, expID);
                    List<SandDankill> findDEALast5 = sanDCalService.selectIDbyDEAlast5(expertname, expID);

                    /*独胆预测值计算*/
                    ArrayList<Double> doubleListSingDan3 = new ArrayList<Double>();
                    for (int d1 = 0; d1 < findEMALast3.size(); d1++) {

                        try {
                            doubleListSingDan3.add(d1, Double.valueOf(findEMALast3.get(d1).getSingDan()));
                        } catch (Exception e) {
                            doubleListSingDan3.add(d1, Double.valueOf("0"));
                            e.printStackTrace();
                        }

                    }


                    singDanEMA3 = (double) Math.round(ValEMA3(doubleListSingDan3) * 100) / 100;

                    ArrayList<Double> doubleListSingDan7 = new ArrayList<Double>();
                    for (int d1 = 0; d1 < findEMALast7.size(); d1++) {

                        try {
                            doubleListSingDan7.add(d1, Double.valueOf(findEMALast7.get(d1).getSingDan()));
                        } catch (Exception e) {
                            doubleListSingDan7.add(d1, Double.valueOf("0"));
                            e.printStackTrace();
                        }

                    }

                    singDanEMA7 = (double) Math.round(ValEMA7(doubleListSingDan7) * 100) / 100;

                    singDanDIF = (double) Math.round((singDanEMA3 - singDanEMA7) * 100) / 100;

                    ArrayList<Double> doubleListSingDanDEA5 = new ArrayList<Double>();
                    for (int d1 = 0; d1 < findDEALast5.size(); d1++) {

                        if (d1 == 0) {
                            doubleListSingDanDEA5.add(d1, singDanDIF);
                        } else {

                            try {
                                doubleListSingDanDEA5.add(d1, Double.valueOf(findDEALast5.get(d1).getSingDanDIF()));
                            } catch (Exception e) {
                                doubleListSingDanDEA5.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }
                        }

                    }


                    singDanDEA = (double) Math.round(ValEMA5(doubleListSingDanDEA5) * 100) / 100;
                    singDanMACD = (double) Math.round((singDanDIF - singDanDEA) * 2 * 100) / 100;
                    doubleListSingDan3.clear();
                    doubleListSingDan7.clear();
                    doubleListSingDanDEA5.clear();

                    for (int d1 = 0; d1 < findEMALast3.size(); d1++) {

                        try {
                            if (findEMALast3.get(d1).getSingDanhit() == 1) {
                                doubleListSingDan3.add(d1, Double.valueOf("9"));
                            }

                            if (findEMALast3.get(d1).getSingDanhit() == 0) {
                                doubleListSingDan3.add(d1, Double.valueOf("0"));
                            }

                        } catch (Exception e) {
                            doubleListSingDan3.add(d1, Double.valueOf("0"));
                            e.printStackTrace();
                        }

                    }
                    singDFortEMA3 = (double) Math.round(ValEMA3(doubleListSingDan3) * 100) / 100;

                    for (int d1 = 0; d1 < findEMALast7.size(); d1++) {

                        try {
                            if (findEMALast7.get(d1).getSingDanhit() == 1) {
                                doubleListSingDan7.add(d1, Double.valueOf("9"));
                            }

                            if (findEMALast7.get(d1).getSingDanhit() == 0) {
                                doubleListSingDan7.add(d1, Double.valueOf("0"));
                            }

                        } catch (Exception e) {
                            doubleListSingDan7.add(d1, Double.valueOf("0"));
                            e.printStackTrace();
                        }

                    }

                    singDFortEMA7 = (double) Math.round(ValEMA7(doubleListSingDan7) * 100) / 100;

                    singDFortDIF = (double) Math.round((singDFortEMA3 - singDFortEMA7) * 100) / 100;

                    for (int d1 = 0; d1 < findDEALast5.size(); d1++) {

                        if (d1 == 0) {
                            doubleListSingDanDEA5.add(d1, singDFortDIF);
                        } else {

                            try {
                                doubleListSingDanDEA5.add(d1, Double.valueOf(findDEALast5.get(d1).getSingDFortDIF()));
                            } catch (Exception e) {
                                doubleListSingDanDEA5.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }
                        }

                    }

                    singDFortDEA = (double) Math.round(ValEMA5(doubleListSingDanDEA5) * 100) / 100;
                    singDFortMACD = (double) Math.round((singDFortDIF - singDFortDEA) * 2 * 100) / 100;
                    doubleListSingDan3.clear();
                    doubleListSingDan7.clear();
                    doubleListSingDanDEA5.clear();

                    /*独胆上次MACD的取值*/
                    singDanlastMACD = findEMALast3.get(1).getSingDanMACD();
                    singDFortlastMACD = findEMALast3.get(1).getSingDFortMACD();

                    /*0轴上下的判断*/


                    try {
                        if(singDFortMACD>0){

                            stringTemp.append("1");

                        }else if(singDFortMACD<0){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                      /*DIF在DEA上和下的判断*/
                    try {
                        if(findEMALast7.get(6).getSingDFortDIF()>findEMALast7.get(6).getSingDFortDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(6).getSingDFortDIF()<findEMALast7.get(6).getSingDFortDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(5).getSingDFortDIF()>findEMALast7.get(5).getSingDFortDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(5).getSingDFortDIF()<findEMALast7.get(5).getSingDFortDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(4).getSingDFortDIF()>findEMALast7.get(4).getSingDFortDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(4).getSingDFortDIF()<findEMALast7.get(4).getSingDFortDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(3).getSingDFortDIF()>findEMALast7.get(3).getSingDFortDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(3).getSingDFortDIF()<findEMALast7.get(3).getSingDFortDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(2).getSingDFortDIF()>findEMALast7.get(2).getSingDFortDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(2).getSingDFortDIF()<findEMALast7.get(2).getSingDFortDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(1).getSingDFortDIF()>findEMALast7.get(1).getSingDFortDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(1).getSingDFortDIF()<findEMALast7.get(1).getSingDFortDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(singDFortDIF>singDFortDEA){

                            stringTemp.append("1");

                        }else if(singDFortDIF<singDFortDEA){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }


                    singDFortstat = stateCal(stringTemp.toString());

                    stringTemp.delete( 0, stringTemp.length());

                    /*五胆EMA计算*/

                    ArrayList<Double> doubleListFiveDan3 = new ArrayList<Double>();
                    for (int d1 = 0; d1 < findEMALast3.size(); d1++) {

                        try {
                            if (findEMALast3.get(d1).getFiveDanhit() == 3) {
                                doubleListFiveDan3.add(d1, Double.valueOf("9"));
                            }

                            if (findEMALast3.get(d1).getFiveDanhit() == 2) {
                                doubleListFiveDan3.add(d1, Double.valueOf("5"));
                            }

                            if (findEMALast3.get(d1).getFiveDanhit() == 1) {
                                doubleListFiveDan3.add(d1, Double.valueOf("3"));
                            }

                            if (findEMALast3.get(d1).getFiveDanhit() == 0) {
                                doubleListFiveDan3.add(d1, Double.valueOf("0"));
                            }

                        } catch (Exception e) {
                            doubleListFiveDan3.add(d1, Double.valueOf("0"));
                            e.printStackTrace();
                        }

                    }

                    fiveDFortEMA3 = (double) Math.round(ValEMA3(doubleListFiveDan3) * 100) / 100;

                    ArrayList<Double> doubleListFiveDan7 = new ArrayList<Double>();

                    for (int d1 = 0; d1 < findEMALast7.size(); d1++) {

                        try {
                            if (findEMALast7.get(d1).getFiveDanhit() == 3) {
                                doubleListFiveDan7.add(d1, Double.valueOf("9"));
                            }

                            if (findEMALast7.get(d1).getFiveDanhit() == 2) {
                                doubleListFiveDan7.add(d1, Double.valueOf("5"));
                            }

                            if (findEMALast7.get(d1).getFiveDanhit() == 1) {
                                doubleListFiveDan7.add(d1, Double.valueOf("3"));
                            }

                            if (findEMALast7.get(d1).getFiveDanhit() == 0) {
                                doubleListFiveDan7.add(d1, Double.valueOf("0"));
                            }

                        } catch (Exception e) {
                            doubleListFiveDan7.add(d1, Double.valueOf("0"));
                            e.printStackTrace();
                        }

                    }

                    fiveDFortEMA7 = (double) Math.round(ValEMA7(doubleListFiveDan7) * 100) / 100;

                    fiveDanDIF = (double) Math.round((fiveDFortEMA3 - fiveDFortEMA7) * 100) / 100;

                    ArrayList<Double> doubleListFiveDanDEA5 = new ArrayList<Double>();

                    for (int d1 = 0; d1 < findDEALast5.size(); d1++) {

                        if (d1 == 0) {
                            doubleListFiveDanDEA5.add(d1, fiveDanDIF);
                        } else {

                            try {
                                doubleListFiveDanDEA5.add(d1, Double.valueOf(findDEALast5.get(d1).getFiveDanDIF()));
                            } catch (Exception e) {
                                doubleListFiveDanDEA5.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }
                        }

                    }

                    fiveDanDEA = (double) Math.round(ValEMA5(doubleListFiveDanDEA5) * 100) / 100;
                    fiveDFortMACD = (double) Math.round((fiveDanDIF - fiveDanDEA) * 2 * 100) / 100;
                    doubleListFiveDan3.clear();
                    doubleListFiveDan7.clear();
                    doubleListFiveDanDEA5.clear();

                    /*五胆上次MACD的取值*/

                    fiveDFortlastMACD = findEMALast3.get(1).getFiveDFortMACD();



                        /*0轴上下的判断*/

                    try {
                        if(fiveDFortMACD > 0){

                            stringTemp.append("1");

                        }else if(fiveDFortMACD < 0){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                      /*DIF在DEA上和下的判断*/
                    try {
                        if(findEMALast7.get(6).getFiveDanDIF()>findEMALast7.get(6).getFiveDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(6).getFiveDanDIF()<findEMALast7.get(6).getFiveDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(5).getFiveDanDIF()>findEMALast7.get(5).getFiveDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(5).getFiveDanDIF()<findEMALast7.get(5).getFiveDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(4).getFiveDanDIF()>findEMALast7.get(4).getFiveDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(4).getFiveDanDIF()<findEMALast7.get(4).getFiveDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(3).getFiveDanDIF()>findEMALast7.get(3).getFiveDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(3).getFiveDanDIF()<findEMALast7.get(3).getFiveDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(2).getFiveDanDIF()>findEMALast7.get(2).getFiveDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(2).getFiveDanDIF()<findEMALast7.get(2).getFiveDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(1).getFiveDanDIF()>findEMALast7.get(1).getFiveDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(1).getFiveDanDIF()<findEMALast7.get(1).getFiveDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(fiveDanDIF>fiveDanDEA){

                            stringTemp.append("1");

                        }else if(fiveDanDIF<fiveDanDEA){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }


                    fiveDFortstat = stateCal(stringTemp.toString());

                    stringTemp.delete( 0, stringTemp.length());



                    /*和值EMA计算*/

                    ArrayList<Double> doubleListSumDan3 = new ArrayList<Double>();
                    for (int d1 = 0; d1 < findEMALast3.size(); d1++) {

                        try {

                            if (findEMALast3.get(d1).getSumDanhit() == 1) {
                                doubleListSumDan3.add(d1, Double.valueOf("5"));
                            }

                            if (findEMALast3.get(d1).getSumDanhit() == 0) {
                                doubleListSumDan3.add(d1, Double.valueOf("0"));
                            }

                        } catch (Exception e) {
                            doubleListSumDan3.add(d1, Double.valueOf("0"));
                            e.printStackTrace();
                        }

                    }
                    sumDFortEMA3 = (double) Math.round(ValEMA3(doubleListSumDan3) * 100) / 100;

                    ArrayList<Double> doubleListSumDan7 = new ArrayList<Double>();

                    for (int d1 = 0; d1 < findEMALast7.size(); d1++) {

                        try {

                            if (findEMALast7.get(d1).getSumDanhit() == 1) {
                                doubleListSumDan7.add(d1, Double.valueOf("5"));
                            }

                            if (findEMALast7.get(d1).getSumDanhit() == 0) {
                                doubleListSumDan7.add(d1, Double.valueOf("0"));
                            }

                        } catch (Exception e) {
                            doubleListSumDan7.add(d1, Double.valueOf("0"));
                            e.printStackTrace();
                        }

                    }

                    sumDFortEMA7 = (double) Math.round(ValEMA7(doubleListSumDan7) * 100) / 100;

                    sumDanDIF = (double) Math.round((sumDFortEMA3 - sumDFortEMA7) * 100) / 100;

                    ArrayList<Double> doubleListSumDanDEA5 = new ArrayList<Double>();

                    for (int d1 = 0; d1 < findDEALast5.size(); d1++) {

                        if (d1 == 0) {
                            doubleListSumDanDEA5.add(d1, sumDanDIF);
                        } else {

                            try {
                                doubleListSumDanDEA5.add(d1, Double.valueOf(findDEALast5.get(d1).getSumDanDIF()));
                            } catch (Exception e) {
                                doubleListSumDanDEA5.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }
                        }

                    }

                    sumDanDEA = (double) Math.round(ValEMA5(doubleListSumDanDEA5) * 100) / 100;
                    sumDFortMACD = (double) Math.round((sumDanDIF - sumDanDEA) * 2 * 100) / 100;
                    doubleListSumDan3.clear();
                    doubleListSumDan7.clear();
                    doubleListSumDanDEA5.clear();


                    /*和值上次MACD的取值*/

                    sumDFortlastMACD = findEMALast3.get(1).getSumDFortMACD();



                        /*0轴上下的判断*/

                    try {
                        if(sumDFortMACD > 0){

                            stringTemp.append("1");

                        }else if(sumDFortMACD < 0){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                      /*DIF在DEA上和下的判断*/
                    try {
                        if(findEMALast7.get(6).getSumDanDIF()>findEMALast7.get(6).getSumDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(6).getSumDanDIF()<findEMALast7.get(6).getSumDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(5).getSumDanDIF()>findEMALast7.get(5).getSumDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(5).getSumDanDIF()<findEMALast7.get(5).getSumDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(4).getSumDanDIF()>findEMALast7.get(4).getSumDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(4).getSumDanDIF()<findEMALast7.get(4).getSumDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(3).getSumDanDIF()>findEMALast7.get(3).getSumDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(3).getSumDanDIF()<findEMALast7.get(3).getSumDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(2).getSumDanDIF()>findEMALast7.get(2).getSumDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(2).getSumDanDIF()<findEMALast7.get(2).getSumDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(1).getSumDanDIF()>findEMALast7.get(1).getSumDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(1).getSumDanDIF()<findEMALast7.get(1).getSumDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(sumDanDIF>sumDanDEA){

                            stringTemp.append("1");

                        }else if(sumDanDIF<sumDanDEA){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }


                    sumDFortstat = stateCal(stringTemp.toString());

                    stringTemp.delete( 0, stringTemp.length());

                    /*跨度值EMA计算*/

                    ArrayList<Double> doubleListKuaDan3 = new ArrayList<Double>();
                    for (int d1 = 0; d1 < findEMALast3.size(); d1++) {

                        try {

                            if (findEMALast3.get(d1).getKuaDanhit() == 1) {
                                doubleListKuaDan3.add(d1, Double.valueOf("3"));
                            }

                            if (findEMALast3.get(d1).getKuaDanhit() == 0) {
                                doubleListKuaDan3.add(d1, Double.valueOf("0"));
                            }

                        } catch (Exception e) {
                            doubleListKuaDan3.add(d1, Double.valueOf("0"));
                            e.printStackTrace();
                        }

                    }
                    kuaDFortEMA3 = (double) Math.round(ValEMA3(doubleListKuaDan3) * 100) / 100;

                    ArrayList<Double> doubleListKuaDan7 = new ArrayList<Double>();

                    for (int d1 = 0; d1 < findEMALast7.size(); d1++) {

                        try {

                            if (findEMALast7.get(d1).getKuaDanhit() == 1) {
                                doubleListKuaDan7.add(d1, Double.valueOf("3"));
                            }

                            if (findEMALast7.get(d1).getKuaDanhit() == 0) {
                                doubleListKuaDan7.add(d1, Double.valueOf("0"));
                            }

                        } catch (Exception e) {
                            doubleListKuaDan7.add(d1, Double.valueOf("0"));
                            e.printStackTrace();
                        }

                    }

                    kuaDFortEMA7 = (double) Math.round(ValEMA7(doubleListKuaDan7) * 100) / 100;

                    kuaDanDIF = (double) Math.round((kuaDFortEMA3 - kuaDFortEMA7) * 100) / 100;

                    ArrayList<Double> doubleListKuaDanDEA5 = new ArrayList<Double>();

                    for (int d1 = 0; d1 < findDEALast5.size(); d1++) {

                        if (d1 == 0) {
                            doubleListKuaDanDEA5.add(d1, kuaDanDIF);
                        } else {

                            try {
                                doubleListKuaDanDEA5.add(d1, Double.valueOf(findDEALast5.get(d1).getKuaDanDIF()));
                            } catch (Exception e) {
                                doubleListKuaDanDEA5.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }
                        }

                    }

                    kuaDanDEA = (double) Math.round(ValEMA5(doubleListKuaDanDEA5) * 100) / 100;
                    kuaDFortMACD =(double) Math.round((kuaDanDIF - kuaDanDEA) * 2 * 100) / 100 ;
                    doubleListKuaDan3.clear();
                    doubleListKuaDan7.clear();
                    doubleListKuaDanDEA5.clear();


                    /*跨度值上次MACD的取值*/

                    kuaDFortlastMACD = findEMALast3.get(1).getKuaDFortMACD();



                        /*0轴上下的判断*/

                    try {
                        if(kuaDFortMACD > 0){

                            stringTemp.append("1");

                        }else if(kuaDFortMACD < 0){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                      /*DIF在DEA上和下的判断*/
                    try {
                        if(findEMALast7.get(6).getKuaDanDIF()>findEMALast7.get(6).getKuaDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(6).getKuaDanDIF()<findEMALast7.get(6).getKuaDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(5).getKuaDanDIF()>findEMALast7.get(5).getKuaDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(5).getKuaDanDIF()<findEMALast7.get(5).getKuaDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(4).getKuaDanDIF()>findEMALast7.get(4).getKuaDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(4).getKuaDanDIF()<findEMALast7.get(4).getKuaDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(3).getKuaDanDIF()>findEMALast7.get(3).getKuaDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(3).getKuaDanDIF()<findEMALast7.get(3).getKuaDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(2).getKuaDanDIF()>findEMALast7.get(2).getKuaDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(2).getKuaDanDIF()<findEMALast7.get(2).getKuaDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(findEMALast7.get(1).getKuaDanDIF()>findEMALast7.get(1).getKuaDanDEA()){

                            stringTemp.append("1");

                        }else if(findEMALast7.get(1).getKuaDanDIF()<findEMALast7.get(1).getKuaDanDEA()){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }

                    try {
                        if(kuaDanDIF>kuaDanDEA){

                            stringTemp.append("1");

                        }else if(kuaDanDIF<kuaDanDEA){

                            stringTemp.append("0");

                        }else{

                            stringTemp.append("9");

                        }
                    } catch (Exception e) {
                        stringTemp.append("9");
                        e.printStackTrace();
                    }


                    kuaDFortstat = stateCal(stringTemp.toString());

                    stringTemp.delete( 0, stringTemp.length());


                    int Result = sanDCalService.updateSanDEMA(singDanEMA3, singDanEMA7, singDanDIF, singDanDEA, singDanMACD,
                            singDFortEMA3, singDFortEMA7, singDFortDIF, singDFortDEA, singDFortMACD, singDFortstat,
                            singDanlastMACD, singDFortlastMACD, fiveDFortEMA3, fiveDFortEMA7, fiveDanDIF, fiveDanDEA,
                            fiveDFortMACD, fiveDFortstat, fiveDFortlastMACD, sumDFortEMA3, sumDFortEMA7, sumDanDIF, sumDanDEA,
                            sumDFortMACD, sumDFortstat, sumDFortlastMACD, kuaDFortEMA3, kuaDFortEMA7, kuaDanDIF, kuaDanDEA,
                            kuaDFortMACD, kuaDFortstat, kuaDFortlastMACD, expID);

                    if (Result == 1) {
                        rcount = rcount + 1;
                    }

                }

            }


            if (findid.size() > 1) {

                for (int j = 0; j < findid.size(); j++) {
                    fid = findid.get(j).getId();
                    List<SandDankill> findLastid = sanDCalService.selectIDbyEMA3last7(expertname, fid);
                    if (findLastid.size() < 7) {

                        singDanEMA3 = 0.0;
                        singDanEMA7 = 0.0;
                        singDanDIF = 0.0;
                        singDanDEA = 0.0;
                        singDanMACD = 0.0;
                        singDFortEMA3 = 0.0;
                        singDFortEMA7 = 0.0;
                        singDFortDIF = 0.0;
                        singDFortDEA = 0.0;
                        singDFortMACD = 0.0;
                        singDFortstat = 0;
                        singDanlastMACD = 0.0;
                        singDFortlastMACD = 0.0;

                        fiveDFortEMA3 = 0.0;
                        fiveDFortEMA7 = 0.0;
                        fiveDanDIF = 0.0;
                        fiveDanDEA = 0.0;
                        fiveDFortMACD = 0.0;
                        fiveDFortstat = 0;
                        fiveDFortlastMACD = 0.0;


                        sumDFortEMA3 = 0.0;
                        sumDFortEMA7 = 0.0;
                        sumDanDIF = 0.0;
                        sumDanDEA = 0.0;
                        sumDFortMACD = 0.0;
                        sumDFortstat = 0;
                        sumDFortlastMACD = 0.0;

                        kuaDFortEMA3 = 0.0;
                        kuaDFortEMA7 = 0.0;
                        kuaDanDIF = 0.0;
                        kuaDanDEA = 0.0;
                        kuaDFortMACD = 0.0;
                        kuaDFortstat = 0;
                        kuaDFortlastMACD = 0.0;

                        int Result = sanDCalService.updateSanDEMA(singDanEMA3, singDanEMA7, singDanDIF, singDanDEA, singDanMACD,
                                singDFortEMA3, singDFortEMA7, singDFortDIF, singDFortDEA, singDFortMACD, singDFortstat,
                                singDanlastMACD, singDFortlastMACD, fiveDFortEMA3, fiveDFortEMA7, fiveDanDIF, fiveDanDEA,
                                fiveDFortMACD, fiveDFortstat, fiveDFortlastMACD, sumDFortEMA3, sumDFortEMA7, sumDanDIF, sumDanDEA,
                                sumDFortMACD, sumDFortstat, sumDFortlastMACD, kuaDFortEMA3, kuaDFortEMA7, kuaDanDIF, kuaDanDEA,
                                kuaDFortMACD, kuaDFortstat, kuaDFortlastMACD, fid);
                        if (Result == 1) {
                            rcount = rcount + 1;
                        }
                    }

                    if (findLastid.size() >= 7) {

                        List<SandDankill> findEMALast3 = sanDCalService.selectIDbyEMA3last3(expertname, fid);
                        List<SandDankill> findEMALast7 = sanDCalService.selectIDbyEMA3last7(expertname, fid);
                        List<SandDankill> findDEALast5 = sanDCalService.selectIDbyDEAlast5(expertname, fid);

                    /*独胆预测值计算*/
                        ArrayList<Double> doubleListSingDan3 = new ArrayList<Double>();
                        for (int d1 = 0; d1 < findEMALast3.size(); d1++) {

                            try {
                                doubleListSingDan3.add(d1, Double.valueOf(findEMALast3.get(d1).getSingDan()));
                            } catch (Exception e) {
                                doubleListSingDan3.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }

                        }

                        singDanEMA3 = (double) Math.round(ValEMA3(doubleListSingDan3) * 100) / 100;

                        ArrayList<Double> doubleListSingDan7 = new ArrayList<Double>();
                        for (int d1 = 0; d1 < findEMALast7.size(); d1++) {

                            try {
                                doubleListSingDan7.add(d1, Double.valueOf(findEMALast7.get(d1).getSingDan()));
                            } catch (Exception e) {
                                doubleListSingDan7.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }

                        }

                        singDanEMA7 = (double) Math.round(ValEMA7(doubleListSingDan7) * 100) / 100;

                        singDanDIF = (double) Math.round((singDanEMA3 - singDanEMA7) * 100) / 100;

                        ArrayList<Double> doubleListSingDanDEA5 = new ArrayList<Double>();
                        for (int d1 = 0; d1 < findDEALast5.size(); d1++) {

                            if (d1 == 0) {
                                doubleListSingDanDEA5.add(d1, singDanDIF);
                            } else {

                                try {
                                    doubleListSingDanDEA5.add(d1, Double.valueOf(findDEALast5.get(d1).getSingDanDIF()));
                                } catch (Exception e) {
                                    doubleListSingDanDEA5.add(d1, Double.valueOf("0"));
                                    e.printStackTrace();
                                }
                            }

                        }


                        singDanDEA = (double) Math.round(ValEMA5(doubleListSingDanDEA5) * 100) / 100;
                        singDanMACD = (double) Math.round((singDanDIF - singDanDEA) * 2* 100) / 100;
                        doubleListSingDan3.clear();
                        doubleListSingDan7.clear();
                        doubleListSingDanDEA5.clear();

                        for (int d1 = 0; d1 < findEMALast3.size(); d1++) {

                            try {
                                if (findEMALast3.get(d1).getSingDanhit() == 1) {
                                    doubleListSingDan3.add(d1, Double.valueOf("9"));
                                }

                                if (findEMALast3.get(d1).getSingDanhit() == 0) {
                                    doubleListSingDan3.add(d1, Double.valueOf("0"));
                                }

                            } catch (Exception e) {
                                doubleListSingDan3.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }

                        }
                        singDFortEMA3 = (double) Math.round(ValEMA3(doubleListSingDan3)* 100) / 100;

                        for (int d1 = 0; d1 < findEMALast7.size(); d1++) {

                            try {
                                if (findEMALast7.get(d1).getSingDanhit() == 1) {
                                    doubleListSingDan7.add(d1, Double.valueOf("9"));
                                }

                                if (findEMALast7.get(d1).getSingDanhit() == 0) {
                                    doubleListSingDan7.add(d1, Double.valueOf("0"));
                                }

                            } catch (Exception e) {
                                doubleListSingDan7.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }

                        }

                        singDFortEMA7 = (double) Math.round(ValEMA7(doubleListSingDan7) * 100) / 100;

                        singDFortDIF = (double) Math.round((singDFortEMA3 - singDFortEMA7) * 100) / 100;

                        for (int d1 = 0; d1 < findDEALast5.size(); d1++) {

                            if (d1 == 0) {
                                doubleListSingDanDEA5.add(d1, singDFortDIF);
                            } else {

                                try {
                                    doubleListSingDanDEA5.add(d1, Double.valueOf(findDEALast5.get(d1).getSingDFortDIF()));
                                } catch (Exception e) {
                                    doubleListSingDanDEA5.add(d1, Double.valueOf("0"));
                                    e.printStackTrace();
                                }
                            }

                        }

                        singDFortDEA = (double) Math.round(ValEMA5(doubleListSingDanDEA5) * 100) / 100;
                        singDFortMACD = (double) Math.round((singDFortDIF - singDFortDEA) * 2 * 100) / 100;
                        doubleListSingDan3.clear();
                        doubleListSingDan7.clear();
                        doubleListSingDanDEA5.clear();

                    /*独胆上次MACD的取值*/
                        singDanlastMACD = findEMALast3.get(1).getSingDanMACD();
                        singDFortlastMACD = findEMALast3.get(1).getSingDFortMACD();
                  /*0轴上下的判断*/

                        try {
                            if(singDFortMACD>0){

                                stringTemp.append("1");

                            }else if(singDFortMACD<0){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                      /*DIF在DEA上和下的判断*/
                        try {
                            if(findEMALast7.get(6).getSingDFortDIF()>findEMALast7.get(6).getSingDFortDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(6).getSingDFortDIF()<findEMALast7.get(6).getSingDFortDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(5).getSingDFortDIF()>findEMALast7.get(5).getSingDFortDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(5).getSingDFortDIF()<findEMALast7.get(5).getSingDFortDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(4).getSingDFortDIF()>findEMALast7.get(4).getSingDFortDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(4).getSingDFortDIF()<findEMALast7.get(4).getSingDFortDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(3).getSingDFortDIF()>findEMALast7.get(3).getSingDFortDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(3).getSingDFortDIF()<findEMALast7.get(3).getSingDFortDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(2).getSingDFortDIF()>findEMALast7.get(2).getSingDFortDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(2).getSingDFortDIF()<findEMALast7.get(2).getSingDFortDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(1).getSingDFortDIF()>findEMALast7.get(1).getSingDFortDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(1).getSingDFortDIF()<findEMALast7.get(1).getSingDFortDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(singDFortDIF>singDFortDEA){

                                stringTemp.append("1");

                            }else if(singDFortDIF<singDFortDEA){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }


                        singDFortstat = stateCal(stringTemp.toString());

                        stringTemp.delete( 0, stringTemp.length());

                                 /*五胆EMA计算*/

                        ArrayList<Double> doubleListFiveDan3 = new ArrayList<Double>();
                        for (int d1 = 0; d1 < findEMALast3.size(); d1++) {

                            try {
                                if (findEMALast3.get(d1).getFiveDanhit() == 3) {
                                    doubleListFiveDan3.add(d1, Double.valueOf("9"));
                                }

                                if (findEMALast3.get(d1).getFiveDanhit() == 2) {
                                    doubleListFiveDan3.add(d1, Double.valueOf("5"));
                                }

                                if (findEMALast3.get(d1).getFiveDanhit() == 1) {
                                    doubleListFiveDan3.add(d1, Double.valueOf("3"));
                                }

                                if (findEMALast3.get(d1).getFiveDanhit() == 0) {
                                    doubleListFiveDan3.add(d1, Double.valueOf("0"));
                                }

                            } catch (Exception e) {
                                doubleListFiveDan3.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }

                        }

                        fiveDFortEMA3 = (double) Math.round(ValEMA3(doubleListFiveDan3) * 100) / 100;

                        ArrayList<Double> doubleListFiveDan7 = new ArrayList<Double>();

                        for (int d1 = 0; d1 < findEMALast7.size(); d1++) {

                            try {
                                if (findEMALast7.get(d1).getFiveDanhit() == 3) {
                                    doubleListFiveDan7.add(d1, Double.valueOf("9"));
                                }

                                if (findEMALast7.get(d1).getFiveDanhit() == 2) {
                                    doubleListFiveDan7.add(d1, Double.valueOf("5"));
                                }

                                if (findEMALast7.get(d1).getFiveDanhit() == 1) {
                                    doubleListFiveDan7.add(d1, Double.valueOf("3"));
                                }

                                if (findEMALast7.get(d1).getFiveDanhit() == 0) {
                                    doubleListFiveDan7.add(d1, Double.valueOf("0"));
                                }

                            } catch (Exception e) {
                                doubleListFiveDan7.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }

                        }

                        fiveDFortEMA7 = (double) Math.round(ValEMA7(doubleListFiveDan7) * 100) / 100;

                        fiveDanDIF = (double) Math.round((fiveDFortEMA3 - fiveDFortEMA7) * 100) / 100;

                        ArrayList<Double> doubleListFiveDanDEA5 = new ArrayList<Double>();

                        for (int d1 = 0; d1 < findDEALast5.size(); d1++) {

                            if (d1 == 0) {
                                doubleListFiveDanDEA5.add(d1, fiveDanDIF);
                            } else {

                                try {
                                    doubleListFiveDanDEA5.add(d1, Double.valueOf(findDEALast5.get(d1).getFiveDanDIF()));
                                } catch (Exception e) {
                                    doubleListFiveDanDEA5.add(d1, Double.valueOf("0"));
                                    e.printStackTrace();
                                }
                            }

                        }

                        fiveDanDEA = (double) Math.round(ValEMA5(doubleListFiveDanDEA5) * 100) / 100;
                        fiveDFortMACD = (double) Math.round((fiveDanDIF - fiveDanDEA) * 2 * 100) / 100;
                        doubleListFiveDan3.clear();
                        doubleListFiveDan7.clear();
                        doubleListFiveDanDEA5.clear();

                    /*五胆上次MACD的取值*/

                        fiveDFortlastMACD = findEMALast3.get(1).getFiveDFortMACD();


                        /*0轴上下的判断*/

                        try {
                            if(fiveDFortMACD > 0){

                                stringTemp.append("1");

                            }else if(fiveDFortMACD < 0){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                      /*DIF在DEA上和下的判断*/
                        try {
                            if(findEMALast7.get(6).getFiveDanDIF()>findEMALast7.get(6).getFiveDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(6).getFiveDanDIF()<findEMALast7.get(6).getFiveDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(5).getFiveDanDIF()>findEMALast7.get(5).getFiveDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(5).getFiveDanDIF()<findEMALast7.get(5).getFiveDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(4).getFiveDanDIF()>findEMALast7.get(4).getFiveDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(4).getFiveDanDIF()<findEMALast7.get(4).getFiveDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(3).getFiveDanDIF()>findEMALast7.get(3).getFiveDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(3).getFiveDanDIF()<findEMALast7.get(3).getFiveDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(2).getFiveDanDIF()>findEMALast7.get(2).getFiveDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(2).getFiveDanDIF()<findEMALast7.get(2).getFiveDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(1).getFiveDanDIF()>findEMALast7.get(1).getFiveDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(1).getFiveDanDIF()<findEMALast7.get(1).getFiveDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(fiveDanDIF>fiveDanDEA){

                                stringTemp.append("1");

                            }else if(fiveDanDIF<fiveDanDEA){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }


                        fiveDFortstat = stateCal(stringTemp.toString());

                        stringTemp.delete( 0, stringTemp.length());



                    /*和值EMA计算*/

                        ArrayList<Double> doubleListSumDan3 = new ArrayList<Double>();
                        for (int d1 = 0; d1 < findEMALast3.size(); d1++) {

                            try {

                                if (findEMALast3.get(d1).getSumDanhit() == 1) {
                                    doubleListSumDan3.add(d1, Double.valueOf("5"));
                                }

                                if (findEMALast3.get(d1).getSumDanhit() == 0) {
                                    doubleListSumDan3.add(d1, Double.valueOf("0"));
                                }

                            } catch (Exception e) {
                                doubleListSumDan3.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }

                        }
                        sumDFortEMA3 = (double) Math.round(ValEMA3(doubleListSumDan3) * 100) / 100;

                        ArrayList<Double> doubleListSumDan7 = new ArrayList<Double>();

                        for (int d1 = 0; d1 < findEMALast7.size(); d1++) {

                            try {

                                if (findEMALast7.get(d1).getSumDanhit() == 1) {
                                    doubleListSumDan7.add(d1, Double.valueOf("5"));
                                }

                                if (findEMALast7.get(d1).getSumDanhit() == 0) {
                                    doubleListSumDan7.add(d1, Double.valueOf("0"));
                                }

                            } catch (Exception e) {
                                doubleListSumDan7.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }

                        }

                        sumDFortEMA7 = (double) Math.round(ValEMA7(doubleListSumDan7) * 100) / 100;

                        sumDanDIF = (double) Math.round((sumDFortEMA3 - sumDFortEMA7) * 100) / 100;

                        ArrayList<Double> doubleListSumDanDEA5 = new ArrayList<Double>();

                        for (int d1 = 0; d1 < findDEALast5.size(); d1++) {

                            if (d1 == 0) {
                                doubleListSumDanDEA5.add(d1, sumDanDIF);
                            } else {

                                try {
                                    doubleListSumDanDEA5.add(d1, Double.valueOf(findDEALast5.get(d1).getSumDanDIF()));
                                } catch (Exception e) {
                                    doubleListSumDanDEA5.add(d1, Double.valueOf("0"));
                                    e.printStackTrace();
                                }
                            }

                        }

                        sumDanDEA = (double) Math.round(ValEMA5(doubleListSumDanDEA5) * 100) / 100;
                        sumDFortMACD = (double) Math.round((sumDanDIF - sumDanDEA) * 2 * 100) / 100;
                        doubleListSumDan3.clear();
                        doubleListSumDan7.clear();
                        doubleListSumDanDEA5.clear();


                    /*和值上次MACD的取值*/

                        sumDFortlastMACD = findEMALast3.get(1).getSumDFortMACD();

                        /*0轴上下的判断*/

                        try {
                            if(sumDFortMACD > 0){

                                stringTemp.append("1");

                            }else if(sumDFortMACD < 0){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                      /*DIF在DEA上和下的判断*/
                        try {
                            if(findEMALast7.get(6).getSumDanDIF()>findEMALast7.get(6).getSumDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(6).getSumDanDIF()<findEMALast7.get(6).getSumDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(5).getSumDanDIF()>findEMALast7.get(5).getSumDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(5).getSumDanDIF()<findEMALast7.get(5).getSumDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(4).getSumDanDIF()>findEMALast7.get(4).getSumDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(4).getSumDanDIF()<findEMALast7.get(4).getSumDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(3).getSumDanDIF()>findEMALast7.get(3).getSumDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(3).getSumDanDIF()<findEMALast7.get(3).getSumDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(2).getSumDanDIF()>findEMALast7.get(2).getSumDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(2).getSumDanDIF()<findEMALast7.get(2).getSumDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(1).getSumDanDIF()>findEMALast7.get(1).getSumDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(1).getSumDanDIF()<findEMALast7.get(1).getSumDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(sumDanDIF>sumDanDEA){

                                stringTemp.append("1");

                            }else if(sumDanDIF<sumDanDEA){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }


                        sumDFortstat = stateCal(stringTemp.toString());

                        stringTemp.delete( 0, stringTemp.length());

                    /*跨度值EMA计算*/

                        ArrayList<Double> doubleListKuaDan3 = new ArrayList<Double>();
                        for (int d1 = 0; d1 < findEMALast3.size(); d1++) {

                            try {

                                if (findEMALast3.get(d1).getKuaDanhit() == 1) {
                                    doubleListKuaDan3.add(d1, Double.valueOf("3"));
                                }

                                if (findEMALast3.get(d1).getKuaDanhit() == 0) {
                                    doubleListKuaDan3.add(d1, Double.valueOf("0"));
                                }

                            } catch (Exception e) {
                                doubleListKuaDan3.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }

                        }
                        kuaDFortEMA3 = (double) Math.round(ValEMA3(doubleListKuaDan3) * 100) / 100;

                        ArrayList<Double> doubleListKuaDan7 = new ArrayList<Double>();

                        for (int d1 = 0; d1 < findEMALast7.size(); d1++) {

                            try {

                                if (findEMALast7.get(d1).getKuaDanhit() == 1) {
                                    doubleListKuaDan7.add(d1, Double.valueOf("3"));
                                }

                                if (findEMALast7.get(d1).getKuaDanhit() == 0) {
                                    doubleListKuaDan7.add(d1, Double.valueOf("0"));
                                }

                            } catch (Exception e) {
                                doubleListKuaDan7.add(d1, Double.valueOf("0"));
                                e.printStackTrace();
                            }

                        }

                        kuaDFortEMA7 = (double) Math.round(ValEMA7(doubleListKuaDan7) * 100) / 100;

                        kuaDanDIF = (double) Math.round((kuaDFortEMA3 - kuaDFortEMA7) * 100) / 100;

                        ArrayList<Double> doubleListKuaDanDEA5 = new ArrayList<Double>();

                        for (int d1 = 0; d1 < findDEALast5.size(); d1++) {

                            if (d1 == 0) {
                                doubleListKuaDanDEA5.add(d1, kuaDanDIF);
                            } else {

                                try {
                                    doubleListKuaDanDEA5.add(d1, Double.valueOf(findDEALast5.get(d1).getKuaDanDIF()));
                                } catch (Exception e) {
                                    doubleListKuaDanDEA5.add(d1, Double.valueOf("0"));
                                    e.printStackTrace();
                                }
                            }

                        }

                        kuaDanDEA = (double) Math.round(ValEMA5(doubleListKuaDanDEA5) * 100) / 100;
                        kuaDFortMACD = (double) Math.round((kuaDanDIF - kuaDanDEA) * 2 * 100) / 100;
                        doubleListKuaDan3.clear();
                        doubleListKuaDan7.clear();
                        doubleListKuaDanDEA5.clear();


                    /*跨度值上次MACD的取值*/

                        kuaDFortlastMACD = findEMALast3.get(1).getKuaDFortMACD();


                        /*0轴上下的判断*/

                        try {
                            if(kuaDFortMACD > 0){

                                stringTemp.append("1");

                            }else if(kuaDFortMACD < 0){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                      /*DIF在DEA上和下的判断*/
                        try {
                            if(findEMALast7.get(6).getKuaDanDIF()>findEMALast7.get(6).getKuaDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(6).getKuaDanDIF()<findEMALast7.get(6).getKuaDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(5).getKuaDanDIF()>findEMALast7.get(5).getKuaDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(5).getKuaDanDIF()<findEMALast7.get(5).getKuaDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(4).getKuaDanDIF()>findEMALast7.get(4).getKuaDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(4).getKuaDanDIF()<findEMALast7.get(4).getKuaDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(3).getKuaDanDIF()>findEMALast7.get(3).getKuaDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(3).getKuaDanDIF()<findEMALast7.get(3).getKuaDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(2).getKuaDanDIF()>findEMALast7.get(2).getKuaDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(2).getKuaDanDIF()<findEMALast7.get(2).getKuaDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(findEMALast7.get(1).getKuaDanDIF()>findEMALast7.get(1).getKuaDanDEA()){

                                stringTemp.append("1");

                            }else if(findEMALast7.get(1).getKuaDanDIF()<findEMALast7.get(1).getKuaDanDEA()){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }

                        try {
                            if(kuaDanDIF>kuaDanDEA){

                                stringTemp.append("1");

                            }else if(kuaDanDIF<kuaDanDEA){

                                stringTemp.append("0");

                            }else{

                                stringTemp.append("9");

                            }
                        } catch (Exception e) {
                            stringTemp.append("9");
                            e.printStackTrace();
                        }


                        kuaDFortstat = stateCal(stringTemp.toString());

                        stringTemp.delete( 0, stringTemp.length());


                        int Result = sanDCalService.updateSanDEMA(singDanEMA3, singDanEMA7, singDanDIF, singDanDEA, singDanMACD,
                                singDFortEMA3, singDFortEMA7, singDFortDIF, singDFortDEA, singDFortMACD, singDFortstat,
                                singDanlastMACD, singDFortlastMACD, fiveDFortEMA3, fiveDFortEMA7, fiveDanDIF, fiveDanDEA,
                                fiveDFortMACD, fiveDFortstat, fiveDFortlastMACD, sumDFortEMA3, sumDFortEMA7, sumDanDIF, sumDanDEA,
                                sumDFortMACD, sumDFortstat, sumDFortlastMACD, kuaDFortEMA3, kuaDFortEMA7, kuaDanDIF, kuaDanDEA,
                                kuaDFortMACD, kuaDFortstat, kuaDFortlastMACD, fid);

                        if (Result == 1) {
                            rcount = rcount + 1;
                        }

                    }
                }
            }



        }
    }

    public int getIncreDcount(){
        return this.rcount;

    }



    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        CalSanDanMACDTask calSanDanMACDTask = (CalSanDanMACDTask) context.getBean("CalSanDanMACDTask");

        //       SanDCalService sanDCalService = (SanDCalService) context.getBean("SanDCalService");
        System.out.print("测试");

        try {
            calSanDanMACDTask.pushIcrementData();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}