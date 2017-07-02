package com.product.message.processor;

import java.lang.reflect.Proxy;

/**
 * 
 * Class Name: ProcessorFactory
 *
 * Description: A ProcessorFactory class is responsible for getting the instance
 * of Processor class
 * 
 * @author Sujata Bhatter
 * @version 1.0
 * @since 2017-07-02
 */
public class ProcessorFactory {
	public Processor getInstance() {
		return (Processor) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { Processor.class },
				new ProcessInvocationHandlerInterceptor(new MessageProcessor()));
	}
}
