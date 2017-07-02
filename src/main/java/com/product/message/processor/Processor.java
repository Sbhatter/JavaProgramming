package com.product.message.processor;

import java.util.HashMap;

import com.product.message.domain.Product;

/**
 * 
 * Class Name: Processor
 *
 * Description: A Processor interface is responsible for abstraction.
 * 
 * @author Sujata Bhatter
 * @version 1.0
 * @since 2017-07-02
 */
public interface Processor {

	public HashMap<String, Product> processInputMessage(String message);

	public void process(Product product);

	static Processor newProcessor() {
		ProcessorFactory processorFactory = new ProcessorFactory();
		return processorFactory.getInstance();
	}
}
