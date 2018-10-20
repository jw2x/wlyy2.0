
-- 机构信息表 
drop table IF EXISTS `base_org`;
CREATE TABLE `base_org` (
  `id` varchar(50) NOT NULL COMMENT 'uuid,uuid唯一标识,也是机构code',
  `saasid` varchar(50) NOT NULL COMMENT 'saas化配置',
  `province_code` varchar(50) DEFAULT NULL COMMENT '省代码',
  `province_name` varchar(50) DEFAULT NULL COMMENT '省名称',
  `city_code` varchar(50) DEFAULT NULL COMMENT '市代码',
  `city_name` varchar(50) DEFAULT NULL COMMENT '市名称',
  `town_code` varchar(50) DEFAULT NULL COMMENT '区县代码',
  `town_name` varchar(50) DEFAULT NULL COMMENT '区县名称',
  `street_code` varchar(50) DEFAULT NULL COMMENT '街道代码',
  `street_name` varchar(50) DEFAULT NULL COMMENT '街道名称',
  `name` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `alias` varchar(10) DEFAULT NULL COMMENT '机构别名',
  `spell` varchar(20) DEFAULT NULL COMMENT '机构名称拼音首字母',
  `type` char(2) DEFAULT '1' COMMENT '机构类型: 1.  医疗机构2.  企事业单位3.  政府机关4.  社会团体 5.药店 0.  部门 6.单位或者独立子公司7.基层机构 8.专业公共机构',
  `brief` varchar(300) DEFAULT NULL COMMENT '机构简介',
  `address` varchar(300) DEFAULT NULL COMMENT '机构详细地址',
  `photo` varchar(200) DEFAULT NULL COMMENT '机构图片',
  `longitude` varchar(10) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(10) DEFAULT NULL COMMENT '纬度',
  `legalperson` varchar(50) DEFAULT NULL COMMENT '法人',
  `org_admin` varchar(50) DEFAULT NULL COMMENT '机构管理员',
  `org_url` varchar(200) DEFAULT NULL COMMENT '机构网址',
  `intro` text COMMENT '机构简介',
  `qrcode` varchar(30) DEFAULT NULL COMMENT '机构二维码',
  `del` varchar(1) DEFAULT '1' COMMENT '作废标识，1正常，0作废',
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人名',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机构信息（医院）';

-- 机构Saas关联表
drop table IF EXISTS `base_org_saas`;
CREATE TABLE `base_org_saas` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT 'id，自增长',
  `saasid` varchar(50) NOT NULL COMMENT 'saas化配置',
  `org_code` varchar(50) NOT NULL COMMENT '机构标识',
  PRIMARY KEY (`id`),
  KEY `idx_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机构与Saas关联信息';

-- 机构管理员关联表
drop table IF EXISTS `base_org_user`;
CREATE TABLE `base_org_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT 'id，自增长',
  `org_code` varchar(50) NOT NULL COMMENT '机构标识，base_org里的code',
  `user_account` varchar(50) NOT NULL COMMENT '用户账号，base_user表里的username',
  PRIMARY KEY (`id`),
  KEY `idx_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机构与机构管理员关联信息';

-- 医生信息表
drop table IF EXISTS `base_doctor`;
CREATE TABLE `base_doctor` (
  `id` varchar(50) NOT NULL COMMENT 'uuid,uuid唯一标识',
  `org_code` varchar(100) DEFAULT NULL COMMENT '机构id',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` char(2) DEFAULT NULL COMMENT '性别（1男，2女） 用国家标准字典',
  `expertise` varchar(300) DEFAULT NULL COMMENT '医生专长',
  `introduce` varchar(1500) DEFAULT NULL COMMENT '医生介绍',
  `idcard` varchar(20) DEFAULT NULL COMMENT ' 身份证',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `photo` varchar(100) DEFAULT NULL COMMENT '头像http地址',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `qrcode` varchar(30) DEFAULT NULL COMMENT '医生二维码',
  `province_code` varchar(50) DEFAULT NULL COMMENT '省代码',
  `province_name` varchar(50) DEFAULT NULL COMMENT '省名称',
  `city_code` varchar(50) DEFAULT NULL COMMENT '市代码',
  `city_name` varchar(50) DEFAULT NULL COMMENT '市名称',
  `town_code` varchar(50) DEFAULT NULL COMMENT '区县代码',
  `town_name` varchar(50) DEFAULT NULL COMMENT '区县名称',
  `street_code` varchar(50) DEFAULT NULL COMMENT '街道代码',
  `street_name` varchar(50) DEFAULT NULL COMMENT '街道名称',
  `iscertified` varchar(1) DEFAULT NULL DEFAULT '0' COMMENT '资格是否认证通过，1是，0否',
  `is_famous` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否是名医，1是，0否',
  `is_password_prompt` char(1) DEFAULT NULL COMMENT '是否提示设置密码  1 提示过 0未提示',
  `spell` varchar(10) DEFAULT NULL COMMENT '名称拼音首字母',
  `certified_overtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'CA证书过期时间',
  `certificate_num` varchar(100) DEFAULT NULL COMMENT 'CA证书编号',
  `openid` varchar(50) DEFAULT NULL  COMMENT '用户微信openid',
  `del` varchar(1) DEFAULT '1' COMMENT '作废标识，1正常，0作废',
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人名',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生基本信息';

-- 医生角色字典表
drop table IF EXISTS `base_doctor_role_dict`;
CREATE TABLE `base_doctor_role_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `code` varchar(50) NOT NULL COMMENT '角色code',
  `name` varchar(50) NOT NULL COMMENT '角色名称：全科医生、专科医生、健康管理师、管理员等',
  `del` varchar(1) DEFAULT '1' COMMENT '作废标识，1正常，0作废',
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人名',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_doctor_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生角色字典';

-- 医生角色关联表
drop table IF EXISTS `base_doctor_role`;
CREATE TABLE `base_doctor_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，关联表',
  `doctor_code` varchar(50) NOT NULL COMMENT '医生code',
  `role_code` varchar(50) NOT NULL COMMENT '医生角色id',
  `del` varchar(1) DEFAULT '1' COMMENT '作废标识，1正常，0作废',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生角色关联信息';

-- 医生执业表（一个医生可在多个医院供职，角色等）
drop table IF EXISTS `base_doctor_hospital`;
CREATE TABLE `base_doctor_hospital` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `hosp_code` varchar(50) NOT NULL COMMENT '医院标识',
  `hosp_name` varchar(50) NOT NULL COMMENT '医院名称',
  `doctor_duty_code` varchar(50) NOT NULL COMMENT '医生职务标识',
  `doctor_duty_name` varchar(50) NOT NULL COMMENT '医院职务名称',
  `job_title_code` varchar(50) NOT NULL COMMENT '职称代码',
  `job_title_name` varchar(50) NOT NULL COMMENT '职称名称',
  `del` varchar(1) DEFAULT '1' COMMENT '作废标识，1正常，0作废',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生执业信息';

-- 居民信息表
drop table IF EXISTS `base_patient`;
CREATE TABLE `base_patient` (
  `id` varchar(50) NOT NULL   COMMENT 'uuid,uuid唯一标识',
  `saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id',
  `idcard` varchar(50) NOT NULL COMMENT '身份证号',
  `password` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `salt` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` varchar(100) DEFAULT NULL COMMENT '性别，1男，2女',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `phone` varchar(200) DEFAULT NULL COMMENT '联系电话',
  `ssc` varchar(50) DEFAULT NULL COMMENT '社保卡号',
  `photo` varchar(100) DEFAULT NULL COMMENT '头像http地址',
  `province_code` varchar(50) DEFAULT NULL COMMENT '省代码',
  `province_name` varchar(50) DEFAULT NULL COMMENT '省名称',
  `city_code` varchar(50) DEFAULT NULL COMMENT '市代码',
  `city_name` varchar(50) DEFAULT NULL COMMENT '市名称',
  `town_code` varchar(50) DEFAULT NULL COMMENT '区县代码',
  `town_name` varchar(50) DEFAULT NULL COMMENT '区县名称',
  `street_code` varchar(50) DEFAULT NULL COMMENT '街道代码',
  `street_name` varchar(50) DEFAULT NULL COMMENT '街道名称',
  `live_province_code` varchar(50) DEFAULT NULL COMMENT '居住省代码',
  `live_province_name` varchar(50) DEFAULT NULL COMMENT '居住省名称',
  `live_city_code` varchar(50) DEFAULT NULL COMMENT '居住市代码',
  `live_city_name` varchar(50) DEFAULT NULL COMMENT '居住市名称',
  `live_town_code` varchar(50) DEFAULT NULL COMMENT '居住区县代码',
  `live_town_name` varchar(50) DEFAULT NULL COMMENT '居住区县名称',
  `live_street_code` varchar(50) DEFAULT NULL COMMENT '居住街道代码',
  `live_street_name` varchar(50) DEFAULT NULL COMMENT '居住街道名称',
  `committee_code` varchar(50) DEFAULT NULL COMMENT '居住居委会代码',
  `committee_name` varchar(50) DEFAULT NULL COMMENT '居住居委会名称',
  `disease` varchar(100) DEFAULT NULL COMMENT '疾病类型，0健康，1高血压，2糖尿病，3高血压+糖尿病',
  `disease_condition` varchar(100) DEFAULT NULL COMMENT '病情：0绿标，1黄标，2红标，3重点关注,',
  `points` varchar(100) DEFAULT NULL COMMENT '总积分',
  `record_amount` varchar(100) DEFAULT NULL COMMENT '病历总数',
  `openid` varchar(50) DEFAULT NULL COMMENT '微信编号',
  `patient_status` varchar(100) DEFAULT NULL COMMENT '用户状态：1正常，0禁用，-1恶意注册，2审核中',
  `mobile_remarks` varchar(200) DEFAULT NULL COMMENT '联系方式备注【基卫】',
  `openid_time` datetime DEFAULT NULL COMMENT '第一次添加open的时间',
  `principal_code` varchar(50) DEFAULT NULL COMMENT '绑定电子社保卡主体（共济为操作人code）',
  `sicard_status` varchar(100) DEFAULT NULL COMMENT '是否绑定电子社保卡 （0否 1是）',
  `sicard_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '电子社保卡绑定时间',
  `is_wxtag` int(1) DEFAULT NULL COMMENT '是否分配过微信标签',
  `wxtagid` varchar(100) DEFAULT NULL COMMENT '微信tagId',
  `standard_status` tinyint(4) DEFAULT NULL COMMENT '居民预警状态：0为标准，1为预警状态',
  `medicare_number` varchar(50) DEFAULT NULL COMMENT '医疗保险号',
  `unionid` varchar(50) DEFAULT NULL COMMENT 'unionId 开发平台唯一标识',
  `del` varchar(1) DEFAULT '1' COMMENT '作废标识，1正常，0作废',
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人名',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_patient_idcard` (`idcard`),
  KEY `idx_mobile` (`mobile`) USING BTREE,
  KEY `idx_openid` (`openid`) USING BTREE,
  KEY `idx_name` (`name`),
  KEY `idx_principal_code` (`principal_code`) USING BTREE,
  KEY `idx_unionid` (`unionid`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='居民信息（居民就是患者）';

-- 团队信息
drop table IF EXISTS `base_team`;
CREATE TABLE `base_team` (
  `id` varchar(50) NOT NULL   COMMENT '主键，团队uuid标识',
  `org_code` varchar(50) DEFAULT NULL COMMENT '机构代码',
  `org_name` varchar(30) DEFAULT NULL COMMENT '机构名称',
  `name` varchar(50) DEFAULT NULL COMMENT '团队名称',
  `leader_id` varchar(50) NOT NULL COMMENT '领导医生标识',
  `team_num` varchar(50) NOT NULL COMMENT '团队人数',
  `qrcode` varchar(50) DEFAULT NULL COMMENT '团队二维码',
  `del` varchar(1) DEFAULT '1' COMMENT '作废标识，1正常，0作废',
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人名',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='团队信息';

drop table IF EXISTS `base_team_member`;
CREATE TABLE `base_team_member` (
  `id` varchar(50) NOT NULL    COMMENT 'uuid',
  `team_id` varchar(100) NOT NULL COMMENT '团队ID',
  `org_id` varchar(100) NOT NULL COMMENT '机构标识',
  `doctor_id` varchar(500) NOT NULL COMMENT '医生标识，多个医生以逗号分开',
  `del` varchar(1) DEFAULT '1' COMMENT '作废标识，1正常，0作废',
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人名',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='团队成员';


-- 基础人口信息
drop table IF EXISTS `base_population`;
CREATE TABLE `base_population` (
  `id` varchar(50) NOT NULL DEFAULT '' COMMENT '主键(uuid)，基础人口信息uuid',
  `saas_id`  varchar(100) NOT NULL COMMENT 'saas化的id',
  `province_code` varchar(50) DEFAULT NULL COMMENT '所属省代码',
  `city_code` varchar(50) DEFAULT NULL COMMENT '所属市代码',
  `district_code` varchar(50) DEFAULT NULL COMMENT '所属区代码',
  `name` varchar(200) DEFAULT NULL COMMENT '所属具体名称',
  `population_num` int(10) DEFAULT NULL COMMENT '人口数',
  `type` varchar(2) DEFAULT NULL COMMENT '类别 0是省，1是市，2是区，3是机构',
  `year` int(10) DEFAULT NULL COMMENT '每年的人口数',
  `HBP_num` int(10) DEFAULT  '0' COMMENT '高血压发病数,HBP为医学简称',
  `DM_num` int(10) DEFAULT  '0' COMMENT '糖尿病发病数,DM为医学简称',
  `older_than_65_num` int(10) DEFAULT  '0' COMMENT '65岁以上老年人口数',
  `HBP_task_num` int(10) DEFAULT  '0' COMMENT '高血压任务数',
  `DM_task_num` int(10) DEFAULT  '0' COMMENT '糖尿病任务数',
  `older_than_65_task_num` int(10) DEFAULT  '0' COMMENT '65岁以上老年人口任务数',
  `task_num` int(10) DEFAULT  '0' COMMENT '户籍人口任务数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基础人口基数信息';

-- 行政区划数据  4个表 ---start----
-- 省
drop table IF EXISTS `base_province`;
CREATE TABLE `base_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `code` varchar(50) NOT NULL COMMENT '省份编码',
  `name` varchar(50) NOT NULL COMMENT '省份名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='省份字典';

-- 城市
drop table IF EXISTS `base_city`;
CREATE TABLE `base_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `province` varchar(10) DEFAULT NULL COMMENT '省编码',
  `code` varchar(50) DEFAULT NULL COMMENT '城市编码',
  `name` varchar(50) DEFAULT NULL COMMENT '城市名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_dm_city_province` (`province`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='城市字典';

-- 城镇区县
drop table IF EXISTS `base_town`;
CREATE TABLE `base_town` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `province` varchar(50) DEFAULT NULL COMMENT '省编码',
  `city` varchar(50) DEFAULT NULL COMMENT '城市编码',
  `code` varchar(50) DEFAULT NULL COMMENT '区县编码',
  `name` varchar(50) DEFAULT NULL COMMENT '区县名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_dm_town` (`city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区县字典';

-- 街道
drop table IF EXISTS `base_street`;
CREATE TABLE `base_street` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `province` varchar(50) DEFAULT NULL COMMENT '省标识',
  `city` varchar(50) DEFAULT NULL COMMENT '市标识',
  `town` varchar(50) DEFAULT NULL COMMENT '区县标识',
  `code` varchar(50) DEFAULT NULL COMMENT '街道标识',
  `name` varchar(100) DEFAULT NULL COMMENT '街道名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_dm_street` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='街道字典';

-- 居委会
drop table IF EXISTS `base_committee`;
CREATE TABLE `base_committee` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '业务无关主键',
  `province` varchar(50) DEFAULT NULL COMMENT '省标识',
  `city` varchar(50) DEFAULT NULL COMMENT '市标识',
  `town` varchar(50) DEFAULT NULL COMMENT '区县标识',
  `street` varchar(50) DEFAULT NULL COMMENT '街道标识',
  `code` varchar(50) DEFAULT NULL COMMENT '居委会标识',
  `name` varchar(100) DEFAULT NULL COMMENT '居委会名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT '居委会';

-- 行政区划数据  5个表 ---end----


-- 医生职称字典表
drop table IF EXISTS `dict_job_title`;
create table `dict_job_title`(
`id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
`saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id，null标识公共字典',
`code` varchar(50) default NULL COMMENT '职称标识',
`name` varchar(20) default NULL COMMENT '职称名(主任医师/副主任医师)',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
 KEY `idx_job_title_code` (`code`),
primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职称字典';

-- 医生职务字典表
drop table IF EXISTS `dict_doctor_duty`;
create table `dict_doctor_duty`(
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `code` varchar(50) default NULL COMMENT '职务标识',
  `name` varchar(20) default NULL COMMENT '职务名称（院长/科室主任等等）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  KEY `idx_job_title_code` (`code`),
  primary key (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生职务字典';

-- 机构药品分发
drop table IF EXISTS `dict_medicine_distribute_org`;
CREATE TABLE `dict_medicine_distribute_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_id` varchar(50) DEFAULT NULL COMMENT '机构编码',
  `medicine_code` varchar(50) DEFAULT NULL COMMENT '药品代码',
  `quantity` int(10) DEFAULT NULL COMMENT '分发数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机构药品分发字典';

-- 药品字典
drop table IF EXISTS `dict_medicine`;
create table `dict_medicine`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) DEFAULT NULL COMMENT '药品代码',
  `name` varchar(2000) DEFAULT NULL COMMENT '药品中文名',
  `subject_code` varchar(2000) DEFAULT NULL COMMENT '药品所属科目代码',
  `dosage_form` varchar(50) DEFAULT NULL COMMENT '药品剂型',
  `specification` varchar(200) DEFAULT NULL COMMENT '药品规格',
  `packing_specification` varchar(50) DEFAULT NULL COMMENT '包装规格',
  `indication` varchar(250) DEFAULT NULL COMMENT '适应症',
  `spell_code` varchar(200) DEFAULT NULL COMMENT '拼音首码',
  `wbzx_code` varchar(200) DEFAULT NULL COMMENT '五笔首码',
  `sequence` int(10) DEFAULT NULL COMMENT '排序号',
  `storage_conditions` varchar(50) DEFAULT NULL COMMENT '2表示需要冷藏，其他表示不需要冷藏',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='药品字典';

-- 药品剂型字典表（颗粒型，注射液，胶囊）
drop table IF EXISTS `dict_medicine_dosage_form`;
create table `dict_medicine_dosage_form`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) DEFAULT NULL COMMENT '剂型代码',
  `name` varchar(2000) DEFAULT NULL COMMENT '剂型名称（颗粒型，注射液，胶囊等）',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='药品剂型字典';

-- 药品科目类别表
drop table IF EXISTS `dict_medicine_subject`;
create table `dict_medicine_subject`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) DEFAULT NULL COMMENT '类别代码',
  `name` varchar(2000) DEFAULT NULL COMMENT '类别名称',
  `parent_code` varchar(2000) DEFAULT NULL COMMENT '父类code',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='药品科目类别字典';




-- ICD10表
drop table IF EXISTS `dict_icd10`;
CREATE TABLE `dict_icd10` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id，null标识公共字典',
  `code` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT 'icd10字典编码',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT 'icd10字典名称',
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  key `idx_icd10_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='ICD10字典';


-- 健康问题表
drop table IF EXISTS `dict_health_problem`;
CREATE TABLE `dict_health_problem` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id，null标识公共字典',
  `code` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '字典编码',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '字典名称',
  `chronic_flag` varchar(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '是否慢病,1-是，0-否',
  `infectious_flag` varchar(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '是否传染病,1-是，0-否',
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  key `idx_hea_problem_code` (`code`),
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='健康问题字典';


-- 病种字典表
drop table IF EXISTS `dict_disease`;
CREATE TABLE `dict_disease` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id，null标识公共字典',
  `code` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '疾病编码',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '疾病名称',
  key `idx_hea_problem_code` (`code`),
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='病种字典';

-- 标准科室
drop table IF EXISTS `dict_hospital_dept`;
create table `dict_hospital_dept`
(
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id，null标识公共字典',
  `code` varchar(50) not null COMMENT '科室标识',
  `name` varchar(50) not null COMMENT '科室名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  key `idx_hos_dept_code` (`code`),
  primary key (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医院科室字典';

-- 模块
Drop table IF EXISTS `base_module`;
CREATE TABLE `base_module` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '模块id',
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `parent_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '父业务id',
  `status` int(11) NOT NULL COMMENT '状态（0-不可用，1-可用，2-删除）',
  `create_user` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人名',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='模块';

-- 功能
Drop table IF EXISTS `base_function`;
CREATE TABLE `base_function` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '功能id',
  `module_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '模块ID',
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '功能名称',
  `prefix` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '使用网关转发时的url前缀',
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '功能对应的后台url',
  `create_user` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人名',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='功能';

-- SAAS
Drop table IF EXISTS `base_saas`;
CREATE TABLE `base_saas` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'saas业务标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建者',
  `create_user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新者',
  `update_user_name` datetime DEFAULT NULL COMMENT '更新者',
  `org_code` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '机构编码',
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '系统名称',
  `status` int(11) NOT NULL COMMENT '状态（0-待审核 1-审核通过 2-审核不通过 3-已删除）',
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `logo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '远程fastDFS文件地址',
  `type` int(11) NOT NULL COMMENT '类型',
  `manager` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '管理员 - 关联user表id字段',
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '管理员邮箱',
  `mobile` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '管理员手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='SAAS';

-- Saas模块功能
Drop table IF EXISTS `base_saas_module_function`;
CREATE TABLE `base_saas_module_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `saas_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '角色ID',
  `module_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '模块ID',
  `function_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '功能ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Saas模块功能';

-- Saas类型默认的模块功能
Drop table IF EXISTS `base_saas_default_module_function`;
CREATE TABLE `base_saas_default_module_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `saas_type` int(11) NOT NULL COMMENT 'saas类型',
  `module_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '模块ID',
  `function_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '功能ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Saas类型默认的模块功能';

-- Saas名片
Drop table IF EXISTS `base_saas_business_card`;
CREATE TABLE `base_saas_business_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `saas_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT 'SAAS ID',
  `field` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '所要生成的目标名片的字段，如医生的name、age等',
  `type` int(11) NOT NULL COMMENT '名片类型 （0 - 医生， 1-居民）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Saas名片';

-- 短信网关
Drop table IF EXISTS `base_sms_gateway`;
CREATE TABLE `base_sms_gateway` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `client_id` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'saas_id 关联 base_saas',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '网关名称',
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '网关用户名',
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '网关密码',
  `website` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '官网地址',
  `expire_min` int(11) NOT NULL COMMENT '超时时间（分钟）',
  `request_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '网关接口访问URL',
  `request_certificate` varchar(2550) CHARACTER SET utf8 NOT NULL COMMENT '调用凭证（json串）',
  `response_code` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '短信接口调用状态标识',
  `success_value` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '调用成功的状态码',
  `status` int(11) NOT NULL COMMENT '0-禁用，1-可用，2-不可用',
  `create_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人名',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='短信网关';

-- 短信模板
Drop table IF EXISTS `base_sms_template`;
CREATE TABLE `base_sms_template` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '主键',
  `client_id` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '应用ID',
  `type` int(11) NOT NULL COMMENT '短信标签',
  `header` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '【短信抬头】',
  `content` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '正文',
  `create_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人名',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='短信模板';

-- 短信记录
Drop table IF EXISTS `base_sms`;
CREATE TABLE `base_sms` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '业务无关主键',
  `client_id` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '应用ID',
  `sms_gateway_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '使用的网关ID',
  `request_ip` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '请求的ip地址',
  `mobile` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '接收手机号',
  `content` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '消息内容',
  `deadline` datetime NOT NULL COMMENT '过期时间',
  `captcha` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '验证码',
  `type` int(11) NOT NULL COMMENT '消息类型：1微信端注册，2微信端找回密码，3医生端找回密码，4患者登录，5医生登录',
  `create_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人名',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_wlyy_sms_1` (`mobile`),
  KEY `idx_wlyy_sms_2` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='短信记录';

-- 系统字典
Drop table IF EXISTS `base_system_dict`;
CREATE TABLE `base_system_dict` (
  `saas_id` varchar(50) DEFAULT NULL COMMENT 'SaasId',
  `code` varchar(50) NOT NULL COMMENT '字典代码',
  `py_code` varchar(50) NOT NULL COMMENT '拼音码',
  `name` varchar(50) NOT NULL COMMENT '字典名称',
  `type` int(11) NOT NULL COMMENT '字典类型（0-基础字典，1-扩展字典）',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统字典';

-- 系统字典项
Drop table IF EXISTS `base_system_dict_entry`;
CREATE TABLE `base_system_dict_entry` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '主键',
  `dict_code` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '所属字典代码',
  `code` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '字典项代码',
  `py_code` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '字典项拼音码',
  `value` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '字典项值',
  `sort` int(11) DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统字典项';

-- 后台管理员
Drop table IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建用户的ID',
  `create_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建用户的用户名',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新用户的ID',
  `update_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新用户的用户名',
  `username` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '密码散列值',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '真实名字',
  `gender` int(11) DEFAULT NULL COMMENT '性别（0-男，1-女）',
  `idcard` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '身份证号码',
  `mobile` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号码',
  `ak` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '简化模式获取token所需要的凭证',
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `enabled` int(1) DEFAULT NULL COMMENT '账号是否可用1为可用',
  `locked` int(1) DEFAULT NULL COMMENT '账号是否被锁',
  `locked_date` datetime DEFAULT NULL COMMENT '账号被锁时间',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `login_failure_count` int(11) DEFAULT NULL COMMENT '用户名密码验证失败次数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='后台管理员';

-- 角色
Drop table IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `saas_id` varchar(50) DEFAULT NULL COMMENT 'saas_id',
  `client_id` varchar(10) NOT NULL COMMENT '所属应用',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(16) NOT NULL COMMENT '角色代码',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人名',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `system` bit(1) NOT NULL COMMENT '是否系统角色',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- 角色权限
Drop table IF EXISTS `base_role_authority`;
CREATE TABLE `base_role_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键，无关业务',
  `role_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '角色ID',
  `authorities` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色权限';

-- 用户角色
Drop table IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '用户ID',
  `role_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '角色编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户角色';

-- 基于MQ的消息推送
Drop table IF EXISTS `base_mq_message`;
CREATE TABLE `base_mq_message` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `topic` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '主题',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `template` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '模板',
  `create_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人名',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='基于MQ的消息推送';

-- 基于MQ的消息推送订阅者
Drop table IF EXISTS `base_mq_message_subscriber`;
CREATE TABLE `base_mq_message_subscriber` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '主键',
  `saas_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '所属SAAS',
  `topic` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '主题',
  `url` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '推送地址',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `create_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人名',
  `update_time` datetime NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='基于MQ的消息推送订阅者';


-- 消息管理模块
drop table IF EXISTS `base_message`;
CREATE TABLE `base_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '与业务无关主键',
  `saas_id` varchar(100) NOT NULL COMMENT 'saas配置id，消息里不可为空',
  `code` varchar(50) NOT NULL COMMENT '消息标识',
  `receiver` varchar(50) NOT NULL COMMENT '消息接收人（微信平台为患者标识，医生APP平台为医生标识）',
  `receiver_name` varchar(200) DEFAULT NULL  COMMENT '接收人姓名',
  `sender` varchar(50) DEFAULT NULL COMMENT '消息发送人标识',
  `sender_name` varchar(1000) DEFAULT NULL  COMMENT '发送人姓名',
  `sender_photo` varchar(200) DEFAULT NULL COMMENT '发送者头像',
  `title` varchar(50) NOT NULL COMMENT '消息标题',
  `msg_digest` varchar(100) NOT NULL COMMENT '消息摘要',
  `msg_content` varchar(200) NOT NULL COMMENT '消息内容，存json',
  `msg_type` int(11) DEFAULT NULL COMMENT '消息类型（1.是家庭签约信息  2体征 101患者申请取消签约、102患者同意取消签约、103患者拒绝取消签约、104患者填写了血糖记录、105患者填写了血压记录、106患者填写了体重记录、107患者填写了腰围记录、108患者填写了运动记录、109患者填写了用药记录、110患者填写了饮食记录、111患者提交了问卷随访、112请求添加好友消息、113入群消息、114群解散消息、115踢出群消息、116新的网络咨询、117网络咨询追问、201医生拒绝签约、202医生同意签约、203医生申请取消签约、204医生同意取消签约、205医生拒绝取消签约、206新的问卷随访、207新的健康干预、208请求添加好友消息、209入群消息、210群解散消息、211踢出群消息、212聊天消息提醒、213群聊天消息、214医生回复了网络咨询、215请求添加为家人、216电话随访，217、上门随访）',
  `msg_type_name` varchar(20) DEFAULT NULL COMMENT '消息类型名称',
  `platform` int(11) DEFAULT NULL COMMENT '消息平台，1微信端/患者端，2医生APP端',
  `read_state` int(11) DEFAULT '0' COMMENT '阅读状态，1 已读，0未读',
  `readonly` int(11) DEFAULT NULL COMMENT '只读消息：1否，0是',
  `del` varchar(1) DEFAULT '1' COMMENT '是否删除，1正常，0作废',
#   `sign_status` varchar(11) DEFAULT NULL COMMENT '//签约状态 如果是签约 1新的签约，2签约成功，3签约失败，4解约申请，5解约成功，6解约失败，7签约到期，8续签',
#   `prescription_status` varchar(50) DEFAULT NULL COMMENT '续方审核状态 0待审核，1审核通过，2审核失败',
#   `relation_code` text COMMENT '业务关联code，type=6时为咨询code,type=301时为患者code',
#   `consult_num` int(11) DEFAULT NULL COMMENT '待回复咨询数',
  `session_id` varchar(150) DEFAULT NULL COMMENT 'im会话id',
  `session_name` varchar(50) DEFAULT NULL COMMENT 'im会话名称',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'  COMMENT '消息发送时间',
  `read_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'  COMMENT '消息阅读时间',
  PRIMARY KEY (`id`),
  KEY `idx_wlyy_message_uid` (`receiver`)
) ENGINE=InnoDB AUTO_INCREMENT=52616 DEFAULT CHARSET=utf8mb4 COMMENT='消息表';

-- 消息类型字典表
drop table IF EXISTS `base_message_type`;
create table `base_message_type`
(
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id，null标识公共字典',
  `code` varchar(50) not null COMMENT '类型编码（体征预警消息、提醒缴费消息、提醒测量体征数据消息、续签提醒消息、筛查提醒消息 1.是家庭签约信息  2体征 101患者申请取消签约、102患者同意取消签约、103患者拒绝取消签约、104患者填写了血糖记录、105患者填写了血压记录、106患者填写了体重记录、107患者填写了腰围记录、108患者填写了运动记录、109患者填写了用药记录、110患者填写了饮食记录、111患者提交了问卷随访、112请求添加好友消息、113入群消息、114群解散消息、115踢出群消息、116新的网络咨询、117网络咨询追问、201医生拒绝签约、202医生同意签约、203医生申请取消签约、204医生同意取消签约、205医生拒绝取消签约、206新的问卷随访、207新的健康干预、208请求添加好友消息、209入群消息、210群解散消息、211踢出群消息、212聊天消息提醒、213群聊天消息、214医生回复了网络咨询、215请求添加为家人、216电话随访，217、上门随访）',
  `name` varchar(50) not null COMMENT '类型名称',
  primary key (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息类型字典';


-- 业务模块角色表
drop table IF EXISTS `base_role_module`;
create table `base_role_module`
(
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `role_id` varchar(100) DEFAULT NULL COMMENT '角色id',
  `module_id` varchar(50) not null COMMENT '业务模块id',
  primary key (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务模块角色';

-- 机构区域树形结构表
drop table IF EXISTS `org_tree`;
create table `org_tree`
(
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长',
  `parent_code` varchar(50) not null COMMENT '父级行政区域code',
  `code` varchar(100) DEFAULT NULL COMMENT '行政区域code',
  `name` varchar(50) not null COMMENT '行政区域名称',
  `level` varchar(50) not null COMMENT '节点所在层级（即有几个父亲）',
  primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机构区域树形结构';

-- 居民医保卡电子卡等信息
drop table IF EXISTS `patient_medicare_card`;
create table `patient_medicare_card`
(
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长',
  `code` varchar(50) not null COMMENT '卡标识',
  `type` varchar(1) DEFAULT NULL COMMENT '卡类型，1-医保卡，2-电子健康卡',
  `patient_code` varchar(50) not null COMMENT '居民标识',
  primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='居民医保关联卡';