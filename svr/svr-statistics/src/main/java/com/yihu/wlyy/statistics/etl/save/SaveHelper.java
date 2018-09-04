package com.yihu.wlyy.statistics.etl.save;

import com.yihu.wlyy.statistics.etl.save.es.ElastricSearchSave;
import com.yihu.wlyy.statistics.util.SpringUtil;
import com.yihu.wlyy.statistics.vo.SaveModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chenweida on 2017/6/2.
 */
@Component
@Scope("prototype")
public class SaveHelper {

    public Boolean save(List<SaveModel> sms) {
        return SpringUtil.getBean(ElastricSearchSave.class).save(sms);


    }

    public Boolean update(List<SaveModel> sms) {
        return SpringUtil.getBean(ElastricSearchSave.class).update(sms);


    }
}
