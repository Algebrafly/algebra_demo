# 基于MyBatisPlus-3.4.*的行数据权限控制

## 前言
1. 项目主要是根据`MyBatisPlus-3.4.*`框架的sql拦截器进行了部分修改； 
2. 通过`aop`来实现了注解的拦截，以及方法执行期间临时数据的赋值和清空；
3. 方法执行期间的临时数据是通过`@Slf4j` 的`MDC`来进行了传递；
## 1，功能介绍
行数据权限是为了使不同人员或角色查看不同的数据，本质就是为了控制数据库的查询， 
在查询数据的时候添加查询条件来控制数据的展示，本功能就是通过注入sql来实现行数据权限的。
## 2，使用方式
1. 启动类中添加`@EnableDataFilter`来启动主要模块的bean构建
2. 添加系统配置，开启数据过滤功能
```yml
frc:
  authority:
    data-filter-enable: true 
```
3. 在需要进行行数据权限过滤的查询接口上添加`@DataFilter`注解，注解可以配置参数`alias`
   来控制注入语句的查询别名，如果不配置别名则默认为`userid`
```java
public class Demo {
    @DataFilter(alias = "1")
    public WebApiResult<List<SysRoleVo>> getRoleList() {
        return iRoleService.selectRoleListByRoleName(null);
    }
}
```
```text
原语句：
==> Preparing: SELECT * FROM sys_role WHERE deleted = false 
==> Parameters: 
<== Total: 7
注入后：
==> Preparing: SELECT * FROM sys_role WHERE deleted = false AND 1 IN ('1', '2', '3') 
==> Parameters: 
<== Total: 7
```
## 3，实现原理
本模块的主要原理就是sql拦截，然后对sql语句进行再拼接来实现查询的过滤；
* 通过`@EnableDataFilter`注解的配置，来实现类`DataFilterConfiguration`的加载，
  `DataFilterConfiguration`主要功能是将`DataFilterAspect`，`DataFilterHandler`，
  `DataFilterSqlInjector`这三个类进行bean的注入，也相当于开启数据权限功能；
  

* 通过AOP来拦截，`DataFilterAspect`是一个注解切面，切点为`@annotation(DataFilter)`
  已经配置过`@DataFilter`注解的方法，回进入切面，在切面中进行拦截器所需数据的赋值和清空，
  此处会调用`DataFilterHandler`进行数据的查询或数据的处理等；
  ```
  ！！！需要注意的是，DataFilterHandler类中会进行数据库的查询，
  这里使用的JdbcTemplate进行数据库查询，springboot中会注入该bean对象。
  ```
  

* `DataFilterSqlInjector`为sql拦截器，也是主要的功能模块，实现sql的拦截和注入；
  拦截入口方法为`intercept(Invocation invocation)`
  
## 4，待改进之处
1. 需要根据表名来过滤sql语句，以免在多sql查询时出现全部注入sql的情况


    