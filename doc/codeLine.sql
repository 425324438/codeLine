/*
 Navicat Premium Data Transfer

 Source Server         : localhost——pass——guzhan11
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : codeLine

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 14/07/2022 23:30:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL COMMENT 'id',
  `key_str` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'key',
  `value_str` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'value',
  `describe_str` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '说明',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
  `modifier_id` bigint DEFAULT NULL COMMENT '修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` tinyint DEFAULT NULL COMMENT '状态 0:无效,1:有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统配置表';

-- ----------------------------
-- Table structure for t_envent_subtask_config
-- ----------------------------
DROP TABLE IF EXISTS `t_envent_subtask_config`;
CREATE TABLE `t_envent_subtask_config` (
  `id` bigint NOT NULL COMMENT 'id',
  `event` varchar(50) DEFAULT NULL COMMENT '事件',
  `action_task` varchar(100) DEFAULT NULL COMMENT '系统内置的actitonKey',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'task名称',
  `describe` varchar(50) DEFAULT NULL COMMENT '说明',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
  `modifier_id` bigint DEFAULT NULL COMMENT '修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` tinyint DEFAULT NULL COMMENT '状态 0:无效,1:有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='事件子任务表';

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '项目名称',
  `git_url` varchar(300) DEFAULT NULL COMMENT '项目git地址',
  `group` varchar(80) DEFAULT NULL COMMENT '项目所属的组',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
  `modifier_id` bigint DEFAULT NULL COMMENT '修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` tinyint DEFAULT NULL COMMENT '状态 0:无效,1:有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目列表';

-- ----------------------------
-- Table structure for t_sprint
-- ----------------------------
DROP TABLE IF EXISTS `t_sprint`;
CREATE TABLE `t_sprint` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '迭代名称',
  `sprint_type` varchar(30) DEFAULT NULL COMMENT '迭代类型：urgent，routine',
  `sprint_tempalte_id` bigint DEFAULT NULL COMMENT '迭代模版id',
  `sprint_env_status` varchar(20) DEFAULT NULL COMMENT '初始状态：DEV,Sprint 迭代环境信息枚举',
  `git_storage_type` varchar(10) DEFAULT NULL COMMENT 'git仓库类型，可选值：gitlab,github,gitee',
  `version` varchar(80) DEFAULT NULL COMMENT '版本号',
  `has_error` int DEFAULT '0' COMMENT '0无异常，1 action执行有异常',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
  `modifier_id` bigint DEFAULT NULL COMMENT '修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` tinyint DEFAULT NULL COMMENT '状态 0:无效,1:有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='迭代列表';

-- ----------------------------
-- Table structure for t_sprint_action_list
-- ----------------------------
DROP TABLE IF EXISTS `t_sprint_action_list`;
CREATE TABLE `t_sprint_action_list` (
  `id` bigint NOT NULL,
  `sprint_id` bigint DEFAULT NULL,
  `action_status` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '默认=未开始，枚举值：未开始，激活，执行中，执行失败，已结束',
  `action_bean_type_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '程序中Action中的type属性，代表了需要执行的Action',
  `sprint_env_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'SprintEnvStatusEnums 这里指Spint环境阶段信息',
  `name` varchar(80) DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'outer:调用外部接口，inner 内部Action',
  `description` varchar(400) DEFAULT NULL COMMENT '说明',
  `exe_result` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行后的返回数据',
  `sort_no` int DEFAULT NULL COMMENT '执行序号',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint NOT NULL COMMENT '状态 0:无效,1:有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Sprint的执行任务列表';

-- ----------------------------
-- Table structure for t_sprint_project
-- ----------------------------
DROP TABLE IF EXISTS `t_sprint_project`;
CREATE TABLE `t_sprint_project` (
  `id` bigint NOT NULL COMMENT 'id',
  `sprint_id` bigint NOT NULL COMMENT '迭代id',
  `project_id` bigint DEFAULT NULL COMMENT '项目id',
  `git_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '项目git地址',
  `name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `branch` varchar(50) DEFAULT NULL COMMENT '分支名称',
  `web_url` varchar(1000) DEFAULT NULL COMMENT '页面访问地址',
  `param_json` json DEFAULT NULL COMMENT 'action执行过程需要的数据冗余字段',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
  `modifier_id` bigint DEFAULT NULL COMMENT '修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` tinyint DEFAULT NULL COMMENT '状态 0:无效,1:有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='迭代内的项目列表';

-- ----------------------------
-- Table structure for t_sprint_template
-- ----------------------------
DROP TABLE IF EXISTS `t_sprint_template`;
CREATE TABLE `t_sprint_template` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '模版名',
  `type` tinyint DEFAULT NULL COMMENT '模版类型 2常规迭代，1紧急迭代',
  `describe` varchar(50) DEFAULT NULL COMMENT '说明',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
  `modifier_id` bigint DEFAULT NULL COMMENT '修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` tinyint DEFAULT NULL COMMENT '状态 0:无效,1:有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='迭代模版';

-- ----------------------------
-- Table structure for t_sprint_template_action_list
-- ----------------------------
DROP TABLE IF EXISTS `t_sprint_template_action_list`;
CREATE TABLE `t_sprint_template_action_list` (
  `id` bigint NOT NULL,
  `sprint_template_id` bigint DEFAULT NULL,
  `sprint_env_status` varchar(50) DEFAULT NULL COMMENT 'SprintEnvStatusEnums 这里指Spint环境阶段信息',
  `action_bean_type_name` varchar(150) DEFAULT NULL COMMENT '程序中Action中的type属性，代表了需要执行的Action',
  `name` varchar(80) DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'outer:调用外部接口，inner 内部Action',
  `description` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '说明',
  `sort_no` int DEFAULT NULL COMMENT '执行序号',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint NOT NULL COMMENT '状态 0:无效,1:有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Sprint模版action执行列表';

SET FOREIGN_KEY_CHECKS = 1;
