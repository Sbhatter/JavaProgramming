package com.product.message.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.product.message.domain.Product;

/**
 * 
 * Class Name: Report
 *
 * Description: A Report class is responsible for logging the product details.
 * 
 * @author Sujata Bhatter
 * @version 1.0
 * @since 2017-07-02
 */
public class Report {
	/**
	 * This method is responsible for logging report of product sale detail
	 * after every 10th message received and pauses after 50 messages received
	 * and displays the adjustment details.
	 * 
	 * @param HashMap
	 *            This is the map with Product Name as key and Product as value
	 * @param int
	 *            This is the counter for the message received.
	 */
	public void generateLog(HashMap<String, Product> productHashMap, int incomingMessageCount) {
		if (incomingMessageCount % 50 == 0) {
			Product lastMapRecord = null;
			Iterator<Map.Entry<String, Product>> iteratorMap = productHashMap.entrySet().iterator();
			while (iteratorMap.hasNext()) {
				lastMapRecord = iteratorMap.next().getValue();
			}
			generateAdjustmentReport(lastMapRecord.getAdjustmentMessage());
		} else if (incomingMessageCount % 10 == 0) {
			generateGeneralReport(productHashMap);
		}

	}

	/**
	 * This method is responsible for logging report of product sale detail
	 * after every 10th message received,
	 * 
	 * @param HashMap
	 *            This is the map with Product Name as key and Product as value
	 */
	public void generateGeneralReport(HashMap<String, Product> productHashMap) {
		System.out.println(
				"-----------------------------------Process is pausing, unable to accept new messages-----------------------------------");
		for (String key : productHashMap.keySet()) {
			Product product = productHashMap.get(key);
			System.out.println("Product Name: " + product.getProductName() + "  " + "Total Number Of Product: "
					+ product.getQuantity() + "  " + "Total Sale Amount: " + product.getTotalProductPrice() + "p");
		}

	}

	/**
	 * This method is responsible for logging report of adjustments done during
	 * run time to products after 50 messages is received and stops processing
	 * any further messages after that
	 * 
	 * @param ArrayList
	 *            This is the list of adjustment message done during run time to
	 *            the products
	 */
	public void generateAdjustmentReport(ArrayList<String> adjustmentMessageList) {
		System.out.println("-----------------------------------Sale Report-----------------------------------");
		if (adjustmentMessageList != null) {
			for (String adjustmentMessage : adjustmentMessageList) {
				System.out.println(adjustmentMessage);
			}
		}
		System.exit(1);
	}
}
