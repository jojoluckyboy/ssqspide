package com.spider.lottery.precodition.task;

import com.spider.lottery.pojo.SsqThred;
import com.spider.lottery.ssqservice.SsqCalService;
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
public class CalPerformScoreTask {

    @Autowired
    private SsqCalService ssqCalService;


    /**
     * 前置准备导入历史数据
     *
     * @return 0 or 1
     * @throws Exception
     */

    private int rcount = 0;
    private int Result = 0;

    public void pushIcrementData() throws Exception {

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
                int expID = findid.get(0).getId();

                List<SsqThred> findbyexpert = ssqCalService.selectIDbyExplimit(expID, expertname);

                int id = 0;

                int hitScore = 0;
                int errorScore = 0;
                int performScore = 0;

                int red1 = 0;
                int red2 = 0;
                int red3 = 0;

                int distinct1 = 0;
                int distinct2 = 0;
                int distinct3 = 0;
                int distinct = 10;




                List<SsqThred> findbyexpert1 = ssqCalService.selectIDbyExpPesAsc(expertname, 1000);
                boolean flag = findbyexpert.addAll(findbyexpert1);
                if (flag && findbyexpert.size() >= 3) {
                    /*loop4:
                    for (int j = 0; j < 2; j++) {

                        String[] orired = findbyexpert.get(j).getRedthree().split(" ");
                        if (orired.length == 3) {
                            red1 = Integer.parseInt(orired[0]);
                            red2 = Integer.parseInt(orired[1]);
                            red3 = Integer.parseInt(orired[2]);

                            if (red1 > 0 && red1 <= 11) {
                                distinct1 = 1;
                            } else if (red1 >= 12 && red1 <= 22) {
                                distinct1 = 2;
                            } else if (red1 >= 23 && red1 <= 33) {
                                distinct1 = 3;
                            }

                            if (red2 > 0 && red2 <= 11) {
                                distinct2 = 1;
                            } else if (red2 >= 12 && red2 <= 22) {
                                distinct2 = 2;
                            } else if (red2 >= 23 && red2 <= 33) {
                                distinct2 = 3;
                            }

                            if (red3 > 0 && red3 <= 11) {
                                distinct3 = 1;
                            } else if (red3 >= 12 && red3 <= 22) {
                                distinct3 = 2;
                            } else if (red3 >= 23 && red3 <= 33) {
                                distinct3 = 3;
                            }

                            if (distinct1 == 1 && distinct2 == 2 && distinct3 == 3) {
                                distinct = 0;
                            } else if (distinct1 + distinct2 == 2 || distinct1 + distinct3 == 2 || distinct2 + distinct3 == 2) {
                                distinct = 1;

                            } else if (distinct1 + distinct2 == 6 || distinct1 + distinct3 == 6 || distinct2 + distinct3 == 6) {
                                distinct = 3;

                            } else {

                                distinct = 2;
                            }

                        }
                        //折算成绩
                        if (findbyexpert.get(j).getHitrecord() == 3) {
                            hitScore = 100;
                        }

                        if (findbyexpert.get(j).getHitrecord() == 2) {
                            hitScore = 75;

                        }

                        if (findbyexpert.get(j).getHitrecord() == 1) {
                            hitScore = 50;

                        }

                        if (findbyexpert.get(j).getHitrecord() == 0) {
                            hitScore = 0;

                        }

                        errorScore = 0;
                        //上两期连错大于等于3，当前连对2期+5 ptwo30<ptwo20<ptwo10


                        performScore = errorScore + hitScore;
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateDisPerS(errorScore, performScore, distinct, id);
                        rcount = rcount + Result;


                    }*/
                    loop2:
                    for (int j = 2; j < findbyexpert.size(); j++) {

                        String[] orired = findbyexpert.get(j).getRedthree().split(" ");
                        if (orired.length == 3) {
                            red1 = Integer.parseInt(orired[0]);
                            red2 = Integer.parseInt(orired[1]);
                            red3 = Integer.parseInt(orired[2]);

                            if (red1 > 0 && red1 <= 11) {
                                distinct1 = 1;
                            } else if (red1 >= 12 && red1 <= 22) {
                                distinct1 = 2;
                            } else if (red1 >= 23 && red1 <= 33) {
                                distinct1 = 3;
                            }

                            if (red2 > 0 && red2 <= 11) {
                                distinct2 = 1;
                            } else if (red2 >= 12 && red2 <= 22) {
                                distinct2 = 2;
                            } else if (red2 >= 23 && red2 <= 33) {
                                distinct2 = 3;
                            }

                            if (red3 > 0 && red3 <= 11) {
                                distinct3 = 1;
                            } else if (red3 >= 12 && red3 <= 22) {
                                distinct3 = 2;
                            } else if (red3 >= 23 && red3 <= 33) {
                                distinct3 = 3;
                            }

                            if (distinct1 == 1 && distinct2 == 2 && distinct3 == 3) {
                                distinct = 0;
                            } else if (distinct1 + distinct2 == 2 || distinct1 + distinct3 == 2 || distinct2 + distinct3 == 2) {
                                distinct = 1;

                            } else if (distinct1 + distinct2 == 6 || distinct1 + distinct3 == 6 || distinct2 + distinct3 == 6) {
                                distinct = 3;

                            } else {

                                distinct = 2;
                            }

                        }
                        //折算成绩
                        if (findbyexpert.get(j).getHitrecord() == 3) {
                            hitScore = 100;
                        }

                        if (findbyexpert.get(j).getHitrecord() == 2) {
                            hitScore = 75;

                        }

                        if (findbyexpert.get(j).getHitrecord() == 1) {
                            hitScore = 50;

                        }

                        if (findbyexpert.get(j).getHitrecord() == 0) {
                            hitScore = 0;

                        }

                        //连错模式成绩
                        //上期连错大于历史最大，当前错 + 50

                        if (findbyexpert.get(j).getLastconterrorpd() > findbyexpert.get(j).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord() == 0) {
                            errorScore = 50;
                        }
                        //上期连错大于历史最大，当前期数对 +100
                        else if (findbyexpert.get(j).getLastconterrorpd() > findbyexpert.get(j).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord() > 0) {
                            errorScore = 100;
                        }
                        //上两期连错大于历史最大，当前期数连对2期 +40
                        else if (findbyexpert.get(j - 1).getLastconterrorpd() > findbyexpert.get(j - 1).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord() > 0
                                && findbyexpert.get(j - 1).getHitrecord() > 0 && findbyexpert.get(j).getPtwo30() < findbyexpert.get(j).getPtwo20() && findbyexpert.get(j).getPtwo20() < findbyexpert.get(j).getPtwo10()) {
                            errorScore = 40;
                        }

                        //上期连错大于等于6，当前错 +30
                        else if (findbyexpert.get(j).getLastconterrorpd() >= 6 && findbyexpert.get(j).getLastconterrorpd() <= findbyexpert.get(j - 1).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord() == 0) {
                            errorScore = 30;
                        }

                        //上期连错大于等于6，当前期数对+50       ptwo30<ptwo20<ptwo10
                        else if (findbyexpert.get(j).getLastconterrorpd() >= 6 && findbyexpert.get(j).getLastconterrorpd() <= findbyexpert.get(j - 1).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord() > 0
                                && findbyexpert.get(j).getPtwo30() < findbyexpert.get(j).getPtwo20() && findbyexpert.get(j).getPtwo20() < findbyexpert.get(j).getPtwo10()) {
                            errorScore = 50;
                        }

                        //上两期连错大于等于6，当前期数连续2期对+20       ptwo30<ptwo20<ptwo10
                        else if (findbyexpert.get(j - 1).getLastconterrorpd() >= 6 && findbyexpert.get(j - 1).getLastconterrorpd() <= findbyexpert.get(j - 1).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord() > 0
                                && findbyexpert.get(j - 1).getHitrecord() > 0 && findbyexpert.get(j).getPtwo30() < findbyexpert.get(j).getPtwo20() && findbyexpert.get(j).getPtwo20() < findbyexpert.get(j).getPtwo10()) {
                            errorScore = 20;
                        }

                        //上期连错大于等于2，当前错+20
                        else if (findbyexpert.get(j).getLastconterrorpd() >= 2 && findbyexpert.get(j).getLastconterrorpd() < 6 && findbyexpert.get(j).getHitrecord() == 0) {
                            errorScore = 20;
                        }

                        //上期连错大于等于3，当前对+30  ptwo30<ptwo20<ptwo10
                        else if (findbyexpert.get(j).getLastconterrorpd() >= 3 && findbyexpert.get(j).getLastconterrorpd() < 6 && findbyexpert.get(j).getHitrecord() > 0
                                && findbyexpert.get(j).getPtwo30() < findbyexpert.get(j).getPtwo20() && findbyexpert.get(j).getPtwo20() < findbyexpert.get(j).getPtwo10()) {
                            errorScore = 30;
                        }

                        //上两期连错大于等于3，当前连对2期+5 ptwo30<ptwo20<ptwo10
                        else if (findbyexpert.get(j - 1).getLastconterrorpd() >= 3 && findbyexpert.get(j - 1).getLastconterrorpd() < 6 && findbyexpert.get(j - 1).getHitrecord() > 0 && findbyexpert.get(j).getHitrecord() > 0
                                && findbyexpert.get(j).getPtwo30() < findbyexpert.get(j).getPtwo20() && findbyexpert.get(j).getPtwo20() < findbyexpert.get(j).getPtwo10()) {
                            errorScore = 5;
                        } else {

                            errorScore = 0;
                        }


                        performScore = errorScore + hitScore;
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateDisPerS(errorScore, performScore, distinct, id);
                        rcount = rcount + Result;


                    }

                } else {
                    loop2:
                    for (int j = 0; j < findbyexpert.size(); j++) {

                        String[] orired = findbyexpert.get(j).getRedthree().split(" ");
                        if (orired.length == 3) {
                            red1 = Integer.parseInt(orired[0]);
                            red2 = Integer.parseInt(orired[1]);
                            red3 = Integer.parseInt(orired[2]);

                            if (red1 > 0 && red1 <= 11) {
                                distinct1 = 1;
                            } else if (red1 >= 12 && red1 <= 22) {
                                distinct1 = 2;
                            } else if (red1 >= 23 && red1 <= 33) {
                                distinct1 = 3;
                            }

                            if (red2 > 0 && red2 <= 11) {
                                distinct2 = 1;
                            } else if (red2 >= 12 && red2 <= 22) {
                                distinct2 = 2;
                            } else if (red2 >= 23 && red2 <= 33) {
                                distinct2 = 3;
                            }

                            if (red3 > 0 && red3 <= 11) {
                                distinct3 = 1;
                            } else if (red3 >= 12 && red3 <= 22) {
                                distinct3 = 2;
                            } else if (red3 >= 23 && red3 <= 33) {
                                distinct3 = 3;
                            }

                            if (distinct1 == 1 && distinct2 == 2 && distinct3 == 3) {
                                distinct = 0;
                            } else if (distinct1 + distinct2 == 2 || distinct1 + distinct3 == 2 || distinct2 + distinct3 == 2) {
                                distinct = 1;

                            } else if (distinct1 + distinct2 == 6 || distinct1 + distinct3 == 6 || distinct2 + distinct3 == 6) {
                                distinct = 3;

                            } else {

                                distinct = 2;
                            }

                        }
                        //折算成绩
                        if (findbyexpert.get(j).getHitrecord() == 3) {
                            hitScore = 100;
                        }

                        if (findbyexpert.get(j).getHitrecord() == 2) {
                            hitScore = 75;

                        }

                        if (findbyexpert.get(j).getHitrecord() == 1) {
                            hitScore = 50;

                        }

                        if (findbyexpert.get(j).getHitrecord() == 0) {
                            hitScore = 0;

                        }

                        errorScore = 0;
                        //上两期连错大于等于3，当前连对2期+5 ptwo30<ptwo20<ptwo10


                        performScore = errorScore + hitScore;
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateDisPerS(errorScore, performScore, distinct, id);
                        rcount = rcount + Result;


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
        SsqCalService ssqCalCalService = (SsqCalService) context.getBean("SsqCalService");

        int rcount = 0;

        List<String> findexpert = ssqCalCalService.selectByexpert();
        //List<String> findexpert= Arrays.asList();


        Loop1:
        for (int i = 0; i < findexpert.size(); i++) {
            String expertname = findexpert.get(i).toString();
          //  String expertname = "一码当先";
            List<SsqThred> findbyexpert = ssqCalCalService.selectGroupbyExpDesc(expertname);


            int paironeC = 0;
            int pairtwoC = 0;
            int pairthreeC = 0;
            int paircalcu = 0;

            int Result = 0;
            int id= 0;

            int hitScore = 0;
            int errorScore = 0;
            int performScore = 0;

            int red1=0;
            int red2=0;
            int red3=0;

            int distinct1=0;
            int distinct2=0;
            int distinct3=0;
            int distinct=10;




            loop2:
            if(findbyexpert.size()>=3){
                for(int j =  2; j<findbyexpert.size(); j++){

                    String [] orired = findbyexpert.get(j).getRedthree().split(" ");
                    if(orired.length==3) {
                        red1 = Integer.parseInt(orired[0]);
                        red2 = Integer.parseInt(orired[1]);
                        red3 = Integer.parseInt(orired[2]);

                        if(red1>0 && red1<=11){
                            distinct1 = 1;
                        }else if(red1>=12 && red1<=22){
                            distinct1 = 2;
                        }else if(red1>=23 && red1<=33){
                            distinct1 = 3;
                        }

                        if(red2>0 && red2<=11){
                            distinct2 = 1;
                        }else if(red2>=12 && red2<=22){
                            distinct2 = 2;
                        }else if(red2>=23 && red2<=33){
                            distinct2 = 3;
                        }

                        if(red3>0 && red3<=11){
                            distinct3 = 1;
                        }else if(red3>=12 && red3<=22){
                            distinct3 = 2;
                        }else if(red3>=23 && red3<=33){
                            distinct3 = 3;
                        }

                        if(distinct1==1 && distinct2==2 && distinct3==3){
                            distinct = 0;
                        }else if(distinct1+distinct2==2 || distinct1+distinct3==2 || distinct2+distinct3==2) {
                            distinct = 1;

                        }
                        else if(distinct1+distinct2==6 || distinct1+distinct3==6 || distinct2+distinct3==6) {
                            distinct = 3;

                        }else{

                            distinct = 2;
                        }

                    }
                    //折算成绩
                    if(findbyexpert.get(j).getHitrecord()==3){
                        hitScore = 100;
                    }

                    if(findbyexpert.get(j).getHitrecord()==2){
                        hitScore = 75;

                    }

                    if(findbyexpert.get(j).getHitrecord()==1){
                        hitScore = 50;

                    }

                    if(findbyexpert.get(j).getHitrecord()==0){
                        hitScore = 0;

                    }

                    //连错模式成绩
                    //上期连错大于历史最大，当前错 + 50

                    if(findbyexpert.get(j).getLastconterrorpd()>findbyexpert.get(j).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord()==0){
                        errorScore = 50;
                    }
                    //上期连错大于历史最大，当前期数对 +100
                    else if(findbyexpert.get(j).getLastconterrorpd()>findbyexpert.get(j).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord()>0){
                        errorScore = 100;
                    }
                    //上两期连错大于历史最大，当前期数连对2期 +40
                    else if(findbyexpert.get(j-1).getLastconterrorpd()>findbyexpert.get(j-1).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord()>0
                            && findbyexpert.get(j-1).getHitrecord()>0 && findbyexpert.get(j).getPtwo30()<findbyexpert.get(j).getPtwo20() && findbyexpert.get(j).getPtwo20()<findbyexpert.get(j).getPtwo10()){
                        errorScore = 40;
                    }

                    //上期连错大于等于6，当前错 +30
                    else if(findbyexpert.get(j).getLastconterrorpd()>=6 && findbyexpert.get(j).getLastconterrorpd()<= findbyexpert.get(j-1).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord()==0){
                        errorScore = 30;
                    }

                    //上期连错大于等于6，当前期数对+50       ptwo30<ptwo20<ptwo10
                    else if(findbyexpert.get(j).getLastconterrorpd()>=6 && findbyexpert.get(j).getLastconterrorpd()<= findbyexpert.get(j-1).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord()>0
                            && findbyexpert.get(j).getPtwo30()<findbyexpert.get(j).getPtwo20() && findbyexpert.get(j).getPtwo20()<findbyexpert.get(j).getPtwo10()){
                        errorScore = 50;
                    }

                    //上两期连错大于等于6，当前期数连续2期对+20       ptwo30<ptwo20<ptwo10
                    else if(findbyexpert.get(j-1).getLastconterrorpd()>=6 && findbyexpert.get(j-1).getLastconterrorpd()<= findbyexpert.get(j-1).getHistorymaxerrorpd() && findbyexpert.get(j).getHitrecord()>0
                            && findbyexpert.get(j-1).getHitrecord()>0 && findbyexpert.get(j).getPtwo30()<findbyexpert.get(j).getPtwo20() && findbyexpert.get(j).getPtwo20()<findbyexpert.get(j).getPtwo10()){
                        errorScore = 20;
                    }

                    //上期连错大于等于2，当前错+20
                    else if(findbyexpert.get(j).getLastconterrorpd()>=2 && findbyexpert.get(j).getLastconterrorpd()<6 && findbyexpert.get(j).getHitrecord()==0){
                        errorScore = 20;
                    }

                    //上期连错大于等于3，当前对+30  ptwo30<ptwo20<ptwo10
                    else if(findbyexpert.get(j).getLastconterrorpd()>=3 && findbyexpert.get(j).getLastconterrorpd()<6 && findbyexpert.get(j).getHitrecord()>0
                            && findbyexpert.get(j).getPtwo30()<findbyexpert.get(j).getPtwo20() && findbyexpert.get(j).getPtwo20()<findbyexpert.get(j).getPtwo10()){
                        errorScore = 30;
                    }

                    //上两期连错大于等于3，当前连对2期+5 ptwo30<ptwo20<ptwo10
                    else if(findbyexpert.get(j-1).getLastconterrorpd()>=3 && findbyexpert.get(j-1).getLastconterrorpd()<6 && findbyexpert.get(j-1).getHitrecord()>0 && findbyexpert.get(j).getHitrecord()>0
                            && findbyexpert.get(j).getPtwo30()<findbyexpert.get(j).getPtwo20() && findbyexpert.get(j).getPtwo20()<findbyexpert.get(j).getPtwo10()){
                        errorScore = 5;
                    }else{

                        errorScore = 0;
                    }


                    performScore= errorScore+hitScore;
                    id = findbyexpert.get(j).getId();
                    Result = ssqCalCalService.updateDisPerS(errorScore,performScore,distinct,id);
                    rcount = rcount + Result;


                }


                    }
                    else
            {
                for(int j =  0; j<findbyexpert.size(); j++){

                String [] orired = findbyexpert.get(j).getRedthree().split(" ");
                if(orired.length==3) {
                    red1 = Integer.parseInt(orired[0]);
                    red2 = Integer.parseInt(orired[1]);
                    red3 = Integer.parseInt(orired[2]);

                    if(red1>0 && red1<=11){
                        distinct1 = 1;
                    }else if(red1>=12 && red1<=22){
                        distinct1 = 2;
                    }else if(red1>=23 && red1<=33){
                        distinct1 = 3;
                    }

                    if(red2>0 && red2<=11){
                        distinct2 = 1;
                    }else if(red2>=12 && red2<=22){
                        distinct2 = 2;
                    }else if(red2>=23 && red2<=33){
                        distinct2 = 3;
                    }

                    if(red3>0 && red3<=11){
                        distinct3 = 1;
                    }else if(red3>=12 && red3<=22){
                        distinct3 = 2;
                    }else if(red3>=23 && red3<=33){
                        distinct3 = 3;
                    }

                    if(distinct1==1 && distinct2==2 && distinct3==3){
                        distinct = 0;
                    }else if(distinct1+distinct2==2 || distinct1+distinct3==2 || distinct2+distinct3==2) {
                        distinct = 1;

                    }
                    else if(distinct1+distinct2==6 || distinct1+distinct3==6 || distinct2+distinct3==6) {
                        distinct = 3;

                    }else{

                        distinct = 2;
                    }

                }
                //折算成绩
                if(findbyexpert.get(j).getHitrecord()==3){
                    hitScore = 100;
                }

                if(findbyexpert.get(j).getHitrecord()==2){
                    hitScore = 75;

                }

                if(findbyexpert.get(j).getHitrecord()==1){
                    hitScore = 50;

                }

                if(findbyexpert.get(j).getHitrecord()==0){
                    hitScore = 0;

                }
                errorScore = 0;
                performScore= errorScore+hitScore;
                id = findbyexpert.get(j).getId();
                Result = ssqCalCalService.updateDisPerS(errorScore,performScore,distinct,id);
                rcount = rcount + Result;


            }


        }

                }
                System.out.println("新增共计"+rcount+"条历史数据更新错误成绩");
            }

    }

