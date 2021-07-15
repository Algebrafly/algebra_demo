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
        DefaultContext<String, Object> context1 = new DefaultContext<>();
        context1.put("a",1);
        context1.put("b",1);
        context1.put("c",3);

        DefaultContext<String, Object> context2 = new DefaultContext<>();
        String exp =  "import com.alibaba.compileflow.demo.Person;" +
                "person=new Person();" +
                "person.sayHello();" +
                "person.sayHelloStatic();";

        Object execute = null;
        try {
            execute = runner.execute("(a+b)*c", context1, null, true, true);
//            runner.execute(exp, context2, null, true, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(execute);

    }

}
