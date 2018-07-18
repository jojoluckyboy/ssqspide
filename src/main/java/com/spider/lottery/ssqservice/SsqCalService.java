package com.spider.lottery.ssqservice;

import com.spider.lottery.mapper.SsqAnalysysMapper;
import com.spider.lottery.mapper.SsqRealTimeMapper;
import com.spider.lottery.mapper.SsqThredMapper;
import com.spider.lottery.pojo.SsqAnalyse;
import com.spider.lottery.pojo.SsqRealTime;
import com.spider.lottery.pojo.SsqThred;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Description：
 * 双色球专家数据模型计算服务
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/6
 * Time：14:56
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
public class SsqCalService {

    @Autowired
    private SsqThredMapper SsqThredMapper;

    @Autowired
    private SsqAnalysysMapper SsqAnalysysMapper;

    @Autowired
    private SsqRealTimeMapper ssqRealTimeMapper;
    /**
     * 返回id区间的查询字段
     *
     * @return 1
     * @throws Exception
     */
    public String selectRedthreeById(@Param("startId") int startId) {

        return SsqThredMapper.selectRedthreeById(startId);


    }

    public String selectRedsixById(@Param("startId") int startId) {

        return SsqThredMapper.selectRedsixById(startId);


    }

    public int selectIdByEdesc(@Param("hiserrprd") int hiserrprd) {

        return SsqThredMapper.selectIdByEdesc(hiserrprd);


    }

    public List<SsqThred> selectIdByE(@Param("hiserrprd") int hiserrprd) {

        return SsqThredMapper.selectIdByE(hiserrprd);


    }

    public int updateSSQhit(@Param("hitResult") int hitResult,@Param("hitScore") int hitScore, @Param("startId") int startId) {

        return SsqThredMapper.updateSSQhit(hitResult,hitScore,startId);


    }

    public int updateSSQerror(@Param("lasterr") int lasterr,@Param("maxerr") int maxerr, @Param("startId") int startId) {

        return SsqThredMapper.updateSSQerror(lasterr,maxerr,startId);


    }

    public int updateSSQpair(@Param("paironeP") float paironeP,@Param("pairtwoP") float pairtwoP,@Param("pairthreeP") float pairthreeP,@Param("paironeC") int paironeC,@Param("pairtwoC") int pairtwoC,@Param("pairthreeC") int pairthreeC, @Param("startId") int startId) {

        return SsqThredMapper.updateSSQpair(paironeP,pairtwoP,pairthreeP,paironeC,pairtwoC,pairthreeC,startId);


    }

    public int calHit(int startId) {

        int count = 0;
        List<String> redthr= Arrays.asList(this.selectRedthreeById(startId).split(" "));
        List<String> redsix= Arrays.asList(this.selectRedsixById(startId).split(" "));
        Loop1:for ( int i=0;i<redthr.size();i++)
        {
            boolean contains = redsix.contains(redthr.get(i));
            if(contains){
                count=count+1;
            }

        }

        return count;


    }

    public List<String> selectByexpert() {

        return SsqThredMapper.selectByexpert();


    }

    public List<SsqThred> selectSSQThrDbLast() {

        return SsqThredMapper.selectSSQThrDbLast();


    }

    public List<SsqRealTime> selectSSQRealTDbLast() {

        return ssqRealTimeMapper.selectSSQRealTDbLast();


    }

    public List<String> selectByexpertID(@Param("startid") int startid,@Param("endid") int endid) {

        return SsqThredMapper.selectByexpertID(startid,endid);


    }

    public List<SsqThred> selectIDbyExpHit(@Param("expertname") String expertname,@Param("hiserrprd") int hiserrprd) {

        return SsqThredMapper.selectIDbyExpHit(expertname,hiserrprd);


    }

    public List<SsqThred> selectGroupbyExp(@Param("expertname") String expertname) {

        return SsqThredMapper.selectGroupbyExp(expertname);


    }

    public List<SsqThred> selectGroupbyExpDesc(@Param("expertname") String expertname) {

        return SsqThredMapper.selectGroupbyExpDesc(expertname);


    }

    public List<SsqThred> selectIDbyExpHitlast(@Param("expid") int expid,@Param("expertname") String expertname) {

        return SsqThredMapper.selectIDbyExpHitlast(expid,expertname);


    }
    public List<SsqThred> selectIDbyExpANDissue(@Param("issuedate") int issuedate,@Param("expertname") String expertname) {

        return SsqThredMapper.selectIDbyExpANDissue(issuedate,expertname);


    }

    public List<SsqThred> selectIDbyissueHitlast(@Param("issuedate") int issuedate,@Param("expertname") String expertname) {

        return SsqThredMapper.selectIDbyissueHitlast(issuedate,expertname);


    }
    public List<SsqThred> selectRcByhismaxpd(@Param("hiserrprd") int hiserrprd) {

        return SsqThredMapper.selectRcByhismaxpd(hiserrprd);


    }

    public int selectIdByPdesc(@Param("perrorscore") int perrorscore) {

        return SsqThredMapper.selectIdByPdesc(perrorscore);


    }

    public List<SsqThred> selectIdByP(@Param("perrorscore") int perrorscore) {

        return SsqThredMapper.selectIdByP(perrorscore);


    }

    public List<SsqThred> selectIDbyExpPes(@Param("expertname") String expertname,@Param("perrorscore") int perrorscore) {

        return SsqThredMapper.selectIDbyExpPes(expertname,perrorscore);


    }

    public List<SsqThred> selectIDbyExpPesAsc(@Param("expertname") String expertname,@Param("perrorscore") int perrorscore) {

        return SsqThredMapper.selectIDbyExpPesAsc(expertname,perrorscore);


    }

    public List<SsqThred> selectfromIDbyExp(@Param("expid") int expid,@Param("expertname") String expertname) {

        return SsqThredMapper.selectfromIDbyExp(expid,expertname);


    }

    public List<SsqThred> selectIDbyExplimit(@Param("expid") int expid,@Param("expertname") String expertname) {

        return SsqThredMapper.selectIDbyExplimit(expid,expertname);


    }
    public int selectCountbyExplast(@Param("expid") int expid,@Param("expertname") String expertname) {

        return SsqThredMapper.selectCountbyExplast(expid,expertname);


    }

    public int updateSSQpairRecent5(@Param("pone5P") double pone5P,@Param("ptwo5P") double ptwo5P, @Param("pthree5P") double pthree5P,@Param("startId") int startId) {

        return SsqThredMapper.updateSSQpairRecent5(pone5P,ptwo5P,pthree5P,startId);


    }

    public int updateSSQpairRecent10(@Param("pone10P") double pone10P,@Param("ptwo10P") double ptwo10P, @Param("pthree10P") double pthree10P,@Param("startId") int startId) {

        return SsqThredMapper.updateSSQpairRecent10(pone10P,ptwo10P,pthree10P,startId);


    }

    public int updateSSQpairRecent20(@Param("pone20P") double pone20P,@Param("ptwo20P") double ptwo20P, @Param("pthree20P") double pthree20P,@Param("startId") int startId) {

        return SsqThredMapper.updateSSQpairRecent20(pone20P,ptwo20P,pthree20P,startId);


    }

    public int updateSSQpairRecent30(@Param("pone30P") double pone30P,@Param("ptwo30P") double ptwo30P, @Param("pthree30P") double pthree30P,@Param("startId") int startId) {

        return SsqThredMapper.updateSSQpairRecent30(pone30P,ptwo30P,pthree30P,startId);


    }

    public int updateSSQpairRecent50(@Param("pone50P") double pone50P,@Param("ptwo50P") double ptwo50P, @Param("pthree50P") double pthree50P,@Param("startId") int startId) {

        return SsqThredMapper.updateSSQpairRecent50(pone50P,ptwo50P,pthree50P,startId);


    }

    public int updateSSQpairRecent100(@Param("pone100P") double pone100P,@Param("ptwo100P") double ptwo100P, @Param("pthree100P") double pthree100P,@Param("startId") int startId) {

        return SsqThredMapper.updateSSQpairRecent100(pone100P,ptwo100P,pthree100P,startId);


    }

    public int updateSSQAnalysys(@Param("one") int one, @Param("two") int two, @Param("three") int three, @Param("four") int four
            , @Param("five") int five, @Param("six") int six, @Param("seven") int seven, @Param("eight") int eight
            , @Param("nine") int nine, @Param("ten") int ten, @Param("eleven") int eleven, @Param("twelve") int twelve
            , @Param("thirteen") int thirteen, @Param("fourteen") int fourteen, @Param("fifteen") int fifteen, @Param("sixteen") int sixteen
            , @Param("seventeen") int seventeen, @Param("eighteen") int eighteen, @Param("nineteen") int nineteen, @Param("twenty") int twenty
            , @Param("twentyone") int twentyone, @Param("twentytwo") int twentytwo, @Param("twentythree") int twentythree, @Param("twentyfour") int twentyfour
            , @Param("twentyfive") int twentyfive, @Param("twentysix") int twentysix, @Param("twentyseven") int twentyseven, @Param("twentyeight") int twentyeight
            , @Param("twentynine") int twentynine, @Param("thirty") int thirty,@Param("expertname") String expertname) {

        return SsqAnalysysMapper.updateSSQAnalysys(one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,thirteen,fourteen
        ,fifteen,sixteen,seventeen,eighteen,nineteen,twenty,twentyone,twentytwo,twentythree,twentyfour,twentyfive,twentysix
        ,twentyseven,twentyeight,twentynine,thirty,expertname);


    }


    public int insertSSQAnalysys(SsqAnalyse record) {

        return SsqAnalysysMapper.insertSSQAnalysys(record);


    }

    public int updateSSQAnalyPer(@Param("pert80") int pert80, @Param("pert90") int pert90, @Param("expertname") String expertname) {

        return SsqAnalysysMapper.updateSSQAnalyPer(pert80,pert90,expertname);


    }

    public List<SsqAnalyse> selectByexpert(@Param("expertname") String expertname) {

        return SsqAnalysysMapper.selectByexpert(expertname);


    }

    public int updateDisPerS(@Param("errorScore") int errorScore,@Param("performscore") int performscore,@Param("distinct") int distinct, @Param("startId") int startId) {

        return SsqThredMapper.updateDisPerS(errorScore,performscore,distinct,startId);


    }

    public List<SsqThred> selectMLModelbyId(@Param("expid") int expid) {

        return SsqThredMapper.selectMLModelbyId(expid);


    }

    public int updateSSQMLModelbyId(@Param("lasthit") int lasthit, @Param("lastscore") int lastscore, @Param("formax") int formax, @Param("oddeven") int oddeven
            , @Param("smallbig") int smallbig, @Param("primecom") int primecom, @Param("p51") int p51, @Param("p52") int p52
            , @Param("p53") int p53, @Param("p101") int p101, @Param("p102") int p102, @Param("p103") int p103
            , @Param("p201") int p201, @Param("p202") int p202, @Param("p203") int p203, @Param("p301") int p301
            , @Param("p302") int p302, @Param("p303") int p303, @Param("p501") int p501, @Param("p502") int p502
            , @Param("p503") int p503, @Param("p1001") int p1001, @Param("p1002") int p1002, @Param("p1003") int p1003,
             @Param("nowhit") int nowhit, @Param("nowscore") int nowscore, @Param("threesum") int threesum,
                                    @Param("div30") int div30, @Param("div31") int div31, @Param("div32") int div32,
                                    @Param("divergence") int divergence, @Param("skewness") int skewness, @Param("acV") int acV,
                                    @Param("expid") int expid) {

        return SsqThredMapper.updateSSQMLModelbyId(lasthit,lastscore,formax,oddeven,smallbig,primecom,p51,p52,p53,p101,p102,p103,p201,p202
                ,p203,p301,p302,p303,p501,p502,p503,p1001,p1002,p1003,nowhit,nowscore,threesum,div30,div31,div32,divergence,
                skewness,acV,expid);


    }

    public List<SsqThred> selectMLbyExpPesAsc(@Param("expertname") String expertname,@Param("perrorscore") int perrorscore) {

        return SsqThredMapper.selectMLbyExpPesAsc(expertname,perrorscore);


    }

}
