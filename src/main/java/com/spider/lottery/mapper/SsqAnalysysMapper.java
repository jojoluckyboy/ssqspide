package com.spider.lottery.mapper;

import com.spider.lottery.pojo.SsqAnalyse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SsqAnalysysMapper {


    int insertSSQAnalysys(SsqAnalyse record);

    int updateSSQAnalysys(@Param("one") int one, @Param("two") int two, @Param("three") int three, @Param("four") int four
            , @Param("five") int five, @Param("six") int six, @Param("seven") int seven, @Param("eight") int eight
            , @Param("nine") int nine, @Param("ten") int ten, @Param("eleven") int eleven, @Param("twelve") int twelve
            , @Param("thirteen") int thirteen, @Param("fourteen") int fourteen, @Param("fifteen") int fifteen, @Param("sixteen") int sixteen
            , @Param("seventeen") int seventeen, @Param("eighteen") int eighteen, @Param("nineteen") int nineteen, @Param("twenty") int twenty
            , @Param("twentyone") int twentyone, @Param("twentytwo") int twentytwo, @Param("twentythree") int twentythree, @Param("twentyfour") int twentyfour
            , @Param("twentyfive") int twentyfive, @Param("twentysix") int twentysix, @Param("twentyseven") int twentyseven, @Param("twentyeight") int twentyeight
            , @Param("twentynine") int twentynine, @Param("thirty") int thirty,@Param("expertname") String expertname
    );


    int updateSSQAnalyPer(@Param("pert80") int pert80, @Param("pert90") int pert90,@Param("expertname") String expertname);


    List<SsqAnalyse> selectByexpert(@Param("expertname") String expertname);

    }