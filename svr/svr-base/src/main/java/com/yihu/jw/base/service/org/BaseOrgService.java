package com.yihu.jw.base.service.org;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.base.dao.org.BaseOrgDao;
import com.yihu.jw.base.service.org.tree.SimpleTree;
import com.yihu.jw.base.service.org.tree.SimpleTreeNode;
import com.yihu.jw.base.service.org.tree.TreeNode;
import com.yihu.jw.base.service.user.UserRoleService;
import com.yihu.jw.base.service.user.UserService;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.base.util.JavaBeanUtils;
import com.yihu.jw.entity.base.org.BaseOrgSaasDO;
import com.yihu.jw.entity.base.org.BaseOrgUserDO;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.jw.entity.base.user.UserRoleDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${configDefault.saasId}")
    private String defaultSaasId;

    @Autowired
    private BaseOrgDao baseOrgDao;

    @Autowired
    private BaseOrgSaasService baseOrgSaasService;

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
    public JSONObject queryOrgBaseInfoList(String codeOrName,String orgStatus,int page,int size) throws Exception {
        JSONObject result = new JSONObject();
        int start = 0 == page ? page++ : (page - 1) * size;
        int end = 0 == size ? 15 : page * size;
        String codeOrNameValue = null == codeOrName ? "" : codeOrName;
        String sql = "select id,code,name,case del when 1 then '有效' else '失效' end as status,concat(province_name,city_name,town_name,address) as address " +
                " from base_org " +
                " where " +
                " ((code like '{code}' or ''='{code}')  and (del = '{orgStatus}' or ''='{orgStatus}'))" +
                " or " +
                " ((name like '{name}' or ''='{name}') and (del = '{orgStatus}' or ''='{orgStatus}'))" +
                " and" +
                " (del = '{orgStatus}' or ''='{orgStatus}')" +
                "  order by create_time desc limit {start},{end}";
        String finalSql = sql

                .replace("{code}", "%" + codeOrNameValue + "%")
                .replace("{name}", "%" + codeOrNameValue + "%")
                .replace("{orgStatus}", null == orgStatus ? "" : orgStatus).replace("{start}", String.valueOf(start))
                .replace("{end}", String.valueOf(end));

        String countSql = "SELECT count(id)" +
                " FROM base_org " +
                " WHERE " +
                " ((code like '{code}' or ''='{code}')  and (del = '{orgStatus}' or ''='{orgStatus}'))" +
                " OR " +
                " ((name like '{name}' or ''='{name}') and (del = '{orgStatus}' or ''='{orgStatus}'))" +
                " AND" +
                " (del = '{orgStatus}' OR ''='{orgStatus}')";
        String finalCountSql = countSql
                .replace("{code}", "%" + codeOrNameValue + "%")
                .replace("{name}", "%" + codeOrNameValue + "%")
                .replace("{orgStatus}", null == orgStatus ? "" : orgStatus);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(finalSql);
        Integer count = jdbcTemplate.queryForObject(finalCountSql, Integer.class);
        result.put("count", count);
        result.put("msg", JavaBeanUtils.getInstance().mapListJson(list));
        return result;
    }

    /**
     * 根据id查询机构，只查询机构的信息
     * @param id
     * @return
     */
    public JSONObject queryOneById(String id) throws JsonProcessingException {
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(id)){
            result.put("msg","org not exist for id:"+id);
            result.put("response",ConstantUtils.FAIL);
            return result;
        }
        BaseOrgDO baseOrgDO = baseOrgDao.findOne(id);
        if(null == baseOrgDO){
            return null;
        }
        result.put("response",ConstantUtils.SUCCESS);
        result.put("msg",baseOrgDO);
        return result;
    }

    /**
     * 新增/修改机构
     * @param baseOrgDO
     * @param orgAdminJson
     */
    public String createOrUpdateOrg(BaseOrgDO baseOrgDO,JSONObject orgAdminJson){
        UserDO userDO = null;
        //id为空表示新增
        if(StringUtils.isEmpty(baseOrgDO.getId())){
            String mobile = orgAdminJson.getString("mobile");
            String adminName = orgAdminJson.getString("orgAdmin");
            if(StringUtils.isEmpty(mobile)){
                return "paramter for admin is null";
            }
            baseOrgDO.setOrgAdmin(adminName);
            baseOrgDao.save(baseOrgDO);

            //新增机构与saas关联关系，初始saas设置为默认值，机构分配给租户在租户管理中操作
            BaseOrgSaasDO baseOrgSaasDO = new BaseOrgSaasDO();
            baseOrgSaasDO.setOrgCode(baseOrgDO.getCode());
            baseOrgSaasDO.setSaasid(defaultSaasId);
            baseOrgSaasService.save(baseOrgSaasDO);

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

            //新增机构与区域的树形结构关系
            orgTreeService.addOrgTreeNode(baseOrgDO);
        }else{
            BaseOrgDO oldBaseOrgDO = baseOrgDao.findOne(baseOrgDO.getId());
            if(null == oldBaseOrgDO){
                return "no exist this org";
            }
            if(!baseOrgDO.getCode().equalsIgnoreCase(oldBaseOrgDO.getCode()) || !baseOrgDO.getName().equalsIgnoreCase(oldBaseOrgDO.getName())){
                orgTreeService.updateOrgTreeNode(oldBaseOrgDO,baseOrgDO,OrgTree.Level.org.getLevelValue());
            }
            if(!baseOrgDO.getTownCode().equalsIgnoreCase(oldBaseOrgDO.getTownCode())){
                orgTreeService.updateOrgTreeNode(oldBaseOrgDO,baseOrgDO,OrgTree.Level.town.getLevelValue());
            }
            if(!baseOrgDO.getCityCode().equalsIgnoreCase(oldBaseOrgDO.getCityCode())){
                orgTreeService.updateOrgTreeNode(oldBaseOrgDO,baseOrgDO,OrgTree.Level.city.getLevelValue());
            }
            if(!baseOrgDO.getProvinceCode().equalsIgnoreCase(oldBaseOrgDO.getProvinceCode())){
                orgTreeService.updateOrgTreeNode(oldBaseOrgDO,baseOrgDO,OrgTree.Level.province.getLevelValue());
            }
            baseOrgDao.save(baseOrgDO);
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
     * 生效或失效单个机构
     * @param id
     * @param del
     * @return
     */
    public String enableOrDisableOrg(String id,String del){
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(del)){
            result.put("msg","parameter id or del is null");
            result.put("response",ConstantUtils.FAIL);
            return result.toJSONString();
        }
        BaseOrgDO baseOrgDO = baseOrgDao.findOne(id);
        if( null == baseOrgDO ){
            result.put("msg","org not exist for id:" + id);
            result.put("response",ConstantUtils.FAIL);
            return result.toJSONString();
        }
        baseOrgDO.setDel(del);
        this.save(baseOrgDO);
        result.put("response",ConstantUtils.SUCCESS);
        return result.toJSONString();
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
//        filter.getExcludes().add("parentNodeId");
        filter.getExcludes().add("orderNum");
        filter.getExcludes().add("level");

        return JSONObject.toJSONString(treeNode, filter);
    }

    public String getOrgAreaTree(String saasId){
        StringBuffer sql = new StringBuffer("SELECT t.* from base_org b,org_tree t,base_org_saas o ")
                .append("WHERE o.saasid='").append(saasId).append("' AND o.org_code = b.`code` AND ")
                .append("(b.`code`= t.`code` or b.city_code=t.`code` or b.province_code = t.`code` or b.town_code=t.`code`)");

        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.addAll(jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper(OrgTree.class)));
        SimpleTree tree = new SimpleTree(treeNodes);
        List<SimpleTreeNode> treeNode = tree.getRoot();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("parent");
        filter.getExcludes().add("allChildren");
//        filter.getExcludes().add("parentNodeId");
        filter.getExcludes().add("orderNum");
        filter.getExcludes().add("level");

        return JSONObject.toJSONString(treeNode, filter);
    }

    /**
     * 查找某一saasId下的有效的机构code
     * @param saasId
     * @return
     */
    public List findOrgCodeListBySaasId(String saasId){
        List result = new ArrayList();
        if(StringUtils.isEmpty(saasId)){
            return result;
        }
        return baseOrgDao.findOrgCodeBySaasId(saasId);
    }

     /**
     * 查找某一saasId下的有效的机构列表，新增团队时选择归属机构时用到
     * @param saasId
     * @return
     */
    public List findOrgListBySaasId(String saasId){
        List result = new ArrayList();
        if(StringUtils.isEmpty(saasId)){
            return result;
        }
        return baseOrgDao.findOrgCodeBySaasId(saasId);
    }


}
