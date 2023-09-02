USE cloud;
DROP TABLE IF EXISTS member;
CREATE TABLE member
(
    id     bigint NOT NULL COMMENT 'id',
    mobile varchar(11) COMMENT '手机号',
    PRIMARY KEY (id),
    UNIQUE KEY mobile_unique (mobile)
) COMMENT ='会员';