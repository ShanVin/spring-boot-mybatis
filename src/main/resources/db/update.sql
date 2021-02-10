-- 创建数据库
create database project;

-- 创建用户
create user "shanvin"@"%" identified by 'Project3306!';

-- 授权
grant all on project.* to 'shanvin'@'%';

-- 切换数据库
use project;

-- 创建用户表
create table user (
    user_id varchar(50) not null,
    user_nm varchar(300) not null
) engine=InnoDB default charset=utf8;

-- 新增数据
insert user(user_id, user_nm) value ('shanvin', 'shanvin');
commit;