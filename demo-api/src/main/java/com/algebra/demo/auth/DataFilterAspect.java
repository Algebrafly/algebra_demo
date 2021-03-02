package com.algebra.demo.auth;

import com.algebra.demo.util.MdcConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @apiNote 数据权限处理类
 * @since 2021/2/22 14:05
 * @author al
 * @version v1.0.0
 */
@Aspect
@Slf4j
public class DataFilterAspect {

    @Autowired
    private DataFilterHandler dataFilterHandler;

    @Pointcut("@annotation(DataFilter) || execution(public * @DataFilter *.*(..)))")
    public void dataFilterCut() {}

    @Before("dataFilterCut()")
    public void beforeDataFilter(JoinPoint point) throws Throwable {
        // open data filter
        MDC.put(DataFilterUtils.SQL_FILTER_ENABLE, "true");
        Method currentMethod = getCurrentMethod(point);
        DataFilter dataFilter = currentMethod.getAnnotation(DataFilter.class);
        String custstoreidAlias = dataFilter.custstoreidAlias();
        MDC.put(DataFilterUtils.SQL_ALIAS_CUSTSTOREID, custstoreidAlias);
        String equipmentAlias = dataFilter.equipmentAlias();
        MDC.put(DataFilterUtils.SQL_ALIAS_EQUIPMENT, equipmentAlias);
        // deal filter data
        String userId = MDC.get(MdcConstant.USER_ID);
        // TODO 测试一下
        userId = "136fddbf-616b-4b9b-9f63-116d350372f1";
        // if userid is null, close data filter
        if (StringUtils.isEmpty(userId)) {
            closeDataFilter();
            return ;
        }


        int dataType = 0;
        try {
            dataType = dataFilterHandler.getDataTypeByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO 获取用户角色的行数据权限信息（查询出现异常就查看本部门）
            dataType = 2;
        }

        ///dataType=4;
        if (DataFilterHandler.isSeeAll(dataType)) {
            closeDataFilter();
            return ;
        }

        String custstoreFilterData = dataFilterHandler.getCuststoreFilterDataByUserId(userId, dataType);
        String carFilterData = dataFilterHandler.getCarFilterDataByUserId(userId, dataType);
        ///custstoreFilterData = "[\"1\",\"2\"]";
        if (DataFilterHandler.notHasFilterData(custstoreFilterData,carFilterData)) {
            closeDataFilter();
            return ;
        }
        MDC.put(DataFilterUtils.SQL_FILTER_DATA_CUSTSTOREID, custstoreFilterData);
        MDC.put(DataFilterUtils.SQL_FILTER_DATA_EQUIPMENT, carFilterData);

    }

    private void closeDataFilter() {
        MDC.put(DataFilterUtils.SQL_FILTER_ENABLE, "false");
    }

    private Method getCurrentMethod(JoinPoint point) throws NoSuchMethodException {
        Method method;
        //具体要访问的类
        Class clazz = point.getTarget().getClass();
        //获取访问的方法的名称
        String methodName = point.getSignature().getName();
        //获取访问的方法的参数
        Object[] args = point.getArgs();
        //获取具体执行的方法的Method对象
        /**
         * 这里有个重点内容，获取的参数里不能有ModelMap，否则获取不到，即为null
         * 例如：@RequestMapping("/kan")
         public List kan(ModelMap map)!!
         **/
        if (args == null || args.length == 0) {
            //只能获取无参数的方法
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            for (Class classArg : classArgs) {
                System.out.println("classArg="+classArg);
            }
            System.out.println("clazz="+clazz);
            System.out.println("methodName="+methodName);
            System.out.println("classArgs="+classArgs);
            method = clazz.getMethod(methodName, classArgs);
        }
        return method;
    }

    @After("dataFilterCut()")
    public void afterDataFilter(JoinPoint point) throws Throwable {
        // clear DataFilter data
        MDC.remove(DataFilterUtils.SQL_FILTER_ENABLE);
        MDC.remove(DataFilterUtils.SQL_FILTER_DATA_CUSTSTOREID);
        MDC.remove(DataFilterUtils.SQL_FILTER_DATA_EQUIPMENT);
        MDC.remove(DataFilterUtils.SQL_ALIAS_CUSTSTOREID);
        MDC.remove(DataFilterUtils.SQL_ALIAS_EQUIPMENT);
    }

}
