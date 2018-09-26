package com.yihu.jw.base.service.wx;

import com.yihu.jw.restmodel.base.saas.SaasVO;
import com.yihu.jw.restmodel.base.wx.WxSaasVO;
import com.yihu.jw.restmodel.base.wx.WxWechatVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by trick on 2017/5/20 0020.
 */
@Service
@Transactional
public class WechatService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
            sql += " AND w.`status` = " + status + "";
        }
        if (StringUtils.isNotBlank(saasName)) {
            sql += " AND bs.`name` like '%" + saasName + "%'";
        }
        sql += "LIMIT " + (page - 1) * size + "," + size + "";
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

        return MixEnvelop.getSuccessListWithPage(SpecialistMapping.api_success, list, page, size, count);
    }

    public List<WxSaasVO> getWxSaasVOs(String id){
        String sql ="SELECT " +
                " bs.`name` AS saasName, " +
                " bs.id AS saasid" +
                " FROM " +
                " wx_wechat_saas s " +
                " JOIN base_saas bs ON s.saas_id = s.saas_id " +
                " WHERE " +
                " s.wechat_id = '"+id+"'";
        List<WxSaasVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(WxSaasVO.class));
        return list;
    }
}
