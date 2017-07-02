package com.product.message.processor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 
 * Class Name: ProcessInvocationHandlerInterceptor
 *
 * Description: A ProcessInvocationHandlerInterceptor class act as invocation
 * handler for Processor class
 * 
 * @author Sujata Bhatter
 * @version 1.0
 * @since 2017-07-02
 */

public class ProcessInvocationHandlerInterceptor implements InvocationHandler {

	private MessageProcessor messageProcessor;

	public ProcessInvocationHandlerInterceptor(MessageProcessor messageProcessor) {
		this.messageProcessor = messageProcessor;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		return method.invoke(this.messageProcessor, args);
	}

}
