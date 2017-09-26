package org.fsdcic.xjh.mongoClient.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by ben on 2017-9-25.
 */

public class Advices implements MethodInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        before();
        Object obj = methodInvocation.proceed();
        after();
        return  obj;
    }

    private void after() {
        System.out.println("after");
    }

    private void before() {
        System.out.println("before");
    }
}
