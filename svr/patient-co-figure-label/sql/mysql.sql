# job配置表
DROP TABLE IF EXISTS `fl_job_config`;
CREATE TABLE `fl_job_config` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_class` varchar(200) DEFAULT NULL COMMENT '任务执行的class',
  `query_sql` varchar(2000) DEFAULT NULL COMMENT '抽取数据的sql语句',
  `sql_field_type` int(2) DEFAULT NULL COMMENT '增量字段类型 1时间 （yyyy-mm-dd  HH:MM:ss）2数字',
  `sql_field_value` varchar(255) DEFAULT NULL COMMENT '增量字段值',
  `sql_field` varchar(255) DEFAULT NULL COMMENT '增量字段',
  `quartz_cron` varchar(200) DEFAULT NULL COMMENT 'quartz表达式',
  `status` varchar(1) DEFAULT NULL COMMENT '1 启动 0停止',
  `del` varchar(1) DEFAULT NULL COMMENT '1 正常    0 删除',
  `source_type` varchar(20) DEFAULT NULL COMMENT '来源类型（mysql，hbase，file等）',
  `source` varchar(100) DEFAULT NULL COMMENT '数据来源',
  `datasource` varchar(200) NOT NULL DEFAULT '' COMMENT '数据库连接，格式为：url-database',
  `extract_field` varchar(255) DEFAULT '' COMMENT '查询的字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


# 标签具体字典表
DROP TABLE IF EXISTS `fl_label_dict`;
CREATE TABLE `fl_label_dict` (

  `id` int(10) NOT NULL AUTO_INCREMENT,
  `dict_code` varchar(64) DEFAULT NULL COMMENT 'code一致的标签属于同一类',
  `parent_code` varchar(255) DEFAULT '0' COMMENT '字典父类代码，没有父类默认为0',
  `label_code` varchar(200) DEFAULT NULL COMMENT '标签code',
  `label_name` varchar(200) DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

# 标签分类表
DROP TABLE IF EXISTS `fl_label_dict_category`;
CREATE TABLE `fl_label_dict_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_code` varchar(255) NOT NULL COMMENT '标签字典代码',
  `dict_name` varchar(255) NOT NULL COMMENT '标签字典名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='标签字典分类表';


# 标签转换器表
DROP TABLE IF EXISTS `fl_label_dict_job`;
CREATE TABLE `fl_label_dict_job` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `job_id` int(10) NOT NULL COMMENT '任务id',
  `category_id` int(10) NOT NULL COMMENT '标签分类id',
  `label_type` varchar(10) DEFAULT NULL COMMENT '标签类型 1 个人基本信息 2人群属性 3行政区域 4互联网行为 5签约分析 6咨询分析 7随访记录 8生活环境 9健康档案分析',
  `query_sql` varchar(2000) DEFAULT NULL COMMENT '抽取数据的sql语句',
  `convert_clazz` varchar(255) DEFAULT NULL COMMENT '需要转换的类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;