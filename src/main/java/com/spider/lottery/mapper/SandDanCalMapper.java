package com.spider.lottery.mapper;

import com.spider.lottery.pojo.SandDankill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SandDanCalMapper {

    int selectIdByDVasc(@Param("singDanlasthit") int singDanlasthit);

    int selectIdByDVdesc(@Param("singDanlasthit") int singDanlasthit);

    List<SandDankill> selectByID(int id);

    int updateSanDhit(@Param("singDanhit") int singDanhit,@Param("fiveDanhit") int fiveDanhit,
                      @Param("sumDanhit") int sumDanhit, @Param("kuaDanhit") int kuaDanhit, @Param("id") int id);
    List<String> selectexpertByID(@Param("startid") int startid, @Param("endid") int endid);

    List<SandDankill> selectIDbyExpHit(@Param("expertname") String expertname, @Param("singDanlasthit") int singDanlasthit,
                                       @Param("endid") int endid);

    List<SandDankill> selectIDbyExpHitlast( @Param("expid") int expid, @Param("expertname") String expertname);

    int updateSanDconhit(@Param("singDanlasthit") int singDanlasthit,@Param("singDanconhit") int singDanconhit,
                         @Param("fiveDanlasthit") int fiveDanlasthit,@Param("fiveDanconhit") int fiveDanconhit,
                         @Param("sumDanlasthit") int sumDanlasthit,@Param("sumDanconhit") int sumDanconhit,
                         @Param("kuaDanlasthit") int kuaDanlasthit,@Param("kuaDanconhit") int kuaDanconhit, @Param("id") int id);

    List<SandDankill> selecthitbyExpIssue( @Param("issue") String issue, @Param("expertname") String expertname);

    List<SandDankill> selectIDbyUpdateHit( @Param("fid") int fid, @Param("expertname") String expertname);

    int selectIdByRockasc(@Param("spicerock") int spicerock);

    int selectIdByRockdesc(@Param("spicerock") int spicerock);

    int selectIdBySkewnasc(@Param("fiveDanskewn") int fiveDanskewn);

    int selectIdBySkewndesc(@Param("fiveDanskewn") int fiveDanskewn);

    int selectIdBySinDEMA3asc(@Param("singDanEMA3") Double singDanEMA3);

    int selectIdBySinDEMA3desc(@Param("singDanEMA3") Double singDanEMA3);

    int selectIdBySinDFortEMA3asc(@Param("singDFortEMA3") Double singDFortEMA3);

    int selectIdBySinDFortEMA3desc(@Param("singDFortEMA3") Double singDFortEMA3);

    List<SandDankill> selectIDbyExpDIV(@Param("expertname") String expertname, @Param("spicerock") int spicerock,
                                       @Param("endid") int endid);

    List<SandDankill> selectIDbyExpHDIVlast( @Param("expid") int expid, @Param("expertname") String expertname);

    int updateSanDACV(@Param("fiveDandiv") int fiveDandiv,@Param("fiveDanskewn") int fiveDanskewn,
                         @Param("fiveDanacV") int fiveDanacV,@Param("sumDandiv") int sumDandiv,
                         @Param("sumDanskewn") int sumDanskewn,@Param("sumDanacV") int sumDanacV,
                         @Param("kuaDandiv") int kuaDandiv,@Param("kuaDanskewn") int kuaDanskewn,
                         @Param("kuaDanacV") int kuaDanacV, @Param("id") int id);


    int updateSanDRock(@Param("spicerock") int spicerock,@Param("singDankd") int singDankd, @Param("id") int id);
    List<SandDankill> selectIDbyUpdateACV( @Param("fid") int fid, @Param("expertname") String expertname);

    List<SandDankill> selectIDbyEMA3(@Param("expertname") String expertname, @Param("singDanEMA3") double singDanEMA3,
                                       @Param("endid") int endid);

    List<SandDankill> selectIDbyEMA3last7(@Param("expertname") String expertname, @Param("id") int id);
    List<SandDankill> selectIDbyEMA3last3(@Param("expertname") String expertname, @Param("id") int id);
    List<SandDankill> selectIDbyDEAlast5(@Param("expertname") String expertname, @Param("id") int id);

    int updateSanDEMA(@Param("singDanEMA3") double singDanEMA3,@Param("singDanEMA7") double singDanEMA7,@Param("singDanDIF") double singDanDIF,
                      @Param("singDanDEA") double singDanDEA,@Param("singDanMACD") double singDanMACD,@Param("singDFortEMA3") double singDFortEMA3,
                      @Param("singDFortEMA7") double singDFortEMA7,@Param("singDFortDIF") double singDFortDIF,@Param("singDFortDEA") double singDFortDEA,
                      @Param("singDFortMACD") double singDFortMACD,@Param("singDFortstat") int singDFortstat,@Param("singDanlastMACD") double singDanlastMACD,
                      @Param("singDFortlastMACD") double singDFortlastMACD,@Param("fiveDFortEMA3") double fiveDFortEMA3,@Param("fiveDFortEMA7") double fiveDFortEMA7,
                      @Param("fiveDanDIF") double fiveDanDIF,@Param("fiveDanDEA") double fiveDanDEA,@Param("fiveDFortMACD") double fiveDFortMACD,
                      @Param("fiveDFortstat") int fiveDFortstat,@Param("fiveDFortlastMACD") double fiveDFortlastMACD,
                      @Param("sumDFortEMA3") double sumDFortEMA3,@Param("sumDFortEMA7") double sumDFortEMA7,
                      @Param("sumDanDIF") double sumDanDIF,@Param("sumDanDEA") double sumDanDEA,@Param("sumDFortMACD") double sumDFortMACD,
                      @Param("sumDFortstat") int sumDFortstat,@Param("sumDFortlastMACD") double sumDFortlastMACD,
                      @Param("kuaDFortEMA3") double kuaDFortEMA3,@Param("kuaDFortEMA7") double kuaDFortEMA7,
                      @Param("kuaDanDIF") double kuaDanDIF,@Param("kuaDanDEA") double kuaDanDEA,@Param("kuaDFortMACD") double kuaDFortMACD,
                      @Param("kuaDFortstat") int kuaDFortstat,@Param("kuaDFortlastMACD") double kuaDFortlastMACD,
                      @Param("id") int id);

    List<SandDankill> selectxinbyExpIssue( @Param("issue") String issue, @Param("expertname") String expertname);

}