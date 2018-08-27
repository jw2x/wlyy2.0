
-- 机构信息表 
drop table IF EXISTS `base_org`;
CREATE TABLE `base_org` (
  `id` varchar(50) NOT NULL COMMENT 'uuid,uuid唯一标识,也是机构code',
  `saasid` varchar(50) NOT NULL COMMENT 'saas化配置',
  `province_code` varchar(50) DEFAULT NULL COMMENT '省份标识',
  `city_code` varchar(50) DEFAULT NULL COMMENT '城市标识',
  `town_code` varchar(50) DEFAULT NULL COMMENT '区县标识',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机构表';

-- 医生信息表
drop table IF EXISTS `base_doctor`;
CREATE TABLE `base_doctor` (
  `id` varchar(50) NOT NULL COMMENT 'uuid,uuid唯一标识',
  `saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id',
  `doctor_role_code` varchar(50) NOT NULL COMMENT '医生角色标识',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` char(2) DEFAULT NULL COMMENT '性别（1男，2女） 用国家标准字典',
  `hospital_code` varchar(50) DEFAULT NULL COMMENT '医院标识',
  `dept_code` varchar(50) DEFAULT NULL COMMENT '科室标识',
  `job_title_code` varchar(50) DEFAULT NULL COMMENT '职称',
  `expertise` varchar(300) DEFAULT NULL COMMENT '医生专长',
  `introduce` varchar(1500) DEFAULT NULL COMMENT '医生介绍',
  `idcard` varchar(20) DEFAULT NULL COMMENT ' 身份证',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `photo` varchar(100) DEFAULT NULL COMMENT '头像http地址',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `qrcode` varchar(30) DEFAULT NULL COMMENT '医生二维码',
  `province_code` varchar(50) DEFAULT NULL COMMENT '省编码',
  `city_code` varchar(50) DEFAULT NULL COMMENT '市编码',
  `town_code` varchar(50) DEFAULT NULL COMMENT '区县编码',
  `iscertified` varchar(1) NOT NULL DEFAULT '0' COMMENT '资格是否认证通过，1是，0否',
  `is_famous` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否是名医，1是，0否',
  `identity_type` char(1) DEFAULT NULL COMMENT '类型：1专科医生，2全科医生，3健康管理师',
  `is_password_prompt` char(1) DEFAULT NULL COMMENT '是否提示设置密码  1 提示过 0未提示',
  `spell` varchar(10) DEFAULT NULL COMMENT '名称拼音首字母',
  `certified_overtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'CA证书过期时间',
  `certificate_num` varchar(100) DEFAULT NULL COMMENT 'CA证书编号',
  `openid` varchar(50) DEFAULT NULL,
  `del` varchar(1) DEFAULT '1' COMMENT '作废标识，1正常，0作废',
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人名',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生信息表';

-- 医生角色字典表
drop table IF EXISTS `base_doctor_role`;
CREATE TABLE `base_doctor_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `code` varchar(50) NOT NULL COMMENT '角色code',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `del` varchar(1) DEFAULT '1' COMMENT '作废标识，1正常，0作废',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_doctor_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生角色表';

-- 医生角色关联表
drop table IF EXISTS `base_doctor_role`;
CREATE TABLE `base_doctor_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，关联表',
  `role_code` varchar(50) NOT NULL COMMENT '医生角色id',
  `doctor_id` varchar(50) NOT NULL COMMENT '医生code',
  `del` varchar(1) DEFAULT '1' COMMENT '作废标识，1正常，0作废',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生角色表';


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
  `province_code` varchar(50) DEFAULT NULL COMMENT '省编码',
  `city_code` varchar(50) DEFAULT NULL COMMENT '市编码',
  `town_code` varchar(50) DEFAULT NULL COMMENT '区县编码',
  `street_code` varchar(50) DEFAULT NULL COMMENT '街道编码',
  `address` varchar(200) DEFAULT NULL COMMENT '具体详细地址',
  `disease` varchar(100) DEFAULT NULL COMMENT '疾病类型，0健康，1高血压，2糖尿病，3高血压+糖尿病',
  `disease_condition` varchar(100) DEFAULT NULL COMMENT '病情：0绿标，1黄标，2红标，3重点关注,',
  `points` varchar(100) DEFAULT NULL COMMENT '总积分',
  `record_amount` varchar(100) DEFAULT NULL COMMENT '病历总数',
  `openid` varchar(50) DEFAULT NULL COMMENT '微信编号',
  `patient_status` varchar(100) DEFAULT NULL COMMENT '用户状态：1正常，0禁用，-1恶意注册，2审核中',
  `mobile_remarks` varchar(200) DEFAULT NULL COMMENT '联系方式备注【基卫】',
  `openid_time` datetime DEFAULT NULL COMMENT '第一次添加open的时间',
  `sick_village` varchar(50) DEFAULT NULL COMMENT '居委会代码',
  `sick_village_name` varchar(100) DEFAULT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='居民信息表（居民就是患者）';

-- 团队信息
drop table IF EXISTS `base_team`;
CREATE TABLE `base_team` (
  `id` varchar(50) NOT NULL   COMMENT '主键，团队uuid标识',
  `saasid` varchar(50) DEFAULT NULL COMMENT 'saas化',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='团队';

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
drop table IF EXISTS `base_people_num`;
CREATE TABLE `base_people_num` (
  `id` varchar(50) NOT NULL DEFAULT '' COMMENT '主键(uuid)，基础人口信息uuid',
  `saas_id`  varchar(100) NOT NULL COMMENT 'saas化的id',
  `province_code` varchar(50) DEFAULT NULL COMMENT '所属省代码',
  `city_code` varchar(50) DEFAULT NULL COMMENT '所属市代码',
  `district_code` varchar(50) DEFAULT NULL COMMENT '所属区代码',
  `name` varchar(200) DEFAULT NULL COMMENT '所属具体名称',
  `num` int(10) DEFAULT NULL COMMENT '人口数',
  `type` varchar(2) DEFAULT NULL COMMENT '类别 0是省，1是市，2是区，3是机构',
  `year` int(10) DEFAULT NULL COMMENT '每年的人口数',
  `gxy_num` int(10) DEFAULT  '0' COMMENT '高血压发病数',
  `tnb_num` int(10) DEFAULT  '0' COMMENT '糖尿病发病数',
  `six_five_num` int(10) DEFAULT  '0' COMMENT '65岁以上老年人口数',
  `gxy_task_num` int(10) DEFAULT  '0' COMMENT '高血压任务数',
  `tnb_task_num` int(10) DEFAULT  '0' COMMENT '糖尿病任务数',
  `six_five_task_num` int(10) DEFAULT  '0' COMMENT '65岁以上老年人口任务数',
  `task_num` int(10) DEFAULT  '0' COMMENT '户籍人口任务数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- 行政区划数据  4个表 ---start----
-- 省
drop table IF EXISTS `base_province`;
CREATE TABLE `base_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `code` varchar(50) NOT NULL COMMENT '省份编码',
  `name` varchar(50) NOT NULL COMMENT '省份名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='省份表';

-- 城市
drop table IF EXISTS `base_city`;
CREATE TABLE `base_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `province` varchar(10) DEFAULT NULL COMMENT '省编码',
  `code` varchar(50) DEFAULT NULL COMMENT '城市编码',
  `name` varchar(50) DEFAULT NULL COMMENT '城市名称',
  PRIMARY KEY (`id`),
  KEY `idx_dm_city_province` (`province`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='城市字典表';

-- 城镇区县
drop table IF EXISTS `base_town`;
CREATE TABLE `base_town` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `province` varchar(50) DEFAULT NULL COMMENT '省编码',
  `city` varchar(50) DEFAULT NULL COMMENT '城市编码',
  `code` varchar(50) DEFAULT NULL COMMENT '区县编码',
  `name` varchar(50) DEFAULT NULL COMMENT '区县名称',
  `photo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_dm_town` (`city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区县字典表';

-- 街道
drop table IF EXISTS `base_street`;
CREATE TABLE `base_street` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `province` varchar(50) DEFAULT NULL COMMENT '省标识',
  `city` varchar(50) DEFAULT NULL COMMENT '市标识',
  `town` varchar(50) DEFAULT NULL COMMENT '区县标识',
  `code` varchar(50) DEFAULT NULL COMMENT '街道标识',
  `name` varchar(100) DEFAULT NULL COMMENT '街道名称',
  PRIMARY KEY (`id`),
  KEY `idx_dm_street` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='街道代码表';

-- 行政区划数据  4个表 ---end----


-- 职称表
drop table IF EXISTS `dict_job_title`;
create table `dict_job_title`(
`id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
`saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id，null标识公共字典',
`code` varchar(50) default NULL COMMENT '职称标识',
`name` varchar(20) default NULL COMMENT '职称名',
 KEY `idx_job_title_code` (`code`),
primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 药品
drop table IF EXISTS `dict_medicine`;
create table `dict_medicine`(
`id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
`saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id，null标识公共字典',
`code` varchar(100) not null COMMENT '药品编码',
`name` varchar(50) not null COMMENT '药品名称',
`type` char(1) default NULL COMMENT '药品类型：1健康记录',
primary key (id),
key `idx_medicine_code` (`code`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='药品字典';

-- ICD10表
drop table IF EXISTS `dict_icd10`;
CREATE TABLE `dict_icd10` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id，null标识公共字典',
  `code` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT 'icd10字典编码',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT 'icd10字典名称',
  `phonetic_code` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '字典名称拼音首字母',
  `chronic_flag` varchar(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '是否慢病',
  `infectious_flag` varchar(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '是否传染病',
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  key `idx_icd10_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 健康问题表
drop table IF EXISTS `dict_health_problem`;
CREATE TABLE `dict_health_problem` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id，null标识公共字典',
  `code` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '字典编码',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '字典名称',
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  key `idx_hea_problem_code` (`code`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 标准科室
drop table IF EXISTS `dict_hospital_dept`;
create table `dict_hospital_dept`
(
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '表id，自增长，字典型',
  `saas_id` varchar(100) DEFAULT NULL COMMENT 'saas配置id，null标识公共字典',
  `code` varchar(50) not null COMMENT '科室标识',
  `name` varchar(50) not null COMMENT '科室名称',
  key `idx_hos_dept_code` (`code`),
  primary key (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医院科室字典表';
