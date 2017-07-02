package com.product.message.processor;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.product.message.domain.Product;
import com.product.message.report.Report;

public class MessageProcessorTest {

	private MessageProcessor instance;
	public static HashMap<String, Product> productHashMap = new HashMap<>();
	Report log = new Report();
	ArrayList<String> messageList = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		instance = MessageProcessor.getInstance();
		messageList.add("apple at 10p");
		messageList.add("Add 20p apples");
	}

	/**
	 * Test case : processInputMessageTypeSale
	 * 
	 * Description : This test case check if the list got populated with the
	 * messages received.
	 */
	@Test
	public void processInputMessage() throws Exception {

		for (String message : messageList) {
			productHashMap.putAll(instance.processInputMessage(message));
		}

		assertTrue(productHashMap.size() > 0);

	}

	/**
	 * Test case : processInputMessageTypeAdjustment
	 * 
	 * Description : This test case test if the adjustment list get populated
	 * once it received adjustment message.
	 */
	@Test
	public void processInputMessageTypeAdjustment() throws Exception {
		Product lastMapRecord = null;
		for (String message : messageList) {
			productHashMap.putAll(instance.processInputMessage(message));
		}
		Iterator<Map.Entry<String, Product>> iteratorMap = productHashMap.entrySet().iterator();
		while (iteratorMap.hasNext()) {
			lastMapRecord = iteratorMap.next().getValue();
		}
		assertTrue(productHashMap.size() > 0);
		assertTrue(lastMapRecord.getAdjustmentMessage().size() > 0);
	}

	/**
	 * Test case : generateReport
	 * 
	 * Description : This test case test if the list get populated once it
	 * received 10 messages.
	 */
	@Test
	public void generateReport() throws Exception {

		HashMap<String, Product> productHashMapTest = new HashMap<>();
		int counter = 10;
		for (String message : messageList) {
			productHashMapTest.putAll(instance.processInputMessage(message));
		}
		String expectedOutputContains = "Product Name: apple  Total Number Of Product: 3  Total Sale Amount: 1.5p";
		ByteArrayOutputStream outContentTrue = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContentTrue));
		log.generateLog(productHashMapTest, counter);
		assertTrue(outContentTrue.toString().contains(expectedOutputContains));
		counter = 5;
		ByteArrayOutputStream outContentFlase = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContentFlase));
		log.generateLog(productHashMapTest, counter);
		assertFalse(outContentFlase.toString().contains(expectedOutputContains));
	}
}
