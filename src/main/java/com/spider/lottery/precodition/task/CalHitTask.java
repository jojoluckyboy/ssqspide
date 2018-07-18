package com.spider.lottery.precodition.task;

import com.spider.lottery.pojo.SsqThred;
import com.spider.lottery.ssqservice.SsqCalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：计算id区间的命中值和命中分数
 * <p>
 * Created with IntelliJ IDEA.
 * User：wang wencong
 * Date：2017/7/12
 * Time：9:55
 * Copyright© 2003-2016 Zhejiang huixin technology company
 *
 */

@Service
public class CalHitTask {

  @Autowired
    private SsqCalService ssqCalService ;

    private int count = 0;

    /**
     * 前置准备导入历史数据
     *
     * @return 0 or 1
     * @throws Exception
     */
    public void pushIcrementData() throws Exception {

        List<SsqThred> idstring= ssqCalService.selectIdByE(1000);

        if (idstring.size()!=0) {

            int startid = idstring.get(0).getId();
            int endid = ssqCalService.selectIdByEdesc(1000);


            loop1:
            for (int id = startid; id < endid + 1; id++)

            {
        /*返回查询字段*/
                int hitResult = ssqCalService.calHit(id);
                int hitScore = 0;

                if (hitResult == 0) {

                    hitScore = 0;

                } else if (hitResult == 1) {

                    hitScore = 50;
                    System.out.print("分数为50");

                } else if (hitResult == 2) {

                    hitScore = 75;

                } else if (hitResult == 3) {

                    hitScore = 100;

                }

                int Result = ssqCalService.updateSSQhit(hitResult, hitScore, id);
                if (Result == 1) {
                    count = count + 1;
                }

            }

        }
        //  System.out.println("共计"+ count+"条历史数据更新");
        }

    public int getIncreDcount() {
        return this.count;

    }

}
