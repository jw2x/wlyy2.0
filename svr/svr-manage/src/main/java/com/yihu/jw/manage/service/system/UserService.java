package com.yihu.jw.manage.service.system;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.manage.dao.system.UserDao;
import com.yihu.jw.manage.dao.system.UserRoleDao;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.wlyy.WlyyContant;
import com.yihu.jw.util.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by chenweida on 2017/6/9.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserService userService;

    public ManageUser findByAccount(String username) {
        return userDao.findByAccount(username);
    }

    public ManageUser findByCode(String usercode) {
        return userDao.findByCode(usercode);
    }

    public Page<ManageUser> userList(Integer page, Integer pageSize,Map<String,String> map)throws ManageException {
        // 排序
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        // 分页信息
        PageRequest pageRequest = new PageRequest(page, pageSize, sort);
        // 设置查询条件
        Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
        // 用户名称
        String name = map.get("name");
        if (!StringUtils.isEmpty(name)) {
            filters.put("name", new SearchFilter("name", SearchFilter.Operator.LIKE, name));
        }
        // 电话号码
        String mobile = map.get("mobile");
        if (!StringUtils.isEmpty(mobile)) {
            filters.put("mobile", new SearchFilter("mobile", SearchFilter.Operator.LIKE, mobile));
        }
        // 未作废
        filters.put("status", new SearchFilter("status", SearchFilter.Operator.EQ, WlyyContant.status_normal));
        Specification<ManageUser> spec = DynamicSpecifications.bySearchFilter(filters.values(), ManageUser.class);

        return userDao.findAll(spec, pageRequest);
    }


    @Transactional
    public void delete(String codes, String userCode) {
        ManageUser user = userService.findByCode(userCode);
        String userName = user.getName();
        for(String code:codes.split(",")){
            ManageUser user1 = findByCode(code);
            user1.setStatus(-1);
            user1.setUpdateUserName(userName);
            user1.setUpdateUser(userCode);
            userDao.save(user1);
        }
    }

    public Envelop saveOrUpdate(ManageUser user, String oldPsd, String userCode) throws ManageException {
        ManageUser loginUser = userService.findByCode(userCode);
        String userName = loginUser.getName();
        if(user.getId()==null){//保存
            //判断登陆账号是否存在
            String loginAccount = user.getLoginAccount();
            ManageUser user1 = userDao.findByAccount(loginAccount);
            if(user1!=null){//登陆账号已存在
                throw new ManageException("该登陆账号已存在");
            }
            String salt = UUID.randomUUID().toString().replaceAll("-","");
            user.setSalt(salt);
            String password = MD5.GetMD5Code(user.getPassword() + salt);
            user.setPassword(password);
            String code = UUID.randomUUID().toString().replaceAll("-", "");
            user.setCode(code);
            user.setCreateUserName(userName);
            user.setCreateUser(userCode);
            user.setStatus(1);
            userDao.save(user);
            return Envelop.getSuccess("保存成功",user);
        }else{//修改
            ManageUser user1 = findByCode(user.getCode());
            String psd = MD5.GetMD5Code(oldPsd + user1.getSalt());
            if(!user1.getPassword().equals(psd)){//判断密码是否相同
                throw new ManageException("原密码错误");
            }
            user.setUpdateUser(userCode);
            user.setUpdateUserName(userName);
            userDao.save(user);
            return Envelop.getSuccess("修改成功",user);
        }
    }
}
