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
    unique key `idx_user_role_id` (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色';


drop table ums_role_menus;

-- 菜单-角色
CREATE TABLE `ums_role_menus`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `role_id`     int unsigned NOT NULL DEFAULT 0 COMMENT '角色id',
    `menu_id`     int unsigned NOT NULL DEFAULT 0 COMMENT '菜单id',
    `create_time` datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `create_by`   int unsigned NOT NULL DEFAULT 0 COMMENT '创建者',
    `create_ip`   int unsigned not null default 0 comment '创建者ip',
    PRIMARY KEY (`id`),
    unique KEY `idx_role_menu_id` (`role_id`, `menu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='菜单-角色';


-- 权限表
CREATE TABLE `ums_permissions`
(
    `id`          int unsigned NOT NULL COMMENT '自增id',
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
    `id`             int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `role_id`        int unsigned NOT NULL DEFAULT 0 COMMENT '角色id',
    `permissions_id` int unsigned NOT NULL DEFAULT 0 COMMENT '权限id',
    `create_at`      int unsigned NOT NULL DEFAULT 0 COMMENT '创建IP',
    `create_time`    datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `create_by`      int unsigned NOT NULL DEFAULT 0 COMMENT '修改者',
    PRIMARY KEY (`id`),
    unique KEY `idx_role_menu_id` (`role_id`, `permissions_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色-权限';


create table `fms_folder`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT primary key COMMENT '自增id',
    `parent_id`   int unsigned NOT NULL COMMENT '父文件夹id',
    `user_id`     int unsigned NOT NULL COMMENT '用户id',
    `name`        varchar(32)  NOT NULL DEFAULT '' COMMENT '文件夹名称',
    `description` varchar(255) NOT NULL DEFAULT '' COMMENT '文件夹描述',
    `full_path`   varchar(255) NOT NULL comment '完整路径',
    `create_time` datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `create_ip`   int unsigned not null default 0 comment '创建者ip',
    unique key uk_full_path (`full_path`),
    key idx_parent_id (`parent_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='文件夹';


create table `fms_files`
(
    `id`            int unsigned NOT NULL AUTO_INCREMENT primary key COMMENT '自增id',
    `folder_id`     int unsigned NOT NULL default 0 COMMENT '文件夹id',
    `user_id`       int unsigned NOT NULL COMMENT '用户id',
    `name`          varchar(128) NOT NULL DEFAULT '' COMMENT '文件名',
    `full_path`     varchar(255) NOT NULL comment '存储的完整路径',
    `size`          int unsigned not null default 0 comment '文件大小',
    `compress_path` varchar(255) NOT NULL default '' comment '压缩的文件路径',
    `status`        tinyint(1)   not null default 3 comment '文件状态',
    `policy`        tinyint(1)   not null default 0 comment '存储策略',
    `create_time`   datetime     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time`   datetime     NOT NULL DEFAULT current_timestamp COMMENT '更新时间',
    `create_ip`     int unsigned not null default 0 comment '创建者ip',
    unique key uk_full_path (`full_path`),
    key idx_parent_id (`folder_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='文件';

create table `ums_variable`
(
    `id`    int unsigned   NOT NULL AUTO_INCREMENT primary key COMMENT '自增id',
    `name`  varchar(128)   NOT NULL unique DEFAULT '' COMMENT '文件名',
    `value` varchar(10240) not null        default '' comment '值',
    `type`  varchar(512)   not null        default '' comment '类型'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='环境变量';
