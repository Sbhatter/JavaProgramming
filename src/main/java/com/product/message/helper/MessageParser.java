package com.product.message.helper;

import com.product.message.enums.OperationIndicator;
import com.product.message.enums.Operations;

/**
 * 
 * Class Name: MessageParser
 *
 * Description: A MessageParser class is responsible for the parsing the input
 * file and obtains product details. If parsing is successfull returns true else
 * returns false.
 * 
 * @author Sujata Bhatter
 * @version 1.0
 * @since 2017-07-02
 */

public class MessageParser {

	// Parsed product name
	private String productName;

	// Parsed product price per piece
	private double pricePerPC;

	// Parsed product quantity
	private int quantity;

	// Parsed product operation
	private Operations operation;

	// Parsed product operationIndicator
	private OperationIndicator operationIndicator;

	// Flag sets as true if the parsing is successful else set as false
	boolean parsingSuccessful;

	/**
	 * This method is Parameterised Constructor to parses the input message
	 * 
	 * @param String
	 *            This is the message conating the details of the product to be
	 *            parsed
	 */
	public MessageParser(String message) {
		this.pricePerPC = 0.0;
		this.quantity = 0;
		this.operation = Operations.SALE;
		if (parseMessage(message)) {
			this.parsingSuccessful = true;
		} else {
			this.parsingSuccessful = false;
		}
	}

	/*
	 * Validates the message and and parses properly to obtain product details.
	 * 
	 * @return boolean If the message is parsed successfully returns true else
	 * false
	 */
	private boolean parseMessage(String message) {
		if (message == null || message.isEmpty()) {
			return false;
		}
		String[] messageArray = message.trim().split("\\s+");
		String firstWord = messageArray[0];
		if (firstWord.matches("Add|Subtract|Multiply")) {
			operation = Operations.ADJUSTMENT;
			return adjustmentMessage(messageArray);
		} else if (firstWord.matches("^\\d+")) {
			return multipleSaleMessage(messageArray);
		} else if (messageArray.length == 3 && messageArray[1].contains("at")) {
			return saleMessage(messageArray);
		} else {
			System.out.println("Wrong sales notice");
		}
		return true;
	}

	/*
	 * Validates the message and and parses the message which has details of
	 * single product sale
	 * 
	 * Example of meassage that is parsed in this method - 'apple at 10p'
	 * 
	 * @return boolean If the message is parsed successfully returns true else
	 * false
	 */
	private boolean saleMessage(String[] messageArray) {
		if (messageArray.length > 3 || messageArray.length < 3)
			return false;
		productName = parseType(messageArray[0]);
		pricePerPC = parsePrice(messageArray[2]);
		quantity = 1; // Will be always 1
		return true;
	}

	/*
	 * Validates the message and and parses the message which has details of
	 * multiple product sale
	 * 
	 * E.g. of message that is parsed in this method - 'sales of apples at 10p
	 * each'
	 * 
	 * @return boolean If the message is parsed successfully returns true else
	 * false
	 */
	private boolean multipleSaleMessage(String[] messageArray) {
		if (messageArray.length > 7 || messageArray.length < 7)
			return false;
		productName = parseType(messageArray[3]);
		pricePerPC = parsePrice(messageArray[5]);
		quantity = Integer.parseInt(messageArray[0]);
		return true;
	}

	/*
	 * Validates the message and and parses the message which has details of
	 * adjustment to be done on products
	 * 
	 * E.g. of message that is parsed in this method - 'Add 20p apples'
	 * 
	 * @return boolean If the message is parsed successfully returns true else
	 * false
	 */
	private boolean adjustmentMessage(String[] messageArray) {
		if (messageArray.length > 3 || messageArray.length < 3)
			return false;
		operationIndicator = OperationIndicator.valueOf(messageArray[0].toUpperCase());
		productName = parseType(messageArray[2]);
		quantity = 0;
		pricePerPC = parsePrice(messageArray[1]);
		return true;
	}

	/*
	 * Handles the plural cases of the product
	 * 
	 * E.g. 'apples' will be parsed to 'apple'
	 * 
	 * @return String The lower case of singular product
	 */
	public String parseType(String rawType) {
		if (rawType.toLowerCase().endsWith("s")) {
			rawType = rawType.substring(0, rawType.length() - 1);
		} else if (rawType.toLowerCase().endsWith("ies")) {
			rawType = rawType.substring(0, rawType.length() - 3);
			rawType = rawType + 'y';
		}
		return rawType.toLowerCase();
	}

	/*
	 * Parse the price with unit and returns the numeric part
	 * 
	 * @return double e.g "10p" will become 0.10
	 */
	public double parsePrice(String rawPrice) {
		double price = Double.parseDouble(rawPrice.replaceAll("£|p", ""));
		if (!rawPrice.contains(".")) {
			price = Double.valueOf(Double.valueOf(price) / Double.valueOf("100"));
		}
		return price;
	}

	/*
	 * Gets the name of the parsed product
	 * 
	 * @return String This returns the name of the product
	 */
	public String getProductName() {
		return productName;
	}

	/*
	 * Gets the price per piece of the parsed product
	 * 
	 * @return double This returns the price per product
	 */
	public double getPricePerPC() {
		return pricePerPC;
	}

	/*
	 * Gets the number of the parsed product
	 * 
	 * @return int This returns the quantity of the product
	 */
	public int getQuantity() {
		return quantity;
	}

	/*
	 * Gets the operation performed on the parsed product
	 * 
	 * @return Operations This returns the operation performed on the product
	 */
	public Operations getOperation() {
		return operation;
	}

	/*
	 * Gets the operation indicator for the parsed product
	 * 
	 * @return OperationIndicator This returns the Operation indicator
	 */
	public OperationIndicator getOperationIndicator() {
		return operationIndicator;
	}

	/*
	 * Gets the lfag sets as true if the parsing is successful else false
	 * 
	 * @return boolean This returns the true if parsing is successful
	 */
	public boolean isParsingSuccessful() {
		return parsingSuccessful;
	}

}