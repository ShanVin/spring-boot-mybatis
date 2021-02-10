-- 删除数据库
drop database project;

-- 删除用户
drop user shanvin;

-- 撤销授权
revoke all on project.* from 'shanvin'@'%';

-- 删除用户表
drop table user;