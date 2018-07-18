package com.spider.lottery.ssq;

import com.spider.lottery.pojo.SsqAnalyse;
import com.spider.lottery.pojo.SsqThred;
import com.spider.lottery.ssqservice.SsqCalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：计算模型参数
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/12
 * Time：9:55
 * Copyright© 2003-2016 Zhejiang huixin technology company
 *
 */

@Service
public class CaltestPara {

    @Autowired
    private SsqCalService ssqCalService;


    /**
     * 前置准备导入历史数据
     *
     * @return 0 or 1
     * @throws Exception
     */



    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-lottery.xml");
        SsqCalService ssqCalService = (SsqCalService) context.getBean("SsqCalService");

        int rcount = 0;

        List<String> findexpert = ssqCalService.selectByexpert();


        Loop1:
        for (int i = 0; i < findexpert.size(); i++) {
            String expertname = findexpert.get(i).toString();
            //String expertname ="一人留";

                    List<SsqThred> findbyexpert = ssqCalService.selectGroupbyExp(expertname);


            int paironeC = 0;
            int pairtwoC = 0;
            int pairthreeC = 0;
            int pairfourC = 0;
            int pairfiveC = 0;
            int pairsixC = 0;
            int pairsevenC = 0;
            int paireightC = 0;
            int pairnineC = 0;
            int pairtenC = 0;

            int pairelevenC = 0;
            int pairtwelveC = 0;
            int pairthirteenC = 0;
            int pairfourteenC = 0;
            int pairfifteenC = 0;
            int pairsixteenC = 0;
            int pairseventeenC = 0;
            int paireighteenC = 0;
            int pairnineteenC = 0;
            int pairtwentyC = 0;

            int pairtwentyoneC = 0;
            int pairtwentytwoC = 0;
            int pairtwentythreeC = 0;
            int pairtwentyfourC = 0;
            int pairtwentyfiveC = 0;
            int pairtwentysixC = 0;
            int pairtwentysevenC = 0;
            int pairtwentyeightC = 0;
            int pairtwentynineC = 0;
            int pairthirtyC = 0;

            int pert80 = 0;
            int pert90 = 0;

            int linesum = 0;
            int paircalcu = 0;

            int Result = 0;

            loop2:

                for(int j =  0; j<findbyexpert.size(); j++) {

                    if (findbyexpert.get(j).getHitrecord() == 0) {
                        paircalcu = paircalcu + 1;
                        linesum = linesum+1;
                    }

                    if (findbyexpert.get(j).getHitrecord() > 0) {

                        if (paircalcu == 1) {
                            paironeC = paironeC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 2) {
                            pairtwoC = pairtwoC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 3) {
                            pairthreeC = pairthreeC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 4) {
                            pairfourC = pairfourC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 5) {
                            pairfiveC = pairfiveC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 6) {
                            pairsixC = pairsixC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 7) {
                            pairsevenC = pairsevenC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 8) {
                            paireightC = paireightC + 1;
                            paircalcu = 0;
                        }

                        if (paircalcu == 9) {
                            pairnineC = pairnineC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 10) {
                            pairtenC = pairtenC + 1;
                            paircalcu = 0;
                        }


                        if (paircalcu == 11) {
                            pairtwentyoneC = pairtwentyoneC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 12) {
                            pairtwentytwoC = pairtwentytwoC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 13) {
                            pairthirteenC = pairthirteenC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 14) {
                            pairfourteenC = pairfourteenC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 15) {
                            pairfifteenC = pairfifteenC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 16) {
                            pairsixteenC = pairsixteenC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 17) {
                            pairseventeenC = pairseventeenC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 18) {
                            paireighteenC = paireighteenC + 1;
                            paircalcu = 0;
                        }

                        if (paircalcu == 19) {
                            pairnineteenC = pairnineteenC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 20) {
                            pairtwentyC = pairtwentyC + 1;
                            paircalcu = 0;
                        }

                        if (paircalcu == 21) {
                            pairelevenC = pairelevenC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 22) {
                            pairtwelveC = pairtwelveC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 23) {
                            pairtwentythreeC = pairtwentythreeC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 24) {
                            pairtwentyfourC = pairtwentyfourC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 25) {
                            pairtwentyfiveC = pairtwentyfiveC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 26) {
                            pairtwentysixC = pairtwentysixC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 27) {
                            pairtwentysevenC = pairtwentysevenC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu == 28) {
                            pairtwentyeightC = pairtwentyeightC + 1;
                            paircalcu = 0;
                        }

                        if (paircalcu == 29) {
                            pairtwentynineC = pairtwentynineC + 1;
                            paircalcu = 0;
                        }
                        if (paircalcu >= 30) {
                            pairthirtyC = pairthirtyC + 1;
                            paircalcu = 0;
                        }
                    }
                }
                    List<SsqAnalyse> findrecord = ssqCalService.selectByexpert(expertname);

                    SsqAnalyse ssqAnalyse = new SsqAnalyse();

                    if(findrecord.size()==0){

                        ssqAnalyse.setExpertname(expertname);
                        ssqAnalyse.setOne(paironeC);
                        ssqAnalyse.setTwo(pairtwoC);
                        ssqAnalyse.setThree(pairthreeC);
                        ssqAnalyse.setFour(pairfourC);
                        ssqAnalyse.setFive(pairfiveC);
                        ssqAnalyse.setSix(pairsixC);
                        ssqAnalyse.setSeven(pairsevenC);
                        ssqAnalyse.setEight(paireightC);
                        ssqAnalyse.setNine(pairnineC);
                        ssqAnalyse.setTen(pairtenC);

                        ssqAnalyse.setEleven(pairelevenC);
                        ssqAnalyse.setTwelve(pairtwelveC);
                        ssqAnalyse.setThirteen(pairthirteenC);
                        ssqAnalyse.setFourteen(pairfourteenC);
                        ssqAnalyse.setFifteen(pairfifteenC);
                        ssqAnalyse.setSixteen(pairsixteenC);
                        ssqAnalyse.setSeventeen(pairseventeenC);
                        ssqAnalyse.setEighteen(paireighteenC);
                        ssqAnalyse.setNineteen(pairnineteenC);
                        ssqAnalyse.setTwenty(pairtwentyC);

                        ssqAnalyse.setTwentyone(pairtwentyoneC);
                        ssqAnalyse.setTwentytwo(pairtwentytwoC);
                        ssqAnalyse.setTwentythree(pairtwentythreeC);
                        ssqAnalyse.setTwentyfour(pairtwentyfourC);
                        ssqAnalyse.setTwentyfive(pairtwentyfiveC);
                        ssqAnalyse.setTwentysix(pairtwentysixC);
                        ssqAnalyse.setTwentyseven(pairtwentysevenC);
                        ssqAnalyse.setTwentyeight(pairtwentyeightC);
                        ssqAnalyse.setTwentynine(pairtwentynineC);
                        ssqAnalyse.setThirty(pairthirtyC);

                        ssqAnalyse.setPert80(pert80);
                        ssqAnalyse.setPert90(pert90);

                        Result = ssqCalService.insertSSQAnalysys(ssqAnalyse);

                    }else{


                     Result = ssqCalService.updateSSQAnalysys(paironeC ,pairtwoC ,pairthreeC ,pairfourC ,pairfiveC ,
                            pairsixC ,pairsevenC ,paireightC ,pairnineC ,pairtenC ,pairelevenC ,pairtwelveC ,pairthirteenC
                            ,pairfourteenC ,pairfifteenC ,pairsixteenC ,pairseventeenC ,paireighteenC ,pairnineteenC
                            ,pairtwentyC ,pairtwentyoneC ,pairtwentytwoC ,pairtwentythreeC ,pairtwentyfourC ,pairtwentyfiveC
                            ,pairtwentysixC ,pairtwentysevenC ,pairtwentyeightC ,pairtwentynineC ,pairthirtyC,expertname );
                    }
                   if(linesum==0){
                       linesum = 1;
                   }

                    float perT = 0;
            loop3:
            for(int k =  1; k<31; k++) {

                if(k==1){
                    perT=(float)paironeC/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==2){
                    perT=(float)(paironeC+pairtwoC*2)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==3){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==4){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==5){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==6){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==7){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==8){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }

                if(k==9){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==10){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }


                if(k==11){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==12){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==13){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==14){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==15){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==16){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==17){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==18){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }

                if(k==19){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==20){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }

                if(k==21){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==22){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==23){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==24){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==25){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==26){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25+pairtwentysixC*26)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==27){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25+pairtwentysixC*26+pairtwentysevenC*27)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k==28){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25+pairtwentysixC*26+pairtwentysevenC*27
                            +pairtwentyeightC*28)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }

                if(k==29){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25+pairtwentysixC*26+pairtwentysevenC*27
                            +pairtwentyeightC*28+pairtwentynineC*29)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }
                if(k>=30){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25+pairtwentysixC*26+pairtwentysevenC*27
                            +pairtwentyeightC*28+pairtwentynineC*29+pairthirtyC*30)/linesum;
                    if(perT>=0.6){
                        pert80 = k;
                        break;
                    }
                }

            }
            loop4:
            for(int l =  1; l<31; l++) {

                if(l==1){
                    perT=(float)paironeC/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==2){
                    perT=(float)(paironeC+pairtwoC*2)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==3){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==4){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==5){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==6){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==7){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==8){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }

                if(l==9){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==10){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }


                if(l==11){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==12){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==13){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==14){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==15){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==16){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==17){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==18){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }

                if(l==19){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==20){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }

                if(l==21){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==22){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==23){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==24){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==25){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==26){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25+pairtwentysixC*26)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==27){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25+pairtwentysixC*26+pairtwentysevenC*27)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l==28){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25+pairtwentysixC*26+pairtwentysevenC*27
                            +pairtwentyeightC*28)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }

                if(l==29){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25+pairtwentysixC*26+pairtwentysevenC*27
                            +pairtwentyeightC*28+pairtwentynineC*29)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }
                if(l>=30){
                    perT=(float)(paironeC+pairtwoC*2+pairthreeC*3+pairfourC*4+pairfiveC*5+pairsixC*6+pairsevenC*7
                            +paireightC*8+pairnineC*9+pairtenC*10+pairelevenC*11+pairtwelveC*12+pairthirteenC*13
                            +pairfourteenC*14+pairfifteenC*15+pairsixteenC*16+pairseventeenC*17+paireighteenC*18
                            +pairnineteenC*19+pairtwentyC*20+pairtwentyoneC*21+pairtwentytwoC*22+pairtwentythreeC*23
                            +pairtwentyfourC*24+pairtwentyfiveC*25+pairtwentysixC*26+pairtwentysevenC*27
                            +pairtwentyeightC*28+pairtwentynineC*29+pairthirtyC*30)/linesum;
                    if(perT>=0.8){
                        pert90 = l;
                        break;
                    }
                }

            }

                    int Result1 = ssqCalService.updateSSQAnalyPer(pert80,pert90, expertname);
                    rcount = rcount + Result;



                }
                System.out.println("新增共计"+rcount+"条历史数据更新分析表");
            }

        }


