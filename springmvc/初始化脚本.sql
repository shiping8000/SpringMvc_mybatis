CREATE TABLE `t_bill` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `amount` decimal(10,0) DEFAULT '0' COMMENT '消费金额 单位元',
  `ltl_exp` decimal(10,0) DEFAULT '0' COMMENT '手续费用 单位（元）',
  `terminal_no` varchar(16) DEFAULT NULL COMMENT '终端号',
  `terminal_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '终端名称',
  `card_no` varchar(20) DEFAULT NULL COMMENT '银行卡号',
  `merchant_id` varchar(30) DEFAULT NULL COMMENT '商户Id',
  `agent_id` varchar(30) DEFAULT NULL COMMENT '代理商ID',
  `status` char(1) DEFAULT '0' COMMENT '状态 0 初始化  1 成功  2 失败',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COMMENT='账单流水表';

CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `user_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `password` varchar(50) DEFAULT NULL,
  `age` int(10) DEFAULT NULL COMMENT '年龄',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `t_system_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `server_id` int(11) DEFAULT NULL COMMENT '服务器ID',
  `server_name` varchar(255) DEFAULT NULL COMMENT '服务器名称',
  `type` int(11) DEFAULT NULL COMMENT '公告类型1服务器2系统',
  `content` varchar(500) DEFAULT NULL COMMENT '公告内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `delay` int(11) DEFAULT NULL COMMENT '播放间隔',
  `notice_time` datetime DEFAULT NULL COMMENT '上次播报时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7238 DEFAULT CHARSET=utf8 COMMENT='系统公告表';

