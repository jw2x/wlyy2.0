package com.yihu.jw.manage.service.system;

import com.yihu.jw.manage.dao.system.UserDao;
import com.yihu.jw.manage.dao.system.UserRoleDao;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.restmodel.exception.ManageException;
import com.yihu.jw.restmodel.wlyy.WlyyContant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/9.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    public ManageUser findByAccount(String username) {
        return userDao.findByAccount(username);
    }

    public ManageUser findByCode(String usercode) {
        return userDao.findByCode(usercode);
    }

    public Page<ManageUser> userList(String name,Integer page, Integer pageSize)throws ManageException {
        // 排序
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        // 分页信息
        PageRequest pageRequest = new PageRequest(page, pageSize, sort);
        // 设置查询条件
        Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
        // 用户名称
        if (!StringUtils.isEmpty(name)&&!("null".equals(name))) {
            name="%"+name+"%";
            filters.put("name", new SearchFilter("name", SearchFilter.Operator.LIKE, name));
        }
//        filters.put("recordDate1", new SearchFilter("recordDate", Operator.GTE, begin));
//        filters.put("recordDate2", new SearchFilter("recordDate", Operator.LTE, end));
        // 未作废
        filters.put("status", new SearchFilter("status", SearchFilter.Operator.EQ, WlyyContant.status_normal));
        Specification<ManageUser> spec = DynamicSpecifications.bySearchFilter(filters.values(), ManageUser.class);

        return userDao.findAll(spec, pageRequest);
    }
}
