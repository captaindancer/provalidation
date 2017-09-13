package com.dancer.provalidation.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BusinessProcessorHandler implements InvocationHandler {

	private Object target;
	
	public BusinessProcessorHandler(Object target) {
		this.target=target;
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Method : "+method);
		System.out.println("proxy......before real impl class");
		Object result=method.invoke(target, args);
		System.out.println("proxy......after real imp class");
		System.out.println(result);
		return result;
	}

}
