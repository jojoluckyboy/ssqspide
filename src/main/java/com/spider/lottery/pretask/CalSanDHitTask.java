package com.spider.lottery.pretask;

import com.spider.lottery.pojo.SandDankill;
import com.spider.lottery.sanDservice.SanDCalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

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
public class CalSanDHitTask {

    @Autowired
    private SanDCalService sanDCalService;


    /**
     * 前置准备导入历史数据
     *
     * @return 0 or 1
     * @throws Exception
     */

    private int rcount = 0;
    private int rcount1 = 0;

    public void pushIcrementData() throws Exception {




        int fid = 0;
        String fissue;
        String lissue;
//        int startid = sanDCalService.selectIdByDVasc(888);
//        int endid = sanDCalService.selectIdByDVdesc(888);

        int startid = 62505;
        int endid = 374444;

        SandDankill sandDankill = new SandDankill();

        for (int id = startid; id < endid +1 ; id++) {

            List<SandDankill> hitresult = sanDCalService.selectByID(id);
            String sanDV = hitresult.get(0).getSanDV();
            String singDan = hitresult.get(0).getSingDan();
            String fiveDanV = hitresult.get(0).getFiveDanV();

            int sanDsum = hitresult.get(0).getSanDsum();
            int sanDkd = hitresult.get(0).getSanDkd();
            String sumDanV = hitresult.get(0).getSumDanV();
            String kuaDanV = hitresult.get(0).getKuaDanV();

            //判断独胆命中
            int singDanhit = 0;
            if(singDan.equals("--")){

               singDanhit = 0;

            }else if(!singDan.equals("--") && sanDV.contains(singDan)){

                singDanhit = 1;
            }else if(!singDan.equals("--") && !sanDV.contains(singDan)){

                singDanhit = 0;
            }

            String[] fivesplit = fiveDanV.split(" ");
            String[] sumDanVsplit = sumDanV.split(" ");
            String[] kuaDanVsplit = kuaDanV.split(" ");

            //判断五胆命中
            int fiveDanhit = 0;
            if(fiveDanV.equals("--")){

                fiveDanhit = 0;

            }else {

                for (int j = 0; j < fivesplit.length; j++) {
                    if(sanDV.contains(fivesplit[j])){

                        fiveDanhit = fiveDanhit + 1;

                    }
                }
            }

            //判断和值命中
            int sumDanhit = 0;
            if(sumDanV.equals("--")){

                sumDanhit = 0;

            }else {

                for (int k = 0; k < sumDanVsplit.length; k++) {
                    if(String.valueOf(sanDsum).equals(sumDanVsplit[k])){

                        sumDanhit = 1;
                        break;

                    }
                }
            }

            //判断跨度命中
            int kuaDanhit = 0;
            if(kuaDanV.equals("--")){

                kuaDanhit = 0;

            }else {

                for (int l = 0; l < kuaDanVsplit.length; l++) {
                    if(String.valueOf(sanDkd).equals(kuaDanVsplit[l])){

                        kuaDanhit = 1;
                        break;

                    }
                }
            }


            int Result = sanDCalService.updateSanDhit(singDanhit, fiveDanhit, sumDanhit, kuaDanhit, id);
            if (Result == 1) {
                rcount = rcount + 1;
            }
        }
        System.out.println("共计" + rcount + "条Hit历史数据更新");

        List<String> findexpert = sanDCalService.selectexpertByID(startid, endid);

        Loop1:
        for (int i = 0; i < findexpert.size(); i++) {

            String expertname = findexpert.get(i).toString();


            List<SandDankill> findid = sanDCalService.selectIDbyExpHit(expertname, 888, endid);
            if (findid.size() == 0) {
                continue;
            }

            if (findid.size() == 1) {

                int expID = findid.get(0).getId();

                List<SandDankill> findLastid = sanDCalService.selectIDbyExpHitlast(expID, expertname);

                int singDanlasthit = 0;
                int singDanconhit = 0;
                int fiveDanlasthit = 0;
                int fiveDanconhit = 0;
                int sumDanlasthit = 0;
                int sumDanconhit = 0;
                int kuaDanlasthit = 0;
                int kuaDanconhit = 0;

                int singDanlasthitTmp = 0;
                int singDanconhitTmp = 0;
                int fiveDanlasthitTmp = 0;
                int fiveDanconhitTmp = 0;
                int sumDanlasthitTmp = 0;
                int sumDanconhitTmp = 0;
                int kuaDanlasthitTmp = 0;
                int kuaDanconhitTmp = 0;

                //增加一年加一个

/*            2013001,2012359
                2014001,2013358
                2015001,2014357
                2016001,2015358
                2017001,2016359
                2018001,2017358*/

                boolean bool1 = false;
                boolean bool2 = false;
                boolean bool3 = false;
                boolean bool4 = false;
                boolean bool5 = false;
                boolean bool6 = false;
                boolean bool7 = false;


                if (findLastid.size() == 0) {

                    singDanconhitTmp = 0;
                    fiveDanconhitTmp = 0;

                    sumDanconhitTmp = 0;
                    kuaDanconhitTmp = 0;

                    singDanlasthit = 0;
                    fiveDanlasthit = 0;

                    sumDanlasthit = 0;
                    kuaDanlasthit = 0;

                    int Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhitTmp, fiveDanlasthit, fiveDanconhitTmp, sumDanlasthit,
                            sumDanconhitTmp, kuaDanlasthit, kuaDanconhitTmp, expID);
                    if (Result == 1) {
                        rcount1 = rcount1 + 1;
                    }


                } else {

                    int lid = findLastid.get(0).getId();

                    singDanconhitTmp = findLastid.get(0).getSingDanconhit();
                    fiveDanconhitTmp = findLastid.get(0).getFiveDanconhit();

                    sumDanconhitTmp = findLastid.get(0).getSumDanconhit();
                    kuaDanconhitTmp = findLastid.get(0).getKuaDanconhit();

                    singDanlasthit = findLastid.get(0).getSingDanhit();
                    fiveDanlasthit = findLastid.get(0).getFiveDanhit();

                    sumDanlasthit = findLastid.get(0).getSumDanhit();
                    kuaDanlasthit = findLastid.get(0).getKuaDanhit();

                    if (singDanlasthit > 0) {

                        singDanconhit = singDanconhitTmp + 1;

                    } else {

                        singDanconhit = 0;

                    }

                    if (fiveDanlasthit > 0) {

                        fiveDanconhit = fiveDanconhitTmp + 1;

                    } else {

                        fiveDanconhit = 0;

                    }

                    if (sumDanlasthit > 0) {

                        sumDanconhit = sumDanconhitTmp + 1;

                    } else {

                        sumDanconhit = 0;

                    }

                    if (kuaDanlasthit > 0) {

                        kuaDanconhit = kuaDanconhitTmp + 1;

                    } else {

                        kuaDanconhit = 0;

                    }
                    int Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhit, fiveDanlasthit, fiveDanconhit, sumDanlasthit,
                            sumDanconhit, kuaDanlasthit, kuaDanconhit, expID);

                    if (Result == 1) {
                        rcount1 = rcount1 + 1;
                    }


                }

            } else {
                int expID = findid.get(0).getId();

                List<SandDankill> findLastid = sanDCalService.selectIDbyExpHitlast(expID, expertname);

                int singDanlasthit = 0;
                int singDanconhit = 0;
                int fiveDanlasthit = 0;
                int fiveDanconhit = 0;
                int sumDanlasthit = 0;
                int sumDanconhit = 0;
                int kuaDanlasthit = 0;
                int kuaDanconhit = 0;

                int singDanlasthitTmp = 0;
                int singDanconhitTmp = 0;
                int fiveDanlasthitTmp = 0;
                int fiveDanconhitTmp = 0;
                int sumDanlasthitTmp = 0;
                int sumDanconhitTmp = 0;
                int kuaDanlasthitTmp = 0;
                int kuaDanconhitTmp = 0;

                //增加一年加一个
/*              2013001,2012359
                2014001,2013358
                2015001,2014357
                2016001,2015358
                2017001,2016359
                2018001,2017358**/

                boolean bool1 = false;
                boolean bool2 = false;
                boolean bool3 = false;
                boolean bool4 = false;
                boolean bool5 = false;
                boolean bool6 = false;
                boolean bool7 = false;


                if (findLastid.size() == 0) {

                    singDanconhitTmp = 0;
                    fiveDanconhitTmp = 0;

                    sumDanconhitTmp = 0;
                    kuaDanconhitTmp = 0;

                    singDanlasthit = 0;
                    fiveDanlasthit = 0;

                    sumDanlasthit = 0;
                    kuaDanlasthit = 0;

                    int Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhitTmp, fiveDanlasthit, fiveDanconhitTmp, sumDanlasthit,
                            sumDanconhitTmp, kuaDanlasthit, kuaDanconhitTmp, expID);
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
                        bool7 = lissue.equals(String.valueOf(Integer.parseInt(fissue) - 1));

                        List<SandDankill> newfind = sanDCalService.selectIDbyUpdateHit(fid, expertname);

                        if (bool1 || bool2 || bool3 || bool4 || bool5 || bool6 || bool7) {

                            singDanlasthit = newfind.get(1).getSingDanhit();
                            fiveDanlasthit = newfind.get(1).getFiveDanhit();

                            sumDanlasthit = newfind.get(1).getSumDanhit();
                            kuaDanlasthit = newfind.get(1).getKuaDanhit();

                            singDanconhitTmp = newfind.get(1).getSingDanconhit();
                            fiveDanconhitTmp = newfind.get(1).getFiveDanconhit();

                            if (singDanlasthit > 0) {

                                singDanconhit = singDanconhitTmp + 1;

                            } else {

                                singDanconhit = 0;

                            }

                            if (fiveDanlasthit > 0) {

                                fiveDanconhit = fiveDanconhitTmp + 1;

                            } else {

                                fiveDanconhit = 0;

                            }

                            sumDanconhitTmp = newfind.get(1).getSumDanconhit();
                            kuaDanconhitTmp = newfind.get(1).getKuaDanconhit();

                            if (sumDanlasthit > 0) {

                                sumDanconhit = sumDanconhitTmp + 1;

                            } else {

                                sumDanconhit = 0;

                            }

                            if (kuaDanlasthit > 0) {

                                kuaDanconhit = kuaDanconhitTmp + 1;

                            } else {

                                kuaDanconhit = 0;

                            }

                        } else {

                            singDanconhit = 0;
                            fiveDanconhit = 0;

                            sumDanconhit = 0;
                            kuaDanconhit = 0;

                            singDanlasthit = 0;
                            fiveDanlasthit = 0;

                            sumDanlasthit = 0;
                            kuaDanlasthit = 0;

                        }

                        Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhit, fiveDanlasthit, fiveDanconhit, sumDanlasthit,
                                sumDanconhit, kuaDanlasthit, kuaDanconhit, fid);

                        if (Result == 1) {
                            rcount = rcount + 1;
                        }

                        newfind.clear();

                    }

                } else {

                    int lid = findLastid.get(0).getId();

                    singDanconhitTmp = findLastid.get(0).getSingDanconhit();
                    fiveDanconhitTmp = findLastid.get(0).getFiveDanconhit();

                    sumDanconhitTmp = findLastid.get(0).getSumDanconhit();
                    kuaDanconhitTmp = findLastid.get(0).getKuaDanconhit();

                    singDanlasthit = findLastid.get(0).getSingDanhit();
                    fiveDanlasthit = findLastid.get(0).getFiveDanhit();

                    sumDanlasthit = findLastid.get(0).getSumDanhit();
                    kuaDanlasthit = findLastid.get(0).getKuaDanhit();

                    if (singDanlasthit > 0) {

                        singDanconhit = singDanconhitTmp + 1;

                    } else {

                        singDanconhit = 0;

                    }

                    if (fiveDanlasthit > 0) {

                        fiveDanconhit = fiveDanconhitTmp + 1;

                    } else {

                        fiveDanconhit = 0;

                    }

                    if (sumDanlasthit > 0) {

                        sumDanconhit = sumDanconhitTmp + 1;

                    } else {

                        sumDanconhit = 0;

                    }

                    if (kuaDanlasthit > 0) {

                        kuaDanconhit = kuaDanconhitTmp + 1;

                    } else {

                        kuaDanconhit = 0;

                    }
                    int Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhit, fiveDanlasthit, fiveDanconhit, sumDanlasthit,
                            sumDanconhit, kuaDanlasthit, kuaDanconhit, expID);

                    if (Result == 1) {
                        rcount = rcount + 1;
                    }
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
                        bool7 = lissue.equals(String.valueOf(Integer.parseInt(fissue) - 1));

                        List<SandDankill> newfind = sanDCalService.selectIDbyUpdateHit(fid, expertname);
                        if (bool1 || bool2 || bool3 || bool4 || bool5 || bool6 || bool7) {

                            singDanlasthit = newfind.get(1).getSingDanhit();
                            fiveDanlasthit = newfind.get(1).getFiveDanhit();

                            sumDanlasthit = newfind.get(1).getSumDanhit();
                            kuaDanlasthit = newfind.get(1).getKuaDanhit();

                            singDanconhitTmp = newfind.get(1).getSingDanconhit();
                            fiveDanconhitTmp = newfind.get(1).getFiveDanconhit();

                            if (singDanlasthit > 0) {

                                singDanconhit = singDanconhitTmp + 1;

                            } else {

                                singDanconhit = 0;

                            }

                            if (fiveDanlasthit > 0) {

                                fiveDanconhit = fiveDanconhitTmp + 1;

                            } else {

                                fiveDanconhit = 0;

                            }

                            sumDanconhitTmp = newfind.get(1).getSumDanconhit();
                            kuaDanconhitTmp = newfind.get(1).getKuaDanconhit();

                            if (sumDanlasthit > 0) {

                                sumDanconhit = sumDanconhitTmp + 1;

                            } else {

                                sumDanconhit = 0;

                            }

                            if (kuaDanlasthit > 0) {

                                kuaDanconhit = kuaDanconhitTmp + 1;

                            } else {

                                kuaDanconhit = 0;

                            }

                        } else {

                            singDanconhit = 0;
                            fiveDanconhit = 0;

                            sumDanconhit = 0;
                            kuaDanconhit = 0;

                            singDanlasthit = 0;
                            fiveDanlasthit = 0;

                            sumDanlasthit = 0;
                            kuaDanlasthit = 0;

                        }

                        Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhit, fiveDanlasthit, fiveDanconhit, sumDanlasthit,
                                sumDanconhit, kuaDanlasthit, kuaDanconhit, fid);

                        if (Result == 1) {
                            rcount = rcount + 1;
                        }

                        newfind.clear();
                    }


                }


            }
        }
        System.out.println("共计" + rcount + "条Hit历史数据更新");

    }

    public void pushIcrementDataTask() throws Exception {




        int fid = 0;
        String fissue;
        String lissue;

        int startid = 386100;
        int endid = 386140;
        try {

             startid = sanDCalService.selectIdByDVasc(888);
             endid = sanDCalService.selectIdByDVdesc(888);

        }catch (Exception e) {
            System.out.println("库中不存在新数据");
            e.printStackTrace();
        }


        if(startid == 0){

            return;
        }

        SandDankill sandDankill = new SandDankill();

        for (int id = startid; id < endid +1 ; id++) {

            List<SandDankill> hitresult = sanDCalService.selectByID(id);
            String sanDV = hitresult.get(0).getSanDV();
            String singDan = hitresult.get(0).getSingDan();
            String fiveDanV = hitresult.get(0).getFiveDanV();

            int sanDsum = hitresult.get(0).getSanDsum();
            int sanDkd = hitresult.get(0).getSanDkd();
            String sumDanV = hitresult.get(0).getSumDanV();
            String kuaDanV = hitresult.get(0).getKuaDanV();

            //判断独胆命中
            int singDanhit = 0;
            if(singDan.equals("--")){

                singDanhit = 0;

            }else if(!singDan.equals("--") && sanDV.contains(singDan)){

                singDanhit = 1;
            }else if(!singDan.equals("--") && !sanDV.contains(singDan)){

                singDanhit = 0;
            }

            String[] fivesplit = fiveDanV.split(" ");
            String[] sumDanVsplit = sumDanV.split(" ");
            String[] kuaDanVsplit = kuaDanV.split(" ");

            //判断五胆命中
            int fiveDanhit = 0;
            if(fiveDanV.equals("--")){

                fiveDanhit = 0;

            }else {

                for (int j = 0; j < fivesplit.length; j++) {
                    if(sanDV.contains(fivesplit[j])){

                        fiveDanhit = fiveDanhit + 1;

                    }
                }
            }

            //判断和值命中
            int sumDanhit = 0;
            if(sumDanV.equals("--")){

                sumDanhit = 0;

            }else {

                for (int k = 0; k < sumDanVsplit.length; k++) {
                    if(String.valueOf(sanDsum).equals(sumDanVsplit[k])){

                        sumDanhit = 1;
                        break;

                    }
                }
            }

            //判断跨度命中
            int kuaDanhit = 0;
            if(kuaDanV.equals("--")){

                kuaDanhit = 0;

            }else {

                for (int l = 0; l < kuaDanVsplit.length; l++) {
                    if(String.valueOf(sanDkd).equals(kuaDanVsplit[l])){

                        kuaDanhit = 1;
                        break;

                    }
                }
            }


            int Result = sanDCalService.updateSanDhit(singDanhit, fiveDanhit, sumDanhit, kuaDanhit, id);
            if (Result == 1) {
                rcount1 = rcount1 + 1;
            }
        }
        System.out.println("共计" + rcount1 + "条Hit历史数据更新");

        List<String> findexpert = sanDCalService.selectexpertByID(startid, endid);

        Loop1:
        for (int i = 0; i < findexpert.size(); i++) {

            String expertname = findexpert.get(i).toString();

            List<SandDankill> findid = sanDCalService.selectIDbyExpHit(expertname, 888, endid);
            if (findid.size() == 0) {
                continue;
            }

            if (findid.size() == 1) {
                int expID = findid.get(0).getId();

                List<SandDankill> findLastid = sanDCalService.selectIDbyExpHitlast(expID, expertname);

                int singDanlasthit = 0;
                int singDanconhit = 0;
                int fiveDanlasthit = 0;
                int fiveDanconhit = 0;
                int sumDanlasthit = 0;
                int sumDanconhit = 0;
                int kuaDanlasthit = 0;
                int kuaDanconhit = 0;

                int singDanlasthitTmp = 0;
                int singDanconhitTmp = 0;
                int fiveDanlasthitTmp = 0;
                int fiveDanconhitTmp = 0;
                int sumDanlasthitTmp = 0;
                int sumDanconhitTmp = 0;
                int kuaDanlasthitTmp = 0;
                int kuaDanconhitTmp = 0;

                //增加一年加一个

/*            2013001,2012359
                2014001,2013358
                2015001,2014357
                2016001,2015358
                2017001,2016359
                2018001,2017358*/

                boolean bool1 = false;
                boolean bool2 = false;
                boolean bool3 = false;
                boolean bool4 = false;
                boolean bool5 = false;
                boolean bool6 = false;
                boolean bool7 = false;


                if (findLastid.size() == 0) {

                    singDanconhitTmp = 0;
                    fiveDanconhitTmp = 0;

                    sumDanconhitTmp = 0;
                    kuaDanconhitTmp = 0;

                    singDanlasthit = 0;
                    fiveDanlasthit = 0;

                    sumDanlasthit = 0;
                    kuaDanlasthit = 0;

                    int Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhitTmp, fiveDanlasthit, fiveDanconhitTmp, sumDanlasthit,
                            sumDanconhitTmp, kuaDanlasthit, kuaDanconhitTmp, expID);
                    if (Result == 1) {
                        rcount1 = rcount1 + 1;
                    }


                } else {

                    int lid = findLastid.get(0).getId();

                    singDanconhitTmp = findLastid.get(0).getSingDanconhit();
                    fiveDanconhitTmp = findLastid.get(0).getFiveDanconhit();

                    sumDanconhitTmp = findLastid.get(0).getSumDanconhit();
                    kuaDanconhitTmp = findLastid.get(0).getKuaDanconhit();

                    singDanlasthit = findLastid.get(0).getSingDanhit();
                    fiveDanlasthit = findLastid.get(0).getFiveDanhit();

                    sumDanlasthit = findLastid.get(0).getSumDanhit();
                    kuaDanlasthit = findLastid.get(0).getKuaDanhit();

                    if (singDanlasthit > 0) {

                        singDanconhit = singDanconhitTmp + 1;

                    } else {

                        singDanconhit = 0;

                    }

                    if (fiveDanlasthit > 0) {

                        fiveDanconhit = fiveDanconhitTmp + 1;

                    } else {

                        fiveDanconhit = 0;

                    }

                    if (sumDanlasthit > 0) {

                        sumDanconhit = sumDanconhitTmp + 1;

                    } else {

                        sumDanconhit = 0;

                    }

                    if (kuaDanlasthit > 0) {

                        kuaDanconhit = kuaDanconhitTmp + 1;

                    } else {

                        kuaDanconhit = 0;

                    }
                    int Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhit, fiveDanlasthit, fiveDanconhit, sumDanlasthit,
                            sumDanconhit, kuaDanlasthit, kuaDanconhit, expID);

                    if (Result == 1) {
                        rcount1 = rcount1 + 1;
                    }


                }


            } else {

                int expID = findid.get(0).getId();

                List<SandDankill> findLastid = sanDCalService.selectIDbyExpHitlast(expID, expertname);

                int singDanlasthit = 0;
                int singDanconhit = 0;
                int fiveDanlasthit = 0;
                int fiveDanconhit = 0;
                int sumDanlasthit = 0;
                int sumDanconhit = 0;
                int kuaDanlasthit = 0;
                int kuaDanconhit = 0;

                int singDanlasthitTmp = 0;
                int singDanconhitTmp = 0;
                int fiveDanlasthitTmp = 0;
                int fiveDanconhitTmp = 0;
                int sumDanlasthitTmp = 0;
                int sumDanconhitTmp = 0;
                int kuaDanlasthitTmp = 0;
                int kuaDanconhitTmp = 0;

                //增加一年加一个

/*            2013001,2012359
                2014001,2013358
                2015001,2014357
                2016001,2015358
                2017001,2016359
                2018001,2017358*/

                boolean bool1 = false;
                boolean bool2 = false;
                boolean bool3 = false;
                boolean bool4 = false;
                boolean bool5 = false;
                boolean bool6 = false;
                boolean bool7 = false;


                if (findLastid.size() == 0) {

                    singDanconhitTmp = 0;
                    fiveDanconhitTmp = 0;

                    sumDanconhitTmp = 0;
                    kuaDanconhitTmp = 0;

                    singDanlasthit = 0;
                    fiveDanlasthit = 0;

                    sumDanlasthit = 0;
                    kuaDanlasthit = 0;

                    int Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhitTmp, fiveDanlasthit, fiveDanconhitTmp, sumDanlasthit,
                            sumDanconhitTmp, kuaDanlasthit, kuaDanconhitTmp, expID);
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
                        bool7 = lissue.equals(String.valueOf(Integer.parseInt(fissue) - 1));

                        List<SandDankill> newfind = sanDCalService.selectIDbyUpdateHit(fid, expertname);

                        if (bool1 || bool2 || bool3 || bool4 || bool5 || bool6 || bool7) {

                            singDanlasthit = newfind.get(1).getSingDanhit();
                            fiveDanlasthit = newfind.get(1).getFiveDanhit();

                            sumDanlasthit = newfind.get(1).getSumDanhit();
                            kuaDanlasthit = newfind.get(1).getKuaDanhit();

                            singDanconhitTmp = newfind.get(1).getSingDanconhit();
                            fiveDanconhitTmp = newfind.get(1).getFiveDanconhit();

                            if (singDanlasthit > 0) {

                                singDanconhit = singDanconhitTmp + 1;

                            } else {

                                singDanconhit = 0;

                            }

                            if (fiveDanlasthit > 0) {

                                fiveDanconhit = fiveDanconhitTmp + 1;

                            } else {

                                fiveDanconhit = 0;

                            }

                            sumDanconhitTmp = newfind.get(1).getSumDanconhit();
                            kuaDanconhitTmp = newfind.get(1).getKuaDanconhit();

                            if (sumDanlasthit > 0) {

                                sumDanconhit = sumDanconhitTmp + 1;

                            } else {

                                sumDanconhit = 0;

                            }

                            if (kuaDanlasthit > 0) {

                                kuaDanconhit = kuaDanconhitTmp + 1;

                            } else {

                                kuaDanconhit = 0;

                            }

                        } else {

                            singDanconhit = 0;
                            fiveDanconhit = 0;

                            sumDanconhit = 0;
                            kuaDanconhit = 0;

                            singDanlasthit = 0;
                            fiveDanlasthit = 0;

                            sumDanlasthit = 0;
                            kuaDanlasthit = 0;

                        }

                        Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhit, fiveDanlasthit, fiveDanconhit, sumDanlasthit,
                                sumDanconhit, kuaDanlasthit, kuaDanconhit, fid);

                        if (Result == 1) {
                            rcount1 = rcount1 + 1;
                        }

                        newfind.clear();

                    }

                } else {

                    int lid = findLastid.get(0).getId();

                    singDanconhitTmp = findLastid.get(0).getSingDanconhit();
                    fiveDanconhitTmp = findLastid.get(0).getFiveDanconhit();

                    sumDanconhitTmp = findLastid.get(0).getSumDanconhit();
                    kuaDanconhitTmp = findLastid.get(0).getKuaDanconhit();

                    singDanlasthit = findLastid.get(0).getSingDanhit();
                    fiveDanlasthit = findLastid.get(0).getFiveDanhit();

                    sumDanlasthit = findLastid.get(0).getSumDanhit();
                    kuaDanlasthit = findLastid.get(0).getKuaDanhit();

                    if (singDanlasthit > 0) {

                        singDanconhit = singDanconhitTmp + 1;

                    } else {

                        singDanconhit = 0;

                    }

                    if (fiveDanlasthit > 0) {

                        fiveDanconhit = fiveDanconhitTmp + 1;

                    } else {

                        fiveDanconhit = 0;

                    }

                    if (sumDanlasthit > 0) {

                        sumDanconhit = sumDanconhitTmp + 1;

                    } else {

                        sumDanconhit = 0;

                    }

                    if (kuaDanlasthit > 0) {

                        kuaDanconhit = kuaDanconhitTmp + 1;

                    } else {

                        kuaDanconhit = 0;

                    }
                    int Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhit, fiveDanlasthit, fiveDanconhit, sumDanlasthit,
                            sumDanconhit, kuaDanlasthit, kuaDanconhit, expID);

                    if (Result == 1) {
                        rcount1 = rcount1 + 1;
                    }
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
                        bool7 = lissue.equals(String.valueOf(Integer.parseInt(fissue) - 1));

                        List<SandDankill> newfind = sanDCalService.selectIDbyUpdateHit(fid, expertname);
                        if (bool1 || bool2 || bool3 || bool4 || bool5 || bool6 || bool7) {

                            singDanlasthit = newfind.get(1).getSingDanhit();
                            fiveDanlasthit = newfind.get(1).getFiveDanhit();

                            sumDanlasthit = newfind.get(1).getSumDanhit();
                            kuaDanlasthit = newfind.get(1).getKuaDanhit();

                            singDanconhitTmp = newfind.get(1).getSingDanconhit();
                            fiveDanconhitTmp = newfind.get(1).getFiveDanconhit();

                            if (singDanlasthit > 0) {

                                singDanconhit = singDanconhitTmp + 1;

                            } else {

                                singDanconhit = 0;

                            }

                            if (fiveDanlasthit > 0) {

                                fiveDanconhit = fiveDanconhitTmp + 1;

                            } else {

                                fiveDanconhit = 0;

                            }

                            sumDanconhitTmp = newfind.get(1).getSumDanconhit();
                            kuaDanconhitTmp = newfind.get(1).getKuaDanconhit();

                            if (sumDanlasthit > 0) {

                                sumDanconhit = sumDanconhitTmp + 1;

                            } else {

                                sumDanconhit = 0;

                            }

                            if (kuaDanlasthit > 0) {

                                kuaDanconhit = kuaDanconhitTmp + 1;

                            } else {

                                kuaDanconhit = 0;

                            }

                        } else {

                            singDanconhit = 0;
                            fiveDanconhit = 0;

                            sumDanconhit = 0;
                            kuaDanconhit = 0;

                            singDanlasthit = 0;
                            fiveDanlasthit = 0;

                            sumDanlasthit = 0;
                            kuaDanlasthit = 0;

                        }

                        Result = sanDCalService.updateSanDconhit(singDanlasthit, singDanconhit, fiveDanlasthit, fiveDanconhit, sumDanlasthit,
                                sumDanconhit, kuaDanlasthit, kuaDanconhit, fid);

                        if (Result == 1) {
                            rcount1 = rcount1 + 1;
                        }

                        newfind.clear();
                    }


                }


            }

        }


        System.out.println("共计" + rcount1 + "条Hit历史数据更新");

    }

    public int getIncreDcount(){
        return this.rcount1;

    }


    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        CalSanDHitTask calSanDHitTask = (CalSanDHitTask) context.getBean("CalSanDHitTask");

 //     SanDCalService sanDCalService = (SanDCalService) context.getBean("SanDCalService");
        System.out.print("测试");

        try {
            calSanDHitTask.pushIcrementData();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}