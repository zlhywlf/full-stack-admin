DROP TABLE IF EXISTS student;
CREATE TABLE IF NOT EXISTS student
(
    id          integer PRIMARY KEY AUTOINCREMENT,
    岗位名称    varchar(255),
    行业        varchar(255),
    融资情况    varchar(255),
    公司规模    varchar(255),
    发布时间    varchar(255),
    工资        varchar(255),
    经验        varchar(255),
    学历        varchar(255),
    地址        varchar(255),
    招聘人title varchar(255),
    职位简介    text
);