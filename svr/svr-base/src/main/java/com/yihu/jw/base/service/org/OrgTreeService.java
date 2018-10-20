package com.yihu.jw.base.service.org;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.yihu.jw.base.dao.org.OrgTreeDao;
import com.yihu.jw.base.service.org.tree.SimpleTree;
import com.yihu.jw.base.service.org.tree.SimpleTreeNode;
import com.yihu.jw.base.service.org.tree.TreeNode;
import com.yihu.jw.entity.base.org.BaseOrgDO;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * 机构信息服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年10月15日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class OrgTreeService extends BaseJpaService<OrgTree, OrgTreeDao> {

    @Autowired
    private OrgTreeDao orgTreeDao;

    /**
     * 添加机构和区域的关系
     * @param baseOrgDO
     */
    public void addOrgTreeNode(BaseOrgDO baseOrgDO){
        List<OrgTree> treeNodes = new ArrayList<>();
        if(!orgTreeDao.existsByCode(baseOrgDO.getCode())){
            OrgTree orgNode = new OrgTree(baseOrgDO.getCode(),baseOrgDO.getTownCode(),baseOrgDO.getName(), OrgTree.Level.org.getLevelValue());
            treeNodes.add(orgNode);
        }
        if(!orgTreeDao.existsByCode(baseOrgDO.getTownCode())){
            OrgTree townTree = new OrgTree(baseOrgDO.getTownCode(),baseOrgDO.getCityCode(),baseOrgDO.getTownName(), OrgTree.Level.town.getLevelValue());
            treeNodes.add(townTree);
        }
        if(!orgTreeDao.existsByCode(baseOrgDO.getCityCode())){
            OrgTree cityTree = new OrgTree(baseOrgDO.getCityCode(),baseOrgDO.getProvinceCode(),baseOrgDO.getCityName(), OrgTree.Level.city.getLevelValue());
            treeNodes.add(cityTree);
        }
        if(!orgTreeDao.existsByCode(baseOrgDO.getProvinceCode())){
            OrgTree provinceTree = new OrgTree(baseOrgDO.getProvinceCode(),"",baseOrgDO.getProvinceName(), OrgTree.Level.province.getLevelValue());
            treeNodes.add(provinceTree);
        }
        /*if(orgTreeDao.existsByCode(baseOrgDO.getTownCode())){
            OrgTree orgNode = new OrgTree(baseOrgDO.getCode(),baseOrgDO.getTownCode(),baseOrgDO.getName(), OrgTree.Level.org.getLevelValue());
            treeNodes.add(orgNode);
        }
        else if(orgTreeDao.existsByCode(baseOrgDO.getCityCode())){
            OrgTree townNode = new OrgTree(baseOrgDO.getTownCode(),baseOrgDO.getCityCode(),baseOrgDO.getTownName(), OrgTree.Level.town.getLevelValue());
            treeNodes.add(townNode);
        }
        else if(orgTreeDao.existsByCode(baseOrgDO.getProvinceCode())){
            OrgTree cityNode = new OrgTree(baseOrgDO.getCityCode(),baseOrgDO.getProvinceCode(),baseOrgDO.getCityName(), OrgTree.Level.city.getLevelValue());
            treeNodes.add(cityNode);
        }
        else{
            OrgTree provinceNode = new OrgTree(baseOrgDO.getProvinceCode(),"",baseOrgDO.getProvinceName(), OrgTree.Level.province.getLevelValue());
            treeNodes.add(provinceNode);
        }*/
        this.batchInsert(treeNodes);
    }

    /**
     * 修改机构和区域的关系
     * @param oldBaseOrgDO
     * @param newBaseOrgDO
     */
    public void updateOrgTreeNode(BaseOrgDO oldBaseOrgDO,BaseOrgDO newBaseOrgDO,int level){
        if( level == OrgTree.Level.town.getLevelValue() ){
            OrgTree orgTree = orgTreeDao.findByCodeAndParentCode(oldBaseOrgDO.getCode(),oldBaseOrgDO.getTownCode());
            if(null == orgTree){
                return;
            }
            orgTree.setParentCode(newBaseOrgDO.getTownCode());
            this.save(orgTree);
           /* List<OrgTree> treeNodes = new ArrayList<>();
            //新修改的区代码不存在，表示未添加过，应把相应的父类关联关系存储起来（城市，省份等）
            if(!orgTreeDao.existsByCode(newBaseOrgDO.getTownCode())){
                OrgTree townTree = new OrgTree(newBaseOrgDO.getTownCode(),newBaseOrgDO.getCityCode(),newBaseOrgDO.getTownName(), OrgTree.Level.town.getLevelValue());
                treeNodes.add(townTree);
            }
            if(!orgTreeDao.existsByCode(newBaseOrgDO.getCityCode())){
                OrgTree cityTree = new OrgTree(newBaseOrgDO.getCityCode(),newBaseOrgDO.getProvinceCode(),newBaseOrgDO.getCityName(), OrgTree.Level.city.getLevelValue());
                treeNodes.add(cityTree);
            }
            if(!orgTreeDao.existsByCode(newBaseOrgDO.getProvinceCode())){
                OrgTree provinceTree = new OrgTree(newBaseOrgDO.getProvinceCode(),"",newBaseOrgDO.getProvinceName(), OrgTree.Level.province.getLevelValue());
                treeNodes.add(provinceTree);
            }
            this.batchInsert(treeNodes);*/
            addOrgTreeNode(newBaseOrgDO);
        }
       else if( level == OrgTree.Level.city.getLevelValue() ){
            OrgTree orgTree = orgTreeDao.findByCodeAndParentCode(oldBaseOrgDO.getTownCode(),oldBaseOrgDO.getCityCode());
            if(null == orgTree){
                return;
            }
            orgTree.setParentCode(newBaseOrgDO.getCityCode());
            this.save(orgTree);
            addOrgTreeNode(newBaseOrgDO);
            /*List<OrgTree> treeNodes = new ArrayList<>();
            if(!orgTreeDao.existsByCode(newBaseOrgDO.getCityCode())){
                OrgTree cityTree = new OrgTree(newBaseOrgDO.getCityCode(),newBaseOrgDO.getProvinceCode(),newBaseOrgDO.getCityName(), OrgTree.Level.city.getLevelValue());
                treeNodes.add(cityTree);
            }
            if(!orgTreeDao.existsByCode(newBaseOrgDO.getProvinceCode())){
                OrgTree provinceTree = new OrgTree(newBaseOrgDO.getProvinceCode(),"",newBaseOrgDO.getProvinceName(), OrgTree.Level.province.getLevelValue());
                treeNodes.add(provinceTree);
            }
            this.batchInsert(treeNodes);*/
        }
        else if( level == OrgTree.Level.province.getLevelValue() ){
            OrgTree orgTree = orgTreeDao.findByCodeAndParentCode(oldBaseOrgDO.getCityCode(),oldBaseOrgDO.getProvinceCode());
            if(null == orgTree){
                return;
            }
            orgTree.setParentCode(newBaseOrgDO.getProvinceCode());
            this.save(orgTree);
            addOrgTreeNode(newBaseOrgDO);
            /*List<OrgTree> treeNodes = new ArrayList<>();
            if(!orgTreeDao.existsByCode(newBaseOrgDO.getProvinceCode())){
                OrgTree provinceTree = new OrgTree(newBaseOrgDO.getProvinceCode(),"",newBaseOrgDO.getProvinceName(), OrgTree.Level.province.getLevelValue());
                treeNodes.add(provinceTree);
            }
            this.batchInsert(treeNodes);*/
        }
    }

    /**
     * 根据树的节点层级查询
     * @param level
     * @return
     */
    public List<OrgTree> findListByLevel(Integer level){
        List<OrgTree> result = new ArrayList<>();
        if(null == level){
            return result;
        }
        return orgTreeDao.findByLevelLessThanEqual(level);
    }

    /**
     * 根据区下面的机构列表
     *
     * @param townCode
     * @return
     */
    public List<Map<String, Object>> findOrgListByParentCode(String townCode) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (StringUtils.isEmpty(townCode)) {
            return result;
        }
        return orgTreeDao.findOrgListByParentCode(townCode);
    }
}
