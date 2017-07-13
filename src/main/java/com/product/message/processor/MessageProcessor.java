package com.product.message.processor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.product.message.domain.Product;
import com.product.message.enums.OperationIndicator;
import com.product.message.enums.Operations;
import com.product.message.helper.MessageParser;

/**
 * 
 * Class Name: MessageProcessor
 *
 * Description: A MessageProcessor class is responsible for processing the
 * parsed product details.
 * 
 * @author Sujata Bhatter
 * @version 1.0
 * @since 2017-07-02
 */
public class MessageProcessor implements Processor {

	private static final MessageProcessor INSTANCE = new MessageProcessor();

	public static HashMap<String, Product> productHashMap = new HashMap<>();

	private ArrayList<String> adjustmentMessageList = new ArrayList<>();

	private static DecimalFormat df2 = new DecimalFormat(".##");

	/**
	 * This method gets the instance of this class
	 * 
	 * @param Object
	 *            This is the instance of this class
	 */
	public static MessageProcessor getInstance() {

		return INSTANCE;
	}

	/**
	 * This method is responsible for formating the input parameter to two
	 * decimal place
	 * 
	 * @param double
	 *            This is the value with two decimal parts.
	 */
	private double df2Formate(double value) {
		value = Double.parseDouble(df2.format(value));
		return value;

	}

	/**
	 * This method is responsible for parsing the parsed product and add it to
	 * the map post processing
	 * 
	 * @param HashMap
	 *            This is the map with Product Name as key and Product as value
	 */
	public HashMap<String, Product> processInputMessage(String message) {
		MessageParser messageParser = new MessageParser(message);
		if (messageParser.isParsingSuccessful()) {
			Product product = new Product();
			product.setProductName(messageParser.getProductName());
			product.setQuantity(messageParser.getQuantity());
			product.setPricePerPC(messageParser.getPricePerPC());
			product.setOperation(messageParser.getOperation());
			product.setOperationIndicator(messageParser.getOperationIndicator());

			process(product);
		}
		return productHashMap;
	}

	/**
	 * This method is responsible for processing the parsed product and add it
	 * to the map
	 * 
	 * @param Nothing
	 */
	public void process(Product product) {
		double totalProductPrice;
		// Based on operation, process the product
		if (productHashMap != null && productHashMap.containsKey(product.getProductName())) {
			if (Operations.SALE.equals(product.getOperation())) {
				totalProductPrice = getTotalPrice(product.getQuantity(), product.getPricePerPC());
				product.setTotalProductPrice(totalProductPrice);
				updateProduct(productHashMap, product);
			} else {
				updateAdjustment(productHashMap, product);
			}
		} else {
			totalProductPrice = getTotalPrice(product.getQuantity(), product.getPricePerPC());
			product.setTotalProductPrice(df2Formate(totalProductPrice));
			productHashMap.put(product.getProductName(), product);
		}

	}

	/**
	 * This method is responsible for calculating the total price of the product
	 * to the map
	 * 
	 * @param double
	 *            This is the total price of the product
	 */
	private double getTotalPrice(int quantity, double pricePerPC) {
		return (quantity * pricePerPC);
	}

	/**
	 * This method is responsible for processing the product with operation type
	 * as SALE and store the value into the map
	 * 
	 * @param Nothing
	 */
	private void updateProduct(HashMap<String, Product> productHashMap, Product product) {
		product.setAdjustmentMessage(adjustmentMessageList);
		product.setQuantity(product.getQuantity() + productHashMap.get(product.getProductName()).getQuantity());
		product.setTotalProductPrice(df2Formate(product.getTotalProductPrice()
				+ productHashMap.get(product.getProductName()).getTotalProductPrice()));
		productHashMap.put(product.getProductName(), product);
	}

	/**
	 * This method is responsible for processing the product with operation type
	 * as ADJUSTMENT and store the value into the map. And also stores the
	 * adjustment messages in the arraylist
	 * 
	 * @param Nothing
	 */
	private void updateAdjustment(HashMap<String, Product> productHashMap, Product product) {
		Product productHashMapValue = new Product();
		double totalAdjustAmount;
		String adjustmentMessage;
		if (product.getProductName().equals(productHashMap.get(product.getProductName()).getProductName())) {
			productHashMapValue = productHashMap.get(product.getProductName());
			adjustmentMessage = "Operation Performed: " + product.getOperationIndicator() + " "
					+ +product.getPricePerPC() + "p to " + productHashMapValue.getQuantity() + " "
					+ productHashMapValue.getProductName() + ". Price adjusted from "
					+ df2Formate(productHashMapValue.getTotalProductPrice()) + "p";
			totalAdjustAmount = getTotalPrice(productHashMap.get(product.getProductName()).getQuantity(),
					product.getPricePerPC());
			if (product.getOperationIndicator().equals(OperationIndicator.ADD)) {
				productHashMapValue.setTotalProductPrice(df2Formate(
						productHashMap.get(product.getProductName()).getTotalProductPrice() + totalAdjustAmount));
			} else if (product.getOperationIndicator().equals(OperationIndicator.SUBTRACT)) {
				productHashMapValue.setTotalProductPrice(df2Formate(
						productHashMap.get(product.getProductName()).getTotalProductPrice() - totalAdjustAmount));
			} else {
				productHashMapValue.setTotalProductPrice(df2Formate(
						productHashMap.get(product.getProductName()).getTotalProductPrice() + getTotalPrice(
								productHashMap.get(product.getProductName()).getQuantity(), product.getPricePerPC())));
			}
			adjustmentMessage = adjustmentMessage + " to " + df2Formate(productHashMapValue.getTotalProductPrice())
					+ "p";

			adjustmentMessageList.add(adjustmentMessage);
			productHashMapValue.setAdjustmentMessage(adjustmentMessageList);
			productHashMap.put(product.getProductName(), productHashMapValue);
		}
	}

}
