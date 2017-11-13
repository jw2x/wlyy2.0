package com.yihu.jw.manage.controller.version;

import com.yihu.jw.manage.service.version.UserUrlVersionService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/20.
 */
@RestController
@RequestMapping(BaseVersionRequestMapping.api_common)
@Api(description = "app版本管理")
public class UserUrlVersionController {
    @Autowired
    private UserUrlVersionService userUrlVersionService;


    @GetMapping(BaseVersionRequestMapping.UserVersion.api_getListNoPage)
    @ApiOperation(value = "获取后台用户版本列表")
    public List getListNoPage(@ApiParam(name = "saasId", value = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId,
                              @ApiParam(name = "bsvCode", value = "bsvCode", required = false) @RequestParam(required = false, name = "bsvCode") String bsvCode) {
        Map<String, Object> req = new HashMap<String, Object>();
        Map<String, String> map = new HashMap<>();
        map.put("saasId",saasId);
        map.put("bsvCode",bsvCode);
        Envelop envelop = userUrlVersionService.getListNoPage(map);

        List<Map<String,Object>> list = envelop.getDetailModelList();
        if(list==null){
            return  new ArrayList<>();
        }
        return list;
    }


}
