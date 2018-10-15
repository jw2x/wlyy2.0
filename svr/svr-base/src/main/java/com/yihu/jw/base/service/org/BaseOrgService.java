package com.yihu.jw.base.service.org;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.google.gson.JsonObject;
import com.yihu.jw.base.dao.org.BaseOrgDao;
import com.yihu.jw.base.dao.org.OrgTreeDao;
import com.yihu.jw.base.dao.user.UserDao;
import com.yihu.jw.base.dao.user.UserRoleDao;
import com.yihu.jw.base.service.org.tree.SimpleTree;
import com.yihu.jw.base.service.org.tree.SimpleTreeNode;
import com.yihu.jw.base.service.org.tree.Tree;
import com.yihu.jw.base.service.org.tree.TreeNode;
import com.yihu.jw.base.service.user.UserRoleService;
import com.yihu.jw.base.service.user.UserService;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.jw.entity.base.user.UserRoleDO;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.mysql.query.BaseJpaService;
import com.yihu.utils.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.org.BaseOrgDO;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 
 * 机构信息服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class BaseOrgService extends BaseJpaService<BaseOrgDO, BaseOrgDao> {

    @Autowired
    private BaseOrgDao baseOrgDao;

    @Autowired
    private OrgTreeDao orgTreeDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;



    /**
     * 机构基础信息列表
     * @param orgCode
     * @param orgName
     * @param orgStatus
     * @return
     */
    public List<Map<String,Object>> queryOrgBaseInfoList(String orgCode,String orgName,String orgStatus,int page,int size,String sorts){
        List<Map<String,Object>> result = new ArrayList<>();
        if(StringUtils.endsWithIgnoreCase("1",orgStatus)){
            if(!StringUtils.isEmpty(orgCode) ){
                result = baseOrgDao.findByCodeAndDel(orgCode,orgStatus,createPage(page,size,sorts));
            }else if(!StringUtils.isEmpty(orgCode)){
                result = baseOrgDao.findByNameAndDel(orgName,orgStatus,createPage(page,size,sorts));
            }else{
                result = baseOrgDao.findBaseInfoByDel(orgStatus,createPage(page,size,sorts));
            }
        }else{
            if(!StringUtils.isEmpty(orgCode) ){
                result = baseOrgDao.findByCode(orgCode,createPage(page,size,sorts));
            }else if(!StringUtils.isEmpty(orgCode)){
                result = baseOrgDao.findByName(orgName,createPage(page,size,sorts));
            }else{
                result = baseOrgDao.findBaseInfo(createPage(page,size,sorts));
            }
        }
        return result;
    }

    /**
     * 新增/修改机构
     * @param baseOrgDO
     * @param orgAdminJson
     */
    public String createOrUpdateOrg(BaseOrgDO baseOrgDO,JSONObject orgAdminJson){
        UserDO userDO = null;
        String mobile = orgAdminJson.getString("mobile");
        String adminName = orgAdminJson.getString("orgAdmin");
        if(StringUtils.isEmpty(mobile)){
            return "paramter for admin is null";
        }
        //id为空表示新增
        if(StringUtils.isEmpty(baseOrgDO.getId())){
            baseOrgDao.save(baseOrgDO);

            //新增用户（管理员）
            userDO = new UserDO();
            userDO.setUsername(adminName);
            userDO.setMobile(mobile);
            userService.register(userDO);

            // 新增用户角色关联关系
            UserRoleDO userRoleDO = new UserRoleDO();
            userRoleDO.setRoleId("");
            userRoleDO.setUserId("");
            userRoleService.save(userRoleDO);
        }else{
            String id = orgAdminJson.getString("id");
            if(StringUtils.isEmpty(id)){
                return "paramter id for admin is null when update";
            }
            baseOrgDao.save(baseOrgDO);
            userDO = userService.findById(id);
            //没有修改就不保存
            if(StringUtils.endsWithIgnoreCase(adminName,userDO.getUsername()) && StringUtils.endsWithIgnoreCase(mobile,userDO.getMobile())){
                return ConstantUtils.SUCCESS;
            }
            userDO.setUsername(adminName);
            userDO.setMobile(mobile);
            userService.save(userDO);
        }
        return ConstantUtils.SUCCESS;
    }


    /**
     * 判断机构代码是否存在
     * @param code
     * @return
     */
    public Boolean existCode(String code){
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        return baseOrgDao.existsByCode(code);
    }

    /**
     * 构建机构区域树形结构
     * @return
     */
    public String getOrgAreaTree(){

        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.addAll(orgTreeDao.findAll());
        SimpleTree tree = new SimpleTree(treeNodes);
        List<SimpleTreeNode> treeNode = tree.getRoot();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("parent");
        filter.getExcludes().add("allChildren");

        return JSONObject.toJSONString(treeNode, filter);
    }

}
