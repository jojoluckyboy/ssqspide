package com.spider.lottery.precodition.task;

import com.spider.lottery.pojo.SsqThred;
import com.spider.lottery.ssqservice.SsqCalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description：增添机器学习模型数据
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/12/04
 * Time：9:55
 * Copyright© 2003-2016 Zhejiang huixin technology company
 *
 */

@Service
public class CalMLTask {

    @Autowired
    private SsqCalService ssqCalService;


    /**
     * 前置准备导入历史数据
     *
     * @return 0 or 1
     * @throws Exception
     */

    /*散度计算*/
    public int divergence(String[] orired )throws Exception {

        int red1 = Integer.parseInt(orired[0]);
        int red2 = Integer.parseInt(orired[1]);
        int red3 = Integer.parseInt(orired[2]);
        int a = Math.abs(red1-red2)<=Math.abs(red1-red3)?Math.abs(red1-red2):Math.abs(red1-red3);
        int b = Math.abs(red2-red1)<=Math.abs(red2-red3)?Math.abs(red2-red1):Math.abs(red2-red3);
        int c = Math.abs(red3-red1)<=Math.abs(red3-red2)?Math.abs(red3-red1):Math.abs(red3-red2);
        int max = a>=b?a:b;
        int divergence = max>=c?max:c;
        return divergence;

    }
    /*偏度计算*/
    public int skewness(String[] orired,String[] lastrecord )throws Exception {
        int red3k;
        int matchV=0;
        int matchT;
        int a = 0;
        int b = 0;
        int c = 0;

        for (int i = 0; i < orired.length; i++) {
            red3k = Integer.parseInt(orired[i]);
            for (int j = 0; j < lastrecord.length; j++) {
                if (j==0){
                    matchV=Math.abs(red3k-Integer.parseInt(lastrecord[j]));
                }
                matchT = Math.abs(red3k-Integer.parseInt(lastrecord[j]));
                matchV = matchV<=matchT?matchV:matchT;
            }

            if (i==0){
                a = matchV;
            }
            if (i==1){
                b = matchV;
            }
            if (i==2){
                c = matchV;
            }
        }

        int max = a>=b?a:b;
        int skewness = max>=c?max:c;
        return skewness;

    }

    /*最小邻间距计算*/
    public int acV(String[] orired )throws Exception {

        int red1 = Integer.parseInt(orired[0]);
        int red2 = Integer.parseInt(orired[1]);
        int red3 = Integer.parseInt(orired[2]);
        int a = Math.abs(red1-red2);
        int b = Math.abs(red1-red3);
        int c = Math.abs(red2-red3);
        int min = a<=b?a:b;
        int acV = min<=c?min:c;
        return acV;

    }
    private int rcount = 0;
    private int Result = 0;

    public void pushIcrementData() throws Exception {

        Set<String> setprime = new HashSet<String>();
        setprime.add("2");
        setprime.add("3");
        setprime.add("5");
        setprime.add("7");
        setprime.add("11");
        setprime.add("13");
        setprime.add("17");
        setprime.add("19");
        setprime.add("23");
        setprime.add("29");
        setprime.add("31");

        List<SsqThred> idstring = ssqCalService.selectIdByP(1000);

        if (idstring.size() != 0) {

            int startid = idstring.get(0).getId();
            int endid = ssqCalService.selectIdByPdesc(1000);

            List<String> findexpert = ssqCalService.selectByexpertID(startid, endid);

            Loop1:
            for (int i = 0; i < findexpert.size(); i++) {
                String expertname = findexpert.get(i).toString();

                //  String expertname = "迟丽颖";
                List<SsqThred> findid = ssqCalService.selectIDbyExpPes(expertname, 1000);
                if (findid.size() == 0) {
                    continue;
                }

                int expID0 = findid.get(0).getId();

                List<SsqThred> findbyexpert = ssqCalService.selectIDbyExplimit(expID0, expertname);
                if (findbyexpert.size() == 0) {
                    List<SsqThred> findbyexpert1 = ssqCalService.selectMLbyExpPesAsc(expertname, 1000);
                    for (int j = 0; j < findbyexpert1.size(); j++) {

                        int lasthit = 0;
                        int lastscore = 0;
                        int formax = 0;
                        int oddeven = 0;
                        int smallbig = 0;
                        int primecom = 0;
                        int countodd = 0;
                        int countsmall = 0;
                        int countprime = 0;

                        int p51 = 0;
                        int p52 = 0;
                        int p53 = 0;
                        int p101 = 0;
                        int p102 = 0;
                        int p103 = 0;
                        int p201 = 0;
                        int p202 = 0;
                        int p203 = 0;
                        int p301 = 0;
                        int p302 = 0;
                        int p303 = 0;
                        int p501 = 0;
                        int p502 = 0;
                        int p503 = 0;
                        int p1001 = 0;
                        int p1002 = 0;
                        int p1003 = 0;
                        int nowhit = 0;
                        int nowscore = 0;

                        int red1 = 0;
                        int red2 = 0;
                        int red3 = 0;
                        int threesum = 0;
                        int div30 = 0;
                        int div31 = 0;
                        int div32 = 0;
                        int divergence = 0;
                        int skewness = 0;
                        int acV = 0;


                        int expID = findbyexpert1.get(0).getId();
                        //   int expID = 20554;
                        //increlinebefore是符合标准的最小当期的上一期记录，1条
                        int increlinebefore = ssqCalService.selectCountbyExplast(expID, expertname);

                        if (increlinebefore == 1) {
                            if (j == 0) {
                                int exIDj01 = findbyexpert1.get(j).getId();
                                List<SsqThred> recordbyid = ssqCalService.selectMLModelbyId(exIDj01);
                                if (recordbyid.get(0).getHitrecord() > 0) {
                                    lasthit = 1;
                                } else {
                                    lasthit = 0;
                                }
                                lastscore = recordbyid.get(0).getHitrecord();

                                nowhit = lasthit;
                                nowscore = lastscore;

                                formax = 0;

                                String[] orired = findbyexpert1.get(j).getRedthree().split(" ");
                                if (orired.length == 3) {
                                    red1 = Integer.parseInt(orired[0]);
                                    red2 = Integer.parseInt(orired[1]);
                                    red3 = Integer.parseInt(orired[2]);

                                }

                                threesum = red1 + red2 + red3;
                                divergence = divergence(orired);
                                skewness = 0;
                                acV = acV(orired);

                                if (red1 % 2 == 1) {
                                    countodd = countodd + 1;
                                }
                                if (red2 % 2 == 1) {
                                    countodd = countodd + 1;
                                }
                                if (red3 % 2 == 1) {
                                    countodd = countodd + 1;
                                }
                                if (red1 < 17) {
                                    countsmall = countsmall + 1;
                                }
                                if (red2 < 17) {
                                    countsmall = countsmall + 1;
                                }
                                if (red3 < 17) {
                                    countsmall = countsmall + 1;
                                }
                                if (setprime.contains(String.valueOf(red1))) {
                                    countprime = countprime + 1;
                                }
                                if (setprime.contains(String.valueOf(red2))) {
                                    countprime = countprime + 1;
                                }
                                if (setprime.contains(String.valueOf(red3))) {
                                    countprime = countprime + 1;
                                }

                     /*奇数偶数判断全奇0，全偶3，2奇1偶1，2偶1奇2*/
                                if (countodd == 3) {
                                    oddeven = 0;
                                }
                                if (countodd == 2) {
                                    oddeven = 1;
                                }
                                if (countodd == 1) {
                                    oddeven = 2;
                                }
                                if (countodd == 0) {
                                    oddeven = 3;
                                }

                        /*大数小数判断全小0，全大3，2大1小1，2小1奇2*/

                                if (countsmall == 3) {
                                    smallbig = 0;
                                }
                                if (countsmall == 2) {
                                    smallbig = 2;
                                }
                                if (countsmall == 1) {
                                    smallbig = 1;
                                }
                                if (countsmall == 0) {
                                    smallbig = 3;
                                }

                        /*质数合数判断全质0，全合3，2质1合1，2合1质2*/

                                if (countprime == 3) {
                                    primecom = 0;
                                }
                                if (countprime == 2) {
                                    primecom = 1;
                                }
                                if (countprime == 1) {
                                    primecom = 2;
                                }
                                if (countprime == 0) {
                                    primecom = 3;
                                }
                        /*除3余数*/

                                if (red1 % 3 == 0) {
                                    div30 = div30 + 1;
                                }
                                if (red1 % 3 == 1) {
                                    div31 = div31 + 1;
                                }
                                if (red1 % 3 == 2) {
                                    div32 = div32 + 1;
                                }

                                if (red2 % 3 == 0) {
                                    div30 = div30 + 1;
                                }
                                if (red2 % 3 == 1) {
                                    div31 = div31 + 1;
                                }
                                if (red2 % 3 == 2) {
                                    div32 = div32 + 1;
                                }

                                if (red3 % 3 == 0) {
                                    div30 = div30 + 1;
                                }
                                if (red3 % 3 == 1) {
                                    div31 = div31 + 1;
                                }
                                if (red3 % 3 == 2) {
                                    div32 = div32 + 1;
                                }
                                Result = ssqCalService.updateSSQMLModelbyId(lasthit, lastscore, formax, oddeven, smallbig, primecom, p51, p52, p53, p101, p102, p103, p201, p202
                                        , p203, p301, p302, p303, p501, p502, p503, p1001, p1002, p1003, nowhit, nowscore, threesum, div30, div31, div32,divergence,skewness,acV, exIDj01);
                                rcount = rcount + Result;
                                System.out.println("出现1条新数据,登记第一条,id为" + exIDj01);

                            } else if (j == 1) {
                                int expID1 = findbyexpert1.get(j).getId();
                                int expID00 = findbyexpert1.get(j - 1).getId();
                                List<SsqThred> recordbyid0 = ssqCalService.selectMLModelbyId(expID00);
                                List<SsqThred> recordbyid1 = ssqCalService.selectMLModelbyId(expID1);
                                if (recordbyid0.get(0).getHitrecord() > 0) {
                                    lasthit = 1;
                                } else {
                                    lasthit = 0;
                                }
                                lastscore = recordbyid0.get(0).getHitrecord();

                                if (recordbyid1.get(0).getHitrecord() > 0) {
                                    nowhit = 1;
                                } else {
                                    nowhit = 0;
                                }


                                nowscore = recordbyid1.get(0).getHitrecord();

                                formax = recordbyid1.get(0).getHistorymaxerrorpd() - recordbyid1.get(0).getLastconterrorpd();

                                String[] orired = findbyexpert1.get(j).getRedthree().split(" ");
                                String[] lastRecord = recordbyid0.get(0).getRed().split(" ");
                                if (orired.length == 3) {
                                    red1 = Integer.parseInt(orired[0]);
                                    red2 = Integer.parseInt(orired[1]);
                                    red3 = Integer.parseInt(orired[2]);

                                }

                                threesum = red1 + red2 + red3;
                                divergence = divergence(orired);
                                skewness = skewness(orired,lastRecord);
                                acV = acV(orired);

                                if (red1 % 2 == 1) {
                                    countodd = countodd + 1;
                                }
                                if (red2 % 2 == 1) {
                                    countodd = countodd + 1;
                                }
                                if (red3 % 2 == 1) {
                                    countodd = countodd + 1;
                                }
                                if (red1 < 17) {
                                    countsmall = countsmall + 1;
                                }
                                if (red2 < 17) {
                                    countsmall = countsmall + 1;
                                }
                                if (red3 < 17) {
                                    countsmall = countsmall + 1;
                                }
                                if (setprime.contains(String.valueOf(red1))) {
                                    countprime = countprime + 1;
                                }
                                if (setprime.contains(String.valueOf(red2))) {
                                    countprime = countprime + 1;
                                }
                                if (setprime.contains(String.valueOf(red3))) {
                                    countprime = countprime + 1;
                                }

                     /*奇数偶数判断全奇0，全偶3，2奇1偶1，2偶1奇2*/
                                if (countodd == 3) {
                                    oddeven = 0;
                                }
                                if (countodd == 2) {
                                    oddeven = 1;
                                }
                                if (countodd == 1) {
                                    oddeven = 2;
                                }
                                if (countodd == 0) {
                                    oddeven = 3;
                                }

                        /*大数小数判断全小0，全大3，2大1小1，2小1奇2*/

                                if (countsmall == 3) {
                                    smallbig = 0;
                                }
                                if (countsmall == 2) {
                                    smallbig = 2;
                                }
                                if (countsmall == 1) {
                                    smallbig = 1;
                                }
                                if (countsmall == 0) {
                                    smallbig = 3;
                                }

                        /*质数合数判断全质0，全合3，2质1合1，2合1质2*/

                                if (countprime == 3) {
                                    primecom = 0;
                                }
                                if (countprime == 2) {
                                    primecom = 1;
                                }
                                if (countprime == 1) {
                                    primecom = 2;
                                }
                                if (countprime == 0) {
                                    primecom = 3;
                                }
                        /*除3余数*/

                                if (red1 % 3 == 0) {
                                    div30 = div30 + 1;
                                }
                                if (red1 % 3 == 1) {
                                    div31 = div31 + 1;
                                }
                                if (red1 % 3 == 2) {
                                    div32 = div32 + 1;
                                }
                                if (red2 % 3 == 0) {
                                    div30 = div30 + 1;
                                }
                                if (red2 % 3 == 1) {
                                    div31 = div31 + 1;
                                }
                                if (red2 % 3 == 2) {
                                    div32 = div32 + 1;
                                }

                                if (red3 % 3 == 0) {
                                    div30 = div30 + 1;
                                }
                                if (red3 % 3 == 1) {
                                    div31 = div31 + 1;
                                }
                                if (red3 % 3 == 2) {
                                    div32 = div32 + 1;
                                }
                                Result = ssqCalService.updateSSQMLModelbyId(lasthit, lastscore, formax, oddeven, smallbig, primecom, p51, p52, p53, p101, p102, p103, p201, p202
                                        , p203, p301, p302, p303, p501, p502, p503, p1001, p1002, p1003, nowhit, nowscore, threesum, div30, div31, div32,divergence,skewness,acV,expID1);
                                rcount = rcount + Result;

                            } else if (j > 1) {

                                int expIDN = findbyexpert1.get(j).getId();
                                //int expIDN = 22860;
                                int expIDL = findbyexpert1.get(j - 1).getId();
                                int expIDL2 = findbyexpert1.get(j - 2).getId();
                                List<SsqThred> recordbyidL = ssqCalService.selectMLModelbyId(expIDL);
                                List<SsqThred> recordbyidL2 = ssqCalService.selectMLModelbyId(expIDL2);
                                if (recordbyidL.get(0).getHitrecord() > 0) {
                                    lasthit = 1;
                                } else {
                                    lasthit = 0;
                                }
                                lastscore = recordbyidL.get(0).getHitrecord();

                                List<SsqThred> recordbyidN = ssqCalService.selectMLModelbyId(expIDN);
                                if (recordbyidN.get(0).getHitrecord() > 0) {
                                    nowhit = 1;
                                } else {
                                    nowhit = 0;
                                }
                                nowscore = recordbyidN.get(0).getHitrecord();


                                formax = recordbyidN.get(0).getHistorymaxerrorpd() - recordbyidN.get(0).getLastconterrorpd();

                                String[] orired = findbyexpert1.get(j).getRedthree().split(" ");
                                String[] lastRecord = recordbyidL.get(0).getRed().split(" ");
                                if (orired.length == 3) {
                                    red1 = Integer.parseInt(orired[0]);
                                    red2 = Integer.parseInt(orired[1]);
                                    red3 = Integer.parseInt(orired[2]);

                                }
                                threesum = red1 + red2 + red3;
                                divergence = divergence(orired);
                                skewness = skewness(orired,lastRecord);
                                acV = acV(orired);

                                if (red1 % 2 == 1) {
                                    countodd = countodd + 1;
                                }
                                if (red2 % 2 == 1) {
                                    countodd = countodd + 1;
                                }
                                if (red3 % 2 == 1) {
                                    countodd = countodd + 1;
                                }
                                if (red1 < 17) {
                                    countsmall = countsmall + 1;
                                }
                                if (red2 < 17) {
                                    countsmall = countsmall + 1;
                                }
                                if (red3 < 17) {
                                    countsmall = countsmall + 1;
                                }
                                if (setprime.contains(String.valueOf(red1))) {
                                    countprime = countprime + 1;
                                }
                                if (setprime.contains(String.valueOf(red2))) {
                                    countprime = countprime + 1;
                                }
                                if (setprime.contains(String.valueOf(red3))) {
                                    countprime = countprime + 1;
                                }

                     /*奇数偶数判断全奇0，全偶3，2奇1偶1，2偶1奇2*/
                                if (countodd == 3) {
                                    oddeven = 0;
                                }
                                if (countodd == 2) {
                                    oddeven = 1;
                                }
                                if (countodd == 1) {
                                    oddeven = 2;
                                }
                                if (countodd == 0) {
                                    oddeven = 3;
                                }

                        /*大数小数判断全小0，全大3，2大1小1，2小1奇2*/

                                if (countsmall == 3) {
                                    smallbig = 0;
                                }
                                if (countsmall == 2) {
                                    smallbig = 2;
                                }
                                if (countsmall == 1) {
                                    smallbig = 1;
                                }
                                if (countsmall == 0) {
                                    smallbig = 3;
                                }

                        /*质数合数判断全质0，全合3，2质1合1，2合1质2*/

                                if (countprime == 3) {
                                    primecom = 0;
                                }
                                if (countprime == 2) {
                                    primecom = 1;
                                }
                                if (countprime == 1) {
                                    primecom = 2;
                                }
                                if (countprime == 0) {
                                    primecom = 3;
                                }

                            /*除3余数*/

                                if (red1 % 3 == 0) {
                                    div30 = div30 + 1;
                                }
                                if (red1 % 3 == 1) {
                                    div31 = div31 + 1;
                                }
                                if (red1 % 3 == 2) {
                                    div32 = div32 + 1;
                                }
                                if (red2 % 3 == 0) {
                                    div30 = div30 + 1;
                                }
                                if (red2 % 3 == 1) {
                                    div31 = div31 + 1;
                                }
                                if (red2 % 3 == 2) {
                                    div32 = div32 + 1;
                                }

                                if (red3 % 3 == 0) {
                                    div30 = div30 + 1;
                                }
                                if (red3 % 3 == 1) {
                                    div31 = div31 + 1;
                                }
                                if (red3 % 3 == 2) {
                                    div32 = div32 + 1;
                                }

                        /*前几期连中1中2中3概率趋势,上升或持平为1，下降为0*/

                                if (recordbyidL.get(0).getPone5() == 0.0 && recordbyidL2.get(0).getPone5() == 0.0) {
                                    p51 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPone5() >= recordbyidL2.get(0).getPone5()) {
                                        p51 = 1;
                                    } else {
                                        p51 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPtwo5() == 0.0 && recordbyidL2.get(0).getPtwo5() == 0.0) {
                                    p52 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPtwo5() >= recordbyidL2.get(0).getPtwo5()) {
                                        p52 = 1;
                                    } else {
                                        p52 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPthree5() == 0.0 && recordbyidL2.get(0).getPthree5() == 0.0) {
                                    p53 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPthree5() >= recordbyidL2.get(0).getPthree5()) {
                                        p53 = 1;
                                    } else {
                                        p53 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPone10() == 0.0 && recordbyidL2.get(0).getPone10() == 0.0) {
                                    p101 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPone10() >= recordbyidL2.get(0).getPone10()) {
                                        p101 = 1;
                                    } else {
                                        p101 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPtwo10() == 0.0 && recordbyidL2.get(0).getPtwo10() == 0.0) {
                                    p102 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPtwo10() >= recordbyidL2.get(0).getPtwo10()) {
                                        p102 = 1;
                                    } else {
                                        p102 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPthree10() == 0.0 && recordbyidL2.get(0).getPthree10() == 0.0) {
                                    p103 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPthree10() >= recordbyidL2.get(0).getPthree10()) {
                                        p103 = 1;
                                    } else {
                                        p103 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPone20() == 0.0 && recordbyidL2.get(0).getPone20() == 0.0) {
                                    p201 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPone20() >= recordbyidL2.get(0).getPone20()) {
                                        p201 = 1;
                                    } else {
                                        p201 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPtwo20() == 0.0 && recordbyidL2.get(0).getPtwo20() == 0.0) {
                                    p202 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPtwo20() >= recordbyidL2.get(0).getPtwo20()) {
                                        p202 = 1;
                                    } else {
                                        p202 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPthree20() == 0.0 && recordbyidL2.get(0).getPthree20() == 0.0) {
                                    p203 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPthree20() >= recordbyidL2.get(0).getPthree20()) {
                                        p203 = 1;
                                    } else {
                                        p203 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPone30() == 0.0 && recordbyidL2.get(0).getPone30() == 0.0) {
                                    p301 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPone30() >= recordbyidL2.get(0).getPone30()) {
                                        p301 = 1;
                                    } else {
                                        p301 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPtwo30() == 0.0 && recordbyidL2.get(0).getPtwo30() == 0.0) {
                                    p302 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPtwo30() >= recordbyidL2.get(0).getPtwo30()) {
                                        p302 = 1;
                                    } else {
                                        p302 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPthree30() == 0.0 && recordbyidL2.get(0).getPthree30() == 0.0) {
                                    p303 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPthree30() >= recordbyidL2.get(0).getPthree30()) {
                                        p303 = 1;
                                    } else {
                                        p303 = 0;
                                    }

                                }
                                if (recordbyidL.get(0).getPone50() == 0.0 && recordbyidL2.get(0).getPone50() == 0.0) {
                                    p501 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPone50() >= recordbyidL2.get(0).getPone50()) {
                                        p501 = 1;
                                    } else {
                                        p501 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPtwo50() == 0.0 && recordbyidL2.get(0).getPtwo50() == 0.0) {
                                    p502 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPtwo50() >= recordbyidL2.get(0).getPtwo50()) {
                                        p502 = 1;
                                    } else {
                                        p502 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPthree50() == 0.0 && recordbyidL2.get(0).getPthree50() == 0.0) {
                                    p503 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPthree50() >= recordbyidL2.get(0).getPthree50()) {
                                        p503 = 1;
                                    } else {
                                        p503 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPone100() == 0.0 && recordbyidL2.get(0).getPone100() == 0.0) {
                                    p1001 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPone100() >= recordbyidL2.get(0).getPone100()) {
                                        p1001 = 1;
                                    } else {
                                        p1001 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPtwo100() == 0.0 && recordbyidL2.get(0).getPtwo100() == 0.0) {
                                    p1002 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPtwo100() >= recordbyidL2.get(0).getPtwo100()) {
                                        p1002 = 1;
                                    } else {
                                        p1002 = 0;
                                    }

                                }

                                if (recordbyidL.get(0).getPthree100() == 0.0 && recordbyidL2.get(0).getPthree100() == 0.0) {
                                    p1003 = 0;
                                } else {
                                    if (recordbyidL.get(0).getPthree100() >= recordbyidL2.get(0).getPthree100()) {
                                        p1003 = 1;
                                    } else {
                                        p1003 = 0;
                                    }

                                }

                                Result = ssqCalService.updateSSQMLModelbyId(lasthit, lastscore, formax, oddeven, smallbig, primecom, p51, p52, p53, p101, p102, p103, p201, p202
                                        , p203, p301, p302, p303, p501, p502, p503, p1001, p1002, p1003, nowhit, nowscore, threesum, div30, div31, div32, divergence,skewness,acV,expIDN);
                                rcount = rcount + Result;


                            }


                        }
                    }
                }else  {

                            List<SsqThred> findbyexpert1 = ssqCalService.selectMLbyExpPesAsc(expertname, 1000);
                            for (int j = 0; j < findbyexpert1.size(); j++) {
                                int lasthit = 0;
                                int lastscore = 0;
                                int formax = 0;
                                int oddeven = 0;
                                int smallbig = 0;
                                int primecom = 0;
                                int countodd = 0;
                                int countsmall = 0;
                                int countprime = 0;

                                int p51 = 0;
                                int p52 = 0;
                                int p53 = 0;
                                int p101 = 0;
                                int p102 = 0;
                                int p103 = 0;
                                int p201 = 0;
                                int p202 = 0;
                                int p203 = 0;
                                int p301 = 0;
                                int p302 = 0;
                                int p303 = 0;
                                int p501 = 0;
                                int p502 = 0;
                                int p503 = 0;
                                int p1001 = 0;
                                int p1002 = 0;
                                int p1003 = 0;
                                int nowhit = 0;
                                int nowscore = 0;

                                int red1 = 0;
                                int red2 = 0;
                                int red3 = 0;
                                int threesum = 0;
                                int div30 = 0;
                                int div31 = 0;
                                int div32 = 0;
                                int divergence = 0;
                                int skewness = 0;
                                int acV = 0;

                                if (j == 0) {
                                    int expIDj0 = findbyexpert1.get(j).getId();

                                    if(findbyexpert.size()==2){
                                        int expIDLT0 = findbyexpert.get(findbyexpert.size() - 1).getId();
                                        int expIDLT1 = findbyexpert.get(findbyexpert.size() - 2).getId();
                                        List<SsqThred> recordbyid = ssqCalService.selectMLModelbyId(expIDj0);
                                        List<SsqThred> recordbyidL0 = ssqCalService.selectMLModelbyId(expIDLT0);
                                        List<SsqThred> recordbyidL1 = ssqCalService.selectMLModelbyId(expIDLT1);
                                        if (recordbyidL0.get(0).getHitrecord() > 0) {
                                            lasthit = 1;
                                        } else {
                                            lasthit = 0;
                                        }
                                        lastscore = recordbyidL0.get(0).getHitrecord();

                                        if (recordbyid.get(0).getHitrecord() > 0) {
                                            nowhit = 1;
                                        } else {
                                            nowhit = 0;
                                        }

                                        nowscore = recordbyid.get(0).getHitrecord();

                                        formax = recordbyid.get(0).getHistorymaxerrorpd() - recordbyid.get(0).getLastconterrorpd();

                                        String[] orired = findbyexpert1.get(j).getRedthree().split(" ");
                                        String[] lastRecord = recordbyidL0.get(0).getRed().split(" ");

                                        if (orired.length == 3) {
                                            red1 = Integer.parseInt(orired[0]);
                                            red2 = Integer.parseInt(orired[1]);
                                            red3 = Integer.parseInt(orired[2]);

                                        }
                                        threesum = red1 + red2 +red3;
                                        divergence = divergence(orired);
                                        skewness = skewness(orired,lastRecord);
                                        acV = acV(orired);

                                        if (red1 % 2 == 1) {
                                            countodd = countodd + 1;
                                        }
                                        if (red2 % 2 == 1) {
                                            countodd = countodd + 1;
                                        }
                                        if (red3 % 2 == 1) {
                                            countodd= countodd + 1;
                                        }
                                        if (red1 < 17) {
                                            countsmall = countsmall + 1;
                                        }
                                        if (red2 < 17) {
                                            countsmall = countsmall + 1;
                                        }
                                        if (red3 < 17) {
                                            countsmall = countsmall + 1;
                                        }
                                        if (setprime.contains(String.valueOf(red1))) {
                                            countprime = countprime + 1;
                                        }
                                        if (setprime.contains(String.valueOf(red2))) {
                                            countprime = countprime + 1;
                                        }
                                        if (setprime.contains(String.valueOf(red3))) {
                                            countprime = countprime + 1;
                                        }

                     /*奇数偶数判断全奇0，全偶3，2奇1偶1，2偶1奇2*/
                                        if (countodd == 3) {
                                            oddeven = 0;
                                        }
                                        if (countodd == 2) {
                                            oddeven = 1;
                                        }
                                        if (countodd == 1) {
                                            oddeven = 2;
                                        }
                                        if (countodd == 0) {
                                            oddeven = 3;
                                        }

                        /*大数小数判断全小0，全大3，2大1小1，2小1奇2*/

                                        if (countsmall == 3) {
                                            smallbig = 0;
                                        }
                                        if (countsmall == 2) {
                                            smallbig = 2;
                                        }
                                        if (countsmall == 1) {
                                            smallbig = 1;
                                        }
                                        if (countsmall == 0) {
                                            smallbig = 3;
                                        }

                        /*质数合数判断全质0，全合3，2质1合1，2合1质2*/

                                        if (countprime == 3) {
                                            primecom = 0;
                                        }
                                        if (countprime == 2) {
                                            primecom = 1;
                                        }
                                        if (countprime == 1) {
                                            primecom = 2;
                                        }
                                        if (countprime == 0) {
                                            primecom = 3;
                                        }

                                        /*除3余数*/

                                        if (red1 % 3 == 0) {
                                            div30 = div30 + 1;
                                        }
                                        if (red1 % 3 == 1) {
                                            div31 = div31 + 1;
                                        }
                                        if (red1 % 3 == 2) {
                                            div32 = div32 + 1;
                                        }

                                        if (red2 % 3 == 0) {
                                            div30 = div30 + 1;
                                        }
                                        if (red2 % 3 == 1) {
                                            div31 = div31 + 1;
                                        }
                                        if (red2 % 3 == 2) {
                                            div32 = div32 + 1;
                                        }

                                        if (red3 % 3 == 0) {
                                            div30 = div30 + 1;
                                        }
                                        if (red3 % 3 == 1) {
                                            div31 = div31 + 1;
                                        }
                                        if (red3 % 3 == 2) {
                                            div32 = div32 + 1;
                                        }

                            /*前几期连中1中2中3概率趋势,上升或持平为1，下降为0*/

                                        if (recordbyidL0.get(0).getPone5() == 0.0 && recordbyidL1.get(0).getPone5() == 0.0) {
                                            p51 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPone5() >= recordbyidL1.get(0).getPone5()) {
                                                p51 = 1;
                                            } else {
                                                p51 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPtwo5() == 0.0 && recordbyidL1.get(0).getPtwo5() == 0.0) {
                                            p52 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPtwo5() >= recordbyidL1.get(0).getPtwo5()) {
                                                p52 = 1;
                                            } else {
                                                p52 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPthree5() == 0.0 && recordbyidL1.get(0).getPthree5() == 0.0) {
                                            p53 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPthree5() >= recordbyidL1.get(0).getPthree5()) {
                                                p53 = 1;
                                            } else {
                                                p53 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPone10() == 0.0 && recordbyidL1.get(0).getPone10() == 0.0) {
                                            p101 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPone10() >= recordbyidL1.get(0).getPone10()) {
                                                p101 = 1;
                                            } else {
                                                p101 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPtwo10() == 0.0 && recordbyidL1.get(0).getPtwo10() == 0.0) {
                                            p102 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPtwo10() >= recordbyidL1.get(0).getPtwo10()) {
                                                p102 = 1;
                                            } else {
                                                p102 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPthree10() == 0.0 && recordbyidL1.get(0).getPthree10() == 0.0) {
                                            p103 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPthree10() >= recordbyidL1.get(0).getPthree10()) {
                                                p103 = 1;
                                            } else {
                                                p103 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPone20() == 0.0 && recordbyidL1.get(0).getPone20() == 0.0) {
                                            p201 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPone20() >= recordbyidL1.get(0).getPone20()) {
                                                p201 = 1;
                                            } else {
                                                p201 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPtwo20() == 0.0 && recordbyidL1.get(0).getPtwo20() == 0.0) {
                                            p202 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPtwo20() >= recordbyidL1.get(0).getPtwo20()) {
                                                p202 = 1;
                                            } else {
                                                p202 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPthree20() == 0.0 && recordbyidL1.get(0).getPthree20() == 0.0) {
                                            p203 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPthree20() >= recordbyidL1.get(0).getPthree20()) {
                                                p203 = 1;
                                            } else {
                                                p203 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPone30() == 0.0 && recordbyidL1.get(0).getPone30() == 0.0) {
                                            p301 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPone30() >= recordbyidL1.get(0).getPone30()) {
                                                p301 = 1;
                                            } else {
                                                p301 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPtwo30() == 0.0 && recordbyidL1.get(0).getPtwo30() == 0.0) {
                                            p302 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPtwo30() >= recordbyidL1.get(0).getPtwo30()) {
                                                p302 = 1;
                                            } else {
                                                p302 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPthree30() == 0.0 && recordbyidL1.get(0).getPthree30() == 0.0) {
                                            p303 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPthree30() >= recordbyidL1.get(0).getPthree30()) {
                                                p303 = 1;
                                            } else {
                                                p303 = 0;
                                            }

                                        }
                                        if (recordbyidL0.get(0).getPone50() == 0.0 && recordbyidL1.get(0).getPone50() == 0.0) {
                                            p501 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPone50() >= recordbyidL1.get(0).getPone50()) {
                                                p501 = 1;
                                            } else {
                                                p501 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPtwo50() == 0.0 && recordbyidL1.get(0).getPtwo50() == 0.0) {
                                            p502 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPtwo50() >= recordbyidL1.get(0).getPtwo50()) {
                                                p502 = 1;
                                            } else {
                                                p502 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPthree50() == 0.0 && recordbyidL1.get(0).getPthree50() == 0.0) {
                                            p503 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPthree50() >= recordbyidL1.get(0).getPthree50()) {
                                                p503 = 1;
                                            } else {
                                                p503 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPone100() == 0.0 && recordbyidL1.get(0).getPone100() == 0.0) {
                                            p1001 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPone100() >= recordbyidL1.get(0).getPone100()) {
                                                p1001 = 1;
                                            } else {
                                                p1001 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPtwo100() == 0.0 && recordbyidL1.get(0).getPtwo100() == 0.0) {
                                            p1002 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPtwo100() >= recordbyidL1.get(0).getPtwo100()) {
                                                p1002 = 1;
                                            } else {
                                                p1002 = 0;
                                            }

                                        }

                                        if (recordbyidL0.get(0).getPthree100() == 0.0 && recordbyidL1.get(0).getPthree100() == 0.0) {
                                            p1003 = 0;
                                        } else {
                                            if (recordbyidL0.get(0).getPthree100() >= recordbyidL1.get(0).getPthree100()) {
                                                p1003 = 1;
                                            } else {
                                                p1003 = 0;
                                            }

                                        }
                                        Result = ssqCalService.updateSSQMLModelbyId(lasthit, lastscore, formax, oddeven, smallbig, primecom, p51, p52, p53, p101, p102, p103, p201, p202
                                                , p203, p301, p302, p303, p501, p502, p503, p1001, p1002, p1003, nowhit, nowscore,threesum, div30, div31, div32,divergence,skewness,acV, expIDj0);
                                        rcount = rcount + Result;
                                    }else{
                                        int expIDLT0 = findbyexpert.get(findbyexpert.size() - 1).getId();

                                        List<SsqThred> recordbyid = ssqCalService.selectMLModelbyId(expIDj0);
                                        List<SsqThred> recordbyidL0 = ssqCalService.selectMLModelbyId(expIDLT0);
                                        if (recordbyidL0.get(0).getHitrecord() > 0) {
                                            lasthit = 1;
                                        } else {
                                            lasthit = 0;
                                        }
                                        lastscore = recordbyidL0.get(0).getHitrecord();

                                        if (recordbyid.get(0).getHitrecord() > 0) {
                                            nowhit = 1;
                                        } else {
                                            nowhit = 0;
                                        }

                                        nowscore = recordbyid.get(0).getHitrecord();

                                        formax = recordbyid.get(0).getHistorymaxerrorpd() - recordbyid.get(0).getLastconterrorpd();

                                        String[] orired = recordbyid.get(0).getRedthree().split(" ");
                                        String[] lastRecord = recordbyidL0.get(0).getRed().split(" ");

                                        if (orired.length == 3) {
                                            red1 = Integer.parseInt(orired[0]);
                                            red2 = Integer.parseInt(orired[1]);
                                            red3 = Integer.parseInt(orired[2]);

                                        }
                                        threesum = red1 + red2 +red3;
                                        divergence = divergence(orired);
                                        skewness = skewness(orired,lastRecord);
                                        acV = acV(orired);

                                        if (red1 % 2 == 1) {
                                            countodd = countodd + 1;
                                        }
                                        if (red2 % 2 == 1) {
                                            countodd = countodd + 1;
                                        }
                                        if (red3 % 2 == 1) {
                                            countodd= countodd + 1;
                                        }
                                        if (red1 < 17) {
                                            countsmall = countsmall + 1;
                                        }
                                        if (red2 < 17) {
                                            countsmall = countsmall + 1;
                                        }
                                        if (red3 < 17) {
                                            countsmall = countsmall + 1;
                                        }
                                        if (setprime.contains(String.valueOf(red1))) {
                                            countprime = countprime + 1;
                                        }
                                        if (setprime.contains(String.valueOf(red2))) {
                                            countprime = countprime + 1;
                                        }
                                        if (setprime.contains(String.valueOf(red3))) {
                                            countprime = countprime + 1;
                                        }

                     /*奇数偶数判断全奇0，全偶3，2奇1偶1，2偶1奇2*/
                                        if (countodd == 3) {
                                            oddeven = 0;
                                        }
                                        if (countodd == 2) {
                                            oddeven = 1;
                                        }
                                        if (countodd == 1) {
                                            oddeven = 2;
                                        }
                                        if (countodd == 0) {
                                            oddeven = 3;
                                        }

                        /*大数小数判断全小0，全大3，2大1小1，2小1奇2*/

                                        if (countsmall == 3) {
                                            smallbig = 0;
                                        }
                                        if (countsmall == 2) {
                                            smallbig = 2;
                                        }
                                        if (countsmall == 1) {
                                            smallbig = 1;
                                        }
                                        if (countsmall == 0) {
                                            smallbig = 3;
                                        }

                        /*质数合数判断全质0，全合3，2质1合1，2合1质2*/

                                        if (countprime == 3) {
                                            primecom = 0;
                                        }
                                        if (countprime == 2) {
                                            primecom = 1;
                                        }
                                        if (countprime == 1) {
                                            primecom = 2;
                                        }
                                        if (countprime == 0) {
                                            primecom = 3;
                                        }

                                        /*除3余数*/

                                        if (red1 % 3 == 0) {
                                            div30 = div30 + 1;
                                        }
                                        if (red1 % 3 == 1) {
                                            div31 = div31 + 1;
                                        }
                                        if (red1 % 3 == 2) {
                                            div32 = div32 + 1;
                                        }

                                        if (red2 % 3 == 0) {
                                            div30 = div30 + 1;
                                        }
                                        if (red2 % 3 == 1) {
                                            div31 = div31 + 1;
                                        }
                                        if (red2 % 3 == 2) {
                                            div32 = div32 + 1;
                                        }

                                        if (red3 % 3 == 0) {
                                            div30 = div30 + 1;
                                        }
                                        if (red3 % 3 == 1) {
                                            div31 = div31 + 1;
                                        }
                                        if (red3 % 3 == 2) {
                                            div32 = div32 + 1;
                                        }

                                        p51 = 0;
                                        p52 = 0;
                                        p53 = 0;
                                        p101 = 0;
                                        p102 = 0;
                                        p103 = 0;
                                        p201 = 0;
                                        p202 = 0;
                                        p203 = 0;
                                        p301 = 0;
                                        p302 = 0;
                                        p303 = 0;
                                        p501 = 0;
                                        p502 = 0;
                                        p503 = 0;
                                        p1001 = 0;
                                        p1002 = 0;
                                        p1003 = 0;
                                        Result = ssqCalService.updateSSQMLModelbyId(lasthit, lastscore, formax, oddeven, smallbig, primecom, p51, p52, p53, p101, p102, p103, p201, p202
                                                , p203, p301, p302, p303, p501, p502, p503, p1001, p1002, p1003, nowhit, nowscore,threesum, div30, div31, div32,divergence,skewness,acV, expIDj0);
                                        rcount = rcount + Result;

                                    }



                                } else {
                                    int expIDN = findbyexpert1.get(j).getId();
                                    //int expIDN = 22860;
                                    int expIDL = findbyexpert1.get(j - 1).getId();
                                    int expIDL2 = findbyexpert.get(findbyexpert.size() - 1).getId();
                                    List<SsqThred> recordbyidL = ssqCalService.selectMLModelbyId(expIDL);
                                    List<SsqThred> recordbyidL2 = ssqCalService.selectMLModelbyId(expIDL2);
                                    if (recordbyidL.get(0).getHitrecord() > 0) {
                                        lasthit = 1;
                                    } else {
                                        lasthit = 0;
                                    }
                                    lastscore = recordbyidL.get(0).getHitrecord();

                                    List<SsqThred> recordbyidN = ssqCalService.selectMLModelbyId(expIDN);
                                    if (recordbyidN.get(0).getHitrecord() > 0) {
                                        nowhit = 1;
                                    } else {
                                        nowhit = 0;
                                    }
                                    nowscore = recordbyidN.get(0).getHitrecord();


                                    formax = recordbyidN.get(0).getHistorymaxerrorpd() - recordbyidN.get(0).getLastconterrorpd();

                                    String[] orired = findbyexpert1.get(j).getRedthree().split(" ");
                                    String[] lastRecord = recordbyidL.get(0).getRed().split(" ");
                                    if (orired.length == 3) {
                                        red1 = Integer.parseInt(orired[0]);
                                        red2 = Integer.parseInt(orired[1]);
                                        red3 = Integer.parseInt(orired[2]);

                                    }
                                    threesum = red1 + red2 +red3;

                                    divergence = divergence(orired);
                                    skewness = skewness(orired,lastRecord);
                                    acV = acV(orired);

                                    if (red1 % 2 == 1) {
                                        countodd = countodd + 1;
                                    }
                                    if (red2 % 2 == 1) {
                                        countodd = countodd + 1;
                                    }
                                    if (red3 % 2 == 1) {
                                        countodd = countodd + 1;
                                    }
                                    if (red1 < 17) {
                                        countsmall = countsmall + 1;
                                    }
                                    if (red2 < 17) {
                                        countsmall = countsmall + 1;
                                    }
                                    if (red3 < 17) {
                                        countsmall = countsmall + 1;
                                    }
                                    if (setprime.contains(String.valueOf(red1))) {
                                        countprime = countprime + 1;
                                    }
                                    if (setprime.contains(String.valueOf(red2))) {
                                        countprime = countprime + 1;
                                    }
                                    if (setprime.contains(String.valueOf(red3))) {
                                        countprime = countprime + 1;
                                    }

                     /*奇数偶数判断全奇0，全偶3，2奇1偶1，2偶1奇2*/
                                    if (countodd == 3) {
                                        oddeven = 0;
                                    }
                                    if (countodd == 2) {
                                        oddeven = 1;
                                    }
                                    if (countodd == 1) {
                                        oddeven = 2;
                                    }
                                    if (countodd == 0) {
                                        oddeven = 3;
                                    }

                        /*大数小数判断全小0，全大3，2大1小1，2小1奇2*/

                                    if (countsmall == 3) {
                                        smallbig = 0;
                                    }
                                    if (countsmall == 2) {
                                        smallbig = 2;
                                    }
                                    if (countsmall == 1) {
                                        smallbig = 1;
                                    }
                                    if (countsmall == 0) {
                                        smallbig = 3;
                                    }

                        /*质数合数判断全质0，全合3，2质1合1，2合1质2*/

                                    if (countprime == 3) {
                                        primecom = 0;
                                    }
                                    if (countprime == 2) {
                                        primecom = 1;
                                    }
                                    if (countprime == 1) {
                                        primecom = 2;
                                    }
                                    if (countprime == 0) {
                                        primecom = 3;
                                    }

                            /*除3余数*/

                                    if (red1 % 3 == 0) {
                                        div30 = div30 + 1;
                                    }
                                    if (red1 % 3 == 1) {
                                        div31 = div31 + 1;
                                    }
                                    if (red1 % 3 == 2) {
                                        div32 = div32 + 1;
                                    }
                                    if (red2 % 3 == 0) {
                                        div30 = div30 + 1;
                                    }
                                    if (red2 % 3 == 1) {
                                        div31 = div31 + 1;
                                    }
                                    if (red2 % 3 == 2) {
                                        div32 = div32 + 1;
                                    }

                                    if (red3 % 3 == 0) {
                                        div30 = div30 + 1;
                                    }
                                    if (red3 % 3 == 1) {
                                        div31 = div31 + 1;
                                    }
                                    if (red3 % 3 == 2) {
                                        div32 = div32 + 1;
                                    }

                        /*前几期连中1中2中3概率趋势,上升或持平为1，下降为0*/

                                    if (recordbyidL.get(0).getPone5() == 0.0 && recordbyidL2.get(0).getPone5() == 0.0) {
                                        p51 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPone5() >= recordbyidL2.get(0).getPone5()) {
                                            p51 = 1;
                                        } else {
                                            p51 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPtwo5() == 0.0 && recordbyidL2.get(0).getPtwo5() == 0.0) {
                                        p52 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPtwo5() >= recordbyidL2.get(0).getPtwo5()) {
                                            p52 = 1;
                                        } else {
                                            p52 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPthree5() == 0.0 && recordbyidL2.get(0).getPthree5() == 0.0) {
                                        p53 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPthree5() >= recordbyidL2.get(0).getPthree5()) {
                                            p53 = 1;
                                        } else {
                                            p53 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPone10() == 0.0 && recordbyidL2.get(0).getPone10() == 0.0) {
                                        p101 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPone10() >= recordbyidL2.get(0).getPone10()) {
                                            p101 = 1;
                                        } else {
                                            p101 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPtwo10() == 0.0 && recordbyidL2.get(0).getPtwo10() == 0.0) {
                                        p102 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPtwo10() >= recordbyidL2.get(0).getPtwo10()) {
                                            p102 = 1;
                                        } else {
                                            p102 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPthree10() == 0.0 && recordbyidL2.get(0).getPthree10() == 0.0) {
                                        p103 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPthree10() >= recordbyidL2.get(0).getPthree10()) {
                                            p103 = 1;
                                        } else {
                                            p103 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPone20() == 0.0 && recordbyidL2.get(0).getPone20() == 0.0) {
                                        p201 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPone20() >= recordbyidL2.get(0).getPone20()) {
                                            p201 = 1;
                                        } else {
                                            p201 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPtwo20() == 0.0 && recordbyidL2.get(0).getPtwo20() == 0.0) {
                                        p202 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPtwo20() >= recordbyidL2.get(0).getPtwo20()) {
                                            p202 = 1;
                                        } else {
                                            p202 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPthree20() == 0.0 && recordbyidL2.get(0).getPthree20() == 0.0) {
                                        p203 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPthree20() >= recordbyidL2.get(0).getPthree20()) {
                                            p203 = 1;
                                        } else {
                                            p203 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPone30() == 0.0 && recordbyidL2.get(0).getPone30() == 0.0) {
                                        p301 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPone30() >= recordbyidL2.get(0).getPone30()) {
                                            p301 = 1;
                                        } else {
                                            p301 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPtwo30() == 0.0 && recordbyidL2.get(0).getPtwo30() == 0.0) {
                                        p302 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPtwo30() >= recordbyidL2.get(0).getPtwo30()) {
                                            p302 = 1;
                                        } else {
                                            p302 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPthree30() == 0.0 && recordbyidL2.get(0).getPthree30() == 0.0) {
                                        p303 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPthree30() >= recordbyidL2.get(0).getPthree30()) {
                                            p303 = 1;
                                        } else {
                                            p303 = 0;
                                        }

                                    }
                                    if (recordbyidL.get(0).getPone50() == 0.0 && recordbyidL2.get(0).getPone50() == 0.0) {
                                        p501 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPone50() >= recordbyidL2.get(0).getPone50()) {
                                            p501 = 1;
                                        } else {
                                            p501 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPtwo50() == 0.0 && recordbyidL2.get(0).getPtwo50() == 0.0) {
                                        p502 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPtwo50() >= recordbyidL2.get(0).getPtwo50()) {
                                            p502 = 1;
                                        } else {
                                            p502 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPthree50() == 0.0 && recordbyidL2.get(0).getPthree50() == 0.0) {
                                        p503 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPthree50() >= recordbyidL2.get(0).getPthree50()) {
                                            p503 = 1;
                                        } else {
                                            p503 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPone100() == 0.0 && recordbyidL2.get(0).getPone100() == 0.0) {
                                        p1001 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPone100() >= recordbyidL2.get(0).getPone100()) {
                                            p1001 = 1;
                                        } else {
                                            p1001 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPtwo100() == 0.0 && recordbyidL2.get(0).getPtwo100() == 0.0) {
                                        p1002 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPtwo100() >= recordbyidL2.get(0).getPtwo100()) {
                                            p1002 = 1;
                                        } else {
                                            p1002 = 0;
                                        }

                                    }

                                    if (recordbyidL.get(0).getPthree100() == 0.0 && recordbyidL2.get(0).getPthree100() == 0.0) {
                                        p1003 = 0;
                                    } else {
                                        if (recordbyidL.get(0).getPthree100() >= recordbyidL2.get(0).getPthree100()) {
                                            p1003 = 1;
                                        } else {
                                            p1003 = 0;
                                        }

                                    }

                                    Result = ssqCalService.updateSSQMLModelbyId(lasthit, lastscore, formax, oddeven, smallbig, primecom, p51, p52, p53, p101, p102, p103, p201, p202
                                            , p203, p301, p302, p303, p501, p502, p503, p1001, p1002, p1003, nowhit, nowscore, threesum, div30, div31, div32, divergence,skewness,acV,
                                            expIDN);
                                    rcount = rcount + Result;

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
        SsqCalService ssqCalService = (SsqCalService) context.getBean("SsqCalService");

        CalMLTask calmltask = new CalMLTask();
        int rcount = 0;
        int Result = 0;


        List<String> findexpert = ssqCalService.selectByexpert();
        //List<String> findexpert= Arrays.asList();

        Set<String> setprime = new HashSet<String>();
        setprime.add("2");
        setprime.add("3");
        setprime.add("5");
        setprime.add("7");
        setprime.add("11");
        setprime.add("13");
        setprime.add("17");
        setprime.add("19");
        setprime.add("23");
        setprime.add("29");
        setprime.add("31");

        Loop1:
        for (int i = 0; i < findexpert.size(); i++) {
            String expertname = findexpert.get(i).toString();
          //  String expertname = "一码当先";
            List<SsqThred> findbyexpert = ssqCalService.selectGroupbyExpDesc(expertname);
            try {


                for (int j = 0; j < findbyexpert.size(); j++) {
                    int lasthit = 0;
                    int lastscore = 0;
                    int formax = 0;
                    int oddeven = 0;
                    int smallbig = 0;
                    int primecom = 0;
                    int countodd = 0;
                    int countsmall = 0;
                    int countprime = 0;

                    int p51 = 0;
                    int p52 = 0;
                    int p53 = 0;
                    int p101 = 0;
                    int p102 = 0;
                    int p103 = 0;
                    int p201 = 0;
                    int p202 = 0;
                    int p203 = 0;
                    int p301 = 0;
                    int p302 = 0;
                    int p303 = 0;
                    int p501 = 0;
                    int p502 = 0;
                    int p503 = 0;
                    int p1001 = 0;
                    int p1002 = 0;
                    int p1003 = 0;
                    int nowhit = 0;
                    int nowscore = 0;

                    int red1 = 0;
                    int red2 = 0;
                    int red3 = 0;
                    int threesum = 0;
                    int div30 = 0;
                    int div31 = 0;
                    int div32 = 0;
                    int divergence = 0;
                    int skewness = 0;
                    int acV = 0;


                    int expID = findbyexpert.get(0).getId();
                    //   int expID = 20554;
                    //increlinebefore是符合标准的最小当期的上一期记录，1条
                    int increlinebefore = ssqCalService.selectCountbyExplast(expID, expertname);

                    if (increlinebefore == 1) {
                        if (j == 0) {
                            int expID0 = findbyexpert.get(j).getId();
                            List<SsqThred> recordbyid = ssqCalService.selectMLModelbyId(expID0);
                            if (recordbyid.get(0).getHitrecord() > 0) {
                                lasthit = 1;
                            } else {
                                lasthit = 0;
                            }
                            lastscore = recordbyid.get(0).getHitrecord();

                            nowhit = lasthit;
                            nowscore = lastscore;

                            formax = 0;

                            String[] orired = findbyexpert.get(j).getRedthree().split(" ");
                            if (orired.length == 3) {
                                red1 = Integer.parseInt(orired[0]);
                                red2 = Integer.parseInt(orired[1]);
                                red3 = Integer.parseInt(orired[2]);

                            }

                            threesum = red1 + red2 +red3;
                            divergence = calmltask.divergence(orired);
                            skewness = 0;
                            acV = calmltask.acV(orired);

                            if (red1 % 2 == 1) {
                                countodd = countodd + 1;
                            }
                            if (red2 % 2 == 1) {
                                countodd = countodd + 1;
                            }
                            if (red3 % 2 == 1) {
                                countodd = countodd + 1;
                            }
                            if (red1 < 17) {
                                countsmall = countsmall + 1;
                            }
                            if (red2 < 17) {
                                countsmall = countsmall + 1;
                            }
                            if (red3 < 17) {
                                countsmall = countsmall + 1;
                            }
                            if (setprime.contains(String.valueOf(red1))) {
                                countprime = countprime + 1;
                            }
                            if (setprime.contains(String.valueOf(red2))) {
                                countprime = countprime + 1;
                            }
                            if (setprime.contains(String.valueOf(red3))) {
                                countprime = countprime + 1;
                            }

                     /*奇数偶数判断全奇0，全偶3，2奇1偶1，2偶1奇2*/
                            if (countodd == 3) {
                                oddeven = 0;
                            }
                            if (countodd == 2) {
                                oddeven = 1;
                            }
                            if (countodd == 1) {
                                oddeven = 2;
                            }
                            if (countodd == 0) {
                                oddeven = 3;
                            }

                        /*大数小数判断全小0，全大3，2大1小1，2小1奇2*/

                            if (countsmall == 3) {
                                smallbig = 0;
                            }
                            if (countsmall == 2) {
                                smallbig = 2;
                            }
                            if (countsmall == 1) {
                                smallbig = 1;
                            }
                            if (countsmall == 0) {
                                smallbig = 3;
                            }

                        /*质数合数判断全质0，全合3，2质1合1，2合1质2*/

                            if (countprime == 3) {
                                primecom = 0;
                            }
                            if (countprime == 2) {
                                primecom = 1;
                            }
                            if (countprime == 1) {
                                primecom = 2;
                            }
                            if (countprime == 0) {
                                primecom = 3;
                            }
                        /*除3余数*/

                            if (red1 % 3 == 0) {
                                div30 = div30 + 1;
                            }
                            if (red1 % 3 == 1) {
                                div31 = div31 + 1;
                            }
                            if (red1 % 3 == 2) {
                                div32 = div32 + 1;
                            }

                            if (red2 % 3 == 0) {
                                div30 = div30 + 1;
                            }
                            if (red2 % 3 == 1) {
                                div31 = div31 + 1;
                            }
                            if (red2 % 3 == 2) {
                                div32 = div32 + 1;
                            }

                            if (red3 % 3 == 0) {
                                div30 = div30 + 1;
                            }
                            if (red3 % 3 == 1) {
                                div31 = div31 + 1;
                            }
                            if (red3 % 3 == 2) {
                                div32 = div32 + 1;
                            }
                            Result = ssqCalService.updateSSQMLModelbyId(lasthit, lastscore, formax, oddeven, smallbig, primecom, p51, p52, p53, p101, p102, p103, p201, p202
                                    , p203, p301, p302, p303, p501, p502, p503, p1001, p1002, p1003, nowhit, nowscore,threesum, div30, div31, div32,divergence,skewness,acV,expID0);
                            rcount = rcount + Result;

                        }else if(j == 1){
                            int expID1 = findbyexpert.get(j).getId();
                            int expID0 = findbyexpert.get(j-1).getId();
                            List<SsqThred> recordbyid0 = ssqCalService.selectMLModelbyId(expID0);
                            List<SsqThred> recordbyid1 = ssqCalService.selectMLModelbyId(expID1);
                            if (recordbyid0.get(0).getHitrecord() > 0) {
                                lasthit = 1;
                            } else {
                                lasthit = 0;
                            }
                            lastscore = recordbyid0.get(0).getHitrecord();

                            if (recordbyid1.get(0).getHitrecord() > 0) {
                                nowhit = 1;
                            } else {
                                nowhit = 0;
                            }


                            nowscore = recordbyid1.get(0).getHitrecord();

                            formax = recordbyid1.get(0).getHistorymaxerrorpd() - recordbyid1.get(0).getLastconterrorpd();

                            String[] orired = findbyexpert.get(j).getRedthree().split(" ");
                            String[] lastRecord = recordbyid0.get(0).getRed().split(" ");
                            if (orired.length == 3) {
                                red1 = Integer.parseInt(orired[0]);
                                red2 = Integer.parseInt(orired[1]);
                                red3 = Integer.parseInt(orired[2]);

                            }

                            threesum = red1 + red2 +red3;
                            divergence = calmltask.divergence(orired);
                            skewness = calmltask.skewness(orired,lastRecord);
                            acV = calmltask.acV(orired);

                            if (red1 % 2 == 1) {
                                countodd = countodd + 1;
                            }
                            if (red2 % 2 == 1) {
                                countodd = countodd + 1;
                            }
                            if (red3 % 2 == 1) {
                                countodd = countodd + 1;
                            }
                            if (red1 < 17) {
                                countsmall = countsmall + 1;
                            }
                            if (red2 < 17) {
                                countsmall = countsmall + 1;
                            }
                            if (red3 < 17) {
                                countsmall = countsmall + 1;
                            }
                            if (setprime.contains(String.valueOf(red1))) {
                                countprime = countprime + 1;
                            }
                            if (setprime.contains(String.valueOf(red2))) {
                                countprime = countprime + 1;
                            }
                            if (setprime.contains(String.valueOf(red3))) {
                                countprime = countprime + 1;
                            }

                     /*奇数偶数判断全奇0，全偶3，2奇1偶1，2偶1奇2*/
                            if (countodd == 3) {
                                oddeven = 0;
                            }
                            if (countodd == 2) {
                                oddeven = 1;
                            }
                            if (countodd == 1) {
                                oddeven = 2;
                            }
                            if (countodd == 0) {
                                oddeven = 3;
                            }

                        /*大数小数判断全小0，全大3，2大1小1，2小1奇2*/

                            if (countsmall == 3) {
                                smallbig = 0;
                            }
                            if (countsmall == 2) {
                                smallbig = 2;
                            }
                            if (countsmall == 1) {
                                smallbig = 1;
                            }
                            if (countsmall == 0) {
                                smallbig = 3;
                            }

                        /*质数合数判断全质0，全合3，2质1合1，2合1质2*/

                            if (countprime == 3) {
                                primecom = 0;
                            }
                            if (countprime == 2) {
                                primecom = 1;
                            }
                            if (countprime == 1) {
                                primecom = 2;
                            }
                            if (countprime == 0) {
                                primecom = 3;
                            }
                        /*除3余数*/

                            if (red1 % 3 == 0) {
                                div30 = div30 + 1;
                            }
                            if (red1 % 3 == 1) {
                                div31 = div31 + 1;
                            }
                            if (red1 % 3 == 2) {
                                div32 = div32 + 1;
                            }
                            if (red2 % 3 == 0) {
                                div30 = div30 + 1;
                            }
                            if (red2 % 3 == 1) {
                                div31 = div31 + 1;
                            }
                            if (red2 % 3 == 2) {
                                div32 = div32 + 1;
                            }

                            if (red3 % 3 == 0) {
                                div30 = div30 + 1;
                            }
                            if (red3 % 3 == 1) {
                                div31 = div31 + 1;
                            }
                            if (red3 % 3 == 2) {
                                div32 = div32 + 1;
                            }
                            Result = ssqCalService.updateSSQMLModelbyId(lasthit, lastscore, formax, oddeven, smallbig, primecom, p51, p52, p53, p101, p102, p103, p201, p202
                                    , p203, p301, p302, p303, p501, p502, p503, p1001, p1002, p1003, nowhit, nowscore,threesum, div30, div31, div32,divergence,skewness,acV,expID1);
                            rcount = rcount + Result;

                        }

                        else if(j > 1) {

                            int expIDN = findbyexpert.get(j).getId();
                            //int expIDN = 22860;
                            int expIDL = findbyexpert.get(j - 1).getId();
                            int expIDL2 = findbyexpert.get(j - 2).getId();
                            List<SsqThred> recordbyidL = ssqCalService.selectMLModelbyId(expIDL);
                            List<SsqThred> recordbyidL2 = ssqCalService.selectMLModelbyId(expIDL2);
                            if (recordbyidL.get(0).getHitrecord() > 0) {
                                lasthit = 1;
                            } else {
                                lasthit = 0;
                            }
                            lastscore = recordbyidL.get(0).getHitrecord();

                            List<SsqThred> recordbyidN = ssqCalService.selectMLModelbyId(expIDN);
                            if (recordbyidN.get(0).getHitrecord() > 0) {
                                nowhit = 1;
                            } else {
                                nowhit = 0;
                            }
                            nowscore = recordbyidN.get(0).getHitrecord();


                            formax = recordbyidN.get(0).getHistorymaxerrorpd() - recordbyidN.get(0).getLastconterrorpd();

                            String[] orired = findbyexpert.get(j).getRedthree().split(" ");
                            String[] lastRecord = recordbyidL.get(0).getRed().split(" ");
                            if (orired.length == 3) {
                                red1 = Integer.parseInt(orired[0]);
                                red2 = Integer.parseInt(orired[1]);
                                red3 = Integer.parseInt(orired[2]);

                            }
                            threesum = red1 + red2 +red3;
                            divergence = calmltask.divergence(orired);
                            skewness = calmltask.skewness(orired,lastRecord);
                            acV = calmltask.acV(orired);

                            if (red1 % 2 == 1) {
                                countodd = countodd + 1;
                            }
                            if (red2 % 2 == 1) {
                                countodd = countodd + 1;
                            }
                            if (red3 % 2 == 1) {
                                countodd = countodd + 1;
                            }
                            if (red1 < 17) {
                                countsmall = countsmall + 1;
                            }
                            if (red2 < 17) {
                                countsmall = countsmall + 1;
                            }
                            if (red3 < 17) {
                                countsmall = countsmall + 1;
                            }
                            if (setprime.contains(String.valueOf(red1))) {
                                countprime = countprime + 1;
                            }
                            if (setprime.contains(String.valueOf(red2))) {
                                countprime = countprime + 1;
                            }
                            if (setprime.contains(String.valueOf(red3))) {
                                countprime = countprime + 1;
                            }

                     /*奇数偶数判断全奇0，全偶3，2奇1偶1，2偶1奇2*/
                            if (countodd == 3) {
                                oddeven = 0;
                            }
                            if (countodd == 2) {
                                oddeven = 1;
                            }
                            if (countodd == 1) {
                                oddeven = 2;
                            }
                            if (countodd == 0) {
                                oddeven = 3;
                            }

                        /*大数小数判断全小0，全大3，2大1小1，2小1奇2*/

                            if (countsmall == 3) {
                                smallbig = 0;
                            }
                            if (countsmall == 2) {
                                smallbig = 2;
                            }
                            if (countsmall == 1) {
                                smallbig = 1;
                            }
                            if (countsmall == 0) {
                                smallbig = 3;
                            }

                        /*质数合数判断全质0，全合3，2质1合1，2合1质2*/

                            if (countprime == 3) {
                                primecom = 0;
                            }
                            if (countprime == 2) {
                                primecom = 1;
                            }
                            if (countprime == 1) {
                                primecom = 2;
                            }
                            if (countprime == 0) {
                                primecom = 3;
                            }

                            /*除3余数*/

                            if (red1 % 3 == 0) {
                                div30 = div30 + 1;
                            }
                            if (red1 % 3 == 1) {
                                div31 = div31 + 1;
                            }
                            if (red1 % 3 == 2) {
                                div32 = div32 + 1;
                            }
                            if (red2 % 3 == 0) {
                                div30 = div30 + 1;
                            }
                            if (red2 % 3 == 1) {
                                div31 = div31 + 1;
                            }
                            if (red2 % 3 == 2) {
                                div32 = div32 + 1;
                            }

                            if (red3 % 3 == 0) {
                                div30 = div30 + 1;
                            }
                            if (red3 % 3 == 1) {
                                div31 = div31 + 1;
                            }
                            if (red3 % 3 == 2) {
                                div32 = div32 + 1;
                            }

                        /*前几期连中1中2中3概率趋势,上升或持平为1，下降为0*/

                            if (recordbyidL.get(0).getPone5() == 0.0 && recordbyidL2.get(0).getPone5() == 0.0) {
                                p51 = 0;
                            } else {
                                if (recordbyidL.get(0).getPone5() >= recordbyidL2.get(0).getPone5()) {
                                    p51 = 1;
                                } else {
                                    p51 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPtwo5() == 0.0 && recordbyidL2.get(0).getPtwo5() == 0.0) {
                                p52 = 0;
                            } else {
                                if (recordbyidL.get(0).getPtwo5() >= recordbyidL2.get(0).getPtwo5()) {
                                    p52 = 1;
                                } else {
                                    p52 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPthree5() == 0.0 && recordbyidL2.get(0).getPthree5() == 0.0) {
                                p53 = 0;
                            } else {
                                if (recordbyidL.get(0).getPthree5() >= recordbyidL2.get(0).getPthree5()) {
                                    p53 = 1;
                                } else {
                                    p53 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPone10() == 0.0 && recordbyidL2.get(0).getPone10() == 0.0) {
                                p101 = 0;
                            } else {
                                if (recordbyidL.get(0).getPone10() >= recordbyidL2.get(0).getPone10()) {
                                    p101 = 1;
                                } else {
                                    p101 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPtwo10() == 0.0 && recordbyidL2.get(0).getPtwo10() == 0.0) {
                                p102 = 0;
                            } else {
                                if (recordbyidL.get(0).getPtwo10() >= recordbyidL2.get(0).getPtwo10()) {
                                    p102 = 1;
                                } else {
                                    p102 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPthree10() == 0.0 && recordbyidL2.get(0).getPthree10() == 0.0) {
                                p103 = 0;
                            } else {
                                if (recordbyidL.get(0).getPthree10() >= recordbyidL2.get(0).getPthree10()) {
                                    p103 = 1;
                                } else {
                                    p103 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPone20() == 0.0 && recordbyidL2.get(0).getPone20() == 0.0) {
                                p201 = 0;
                            } else {
                                if (recordbyidL.get(0).getPone20() >= recordbyidL2.get(0).getPone20()) {
                                    p201 = 1;
                                } else {
                                    p201 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPtwo20() == 0.0 && recordbyidL2.get(0).getPtwo20() == 0.0) {
                                p202 = 0;
                            } else {
                                if (recordbyidL.get(0).getPtwo20() >= recordbyidL2.get(0).getPtwo20()) {
                                    p202 = 1;
                                } else {
                                    p202 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPthree20() == 0.0 && recordbyidL2.get(0).getPthree20() == 0.0) {
                                p203 = 0;
                            } else {
                                if (recordbyidL.get(0).getPthree20() >= recordbyidL2.get(0).getPthree20()) {
                                    p203 = 1;
                                } else {
                                    p203 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPone30() == 0.0 && recordbyidL2.get(0).getPone30() == 0.0) {
                                p301 = 0;
                            } else {
                                if (recordbyidL.get(0).getPone30() >= recordbyidL2.get(0).getPone30()) {
                                    p301 = 1;
                                } else {
                                    p301 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPtwo30() == 0.0 && recordbyidL2.get(0).getPtwo30() == 0.0) {
                                p302 = 0;
                            } else {
                                if (recordbyidL.get(0).getPtwo30() >= recordbyidL2.get(0).getPtwo30()) {
                                    p302 = 1;
                                } else {
                                    p302 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPthree30() == 0.0 && recordbyidL2.get(0).getPthree30() == 0.0) {
                                p303 = 0;
                            } else {
                                if (recordbyidL.get(0).getPthree30() >= recordbyidL2.get(0).getPthree30()) {
                                    p303 = 1;
                                } else {
                                    p303 = 0;
                                }

                            }
                            if (recordbyidL.get(0).getPone50() == 0.0 && recordbyidL2.get(0).getPone50() == 0.0) {
                                p501 = 0;
                            } else {
                                if (recordbyidL.get(0).getPone50() >= recordbyidL2.get(0).getPone50()) {
                                    p501 = 1;
                                } else {
                                    p501 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPtwo50() == 0.0 && recordbyidL2.get(0).getPtwo50() == 0.0) {
                                p502 = 0;
                            } else {
                                if (recordbyidL.get(0).getPtwo50() >= recordbyidL2.get(0).getPtwo50()) {
                                    p502 = 1;
                                } else {
                                    p502 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPthree50() == 0.0 && recordbyidL2.get(0).getPthree50() == 0.0) {
                                p503 = 0;
                            } else {
                                if (recordbyidL.get(0).getPthree50() >= recordbyidL2.get(0).getPthree50()) {
                                    p503 = 1;
                                } else {
                                    p503 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPone100() == 0.0 && recordbyidL2.get(0).getPone100() == 0.0) {
                                p1001 = 0;
                            } else {
                                if (recordbyidL.get(0).getPone100() >= recordbyidL2.get(0).getPone100()) {
                                    p1001 = 1;
                                } else {
                                    p1001 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPtwo100() == 0.0 && recordbyidL2.get(0).getPtwo100() == 0.0) {
                                p1002 = 0;
                            } else {
                                if (recordbyidL.get(0).getPtwo100() >= recordbyidL2.get(0).getPtwo100()) {
                                    p1002 = 1;
                                } else {
                                    p1002 = 0;
                                }

                            }

                            if (recordbyidL.get(0).getPthree100() == 0.0 && recordbyidL2.get(0).getPthree100() == 0.0) {
                                p1003 = 0;
                            } else {
                                if (recordbyidL.get(0).getPthree100() >= recordbyidL2.get(0).getPthree100()) {
                                    p1003 = 1;
                                } else {
                                    p1003 = 0;
                                }

                            }

                            Result = ssqCalService.updateSSQMLModelbyId(lasthit, lastscore, formax, oddeven, smallbig, primecom, p51, p52, p53, p101, p102, p103, p201, p202
                                    , p203, p301, p302, p303, p501, p502, p503, p1001, p1002, p1003, nowhit, nowscore, threesum, div30, div31, div32,divergence,skewness,acV,expIDN);
                            rcount = rcount + Result;


                        }

                    }


                }

            }catch (Exception e){

                e.printStackTrace();
            }
        }

                System.out.println("新增共计"+rcount+"条历史数据更新错误成绩");
            }

    }

