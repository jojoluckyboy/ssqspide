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
public class CalPercTrendTask {

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
    private int rcount5 = 0;
    private int rcount10 = 0;
    private int rcount20 = 0;
    private int rcount30 = 0;
    private int rcount50 = 0;
    private int rcount100 = 0;

    public void pushIcrementData() throws Exception {

        List<SsqThred> idstring = ssqCalService.selectIdByP(1000);

        if (idstring.size() != 0) {

            int startid = idstring.get(0).getId();
            int endid = ssqCalService.selectIdByPdesc(1000);

            List<String> findexpert = ssqCalService.selectByexpertID(startid, endid);

            Loop1:
            for (int i = 0; i < findexpert.size(); i++) {
                String expertname = findexpert.get(i).toString();
                //String expertname = "龙谷";

                List<SsqThred> findid = ssqCalService.selectIDbyExpPes(expertname, 1000);
                if (findid.size() == 0) {
                    continue;
                }
                int expID = findid.get(0).getId();
                //findLastid是符合标准的最小当期的上一期记录，1条
                int increlinebefore = ssqCalService.selectCountbyExplast(expID, expertname);


                List<SsqThred> findbyexpert = ssqCalService.selectGroupbyExp(expertname);


                int paironeC = 0;
                int pairtwoC = 0;
                int pairthreeC = 0;
                int paircalcu = 0;


                int id = 0;
                int linebegin = 0;
                Loop0:
                for (int l = findbyexpert.size() - 1; l >= 0; l--) {
                    if (findbyexpert.get(l).getId() == expID) {
                        linebegin = l;
                        break;

                    }
                }

                //前5期
                loop2:
                if (increlinebefore >= 5) {
                    for (int j = findbyexpert.size() - 1; j >= linebegin; j--) {

                        for (int k = j; k >= j - 4; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 4) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;


                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 4) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 4) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;


                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 4) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent5((Math.round(paironeC * 10000) / 5) * 0.01d, (Math.round(pairtwoC * 10000) / 5) * 0.01d, (Math.round(pairthreeC * 10000) / 5) * 0.01d, id);
                        rcount = rcount + Result;
                        rcount5 = rcount5 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount5 + "条历史数据更新最近5期连对概率");
                }else{

                    for (int j = findbyexpert.size() - 1; j >= 4; j--) {

                        for (int k = j; k >= j - 4; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 4) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;


                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 4) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 4) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;


                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 4) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent5((Math.round(paironeC * 10000) / 5) * 0.01d, (Math.round(pairtwoC * 10000) / 5) * 0.01d, (Math.round(pairthreeC * 10000) / 5) * 0.01d, id);
                        rcount = rcount + Result;
                        rcount5 = rcount5 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount5 + "条历史数据更新最近5期连对概率");
                }

                //前10期
                loop3:
                if (increlinebefore >= 10) {
                    for (int j = findbyexpert.size() - 1; j >= linebegin; j--) {

                        for (int k = j; k >= j - 9; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 9) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;


                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 9) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 9) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 9) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent10((Math.round(paironeC * 10000) / 10) * 0.01d, (Math.round(pairtwoC * 10000) / 10) * 0.01d, (Math.round(pairthreeC * 10000) / 10) * 0.01d, id);
                        rcount = rcount + Result;
                        rcount10 = rcount10 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount10 + "条历史数据更新最近10期连对概率");

                }else{

                    for (int j = findbyexpert.size() - 1; j >= 9; j--) {

                        for (int k = j; k >= j - 9; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 9) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;


                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 9) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 9) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 9) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent10((Math.round(paironeC * 10000) / 10) * 0.01d, (Math.round(pairtwoC * 10000) / 10) * 0.01d, (Math.round(pairthreeC * 10000) / 10) * 0.01d, id);
                        rcount = rcount + Result;
                        rcount10 = rcount10 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount10 + "条历史数据更新最近10期连对概率");

                }


                //前20期
                loop4:
                if (increlinebefore >= 20) {
                    for (int j = findbyexpert.size() - 1; j >= linebegin; j--) {

                        for (int k = j; k >= j - 19; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 19) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 19) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 19) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 19) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent20((Math.round(paironeC * 10000) / 20) * 0.01d, (Math.round(pairtwoC * 10000) / 20) * 0.01d, (Math.round(pairthreeC * 10000) / 20) * 0.01d, id);
                        rcount = rcount + Result;
                        rcount20 = rcount20 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount20 + "条历史数据更新最近20期连对概率");

                }else{

                    for (int j = findbyexpert.size() - 1; j >= 19; j--) {

                        for (int k = j; k >= j - 19; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 19) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 19) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 19) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 19) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent20((Math.round(paironeC * 10000) / 20) * 0.01d, (Math.round(pairtwoC * 10000) / 20) * 0.01d, (Math.round(pairthreeC * 10000) / 20) * 0.01d, id);
                        rcount = rcount + Result;
                        rcount20 = rcount20 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount20 + "条历史数据更新最近20期连对概率");
                }
                //前30期
                loop5:
                if (increlinebefore >= 30) {
                    for (int j = findbyexpert.size() - 1; j >= linebegin; j--) {

                        for (int k = j; k >= j - 29; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 29) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 29) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 29) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 29) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent30(Double.valueOf(new java.text.DecimalFormat("#.00").format((Math.round(paironeC * 10000) / 30) * 0.01d)).doubleValue(), Double.valueOf(new java.text.DecimalFormat("#.00").format((Math.round(pairtwoC * 10000) / 30) * 0.01d)).doubleValue(), Double.valueOf(new java.text.DecimalFormat("#.00").format((Math.round(pairthreeC * 10000) / 30) * 0.01d)).doubleValue(), id);
                        rcount = rcount + Result;
                        rcount30 = rcount30 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount30 + "条历史数据更新最近30期连对概率");

                }else{
                    for (int j = findbyexpert.size() - 1; j >= 29; j--) {

                        for (int k = j; k >= j - 29; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 29) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 29) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 29) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;


                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 29) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent30(Double.valueOf(new java.text.DecimalFormat("#.00").format((Math.round(paironeC * 10000) / 30) * 0.01d)).doubleValue(), Double.valueOf(new java.text.DecimalFormat("#.00").format((Math.round(pairtwoC * 10000) / 30) * 0.01d)).doubleValue(), Double.valueOf(new java.text.DecimalFormat("#.00").format((Math.round(pairthreeC * 10000) / 30) * 0.01d)).doubleValue(), id);
                        rcount = rcount + Result;
                        rcount30 = rcount30 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount30 + "条历史数据更新最近30期连对概率");

                }
                //前50期
                loop6:
                if (increlinebefore >= 50) {
                    for (int j = findbyexpert.size() - 1; j >= linebegin; j--) {

                        for (int k = j; k >=j - 49; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 49) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 49) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 49) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 49) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent50((Math.round(paironeC * 10000) / 50) * 0.01d, (Math.round(pairtwoC * 10000) / 50) * 0.01d, (Math.round(pairthreeC * 10000) / 50) * 0.01d, id);
                        rcount = rcount + Result;
                        rcount50 = rcount50 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount50 + "条历史数据更新最近50期连对概率");

                }else{
                    for (int j = findbyexpert.size() - 1; j >= 49; j--) {

                        for (int k = j; k >= j - 49; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 49) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 49) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 49) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 49) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent50((Math.round(paironeC * 10000) / 50) * 0.01d, (Math.round(pairtwoC * 10000) / 50) * 0.01d, (Math.round(pairthreeC * 10000) / 50) * 0.01d, id);
                        rcount = rcount + Result;
                        rcount50 = rcount50 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount50 + "条历史数据更新最近50期连对概率");
                }

                //前100期
                loop7:
                if (increlinebefore >= 100) {
                    for (int j = findbyexpert.size() - 1; j >= linebegin; j--) {

                        for (int k = j; k >= j - 99; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 99) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 99) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 99) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 99) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent100((Math.round(paironeC * 10000) / 100) * 0.01d, (Math.round(pairtwoC * 10000) / 100) * 0.01d, (Math.round(pairthreeC * 10000) / 100) * 0.01d, id);
                        rcount = rcount + Result;
                        rcount100 = rcount100 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount100 + "条历史数据更新最近100期连对概率");

                }else{
                    for (int j = findbyexpert.size() - 1; j >= 99; j--) {

                        for (int k = j; k >= j - 99; k--) {
                            if (k == j) {

                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {
                                    paironeC = 1;
                                    paircalcu = 1;
                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                    paircalcu = paircalcu + 1;
                                    paironeC = 1;
                                }

                            } else {
                                if (paircalcu == 0 && k > j - 99) {

                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        paironeC = paironeC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }

                                } else if (paircalcu == 0 && k <= j - 99) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paironeC = paironeC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }
                                } else if (paircalcu == 1 && k > j - 99) {
                                    if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0) {
                                        paircalcu = paircalcu + 1;
                                        pairtwoC = pairtwoC + 1;

                                    } else {
                                        paircalcu = 0;
                                    }
                                } else if (paircalcu == 1 && k <= j - 99) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairtwoC = pairtwoC + 1;
                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                } else if (paircalcu == 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        pairthreeC = pairthreeC + 1;
                                        paircalcu = paircalcu + 1;

                                    } else {
                                        paircalcu = 0;

                                    }

                                } else if (paircalcu > 2) {
                                    if (findbyexpert.get(k).getHitrecord() > 0) {

                                        paircalcu = paircalcu + 1;


                                    } else {
                                        paircalcu = 0;

                                    }


                                }

                            }

                        }
                        id = findbyexpert.get(j).getId();
                        Result = ssqCalService.updateSSQpairRecent100((Math.round(paironeC * 10000) / 100) * 0.01d, (Math.round(pairtwoC * 10000) / 100) * 0.01d, (Math.round(pairthreeC * 10000) / 100) * 0.01d, id);
                        rcount = rcount + Result;
                        rcount100 = rcount100 + Result;
                        paironeC = 0;
                        pairtwoC = 0;
                        pairthreeC = 0;
                        paircalcu = 0;

                    }
                    System.out.println("新增共计" + rcount100 + "条历史数据更新最近100期连对概率");
                }
            }
        }
    }
    public int getIncreDcount(){
        return this.rcount;

    }
    public int getIncre5Dcount(){
        return this.rcount5;

    }
    public int getIncre10Dcount(){
        return this.rcount10;

    }
    public int getIncre20Dcount(){
        return this.rcount20;

    }
    public int getIncre30Dcount(){
        return this.rcount30;

    }
    public int getIncre50Dcount(){
        return this.rcount50;

    }
    public int getIncre100Dcount(){
        return this.rcount100;

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


            int rcount5 = 0;
            int rcount10 = 0;
            int rcount20 = 0;
            int rcount30 = 0;
            int rcount50 = 0;
            int rcount100 = 0;

            List<SsqThred> findbyexpert = ssqCalCalService.selectGroupbyExp(expertname);


            int paironeC = 0;
            int pairtwoC = 0;
            int pairthreeC = 0;
            int paircalcu = 0;

            int Result = 0;
            int id= 0;
      /*  //前5期
            loop2:
            if(findbyexpert.size()>=5){
                for(int j =  findbyexpert.size()-1; j>3; j--){

                    for(int k = j; k>j-5; k--){
                        if(k==j){

                            if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() == 0){
                                paironeC = 1;
                                paircalcu = 1;
                            }else if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() >0){
                                paircalcu = paircalcu + 1;
                            }

                        }else{
                            if (paircalcu == 0 && k>j-4) {

                                if (findbyexpert.get(k).getHitrecord() > 0  && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                    paironeC = paironeC + 1;
                                    paircalcu = paircalcu + 1;

                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0)
                                {
                                    paircalcu = paircalcu + 1;;

                                }
                                else {
                                    paircalcu = 0;
                                }

                            } else if (paircalcu == 0 && k<=j-4){
                                if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                    paironeC = paironeC + 1;
                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }
                            }
                            else if (paircalcu == 1 && k>j-4) {
                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                    pairtwoC = pairtwoC + 1;
                                    paircalcu = paircalcu + 1;


                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0){
                                    paircalcu = paircalcu + 1;;

                                }else {
                                    paircalcu = 0;
                                }
                            }else if (paircalcu == 1 && k<=j-4){
                                if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                    pairtwoC = pairtwoC + 1;
                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }



                            } else if (paircalcu == 2) {
                                if (findbyexpert.get(k).getHitrecord() > 0) {

                                    pairthreeC = pairthreeC + 1;
                                    paircalcu = paircalcu + 1;

                                } else {
                                    paircalcu = 0;

                                }

                            } else if (paircalcu > 2) {
                                if (findbyexpert.get(k).getHitrecord() > 0) {

                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }


                            }

                        }

                    }
                    id = findbyexpert.get(j).getId();
                    Result = ssqCalCalService.updateSSQpairRecent5((Math.round(paironeC * 10000)/5)*0.01d, (Math.round(pairtwoC * 10000)/5)*0.01d, (Math.round(pairthreeC * 10000)/5)*0.01d, id);
                    rcount = rcount + Result;
                    rcount5=rcount5+Result;
                    paironeC = 0;
                    pairtwoC = 0;
                    pairthreeC = 0;
                    paircalcu = 0;

                }
                System.out.println("新增共计"+rcount5+"条历史数据更新最近5期连对概率");
            }*/
       /* //前10期
        loop3:
        if(findbyexpert.size()>=10){
            for(int j =  findbyexpert.size()-1; j> 8; j--){

                for(int k = j; k>j-10; k--){
                    if(k==j){

                        if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() == 0){
                            paironeC = 1;
                            paircalcu = 1;
                        }else if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() >0){
                            paircalcu = paircalcu + 1;
                        }

                    }else{
                        if (paircalcu == 0 && k>j-9) {

                            if (findbyexpert.get(k).getHitrecord() > 0  && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                paironeC = paironeC + 1;
                                paircalcu = paircalcu + 1;

                            } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0)
                              {
                                paircalcu = paircalcu + 1;;

                            }
                            else {
                                paircalcu = 0;
                            }

                        } else if (paircalcu == 0 && k<=j-9){
                            if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                paironeC = paironeC + 1;
                                paircalcu = paircalcu + 1;


                            } else {
                                paircalcu = 0;

                            }
                        }
                        else if (paircalcu == 1 && k>j-9) {
                            if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                pairtwoC = pairtwoC + 1;
                                paircalcu = paircalcu + 1;


                            } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0){
                                paircalcu = paircalcu + 1;;

                            }else {
                                paircalcu = 0;
                            }
                        }else if (paircalcu == 1 && k<=j-9){
                            if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                pairtwoC = pairtwoC + 1;
                                paircalcu = paircalcu + 1;


                            } else {
                                paircalcu = 0;

                            }



                        } else if (paircalcu == 2) {
                            if (findbyexpert.get(k).getHitrecord() > 0) {

                                pairthreeC = pairthreeC + 1;
                                paircalcu = paircalcu + 1;

                            } else {
                                paircalcu = 0;

                            }

                        } else if (paircalcu > 2) {
                            if (findbyexpert.get(k).getHitrecord() > 0) {

                                paircalcu = paircalcu + 1;


                            } else {
                                paircalcu = 0;

                            }


                    }

                }

            }
                id = findbyexpert.get(j).getId();
                Result = ssqCalCalService.updateSSQpairRecent10((Math.round(paironeC * 10000)/10)*0.01d, (Math.round(pairtwoC * 10000)/10)*0.01d, (Math.round(pairthreeC * 10000)/10)*0.01d, id);
                rcount = rcount + Result;
                rcount10=rcount10+Result;
                 paironeC = 0;
                 pairtwoC = 0;
                 pairthreeC = 0;
                 paircalcu = 0;

           }
            System.out.println("新增共计"+rcount10+"条历史数据更新最近10期连对概率");

        }

            //前20期
            loop4:
            if(findbyexpert.size()>=20){
                for(int j =  findbyexpert.size()-1; j>18; j--){

                    for(int k = j; k>j-20; k--){
                        if(k==j){

                            if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() == 0){
                                paironeC = 1;
                                paircalcu = 1;
                            }else if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() >0){
                                paircalcu = paircalcu + 1;
                            }

                        }else{
                            if (paircalcu == 0 && k>j-19) {

                                if (findbyexpert.get(k).getHitrecord() > 0  && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                    paironeC = paironeC + 1;
                                    paircalcu = paircalcu + 1;

                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0)
                                {
                                    paircalcu = paircalcu + 1;;

                                }
                                else {
                                    paircalcu = 0;
                                }

                            } else if (paircalcu == 0 && k<=j-19){
                                if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                    paironeC = paironeC + 1;
                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }
                            }
                            else if (paircalcu == 1 && k>j-19) {
                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                    pairtwoC = pairtwoC + 1;
                                    paircalcu = paircalcu + 1;


                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0){
                                    paircalcu = paircalcu + 1;;

                                }else {
                                    paircalcu = 0;
                                }
                            }else if (paircalcu == 1 && k<=j-19){
                                if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                    pairtwoC = pairtwoC + 1;
                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }



                            } else if (paircalcu == 2) {
                                if (findbyexpert.get(k).getHitrecord() > 0) {

                                    pairthreeC = pairthreeC + 1;
                                    paircalcu = paircalcu + 1;

                                } else {
                                    paircalcu = 0;

                                }

                            } else if (paircalcu > 2) {
                                if (findbyexpert.get(k).getHitrecord() > 0) {

                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }


                            }

                        }

                    }
                    id = findbyexpert.get(j).getId();
                    Result = ssqCalCalService.updateSSQpairRecent20((Math.round(paironeC * 10000)/20)*0.01d, (Math.round(pairtwoC * 10000)/20)*0.01d, (Math.round(pairthreeC * 10000)/20)*0.01d, id);
                    rcount = rcount + Result;
                    rcount20=rcount20+Result;
                    paironeC = 0;
                    pairtwoC = 0;
                    pairthreeC = 0;
                    paircalcu = 0;

                }
                System.out.println("新增共计"+rcount20+"条历史数据更新最近20期连对概率");

            }
            //前30期
            loop5:
            if(findbyexpert.size()>=30){
                for(int j =  findbyexpert.size()-1; j>28; j--){

                    for(int k = j; k>j-30; k--){
                        if(k==j){

                            if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() == 0){
                                paironeC = 1;
                                paircalcu = 1;
                            }else if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() >0){
                                paircalcu = paircalcu + 1;
                            }

                        }else{
                            if (paircalcu == 0 && k>j-29) {

                                if (findbyexpert.get(k).getHitrecord() > 0  && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                    paironeC = paironeC + 1;
                                    paircalcu = paircalcu + 1;

                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0)
                                {
                                    paircalcu = paircalcu + 1;;

                                }
                                else {
                                    paircalcu = 0;
                                }

                            } else if (paircalcu == 0 && k<=j-29){
                                if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                    paironeC = paironeC + 1;
                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }
                            }
                            else if (paircalcu == 1 && k>j-29) {
                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                    pairtwoC = pairtwoC + 1;
                                    paircalcu = paircalcu + 1;


                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0){
                                    paircalcu = paircalcu + 1;;

                                }else {
                                    paircalcu = 0;
                                }
                            }else if (paircalcu == 1 && k<=j-29){
                                if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                    pairtwoC = pairtwoC + 1;
                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }



                            } else if (paircalcu == 2) {
                                if (findbyexpert.get(k).getHitrecord() > 0) {

                                    pairthreeC = pairthreeC + 1;
                                    paircalcu = paircalcu + 1;

                                } else {
                                    paircalcu = 0;

                                }

                            } else if (paircalcu > 2) {
                                if (findbyexpert.get(k).getHitrecord() > 0) {

                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }


                            }

                        }

                    }
                    id = findbyexpert.get(j).getId();
                    Result = ssqCalCalService.updateSSQpairRecent30(Double.valueOf(new java.text.DecimalFormat("#.00").format((Math.round(paironeC * 10000)/30)*0.01d)).doubleValue(), Double.valueOf(new java.text.DecimalFormat("#.00").format((Math.round(pairtwoC * 10000)/30)*0.01d)).doubleValue(), Double.valueOf(new java.text.DecimalFormat("#.00").format((Math.round(pairthreeC * 10000)/30)*0.01d)).doubleValue(), id);
                    rcount = rcount + Result;
                    rcount30=rcount30+Result;
                    paironeC = 0;
                    pairtwoC = 0;
                    pairthreeC = 0;
                    paircalcu = 0;

                }
                System.out.println("新增共计"+rcount30+"条历史数据更新最近30期连对概率");

            }
          /*  //前50期
            loop6:
            if(findbyexpert.size()>=50){
                for(int j =  findbyexpert.size()-1; j>48; j--){

                    for(int k = j; k>j-50; k--){
                        if(k==j){

                            if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() == 0){
                                paironeC = 1;
                                paircalcu = 1;
                            }else if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() >0){
                                paircalcu = paircalcu + 1;
                            }

                        }else{
                            if (paircalcu == 0 && k>j-49) {

                                if (findbyexpert.get(k).getHitrecord() > 0  && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                    paironeC = paironeC + 1;
                                    paircalcu = paircalcu + 1;

                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0)
                                {
                                    paircalcu = paircalcu + 1;;

                                }
                                else {
                                    paircalcu = 0;
                                }

                            } else if (paircalcu == 0 && k<=j-49){
                                if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                    paironeC = paironeC + 1;
                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }
                            }
                            else if (paircalcu == 1 && k>j-49) {
                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                    pairtwoC = pairtwoC + 1;
                                    paircalcu = paircalcu + 1;


                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0){
                                    paircalcu = paircalcu + 1;;

                                }else {
                                    paircalcu = 0;
                                }
                            }else if (paircalcu == 1 && k<=j-49){
                                if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                    pairtwoC = pairtwoC + 1;
                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }



                            } else if (paircalcu == 2) {
                                if (findbyexpert.get(k).getHitrecord() > 0) {

                                    pairthreeC = pairthreeC + 1;
                                    paircalcu = paircalcu + 1;

                                } else {
                                    paircalcu = 0;

                                }

                            } else if (paircalcu > 2) {
                                if (findbyexpert.get(k).getHitrecord() > 0) {

                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }


                            }

                        }

                    }
                    id = findbyexpert.get(j).getId();
                    Result = ssqCalCalService.updateSSQpairRecent50((Math.round(paironeC * 10000)/50)*0.01d, (Math.round(pairtwoC * 10000)/50)*0.01d, (Math.round(pairthreeC * 10000)/50)*0.01d, id);
                    rcount = rcount + Result;
                    rcount50=rcount50+Result;
                    paironeC = 0;
                    pairtwoC = 0;
                    pairthreeC = 0;
                    paircalcu = 0;

                }
                System.out.println("新增共计"+rcount50+"条历史数据更新最近50期连对概率");

            }

            //前100期
            loop7:
            if(findbyexpert.size()>=100){
                for(int j =  findbyexpert.size()-1; j>98; j--){

                    for(int k = j; k>j-100; k--){
                        if(k==j){

                            if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() == 0){
                                paironeC = 1;
                                paircalcu = 1;
                            }else if(findbyexpert.get(k).getHitrecord()>0 && findbyexpert.get(k - 1).getHitrecord() >0){
                                paircalcu = paircalcu + 1;
                            }

                        }else{
                            if (paircalcu == 0 && k>j-99) {

                                if (findbyexpert.get(k).getHitrecord() > 0  && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                    paironeC = paironeC + 1;
                                    paircalcu = paircalcu + 1;

                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0)
                                {
                                    paircalcu = paircalcu + 1;;

                                }
                                else {
                                    paircalcu = 0;
                                }

                            } else if (paircalcu == 0 && k<=j-99){
                                if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                    paironeC = paironeC + 1;
                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }
                            }
                            else if (paircalcu == 1 && k>j-99) {
                                if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() == 0) {

                                    pairtwoC = pairtwoC + 1;
                                    paircalcu = paircalcu + 1;


                                } else if (findbyexpert.get(k).getHitrecord() > 0 && findbyexpert.get(k - 1).getHitrecord() > 0){
                                    paircalcu = paircalcu + 1;;

                                }else {
                                    paircalcu = 0;
                                }
                            }else if (paircalcu == 1 && k<=j-99){
                                if (findbyexpert.get(k).getHitrecord() > 0 ) {

                                    pairtwoC = pairtwoC + 1;
                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }



                            } else if (paircalcu == 2) {
                                if (findbyexpert.get(k).getHitrecord() > 0) {

                                    pairthreeC = pairthreeC + 1;
                                    paircalcu = paircalcu + 1;

                                } else {
                                    paircalcu = 0;

                                }

                            } else if (paircalcu > 2) {
                                if (findbyexpert.get(k).getHitrecord() > 0) {

                                    paircalcu = paircalcu + 1;


                                } else {
                                    paircalcu = 0;

                                }


                            }

                        }

                    }
                    id = findbyexpert.get(j).getId();
                    Result = ssqCalCalService.updateSSQpairRecent100((Math.round(paironeC * 10000)/100)*0.01d, (Math.round(pairtwoC * 10000)/100)*0.01d, (Math.round(pairthreeC * 10000)/100)*0.01d, id);
                    rcount = rcount + Result;
                    rcount100=rcount100+Result;
                    paironeC = 0;
                    pairtwoC = 0;
                    pairthreeC = 0;
                    paircalcu = 0;

                }
                System.out.println("新增共计"+rcount100+"条历史数据更新最近100期连对概率");

            }*/
        }
    }
}
