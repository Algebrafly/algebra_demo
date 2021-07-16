package com.algebra.authentication.util.bpm;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @author al
 * @date 2021/7/14 9:45
 * @description
 */
public class TestQlExp {

    public static void main(String[] args) {

        ExpressRunner runner = new ExpressRunner();

        // 基本操作符：算数、逻辑、移位等都支持
        DefaultContext<String, Object> context1 = new DefaultContext<>();
        context1.put("a",1);
        context1.put("b",2);
        context1.put("c",3);

        // 支持基本的java语法：for循环，if判断，三目运算，Array，List，Map 以及简单的语法糖
        DefaultContext<String, Object> context3 = new DefaultContext<>();
        String exp3 = "abc=NewMap('a':1,'b':2,'c':3);return abc.get('a')+abc.get('b')+abc.get('c');";

        // 支持JS脚本语法
        DefaultContext<String, Object> context4 = new DefaultContext<>();
        String exp4 = "function add(int a,int b){" +
                "return a+b;" +
                "};" +
                "a=2;b=2;" +
                "return add(a,b);";

        // 绑定java对象
        DefaultContext<String, Object> context2 = new DefaultContext<>();
        String exp2 =  "import com.alibaba.compileflow.demo.Person;" +
                "person=new Person();" +
                "person.sayHello();" +
                "person.sayHelloStatic();";

        // 自定义操作符



        Object execute = null;
        try {
            execute = runner.execute("a^b", context1, null, true, true);
            Object execute1 = runner.execute(exp3, context3, null, true, true);
            Object execute2 = runner.execute(exp4, context4, null, true, true);

            System.out.println(execute1);
            System.out.println(execute2);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
