package com.spider.lottery.sanDservice;

import com.spider.lottery.mapper.SandDanCalMapper;
import com.spider.lottery.pojo.SandDankill;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SanDCalService {

    @Autowired
    private SandDanCalMapper sandDanCalMapper;


    /**
     * 返回id区间的查询字段
     *
     * @return 1
     * @throws Exception
     */
    public int selectIdByDVasc(@Param("singDanlasthit") int singDanlasthit) {

        return sandDanCalMapper.selectIdByDVasc(singDanlasthit);
    }

    public int selectIdByDVdesc(@Param("singDanlasthit") int singDanlasthit) {

        return sandDanCalMapper.selectIdByDVdesc(singDanlasthit);

    }

    public List<SandDankill> selectByID(@Param("id") int id) {

        return sandDanCalMapper.selectByID(id);

    }

    public int updateSanDhit(@Param("singDanhit") int singDanhit,@Param("fiveDanhit") int fiveDanhit,
                             @Param("sumDanhit") int sumDanhit, @Param("kuaDanhit") int kuaDanhit, @Param("id") int id) {


        return sandDanCalMapper.updateSanDhit(singDanhit,fiveDanhit,sumDanhit,kuaDanhit,id);

    }
    public List<String> selectexpertByID(@Param("startid") int startid, @Param("endid") int endid) {

        return sandDanCalMapper.selectexpertByID(startid, endid);

    }

    public List<SandDankill> selectIDbyExpHit(@Param("expertname") String expertname, @Param("singDanlasthit") int singDanlasthit,
                                              @Param("endid") int endid) {

        return sandDanCalMapper.selectIDbyExpHit(expertname, singDanlasthit, endid);

    }

    public List<SandDankill> selectIDbyExpHitlast(@Param("expid") int expid, @Param("expertname") String expertname) {

        return sandDanCalMapper.selectIDbyExpHitlast(expid, expertname);

    }

    public int updateSanDconhit(@Param("singDanlasthit") int singDanlasthit,@Param("singDanconhit") int singDanconhit,
                                @Param("fiveDanlasthit") int fiveDanlasthit,@Param("fiveDanconhit") int fiveDanconhit,
                                @Param("sumDanlasthit") int sumDanlasthit,@Param("sumDanconhit") int sumDanconhit,
                                @Param("kuaDanlasthit") int kuaDanlasthit,@Param("kuaDanconhit") int kuaDanconhit, @Param("id") int id) {


        return sandDanCalMapper.updateSanDconhit(singDanlasthit,singDanconhit,fiveDanlasthit,fiveDanconhit,sumDanlasthit,
                sumDanconhit, kuaDanlasthit, kuaDanconhit, id);

    }

    public List<SandDankill> selecthitbyExpIssue(@Param("issue") String issue, @Param("expertname") String expertname) {

        return sandDanCalMapper.selecthitbyExpIssue(issue, expertname);

    }

    public List<SandDankill> selectIDbyUpdateHit(@Param("fid") int fid, @Param("expertname") String expertname) {

        return sandDanCalMapper.selectIDbyUpdateHit(fid, expertname);

    }

    public int selectIdByRockasc(@Param("spicerock") int spicerock) {

        return sandDanCalMapper.selectIdByRockasc(spicerock);
    }

    public int selectIdByRockdesc(@Param("spicerock") int spicerock) {

        return sandDanCalMapper.selectIdByRockdesc(spicerock);

    }

    public int selectIdBySkewnasc(@Param("fiveDanskewn") int fiveDanskewn) {

        return sandDanCalMapper.selectIdBySkewnasc(fiveDanskewn);
    }

    public int selectIdBySkewndesc(@Param("fiveDanskewn") int fiveDanskewn) {

        return sandDanCalMapper.selectIdBySkewndesc(fiveDanskewn);

    }

    public int selectIdBySinDEMA3asc(@Param("singDanEMA3") Double singDanEMA3) {

        return sandDanCalMapper.selectIdBySinDEMA3asc(singDanEMA3);
    }

    public int selectIdBySinDEMA3desc(@Param("singDanEMA3") Double singDanEMA3) {

        return sandDanCalMapper.selectIdBySinDEMA3desc(singDanEMA3);

    }

    public int selectIdBySinDFortEMA3asc(@Param("singDFortEMA3") Double singDFortEMA3) {

        return sandDanCalMapper.selectIdBySinDFortEMA3asc(singDFortEMA3);
    }

    public int selectIdBySinDFortEMA3desc(@Param("singDFortEMA3") Double singDFortEMA3) {

        return sandDanCalMapper.selectIdBySinDFortEMA3desc(singDFortEMA3);

    }

    public List<SandDankill> selectIDbyExpDIV(@Param("expertname") String expertname, @Param("spicerock") int spicerock,
                                              @Param("endid") int endid) {

        return sandDanCalMapper.selectIDbyExpDIV(expertname, spicerock, endid);

    }

    public List<SandDankill> selectIDbyExpHDIVlast(@Param("expid") int expid, @Param("expertname") String expertname) {

        return sandDanCalMapper.selectIDbyExpHDIVlast(expid, expertname);

    }

    public int updateSanDACV(@Param("fiveDandiv") int fiveDandiv,@Param("fiveDanskewn") int fiveDanskewn,
                             @Param("fiveDanacV") int fiveDanacV,@Param("sumDandiv") int sumDandiv,
                             @Param("sumDanskewn") int sumDanskewn,@Param("sumDanacV") int sumDanacV,
                             @Param("kuaDandiv") int kuaDandiv,@Param("kuaDanskewn") int kuaDanskewn,
                             @Param("kuaDanacV") int kuaDanacV, @Param("id") int id) {


        return sandDanCalMapper.updateSanDACV(fiveDandiv,fiveDanskewn,fiveDanacV,sumDandiv,sumDanskewn,
                sumDanacV, kuaDandiv, kuaDanskewn, kuaDanacV, id);

    }

    public int updateSanDRock(@Param("spicerock") int spicerock, @Param("singDankd") int singDankd, @Param("id") int id) {


        return sandDanCalMapper.updateSanDRock(spicerock,singDankd,id);

    }

    public List<SandDankill> selectIDbyUpdateACV(@Param("fid") int fid, @Param("expertname") String expertname) {

        return sandDanCalMapper.selectIDbyUpdateACV(fid, expertname);

    }

    public List<SandDankill> selectIDbyEMA3(@Param("expertname") String expertname, @Param("singDanEMA3") double singDanEMA3,
                                              @Param("endid") int endid) {

        return sandDanCalMapper.selectIDbyEMA3(expertname, singDanEMA3, endid);

    }

    public List<SandDankill> selectIDbyEMA3last7(@Param("expertname") String expertname, @Param("id") int id) {

        return sandDanCalMapper.selectIDbyEMA3last7(expertname, id);

    }

    public List<SandDankill> selectIDbyEMA3last3(@Param("expertname") String expertname, @Param("id") int id) {

        return sandDanCalMapper.selectIDbyEMA3last3(expertname, id);

    }

    public List<SandDankill> selectIDbyDEAlast5(@Param("expertname") String expertname, @Param("id") int id) {

        return sandDanCalMapper.selectIDbyDEAlast5(expertname, id);

    }




    public int updateSanDEMA(@Param("singDanEMA3") double singDanEMA3,@Param("singDanEMA7") double singDanEMA7,@Param("singDanDIF") double singDanDIF,
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
                             @Param("id") int id) {


        return sandDanCalMapper.updateSanDEMA(singDanEMA3,singDanEMA7,singDanDIF,singDanDEA,singDanMACD,singDFortEMA3,singDFortEMA7,
                singDFortDIF, singDFortDEA, singDFortMACD, singDFortstat, singDanlastMACD,singDFortlastMACD,
                fiveDFortEMA3,fiveDFortEMA7,fiveDanDIF,fiveDanDEA,fiveDFortMACD,
                fiveDFortstat,fiveDFortlastMACD,sumDFortEMA3,sumDFortEMA7,sumDanDIF,sumDanDEA,sumDFortMACD,sumDFortstat,sumDFortlastMACD,
                kuaDFortEMA3,kuaDFortEMA7,kuaDanDIF,kuaDanDEA,kuaDFortMACD,kuaDFortstat,kuaDFortlastMACD,id);

    }

    public List<SandDankill> selectxinbyExpIssue(@Param("issue") String issue, @Param("expertname") String expertname) {

        return sandDanCalMapper.selectxinbyExpIssue(issue, expertname);

    }
}
