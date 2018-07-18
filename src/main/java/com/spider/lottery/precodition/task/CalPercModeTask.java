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
public class CalPercModeTask {

    @Autowired
    private SsqCalService ssqCalService;


    /**
     * 前置准备导入历史数据
     *
     * @return 0 or 1
     * @throws Exception
     */

    private int rcount = 0;

    public void pushIcrementData() throws Exception {

        List<SsqThred> idstring = ssqCalService.selectIdByP(1000);

        if (idstring.size() != 0) {

            int startid = idstring.get(0).getId();
            int endid = ssqCalService.selectIdByPdesc(1000);

            List<String> findexpert = ssqCalService.selectByexpertID(startid, endid);
            Loop1:
            for (int i = 0; i < findexpert.size(); i++) {
                String expertname = findexpert.get(i).toString();
                //  String expertname = "买套路";
                List<SsqThred> findid = ssqCalService.selectIDbyExpPes(expertname, 1000);
                if (findid.size() == 0) {
                    continue;
                }
                int expID = findid.get(0).getId();
                //findLastid是符合标准的最小当期的上一期记录，1条
                List<SsqThred> findLastid = ssqCalService.selectIDbyExpHitlast(expID, expertname);
                int id = 0;

                int Result = 0;
                if (findLastid.size() == 0) {
                    List<SsqThred> findbyexpert = ssqCalService.selectGroupbyExp(expertname);

                    float paironeP = 0;
                    float pairtwoP = 0;
                    float pairthreeP = 0;
                    int paironeC = 0;
                    int pairtwoC = 0;
                    int pairthreeC = 0;
                    int paircalcu = 0;


                    Loop2:
                    for (int j = 0; j < findbyexpert.size(); j++) {
                        id = findbyexpert.get(j).getId();
                        if (j == 0) {
                            if (findbyexpert.get(j).getHitrecord() > 0) {
                                paironeC = 1;
                                pairtwoC = 0;
                                pairthreeC = 0;
                                paircalcu = 1;


                                Result = ssqCalService.updateSSQpair(paironeC * 100, pairtwoC * 100, pairthreeC * 100, paironeC, pairtwoC, pairthreeC, id);
                                rcount = rcount + Result;

                            } else {
                                paircalcu = 0;

                                Result = ssqCalService.updateSSQpair(paironeC * 100, pairtwoC * 100, pairthreeC * 100, paironeC, pairtwoC, pairthreeC, id);
                                rcount = rcount + Result;
                            }
                        }

                        if (j == 1) {
                            if (paircalcu > 0) {
                                if (findbyexpert.get(j).getHitrecord() > 0) {
                                    pairtwoC = 1;
                                    paircalcu = paircalcu + 1;

                                    paironeP = Math.round(paironeC * 100 / 2);
                                    pairtwoP = Math.round(pairtwoC * 100 / 2);
                                    pairthreeP = Math.round(pairthreeC * 100 / 2);


                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;

                                } else {
                                    paircalcu = 0;
                                    paironeP = Math.round(paironeC * 100 / 2);
                                    pairtwoP = Math.round(pairtwoC * 100 / 2);
                                    pairthreeP = Math.round(pairthreeC * 100 / 2);
                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;
                                }
                            } else {
                                if (findbyexpert.get(j).getHitrecord() > 0) {
                                    paironeC = 1;
                                    pairtwoC = 0;

                                    paircalcu = paircalcu + 1;
                                    paironeP = Math.round(paironeC * 100 / 2);
                                    pairtwoP = Math.round(pairtwoC * 100 / 2);
                                    pairthreeP = Math.round(pairthreeC * 100 / 2);

                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;

                                } else {

                                    paircalcu = 0;
                                    pairtwoP = Math.round(pairtwoC * 100 / 2);
                                    paironeP = Math.round(paironeC * 100 / 2);
                                    pairthreeP = Math.round(pairthreeC * 100 / 2);

                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;
                                }

                            }
                        }


                        if (j > 1) {
                            if (paircalcu == 2) {

                                if (findbyexpert.get(j).getHitrecord() > 0) {

                                    paircalcu = paircalcu + 1;
                                    pairthreeC = pairthreeC + 1;
                                    paironeP = Math.round(paironeC * 100 / (j + 1));
                                    pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                                    pairthreeP = Math.round(pairthreeC * 100 / (j + 1));

                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;

                                } else {

                                    paircalcu = 0;
                                    paironeP = Math.round(paironeC * 100 / (j + 1));
                                    pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                                    pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;
                                }
                            } else if (paircalcu == 1) {
                                if (findbyexpert.get(j).getHitrecord() > 0) {

                                    pairtwoC = pairtwoC + 1;
                                    paircalcu = paircalcu + 1;

                                    pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                                    pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                                    paironeP = Math.round(paironeC * 100 / (j + 1));

                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;

                                } else {
                                    paircalcu = 0;
                                    paironeP = Math.round(paironeC * 100 / (j + 1));
                                    pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                                    pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;
                                }

                            } else if (paircalcu == 0) {
                                if (findbyexpert.get(j).getHitrecord() > 0) {

                                    paironeC = paironeC + 1;
                                    paircalcu = paircalcu + 1;

                                    pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                                    pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                                    paironeP = Math.round(paironeC * 100 / (j + 1));

                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;

                                } else {
                                    paircalcu = 0;
                                    paironeP = Math.round(paironeC * 100 / (j + 1));
                                    pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                                    pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;
                                }

                            } else if (paircalcu > 2) {
                                if (findbyexpert.get(j).getHitrecord() > 0) {

                                    paircalcu = paircalcu + 1;

                                    pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                                    pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                                    paironeP = Math.round(paironeC * 100 / (j + 1));

                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;

                                } else {
                                    paircalcu = 0;
                                    paironeP = Math.round(paironeC * 100 / (j + 1));
                                    pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                                    pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                                    Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                    rcount = rcount + Result;
                                }


                            }
                        }


                    }


                } else {
                    float paironeP = 0;
                    float pairtwoP = 0;
                    float pairthreeP = 0;

                    int paironeC = findLastid.get(0).getPonecount();
                    int pairtwoC = findLastid.get(0).getPtwocount();
                    int pairthreeC = findLastid.get(0).getPthreecount();
                    int countBefore = ssqCalService.selectCountbyExplast(expID, expertname);
                    int paircalcu = 0;
                    //findtwoLastid是符合标准的最小当期的上两期记录，1条
                    List<SsqThred> findtwoLastid = ssqCalService.selectIDbyExpHitlast(findLastid.get(0).getId(), expertname);
                    if (findtwoLastid.size() == 0) {
                        if (findLastid.get(0).getHitrecord() == 0) {
                            paircalcu = 0;
                        } else {
                            paircalcu = 1;
                        }

                    } else {
                        List<SsqThred> findthreeLastid = ssqCalService.selectIDbyExpHitlast(findtwoLastid.get(0).getId(), expertname);
                        if (findthreeLastid.size() > 0) {
                            if (findLastid.get(0).getHitrecord() == 0) {
                                paircalcu = 0;
                            } else if (findLastid.get(0).getHitrecord() > 0 && findtwoLastid.get(0).getHitrecord() > 0 && findthreeLastid.get(0).getHitrecord() == 0) {
                                paircalcu = 2;
                            } else if (findLastid.get(0).getHitrecord() > 0 && findtwoLastid.get(0).getHitrecord() == 0) {
                                paircalcu = 1;

                            } else if (findLastid.get(0).getHitrecord() > 0 && findtwoLastid.get(0).getHitrecord() > 0 && findthreeLastid.get(0).getHitrecord() > 0) {
                                paircalcu = 3;
                            }
                        } else {
                            if (findLastid.get(0).getHitrecord() == 0) {
                                paircalcu = 0;
                            } else if (findLastid.get(0).getHitrecord() > 0 && findtwoLastid.get(0).getHitrecord() > 0) {
                                paircalcu = 2;
                            } else if (findLastid.get(0).getHitrecord() > 0 && findtwoLastid.get(0).getHitrecord() == 0) {
                                paircalcu = 1;

                            }
                        }
                    }


                    List<SsqThred> findbyexpert = ssqCalService.selectfromIDbyExp(expID, expertname);
                    Loop4:
                    for (int j = 0; j < findbyexpert.size(); j++) {
                        id = findbyexpert.get(j).getId();
                        if (paircalcu == 2) {

                            if (findbyexpert.get(j).getHitrecord() > 0) {

                                paircalcu = paircalcu + 1;
                                pairthreeC = pairthreeC + 1;
                                paironeP = Math.round(paironeC * 100 / (j + 1 + countBefore));
                                pairtwoP = Math.round(pairtwoC * 100 / (j + 1 + countBefore));
                                pairthreeP = Math.round(pairthreeC * 100 / (j + 1 + countBefore));

                                Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                rcount = rcount + Result;

                            } else {

                                paircalcu = 0;
                                paironeP = Math.round(paironeC * 100 / (j + 1 + countBefore));
                                pairtwoP = Math.round(pairtwoC * 100 / (j + 1 + countBefore));
                                pairthreeP = Math.round(pairthreeC * 100 / (j + 1 + countBefore));
                                Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                rcount = rcount + Result;
                            }
                        } else if (paircalcu == 1) {
                            if (findbyexpert.get(j).getHitrecord() > 0) {

                                pairtwoC = pairtwoC + 1;
                                paircalcu = paircalcu + 1;

                                pairtwoP = Math.round(pairtwoC * 100 / (j + 1 + countBefore));
                                pairthreeP = Math.round(pairthreeC * 100 / (j + 1 + countBefore));
                                paironeP = Math.round(paironeC * 100 / (j + 1 + countBefore));

                                Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                rcount = rcount + Result;

                            } else {
                                paircalcu = 0;
                                paironeP = Math.round(paironeC * 100 / (j + 1 + countBefore));
                                pairtwoP = Math.round(pairtwoC * 100 / (j + 1 + countBefore));
                                pairthreeP = Math.round(pairthreeC * 100 / (j + 1 + countBefore));
                                Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                rcount = rcount + Result;
                            }

                        } else if (paircalcu == 0) {
                            if (findbyexpert.get(j).getHitrecord() > 0) {

                                paironeC = paironeC + 1;
                                paircalcu = paircalcu + 1;

                                pairtwoP = Math.round(pairtwoC * 100 / (j + 1 + countBefore));
                                pairthreeP = Math.round(pairthreeC * 100 / (j + 1 + countBefore));
                                paironeP = Math.round(paironeC * 100 / (j + 1 + countBefore));

                                Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                rcount = rcount + Result;

                            } else {
                                paircalcu = 0;
                                paironeP = Math.round(paironeC * 100 / (j + 1 + countBefore));
                                pairtwoP = Math.round(pairtwoC * 100 / (j + 1 + countBefore));
                                pairthreeP = Math.round(pairthreeC * 100 / (j + 1 + countBefore));
                                Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                rcount = rcount + Result;
                            }

                        } else if (paircalcu > 2) {
                            if (findbyexpert.get(j).getHitrecord() > 0) {

                                paircalcu = paircalcu + 1;

                                pairtwoP = Math.round(pairtwoC * 100 / (j + 1 + countBefore));
                                pairthreeP = Math.round(pairthreeC * 100 / (j + 1 + countBefore));
                                paironeP = Math.round(paironeC * 100 / (j + 1 + countBefore));

                                Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                rcount = rcount + Result;

                            } else {
                                paircalcu = 0;
                                paironeP = Math.round(paironeC * 100 / (j + 1 + countBefore));
                                pairtwoP = Math.round(pairtwoC * 100 / (j + 1 + countBefore));
                                pairthreeP = Math.round(pairthreeC * 100 / (j + 1 + countBefore));
                                Result = ssqCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                                rcount = rcount + Result;
                            }


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
        SsqCalService ssqCalCalService = (SsqCalService) context.getBean("SsqCalService");

        /*int rcount = 0;
        List<String> findexpert = ssqCalCalService.selectByexpert();

        Loop1:
        for (int i = 0; i < findexpert.size(); i++) {
            String expertname = findexpert.get(i).toString();
            List<SsqThred> findbyexpert = ssqCalCalService.selectGroupbyExp(expertname);


            float paironeP = 0;
            float pairtwoP = 0;
            float pairthreeP = 0;
            int paironeC = 0;
            int pairtwoC = 0;
            int pairthreeC = 0;
            int paircalcu = 0;


            int id = 0;

            int Result = 0;
            Loop2:
            for (int j = 0; j < findbyexpert.size(); j++) {
                id = findbyexpert.get(j).getId();
                if (j == 0) {
                    if (findbyexpert.get(j).getHitrecord() > 0) {
                        paironeC = 1;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 1;


                        Result = ssqCalCalService.updateSSQpair(paironeC * 100, pairtwoC * 100, pairthreeC * 100, paironeC, pairtwoC, pairthreeC, id);
                        rcount = rcount + Result;

                    } else {
                        paircalcu = 0;

                        Result = ssqCalCalService.updateSSQpair(paironeC * 100, pairtwoC * 100, pairthreeC * 100, paironeC, pairtwoC, pairthreeC, id);
                        rcount = rcount + Result;
                    }
                }

                if (j == 1) {
                    if (paircalcu > 0) {
                        if (findbyexpert.get(j).getHitrecord() > 0) {
                            pairtwoC = 1;
                            paircalcu = paircalcu + 1;

                            paironeP = Math.round(paironeC * 100 / 2);
                            pairtwoP = Math.round(pairtwoC * 100 / 2);
                            pairthreeP = Math.round(pairthreeC * 100 / 2);


                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                            rcount = rcount + Result;

                        } else {
                            paircalcu = 0;
                            paironeP = Math.round(paironeC * 100 / 2);
                            pairtwoP = Math.round(pairtwoC * 100 / 2);
                            pairthreeP = Math.round(pairthreeC * 100 / 2);
                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                            rcount = rcount + Result;
                        }
                    } else {
                        if (findbyexpert.get(j).getHitrecord() > 0) {
                            paironeC = 1;
                            pairtwoC = 0;

                            paircalcu = paircalcu + 1;
                            paironeP = Math.round(paironeC * 100 / 2);
                            pairtwoP = Math.round(pairtwoC * 100 / 2);
                            pairthreeP = Math.round(pairthreeC * 100 / 2);

                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                            rcount = rcount + Result;

                        } else {

                            paircalcu = 0;
                            pairtwoP = Math.round(pairtwoC * 100 / 2);
                            paironeP = Math.round(paironeC * 100 / 2);
                            pairthreeP = Math.round(pairthreeC * 100 / 2);

                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                            rcount = rcount + Result;
                        }

                    }
                }


                if (j > 1) {
                    if (paircalcu == 2) {

                        if (findbyexpert.get(j).getHitrecord() > 0) {

                            paircalcu = paircalcu + 1;
                            pairthreeC = pairthreeC + 1;
                            paironeP = Math.round(paironeC * 100 / (j + 1));
                            pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                            pairthreeP = Math.round(pairthreeC * 100 / (j + 1));

                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                            rcount = rcount + Result;

                        } else {

                            paircalcu = 0;
                            paironeP = Math.round(paironeC * 100 / (j + 1));
                            pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                            pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                            rcount = rcount + Result;
                        }
                    } else if (paircalcu == 1) {
                        if (findbyexpert.get(j).getHitrecord() > 0) {

                            pairtwoC = pairtwoC + 1;
                            paircalcu = paircalcu + 1;

                            pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                            pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                            paironeP = Math.round(paironeC * 100 / (j + 1));

                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                            rcount = rcount + Result;

                        } else {
                            paircalcu = 0;
                            paironeP = Math.round(paironeC * 100 / (j + 1));
                            pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                            pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                            rcount = rcount + Result;
                        }

                    } else if (paircalcu == 0) {
                        if (findbyexpert.get(j).getHitrecord() > 0) {

                            paironeC = paironeC + 1;
                            paircalcu = paircalcu + 1;

                            pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                            pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                            paironeP = Math.round(paironeC * 100 / (j + 1));

                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC,id);
                            rcount = rcount + Result;

                        } else {
                            paircalcu = 0;
                            paironeP = Math.round(paironeC * 100 / (j + 1));
                            pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                            pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                            rcount = rcount + Result;
                        }

                    } else if (paircalcu > 2) {
                        if (findbyexpert.get(j).getHitrecord() > 0) {

                            paircalcu = paircalcu + 1;
                            paironeC = paironeC + 1;
                            pairtwoC = pairtwoC + 1;
                            pairthreeC = pairthreeC + 1;

                            pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                            pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                            paironeP = Math.round(paironeC * 100 / (j + 1));

                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                            rcount = rcount + Result;

                        } else {
                            paircalcu = 0;
                            paironeP = Math.round(paironeC * 100 / (j + 1));
                            pairtwoP = Math.round(pairtwoC * 100 / (j + 1));
                            pairthreeP = Math.round(pairthreeC * 100 / (j + 1));
                            Result = ssqCalCalService.updateSSQpair(paironeP, pairtwoP, pairthreeP, paironeC, pairtwoC, pairthreeC, id);
                            rcount = rcount + Result;
                        }


                    }
                }


            }


        }*/
    }
}
