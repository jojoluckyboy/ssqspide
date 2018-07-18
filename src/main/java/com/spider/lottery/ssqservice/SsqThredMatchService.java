package com.spider.lottery.ssqservice;

import com.spider.lottery.mapper.SsqThredMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：
 * 双色球数据持久层服务
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/6
 * Time：14:56
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
public class SsqThredMatchService {

    @Autowired
    private SsqThredMapper SsqThredMapper;


    /**
     * 插入双色球历史匹配数据
     *
     * @return 1
     * @throws Exception
     */
    public int updateSSQThredMatch(@Param("time1") String time1, @Param("time2")String time2) {

        return SsqThredMapper.updateSSQThredMatch(time1,time2);


    }



}
