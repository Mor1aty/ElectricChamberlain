# 数据库（electric_chamberlain）

```mysql
CREATE DATABASE `electric_chamberlain` CHARSET utf8;
```



## 用户表（user）

|  字段名  |       类型       |         约束          |                             描述                             |
| :------: | :--------------: | :-------------------: | :----------------------------------------------------------: |
|  phone   |   varchar(20)    |         主键          |                      用户手机，唯一登录                      |
| password |   varchar(20)    |                       |                 密码，默认为空，为空时不生效                 |
| nickname |   varchar(20)    |         非空          |                  用户昵称，默认为用户手机号                  |
|  money   | bigint unsigned  |         非空          |                      用户余额，默认为 0                      |
| portrait | bigint unsigned  | 外键，关联到附件表 id |                 用户头像，默认为默认头像(1)                  |
|   type   | tinyint unsigned |         非空          | 用户类型，0 为普通用户，1 为电能采暖用户，默认为 0，10 为申请中状态 |

```mysql
CREATE TABLE `user`(
	`phone` VARCHAR(20),
    `password` VARCHAR(20) DEFAULT NULL,
    `nickname` VARCHAR(20) NOT NULL,
    `money` BIGINT UNSIGNED NOT NULL DEFAULT 0,
    `portrait` BIGINT UNSIGNED NOT NULL DEFAULT 1,
    `type` TINYINT UNSIGNED NOT NULL DEFAULT 0,
    PRIMARY KEY(`phone`),
    CONSTRAINT fk_user_attach FOREIGN KEY(`portrait`) REFERENCES `attach`(`id`)
)CHARSET utf8 ENGINE INNODB;
```

## 申请表（apply）



|   字段名   |       类型       |           约束           |                     描述                     |
| :--------: | :--------------: | :----------------------: | :------------------------------------------: |
|     id     | bigint unsigned  |           主键           |              申请 id，自增主键               |
|    user    |   varchar(20)    | 外键，关联到用户表 phone |                   申请用户                   |
|    type    |       int        |           非空           | 申请类型，0 为普通用户升级为电能采暖用户申请 |
|  context   |   varchar(255)   |           非空           |                   申请内容                   |
|  is_pass   | tinyint unsigned |           非空           |  通过标记，0 为未通过，1 为已通过，默认为 0  |
| apply_time |     datetime     |           非空           |                   申请时间                   |

```mysql
CREATE TABLE `apply`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
    `user` VARCHAR(20),
    `type` INT NOT NULL,
    `context` VARCHAR(255) NOT NULL,
    `is_pass` TINYINT UNSIGNED NOT NULL DEFAULT 0,
    `apply_time` DATETIME NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_apply_user FOREIGN KEY(`user`) REFERENCES `user`(`phone`)
)CHARSET utf8 ENGINE INNODB;
```

## 交易记录表（deal_record）

|   字段名    |      类型       |         约束          |                        描述                        |
| :---------: | :-------------: | :-------------------: | :------------------------------------------------: |
|     id      | bigint unsigned |         主键          |                 记录 id，自增主键                  |
|    type     |       int       |         非空          | 交易类型，0 为升级交易，1 为转账交易，2 为活动交易 |
|    payer    |   varchar(20)   | 外键，关联到用户表 id |                       付款人                       |
|    payee    |   varchar(20)   | 外键，关联到用户表 id |                       收款人                       |
|   amount    | bigint unsigned |         非空          |                        金额                        |
|   context   |  varchar(255)   |         非空          |                        描述                        |
| record_time |    datetime     |         非空          |                      记录时间                      |

```mysql
CREATE TABLE `deal_record`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
    `type` INT,
    `payer` VARCHAR(20),
    `payee` VARCHAR(20),
    `amount` BIGINT UNSIGNED NOT NULL,
    `context` VARCHAR(255) NOT NULL,
    `record_time` DATETIME NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_record_user_payer FOREIGN KEY(`payer`) REFERENCES `user`(`phone`),
    CONSTRAINT fk_record_user_payee FOREIGN KEY(`payee`) REFERENCES `user`(`phone`)
)CHARSET utf8 ENGINE INNODB;
```

## 电价表（electricity_price）

|     字段名     |      类型       | 约束 |             描述             |
| :------------: | :-------------: | :--: | :--------------------------: |
|       id       | bigint unsigned | 主键 |      电价 id，自增主键       |
| ordinary_money | bigint unsigned | 非空 |   普通用户电价，单位 元/度   |
| discount_money | bigint unsigned | 非空 | 电能采暖用户电价，单位 元/度 |
|      city      |  varchar(255)   | 非空 |            省市区            |
|   price_time   |    datetime     | 非空 |             时间             |

```mysql
CREATE TABLE `electricity_price`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
    `ordinary_money` BIGINT UNSIGNED NOT NULL,
    `discount_money` BIGINT UNSIGNED NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `price_time` DATETIME NOT NULL,
    PRIMARY KEY(id)
)CHARSET utf8 ENGINE INNODB;
```

## 活动表（activity）

|    字段名     |       类型       | 约束 |              描述              |
| :-----------: | :--------------: | :--: | :----------------------------: |
|      id       | bigint unsigned  | 主键 |       活动 id，自增主键        |
|     type      |       int        | 非空 |      活动类型，0 为发红包      |
| money_section |   varchar(50)    | 非空 |  金额区间，用 - 分割，单位元   |
|    is_end     | tinyint unsigned | 非空 | 结束标记，0 为未结束，1 为结束 |
| activity_time |     datetime     | 非空 |          活动截至时间          |

```mysql
CREATE TABLE `activity`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
   	`type` INT NOT NULL,
    `money_section` VARCHAR(50) NOT NULL,
    `is_end` TINYINT UNSIGNED NOT NULL,
    `activity_time` DATETIME NOT NULL,
    PRIMARY KEY(id)
)CHARSET utf8 ENGINE INNODB;
```

## 附件表（attach）

|    字段名     |      类型       | 约束 |        描述        |
| :-----------: | :-------------: | :--: | :----------------: |
|      id       | bigint unsigned | 主键 | 附件 id，自增主键  |
|     type      |       int       | 非空 | 附件类型，0 为头像 |
| file_location |  varchar(255)   | 非空 |      附件位置      |
|    context    |  varchar(255)   | 非空 |      附件描述      |

```mysql
CREATE TABLE `attach`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
   	`type` INT NOT NULL,
    `file_location` VARCHAR(255) NOT NULL,
   	`context` VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
)CHARSET utf8 ENGINE INNODB;
```

## 执行 sql

```mysql
CREATE DATABASE `electric_chamberlain` CHARSET utf8;
USE `electric_chamberlain`;
CREATE TABLE `electricity_price`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
    `ordinary_money` BIGINT UNSIGNED NOT NULL,
    `discount_money` BIGINT UNSIGNED NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `price_time` DATETIME NOT NULL,
    PRIMARY KEY(id)
)CHARSET utf8 ENGINE INNODB;
CREATE TABLE `activity`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
   	`type` INT NOT NULL,
    `money_section` VARCHAR(50) NOT NULL,
    `is_end` TINYINT UNSIGNED NOT NULL,
    `activity_time` DATETIME NOT NULL,
    PRIMARY KEY(id)
)CHARSET utf8 ENGINE INNODB;
CREATE TABLE `attach`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
   	`type` INT NOT NULL,
    `file_location` VARCHAR(255) NOT NULL,
   	`context` VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
)CHARSET utf8 ENGINE INNODB;
CREATE TABLE `user`(
	`phone` VARCHAR(20),
    `password` VARCHAR(20) DEFAULT NULL,
    `nickname` VARCHAR(20) NOT NULL,
    `money` BIGINT UNSIGNED NOT NULL DEFAULT 0,
    `portrait` BIGINT UNSIGNED NOT NULL DEFAULT 1,
    `type` TINYINT UNSIGNED NOT NULL DEFAULT 0,
    PRIMARY KEY(`phone`)
)CHARSET utf8 ENGINE INNODB;
CREATE TABLE `apply`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
    `user` VARCHAR(20),
    `type` INT NOT NULL,
    `context` VARCHAR(255) NOT NULL,
    `is_pass` TINYINT UNSIGNED NOT NULL DEFAULT 0,
    `apply_time` DATETIME NOT NULL,
    PRIMARY KEY(id)
)CHARSET utf8 ENGINE INNODB;
CREATE TABLE `deal_record`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
    `type` INT,
    `payer` VARCHAR(20),
    `payee` VARCHAR(20),
    `amount` BIGINT UNSIGNED NOT NULL,
    `context` VARCHAR(255) NOT NULL,
    `record_time` DATETIME NOT NULL,
    PRIMARY KEY(id)
)CHARSET utf8 ENGINE INNODB;
```

