## 数据库表设计
### 角色表
|ID|英文名称|中文名称|描述|状态|创建日期|更新日期|失效日期|
|--|-------|-------|-------|--------|----------|-----------|-----------|
|id|role_en |role_cn |description|state|createTime|updateTime|forbidTime|

### 权限表
|ID|英文名称|中文名称|描述|状态|创建日期|生效日期|失效日期|
|--|-------|-------|------|--------|----------|-----------|-----------|
|id|en     |cn     |description|state|createtime|effectivetime|invalidtime|

### 用户信息表
|ID|用户名|密码|真实姓名|昵称|年龄|性别|生日|头像|手机号|邮箱|身份证|QQ|微信|城市|详细地址|个人简介|创建时间|更新时间|禁用时间|是否锁定|
|--|-------|------|----|----|----|-----|-----|---|-----|------|------|----|---|---|-------|-------|-------|---|---|--|
|id|username |password |realname|nickname|age|sex|birthday|headimage|mobile|email|idcard|qq|weixin|city|address|description|createTime|updateTime|lockTime|lock|
