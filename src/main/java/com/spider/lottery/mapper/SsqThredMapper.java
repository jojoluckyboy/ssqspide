package com.spider.lottery.mapper;

import com.spider.lottery.pojo.SsqThred;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SsqThredMapper {


    int insertSSQThred(SsqThred record);

    int updateSSQThredMatch(@Param("time1") String time1, @Param("time2")String time2);

    String selectRedthreeById(@Param("startId") int startId);
    String selectRedsixById(@Param("startId") int startId);

    int updateSSQhit(@Param("hitResult") int hitResult,@Param("hitScore") int hitScore, @Param("startId") int startId);
    int updateSSQerror(@Param("lasterr") int lasterr,@Param("maxerr") int maxerr, @Param("startId") int startId);
    int updateSSQpair(@Param("paironeP") float paironeP,@Param("pairtwoP") float pairtwoP,@Param("pairthreeP") float pairthreeP,@Param("paironeC") int paironeC,@Param("pairtwoC") int pairtwoC,@Param("pairthreeC") int pairthreeC, @Param("startId") int startId);
    List<String> selectByexpert();

    List<SsqThred> selectGroupbyExp(@Param("expertname") String expertname);

    List<SsqThred> selectGroupbyExpDesc(@Param("expertname") String expertname);

    List<SsqThred> selectSSQThrDbLast();

    int selectIdByEdesc(@Param("hiserrprd") int hiserrprd);
    List<SsqThred> selectIdByE(@Param("hiserrprd") int hiserrprd);
    List<String> selectByexpertID(@Param("startid") int startid,@Param("endid") int endid);
    List<SsqThred> selectIDbyExpHit(@Param("expertname") String expertname,@Param("hiserrprd") int hiserrprd);
    List<SsqThred> selectIDbyExpHitlast(@Param("expid")  int expid,@Param("expertname") String expertname);
    List<SsqThred> selectIDbyExpANDissue(@Param("issuedate")  int issuedate,@Param("expertname") String expertname);
    List<SsqThred> selectIDbyissueHitlast(@Param("issuedate")  int issuedate,@Param("expertname") String expertname);

    List<SsqThred> selectRcByhismaxpd(@Param("hiserrprd") int hiserrprd);

    int selectIdByPdesc(@Param("perrorscore") int perrorscore);
    List<SsqThred> selectIdByP(@Param("perrorscore") int perrorscore);
    List<SsqThred> selectIDbyExpPes(@Param("expertname") String expertname,@Param("perrorscore") int perrorscore);
    List<SsqThred> selectIDbyExpPesAsc(@Param("expertname") String expertname,@Param("perrorscore") int perrorscore);
    List<SsqThred> selectfromIDbyExp(@Param("expid")  int expid,@Param("expertname") String expertname);
    List<SsqThred> selectIDbyExplimit(@Param("expid")  int expid,@Param("expertname") String expertname);
    int selectCountbyExplast(@Param("expid")  int expid,@Param("expertname") String expertname);

    int updateSSQpairRecent5(@Param("pone5P") double pone5P,@Param("ptwo5P") double ptwo5P, @Param("pthree5P") double pthree5P,@Param("startId") int startId);
    int updateSSQpairRecent10(@Param("pone10P") double pone10P,@Param("ptwo10P") double ptwo10P, @Param("pthree10P") double pthree10P,@Param("startId") int startId);
    int updateSSQpairRecent20(@Param("pone20P") double pone20P,@Param("ptwo20P") double ptwo20P, @Param("pthree20P") double pthree20P,@Param("startId") int startId);
    int updateSSQpairRecent30(@Param("pone30P") double pone30P,@Param("ptwo30P") double ptwo30P, @Param("pthree30P") double pthree30P,@Param("startId") int startId);
    int updateSSQpairRecent50(@Param("pone50P") double pone50P,@Param("ptwo50P") double ptwo50P, @Param("pthree50P") double pthree50P,@Param("startId") int startId);
    int updateSSQpairRecent100(@Param("pone100P") double pone100P,@Param("ptwo100P") double ptwo100P, @Param("pthree100P") double pthree100P,@Param("startId") int startId);
    int updateDisPerS(@Param("errorScore") int errorScore,@Param("performscore") int performscore,@Param("distinct") int distinct, @Param("startId") int startId);
    List<SsqThred> selectMLModelbyId(@Param("expid")  int expid);
    int updateSSQMLModelbyId(@Param("lasthit") int lasthit, @Param("lastscore") int lastscore, @Param("formax") int formax, @Param("oddeven") int oddeven
            , @Param("smallbig") int smallbig, @Param("primecom") int primecom, @Param("p51") int p51, @Param("p52") int p52
            , @Param("p53") int p53, @Param("p101") int p101, @Param("p102") int p102, @Param("p103") int p103
            , @Param("p201") int p201, @Param("p202") int p202, @Param("p203") int p203, @Param("p301") int p301
            , @Param("p302") int p302, @Param("p303") int p303, @Param("p501") int p501, @Param("p502") int p502
            , @Param("p503") int p503, @Param("p1001") int p1001, @Param("p1002") int p1002, @Param("p1003") int p1003,
                             @Param("nowhit") int nowhit, @Param("nowscore") int nowscore, @Param("threesum") int threesum,
                             @Param("div30") int div30, @Param("div31") int div31, @Param("div32") int div32,
                             @Param("divergence") int divergence, @Param("skewness") int skewness, @Param("acV") int acV,
                             @Param("expid") int expid);

    List<SsqThred> selectMLbyExpPesAsc(@Param("expertname") String expertname,@Param("perrorscore") int perrorscore);
}