package com.spider.lottery.sanDservice;

import com.spider.lottery.mapper.SanDHistoryMapper;
import com.spider.lottery.pojo.SanDHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description：
 * 3D数据持久层服务
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/6
 * Time：14:56
 * Copyright© 2003-2016 Zhejiang huixin technology company
 */
@Service
@Transactional
public class SandService {

    @Autowired
    private SanDHistoryMapper sanDHistoryMapper;

    /**
     * 插入3D历史数据
     *
     * @return 1
     * @throws Exception
     */
    public int insertSanDHistory(SanDHistory record) {

        return sanDHistoryMapper.insertSanDHistory(record);


    }

    /**
     * 返回3D历史数据当前数据库中最后一行数据
     *
     * @return 当前数据库中最近一次的期数
     * @throws Exception
     */
    public List<SanDHistory> selectSanDbLast() {

        return sanDHistoryMapper.selectSanDbLast();


    }

    public List<SanDHistory> selectSanDhisSpice() {

        return sanDHistoryMapper.selectSanDhisSpice();


    }


    public List<SanDHistory> selectSanDhisByIssue(@Param("issue") String issue) {

        return sanDHistoryMapper.selectSanDhisByIssue(issue);


    }

    public List<SanDHistory> selectSanDhisById(@Param("id") int id) {

        return sanDHistoryMapper.selectSanDhisById(id);


    }


    public int updateSanDById(@Param("duplicateDan") int duplicateDan,@Param("oposeDan") int oposeDan,
                       @Param("compDan") int compDan,@Param("secutiveDan") int secutiveDan,
                       @Param("nextPlusDan") int nextPlusDan,@Param("id") int id){

        return sanDHistoryMapper.updateSanDById(duplicateDan,oposeDan,compDan,secutiveDan,nextPlusDan,id);

    }



}
