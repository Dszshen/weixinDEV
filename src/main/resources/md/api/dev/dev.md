## 功能：登陆
### 请求
* 请求url: /login
* 请求方式: post
* 参数
* `{"id":"id"}`
### 返回值：如果成功，返回user信息，如果不成功，返回null
 `{
   "flag":"success/failure/error",
   "code":"200/300/500",
   "message":"登陆失败/成功。。。"
   "data":{
    "user":{
       "id":"id",
       "username":"admin"
      }
   }
}`

### 系统参数配置--微信配置
* 请求:/sysManage/sysParams
* 请求方式:post
* 参数
**** `{"id":"id"}`

### 系统参数配置--邮件系统配置
* 请求:/sysManage/sysParams
* 请求方式:post
* 参数
**** `{"id":"id"}`