
CREATE TABLE `t_sprint` (
    `id` bigint(20) NOT NULL COMMENT 'id',
    `name` varchar(50) DEFAULT NULL COMMENT '迭代名称',
    `sprint_templet_id` bigint DEFAULT NULL COMMENT '迭代模版id',
    `version` varchar(80) DEFAULT NULL COMMENT '版本号',
    `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
    `creator_id` bigint DEFAULT NULL COMMENT '创建人id',
    `created_time` datetime DEFAULT NULL COMMENT '创建时间',
    `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
    `modifier_id` bigint DEFAULT NULL COMMENT '修改人id',
    `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
    `status` tinyint DEFAULT NULL COMMENT '状态 0:无效,1:有效',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='迭代列表';


CREATE TABLE `t_sprint_project`  (
    `id` bigint(20) NOT NULL COMMENT 'id',
    `sprint_id` bigint(20) NOT NULL COMMENT '迭代id',
    `git_url` varchar(300) NULL COMMENT '项目git地址',
    `name` varchar(50) NULL COMMENT '项目名称',
    `branch` varchar(50) NULL COMMENT '分支名称',
    `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
    `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
    `created_time` datetime DEFAULT NULL COMMENT '创建时间',
    `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
    `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
    `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
    `status` tinyint(4) DEFAULT NULL COMMENT '状态 0:无效,1:有效',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='迭代内的项目列表';


CREATE TABLE `t_envent_subtask_config`  (
    `id` bigint(20) NOT NULL COMMENT 'id',
    `event` varchar(50) NULL COMMENT '事件',
    `action_task` varchar(100) NULL COMMENT '系统内置的actitonKey',
    `name` varchar(50) NULL COMMENT 'task名称',
    `describe` varchar(50) NULL COMMENT '说明',
    `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
    `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
    `created_time` datetime DEFAULT NULL COMMENT '创建时间',
    `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
    `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
    `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
    `status` tinyint(4) DEFAULT NULL COMMENT '状态 0:无效,1:有效',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='事件子任务表';


CREATE TABLE `t_sprint_templet`  (
    `id` bigint(20) NOT NULL COMMENT 'id',
    `name` varchar(100) NULL COMMENT '模版名',
    `type` tinyint(4) NULL COMMENT '模版类型 2常规迭代，1紧急迭代',
    `event_list` varchar(50) NULL COMMENT '事件列表，逗号分隔',
    `describe` varchar(50) NULL COMMENT '说明',
    `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
    `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
    `created_time` datetime DEFAULT NULL COMMENT '创建时间',
    `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
    `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
    `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
    `status` tinyint(4) DEFAULT NULL COMMENT '状态 0:无效,1:有效',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='迭代模版';


CREATE TABLE `sys_config` (
    `id` bigint(20) NOT NULL COMMENT 'id',
    `key` varchar(100) DEFAULT NULL COMMENT 'key',
    `value` varchar(200) DEFAULT NULL COMMENT 'value',
    `describe` varchar(50) DEFAULT NULL COMMENT '说明',
    `creator` varchar(50) DEFAULT NULL COMMENT '创建者',
    `creator_id` bigint DEFAULT NULL COMMENT '创建人id',
    `created_time` datetime DEFAULT NULL COMMENT '创建时间',
    `modifier` varchar(50) DEFAULT NULL COMMENT '修改者',
    `modifier_id` bigint DEFAULT NULL COMMENT '修改人id',
    `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
    `status` tinyint DEFAULT NULL COMMENT '状态 0:无效,1:有效',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统配置表';