insert into ums_account_user(id, email, username, password, login_times, status, create_ip, last_login_ip, create_by,
                             create_time, last_login_time)
values (1001, 'admin@admin.com', 'admin', '39c30570e9ed8bc6e84cc5054543a2fc', 0, 1, 2130706433, 2130706433, 1001,
        current_timestamp, current_timestamp);

insert into ums_account_info(id, nickname, avatar, gender, sign, create_time, update_time)
values (1001, 'admin', 'https://vitejs.dev/logo-with-shadow.png', true, '待到秋来九月八，我花开后百花杀',
        current_timestamp, current_timestamp);

insert into ums_user_roles(id, user_id, role_id, create_by, update_by, create_time, update_time)
values (1, 1001, 1, 1001, 1001, current_timestamp, current_timestamp);

insert into ums_variable(name, value, type)
values ('version', '-1', 'java.lang.Integer');

insert into fms_folder(id, parent_id, user_id, name, description, full_path, create_time, update_time, create_ip)
values (2, 0, 1001, 'image', '你好，我的世界', '/image', current_timestamp, current_timestamp, 2130706433)
