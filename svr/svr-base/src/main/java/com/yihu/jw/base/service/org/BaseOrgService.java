package com.yihu.jw.base.service.org;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.yihu.jw.base.dao.org.BaseOrgDao;
import com.yihu.jw.base.dao.org.BaseOrgUserDao;
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
import com.yihu.jw.base.util.JavaBeanUtils;
import com.yihu.jw.entity.base.org.BaseOrgUserDO;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.jw.entity.base.user.UserRoleDO;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.mysql.query.BaseJpaService;
import com.yihu.utils.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private OrgTreeService orgTreeService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private  BaseOrgUserService baseOrgUserService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 机构基础信息列表
     * @param codeOrName
     * @param orgStatus
     * @return
     */
    public String queryOrgBaseInfoList(String codeOrName,String orgStatus,int page,int size) throws Exception {
        List<Map<String,Object>> result = new ArrayList<>();
        int start = 0 == page ? page++ : (page - 1) * size;
        int end = 0 == size ? size = 10 : page  * size;
        String code = null == codeOrName ? "": codeOrName;
        String name = null == codeOrName ? "": codeOrName;
        String sql = "select id,code,name,case del when 1 then '有效' else '失效' end as status,concat(province_name,city_name,town_name,address) as address " +
                "from base_org " +
                " where " +
                " (code like '{code}' or ''='{code}')" +
                " or " +
                " (name like '{name}' or ''='{name}') " +
                " and" +
                " (del = '{orgStatus}' or ''='{orgStatus}')" +
                " limit {start},{end}";
        String finalSql = sql.replace("{code}", "%" + code + "%")
                .replace("{name}", "%" + name + "%")
                .replace("{orgStatus}", null == orgStatus ? "": orgStatus).replace("{start}", String.valueOf(start))
                .replace("{end}", String.valueOf(end));
        result = jdbcTemplate.queryForList(finalSql);
        return JavaBeanUtils.getInstance().mapListJson(result);
    }

    /**
     * 根据id查询机构
     * @param id
     * @return
     */
    public String queryOneById(String id) throws JsonProcessingException {
        if(StringUtils.isEmpty(id)){
            return "org not exist for id:"+id;
        }
        BaseOrgDO baseOrgDO = baseOrgDao.findOne(id);
        if(null == baseOrgDO){
            return "";
        }
        List<BaseOrgUserDO> adminList = baseOrgUserService.findAllByOrgCode(baseOrgDO.getCode());
        JSONObject jsonObject = JSONObject.parseObject(objectMapper.writeValueAsString(baseOrgDO));
        jsonObject.put("admin",adminList);
        return jsonObject.toJSONString();
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
            baseOrgDO.setOrgAdmin(adminName);
            baseOrgDao.save(baseOrgDO);

            //新增机构与管理员关联关系
            BaseOrgUserDO baseOrgUserDO = new BaseOrgUserDO();
            baseOrgUserDO.setOrgCode(baseOrgDO.getCode());
            baseOrgUserDO.setUserAccount(mobile);
            baseOrgUserService.save(baseOrgUserDO);

            //新增用户（管理员）
            userDO = new UserDO();
            userDO.setUsername(mobile);
            userDO.setName(adminName);
            userDO.setMobile(mobile);
            userService.registerWithMobile(userDO);

            // 新增用户角色关联关系
            UserRoleDO userRoleDO = new UserRoleDO();
            userRoleDO.setRoleId("");
            userRoleDO.setUserId(userDO.getId());
            userRoleService.save(userRoleDO);
        }else{
            String adminId = orgAdminJson.getString("id");
            if(StringUtils.isEmpty(adminId)){
                return "paramter id for admin is null when update";
            }
            BaseOrgDO oldBaseOrgDO = baseOrgDao.findOne(baseOrgDO.getId());
            if(null == oldBaseOrgDO){
                return "no exist this org";
            }
            baseOrgDO.setOrgAdmin(adminName);
            baseOrgDao.save(baseOrgDO);
            if(!baseOrgDO.getTownCode().equalsIgnoreCase(oldBaseOrgDO.getTownCode())){
                orgTreeService.updateOrgTreeNode(oldBaseOrgDO,baseOrgDO,OrgTree.Level.town.getLevelValue());
            }
            if(!baseOrgDO.getCityCode().equalsIgnoreCase(oldBaseOrgDO.getCityCode())){
                orgTreeService.updateOrgTreeNode(oldBaseOrgDO,baseOrgDO,OrgTree.Level.city.getLevelValue());
            }
            if(!baseOrgDO.getProvinceCode().equalsIgnoreCase(oldBaseOrgDO.getProvinceCode())){
                orgTreeService.updateOrgTreeNode(oldBaseOrgDO,baseOrgDO,OrgTree.Level.province.getLevelValue());
            }
            userDO = userService.findById(adminId);
            //没有修改就不保存
            if(StringUtils.endsWithIgnoreCase(adminName,userDO.getUsername()) && StringUtils.endsWithIgnoreCase(mobile,userDO.getMobile())){
                return ConstantUtils.SUCCESS;
            }
            userDO.setName(adminName);
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
        treeNodes.addAll(orgTreeService.findListByLevel(OrgTree.Level.org.getLevelValue()));
        SimpleTree tree = new SimpleTree(treeNodes);
        List<SimpleTreeNode> treeNode = tree.getRoot();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("parent");
        filter.getExcludes().add("allChildren");
        filter.getExcludes().add("parentNodeId");
        filter.getExcludes().add("orderNum");
        filter.getExcludes().add("level");

        return JSONObject.toJSONString(treeNode, filter);
    }

    public String getOrgAreaTree(String saasId){
        StringBuffer sql = new StringBuffer("SELECT t.* from base_org b,org_tree t ")
                .append("WHERE b.saasid='").append(saasId).append("' AND ")
                .append("(b.`code`= t.`code` or b.city_code=t.`code` or b.province_code = t.`code` or b.town_code=t.`code`)");

        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.addAll(jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper(OrgTree.class)));
        SimpleTree tree = new SimpleTree(treeNodes);
        List<SimpleTreeNode> treeNode = tree.getRoot();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("parent");
        filter.getExcludes().add("allChildren");
        filter.getExcludes().add("parentNodeId");
        filter.getExcludes().add("orderNum");
        filter.getExcludes().add("level");

        return JSONObject.toJSONString(treeNode, filter);
    }

    /**
     * 查找某一saasId下的所有机构code
     * @param saasId
     * @return
     */
    public List findOrgCodeBySaasId(String saasId){
        List result = new ArrayList();
        if(StringUtils.isEmpty(saasId)){
            return result;
        }
        return baseOrgDao.findOrgCodeBySaasId(saasId);
    }

    public static void main(String[] args) {
        String codeOrName = "aa";
        String orgStatus = "";
        int page = 1;
        int size = 10;
        int start = (page - 1) * size;
        int end = page  * size;
        String sql = "select id,code,name,case del when 1 then '有效' else '失效' end as status,concat(province_name,city_name,town_name,street_name) as address " +
                "from base_org " +
                " where " +
                " (code like '{code}' or ''='{code}')" +
                " or " +
                " (name like '{name}' or ''='{name}') " +
                " and" +
                " (del = '{orgStatus}' or ''='{orgStatus}')" +
                " limit {start},{end}";
        String finalSql = sql.replace("{code}", "%" + codeOrName + "%")
                .replace("{name}", "%" + codeOrName + "%")
                .replace("{orgStatus}", orgStatus).replace("{start}", String.valueOf(start))
                .replace("{end}", String.valueOf(end));
        System.out.println(finalSql);
    }
}
