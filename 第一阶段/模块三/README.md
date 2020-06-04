# liululu-lagouHomeWork-module3

作业一：
手写MVC框架基础上增加如下功能
1）定义注解@Security（有value属性，接收String数组），该注解用于添加在Controller类或者Handler方法上，表明哪些用户拥有访问该Handler方法的权限（注解配置用户名）
2）访问Handler时，用户名直接以参数名username紧跟在请求的url后面即可，比如http://localhost:8080/demo/handle01?username=zhangsan
3）程序要进行验证，有访问权限则放行，没有访问权限在页面上输出
注意：自己造几个用户以及url，上交作业时，文档提供哪个用户有哪个url的访问权限

url:username:

/demo/handle01:admin;lulu;yingdian;zhangsan

/demo/query:admin;lulu



 

作业二：
需求：实现登录页面（简易版即可），实现登录验证功能、登录之后跳转到列表页，查询出 tb_resume 表【表数据和课上保持一致】的所有数据
（列表不要求分页，在列表右上方有“新增”按钮，每一行后面有“编辑”和“删除”按钮，并实现功能），如果未登录就访问url则跳转到登录页面，用户名和密码固定为admin/admin
技术要求：根据SSM整合的思路，进行SSS整合（Spring+SpringMVC+SpringDataJPA）,登录验证使用SpringMVC拦截器实现
【提交时统一数据库名test，用户名和密码root】