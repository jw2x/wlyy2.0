package com.yihu.jw.base.service.patient;

import com.yihu.jw.base.dao.patient.BasePatientDao;
import com.yihu.jw.base.dao.patient.BasePatientFamilyMemberDao;
import com.yihu.jw.entity.base.patient.BasePatientDO;
import com.yihu.jw.entity.base.patient.BasePatientFamilyMemberDO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.util.idcard.IdCardUtil;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Trick on 2018/8/31.
 */
@Service
@Transactional
public class PatientFamilyMemberService extends BaseJpaService<BasePatientFamilyMemberDO,BasePatientFamilyMemberDao> {

    @Autowired
    private BasePatientFamilyMemberDao familyMemberDao;

    @Autowired
    private BasePatientDao basePatientDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String createFamilyMember(String patient,String member,Integer relation)throws Exception{

        BasePatientDO pDo = basePatientDao.findByIdAndDel(patient,"1");
        BasePatientDO mDo = basePatientDao.findByIdAndDel(patient,"1");

        BasePatientFamilyMemberDO family1 = new BasePatientFamilyMemberDO();

        //添加正向关系
        family1.setPatient(patient);
        family1.setFamilyMember(member);
        family1.setFamilyRelation(relation);
        //默认添加已经授权记录，用户同意授权之后调用此接口
        family1.setIsAuthorize(1);

        //添加反向关系
        BasePatientFamilyMemberDO family2 = new BasePatientFamilyMemberDO();

        family1.setPatient(patient);
        family1.setFamilyMember(member);
        family1.setFamilyRelation(familyRelationTrans(mDo,relation));
        //默认添加已经授权记录，用户同意授权之后调用此接口
        family1.setIsAuthorize(1);

        return "1";
    }

    public String delFamilyMember(String patient,String member)throws Exception{
        BasePatientFamilyMemberDO family1 = familyMemberDao.findByPatientAndFamilyMember(patient,member);
        BasePatientFamilyMemberDO family2 = familyMemberDao.findByPatientAndFamilyMember(member,patient);
        familyMemberDao.delete(family1);
        familyMemberDao.delete(family2);
        return "1";
    }

    /**
     * 家庭关系转换
     *
     * @param patient  居民
     * @param relation 关系 1父亲 2母亲 3老公 4老婆 5儿子 6女儿 7其他
     * @return
     */
    public int familyRelationTrans(BasePatientDO patient, Integer relation) throws Exception {
        int relationTrans = 0;

        switch (relation) {
            case 1:
            case 2:
                if (patient.getSex() == 1) {
                    relationTrans = 5;
                } else if (patient.getSex() == 2) {
                    relationTrans = 6;
                } else {
                    relationTrans = 0;
                }
                if (relationTrans == 0) {
                    if (IdCardUtil.getSexForIdcard(StringUtils.isEmpty(patient.getIdcard()) ? "" : patient.getIdcard()).equals("1")) {
                        relationTrans = 6;
                    } else if (IdCardUtil.getSexForIdcard(StringUtils.isEmpty(patient.getIdcard()) ? "" : patient.getIdcard()).equals("2")) {
                        relationTrans = 5;
                    }
                }
                break;
            case 3:
                relationTrans = 4;
                break;
            case 4:
                relationTrans = 3;
                break;
            case 5:
            case 6:
                if (patient.getSex() == 1) {
                    relationTrans = 1;
                } else if (patient.getSex() == 2) {
                    relationTrans = 2;
                } else {
                    relationTrans = 0;
                }
                if (relationTrans == 0) {
                    if (IdCardUtil.getSexForIdcard(StringUtils.isEmpty(patient.getIdcard()) ? "" : patient.getIdcard()).equals("1")) {
                        relationTrans = 2;
                    } else if (IdCardUtil.getSexForIdcard(StringUtils.isEmpty(patient.getIdcard()) ? "" : patient.getIdcard()).equals("2")) {
                        relationTrans = 1;
                    }
                }
                break;
        }

        return relationTrans;
    }
}
