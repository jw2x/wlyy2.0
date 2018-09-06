package com.yihu.jw.base.endpoint.score;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.base.service.score.ScoreService;
import com.yihu.jw.entity.base.score.BaseEvaluateDO;
import com.yihu.jw.entity.base.score.BaseEvaluateScoreDO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Trick on 2018/9/3.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.Score.PREFIX)
@Api(value = "评价系统", description = "评价系统", tags = {"wlyy基础服务 - 评价系统"})
public class ScoreEndPoint extends EnvelopRestEndpoint {
    @Autowired
    private ScoreService scoreService;

    @PostMapping(value = BaseRequestMapping.Score.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public Envelop addEvaluateScore(@ApiParam(name = "baseEvaluateScoreJson", value = "分数主表json", required = true)
                                    @RequestParam(value = "baseEvaluateScoreJson") String baseEvaluateScoreJson,
                                    @ApiParam(name = "evaluates", value = "用户列表", required = true)
                                    @RequestParam(value = "evaluates") String evaluates) {
        BaseEvaluateScoreDO baseEvaluateScoreDO = JSONObject.parseObject(baseEvaluateScoreJson, BaseEvaluateScoreDO.class);
        List<BaseEvaluateDO> evaluateDOs = (List<BaseEvaluateDO>) JSONArray.parseArray(evaluates, BaseEvaluateDO.class);
        scoreService.addEvaluateScore(baseEvaluateScoreDO, evaluateDOs);
        return success("success");
    }
}
