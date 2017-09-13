package com.dancer.provalidation.proxy;

import java.lang.reflect.Proxy;

public class ProxyMain {

	public static void main(String[] args) {
		BusinessProcessorImpl impl=new BusinessProcessorImpl();
		BusinessProcessorHandler handler=new BusinessProcessorHandler(impl);
		BusinessProcessor processor=(BusinessProcessor) Proxy.newProxyInstance(impl.getClass().getClassLoader(), impl.getClass().getInterfaces(), handler);
		processor.processBusiness();
		System.out.println("...................");
		processor.generateText();
//		System.out.println(processor);
		System.out.println(processor.getClass().getName());
	}

}
