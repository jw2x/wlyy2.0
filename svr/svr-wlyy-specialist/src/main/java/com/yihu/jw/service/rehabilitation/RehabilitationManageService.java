package com.yihu.jw.service.rehabilitation;

import com.yihu.jw.restmodel.web.MixEnvelop;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by 刘文彬 on 2018/8/16.
 */
@Service
@Transactional
public class RehabilitationManageService {


    public MixEnvelop<Map<String,Object>, Map<String,Object>> findRehabilitationPlan(String doctorCode, String diseaseCode, Integer planType, Integer page, Integer pageSize){

        page = page-1;
        Pageable pageRequest = new PageRequest(page, pageSize);

        return null;
    }
}
