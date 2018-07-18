package com.spider.lottery.ssqservice;

import com.spider.lottery.mapper.SsqBlueMapper;
import com.spider.lottery.mapper.SsqHistoryMapper;
import com.spider.lottery.pojo.SsqBlue;
import com.spider.lottery.pojo.SsqHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * 双色球蓝球测数据持久层服务
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/6
 * Time：14:56
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
public class SsqBlueService {

    @Autowired
    private SsqBlueMapper ssqBlueMapper;

    @Autowired
    private SsqHistoryMapper ssqHistoryMapper;
    /**
     * 插入双色球历史数据
     *
     * @return 1
     * @throws Exception
     */
    public int insertSSQBlue(SsqBlue record) {

        return ssqBlueMapper.insertSSQBlue(record);


    }

    public List<SsqHistory> selectbluebyissue(@Param("issue") String issue) {

        return ssqHistoryMapper.selectbluebyissue(issue);


    }




}
