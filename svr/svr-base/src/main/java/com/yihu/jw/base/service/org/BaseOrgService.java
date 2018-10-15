package com.yihu.jw.base.service.org;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.google.gson.JsonObject;
import com.yihu.jw.base.dao.org.BaseOrgDao;
import com.yihu.jw.base.dao.org.OrgTreeDao;
import com.yihu.jw.base.service.org.tree.SimpleTree;
import com.yihu.jw.base.service.org.tree.SimpleTreeNode;
import com.yihu.jw.base.service.org.tree.Tree;
import com.yihu.jw.base.service.org.tree.TreeNode;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.mysql.query.BaseJpaService;
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
                result = baseOrgDao.findByCodeAndDel(orgCode,orgStatus,creatPage(page,size,sorts));
            }else if(!StringUtils.isEmpty(orgCode)){
                result = baseOrgDao.findByNameAndDel(orgName,orgStatus,creatPage(page,size,sorts));
            }else{
                result = baseOrgDao.findBaseInfoByDel(orgStatus,creatPage(page,size,sorts));
            }
        }else{
            if(!StringUtils.isEmpty(orgCode) ){
                result = baseOrgDao.findByCode(orgCode,creatPage(page,size,sorts));
            }else if(!StringUtils.isEmpty(orgCode)){
                result = baseOrgDao.findByName(orgName,creatPage(page,size,sorts));
            }else{
                result = baseOrgDao.findBaseInfo(creatPage(page,size,sorts));
            }
        }
        return result;
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
