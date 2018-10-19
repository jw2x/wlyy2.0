package com.yihu.jw.base.service.saas;

import com.yihu.jw.base.dao.dict.*;
import com.yihu.jw.base.dao.module.ModuleDao;
import com.yihu.jw.base.dao.module.SaasModuleDao;
import com.yihu.jw.base.dao.org.BaseOrgDao;
import com.yihu.jw.base.dao.role.RoleDao;
import com.yihu.jw.base.dao.saas.SaasDao;
import com.yihu.jw.base.dao.saas.SaasThemeDao;
import com.yihu.jw.base.dao.saas.SaasThemeExtendDao;
import com.yihu.jw.base.dao.system.SystemDictDao;
import com.yihu.jw.base.dao.system.SystemDictEntryDao;
import com.yihu.jw.base.dao.user.UserDao;
import com.yihu.jw.base.dao.user.UserRoleDao;
import com.yihu.jw.entity.base.dict.*;
import com.yihu.jw.entity.base.module.ModuleDO;
import com.yihu.jw.entity.base.module.SaasModuleDO;
import com.yihu.jw.entity.base.org.BaseOrgDO;
import com.yihu.jw.entity.base.role.RoleDO;
import com.yihu.jw.entity.base.saas.BaseEmailTemplateConfigDO;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.entity.base.saas.SaasThemeDO;
import com.yihu.jw.entity.base.saas.SaasThemeExtendDO;
import com.yihu.jw.entity.base.system.SystemDictEntryDO;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.jw.entity.base.user.UserRoleDO;
import com.yihu.mysql.query.BaseJpaService;
import com.yihu.utils.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service - SAAS
 * Created by progr1mmer on 2018/8/14.
 */
@Service
public class SaasService extends BaseJpaService<SaasDO, SaasDao> {

    @Autowired
    private SaasDao saasDao;
    @Autowired
    private UserDao userDao;
//    @Autowired
//    private SaasDefaultModuleFunctionDao saasDefaultModuleFunctionDao;
//    @Autowired
//    private SaasModuleFunctionDao roleModuleFunctionDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private BaseOrgDao baseOrgDao;
    @Autowired
    private SystemDictDao systemDictDao;
    @Autowired
    private SystemDictEntryDao systemDictEntryDao;
    @Autowired
    private DictMedicineDao dictMedicineDao;
    @Autowired
    private DictJobTitleDao dictJobTitleDao;
    @Autowired
    private DictIcd10Dao dictIcd10Dao;
    @Autowired
    private DictHealthProblemDao dictHealthProblemDao;
    @Autowired
    private DictDiseaseDao dictDiseaseDao;
    @Autowired
    private DictHospitalDeptDao dictHospitalDeptDao;
    @Autowired
    private SaasModuleDao saasModuleDao;
    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private SaasThemeDao saasThemeDao;
    @Autowired
    private SaasThemeExtendDao saasThemeExtendDao;
    @Value("${configDefault.saasId}")
    private String defaultSaasId;
    @Autowired
    private BaseEmailTemplateConfigService baseEmailTemplateConfigService;
    @Value("${spring.mail.username}")
    private String username;
    @Autowired
    JavaMailSender jms;


    /**
     * 默认租户管理员角色code
     */
    private final String roleCode = "saasAdmin";

    /**
     * 注册新增
     * @param saas
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SaasDO create(SaasDO saas){
        final SaasDO.Status staus = saas.getStatus();
        saas.setStatus(SaasDO.Status.auditPassed);
        saas = saasDao.save(saas);
        List<BaseOrgDO> orgDOList = saas.getOrgList();
        if(orgDOList!=null&&orgDOList.size()>0){
            String saasId = saas.getId();
            orgDOList.forEach(org->{
                BaseOrgDO orgDO = baseOrgDao.findByCodeAndSaasId(org.getCode(),defaultSaasId);
                org.setSaasid(saasId);
                org.setName(orgDO.getName());
                org.setCreateTime(new Date());
                org.setAddress(orgDO.getAddress());
                org.setAlias(orgDO.getAlias());
                org.setBrief(orgDO.getBrief());
                org.setCityCode(orgDO.getCityCode());
                org.setCityName(orgDO.getCityName());
                org.setDel(orgDO.getDel());
                org.setIntro(orgDO.getIntro());
                org.setLatitude(orgDO.getLatitude());
                org.setLegalperson(orgDO.getLegalperson());
                org.setLongitude(orgDO.getLongitude());
                org.setName(orgDO.getName());
                org.setOrgAdmin(orgDO.getOrgAdmin());
                org.setOrgUrl(orgDO.getOrgUrl());
                org.setPhoto(orgDO.getPhoto());
                org.setProvinceCode(orgDO.getProvinceCode());
                org.setProvinceName(orgDO.getProvinceName());
                org.setQrcode(orgDO.getQrcode());
                org.setSpell(orgDO.getSpell());
                org.setStreetCode(orgDO.getStreetCode());
                org.setStreetName(orgDO.getStreetName());
                org.setTownCode(orgDO.getTownCode());
                org.setTownName(orgDO.getTownName());
                org.setType(orgDO.getType());
            });
        }
        baseOrgDao.save(orgDOList);

        //用户信息初始化
        UserDO userDO = new UserDO();
        userDO.setEmail(saas.getEmail());
        userDO.setMobile(saas.getMobile());
        userDO.setName(saas.getManagerName());
        userDO.setUsername(userDO.getEmail());

        saasAudit(saas, userDO);
        send(saas);
        saas.setStatus(staus);
        saasDao.save(saas);
        return saas;
    }

    /**
     * 发送邮件
     * @param saasDO
     */
    public void send(SaasDO saasDO) {
        try {
            SaasDO.Status status = saasDO.getStatus();
            String password = saasDO.getMobile().substring(0, 6);
            BaseEmailTemplateConfigDO baseEmailTemplateConfigDO = baseEmailTemplateConfigService.findByCode(status.name());
            if (null == baseEmailTemplateConfigDO) {
                return;
            }
            //建立邮件消息
            SimpleMailMessage mainMessage = new SimpleMailMessage();
            //发送者
            mainMessage.setFrom(username);
            //接收者
            mainMessage.setTo(saasDO.getEmail());
            //发送的标题
            mainMessage.setSubject(baseEmailTemplateConfigDO.getTemplateName());
            //发送的内容
            StringBuffer content = new StringBuffer();
            content.append(baseEmailTemplateConfigDO.getFirst() + "\n").append(baseEmailTemplateConfigDO.getKeyword1() + "\n");
            content.append(baseEmailTemplateConfigDO.getKeyword2() + "\n");
            if (status.equals(SaasDO.Status.auditPassed)) {
                //账号
                content.append(baseEmailTemplateConfigDO.getKeyword3() + saasDO.getMobile() + "\n");
                //密码
                content.append(baseEmailTemplateConfigDO.getKeyword4() + password + "\n");
            } else if (status.equals(SaasDO.Status.auditNotPassed)) {
                //审核未通过的原因
                content.append(saasDO.getAuditFailedReason() + "\n");
            }
            content.append(baseEmailTemplateConfigDO.getKeyword5() + baseEmailTemplateConfigDO.getUrl() + "\n");
            content.append(baseEmailTemplateConfigDO.getRemark());
            mainMessage.setText(content.toString());
            jms.send(mainMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册修改
     * @param saas
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SaasDO create(SaasDO saas,SaasDO oldSaas){
        oldSaas.setStatus(SaasDO.Status.auditWait);
        oldSaas.setEmail(saas.getEmail());
        oldSaas.setMobile(saas.getMobile());
        oldSaas.setManagerName(saas.getManagerName());
        oldSaas.setName(saas.getName());
        oldSaas.setOrganizationCode(saas.getOrganizationCode());
        oldSaas.setBusinessLicense(saas.getBusinessLicense());
        oldSaas.setType(saas.getType());
        saasDao.save(oldSaas);
        return oldSaas;
    }

    /**
     * 修改
     * @param saas
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SaasDO updateSaas(SaasDO saas,SaasDO oldSaas,UserDO userDO){
        String saasId = saas.getId();
        oldSaas.setEmail(saas.getEmail());
        oldSaas.setMobile(saas.getMobile());
        oldSaas.setManagerName(saas.getManagerName());
        oldSaas.setName(saas.getName());
        oldSaas.setStatus(saas.getStatus());
        oldSaas.setOrganizationCode(saas.getOrganizationCode());
        oldSaas.setBusinessLicense(saas.getBusinessLicense());

        userDO.setEmail(saas.getEmail());
        userDO.setMobile(saas.getMobile());
        userDO.setName(saas.getManagerName());
        baseOrgDao.deleteBySaasId(saasId);
        List<BaseOrgDO> orgDOList = saas.getOrgList();
        if(orgDOList!=null&&orgDOList.size()>0){
            orgDOList.forEach(org->{
                BaseOrgDO orgDO = baseOrgDao.findByCodeAndSaasId(org.getCode(),defaultSaasId);
                org.setSaasid(saasId);
                org.setName(orgDO.getName());
                org.setCreateTime(new Date());
                org.setAddress(orgDO.getAddress());
                org.setAlias(orgDO.getAlias());
                org.setBrief(orgDO.getBrief());
                org.setCityCode(orgDO.getCityCode());
                org.setCityName(orgDO.getCityName());
                org.setDel(orgDO.getDel());
                org.setIntro(orgDO.getIntro());
                org.setLatitude(orgDO.getLatitude());
                org.setLegalperson(orgDO.getLegalperson());
                org.setLongitude(orgDO.getLongitude());
                org.setName(orgDO.getName());
                org.setOrgAdmin(orgDO.getOrgAdmin());
                org.setOrgUrl(orgDO.getOrgUrl());
                org.setPhoto(orgDO.getPhoto());
                org.setProvinceCode(orgDO.getProvinceCode());
                org.setProvinceName(orgDO.getProvinceName());
                org.setQrcode(orgDO.getQrcode());
                org.setSpell(orgDO.getSpell());
                org.setStreetCode(orgDO.getStreetCode());
                org.setStreetName(orgDO.getStreetName());
                org.setTownCode(orgDO.getTownCode());
                org.setTownName(orgDO.getTownName());
                org.setType(orgDO.getType());
            });
        }
        baseOrgDao.save(orgDOList);
        saasDao.save(oldSaas);
        userDao.save(userDO);
        return oldSaas;
    }

    /**
     * 系统配置
     * @param saasDO
     */
    @Transactional(rollbackFor = Exception.class)
    public SaasDO saveSystemConfig(SaasDO saasDO){

        SaasDO oldSaas = saasDao.findById(saasDO.getId());
        oldSaas.setSystemName(saasDO.getSystemName());
        oldSaas.setLogo(saasDO.getLogo());
        oldSaas.setAreaNumber(saasDO.getAreaNumber());
        List<SaasModuleDO> saasModuleDOList = saasDO.getSaasModuleList();
        saasModuleDao.deleteBySaasId(saasDO.getId());
        saasModuleDOList.forEach(saasModuleDO -> {
            ModuleDO moduleDO = moduleDao.findOne(saasModuleDO.getModuleId());
            saasModuleDO.setSaasId(saasDO.getId());
            saasModuleDO.setDel(moduleDO.getDel());
            saasModuleDO.setCreateTime(new Date());
            saasModuleDO.setIsEnd(moduleDO.getIsEnd());
            saasModuleDO.setIsMust(moduleDO.getIsMust());
            saasModuleDO.setName(moduleDO.getName());
            saasModuleDO.setParentModuleId(moduleDO.getParentId());
            saasModuleDO.setRemark(moduleDO.getRemark());
            saasModuleDO.setStatus(moduleDO.getStatus());
            saasModuleDO.setType(moduleDO.getType());
            saasModuleDO.setUrl(moduleDO.getUrl());
        });
        saasDao.save(oldSaas);
        saasModuleDao.save(saasModuleDOList);
        return oldSaas;
    }

    public void updateStatus(String id,SaasDO.Status status){
        SaasDO saasDO = findById(id);
        saasDO.setStatus(status);
        saasDao.save(saasDO);
    }

    /**
     * 主题风格
     * @param saasDO
     */
    @Transactional(rollbackFor = Exception.class)
    public void createThemeConfig(SaasDO saasDO){
        SaasDO oldSaas = saasDao.findById(saasDO.getId());
        oldSaas.setThemeColor(saasDO.getThemeColor());
        List<SaasThemeDO> themeDOList = saasDO.getSaasThemeList();
        List<SaasThemeExtendDO> themeExtendDOList = new ArrayList<>(16);
        themeDOList.forEach(saasThemeDO -> {
            String themeId = getCode();
            saasThemeDO.setSaasId(saasDO.getId());
            saasThemeDO.setId(themeId);
            if(saasThemeDO.getThemeExtendList()!=null&&saasThemeDO.getThemeExtendList().size()>0){
                saasThemeDO.getThemeExtendList().forEach(saasThemeExtendDO -> {
                    saasThemeExtendDO.setThemeId(themeId);
                    themeExtendDOList.add(saasThemeExtendDO);
                });
            }
        });

        saasDao.save(oldSaas);
        saasThemeDao.save(themeDOList);
        saasThemeExtendDao.save(themeExtendDOList);
    }

    @Transactional
    public void delete(String ids) {
        for (String id : ids.split(",")) {
            SaasDO saas = saasDao.findById(id);
            saas.setStatus(SaasDO.Status.delete);
        }
    }

    public SaasDO findById(String id){
        return saasDao.findById(id);
    }

    public SaasDO findByName(String name){
        return saasDao.findByName(name);
    }

    /**
     *审核通过后，初始化租户信息
     * @param saas
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SaasDO saasAudit(SaasDO saas, UserDO user) {
        //初始化角色
        RoleDO roleDO = roleDao.findByCode(roleCode);
        //初始化租户管理员
        user.setEnabled(true);
        user.setLocked(false);
        user.setSalt(randomString(5));
        user.setName(user.getEmail());
        String password = user.getPassword();
        //密码默认手机号后6位
        if (StringUtils.isEmpty(password)) {
            password = user.getMobile().substring(0, 6);
        }
        user.setPassword(MD5.md5Hex(password + "{" + user.getSalt() + "}"));
        //初始化管理员角色
        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setRoleId(roleDO.getId());
        userDao.save(user);
        userRoleDO.setUserId(user.getId());
        userRoleDao.save(userRoleDO);
        saas.setManager(user.getId());
        saas.setAppId(getCode());
        saas.setAppSecret(getCode());
        saas = saasDao.save(saas);
        String saasId = saas.getId();
        //系统字典项
        List<SystemDictEntryDO> systemDictEntryDOList = systemDictEntryDao.findBySaasId(defaultSaasId);
        List<SystemDictEntryDO> dictEntryDOList = new ArrayList<>(systemDictEntryDOList.size());
        systemDictEntryDOList.forEach(dict->{
            SystemDictEntryDO systemDictEntryDO = new SystemDictEntryDO();
            systemDictEntryDO.setCode(dict.getCode());
            systemDictEntryDO.setSort(dict.getSort());
            systemDictEntryDO.setSaasId(saasId);
            systemDictEntryDO.setPyCode(dict.getPyCode());
            systemDictEntryDO.setDictCode(dict.getDictCode());
            systemDictEntryDO.setRemark(dict.getRemark());
            systemDictEntryDO.setValue(dict.getValue());
            dictEntryDOList.add(systemDictEntryDO);
        });
        //药品字典
        List<DictMedicineDO> dictMedicineDOList = dictMedicineDao.findBySaasId(defaultSaasId);
        List<DictMedicineDO> medicineDOList = new ArrayList<>(dictMedicineDOList.size());
        dictMedicineDOList.forEach(dict->{
            DictMedicineDO dictMedicineDO = new DictMedicineDO();
            dictMedicineDO.setCode(dict.getCode());
            dictMedicineDO.setDosageForm(dict.getDosageForm());
            dictMedicineDO.setIndication(dict.getIndication());
            dictMedicineDO.setName(dict.getName());
            dictMedicineDO.setPackingSpecification(dict.getPackingSpecification());
            dictMedicineDO.setSequence(dict.getSequence());
            dictMedicineDO.setSpecification(dict.getSpecification());
            dictMedicineDO.setSpellCode(dict.getSpellCode());
            dictMedicineDO.setSubjectCode(dict.getSubjectCode());
            dictMedicineDO.setWbzxCode(dict.getWbzxCode());
            dictMedicineDO.setSaasId(saasId);
            dictMedicineDO.setStorageConditions(dict.getStorageConditions());
            medicineDOList.add(dictMedicineDO);
        });
        //职称字典
        List<DictJobTitleDO> dictJobTitleDOList = dictJobTitleDao.findBySaasId(defaultSaasId);
        List<DictJobTitleDO> jobTitleDOList = new ArrayList<>(dictJobTitleDOList.size());
        dictJobTitleDOList.forEach(dict->{
            DictJobTitleDO jobTitleDO = new DictJobTitleDO();
            jobTitleDO.setSaasId(saasId);
            jobTitleDO.setName(dict.getName());
            jobTitleDO.setCode(dict.getCode());
            jobTitleDO.setCreateTime(new Date());
            jobTitleDOList.add(jobTitleDO);
        });
        //icd10字典
        List<DictIcd10DO> dictIcd10DOList = dictIcd10Dao.findBySaasId(defaultSaasId);
        List<DictIcd10DO> icd10DOList = new ArrayList<>(dictIcd10DOList.size());
        dictIcd10DOList.forEach(dict->{
            DictIcd10DO icd10DO = new DictIcd10DO();
            icd10DO.setSaasId(saasId);
            icd10DO.setName(dict.getName());
            icd10DO.setCode(dict.getCode());
            icd10DO.setCreateTime(new Date());
            icd10DO.setDescription(dict.getDescription());
            icd10DOList.add(icd10DO);
        });
        //健康问题字典
        List<DictHealthProblemDO> dictHealthProblemDOList = dictHealthProblemDao.findBySaasId(defaultSaasId);
        List<DictHealthProblemDO> healthProblemDOList = new ArrayList<>(dictHealthProblemDOList.size());
        dictHealthProblemDOList.forEach(dict->{
            DictHealthProblemDO healthProblemDO = new DictHealthProblemDO();
            healthProblemDO.setSaasId(saasId);
            healthProblemDO.setName(dict.getName());
            healthProblemDO.setCode(dict.getCode());
            healthProblemDO.setCreateTime(new Date());
            healthProblemDO.setDescription(dict.getDescription());
            healthProblemDO.setChronicFlag(dict.getChronicFlag());
            healthProblemDOList.add(healthProblemDO);
        });
        //病种字典
        List<DictDiseaseDO> dictDiseaseDOList = dictDiseaseDao.findBySaasId(defaultSaasId);
        List<DictDiseaseDO> diseaseDOList = new ArrayList<>(dictDiseaseDOList.size());
        dictDiseaseDOList.forEach(dict->{
            DictDiseaseDO diseaseDO = new DictDiseaseDO();
            diseaseDO.setSaasId(saasId);
            diseaseDO.setName(dict.getName());
            diseaseDO.setCode(dict.getCode());
            diseaseDO.setCreateTime(new Date());
            diseaseDOList.add(diseaseDO);
        });
        //科室字典
        List<DictHospitalDeptDO> dictHospitalDeptDOList = dictHospitalDeptDao.findBySaasId(defaultSaasId);
        List<DictHospitalDeptDO> hospitalDeptDOList = new ArrayList<>(dictHospitalDeptDOList.size());
        dictHospitalDeptDOList.forEach(dict->{
            DictHospitalDeptDO deptDO = new DictHospitalDeptDO();
            deptDO.setSaasId(saasId);
            deptDO.setName(dict.getName());
            deptDO.setCode(dict.getCode());
            deptDO.setCreateTime(new Date());
            hospitalDeptDOList.add(deptDO);
        });

        //保存数据
//        systemDictDao.save(dictDOList);
        systemDictEntryDao.save(dictEntryDOList);
        dictMedicineDao.save(medicineDOList);
        dictJobTitleDao.save(jobTitleDOList);
        dictIcd10Dao.save(icd10DOList);
        dictHealthProblemDao.save(healthProblemDOList);
        dictDiseaseDao.save(diseaseDOList);
        dictHospitalDeptDao.save(hospitalDeptDOList);
        return saas;
    }

}
