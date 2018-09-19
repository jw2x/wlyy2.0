package com.yihu.jw.healthyhouse.service.user;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.dao.UserDao;
import com.yihu.jw.healthyhouse.model.user.User;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.wlyy.HouseUserContant;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author HZY
 * @created 2018/9/18 19:48
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByCode(String code){
        return userDao.findByLoginCode(code);
    }

    public User findByName(String username) {
        return userDao.findByName(username);
    }


    /**
     *  分页获取用户列表
     * @param page
     * @param pageSize
     * @param map
     * @return
     * @throws ManageException
     */
    public Page<User> userList(Integer page, Integer pageSize, Map<String,String> map)throws ManageException {
        // 排序
        Sort sort = new Sort(Sort.Direction.DESC, "modifyDate");
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
        String mobile = map.get("telephone");
        if (!StringUtils.isEmpty(mobile)) {
            filters.put("telephone", new SearchFilter("telephone", SearchFilter.Operator.LIKE, mobile));
        }
        // 激活状态
        filters.put("activated", new SearchFilter("activated", SearchFilter.Operator.EQ, HouseUserContant.activated_active));
        Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
        return userDao.findAll(spec, pageRequest);
    }


    /**
     * 更改用户状态
     * @param codes        待删除code
     * @param userCode     修改者code
     */
    @Transactional
    public void updateStatus(String codes, String userCode,Integer status) {
        User user = userDao.findByLoginCode(userCode);
        String userName = user.getName();
        for(String code:codes.split(",")){
            User user1 = findByCode(code);
            user1.setActivated(status);
            user1.setUpdateUserName(userName);
            user1.setUpdateUser(userCode);
            userDao.save(user1);
        }
    }

    /**
     *  新增/修改用户
     * @param user      用户信息
     * @param userCode  操作者编码
     * @return
     * @throws ManageException
     */
    public Envelop saveOrUpdate(User user, String userCode) throws ManageException {
        User loginUser = userDao.findByLoginCode(userCode);
        String userName = loginUser.getName();
        if(user.getId()==null){//保存
            //判断登陆账号是否存在
            User user1 = userDao.findByLoginCode(userCode);
            if(user1!=null){//登陆账号已存在
                throw new ManageException("该登陆账号已存在");
            }
            String salt = UUID.randomUUID().toString().replaceAll("-","");
            user.setSalt(salt);
            String password = MD5.GetMD5Code(user.getPassword() + salt);
            user.setPassword(password);
            user.setCreateUser(userCode);
            user.setCreateTime(new Date());
            user.setActivated(1);
            userDao.save(user);
            return Envelop.getSuccess("保存成功");
        }else{//修改
            User user1 = findByCode(user.getLoginCode());
//            String psd = MD5.GetMD5Code(oldPsd + user1.getSalt());
//            if(!user1.getPassword().equals(psd)){//判断密码是否相同
//                throw new ManageException("原密码错误");
//            }
            user.setUpdateUser(userCode);
            user.setUpdateUserName(userName);
            userDao.save(user);
            return Envelop.getSuccess("修改成功");
        }
    }
}
