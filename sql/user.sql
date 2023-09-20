-- 账户模型
CREATE TABLE `ums_account_user`
(
    `id`              int unsigned NOT NULL AUTO_INCREMENT COMMENT '账号id',
    `email`           varchar(30)  NOT NULL DEFAULT '' COMMENT '邮箱',
    `username`        varchar(30)  NOT NULL DEFAULT '' COMMENT '用户名',
    `password`        varchar(32)  NOT NULL DEFAULT '' COMMENT '密码',
    `login_times`     int          NOT NULL DEFAULT 0 COMMENT '登录次数',
    `status`          tinyint      NOT NULL DEFAULT 1 COMMENT '状态 1:enable, 0:disable, -1:deleted',
    `create_ip`       int unsigned NOT NULL DEFAULT 0 COMMENT '创建ip',
    `last_login_ip`   int unsigned NOT NULL DEFAULT 0 COMMENT '最后一次登录ip',
    `create_by`       int unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
    `create_time`     datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `last_login_time` datetime     NOT NULL DEFAULT current_timestamp on update current_timestamp COMMENT '最后登陆时间',
    PRIMARY KEY (`id`),
    KEY `idx_email` (`email`),
    KEY `idx_username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='账户';


-- 用户模型
CREATE TABLE `ums_account_info`
(
    `id`          int unsigned NOT NULL COMMENT '用户id',
    `nickname`    varchar(30)  NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar`      varchar(255) NOT NULL DEFAULT '' COMMENT '头像(路径)',
    `gender`      bool         NOT NULL DEFAULT true COMMENT '性别',
    `sign`        varchar(512) NOT NULL DEFAULT '' COMMENT '个性签名',
    `create_time` datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT current_timestamp on update current_timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='账户信息';


-- 菜单
CREATE TABLE `ums_menu`
(
    `id`          int unsigned      NOT NULL COMMENT '自增id',
    `parent_id`   int unsigned      NOT NULL DEFAULT 0 COMMENT '父菜单id',
    `menu_name`   varchar(255)      NOT NULL DEFAULT '未命名' COMMENT '菜单名称',
    `description` varchar(255)      NOT NULL DEFAULT '' COMMENT '菜单描述',
    `menu_uri`    varchar(255)      NOT NULL DEFAULT '' COMMENT '菜单uri',
    `is_show`     bool              NOT NULL DEFAULT true COMMENT '是否展示菜单 ',
    `create_time` datetime          NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time` datetime          NOT NULL DEFAULT current_timestamp on update current_timestamp COMMENT '更新时间',
    `order`       smallint unsigned not null default 0 comment '优先级',
    `icon`        varchar(100)      not null default '' comment '图标',
    PRIMARY KEY (`id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='路由菜单';

-- 角色
CREATE TABLE `ums_role`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name`        varchar(20)  NOT NULL unique DEFAULT '' COMMENT '角色名称',
    `description` varchar(255) NOT NULL        DEFAULT '' COMMENT '角色描述',
    `create_by`   int unsigned NOT NULL        DEFAULT 0 COMMENT '创建者',
    `update_by`   int unsigned NOT NULL        DEFAULT 0 COMMENT '修改者',
    `status`      tinyint      NOT NULL        DEFAULT 1 COMMENT '状态 1:enable, 0:disable, -1:deleted',
    `create_time` datetime     NOT NULL        DEFAULT current_timestamp COMMENT '创建时间',
    `update_time` datetime     NOT NULL        DEFAULT current_timestamp on update current_timestamp COMMENT '更新时间',
    unique key (`name`(20)),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色';

-- 用户-角色
CREATE TABLE `ums_user_roles`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `user_id`     int unsigned NOT NULL COMMENT '用户id',
    `role_id`     int unsigned NOT NULL COMMENT '角色id',
    `create_by`   int unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
    `update_by`   int unsigned NOT NULL DEFAULT 0 COMMENT '修改者',
    `create_time` datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT current_timestamp on update current_timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`),
    key `idx_user_role_id` (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色';


-- 菜单-角色
CREATE TABLE `ums_role_menus`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `role_id`     int unsigned NOT NULL DEFAULT 0 COMMENT '角色id',
    `menu_id`     int unsigned NOT NULL DEFAULT 0 COMMENT '菜单id',
    `create_time` datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT current_timestamp on update current_timestamp COMMENT '更新时间',
    `create_by`   int unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
    `update_by`   int unsigned NOT NULL DEFAULT 0 COMMENT '修改者',
    `status`      tinyint      NOT NULL DEFAULT 1 COMMENT '状态 1:enable, 0:disable, -1:deleted',
    PRIMARY KEY (`id`),
    KEY `idx_role_menu_id` (`role_id`, `menu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='菜单-角色';


-- 权限表
CREATE TABLE `ums_permissions`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name`        varchar(255) NOT NULL unique DEFAULT '' COMMENT '角色名称',
    `description` varchar(255) NOT NULL        DEFAULT '' COMMENT '权限描述',
    `create_time` datetime     NOT NULL        DEFAULT current_timestamp COMMENT '创建时间',
    PRIMARY KEY (`id`),
    unique key `idx_permissions_name` (name(20))
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限';

-- 角色-权限
CREATE TABLE `ums_role_permissions`
(
    `id`              int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `role_id`         int unsigned NOT NULL DEFAULT 0 COMMENT '角色id',
    `permissions_id`  int unsigned NOT NULL DEFAULT 0 COMMENT '权限id',
    `permission_type` tinyint      not null default 0 comment '权限类型',
    `create_at`       int          NOT NULL DEFAULT 0 COMMENT '创建时间',
    `create_time`     datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time`     datetime     NOT NULL DEFAULT current_timestamp on update current_timestamp COMMENT '更新时间',
    `update_by`       int          NOT NULL DEFAULT 0 COMMENT '修改者',
    `status`          tinyint      NOT NULL DEFAULT 1 COMMENT '状态 1:enable, 0:disable, -1:deleted',
    PRIMARY KEY (`id`),
    KEY `idx_role_menu_id` (`role_id`, `permissions_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色-权限';
