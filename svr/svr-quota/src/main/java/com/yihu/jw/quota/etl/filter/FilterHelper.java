package com.yihu.jw.quota.etl.filter;

import com.yihu.jw.quota.etl.Contant;
import com.yihu.jw.quota.etl.filter.filter.NotNullFilter;
import com.yihu.jw.quota.model.jpa.rule.TjQuotaRule;
import com.yihu.jw.quota.service.rule.TjCleanRuleService;
import com.yihu.jw.quota.util.SpringUtil;
import com.yihu.jw.quota.vo.FilterModel;
import com.yihu.jw.quota.vo.QuotaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
@Component
@Scope("prototype")
public class FilterHelper {
    @Autowired
    private TjCleanRuleService cleanRuleService;

    public FilterHelper() {
    }

    /**
     * 根据过滤规则过滤数据
     *
     * @param dataModels
     * @param quotaVO
     * @return
     */
    public FilterModel filter(Object dataModels, QuotaVO quotaVO) {
        List<TjQuotaRule> rules = cleanRuleService.findByQuotaCode(quotaVO.getCode());
        FilterModel f=new FilterModel(dataModels);
        rules.stream().forEach(rule -> {
             switch (rule.getType()){
                 //非空校验
                 case Contant.role.not_null: {
                     SpringUtil.getBean(NotNullFilter.class).filter(f,quotaVO);break;}
                 //如果有其他的校验规则在这边扩展
             }
        });
        return f;
    }

}
