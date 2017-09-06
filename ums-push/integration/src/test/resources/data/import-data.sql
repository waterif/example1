insert into tang_ums_sites (id, url, alias_url, site_status, create_time, status_modify_time, customer_code, site_type) values (1, 'www.quanshimeeting.com', 'www.quanshihuiyi.com', 1, '2010-12-14 00:00:00', '2011-07-27 15:52:46', '006317', 0);
insert into tang_ums_sites (id, url, alias_url, site_status, create_time, status_modify_time, customer_code, site_type) values (1, 'www.gnetmeeting.com', 'www.gnethuiyi.com', 1, '2010-12-14 00:00:00', '2011-07-27 15:52:46', '006317', 0);
insert into tang_ums_sites (id, url, alias_url, site_status, create_time, status_modify_time, customer_code, site_type) values (2, 'www.quanshimeet.com', 'www.quanshihui.com', 1, '2010-12-14 00:00:00', '2011-07-27 15:52:46', '006318', 0);

insert into tang_ums_product (id, name, url, type) values (1, '全时云会议', 'SME-MEETING', 1);

insert into tang_ums_organization(id, name, customercode, siturl, starttime, code, invalid, theme, parent_id, node_code, child_order, child_node_count, type, abbreviation,country_code,area_code,mobile_number,introduction) values (1, '创想空间', '00001', null, '2013-03-19 17:06:07', '900144', null, null, null, '-1', null, 4,1, null, null, null, null, null);
insert into tang_ums_organization(id, name, customercode, siturl, starttime, code, invalid, theme, parent_id, node_code, child_order, child_node_count, type, abbreviation,country_code,area_code,mobile_number,introduction) values (2, '创想空间北京分公司', '00002', null, '2013-03-19 17:06:07', '900144', null, null, 1, '-1-2', null, 0,1, null, null, null, null, null);
insert into tang_ums_organization(id, name, customercode, siturl, starttime, code, invalid, theme, parent_id, node_code, child_order, child_node_count, type,abbreviation,country_code,area_code,mobile_number,introduction) values (3, '创想空间西安分公司', '00003', null, '2013-03-19 17:06:07', '900144', null, null, 1, '-1-3', null, 0,1, null, null, null, null, null);
insert into tang_ums_organization(id, name, customercode, siturl, starttime, code, invalid, theme, parent_id, node_code, child_order, child_node_count, type,abbreviation,country_code,area_code,mobile_number,introduction) values (4, '创想空间香港分公司', '00004', null, '2013-03-19 17:06:07', '900144', null, null, 1, '-1-4', null, 1,1, null, null, null, null, null);
insert into tang_ums_organization(id, name, customercode, siturl, starttime, code, invalid, theme, parent_id, node_code, child_order, child_node_count, type,abbreviation,country_code,area_code,mobile_number,introduction) values (5, '全时', '00004', null, '2013-03-19 17:06:07', '900144', null, null, null, '-1-4-5', null, 0,1, null, null, null, null, null);
insert into tang_ums_organization(id, name, customercode, siturl, starttime, code, invalid, theme, parent_id, node_code, child_order, child_node_count, type,abbreviation,country_code,area_code,mobile_number,introduction) values (6, '全时test', '00006', null, '2013-03-19 17:06:07', '900144', null, null, null, '-6', null, 0,1,null, null, null, null, null);


insert into tang_ums_user (id, login_name, password, pass_type, email, mobile_number, position, display_name, namepinyin, last_name, middle_name, first_name, sex, last_update_time, role, userstatus ) values (1, 'testuser1@quanshitest.com', '12345', 0, 'testuser1@quanshitest.com', '1502009282736', '测试工程师', '测试用户一', 'ceshiyonghuyi', '测试', '', '用户一', '1', '2013-10-22 17:07:06', 1, 1);
insert into tang_ums_user (id, login_name, password, pass_type, email, mobile_number, position, display_name, namepinyin, last_name, middle_name, first_name, sex, last_update_time, role, userstatus ) values (2, 'testuser2@quanshitest.com', '12345', 0, 'testuser2@quanshitest.com', '1502009282736', '测试工程师', '测试用户二', 'ceshiyonghuer', '测试', '', '用户二', '1', '2013-10-22 17:07:06', 1, 1);
insert into tang_ums_user (id, login_name, password, pass_type, email, mobile_number, position, display_name, namepinyin, last_name, middle_name, first_name, sex, last_update_time, role, userstatus ) values (3, 'testuser3@quanshitest.com', '12345', 0, 'testuser3@quanshitest.com', '1502009282736', '测试工程师', '测试用户三', 'ceshiyonghusan', '测试', '', '用户三', '1','2013-10-22 17:07:06', 1, 1);
insert into tang_ums_user (id, login_name, password, pass_type, email, mobile_number, position, display_name, namepinyin, last_name, middle_name, first_name, sex, last_update_time, role, userstatus ) values (4, 'testuser4@quanshitest.com', '12345', 0, 'testuser4@quanshitest.com', '1502009282736', '测试工程师', '测试用户四', 'ceshiyonghusi', '测试', '', '用户四', '1', '2013-10-22 17:07:06', 1, 1);

insert into tang_ums_user_product (id, user_id, product_id, user_status, sites_id, status_modify_time, created_time, is_host_account) values (1, 1, 1, 82, 1, '2013-03-19 17:06:07', '2013-03-19 17:06:07', 1);
insert into tang_ums_user_product (id, user_id, product_id, user_status, sites_id, status_modify_time, created_time, is_host_account) values (2, 2, 1, 82, 1, '2013-03-19 17:06:07', '2013-03-19 17:06:07', 1);

insert into tang_ums_user_organization(user_id, organization_id) values (1, 6);
insert into tang_ums_user_organization(user_id, organization_id) values (2, 6);

insert into tang_ums_contact (id, owner_user_id, dupe_user_id, contact_surname, contact_selfname, contact_fullname, name_pinyin, sex, primary_mail, mobile_number, position, company_name, back_info, last_contact_time,contactstatus) values (1, 1, 2, '张', '三', '张三', 'zhangsan', 0, 'zhangsan@quanshitest.com', '13802399323', '部门经理', '北京创想空间商务通信服务有限公司', '全时-张三', '2013-10-22 17:07:06',1);
insert into tang_ums_contact (id, owner_user_id, dupe_user_id, contact_surname, contact_selfname, contact_fullname, name_pinyin, sex, primary_mail, mobile_number, position, company_name, back_info, last_contact_time, contactstatus) values (2, 2, 3, '李', '四', '李四', 'lisi', 0, 'lisi@quanshitest.com', '13802399323', '开发', '北京创想空间商务通信服务有限公司', '全时-李四', '2013-10-22 17:07:06',1);
insert into tang_ums_contact (id, owner_user_id, dupe_user_id, contact_surname, contact_selfname, contact_fullname, name_pinyin, sex, primary_mail, mobile_number, position, company_name, back_info, last_contact_time, contactstatus) values (3, 1, 3, '李', '四', '李四', 'lisi', 0, 'lisi@quanshitest.com', '13802399323', '开发', '北京创想空间商务通信服务有限公司', '全时-李四', '2013-10-22 17:07:06',1);

insert into tang_ums_contact_group (contact_id, contact_group_id) values (1, 1);

insert into tang_ums_user_contact_group (id, user_id, group_name) values (1, 1, '同事');
insert into tang_ums_user_contact_group (id, user_id, group_name) values (2, 1, '同学');

insert into tang_ums_user_key(id, user_key, user_id, last_update_time) values (1, '3ce1a459-df4e-460d-8b79-d95508148d12', 2, CURRENT_TIMESTAMP());