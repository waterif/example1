CREATE TABLE `ums_push_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `action` varchar(255) NOT NULL DEFAULT '' COMMENT '操作：add,update,delete',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `site_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '站点ID',
  `display_name` varchar(255) NOT NULL DEFAULT '' COMMENT '显示名',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态：0失败，1成功，2重试成功',
  `times` int(11) NOT NULL DEFAULT '0' COMMENT '重试次数',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;