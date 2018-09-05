package com.yihu.jw.base.service.score;

import com.yihu.jw.base.dao.score.BaseEvaluateDao;
import com.yihu.jw.base.dao.score.BaseEvaluateScoreDao;
import com.yihu.jw.entity.base.score.BaseEvaluateDO;
import com.yihu.jw.entity.base.score.BaseEvaluateScoreDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Trick on 2018/8/31.
 */
@Service
@Transactional
public class ScoreService {

    @Autowired
    private BaseEvaluateScoreDao baseEvaluateScoreDao;

    @Autowired
    private BaseEvaluateDao baseEvaluateDao;

    public String addEvaluateScore(BaseEvaluateScoreDO baseEvaluateScoreDO, List<BaseEvaluateDO> evaluateDOList){

        if(evaluateDOList!=null&& evaluateDOList.size()>0){
            Double total = 0.0;
            for(BaseEvaluateDO evaluate :evaluateDOList){
                total += evaluate.getScore();
            }
            Double avg = new BigDecimal(total/evaluateDOList.size()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            baseEvaluateScoreDO.setScore(avg);
            baseEvaluateDao.save(evaluateDOList);
            baseEvaluateScoreDao.save(baseEvaluateScoreDO);
        }
        return "success";
    }

}
