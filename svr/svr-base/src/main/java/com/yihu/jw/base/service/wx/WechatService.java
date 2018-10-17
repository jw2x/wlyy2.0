package com.yihu.jw.base.service.wx;

import com.yihu.jw.base.dao.wx.*;
import com.yihu.jw.entity.base.wx.*;
import com.yihu.jw.restmodel.base.wx.*;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.rm.base.BaseRequestMapping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by trick on 2017/5/20 0020
 */
@Service
@Transactional
public class WechatService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private WechatDao wechatDao;
    @Autowired
    private WxWechatSaasDao wxWechatSaasDao;
    @Autowired
    private WxGraphicSceneDao wxGraphicSceneDao;
    @Autowired
    private WxGraphicSceneGroupDao wxGraphicSceneGroupDao;
    @Autowired
    private WxGraphicMessageDao wxGraphicMessageDao;
    @Autowired
    private WxReplySceneDao wxReplySceneDao;
    @Autowired
    private WxTemplateDao wxTemplateDao;
    @Autowired
    private WxTemplateConfigDao wxTemplateConfigDao;
    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    //====================微信与租户管理=======================

    public MixEnvelop getWxWechatList(String name,String saasName,Integer status,Integer publicType,Integer page,Integer size){

        String sql = "SELECT " +
                " w.id, " +
                " w.name, " +
                " w.`status`, " +
                " w.type, " +
                " w.public_type AS publicType," +
                " w.remark " +
                " FROM " +
                " wx_wechat w ";
        if (StringUtils.isNotBlank(saasName)) {
            sql += " JOIN wx_wechat_saas s ON w.id = s.wechat_id" +
                    " JOIN base_saas bs ON s.saas_id = bs.id ";
        }
        sql += " WHERE 1=1 ";

        if (StringUtils.isNotBlank(name)) {
            sql += " AND w.`name` LIKE '%" + name + "%' ";
        }
        if (publicType!=null) {
            sql += " AND w.public_type = " + publicType + " " ;
        }
        if (status!=null) {
            sql += " AND w.`status` = " + status + " ";
        }
        if (StringUtils.isNotBlank(saasName)) {
            sql += " AND bs.`name` like '%" + saasName + "%'";
        }
        sql += " LIMIT " + (page - 1) * size + "," + size + "";
        List<WxWechatVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(WxWechatVO.class));

        if(list!=null&&list.size()>0){
            for(WxWechatVO wx :list){
                wx.setSaas(getWxSaasVOs(wx.getId()));
            }
        }

        String countSql = "SELECT count(1) AS total"+
                " FROM " +
                " wx_wechat w ";;
        if (StringUtils.isNotBlank(saasName)) {
            countSql += " JOIN wx_wechat_saas s ON w.id = s.wechat_id" +
                    " JOIN base_saas bs ON s.saas_id = bs.id ";
        }
        countSql += " WHERE 1=1 ";
        if (StringUtils.isNotBlank(name)) {
            sql += " AND w.`name` LIKE '%" + name + "%' ";
        }
        if (publicType!=null) {
            sql += " AND w.public_type = " + publicType + " " ;
        }
        if (status!=null) {
            sql += " AND w.`status` = " + status + "";
        }
        if (StringUtils.isNotBlank(saasName)) {
            countSql += " AND bs.`name` like '%" + saasName + "%'";
        }
        List<Map<String, Object>> rstotal = jdbcTemplate.queryForList(countSql);
        Long count = 0L;
        if (rstotal != null && rstotal.size() > 0) {
            count = (Long) rstotal.get(0).get("total");
        }

        return MixEnvelop.getSuccessListWithPage(BaseRequestMapping.WeChat.api_success, list, page, size, count);
    }

    public List<WxSaasVO> getWxSaasVOs(String id){
        String sql ="SELECT " +
                " bs.`name` AS saasName, " +
                " bs.id AS saasid" +
                " FROM " +
                " wx_wechat_saas s " +
                " JOIN base_saas bs ON bs.id = s.saas_id " +
                " WHERE " +
                " s.wechat_id = '"+id+"'";
        List<WxSaasVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(WxSaasVO.class));
        return list;
    }

    public Envelop saveWxAndSaas(WxWechatDO wxWechatDO, List<WxWechatSaasDO> wxWechatSaasDOs){

        WxWechatDO wechat = wechatDao.save(wxWechatDO);

        if(wxWechatSaasDOs!=null&&wxWechatSaasDOs.size()>0){
            for(WxWechatSaasDO wxs:wxWechatSaasDOs){
                wxs.setWechatId(wechat.getId());
            }
            wxWechatSaasDao.save(wxWechatSaasDOs);
        }
        return Envelop.getSuccess(BaseRequestMapping.WeChat.api_success);
    }

    public Envelop updateWxAndSaas(WxWechatDO wxWechatDO, List<WxWechatSaasDO> wxWechatSaasDOs) {
        wechatDao.save(wxWechatDO);

        List<WxWechatSaasDO> ws = wxWechatSaasDao.findByWechatId(wxWechatDO.getId());
        wxWechatSaasDao.delete(ws);

        if(wxWechatSaasDOs!=null&&wxWechatSaasDOs.size()>0){

            for(WxWechatSaasDO wxs:wxWechatSaasDOs){
                wxs.setWechatId(wxWechatDO.getId());
            }
            wxWechatSaasDao.save(wxWechatSaasDOs);
        }
        return Envelop.getSuccess(BaseRequestMapping.WeChat.api_success);
    }

    public WxWechatDO findWxWechatSingle(String wechatId){
        WxWechatDO wxWechatDO = wechatDao.findOne(wechatId);
        return wxWechatDO;
    }

    public Boolean findWxWechatExist(String name){
        List<WxWechatDO> list = wechatDao.findByName(name);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }


    //====================微信与租户管理end=======================

    //====================图文素材管理============================

    public MixEnvelop findWechatCombo(){
        String sql ="SELECT t.id,t.`name`," +
                "t.app_origin_id AS appOriginId," +
                "t.public_type AS publicType " +
                "from wx_wechat t";
        List<WxComboVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(WxComboVO.class));
        return MixEnvelop.getSuccessList(BaseRequestMapping.WeChat.api_success,list);
    }

    public MixEnvelop findWechatImgGroup(String wechatId,String scene,Integer page,Integer size){

        String totalSql ="SELECT COUNT(1) AS total from wx_graphic_scene g WHERE g.wechat_id ='"+wechatId+"'";

        if(StringUtils.isNotBlank(scene)){
            totalSql+=" AND g.scene like'%"+scene+"%' ";
        }

        List<Map<String, Object>> rstotal = jdbcTemplate.queryForList(totalSql);
        Long count = 0L;
        if (rstotal != null && rstotal.size() > 0) {
            count = (Long) rstotal.get(0).get("total");
        }

        String sql = "SELECT " +
                " g.id,g.wechat_id AS wechatId,g.scene " +
                " FROM " +
                " wx_graphic_scene g " +
                " WHERE " +
                " g.wechat_id = '"+wechatId+"' ";

        if(StringUtils.isNotBlank(scene)){
            sql+= " AND g.scene like'%"+scene+"%'" ;
        }

        sql+= " LIMIT  " + (page - 1) * size + "," + size + "";

        List<WxGraphicSceneVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(WxGraphicSceneVO.class));

        return MixEnvelop.getSuccessListWithPage(BaseRequestMapping.WeChat.api_success, list, page, size, count);
    }

    public Envelop createImgGroup(WxGraphicSceneDO wxGraphicSceneDO){
        wxGraphicSceneDao.save(wxGraphicSceneDO);
        return Envelop.getSuccess(BaseRequestMapping.WeChat.api_success);
    }

    public Boolean findImgGroupExist(String wechatId,String scene){
        List<WxGraphicSceneDO> list = wxGraphicSceneDao.findByWechatIdAndScene(wechatId,scene);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }

    public Envelop updateImgGroup(String id,String scene){
        WxGraphicSceneDO wxGraphicSceneDO = wxGraphicSceneDao.findOne(id);
        List<WxGraphicSceneGroupDO> list = wxGraphicSceneGroupDao.findByWechatIdAndScene(wxGraphicSceneDO.getWechatId(),wxGraphicSceneDO.getScene());

        if(list!=null&&list.size()>0){
            for(WxGraphicSceneGroupDO group:list){
                group.setScene(scene);
            }
            wxGraphicSceneGroupDao.save(list);
        }
        wxGraphicSceneDO.setScene(scene);
        wxGraphicSceneDao.save(wxGraphicSceneDO);
        return Envelop.getSuccess(BaseRequestMapping.WeChat.api_success);
    }

    public Envelop deleteImgGroup(String id){
        WxGraphicSceneDO wxGraphicSceneDO = wxGraphicSceneDao.findOne(id);
        List<WxGraphicSceneGroupDO> list = wxGraphicSceneGroupDao.findByWechatIdAndScene(wxGraphicSceneDO.getWechatId(),wxGraphicSceneDO.getScene());
        if(list!=null&&list.size()>0){
            wxGraphicSceneGroupDao.delete(list);
        }
        wxGraphicSceneDao.delete(wxGraphicSceneDO);
        return Envelop.getSuccess(BaseRequestMapping.WeChat.api_success);
    }

    public Envelop saveImg(WxGraphicMessageDO wxGraphicMessageDO){
        wxGraphicMessageDao.save(wxGraphicMessageDO);
        return Envelop.getSuccess(BaseRequestMapping.WeChat.api_success);
    }

    public MixEnvelop findImg(String wechatId,String title,String scene,Integer page,Integer size){

        String sqlTotal = "SELECT " +
                " COUNT(1) AS total " +
                " FROM " +
                " wx_graphic_message m ";
                if(StringUtils.isNotBlank(scene)){
                    sqlTotal+= " JOIN wx_graphic_scene_group g ON g.graphic_id = m.id ";
                }
        sqlTotal+= " WHERE " +
                " m.wechat_id = '"+wechatId+"' " +
                " AND m.status =1" ;
                if(StringUtils.isNotBlank(title)){
                    sqlTotal += " AND m.title LIKE '%"+title+"%' " ;
                }
                if(StringUtils.isNotBlank(scene)){
                    sqlTotal+= " AND g.scene = '"+scene+"'";
                }
        List<Map<String, Object>> rstotal = jdbcTemplate.queryForList(sqlTotal);
        Long count = 0L;
        if (rstotal != null && rstotal.size() > 0) {
            count = (Long) rstotal.get(0).get("total");
        }

        String sql = "SELECT " +
                " m.id, " +
                " m.wechat_id AS wechatId, " +
                " m.title, " +
                " m.description, " +
                " m.url, " +
                " m.pic_url AS picUrl, " +
                " m.`status` " +
                " FROM " +
                " wx_graphic_message m ";
        if(StringUtils.isNotBlank(scene)){
            sql+= " JOIN wx_graphic_scene_group g ON g.graphic_id = m.id ";
        }
        sql+=" WHERE m.wechat_id = '"+wechatId+"' " +
                " AND m.status =1" ;
        if(StringUtils.isNotBlank(title)){
            sql += " AND m.title LIKE '%"+title+"%' " ;
        }
        if(StringUtils.isNotBlank(scene)){
            sql+= " AND g.scene = '"+scene+"'";
        }
        sql+=" LIMIT  " + (page - 1) * size + "," + size + "";
        List<WxGraphicMessageVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(WxGraphicMessageVO.class));
        return MixEnvelop.getSuccessListWithPage(BaseRequestMapping.WeChat.api_success, list, page, size, count);
    }

    public WxGraphicMessageDO findGraphicMessageSingle(String id){
        return wxGraphicMessageDao.findOne(id);
    }

    public Envelop saveImgGroup(List<WxGraphicSceneGroupDO> groups){
        List<WxGraphicSceneGroupDO> gs = wxGraphicSceneGroupDao.findByWechatIdAndScene(groups.get(0).getWechatId(),groups.get(0).getScene());
        if(gs!=null&&gs.size()>0){
            wxGraphicSceneGroupDao.delete(gs);
        }
        wxGraphicSceneGroupDao.save(groups);
        return Envelop.getSuccess(BaseRequestMapping.WeChat.api_success);
    }

    public Envelop saveWxReplyScene(WxReplySceneDO wxReplySceneDO){
        wxReplySceneDao.save(wxReplySceneDO);
        return Envelop.getSuccess(BaseRequestMapping.WeChat.api_success);
    }

    public MixEnvelop findWxReplyScene(String wechatId,String msgType,String event,String content,Integer page,Integer size){
        String totalSql = "SELECT " +
                " COUNT(1) AS total " +
                " FROM " +
                " wx_reply_scene s " +
                " WHERE " +
                " s.wechat_id = '"+wechatId+"'";
        if(StringUtils.isNotBlank(msgType)){
            totalSql += " AND s.msg_type ='"+msgType+"'";
        }
        if(StringUtils.isNotBlank(event)){
            totalSql += " AND s.event ='"+event+"'";
        }
        if(StringUtils.isNotBlank(content)){
            totalSql += " AND s.content like '%"+content+"%'";
        }
        List<Map<String, Object>> rstotal = jdbcTemplate.queryForList(totalSql);
        Long count = 0L;
        if (rstotal != null && rstotal.size() > 0) {
            count = (Long) rstotal.get(0).get("total");
        }

        String sql = "SELECT " +
                " s.wechat_id AS wechatId," +
                " s.scene, " +
                " s.`status`, " +
                " s.id, " +
                " s.content, " +
                " s.app_origin_id AS appOriginId, " +
                " s.`event`, " +
                " s.msg_type AS msgType" +
                " FROM " +
                " wx_reply_scene s " +
                " WHERE " +
                " s.wechat_id = '"+wechatId+"'";
        if(StringUtils.isNotBlank(msgType)){
            sql += " AND s.msg_type ='"+msgType+"'";
        }
        if(StringUtils.isNotBlank(event)){
            sql += " AND s.event ='"+event+"'";
        }
        if(StringUtils.isNotBlank(content)){
            sql += " AND s.content like '%"+content+"%'";
        }
        sql+=" LIMIT  " + (page - 1) * size + "," + size + "";

        List<WxReplySceneVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(WxReplySceneVO.class));
        return MixEnvelop.getSuccessListWithPage(BaseRequestMapping.WeChat.api_success, list, page, size, count);
    }

    //===================图文素材管理end====================================

    //===================模板消息==========================================

    public Envelop saveWxTemp(WxTemplateDO wxTemplateDO){
        wxTemplateDao.save(wxTemplateDO);
        return Envelop.getSuccess(BaseRequestMapping.WeChat.api_success);
    }

    public MixEnvelop findWxtemp(String wechatId,Integer status,String name,String key,Integer page,Integer size){
        String totalSql = "SELECT " +
                " COUNT(1) AS total " +
                " FROM " +
                " wx_template t" +
                " WHERE t.wechat_id= '"+wechatId+"' ";
        if(status!=null){
            totalSql+= " AND t.status = "+ status;
        }
        if(StringUtils.isNotBlank(name)){
            totalSql+= " AND t.template_name ='"+name+"'";
        }
        if(StringUtils.isNotBlank(key)){
            totalSql+= " AND (t.template_id ='"+key+"' OR t.title like'%"+key+"%')";
        }
        List<Map<String, Object>> rstotal = jdbcTemplate.queryForList(totalSql);
        Long count = 0L;
        if (rstotal != null && rstotal.size() > 0) {
            count = (Long) rstotal.get(0).get("total");
        }

        String sql = "SELECT " +
                " t.id, " +
                " t.title, " +
                " t.wechat_id AS wechatId, " +
                " t.template_id AS templateId, " +
                " t.template_name AS templateName, " +
                " t.content, " +
                " t.`status` " +
                " FROM " +
                " wx_template t" +
                " WHERE t.wechat_id= '"+wechatId+"' ";
        if(status!=null){
            sql+= " AND t.status = "+ status;
        }
        if(StringUtils.isNotBlank(name)){
            sql+= " AND t.template_name ='"+name+"'";
        }
        if(StringUtils.isNotBlank(key)){
            sql+= " AND (t.template_id ='"+key+"' OR t.title like'%"+key+"%')";
        }
        sql+=" LIMIT  " + (page - 1) * size + "," + size + "";
        List<WxTemplateVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(WxTemplateVO.class));
        return MixEnvelop.getSuccessListWithPage(BaseRequestMapping.WeChat.api_success, list, page, size, count);
    }

    public Envelop saveWxTempConfig(WxTemplateConfigDO wxTemplateConfigDO){
        wxTemplateConfigDao.save(wxTemplateConfigDO);
        return Envelop.getSuccess(BaseRequestMapping.WeChat.api_success);
    }

    public MixEnvelop findWxTempConfigList(String wechatId,String scene,Integer page,Integer size){
        String totalSql ="SELECT " +
                " COUNT(1) AS total " +
                " FROM " +
                " wx_template_config g " +
                " WHERE " +
                " g.wechat_id ='"+wechatId+"'";
        if(StringUtils.isNotBlank(scene)){
            totalSql += " AND g.scene = '"+scene+"'";
        }
        List<Map<String, Object>> rstotal = jdbcTemplate.queryForList(totalSql);
        Long count = 0L;
        if (rstotal != null && rstotal.size() > 0) {
            count = (Long) rstotal.get(0).get("total");
        }

        String sql ="SELECT " +
                " g.id, " +
                " g.wechat_id AS wechatId, " +
                " g.template_id AS templateId, " +
                " g.template_name AS template_name, " +
                " g.scene, " +
                " g.scene_description AS sceneDescription, " +
                " g.`first`, " +
                " g.url, " +
                " g.remark, " +
                " g.keyword1, " +
                " g.keyword2, " +
                " g.keyword3, " +
                " g.keyword4, " +
                " g.keyword5, " +
                " g.keyword6, " +
                " g.keyword7, " +
                " g.create_time AS createTime, " +
                " g.create_user AS createUser, " +
                " g.create_user_name AS createUserName, " +
                " g.update_time AS updateTime, " +
                " g.update_user AS updateUser, " +
                " g.update_user_name AS updateUserName " +
                " FROM " +
                " wx_template_config g " +
                " WHERE " +
                " g.wechat_id ='"+wechatId+"'";
        if(StringUtils.isNotBlank(scene)){
            sql += " AND g.scene = '"+scene+"'";
        }
        sql+=" LIMIT  " + (page - 1) * size + "," + size + "";
        List<WxTemplateConfigVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(WxTemplateConfigVO.class));
        return MixEnvelop.getSuccessListWithPage(BaseRequestMapping.WeChat.api_success, list, page, size, count);
    }

    public WxTemplateConfigDO findWxTemplateConfig(String wechatId,String name,String scene){
        WxTemplateConfigDO wxTemplateConfigDO =  wxTemplateConfigDao.findByWechatIdAndTemplateNameAndScene(wechatId,name,scene);
        return wxTemplateConfigDO;
    }
    //===================模板消息end=======================================

    //===================微信统计==========================================

    public Envelop getusersummary(String wechatId,String beginDate,String endDate){
        String url ="https://api.weixin.qq.com/datacube/getusersummary?access_token="+wxAccessTokenService.getWxAccessTokenById(wechatId).getAccessToken();
        String param = "{ \n" +
                "    \"begin_date\": \""+beginDate+"\", \n" +
                "    \"end_date\": \""+endDate+"\"\n" +
                "}";
        String result = com.yihu.jw.util.wechat.wxhttp.HttpUtil.sendPost(url, param);
        return Envelop.getSuccess(result);
    }

    public Envelop getusercumulate(String wechatId,String beginDate,String endDate){
        String url ="https://api.weixin.qq.com/datacube/getusercumulate?access_token="+wxAccessTokenService.getWxAccessTokenById(wechatId).getAccessToken();
        String param = "{ \n" +
                "    \"begin_date\": \""+beginDate+"\", \n" +
                "    \"end_date\": \""+endDate+"\"\n" +
                "}";
        String result = com.yihu.jw.util.wechat.wxhttp.HttpUtil.sendPost(url, param);
        return Envelop.getSuccess(result);
    }

    //===================微信统计end=======================================

}
