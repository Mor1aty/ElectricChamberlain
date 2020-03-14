# app 端

## 1、API 接口说明

- 接口基准地址：`http://127.0.0.1:8760/api/app/`
- 服务端已开启 CORS 跨域支持
- API v1 认证统一使用 Token 认证
- 需要授权的 API ，必须在请求头中使用 `Authorization` 字段提供 `token` 令牌
- 数据返回格式统一使用 JSON

### 1.1、支持的请求方式

- GET（SELECT）：从服务器取出资源（一项或多项）
- POST（CREATE）：在服务器新建一个资源
- PUT（UPDATE）：在服务器更新资源
- DELETE（DELETE）：从服务器删除资源

### 1.2、通用返回说明

| 参数 | 名称     | 描述               |
| ---- | -------- | ------------------ |
| code | 返回码   | 0 为成功，1 为失败 |
| data | 返回数据 | 返回的数据         |
| msg  | 返回信息 | 返回描述字符串     |

## 2、用户

### 2.1、登录

- 请求路径：user/login
- 请求方法：post
- 请求参数

| 参数名   | 参数类型 | 参数说明 | 备注     |
| -------- | -------- | -------- | -------- |
| phone    | String   | 用户手机 | 不能为空 |
| password | String   | 密码     | 不能为空 |

- 响应数据

| 参数名         | 参数类型 | 参数说明 | 备注                           |
| -------------- | -------- | -------- | ------------------------------ |
| phone          | String   | 用户手机 |                                |
| nickname       | String   | 用户昵称 |                                |
| money          | long     | 用户余额 |                                |
| portrait       | Object   | 用户头像 |                                |
| - id           | long     | 附件 id  |                                |
| - type         | int      | 附件类型 |                                |
| - fileLocation | String   | 附件位置 |                                |
| - context      | String   | 附件描述 |                                |
| type           | int      | 用户类型 | 0 为普通用户，1 为电能采暖用户 |
| token          | String   | 令牌     | 基于 jwt 的令牌                |

- 响应数据

```json
{
    "code": 0,
    "msg": "登录成功",
    "data": {
        "phone": "15502416971",
        "nickname": "李四",
        "money": 10,
        "portrait": {
            "id": 2,
            "type": 0,
            "fileLocation": "D:\\File\\ElectricChamberlain\\attach\\df772143deb541efa437930f440cc3d2_4397b691bfa05dc27b2487e67a91dbf8.jpg",
            "context": "15502416971的头像"
        },
        "type": 10,
        "token": "7985449be95c47509161200644ad4b28"
    }
}
```

### 2.2、手机号登录/注册

- 请求路径：user/phone
- 请求方法：get
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注     |
| ------ | -------- | -------- | -------- |
| phone  | String   | 用户手机 | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 0,
    "msg": "短信发送成功",
    "data": null
}
```

### 2.3、手机号验证

- 请求路径：user/phone
- 请求方法：post
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注     |
| ------ | -------- | -------- | -------- |
| phone  | String   | 用户手机 | 不能为空 |
| code   | int      | 验证码   | 不能为空 |

- 响应数据

| 参数名         | 参数类型 | 参数说明 | 备注                           |
| -------------- | -------- | -------- | ------------------------------ |
| phone          | String   | 用户手机 |                                |
| nickname       | String   | 用户昵称 |                                |
| money          | long     | 用户余额 |                                |
| portrait       | Object   | 用户头像 |                                |
| - id           | long     | 附件 id  |                                |
| - type         | int      | 附件类型 |                                |
| - fileLocation | String   | 附件位置 |                                |
| - context      | String   | 附件描述 |                                |
| type           | int      | 用户类型 | 0 为普通用户，1 为电能采暖用户 |
| token          | String   | 令牌     | 基于 jwt 的令牌                |

- 响应数据

```json
{
    "code": 0,
    "msg": "登录成功",
    "data": {
        "phone": "15502416971",
        "nickname": "李四",
        "money": 10,
        "portrait": {
            "id": 2,
            "type": 0,
            "fileLocation": "D:\\File\\ElectricChamberlain\\attach\\df772143deb541efa437930f440cc3d2_4397b691bfa05dc27b2487e67a91dbf8.jpg",
            "context": "15502416971的头像"
        },
        "type": 10,
        "token": "eb4b7e7a23ec4dfba3fcb2f13d6776ef"
    }
}
```

### 2.4、修改呢称

- 请求路径：user/nickname
- 请求方法：put
- 请求参数

| 参数名   | 参数类型 | 参数说明 | 备注     |
| -------- | -------- | -------- | -------- |
| nickname | String   | 用户昵称 | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 0,
    "msg": "修改成功",
    "data": null
}
```

### 2.5、修改头像

- 请求路径：user/portrait
- 请求方法：put
- 请求参数

| 参数名   | 参数类型 | 参数说明 | 备注     |
| -------- | -------- | -------- | -------- |
| portrait | file     | 用户头像 | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 0,
    "msg": "修改成功",
    "data": null
}
```

### 2.6、修改密码

- 请求路径：user/password
- 请求方法：post
- 请求参数

| 参数名  | 参数类型 | 参数说明 | 备注     |
| ------- | -------- | -------- | -------- |
| oldPass | String   | 旧密码   | 不能为空 |
| newPass | String   | 新密码   | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 0,
    "msg": "修改成功",
    "data": null
}
```

## 3、申请

### 3.1、用户升级申请

- 请求路径：apply/identity
- 请求方法：put
- 请求参数

| 参数名  | 参数类型 | 参数说明 | 备注     |
| ------- | -------- | -------- | -------- |
| context | String   | 申请内容 | 不能为空 |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 0,
    "msg": "申请成功",
    "data": null
}
```



## 4、交易

### 4.1、用户交易

- 请求路径：deal/money
- 请求方法：put
- 请求参数

| 参数名  | 参数类型 | 参数说明 | 备注                                     |
| ------- | -------- | -------- | ---------------------------------------- |
| type    | int      | 交易类型 | 0 为升级交易，1 为转账交易，2 为活动交易 |
| payer   | String   | 付款人   |                                          |
| payee   | String   | 收款人   |                                          |
| amount  | long     | 金额     |                                          |
| context | String   | 描述     |                                          |
|         |          |          |                                          |

- 响应数据

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

```json
{
    "code": 0,
    "msg": "转账成功",
    "data": null
}
```

## 5、电价

### 5.1、电价展示

- 请求路径：electricity/electricityPrice
- 请求方法：get
- 请求参数

| 参数名    | 参数类型 | 参数说明 | 备注 |
| --------- | -------- | -------- | ---- |
| city      | String   | 省市区   |      |
| startTime | String   | 开始时间 |      |
| endTime   | String   | 结束时间 |      |

- 响应数据

| 参数名        | 参数类型 | 参数说明         | 备注       |
| ------------- | -------- | ---------------- | ---------- |
| ordinaryMoney | int      | 普通用户电价     | 单位 元/度 |
| discountMoney | int      | 电能采暖用户电价 | 单位 元/度 |
| city          | String   | 省市区           |            |
| priceTime     | String   | 时间             |            |

- 响应数据

```json
{
    "code": 0,
    "msg": "查询成功",
    "data": [
        {
            "id": 3,
            "ordinaryMoney": 20,
            "discountMoney": 10,
            "city": "辽宁省大连市",
            "priceTime": "2020-03-14T14:40:38"
        }
    ]
}
```

## 6、活动

### 6.1、获取每日红包

- 请求路径：activity/redPaper
- 请求方法：get
- 请求参数

| 参数名 | 参数类型 | 参数说明 | 备注 |
| ------ | -------- | -------- | ---- |
|        |          |          |      |

- 响应数据

| 参数名     | 参数类型 | 参数说明 | 备注    |
| ---------- | -------- | -------- | ------- |
| money      | long     | 金额     | 单位 元 |
| payer      | Object   | 支付用户 |         |
| - phone    | String   | 用户手机 |         |
| - nickname | String   | 用户昵称 |         |

- 响应数据

```json
{
    "code": 0,
    "msg": "获取成功",
    "data": {
        "money": 5,
        "payer": {
            "phone": "1000",
            "nickname": "电价管家",
            "money": 0,
            "portrait": null,
            "type": 0
        }
    }
}
```

