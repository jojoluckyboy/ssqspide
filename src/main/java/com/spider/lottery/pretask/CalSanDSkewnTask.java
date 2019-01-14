package com.spider.lottery.pretask;

import com.spider.lottery.pojo.SandDankill;
import com.spider.lottery.sanDservice.SanDCalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
public class CalSanDSkewnTask {

    @Autowired
    private SanDCalService sanDCalService;

    /*生克计算*,相生4 反生3 相克1 反克2 相平0/*/
    public int spicerock(String singDan, String lastsingDan )throws Exception {

       int spicerock = 0;
       //木
       if (singDan.equals("3") || singDan.equals("8")){


           if (lastsingDan.equals("1") || lastsingDan.equals("6")){

               spicerock = 4;

           }
           if (lastsingDan.equals("2") || lastsingDan.equals("7")){

               spicerock = 3;

           }
           if (lastsingDan.equals("4") || lastsingDan.equals("9")){

               spicerock = 1;

           }

           if (lastsingDan.equals("5") || lastsingDan.equals("0")){

               spicerock = 2;

           }
           if (lastsingDan.equals("3") || lastsingDan.equals("8")){

               spicerock = 0;

           }


       }

        //火
        if (singDan.equals("2") || singDan.equals("7")){


            if (lastsingDan.equals("3") || lastsingDan.equals("8")){

                spicerock = 4;

            }
            if (lastsingDan.equals("0") || lastsingDan.equals("5")){

                spicerock = 3;

            }
            if (lastsingDan.equals("1") || lastsingDan.equals("6")){

                spicerock = 1;

            }

            if (lastsingDan.equals("4") || lastsingDan.equals("9")){

                spicerock = 2;

            }
            if (lastsingDan.equals("2") || lastsingDan.equals("7")){

                spicerock = 0;

            }


        }


        //土
        if (singDan.equals("0") || singDan.equals("5")){


            if (lastsingDan.equals("2") || lastsingDan.equals("7")){

                spicerock = 4;

            }
            if (lastsingDan.equals("4") || lastsingDan.equals("9")){

                spicerock = 3;

            }
            if (lastsingDan.equals("3") || lastsingDan.equals("8")){

                spicerock = 1;

            }

            if (lastsingDan.equals("1") || lastsingDan.equals("6")){

                spicerock = 2;

            }
            if (lastsingDan.equals("0") || lastsingDan.equals("5")){

                spicerock = 0;

            }


        }

        //金
        if (singDan.equals("4") || singDan.equals("9")){


            if (lastsingDan.equals("0") || lastsingDan.equals("5")){

                spicerock = 4;

            }
            if (lastsingDan.equals("1") || lastsingDan.equals("6")){

                spicerock = 3;

            }
            if (lastsingDan.equals("2") || lastsingDan.equals("7")){

                spicerock = 1;

            }

            if (lastsingDan.equals("3") || lastsingDan.equals("8")){

                spicerock = 2;

            }
            if (lastsingDan.equals("4") || lastsingDan.equals("9")){

                spicerock = 0;

            }


        }

        //水
        if (singDan.equals("1") || singDan.equals("6")){


            if (lastsingDan.equals("4") || lastsingDan.equals("9")){

                spicerock = 4;

            }
            if (lastsingDan.equals("3") || lastsingDan.equals("8")){

                spicerock = 3;

            }
            if (lastsingDan.equals("0") || lastsingDan.equals("5")){

                spicerock = 1;

            }

            if (lastsingDan.equals("2") || lastsingDan.equals("7")){

                spicerock = 2;

            }
            if (lastsingDan.equals("1") || lastsingDan.equals("6")){

                spicerock = 0;

            }


        }


        return spicerock;

    }


    /*散度计算*/
    public int divergence(String[] orired )throws Exception {

        List<Integer> compareList = new ArrayList<Integer>();

        for (int i = 0; i < orired.length; i++){

            compareList.add(Integer.parseInt(orired[i]));
        }

        Collections.sort(compareList);

        int divergence = compareList.get(0);
        return divergence;

    }


    /*偏度计算*/
    public int skewnessfiveDan(String[] orired,String[] lastrecord )throws Exception {
        int dank;
        int matchV=0;
        int matchT;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;

        for (int i = 0; i < orired.length; i++) {
            dank = Integer.parseInt(orired[i]);
            for (int j = 0; j < lastrecord.length; j++) {
                if (j==0){
                    matchV=Math.abs(dank-Integer.parseInt(lastrecord[j]));
                }
                matchT = Math.abs(dank-Integer.parseInt(lastrecord[j]));
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
            if (i==3){
                d = matchV;
            }
            if (i==4){
                e = matchV;
            }
        }

        int max = a>=b?a:b;
        int max1 = max>=c?max:c;
        int max2 = max1>=d?max1:d;
        int skewnessfiveDan = max2>=e?max2:e;
        return skewnessfiveDan;

    }

    public int skewness(String[] orired,int record )throws Exception {
        int dank;

        List<Integer> compareList = new ArrayList<Integer>();

        for (int i = 0; i < orired.length; i++) {
            dank = Integer.parseInt(orired[i]);
            compareList.add(Math.abs(dank-record));

            }

        Collections.sort(compareList);
        int skewness = compareList.get(0);
        return skewness;

    }

    /*AC值计算*/
    public int acV(String[] orired )throws Exception {

        List<Integer> compareList = new ArrayList<Integer>();

        for (int i = 0; i < orired.length; i++){

            for (int j = i+1; j < orired.length; j++){

                compareList.add(Math.abs(Integer.parseInt(orired[j])-Integer.parseInt(orired[i])));

            }
        }
        HashSet h = new HashSet(compareList);
        compareList.clear();
        compareList.addAll(h);

        int acV = compareList.size();
        return acV;

    }


    /**
     * 前置准备导入历史数据
     *
     * @return 0 or 1
     * @throws Exception
     */

    private int rcount = 0;
    private int rcount11 = 0;
    private int rcount1 = 0;

    public void pushIcrementData() throws Exception {


/*        int startid = 1;
        int endid = 600;*/

        int fid = 0;
        String fissue;
        String lissue;
        int startid = sanDCalService.selectIdByRockasc(888);
        int endid = sanDCalService.selectIdByRockdesc(888);

        SandDankill sandDankill = new SandDankill();

        for (int id = startid; id < endid +1 ; id++) {

            List<SandDankill> divsknewn = sanDCalService.selectByID(id);
            String sanDV = divsknewn.get(0).getSanDV();
            String singDan = divsknewn.get(0).getSingDan();
            String fiveDanV = divsknewn.get(0).getFiveDanV();

            int sanDsum = divsknewn.get(0).getSanDsum();
            int sanDkd = divsknewn.get(0).getSanDkd();
            String sumDanV = divsknewn.get(0).getSumDanV();
            String kuaDanV = divsknewn.get(0).getKuaDanV();


            //五胆散度，偏度，ac
            int fiveDandiv = 0;
            int skewnessfiveDan = 0;
            int fiveDanacV = 0;
            if (fiveDanV.equals("--")) {

                fiveDandiv = 0;

            } else {

                String[] orired = fiveDanV.split(" ");
                String[] record = new String[3];
                record[0] = sanDV.substring(0, 1);
                record[1] = sanDV.substring(1, 2);
                record[2] = sanDV.substring(2, 3);
                fiveDandiv = divergence(orired);
                skewnessfiveDan = skewnessfiveDan(orired, record);
                fiveDanacV = acV(orired);
            }


            //和值散度，偏度，ac
            int sumDandiv = 0;
            int sumDanskewn = 0;
            int sumDanacV = 0;
            if (sumDanV.equals("--")) {

                sumDandiv = 0;

            } else {

                String[] oriredsum = sumDanV.split(" ");

                sumDandiv = divergence(oriredsum);
                sumDanskewn = skewness(oriredsum, sanDsum);
                sumDanacV = acV(oriredsum);
            }

            //跨度散度，偏度，ac
            int kuaDandiv = 0;
            int kuaDanskewn = 0;
            int kuaDanacV = 0;
            if (kuaDanV.equals("--")) {

                kuaDandiv = 0;

            } else {

                String[] oriredsum = kuaDanV.split(" ");

                kuaDandiv = divergence(oriredsum);
                kuaDanskewn = skewness(oriredsum, sanDsum);
                kuaDanacV = acV(oriredsum);
            }



            int Result = sanDCalService.updateSanDACV(fiveDandiv, skewnessfiveDan, fiveDanacV, sumDandiv, sumDanskewn,
                    sumDanacV, kuaDandiv, kuaDanskewn, kuaDanacV, id);
            if (Result == 1) {
                rcount1 = rcount1 + 1;
            }
        }
        System.out.println("共计" + rcount1 + "条跨度数据更新");
        List<String> findexpert = sanDCalService.selectexpertByID(startid, endid);

        Loop1:
        for (int i = 0; i < findexpert.size(); i++) {

            String expertname = findexpert.get(i).toString();
     //     String expertname = "云岭彩经";
            List<SandDankill> findid = sanDCalService.selectIDbyExpDIV(expertname, 888, endid);
            if (findid.size() == 0) {
                continue;
            }
            int expID = findid.get(0).getId();

            List<SandDankill> findLastid = sanDCalService.selectIDbyExpHDIVlast(expID, expertname);

            int spicerock = 0;
            int singDankd = 0;


            //增加一年加一个

/*            2013001,2012359
                2014001,2013358
                2015001,2014357
                2016001,2015358
                2017001,2016359
                2018001,2017358
                2019001,2018358*/

            boolean bool1 = false;
            boolean bool2 = false;
            boolean bool3 = false;
            boolean bool4 = false;
            boolean bool5 = false;
            boolean bool6 = false;
            boolean bool7 = false;
            boolean bool8 = false;


            if (findLastid.size() == 0) {

                spicerock = 0;
                singDankd = 0;


                int Result = sanDCalService.updateSanDRock(spicerock, singDankd, expID);
                if (Result == 1) {
                    rcount = rcount + 1;
                }


                Loop2:
                for (int j = 1; j < findid.size(); j++) {
                    fid = findid.get(j).getId();
                    fissue = findid.get(j).getIssue();
                    lissue = findid.get(j - 1).getIssue();

                    bool1 = fissue.equals("2013001") && lissue.equals("2012359");
                    bool2 = fissue.equals("2014001") && lissue.equals("2013358");
                    bool3 = fissue.equals("2015001") && lissue.equals("2014357");
                    bool4 = fissue.equals("2016001") && lissue.equals("2015358");
                    bool5 = fissue.equals("2017001") && lissue.equals("2016359");
                    bool6 = fissue.equals("2018001") && lissue.equals("2017358");
                    bool8 = fissue.equals("2019001") && lissue.equals("2018358");
                    bool7 = lissue.equals(String.valueOf(Integer.parseInt(fissue) - 1));

                    List<SandDankill> newfind = sanDCalService.selectIDbyUpdateACV(fid, expertname);

                    if (bool1 || bool2 || bool3 || bool4 || bool5 || bool6 || bool7 || bool8) {

                        String SingDan = newfind.get(0).getSingDan();

                        String lastSingDan = newfind.get(1).getSingDan();

                        if(SingDan.equals("--") || lastSingDan.equals("--")){

                            singDankd = 0;
                            spicerock = 0;

                        }else {

                            singDankd = Math.abs(Integer.parseInt(SingDan) - Integer.parseInt(lastSingDan));
                            spicerock = spicerock(SingDan, lastSingDan);
                        }



                        newfind.clear();


                    } else {

                        singDankd = 0;
                        spicerock = 0;


                    }
                    Result = sanDCalService.updateSanDRock(spicerock, singDankd, fid);
                    if (Result == 1) {
                        rcount = rcount + 1;
                    }

                }
            }else{

                int lid = findLastid.get(0).getId();
                List<SandDankill> newfind1 = sanDCalService.selectIDbyUpdateACV(expID, expertname);

                String lastSingDan = findLastid.get(0).getSingDan();
                String SingDan = newfind1.get(0).getSingDan();

                if(SingDan.equals("--") || lastSingDan.equals("--")){

                    singDankd = 0;
                    spicerock = 0;

                }else {

                    singDankd = Math.abs(Integer.parseInt(SingDan) - Integer.parseInt(lastSingDan));
                    spicerock = spicerock(SingDan, lastSingDan);
                }



                int Result = sanDCalService.updateSanDRock(spicerock, singDankd, expID);
                newfind1.clear();

                Loop3:
                for (int j = 1; j < findid.size(); j++) {
                    fid = findid.get(j).getId();
                    fissue = findid.get(j).getIssue();
                    lissue = findid.get(j - 1).getIssue();

                    bool1 = fissue.equals("2013001") && lissue.equals("2012359");
                    bool2 = fissue.equals("2014001") && lissue.equals("2013358");
                    bool3 = fissue.equals("2015001") && lissue.equals("2014357");
                    bool4 = fissue.equals("2016001") && lissue.equals("2015358");
                    bool5 = fissue.equals("2017001") && lissue.equals("2016359");
                    bool6 = fissue.equals("2018001") && lissue.equals("2017358");
                    bool8 = fissue.equals("2019001") && lissue.equals("2018358");
                    bool7 = lissue.equals(String.valueOf(Integer.parseInt(fissue) - 1));

                    List<SandDankill> newfind = sanDCalService.selectIDbyUpdateACV(fid, expertname);

                    if (bool1 || bool2 || bool3 || bool4 || bool5 || bool6 || bool7 || bool8) {

                        SingDan = newfind.get(0).getSingDan();

                        lastSingDan = newfind.get(1).getSingDan();

                        if(SingDan.equals("--") || lastSingDan.equals("--")){

                            singDankd = 0;
                            spicerock = 0;

                        }else {

                            singDankd = Math.abs(Integer.parseInt(SingDan) - Integer.parseInt(lastSingDan));
                            spicerock = spicerock(SingDan, lastSingDan);
                        }


                        newfind.clear();


                    } else {

                        singDankd = 0;
                        spicerock = 0;


                    }
                    Result = sanDCalService.updateSanDRock(spicerock, singDankd, fid);
                    if (Result == 1) {
                        rcount = rcount + 1;
                    }

                    newfind.clear();
                }



                }


            }

            System.out.println("共计" + rcount + "条生克数据更新");


    }

    public int getIncreDcount(){
        return this.rcount1;

    }

    public void pushIcrementDataTask() throws Exception {


/*        int startid = 1;
        int endid = 600;*/

        int fid = 0;
        String fissue;
        String lissue;
        int startidtmp1 = sanDCalService.selectIdByRockasc(888);
        int endidtmp1 = sanDCalService.selectIdByRockdesc(888);
        //新版新加,旧版没有  int startid = sanDCalService.selectIdByRockasc(888);
        int startidtmp2 = sanDCalService.selectIdBySkewnasc(888);
        int endidtmp2 = sanDCalService.selectIdBySkewndesc(888);
        int startid;
        int endid;

        if(startidtmp1<= startidtmp2){
            startid = startidtmp1;

        }else{

            startid = startidtmp2;
        }


        if(endidtmp1<= endidtmp2){
            endid = endidtmp1;

        }else{

            endid = endidtmp2;
        }

        SandDankill sandDankill = new SandDankill();

        for (int id = startid; id < endid +1 ; id++) {

            List<SandDankill> divsknewn = sanDCalService.selectByID(id);
            String sanDV = divsknewn.get(0).getSanDV();
            String singDan = divsknewn.get(0).getSingDan();
            String fiveDanV = divsknewn.get(0).getFiveDanV();

            int sanDsum = divsknewn.get(0).getSanDsum();
            int sanDkd = divsknewn.get(0).getSanDkd();
            String sumDanV = divsknewn.get(0).getSumDanV();
            String kuaDanV = divsknewn.get(0).getKuaDanV();


            //五胆散度，偏度，ac
            int fiveDandiv = 0;
            int skewnessfiveDan = 0;
            int fiveDanacV = 0;
            if (fiveDanV.equals("--")) {

                fiveDandiv = 0;

            } else {

                String[] orired = fiveDanV.split(" ");
                String[] record = new String[3];
                fiveDandiv = divergence(orired);
                if(sanDV==null){//新版新加判断sanDV==null,旧版没有

                    skewnessfiveDan=888;

                }else{
                    record[0] = sanDV.substring(0, 1);
                    record[1] = sanDV.substring(1, 2);
                    record[2] = sanDV.substring(2, 3);

                    skewnessfiveDan = skewnessfiveDan(orired, record);

                }

                fiveDanacV = acV(orired);
            }


            //和值散度，偏度，ac
            int sumDandiv = 0;
            int sumDanskewn = 0;
            int sumDanacV = 0;
            if (sumDanV.equals("--")) {

                sumDandiv = 0;

            } else {

                String[] oriredsum = sumDanV.split(" ");

                sumDandiv = divergence(oriredsum);
                if(sanDV==null){//新版新加判断sanDV==null,旧版没有

                    sumDanskewn=888;

                }else{
                    sumDanskewn = skewness(oriredsum, sanDsum);
                }


                sumDanacV = acV(oriredsum);
            }

            //跨度散度，偏度，ac
            int kuaDandiv = 0;
            int kuaDanskewn = 0;
            int kuaDanacV = 0;
            if (kuaDanV.equals("--")) {

                kuaDandiv = 0;

            } else {

                String[] oriredsum = kuaDanV.split(" ");

                kuaDandiv = divergence(oriredsum);
                if(sanDV==null){//新版新加判断sanDV==null,旧版没有

                    kuaDanskewn=888;

                }else{
                    kuaDanskewn = skewness(oriredsum, sanDkd);
                }


                kuaDanacV = acV(oriredsum);
            }



            int Result = sanDCalService.updateSanDACV(fiveDandiv, skewnessfiveDan, fiveDanacV, sumDandiv, sumDanskewn,
                    sumDanacV, kuaDandiv, kuaDanskewn, kuaDanacV, id);

            if (Result == 1) {
                rcount11 = rcount11 + 1;
            }

            if(rcount11%1000==0){

                System.out.println("休息下");

            }
        }
        System.out.println("共计" + rcount11 + "条跨度数据更新");
        List<String> findexpert = sanDCalService.selectexpertByID(startid, endid);

        Loop1:
        for (int i = 0; i < findexpert.size(); i++) {

            String expertname = findexpert.get(i).toString();
            //     String expertname = "云岭彩经";
            List<SandDankill> findid = sanDCalService.selectIDbyExpDIV(expertname, 888, endid);
            if (findid.size() == 0) {
                continue;
            }
            int expID = findid.get(0).getId();

            List<SandDankill> findLastid = sanDCalService.selectIDbyExpHDIVlast(expID, expertname);

            int spicerock = 0;
            int singDankd = 0;


            //增加一年加一个

/*            2013001,2012359
                2014001,2013358
                2015001,2014357
                2016001,2015358
                2017001,2016359
                2018001,2017358
                2019001,2018358
                */

            boolean bool1 = false;
            boolean bool2 = false;
            boolean bool3 = false;
            boolean bool4 = false;
            boolean bool5 = false;
            boolean bool6 = false;
            boolean bool7 = false;
            boolean bool8 = false;

            if (findLastid.size() == 0) {

                spicerock = 0;
                singDankd = 0;


                int Result = sanDCalService.updateSanDRock(spicerock, singDankd, expID);
                if (Result == 1) {
                    rcount1 = rcount1 + 1;
                }


                Loop2:
                for (int j = 1; j < findid.size(); j++) {
                    fid = findid.get(j).getId();
                    fissue = findid.get(j).getIssue();
                    lissue = findid.get(j - 1).getIssue();

                    bool1 = fissue.equals("2013001") && lissue.equals("2012359");
                    bool2 = fissue.equals("2014001") && lissue.equals("2013358");
                    bool3 = fissue.equals("2015001") && lissue.equals("2014357");
                    bool4 = fissue.equals("2016001") && lissue.equals("2015358");
                    bool5 = fissue.equals("2017001") && lissue.equals("2016359");
                    bool6 = fissue.equals("2018001") && lissue.equals("2017358");
                    bool8 = fissue.equals("2019001") && lissue.equals("2018358");
                    bool7 = lissue.equals(String.valueOf(Integer.parseInt(fissue) - 1));

                    List<SandDankill> newfind = sanDCalService.selectIDbyUpdateACV(fid, expertname);

                    if (bool1 || bool2 || bool3 || bool4 || bool5 || bool6 || bool7 || bool8) {

                        String SingDan = newfind.get(0).getSingDan();

                        String lastSingDan = newfind.get(1).getSingDan();

                        if(SingDan.equals("--") || lastSingDan.equals("--")){

                            singDankd = 0;
                            spicerock = 0;

                        }else {

                            singDankd = Math.abs(Integer.parseInt(SingDan) - Integer.parseInt(lastSingDan));
                            spicerock = spicerock(SingDan, lastSingDan);
                        }



                        newfind.clear();


                    } else {

                        singDankd = 0;
                        spicerock = 0;


                    }
                    Result = sanDCalService.updateSanDRock(spicerock, singDankd, fid);
                    if (Result == 1) {
                        rcount1 = rcount1 + 1;
                    }

                    if(rcount1%1000==0){

                        System.out.println("休息下");

                    }
                }


            }else{

                int lid = findLastid.get(0).getId();
                List<SandDankill> newfind1 = sanDCalService.selectIDbyUpdateACV(expID, expertname);

                String lastSingDan = findLastid.get(0).getSingDan();
                String SingDan = newfind1.get(0).getSingDan();

                if(SingDan.equals("--") || lastSingDan.equals("--")){

                    singDankd = 0;
                    spicerock = 0;

                }else {

                    singDankd = Math.abs(Integer.parseInt(SingDan) - Integer.parseInt(lastSingDan));
                    spicerock = spicerock(SingDan, lastSingDan);
                }



                int Result = sanDCalService.updateSanDRock(spicerock, singDankd, expID);
                newfind1.clear();

                Loop3:
                for (int j = 1; j < findid.size(); j++) {
                    fid = findid.get(j).getId();
                    fissue = findid.get(j).getIssue();
                    lissue = findid.get(j - 1).getIssue();

                    bool1 = fissue.equals("2013001") && lissue.equals("2012359");
                    bool2 = fissue.equals("2014001") && lissue.equals("2013358");
                    bool3 = fissue.equals("2015001") && lissue.equals("2014357");
                    bool4 = fissue.equals("2016001") && lissue.equals("2015358");
                    bool5 = fissue.equals("2017001") && lissue.equals("2016359");
                    bool6 = fissue.equals("2018001") && lissue.equals("2017358");
                    bool8 = fissue.equals("2019001") && lissue.equals("2018358");
                    bool7 = lissue.equals(String.valueOf(Integer.parseInt(fissue) - 1));

                    List<SandDankill> newfind = sanDCalService.selectIDbyUpdateACV(fid, expertname);

                    if (bool1 || bool2 || bool3 || bool4 || bool5 || bool6 || bool7 || bool8) {

                        SingDan = newfind.get(0).getSingDan();

                        lastSingDan = newfind.get(1).getSingDan();

                        if(SingDan.equals("--") || lastSingDan.equals("--")){

                            singDankd = 0;
                            spicerock = 0;

                        }else {

                            singDankd = Math.abs(Integer.parseInt(SingDan) - Integer.parseInt(lastSingDan));
                            spicerock = spicerock(SingDan, lastSingDan);
                        }


                        newfind.clear();


                    } else {

                        singDankd = 0;
                        spicerock = 0;


                    }
                    Result = sanDCalService.updateSanDRock(spicerock, singDankd, fid);
                    if (Result == 1) {
                        rcount1 = rcount1 + 1;
                    }

                    newfind.clear();
                }



            }


        }

        System.out.println("共计" + rcount1 + "条生克数据更新");


    }


    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        CalSanDSkewnTask calSanDSkewnTask = (CalSanDSkewnTask) context.getBean("CalSanDSkewnTask");

 //       SanDCalService sanDCalService = (SanDCalService) context.getBean("SanDCalService");
        System.out.print("测试");

        try {
            calSanDSkewnTask.pushIcrementData();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}