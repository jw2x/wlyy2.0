package com.yihu.jw.controller.base.version;

import com.yihu.jw.commnon.base.base.BaseContants;
import com.yihu.jw.feign.base.version.UserVersionFeign;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2017/11/13.
 */

@RestController
@RequestMapping(BaseContants.api_common)
@Api(description = "灰度发布相关")
public class UserVersionController {
    @Autowired
    private UserVersionFeign userVersionFeign;

}
