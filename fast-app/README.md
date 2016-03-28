## 项目介绍
* fast_app 属于APP接口快速开发项目自带mybatis和hibernate框架随意切换 依赖于核心自定义ants-core.jar
* 主要框架是srping4.1.2+mybatis3.2.5 或 hibernate 4.3.10

### 模块
* bean: 参数实体,带注解校验.
* config: 配置一些拦截参数获或者系统相关配置, 利用注解绑定了resources.properties里面的key
* contrller: 里面只有一个动态调用类, 利用类反射和方法反射做到动态调用Service;
* filter: 请求拦截处理;
* mybatis: 数据层, 采用Mybais3 接口规范;
* hibernate: 数据层, 采用hiernate4 orm框架;
* service: 业务层, 采用spring注解, 包前面带上版本号;

#### 管理
* maven(3.3.3)依赖和项目管理
* git版本控制 或 svn版本控制

#### 技术选型
* IoC容器 spring
* web框架 springmvc
* orm框架 mybatis3.0
* 自定义Filter简单权限验证, 过滤xss攻击(可以配置拦截IP、URL、类方法)
* 验证框架 hibernate validator, spring validate
* 数据源 druid
* 日志 slf4j+logback, log4jdbc 可以打印出sql详细参数
* Json 阿里 fastjson
* servlet 3.0(需要支持servlet3的servlet容器，如tomcat7) 自带注解


#### 开发工具
* Mysql 5.6
* Jdk 1.7
* Tomcat 7.0
* Intellij Idea 或 Eclipse

#### 注意事项
* 请求链接满足
/v{version}/{service}/{method}
version 接口版本
service 接口业务类  String类型 需要调用的service类大写开头 当前com.ants.project.fast.app."+version版本号+".service.impl." +service名称+"Service
method  接口方法

* Rquest参数必须满足

    jsonData       String类型 JSON格式数据
    validBean      String类型 bean实体名称 (可以省略, 省略时不校验jsonData数据, 方法参数为HashMap或者无参)

* Service层参数
    只有Bean实体和HashMap或者无参,当Request里面的bean参数存在时候为bean实体, 不存在则为HashMap或者无参
