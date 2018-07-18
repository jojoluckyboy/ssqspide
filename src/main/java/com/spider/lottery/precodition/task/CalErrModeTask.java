package com.spider.lottery.precodition.task;

import com.spider.lottery.pojo.SsqThred;
import com.spider.lottery.ssqservice.SsqCalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：计算错误模型
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/12
 * Time：9:55
 * Copyright© 2003-2016 Zhejiang huixin technology company
 *
 */

@Service
public class CalErrModeTask {

    @Autowired
    private SsqCalService ssqCalService ;

    private int rcount = 0;


    /**
     * 前置准备导入历史数据
     *
     * @return 0 or 1
     * @throws Exception
     *
     */
    public void pushIcrementData() throws Exception {

        List<SsqThred> idstring = ssqCalService.selectIdByE(1000);

        if (idstring.size() != 0) {

            int startid = idstring.get(0).getId();

            //int startid = ssqCalService.selectIdByE(1000);
            int endid = ssqCalService.selectIdByEdesc(1000);

            List<String> findexpert = ssqCalService.selectByexpertID(startid, endid);


            Loop1:
            for (int i = 0; i < findexpert.size(); i++) {
                String expertname = findexpert.get(i).toString();
                List<SsqThred> findid = ssqCalService.selectIDbyExpHit(expertname, 1000);
                if (findid.size() == 0) {
                    continue;
                }
                int expID = findid.get(0).getId();
                int hitrec = findid.get(0).getHitrecord();
                int hitscor = findid.get(0).getHitscore();
                //findid是所有标注1000的该专家记录，lastIssuedate是最后的一期期号，多条
                int lastIssuedate = Integer.parseInt(findid.get(findid.size() - 1).getIssue());
                //findLastid是符合标准的最小当期的上一期记录，1条
                List<SsqThred> findLastid = ssqCalService.selectIDbyExpHitlast(expID, expertname);
                if (findLastid.size() == 0) {
                    int issuedate = Integer.parseInt(findid.get(0).getIssue());
                    int hitrecL0 = findid.get(0).getHitrecord();
                    int id0 = findid.get(0).getId();


                    int lasterrreocrd0 = 0;
                    int lasterr0 = 0;

                    int nowerr = 0;
                    int maxerr0 = findid.get(0).getHistorymaxerrorpd();
                    int id = 0;

                    int Result = 0;

                    Loop3:
                    for (int j = issuedate; j < lastIssuedate + 1; j++) {

                        if (j == issuedate) {
                            if (hitrecL0 == 0) {
                                lasterr0 = 1;
                                maxerr0 = 1;
                                Result = ssqCalService.updateSSQerror(lasterr0, maxerr0, id0);
                                rcount = rcount + Result;

                            } else {

                                lasterr0 = 0;
                                maxerr0 = 0;
                                Result = ssqCalService.updateSSQerror(lasterr0, maxerr0, id0);
                                rcount = rcount + Result;
                            }

                        } else {

                            List<SsqThred> reclast0 = ssqCalService.selectIDbyissueHitlast(j, expertname);
                            lasterrreocrd0 = reclast0.get(0).getLastconterrorpd();
                            hitrecL0 = reclast0.get(0).getHitrecord();

                            List<SsqThred> recnow = ssqCalService.selectIDbyExpANDissue(j, expertname);
                            if (recnow.size() > 0) {
                                id = recnow.get(0).getId();

                                if (recnow.get(0).getHitrecord() == 0 && lasterrreocrd0 > 0 && hitrecL0 > 0) {
                                    lasterr0 = 0;
                                    if (lasterr0 > lasterrreocrd0) {

                                        if (maxerr0 < lasterr0) {
                                            maxerr0 = lasterr0;
                                        }
                                    } else {
                                        if (maxerr0 < lasterrreocrd0) {
                                            maxerr0 = lasterrreocrd0;
                                        }
                                    }

                                    Result = ssqCalService.updateSSQerror(lasterr0, maxerr0, id);
                                    rcount = rcount + Result;

                                } else if (recnow.get(0).getHitrecord() == 0 && lasterrreocrd0 > 0 && hitrecL0 == 0) {

                                    lasterr0 = lasterrreocrd0 + 1;
                                    if (lasterr0 > lasterrreocrd0) {

                                        if (maxerr0 < lasterr0) {
                                            maxerr0 = lasterr0;
                                        }
                                    } else {
                                        if (maxerr0 < lasterrreocrd0) {
                                            maxerr0 = lasterrreocrd0;
                                        }
                                    }
                                    Result = ssqCalService.updateSSQerror(lasterr0, maxerr0, id);
                                    rcount = rcount + Result;
                                } else if (recnow.get(0).getHitrecord() == 0 && lasterrreocrd0 == 0 && hitrecL0 == 0) {

                                    lasterr0 = 1;
                                    if (lasterr0 > lasterrreocrd0) {

                                        if (maxerr0 < lasterr0) {
                                            maxerr0 = lasterr0;
                                        }
                                    } else {
                                        if (maxerr0 < lasterrreocrd0) {
                                            maxerr0 = lasterrreocrd0;
                                        }
                                    }
                                    Result = ssqCalService.updateSSQerror(lasterr0, maxerr0, id);
                                    rcount = rcount + Result;
                                } else if (recnow.get(0).getHitrecord() == 0 && lasterrreocrd0 == 0 && hitrecL0 > 0) {

                                    lasterr0 = 0;
                                    if (lasterr0 > lasterrreocrd0) {

                                        if (maxerr0 < lasterr0) {
                                            maxerr0 = lasterr0;
                                        }
                                    } else {
                                        if (maxerr0 < lasterrreocrd0) {
                                            maxerr0 = lasterrreocrd0;
                                        }
                                    }
                                    Result = ssqCalService.updateSSQerror(lasterr0, maxerr0, id);
                                    rcount = rcount + Result;
                                } else if (recnow.get(0).getHitrecord() > 0 && lasterrreocrd0 == 0 && hitrecL0 == 0) {

                                    lasterr0 = 1;

                                    if (lasterr0 > lasterrreocrd0) {

                                        if (maxerr0 < lasterr0) {
                                            maxerr0 = lasterr0;
                                        }
                                    } else {
                                        if (maxerr0 < lasterrreocrd0) {
                                            maxerr0 = lasterrreocrd0;
                                        }
                                    }
                                    Result = ssqCalService.updateSSQerror(lasterr0, maxerr0, id);
                                    rcount = rcount + Result;
                                } else if (recnow.get(0).getHitrecord() > 0 && lasterrreocrd0 > 0 && hitrecL0 > 0) {

                                    lasterr0 = 0;

                                    if (lasterr0 > lasterrreocrd0) {

                                        if (maxerr0 < lasterr0) {
                                            maxerr0 = lasterr0;
                                        }
                                    } else {
                                        if (maxerr0 < lasterrreocrd0) {
                                            maxerr0 = lasterrreocrd0;
                                        }
                                    }
                                    Result = ssqCalService.updateSSQerror(lasterr0, maxerr0, id);
                                    rcount = rcount + Result;
                                } else if (recnow.get(0).getHitrecord() > 0 && lasterrreocrd0 > 0 && hitrecL0 == 0) {

                                    lasterr0 = lasterrreocrd0 + 1;

                                    if (lasterr0 > lasterrreocrd0) {

                                        if (maxerr0 < lasterr0) {
                                            maxerr0 = lasterr0;
                                        }
                                    } else {
                                        if (maxerr0 < lasterrreocrd0) {
                                            maxerr0 = lasterrreocrd0;
                                        }
                                    }
                                    Result = ssqCalService.updateSSQerror(lasterr0, maxerr0, id);
                                    rcount = rcount + Result;
                                } else if (recnow.get(0).getHitrecord() > 0 && lasterrreocrd0 == 0 && hitrecL0 > 0) {

                                    lasterr0 = 0;

                                    if (lasterr0 > lasterrreocrd0) {

                                        if (maxerr0 < lasterr0) {
                                            maxerr0 = lasterr0;
                                        }
                                    } else {
                                        if (maxerr0 < lasterrreocrd0) {
                                            maxerr0 = lasterrreocrd0;
                                        }
                                    }
                                    Result = ssqCalService.updateSSQerror(lasterr0, maxerr0, id);
                                    rcount = rcount + Result;
                                }
                            }


                        }
                    }

                } else {
                    int expIDL = findLastid.get(0).getId();
                    int hitrecL = findLastid.get(0).getHitrecord();
                    int hitscorL = findLastid.get(0).getHitscore();
                    int issuedate = Integer.parseInt(findLastid.get(0).getIssue());


                    int lasterrreocrd = 0;
                    int lasterr = 0;

                    int maxerr = findLastid.get(0).getHistorymaxerrorpd();
                    int id = 0;

                    int Result = 0;


                    Loop2:
                    for (int j = issuedate + 1; j < lastIssuedate + 1; j++) {

                        List<SsqThred> reclast = ssqCalService.selectIDbyissueHitlast(j, expertname);
                        lasterrreocrd = reclast.get(0).getLastconterrorpd();
                        hitrecL = reclast.get(0).getHitrecord();

                        List<SsqThred> recnow = ssqCalService.selectIDbyExpANDissue(j, expertname);
                        if (recnow.size() > 0) {
                            id = recnow.get(0).getId();

                            if (recnow.get(0).getHitrecord() == 0 && lasterrreocrd > 0 && hitrecL > 0) {
                                lasterr = 0;

                                if (lasterr > lasterrreocrd) {

                                    if (maxerr < lasterr) {
                                        maxerr = lasterr;
                                    }
                                } else {
                                    if (maxerr < lasterrreocrd) {
                                        maxerr = lasterrreocrd;
                                    }
                                }

                                Result = ssqCalService.updateSSQerror(lasterr, maxerr, id);
                                rcount = rcount + Result;

                            } else if (recnow.get(0).getHitrecord() == 0 && lasterrreocrd > 0 && hitrecL == 0) {

                                lasterr = lasterrreocrd + 1;
                                if (lasterr > lasterrreocrd) {

                                    if (maxerr < lasterr) {
                                        maxerr = lasterr;
                                    }
                                } else {
                                    if (maxerr < lasterrreocrd) {
                                        maxerr = lasterrreocrd;
                                    }
                                }

                                Result = ssqCalService.updateSSQerror(lasterr, maxerr, id);
                                rcount = rcount + Result;
                            } else if (recnow.get(0).getHitrecord() == 0 && lasterrreocrd == 0 && hitrecL == 0) {

                                lasterr = 1;
                                if (lasterr > lasterrreocrd) {

                                    if (maxerr < lasterr) {
                                        maxerr = lasterr;
                                    }
                                } else {
                                    if (maxerr < lasterrreocrd) {
                                        maxerr = lasterrreocrd;
                                    }
                                }

                                Result = ssqCalService.updateSSQerror(lasterr, maxerr, id);
                                rcount = rcount + Result;
                            } else if (recnow.get(0).getHitrecord() == 0 && lasterrreocrd == 0 && hitrecL > 0) {

                                lasterr = 0;
                                if (lasterr > lasterrreocrd) {

                                    if (maxerr < lasterr) {
                                        maxerr = lasterr;
                                    }
                                } else {
                                    if (maxerr < lasterrreocrd) {
                                        maxerr = lasterrreocrd;
                                    }
                                }

                                Result = ssqCalService.updateSSQerror(lasterr, maxerr, id);
                                rcount = rcount + Result;
                            } else if (recnow.get(0).getHitrecord() > 0 && lasterrreocrd == 0 && hitrecL == 0) {

                                lasterr = 1;

                                if (lasterr > lasterrreocrd) {

                                    if (maxerr < lasterr) {
                                        maxerr = lasterr;
                                    }
                                } else {
                                    if (maxerr < lasterrreocrd) {
                                        maxerr = lasterrreocrd;
                                    }
                                }

                                Result = ssqCalService.updateSSQerror(lasterr, maxerr, id);
                                rcount = rcount + Result;
                            } else if (recnow.get(0).getHitrecord() > 0 && lasterrreocrd > 0 && hitrecL == 0) {

                                lasterr = lasterrreocrd + 1;

                                if (lasterr > lasterrreocrd) {

                                    if (maxerr < lasterr) {
                                        maxerr = lasterr;
                                    }
                                } else {
                                    if (maxerr < lasterrreocrd) {
                                        maxerr = lasterrreocrd;
                                    }
                                }

                                Result = ssqCalService.updateSSQerror(lasterr, maxerr, id);
                                rcount = rcount + Result;
                            } else if (recnow.get(0).getHitrecord() > 0 && lasterrreocrd == 0 && hitrecL > 0) {

                                lasterr = 0;

                                if (lasterr > lasterrreocrd) {

                                    if (maxerr < lasterr) {
                                        maxerr = lasterr;
                                    }
                                } else {
                                    if (maxerr < lasterrreocrd) {
                                        maxerr = lasterrreocrd;
                                    }
                                }

                                Result = ssqCalService.updateSSQerror(lasterr, maxerr, id);
                                rcount = rcount + Result;
                            } else if (recnow.get(0).getHitrecord() > 0 && lasterrreocrd > 0 && hitrecL > 0) {

                                lasterr = 0;

                                if (lasterr > lasterrreocrd) {

                                    if (maxerr < lasterr) {
                                        maxerr = lasterr;
                                    }
                                } else {
                                    if (maxerr < lasterrreocrd) {
                                        maxerr = lasterrreocrd;
                                    }
                                }


                                Result = ssqCalService.updateSSQerror(lasterr, maxerr, id);
                                rcount = rcount + Result;
                            }
                        }


                    }

                }
            }
            //  System.out.println("共计" + rcount + "条历史数据更新");
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

       Loop1:for ( int i=0;i<findexpert.size();i++)
        {
            String expertname = findexpert.get(i).toString();
            List<SsqThred> findbyexpert = ssqCalCalService.selectGroupbyExp(expertname);
          int lasterr = 0;
          int lasterrT = 0;
          int nowerr = 0;
          int maxerr = 0;
          int id = 0;

          int Result = 0;


        Loop2:for ( int j=0;j<findbyexpert.size();j++)
        {
            id=findbyexpert.get(j).getId();
            if(j==0)
            {
                if(findbyexpert.get(j).getHitrecord() ==0)
                {
                    nowerr = 1;
                    lasterr=1;
                    maxerr = 1;
                    lasterrT = 1;

                    Result = ssqCalCalService.updateSSQerror(nowerr, maxerr, id);
                    rcount = rcount+Result;

                    }else{

                    Result = ssqCalCalService.updateSSQerror(nowerr, maxerr, id);
                    rcount = rcount+Result;
                }
                }



            if(j>0)
            {
                if(findbyexpert.get(j).getHitrecord() ==0)
                {
                    lasterr = nowerr;
                    nowerr = lasterr +1;
                    if(maxerr<lasterr){
                        maxerr = lasterr;
                    }


                }else{
                    lasterr = nowerr;
                    nowerr = 0;
                    if(maxerr<lasterr){
                        maxerr = lasterr;
                    }
                }
                Result = ssqCalCalService.updateSSQerror(lasterr, maxerr, id);
                rcount = rcount+Result;
            }


            }

        System.out.println("插入结果："+Result);

        System.out.println("插入条数："+rcount);
    }
  }
}
